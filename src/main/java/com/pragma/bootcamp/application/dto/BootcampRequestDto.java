package com.pragma.bootcamp.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BootcampRequestDto {
    private String name;
    private String description;
    private String[] capabilities;
}
