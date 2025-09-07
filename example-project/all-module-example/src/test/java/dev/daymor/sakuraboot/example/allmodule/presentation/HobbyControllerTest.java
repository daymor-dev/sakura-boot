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

package dev.daymor.sakuraboot.example.allmodule.presentation;

import lombok.Getter;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import dev.daymor.sakuraboot.example.allmodule.business.HobbyDto;
import dev.daymor.sakuraboot.example.allmodule.business.HobbyService;
import dev.daymor.sakuraboot.example.allmodule.persistence.Hobby;
import dev.daymor.sakuraboot.example.allmodule.util.HobbyTestUtil;
import dev.daymor.sakuraboot.test.bulk.api.presentation.CriteriaBulkControllerTest;
import dev.daymor.sakuraboot.test.hypermedia.api.HypermediaTest;
import dev.daymor.sakuraboot.test.specification.api.presentation.CriteriaControllerTest;

@SuppressWarnings({
    "java:S2187", "JUnitTestCaseWithNoTests"
})
@Getter
public class HobbyControllerTest
    implements CriteriaControllerTest<Hobby, Long, HobbyDto, HobbyFilter>,
    CriteriaBulkControllerTest<Hobby, Long, HobbyDto, HobbyFilter>,
    HypermediaTest<HobbyDto, HobbyModelAssembler> {

    private final HobbyTestUtil util = new HobbyTestUtil();

    @InjectMocks
    private HobbyController controller;

    @Mock
    private HobbyService service;

    @Mock
    private HobbyModelAssembler modelAssembler;
}
