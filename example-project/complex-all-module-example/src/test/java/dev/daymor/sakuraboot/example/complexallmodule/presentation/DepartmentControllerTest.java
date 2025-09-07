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

package dev.daymor.sakuraboot.example.complexallmodule.presentation;

import java.util.UUID;

import lombok.Getter;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import dev.daymor.sakuraboot.example.complexallmodule.business.DepartmentService;
import dev.daymor.sakuraboot.example.complexallmodule.business.dto.DepartmentDto;
import dev.daymor.sakuraboot.example.complexallmodule.persistence.Department;
import dev.daymor.sakuraboot.example.complexallmodule.presentation.filter.DepartmentFilter;
import dev.daymor.sakuraboot.example.complexallmodule.presentation.model.DepartmentModelAssembler;
import dev.daymor.sakuraboot.example.complexallmodule.util.DepartmentTestUtil;
import dev.daymor.sakuraboot.hypermedia.api.Hypermedia;
import dev.daymor.sakuraboot.test.bulk.api.presentation.CriteriaBulkControllerTest;
import dev.daymor.sakuraboot.test.file.api.presentation.FileControllerTest;
import dev.daymor.sakuraboot.test.hypermedia.api.HypermediaTest;
import dev.daymor.sakuraboot.test.specification.api.presentation.CriteriaControllerTest;

@SuppressWarnings({
    "java:S2187", "JUnitTestCaseWithNoTests"
})
@Getter
public class DepartmentControllerTest
    implements
    CriteriaControllerTest<Department, UUID, DepartmentDto, DepartmentFilter>,
    CriteriaBulkControllerTest<Department, UUID, DepartmentDto,
        DepartmentFilter>,
    HypermediaTest<DepartmentDto, DepartmentModelAssembler>,
    FileControllerTest<Department, UUID> {

    private final DepartmentTestUtil util = new DepartmentTestUtil();

    @InjectMocks
    private DepartmentController controller;

    @Mock
    private DepartmentService service;

    @Mock
    private DepartmentModelAssembler modelAssembler;

    @Override
    public Class<DepartmentFilter> getExpectedFilterClass() {

        return DepartmentFilter.class;
    }

    @Override
    public Hypermedia<DepartmentDto, DepartmentModelAssembler> getHypermedia() {

        return controller;
    }

    @Override
    public Class<DepartmentDto> getExpectedDataClass() {

        return DepartmentDto.class;
    }
}
