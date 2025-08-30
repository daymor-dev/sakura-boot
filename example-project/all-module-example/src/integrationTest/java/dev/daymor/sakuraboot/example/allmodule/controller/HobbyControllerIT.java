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

package dev.daymor.sakuraboot.example.allmodule.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.lang.Nullable;
import org.springframework.test.web.servlet.MockMvc;

import dev.daymor.sakuraboot.configuration.GlobalConfiguration;
import dev.daymor.sakuraboot.example.allmodule.business.HobbyDto;
import dev.daymor.sakuraboot.example.allmodule.business.HobbyService;
import dev.daymor.sakuraboot.example.allmodule.persistence.Hobby;
import dev.daymor.sakuraboot.example.allmodule.presentation.HobbyController;
import dev.daymor.sakuraboot.example.allmodule.presentation.HobbyFilter;
import dev.daymor.sakuraboot.example.allmodule.presentation.HobbyModelAssembler;
import dev.daymor.sakuraboot.example.allmodule.util.HobbyITUtil;
import dev.daymor.sakuraboot.hypermedia.aop.HypermediaAspect;
import dev.daymor.sakuraboot.test.integration.controller.bulk.CriteriaBulkControllerIT;
import dev.daymor.sakuraboot.test.integration.controller.hypermedia.HypermediaIT;
import dev.daymor.sakuraboot.test.integration.controller.specification.CriteriaControllerIT;

@SuppressWarnings({
    "java:S2187", "JUnitTestCaseWithNoTests"
})
@Getter
@WebMvcTest(HobbyController.class)
@Import({
    HobbyModelAssembler.class, AopAutoConfiguration.class,
    HypermediaAspect.class, GlobalConfiguration.class
})
public class HobbyControllerIT
    implements CriteriaControllerIT<Hobby, Long, HobbyDto, HobbyFilter>,
    CriteriaBulkControllerIT<Hobby, Long, HobbyDto, HobbyFilter>,
    HypermediaIT<Hobby, Long, HobbyDto> {

    private final HobbyITUtil util = new HobbyITUtil();

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    @MockBean
    @Nullable
    private HobbyService service;

    @SuppressWarnings("JUnitTestCaseWithNonTrivialConstructors")
    @Autowired
    HobbyControllerIT(final MockMvc mockMvc, final ObjectMapper objectMapper) {

        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }
}
