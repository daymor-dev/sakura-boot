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

package dev.daymor.sakuraboot.example.complexhypermediamodule.presentation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.daymor.sakuraboot.basic.api.presentation.BasicController;
import dev.daymor.sakuraboot.example.complexhypermediamodule.business.ManagerService;
import dev.daymor.sakuraboot.example.complexhypermediamodule.persistence.Manager;
import dev.daymor.sakuraboot.example.complexhypermediamodule.presentation.model.ManagerModelAssembler;
import dev.daymor.sakuraboot.hypermedia.api.Hypermedia;

@RestController
@RequestMapping("/managers")
@Getter
@RequiredArgsConstructor
public class ManagerController
    implements BasicController<Manager, Long, Manager>,
    Hypermedia<Manager, ManagerModelAssembler> {

    private final ManagerService service;

    private final ManagerModelAssembler modelAssembler;
}
