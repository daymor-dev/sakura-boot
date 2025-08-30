/*
 * Copyright (C) 2023-2024 Malcolm Rozé.
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

package dev.daymor.sakuraboot.example.complexfulldto.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import dev.daymor.sakuraboot.basic.api.relationship.annotations.Relationshipable;
import dev.daymor.sakuraboot.bulk.api.business.CriteriaBulkService;
import dev.daymor.sakuraboot.cache.api.Cacheable;
import dev.daymor.sakuraboot.cache.api.CachingUtil;
import dev.daymor.sakuraboot.example.complexfulldto.business.dto.HobbyDto;
import dev.daymor.sakuraboot.example.complexfulldto.business.mapper.AbstractHobbyMapper;
import dev.daymor.sakuraboot.example.complexfulldto.persistence.Hobby;
import dev.daymor.sakuraboot.example.complexfulldto.persistence.HobbyRepository;
import dev.daymor.sakuraboot.example.complexfulldto.presentation.filter.HobbyFilter;
import dev.daymor.sakuraboot.log.api.Loggable;
import dev.daymor.sakuraboot.mapper.api.Mappable;
import dev.daymor.sakuraboot.specification.api.business.CriteriaService;
import dev.daymor.sakuraboot.specification.api.business.SpecificationBuilder;

@Relationshipable
@Service
@Getter
@RequiredArgsConstructor
public class HobbyService
    implements CriteriaService<Hobby, Long, HobbyFilter>,
    CriteriaBulkService<Hobby, Long, HobbyFilter>, Cacheable,
    Mappable<Hobby, HobbyDto>, Loggable {

    private final HobbyRepository repository;

    private final ObjectMapper objectMapper;

    private final SpecificationBuilder<Hobby> specificationBuilder;

    private final CachingUtil cachingUtil;

    private final AbstractHobbyMapper mapper;
}
