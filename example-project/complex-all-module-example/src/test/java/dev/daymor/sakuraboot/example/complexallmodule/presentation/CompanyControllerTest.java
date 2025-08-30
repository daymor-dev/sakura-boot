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

package dev.daymor.sakuraboot.example.complexallmodule.presentation;

import java.util.UUID;

import lombok.Getter;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import dev.daymor.sakuraboot.example.complexallmodule.business.CompanyService;
import dev.daymor.sakuraboot.example.complexallmodule.business.dto.CompanyDto;
import dev.daymor.sakuraboot.example.complexallmodule.persistence.Company;
import dev.daymor.sakuraboot.example.complexallmodule.presentation.filter.CompanyFilter;
import dev.daymor.sakuraboot.example.complexallmodule.presentation.model.CompanyModelAssembler;
import dev.daymor.sakuraboot.example.complexallmodule.util.CompanyTestUtil;
import dev.daymor.sakuraboot.hypermedia.api.Hypermedia;
import dev.daymor.sakuraboot.test.bulk.api.presentation.CriteriaBulkControllerTest;
import dev.daymor.sakuraboot.test.file.api.presentation.FileControllerTest;
import dev.daymor.sakuraboot.test.hypermedia.api.HypermediaTest;
import dev.daymor.sakuraboot.test.specification.api.presentation.CriteriaControllerTest;

@SuppressWarnings({
    "java:S2187", "JUnitTestCaseWithNoTests"
})
@Getter
public class CompanyControllerTest
    implements CriteriaControllerTest<Company, UUID, CompanyDto, CompanyFilter>,
    CriteriaBulkControllerTest<Company, UUID, CompanyDto, CompanyFilter>,
    HypermediaTest<CompanyDto, CompanyModelAssembler>,
    FileControllerTest<Company, UUID> {

    private final CompanyTestUtil util = new CompanyTestUtil();

    @InjectMocks
    private CompanyController controller;

    @Mock
    private CompanyService service;

    @Mock
    private CompanyModelAssembler modelAssembler;

    @Override
    public Class<CompanyFilter> getExpectedFilterClass() {

        return CompanyFilter.class;
    }

    @Override
    public Hypermedia<CompanyDto, CompanyModelAssembler> getHypermedia() {

        return controller;
    }

    @Override
    public Class<CompanyDto> getExpectedDataClass() {

        return CompanyDto.class;
    }
}
