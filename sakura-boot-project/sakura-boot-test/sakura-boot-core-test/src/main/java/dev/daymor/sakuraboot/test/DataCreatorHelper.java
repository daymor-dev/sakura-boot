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

package dev.daymor.sakuraboot.test;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.lang.Nullable;
import org.springframework.test.util.ReflectionTestUtils;

import dev.daymor.sakuraboot.DataPresentation;
import dev.daymor.sakuraboot.configuration.GlobalSpecification;
import dev.daymor.sakuraboot.util.RelationshipUtils;

/**
 * The data creator helper used to create all kinds of {@link DataPresentation}
 * with every field set to random values
 * using reflection.
 *
 * @author Malcolm Rozé
 * @since  0.1.0
 */
public final class DataCreatorHelper {

    /**
     * The Bean holder map with the class name as key.
     */
    private static final Map<String, DataHolder<?, ?>> DATA_HOLDERS
        = new ConcurrentHashMap<>();

    private DataCreatorHelper() {

        throw new UnsupportedOperationException(
            "This class is not meant to be instantiated.");
    }

    /**
     * Update id of the given {@link DataPresentation}.
     *
     * @param  <D>                 the type of the data.
     * @param  <I>                 the type of the id.
     * @param  dataForIds          The other data from which to get the
     *                             ids.
     * @param  data                the data to update.
     * @param  globalSpecification The {@link GlobalSpecification}
     *                             that will help for the relationships.
     * @return                     the updated data.
     */
    public static <
        D extends DataPresentation<I>,
        I extends Comparable<? super I> & Serializable> D updateId(
            @Nullable final DataPresentation<I> dataForIds, final D data,
            final GlobalSpecification globalSpecification) {

        if (dataForIds == null) {

            updateIdNull(data, new ArrayList<>(), globalSpecification);
        } else {

            updateIdDataNotNull(dataForIds, data, new ArrayList<>(),
                globalSpecification);
        }
        return data;
    }

    @Nullable
    private static
        Object getFieldValue(final Object target, final String name) {

        try {

            final Field field = target.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(target);
        } catch (final NoSuchFieldException | IllegalAccessException e) {

            throw new RuntimeException(e);
        }
    }

    private static void setFieldValue(
        final Object target, final String name, @Nullable final Object value) {

        try {

            final Field field = target.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(target, value);
        } catch (final NoSuchFieldException ignored) {

            // Ignore field
        } catch (final IllegalAccessException e) {

            throw new RuntimeException(e);
        }
    }

    private static void visitIfNotVisited(
        @Nullable final DataPresentation<?> candidate,
        final List<DataPresentation<?>> visited,
        final Consumer<DataPresentation<?>> updater) {

        if (candidate != null && !visited.contains(candidate)) {

            updater.accept(candidate);
        }
    }

    private static <
        D extends DataPresentation<I>,
        I extends Comparable<? super I> & Serializable> void
        updateIdDataNotNull(
            final DataPresentation<?> dataForIds, final D data,
            final List<DataPresentation<?>> visited,
            final GlobalSpecification globalSpecification) {

        visited.add(data);

        final Field idField;

        try {

            idField = data.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(data, dataForIds.getId());
        } catch (final NoSuchFieldException | IllegalAccessException e) {

            throw new RuntimeException(e);
        }

        RelationshipUtils.doWithRelationFields(dataForIds,
            (
                final Field field,
                final Object relationshipForIds) -> updateRelationshipIdNotNull(
                    data, visited, globalSpecification, field,
                    relationshipForIds),
            (
                final Field field,
                final Collection<
                    ?> relationshipsForIds) -> updateRelationshipsIdNotNull(
                        data, visited, globalSpecification, field,
                        relationshipsForIds),
            globalSpecification);
    }

    private static <
        D extends DataPresentation<I>,
        I extends Comparable<? super I> & Serializable> void
        updateRelationshipIdNotNull(
            final D data, final List<DataPresentation<?>> visited,
            final GlobalSpecification globalSpecification, final Field field,
            final Object relationshipForIds) {

        if (relationshipForIds instanceof final DataPresentation<
            ?> relationshipDataForIds) {

            final DataPresentation<?> relationship
                = (DataPresentation<?>) getFieldValue(data, field.getName());
            visitIfNotVisited(relationship, visited,
                r -> updateIdDataNotNull(relationshipDataForIds, r, visited,
                    globalSpecification));
            setFieldValue(data, field.getName() + "Id",
                relationshipDataForIds.getId());
        }
    }

