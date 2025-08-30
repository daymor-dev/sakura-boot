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

package dev.daymor.sakuraboot.example.complexhypermediamodule.presentation;

import lombok.Getter;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import dev.daymor.sakuraboot.example.complexhypermediamodule.business.ManagerService;
import dev.daymor.sakuraboot.example.complexhypermediamodule.persistence.Manager;
import dev.daymor.sakuraboot.example.complexhypermediamodule.presentation.model.ManagerModelAssembler;
import dev.daymor.sakuraboot.example.complexhypermediamodule.util.ManagerTestUtil;
import dev.daymor.sakuraboot.test.basic.api.presentation.BasicControllerTest;
import dev.daymor.sakuraboot.test.hypermedia.api.HypermediaTest;

@SuppressWarnings({
    "java:S2187", "JUnitTestCaseWithNoTests"
})
@Getter
public class ManagerControllerTest
    implements BasicControllerTest<Manager, Long, Manager>,
    HypermediaTest<Manager, ManagerModelAssembler> {

    private final ManagerTestUtil util = new ManagerTestUtil();

    @InjectMocks
    private ManagerController controller;

    @Mock
    private ManagerService service;

    @Mock
    private ManagerModelAssembler modelAssembler;
}
