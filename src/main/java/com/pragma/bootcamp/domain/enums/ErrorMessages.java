package com.pragma.bootcamp.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessages {
    INTERNAL_ERROR(500, "Hubo un error, por favor intente nuevamente mas tarde."),
    BOOTCAMP_CAPABILITY_MIN_SIZE(400, "El bootcamp debe tener minimo 1 capacidad."),
    BOOTCAMP_CAPABILITY_MAX_SIZE(400, "El bootcamp debe tener maximo 4 capacidades."),
    BOOTCAMP_CAPABILITY_NOT_FOUND(400, "Una de las capacidades no fue encontrada. por favor asegurese de crear todas las capacidades."),
    BOOTCAMP_NEEDS_NAME(400, "El bootcamp debe tener un nombre."),
    BOOTCAMP_NEEDS_DESCRIPTION(400, "El bootcamp debe tener una descripcion."),
    BOOTCAMP_NAME_SIZE(400, "El nombre de El bootcamp debe tener un maximo de 50 caracteres."),
    BOOTCAMP_DESCRIPTION_SIZE(400, "La descripcion de El bootcamp debe tener un maximo de 90 caracteres."),
    ;

    private final Number code;
    private final String message;

    public static ErrorMessages fromException(Throwable ex) {
        for (ErrorMessages error : values()) {
            if (ex.getMessage().contains(error.message)) {
                return error;
            }
        }
        return INTERNAL_ERROR;
    }
}
