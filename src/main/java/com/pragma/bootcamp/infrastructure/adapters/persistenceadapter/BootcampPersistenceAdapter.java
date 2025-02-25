package com.pragma.bootcamp.infrastructure.adapters.persistenceadapter;

import com.pragma.bootcamp.domain.model.Bootcamp;
import com.pragma.bootcamp.domain.spi.BootcampPersistencePort;
import com.pragma.bootcamp.infrastructure.adapters.persistenceadapter.mapper.BootcampEntityMapper;
import com.pragma.bootcamp.infrastructure.adapters.persistenceadapter.repository.BootcampRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
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

    @Override
    public Flux<Bootcamp> getAllBootcampsBy(int page, int size, String sortBy, String sort) {
        int skip = (page -1) * size;
        if(sortBy == null) return bootcampRepository.findAllWithPagination(skip, size).map(bootcampMapper::toModel);
        boolean descending = sort.equalsIgnoreCase("desc");
        int sortDirection = descending ? -1 : 1;
        if(sortBy.equalsIgnoreCase("name")) return bootcampRepository.findAllSortedByName(sortDirection, skip, size).map(bootcampMapper::toModel);
        return bootcampRepository.findAllSortedByCapabilities(sortDirection, skip, size).map(bootcampMapper::toModel);
    }
}
