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

package dev.daymor.sakuraboot.example.complexallmodule.business;

import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import dev.daymor.sakuraboot.bulk.api.business.CriteriaBulkService;
import dev.daymor.sakuraboot.cache.api.Cacheable;
import dev.daymor.sakuraboot.cache.api.CachingUtil;
import dev.daymor.sakuraboot.example.complexallmodule.business.dto.EmployeeDto;
import dev.daymor.sakuraboot.example.complexallmodule.business.mapper.AbstractEmployeeMapper;
import dev.daymor.sakuraboot.example.complexallmodule.persistence.Employee;
import dev.daymor.sakuraboot.example.complexallmodule.persistence.EmployeeRepository;
import dev.daymor.sakuraboot.example.complexallmodule.presentation.filter.EmployeeFilter;
import dev.daymor.sakuraboot.log.api.Loggable;
import dev.daymor.sakuraboot.mapper.api.Mappable;
import dev.daymor.sakuraboot.specification.api.business.CriteriaService;
import dev.daymor.sakuraboot.specification.api.business.SpecificationBuilder;

@Service
@Getter
@RequiredArgsConstructor
public class EmployeeService
    implements CriteriaService<Employee, UUID, EmployeeFilter>,
    CriteriaBulkService<Employee, UUID, EmployeeFilter>, Cacheable,
    Mappable<Employee, EmployeeDto>, Loggable {

    private final EmployeeRepository repository;

    private final ObjectMapper objectMapper;

    private final SpecificationBuilder<Employee> specificationBuilder;

    private final CachingUtil cachingUtil;

    private final AbstractEmployeeMapper mapper;
}
