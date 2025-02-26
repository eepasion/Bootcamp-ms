package com.pragma.bootcamp.domain.validations;

import com.pragma.bootcamp.domain.enums.ErrorMessages;
import com.pragma.bootcamp.domain.exceptions.BusinessException;
import com.pragma.bootcamp.domain.model.Bootcamp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
@ExtendWith(MockitoExtension.class)
class BootcampValidationsTest {

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        Bootcamp bootcamp = new Bootcamp("id",null,"pruebaDescripcion", List.of("id1","id2"));
        assertThatThrownBy(()-> BootcampValidations.validateBootcamp(bootcamp))
                .hasMessage(ErrorMessages.BOOTCAMP_NEEDS_NAME.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameExceedsMaxLength() {
        Bootcamp bootcamp = new Bootcamp("id","A".repeat(51),"pruebaDescripcion", List.of("id1","id2"));
        assertThatThrownBy(()-> BootcampValidations.validateBootcamp(bootcamp))
                .hasMessage(ErrorMessages.BOOTCAMP_NAME_SIZE.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsEmpty() {
        Bootcamp bootcamp = new Bootcamp("id","nombrePrueba",null, List.of("id1","id2"));
        assertThatThrownBy(()-> BootcampValidations.validateBootcamp(bootcamp))
                .hasMessage(ErrorMessages.BOOTCAMP_NEEDS_DESCRIPTION.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionExceedsMaxLength() {
        Bootcamp bootcamp = new Bootcamp("id","nombrePrueba","A".repeat(91), List.of("id1","id2"));
        assertThatThrownBy(()-> BootcampValidations.validateBootcamp(bootcamp))
                .hasMessage(ErrorMessages.BOOTCAMP_DESCRIPTION_SIZE.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCapabilityListIsEmpty() {
        Bootcamp bootcamp = new Bootcamp("id","nombrePrueba","pruebaDescripcion", List.of());
        assertThatThrownBy(() -> BootcampValidations.validateBootcamp(bootcamp))
                .hasMessage(ErrorMessages.BOOTCAMP_CAPABILITY_MIN_SIZE.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCapabilityListExceedsMaxLength() {
        Bootcamp bootcamp = new Bootcamp("id", "nombrePrueba", "pruebaDescripcion", List.of("id1", "id2", "id3", "id4", "id5"));
        assertThatThrownBy(() -> BootcampValidations.validateBootcamp(bootcamp))
                .hasMessage(ErrorMessages.BOOTCAMP_CAPABILITY_MAX_SIZE.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSizeIsLessThanOne() {
        assertThatThrownBy(() -> BootcampValidations.validateBootcampSort(1, 0, "", ""))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorMessages.BOOTCAMP_PARAM_SIZE_LESS_ZERO.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenPageIsLessThanOne() {
        assertThatThrownBy(() -> BootcampValidations.validateBootcampSort(0, 1, "", ""))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorMessages.BOOTCAMP_PARAM_PAGE_LESS_ZERO.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenSortByIsInvalid() {
        assertThatThrownBy(() -> BootcampValidations.validateBootcampSort(1, 1, "invalid", "asc"))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorMessages.BOOTCAMP_SORT_BY_FORMAT.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSortIsInvalid() {
        assertThatThrownBy(() -> BootcampValidations.validateBootcampSort(1, 1, "name", "invalid"))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorMessages.BOOTCAMP_SORT_FORMAT.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSortAreNullAndSortByNotNull() {
        assertThatThrownBy(() -> BootcampValidations.validateBootcampSort(1, 1, "name", null))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorMessages.BOOTCAMP_SORT_BY_NO_HAS_SORT.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSortByAreNullAndSortNotNull() {
        assertThatThrownBy(() -> BootcampValidations.validateBootcampSort(1, 1, null, "asc"))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorMessages.BOOTCAMP_SORT_BY_NO_HAS_SORT.getMessage());
    }
}