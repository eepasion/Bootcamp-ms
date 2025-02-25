package com.pragma.bootcamp.domain.usecase;

import com.pragma.bootcamp.domain.api.BootcampServicePort;
import com.pragma.bootcamp.domain.enums.ErrorMessages;
import com.pragma.bootcamp.domain.exceptions.BusinessException;
import com.pragma.bootcamp.domain.model.Bootcamp;
import com.pragma.bootcamp.domain.model.Capability;
import com.pragma.bootcamp.domain.spi.BootcampPersistencePort;
import com.pragma.bootcamp.domain.spi.CapabilityClientPort;
import com.pragma.bootcamp.domain.validations.BootcampValidations;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class BootcampUseCase implements BootcampServicePort {
    private final BootcampPersistencePort bootcampPersistencePort;
    private final CapabilityClientPort capabilityClientPort;
    @Override
    public Mono<Bootcamp> save(Bootcamp bootcamp) {
        BootcampValidations.validateBootcamp(bootcamp);
        return getAllCapabilitiesById(bootcamp.capabilities())
                .flatMap(capabilities -> {
                    if(capabilities.size() != bootcamp.capabilities().size()) {
                        return Mono.error(new BusinessException(ErrorMessages.BOOTCAMP_CAPABILITY_NOT_FOUND));
                    }
                    return bootcampPersistencePort.save(bootcamp);
                });
    }

    private Mono<List<Capability>> getAllCapabilitiesById(List<String> id) {
        return capabilityClientPort.getAllCapabilitiesById(id);
    }
}
