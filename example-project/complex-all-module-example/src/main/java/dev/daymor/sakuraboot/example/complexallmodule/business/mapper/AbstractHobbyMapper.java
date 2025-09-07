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

package dev.daymor.sakuraboot.example.complexallmodule.business.mapper;

import org.mapstruct.Mapper;

import dev.daymor.sakuraboot.example.complexallmodule.business.dto.HobbyDto;
import dev.daymor.sakuraboot.example.complexallmodule.persistence.Hobby;
import dev.daymor.sakuraboot.mapper.api.AbstractBasicMapperForRelationship;
import dev.daymor.sakuraboot.mapper.api.BasicMapper;

@Mapper(config = BasicMapper.class)
public abstract class AbstractHobbyMapper
    extends AbstractBasicMapperForRelationship<Hobby, HobbyDto> {}
