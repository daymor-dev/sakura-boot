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

package dev.daymor.sakuraboot.example.complexallmodule.presentation.model;

import org.springframework.stereotype.Component;

import dev.daymor.sakuraboot.configuration.GlobalSpecification;
import dev.daymor.sakuraboot.example.complexallmodule.business.dto.ManagerDto;
import dev.daymor.sakuraboot.example.complexallmodule.presentation.ManagerController;
import dev.daymor.sakuraboot.hypermedia.AbstractBasicModelAssembler;

@Component
public class ManagerModelAssembler
    extends AbstractBasicModelAssembler<ManagerDto, ManagerModel> {

    protected ManagerModelAssembler(
        final GlobalSpecification globalSpecification) {

        super(ManagerController.class, ManagerModel.class, globalSpecification);
    }
}
