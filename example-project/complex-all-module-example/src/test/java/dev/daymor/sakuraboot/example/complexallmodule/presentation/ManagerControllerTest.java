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

package dev.daymor.sakuraboot.example.complexallmodule.presentation;

import java.util.UUID;

import lombok.Getter;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import dev.daymor.sakuraboot.example.complexallmodule.business.ManagerService;
import dev.daymor.sakuraboot.example.complexallmodule.business.dto.ManagerDto;
import dev.daymor.sakuraboot.example.complexallmodule.persistence.Manager;
import dev.daymor.sakuraboot.example.complexallmodule.presentation.filter.ManagerFilter;
import dev.daymor.sakuraboot.example.complexallmodule.presentation.model.ManagerModelAssembler;
import dev.daymor.sakuraboot.example.complexallmodule.util.ManagerTestUtil;
import dev.daymor.sakuraboot.hypermedia.api.Hypermedia;
import dev.daymor.sakuraboot.test.bulk.api.presentation.CriteriaBulkControllerTest;
import dev.daymor.sakuraboot.test.hypermedia.api.HypermediaTest;
import dev.daymor.sakuraboot.test.specification.api.presentation.CriteriaControllerTest;

@SuppressWarnings({
    "java:S2187", "JUnitTestCaseWithNoTests"
})
@Getter
public class ManagerControllerTest
    implements CriteriaControllerTest<Manager, UUID, ManagerDto, ManagerFilter>,
    CriteriaBulkControllerTest<Manager, UUID, ManagerDto, ManagerFilter>,
    HypermediaTest<ManagerDto, ManagerModelAssembler> {

    private final ManagerTestUtil util = new ManagerTestUtil();

    @InjectMocks
    private ManagerController controller;

    @Mock
    private ManagerService service;

    @Mock
    private ManagerModelAssembler modelAssembler;

    @Override
    public Class<ManagerFilter> getExpectedFilterClass() {

        return ManagerFilter.class;
    }

    @Override
    public Hypermedia<ManagerDto, ManagerModelAssembler> getHypermedia() {

        return controller;
    }

    @Override
    public Class<ManagerDto> getExpectedDataClass() {

        return ManagerDto.class;
    }
}
