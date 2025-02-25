package com.pragma.bootcamp.domain.exceptions;

import com.pragma.bootcamp.domain.enums.ErrorMessages;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorMessages errorMessages;
    public BusinessException(ErrorMessages errorMessages) {
        super(errorMessages.getMessage());
        this.errorMessages = errorMessages;
    }
}
