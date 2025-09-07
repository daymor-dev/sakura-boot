/*
 * Copyright (C) 2023-2025 Malcolm Rozé.
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

package dev.daymor.sakuraboot.example.complexallmodule.business;

import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import dev.daymor.sakuraboot.cache.api.CachingUtil;
import dev.daymor.sakuraboot.example.complexallmodule.business.dto.FederationDto;
import dev.daymor.sakuraboot.example.complexallmodule.business.mapper.AbstractFederationMapper;
import dev.daymor.sakuraboot.example.complexallmodule.persistence.Federation;
import dev.daymor.sakuraboot.example.complexallmodule.persistence.FederationRepository;
import dev.daymor.sakuraboot.example.complexallmodule.presentation.filter.FederationFilter;
import dev.daymor.sakuraboot.example.complexallmodule.util.FederationTestUtil;
import dev.daymor.sakuraboot.specification.api.business.SpecificationBuilder;
import dev.daymor.sakuraboot.test.bulk.api.business.CriteriaBulkServiceTest;
import dev.daymor.sakuraboot.test.cache.api.CacheableTest;
import dev.daymor.sakuraboot.test.mapper.api.MappableTest;
import dev.daymor.sakuraboot.test.specification.api.business.CriteriaServiceTest;

@SuppressWarnings({
    "java:S2187", "JUnitTestCaseWithNoTests"
})
@Getter
public class FederationServiceTest
    implements CriteriaServiceTest<Federation, UUID, FederationFilter>,
    CriteriaBulkServiceTest<Federation, UUID, FederationFilter>, CacheableTest,
    MappableTest<Federation, FederationDto> {

    private final FederationTestUtil util = new FederationTestUtil();

    @InjectMocks
    private FederationService service;

    @Mock
    private FederationRepository repository;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private SpecificationBuilder<Federation> specificationBuilder;

    @Mock
    private CachingUtil cachingUtil;

    @Mock
    private AbstractFederationMapper mapper;
}
