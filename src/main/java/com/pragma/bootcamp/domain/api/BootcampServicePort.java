package com.pragma.bootcamp.domain.api;

import com.pragma.bootcamp.domain.model.Bootcamp;
import reactor.core.publisher.Mono;

public interface BootcampServicePort {
    Mono<Bootcamp> save(Bootcamp bootcamp);

}
