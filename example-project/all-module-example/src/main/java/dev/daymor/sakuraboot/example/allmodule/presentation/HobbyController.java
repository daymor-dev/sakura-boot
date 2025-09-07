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

package dev.daymor.sakuraboot.example.allmodule.presentation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.daymor.sakuraboot.bulk.api.presentation.CriteriaBulkController;
import dev.daymor.sakuraboot.example.allmodule.business.HobbyDto;
import dev.daymor.sakuraboot.example.allmodule.business.HobbyService;
import dev.daymor.sakuraboot.example.allmodule.persistence.Hobby;
import dev.daymor.sakuraboot.hypermedia.api.Hypermedia;
import dev.daymor.sakuraboot.log.api.Loggable;
import dev.daymor.sakuraboot.specification.api.presentation.CriteriaController;

@RestController
@RequestMapping("/hobbies")
@Getter
@RequiredArgsConstructor
public class HobbyController
    implements CriteriaController<Hobby, Long, HobbyDto, HobbyFilter>,
    CriteriaBulkController<Hobby, Long, HobbyDto, HobbyFilter>,
    Hypermedia<HobbyDto, HobbyModelAssembler>, Loggable {

    private final HobbyService service;

    private final HobbyModelAssembler modelAssembler;
}
