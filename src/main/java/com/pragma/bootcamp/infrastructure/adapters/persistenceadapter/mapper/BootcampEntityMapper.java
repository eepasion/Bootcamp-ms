package com.pragma.bootcamp.infrastructure.adapters.persistenceadapter.mapper;

import com.pragma.bootcamp.domain.model.Bootcamp;
import com.pragma.bootcamp.infrastructure.adapters.persistenceadapter.entity.BootcampEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BootcampEntityMapper {
    Bootcamp toModel(BootcampEntity bootcampEntity);
    BootcampEntity toEntity(Bootcamp bootcamp);
}


