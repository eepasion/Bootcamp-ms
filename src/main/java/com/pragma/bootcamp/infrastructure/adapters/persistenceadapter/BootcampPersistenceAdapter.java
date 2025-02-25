package com.pragma.bootcamp.infrastructure.adapters.persistenceadapter;

import com.pragma.bootcamp.domain.model.Bootcamp;
import com.pragma.bootcamp.domain.spi.BootcampPersistencePort;
import com.pragma.bootcamp.infrastructure.adapters.persistenceadapter.mapper.BootcampEntityMapper;
import com.pragma.bootcamp.infrastructure.adapters.persistenceadapter.repository.BootcampRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
@RequiredArgsConstructor
public class BootcampPersistenceAdapter implements BootcampPersistencePort {
    private final BootcampRepository bootcampRepository;
    private final BootcampEntityMapper bootcampMapper;
    @Override
    public Mono<Bootcamp> save(Bootcamp bootcamp) {
        return bootcampRepository.save(bootcampMapper.toEntity(bootcamp))
                .map(bootcampMapper::toModel);
    }
}
