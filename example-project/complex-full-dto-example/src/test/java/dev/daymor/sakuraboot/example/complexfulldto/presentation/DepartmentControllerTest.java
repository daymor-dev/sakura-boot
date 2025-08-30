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

import dev.daymor.sakuraboot.example.complexfulldto.business.DepartmentService;
import dev.daymor.sakuraboot.example.complexfulldto.business.dto.DepartmentDto;
import dev.daymor.sakuraboot.example.complexfulldto.persistence.Department;
import dev.daymor.sakuraboot.example.complexfulldto.presentation.filter.DepartmentFilter;
import dev.daymor.sakuraboot.example.complexfulldto.presentation.model.DepartmentModelAssembler;
import dev.daymor.sakuraboot.example.complexfulldto.util.DepartmentTestUtil;
import dev.daymor.sakuraboot.test.bulk.api.presentation.CriteriaBulkControllerTest;
import dev.daymor.sakuraboot.test.hypermedia.api.HypermediaTest;
import dev.daymor.sakuraboot.test.specification.api.presentation.CriteriaControllerTest;

@SuppressWarnings({
    "java:S2187", "JUnitTestCaseWithNoTests"
})
@Getter
public class DepartmentControllerTest
    implements
    CriteriaControllerTest<Department, Long, DepartmentDto, DepartmentFilter>,
    CriteriaBulkControllerTest<Department, Long, DepartmentDto,
        DepartmentFilter>,
    HypermediaTest<DepartmentDto, DepartmentModelAssembler> {

    private final DepartmentTestUtil util = new DepartmentTestUtil();

    @InjectMocks
    private DepartmentController controller;

    @Mock
    private DepartmentService service;

    @Mock
    private DepartmentModelAssembler modelAssembler;
}
