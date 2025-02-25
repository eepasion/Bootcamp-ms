package com.pragma.bootcamp.domain.usecase;

import com.pragma.bootcamp.domain.api.BootcampServicePort;
import com.pragma.bootcamp.domain.model.Bootcamp;
import com.pragma.bootcamp.domain.spi.BootcampPersistencePort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BootcampUseCase implements BootcampServicePort {
    private final BootcampPersistencePort bootcampPersistencePort;
    @Override
    public Mono<Bootcamp> save(Bootcamp bootcamp) {
        return bootcampPersistencePort.save(bootcamp);
    }
}
