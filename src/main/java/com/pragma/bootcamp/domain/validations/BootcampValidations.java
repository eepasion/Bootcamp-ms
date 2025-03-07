package com.pragma.bootcamp.domain.validations;

import com.pragma.bootcamp.domain.enums.ErrorMessages;
import com.pragma.bootcamp.domain.exceptions.BusinessException;
import com.pragma.bootcamp.domain.model.Bootcamp;
import java.util.List;


public class BootcampValidations {
    private BootcampValidations() {
        throw new IllegalStateException("Utility class");
    }

    public static void validateBootcamp(Bootcamp bootcamp) {
        validateName(bootcamp.name());
        validateDescription(bootcamp.description());
        validateCapabilities(bootcamp.capabilities());
    }

    public static void validateBootcampSort(int page, int size, String sortBy, String sort) {
        if (page < 1) {
            throw new BusinessException(ErrorMessages.BOOTCAMP_PARAM_PAGE_LESS_ZERO);
        }
        if (size < 1) {
            throw new BusinessException(ErrorMessages.BOOTCAMP_PARAM_SIZE_LESS_ZERO);
        }
        if (!"ASC".equalsIgnoreCase(sort) && !"DESC".equalsIgnoreCase(sort) && sort != null) {
            throw new BusinessException(ErrorMessages.BOOTCAMP_SORT_FORMAT);
        }
        if (!"name".equalsIgnoreCase(sortBy) && !"cap".equalsIgnoreCase(sortBy) && sortBy != null) {
            throw new BusinessException(ErrorMessages.BOOTCAMP_SORT_BY_FORMAT);
        }
        if((sort != null && sortBy == null) || (sort == null && sortBy != null)) {
            throw new BusinessException(ErrorMessages.BOOTCAMP_SORT_BY_NO_HAS_SORT);
        }
    }


    private static void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new BusinessException(ErrorMessages.BOOTCAMP_NEEDS_NAME);
        }
        if (name.length() > 50) {
            throw new BusinessException(ErrorMessages.BOOTCAMP_NAME_SIZE);
        }
    }

    private static void validateDescription(String description) {
        if (description==null || description.isEmpty()) {
            throw new BusinessException(ErrorMessages.BOOTCAMP_NEEDS_DESCRIPTION);
        }
        if (description.length() > 90) {
            throw new BusinessException(ErrorMessages.BOOTCAMP_DESCRIPTION_SIZE);
        }
    }

    private static void validateCapabilities(List<String> capabilities) {
        if (capabilities.isEmpty()) {
            throw new BusinessException(ErrorMessages.BOOTCAMP_CAPABILITY_MIN_SIZE);
        }
        if (capabilities.size() > 4) {
            throw new BusinessException(ErrorMessages.BOOTCAMP_CAPABILITY_MAX_SIZE);
        }
    }
}
