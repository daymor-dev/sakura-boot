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
import dev.daymor.sakuraboot.example.complexallmodule.business.dto.CompanyDto;
import dev.daymor.sakuraboot.example.complexallmodule.business.mapper.CompanyMapper;
import dev.daymor.sakuraboot.example.complexallmodule.persistence.Company;
import dev.daymor.sakuraboot.example.complexallmodule.persistence.CompanyRepository;
import dev.daymor.sakuraboot.example.complexallmodule.presentation.filter.CompanyFilter;
import dev.daymor.sakuraboot.file.api.business.FileService;
import dev.daymor.sakuraboot.log.api.Loggable;
import dev.daymor.sakuraboot.mapper.api.Mappable;
import dev.daymor.sakuraboot.specification.api.business.CriteriaService;
import dev.daymor.sakuraboot.specification.api.business.SpecificationBuilder;

@Service
@Getter
@RequiredArgsConstructor
public class CompanyService
    implements CriteriaService<Company, UUID, CompanyFilter>,
    CriteriaBulkService<Company, UUID, CompanyFilter>, Cacheable,
    Mappable<Company, CompanyDto>, Loggable, FileService<Company, UUID> {

    private final CompanyRepository repository;

    private final ObjectMapper objectMapper;

    private final SpecificationBuilder<Company> specificationBuilder;

    private final CachingUtil cachingUtil;

    private final CompanyMapper mapper;
}