    private static <
        D extends DataPresentation<I>,
        I extends Comparable<? super I> & Serializable> void
        updateRelationshipsIdNotNull(
            final D data, final List<DataPresentation<?>> visited,
            final GlobalSpecification globalSpecification, final Field field,
            final Collection<?> relationshipsForIds) {

        final Iterator<?> relationships;
        final Object fieldValue = getFieldValue(data, field.getName());

        if (fieldValue != null) {

            relationships = ((Iterable<?>) fieldValue).iterator();
        } else {

            relationships = Collections.emptyIterator();
        }

        for (final Object relationshipForIds: relationshipsForIds) {

            if (relationshipForIds instanceof final DataPresentation<
                ?> relationshipDataForIds) {

                if (!relationships.hasNext()) {

                    break;
                }
                final DataPresentation<?> relationship
                    = (DataPresentation<?>) relationships.next();
                visitIfNotVisited(relationship, visited,
                    r -> updateIdDataNotNull(relationshipDataForIds, r, visited,
                        globalSpecification));
            }
        }

        setFieldValue(data, field.getName() + "Id",
            relationshipsForIds.stream()
                .filter(DataPresentation.class::isInstance)
                .map(DataPresentation.class::cast)
                .map(DataPresentation<I>::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toUnmodifiableSet()));
    }

    private static <
        D extends DataPresentation<I>,
        I extends Comparable<? super I> & Serializable> void updateIdNull(
            final D data, final List<DataPresentation<?>> visited,
            final GlobalSpecification globalSpecification) {

        visited.add(data);

        final Field idField;

        try {

            idField = data.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(data, null);
        } catch (final NoSuchFieldException | IllegalAccessException e) {

            throw new RuntimeException(e);
        }

        RelationshipUtils.doWithRelationFields(data,
            (
                final Field field,
                final Object relationship) -> updateRelationshipIdNull(data,
                    visited, globalSpecification, relationship),
            (
                final Field field,
                final Collection<?> relationships) -> updateRelationshipsIdNull(
                    data, visited, globalSpecification, relationships),
            globalSpecification);
    }

    private static <
        D extends DataPresentation<I>,
        I extends Comparable<? super I> & Serializable> void
        updateRelationshipIdNull(
            final D data, final List<DataPresentation<?>> visited,
            final GlobalSpecification globalSpecification,
            final Object relationship) {

        if (relationship instanceof final DataPresentation<
            ?> relationshipData) {

            visitIfNotVisited(relationshipData, visited,
                r -> updateIdNull(r, visited, globalSpecification));
            setFieldValue(data,
                relationshipData.getClass().getSimpleName() + "Id", null);
        }
    }

    private static <
        D extends DataPresentation<I>,
        I extends Comparable<? super I> & Serializable> void
        updateRelationshipsIdNull(
            final D data, final List<DataPresentation<?>> visited,
            final GlobalSpecification globalSpecification,
            final Collection<?> relationships) {

        for (final Object relationship: relationships) {

            if (relationship instanceof final DataPresentation<
                ?> relationshipData) {

                visitIfNotVisited(relationshipData, visited,
                    r -> updateIdNull(r, visited, globalSpecification));
            }
        }
        setFieldValue(data, relationships.getClass().getSimpleName() + "Id",
            Set.of());
    }

    /**
     * Get a data without id for the given data class.
     * Remove also the id from relationships.
     *
     * @param  <D>                 The type of the data.
     * @param  <I>                 The type of the id.
     * @param  dataClass           the data class of the data.
     * @param  globalSpecification The {@link GlobalSpecification}
     *                             that will help for the relationships.
     * @return                     the data without id.
     */
    public static <
        D extends DataPresentation<I>,
        I extends Comparable<? super I> & Serializable> D getDataWithoutId(
            final Class<D> dataClass,
            final GlobalSpecification globalSpecification) {

        final D data
            = SerializationUtils.clone(getDataHolder(dataClass).data());
        return updateId(null, data, globalSpecification);
    }

