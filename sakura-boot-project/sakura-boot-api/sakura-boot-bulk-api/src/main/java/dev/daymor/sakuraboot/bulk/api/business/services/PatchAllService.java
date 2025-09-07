/*
 * Copyright (C) 2025 Malcolm Rozé.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.daymor.sakuraboot.bulk.api.business.services;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import dev.daymor.sakuraboot.DataPresentation;
import dev.daymor.sakuraboot.SuperService;
import dev.daymor.sakuraboot.basic.api.persistence.BasicRepository;
import dev.daymor.sakuraboot.basic.api.relationship.FetchRelationshipRepository;
import dev.daymor.sakuraboot.bulk.api.persistence.BulkRepository;
import dev.daymor.sakuraboot.cache.api.annotations.PutCache;
import dev.daymor.sakuraboot.exceptions.BadRequestException;
import dev.daymor.sakuraboot.exceptions.NotFoundException;
import dev.daymor.sakuraboot.log.api.annotations.Logging;
import dev.daymor.sakuraboot.mapper.api.annotations.Mapping;
import dev.daymor.sakuraboot.util.RelationshipUtils;

/**
 * The service interface for patchAll operation.
 * <p>
 * <b>Example:</b>
 * </p>
 * <p>
 * To create a service interface that inherits from {@link PatchAllService},
 * follow these steps:
 * </p>
 * <p>
 * Create a new service interface:
 * </p>
 * <blockquote>
 *
 * <pre>
 * public interface YourService
 *     extends PatchAllService&lt;YourEntity, YourIdType&gt; {}
 * </pre>
 *
 * </blockquote>
 * <p>
 * To create a service class that implements {@link PatchAllService}, follow
 * these steps:
 * </p>
 * <p>
 * Create a new service class:
 * </p>
 * <blockquote>
 *
 * <pre>
 * &#064;Service
 * public class YourService //
 *     implements PatchAllService&lt;YourEntity, YourIdType&gt; {
 *
 *     // Or implements your interface that extends BasicService.
 *     private final YourRepository repository;
 *
 *     private final ObjectMapper objectMapper;
 *
 *     public YourService(
 *         final YourRepository repository, final ObjectMapper objectMapper) {
 *
 *         this.repository = repository;
 *         this.objectMapper = objectMapper;
 *     }
 *
 *     public YourRepository getRepository() {
 *
 *         return this.repository;
 *     }
 *
 *     public ObjectMapper getObjectMapper() {
 *
 *         return objectMapper;
 *     }
 * }
 * </pre>
 *
 * </blockquote>
 *
 * @param  <E> The {@link DataPresentation} type.
 * @param  <I> The ID of type Comparable and Serializable.
 * @author     Malcolm Rozé
 * @see        SuperService
 * @see        PatchAllService#getObjectMapper()
 * @see        PatchAllService#patchAll(Collection)
 * @since      0.1.2
 */
public interface PatchAllService<E extends DataPresentation<I>,
    I extends Comparable<? super I> & Serializable> extends SuperService<E, I> {

    @Override
    BasicRepository<E, I> getRepository();

    /**
     * Give an {@link ObjectMapper} use to partial update entity.
     *
     * @return An ObjectMapper.
     */
    ObjectMapper getObjectMapper();

    /**
     * Partially updates all existing {@link DataPresentation} with the provided
     * data from all the {@link DataPresentation} in the underlying data
     * storage, using an {@link ObjectMapper}.
     *
     * @param  datas               All the partial {@link DataPresentation}
     *                             containing the
     *                             fields to update.
     * @return                     All the updated {@link DataPresentation}.
     * @throws BadRequestException If the provided {@link DataPresentation} does
     *                             not have an ID.
     * @throws NotFoundException   If no {@link DataPresentation} with the
     *                             specified ID exists in the data storage.
     */
    @Transactional
    @PutCache(key = "#datas[i].id", refreshEntityCache = true)
    @Mapping
    @Logging
    default List<DataPresentation<I>> patchAll(
        final Collection<DataPresentation<I>> datas) {

        final List<I> datasId = getIds(datas);

        final List<E> entities;

        if (getRepository() instanceof final FetchRelationshipRepository<?,
            ?> repository) {

            @SuppressWarnings("unchecked")
            final FetchRelationshipRepository<E, I> fetchRepository
                = (FetchRelationshipRepository<E, I>) repository;
            entities = fetchRepository.findAllEagerRelationship(datasId,
                getEntityClass());
        } else {

            entities = getRepository().findAllById(datasId);
        }

        if (entities.size() != datas.size()) {

            throw new NotFoundException(getEntityClass().getSimpleName());
        }

        initializeProxy(datas);
        final List<E> updatedEntities = new ArrayList<>(entities.size());

        for (final DataPresentation<I> data: datas) {

            final Map<String, Object> entityMap = getObjectMapper()
                .convertValue(data, new TypeReference<>() {});

            final Map<String, Object> objectMapNonNullValues = entityMap
                .entrySet()
                .stream()
                .filter(entityEntry -> Objects.nonNull(entityEntry.getValue()))
                .filter(entityEntry -> getAllFields(getEntityClass())
                    .map(Field::getName)
                    .anyMatch(entityEntry.getKey()::equals))
                .collect(
                    Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            // MUST remove the null value manually because
            // setSerializationInclusion(Include.NON_NULL)
            // don't work with the @NotNull field.

            try {

                E currentEntity = entities.stream()
                    .filter(entity -> entity.getId() != null)
                    .filter(entity -> entity.getId().equals(data.getId()))
                    .findFirst()
                    .orElseThrow();
                currentEntity = getObjectMapper().updateValue(currentEntity,
                    objectMapNonNullValues);
                updatedEntities.add(currentEntity);
            } catch (final JsonMappingException e) {

                throw new BadRequestException("Cannot partial update : "
                    + getEntityClass().getSimpleName(), e);
            }
        }

        final List<E> result;

        if (getRepository() instanceof final BulkRepository<?,
            ?> bulkRepository) {

            @SuppressWarnings("unchecked")
            final BulkRepository<E, I> castBulkRepository
                = (BulkRepository<E, I>) bulkRepository;
            result = castBulkRepository.bulkUpdate(updatedEntities);
        } else {

            result = getRepository().saveAll(updatedEntities);
        }
        @SuppressWarnings("unchecked")
        final List<DataPresentation<I>> castResult
            = (List<DataPresentation<I>>) result;
        return castResult;
    }

    private void initializeProxy(final Collection<DataPresentation<I>> datas) {

        datas.forEach(data -> RelationshipUtils.doWithRelationFields(data,
            (final Field field, final Object relationship) -> {

                if (relationship instanceof final HibernateProxy proxy) {

                    Hibernate.initialize(proxy);
                }
            }, null));
    }

    private List<I> getIds(final Collection<DataPresentation<I>> datas) {

        if (datas.stream()
            .map(DataPresentation::getId)
            .anyMatch(Objects::isNull)) {

            throw new BadRequestException(
                "Can't partially update an entity when they don't have an ID : "
                    + getEntityClass().getSimpleName());
        }

        return datas.stream()
            .map(DataPresentation::getId)
            .filter(Objects::nonNull)
            .toList();
    }

    private static Stream<Field> getAllFields(@Nullable final Class<?> clazz) {

        if (clazz == null || clazz.equals(Object.class)) {

            return Stream.empty();
        }
        return Stream.concat(Arrays.stream(clazz.getDeclaredFields()),
            getAllFields(clazz.getSuperclass()));
    }
}
