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

package dev.daymor.sakuraboot.example.complexfulldto.presentation;

import lombok.Getter;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import dev.daymor.sakuraboot.example.complexfulldto.business.FederationService;
import dev.daymor.sakuraboot.example.complexfulldto.business.dto.FederationDto;
import dev.daymor.sakuraboot.example.complexfulldto.persistence.Federation;
import dev.daymor.sakuraboot.example.complexfulldto.presentation.filter.FederationFilter;
import dev.daymor.sakuraboot.example.complexfulldto.presentation.model.FederationModelAssembler;
import dev.daymor.sakuraboot.example.complexfulldto.util.FederationTestUtil;
import dev.daymor.sakuraboot.test.bulk.api.presentation.CriteriaBulkControllerTest;
import dev.daymor.sakuraboot.test.hypermedia.api.HypermediaTest;
import dev.daymor.sakuraboot.test.specification.api.presentation.CriteriaControllerTest;

@SuppressWarnings({
    "java:S2187", "JUnitTestCaseWithNoTests"
})
@Getter
public class FederationControllerTest
    implements
    CriteriaControllerTest<Federation, Long, FederationDto, FederationFilter>,
    CriteriaBulkControllerTest<Federation, Long, FederationDto,
        FederationFilter>,
    HypermediaTest<FederationDto, FederationModelAssembler> {

    private final FederationTestUtil util = new FederationTestUtil();

    @InjectMocks
    private FederationController controller;

    @Mock
    private FederationService service;

    @Mock
    private FederationModelAssembler modelAssembler;
}
