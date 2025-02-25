package com.pragma.bootcamp.application.mapper;

import com.pragma.bootcamp.application.dto.BootcampRequestDto;
import com.pragma.bootcamp.domain.model.Bootcamp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BootcampMapper {
    @Mapping(target = "id", ignore = true)
    Bootcamp toModel(BootcampRequestDto bootcampRequestDto);
}
