package com.pragma.bootcamp.domain.api;

import com.pragma.bootcamp.domain.model.Bootcamp;
import com.pragma.bootcamp.domain.model.BootcampWithCapabilities;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BootcampServicePort {
    Mono<Bootcamp> save(Bootcamp bootcamp);
    Flux<BootcampWithCapabilities> gettAllBootcampsBy(int page, int size,String sortBy,String sort);
}
