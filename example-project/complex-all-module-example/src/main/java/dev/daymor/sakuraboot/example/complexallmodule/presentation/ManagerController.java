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
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.daymor.sakuraboot.bulk.api.presentation.CriteriaBulkController;
import dev.daymor.sakuraboot.example.complexallmodule.business.ManagerService;
import dev.daymor.sakuraboot.example.complexallmodule.business.dto.ManagerDto;
import dev.daymor.sakuraboot.example.complexallmodule.persistence.Manager;
import dev.daymor.sakuraboot.example.complexallmodule.presentation.filter.ManagerFilter;
import dev.daymor.sakuraboot.example.complexallmodule.presentation.model.ManagerModelAssembler;
import dev.daymor.sakuraboot.hypermedia.api.Hypermedia;
import dev.daymor.sakuraboot.log.api.Loggable;
import dev.daymor.sakuraboot.specification.api.presentation.CriteriaController;

@RestController
@RequestMapping("/managers")
@Getter
@RequiredArgsConstructor
public class ManagerController
    implements CriteriaController<Manager, UUID, ManagerDto, ManagerFilter>,
    CriteriaBulkController<Manager, UUID, ManagerDto, ManagerFilter>,
    Hypermedia<ManagerDto, ManagerModelAssembler>, Loggable {

    private final ManagerService service;

    private final ManagerModelAssembler modelAssembler;
}