    /**
     * Get a data with the given id for the given data class.
     *
     * @param  <D>       The type of the data.
     * @param  <I>       The type of the id.
     * @param  dataClass the data class of the data.
     * @param  id        the id of the data.
     * @return           the data.
     */
    public static <
        D extends DataPresentation<I>,
        I extends Comparable<? super I> & Serializable> D
        getData(final Class<D> dataClass, @Nullable final I id) {

        final D data = getDataHolder(dataClass).data();
        ReflectionTestUtils.setField(data, "id", id);
        return data;
    }

    /**
     * Get a second data with the given id for the given data class.
     *
     * @param  <D>       The type of the data.
     * @param  <I>       The type of the id.
     * @param  dataClass the data class of the data.
     * @param  id        the id of the data.
     * @return           the second data.
     */
    public static <
        D extends DataPresentation<I>,
        I extends Comparable<? super I> & Serializable> D
        getSecondData(final Class<D> dataClass, @Nullable final I id) {

        final D data = getDataHolder(dataClass).secondData();
        ReflectionTestUtils.setField(data, "id", id);
        return data;
    }

    /**
     * Get a different data with the given id for the given data class.
     *
     * @param  <D>       The type of the data.
     * @param  <I>       The type of the id.
     * @param  dataClass the data class of the data.
     * @param  id        the id of the data.
     * @return           the different data.
     */
    public static <
        D extends DataPresentation<I>,
        I extends Comparable<? super I> & Serializable> D
        getDifferentData(final Class<D> dataClass, @Nullable final I id) {

        final D data = getDataHolder(dataClass).differentData();
        ReflectionTestUtils.setField(data, "id", id);
        return data;
    }

    /**
     * Get a partial data with the given id for the given data class.
     *
     * @param  <D>       The type of the data.
     * @param  <I>       The type of the id.
     * @param  dataClass the data class of the data.
     * @param  id        the id of the data.
     * @return           the partial data.
     */
    public static <
        D extends DataPresentation<I>,
        I extends Comparable<? super I> & Serializable> D
        getPartialData(final Class<D> dataClass, @Nullable final I id) {

        final D data = getDataHolder(dataClass).partialData();
        ReflectionTestUtils.setField(data, "id", id);
        return data;
    }

    private static <
        D extends DataPresentation<I>,
        I extends Comparable<? super I> & Serializable> DataHolder<D, I>
        getDataHolder(final Class<D> dataClass) {

        if (!DATA_HOLDERS.containsKey(dataClass.getName())) {

            createDatas(dataClass);
        }

        @SuppressWarnings("unchecked")
        final DataHolder<D, I> dataHolder
            = (DataHolder<D, I>) DATA_HOLDERS.get(dataClass.getName());
        return dataHolder;
    }

    private static <
        D extends DataPresentation<I>,
        I extends Comparable<? super I> & Serializable> void
        createDatas(final Class<D> dataClass) {

        final D differentData;

        try {

            differentData
                = BeanCreatorHelper.createBeanFromConstructor(dataClass);
        } catch (final InstantiationException e) {

            throw new RuntimeException(e);
        }
        final D partialData;

        try {

            partialData = BeanCreatorHelper
                .createBeanFromConstructorWithNullFields(dataClass);
        } catch (final InstantiationException e) {

            throw new RuntimeException(e);
        }

        final BeanCreatorHelper.BeanHolder<D> beans
            = BeanCreatorHelper.getBeanHolder(dataClass);
        final D data = beans.bean();
        final D secondData = beans.secondBean();

        DATA_HOLDERS.put(dataClass.getName(),
            new DataHolder<>(data, secondData, differentData, partialData));
    }

    /**
     * A {@link DataPresentation} holder with two identical data, a different
     * data and a partial data.
     *
     * @param data          the first data.
     * @param secondData    the second data.
     * @param differentData the different data.
     * @param partialData   the partial.
     * @param <D>           the data type.
     * @param <I>           the id type.
     */
    public record DataHolder<D extends DataPresentation<
        I>, I extends Comparable<? super I> & Serializable>(
            D data, D secondData, D differentData, D partialData) {}
}
