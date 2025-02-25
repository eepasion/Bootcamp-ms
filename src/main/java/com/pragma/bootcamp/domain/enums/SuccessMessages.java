package com.pragma.bootcamp.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SuccessMessages {
    BOOTCAMP_CREATED("Bootcamp creado con exito.");
    private final String message;
}
