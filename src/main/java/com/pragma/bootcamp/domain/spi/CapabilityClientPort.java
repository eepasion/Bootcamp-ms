package com.pragma.bootcamp.domain.spi;

import com.pragma.bootcamp.domain.model.Capability;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CapabilityClientPort {
    Mono<List<Capability>> getAllCapabilitiesById(List<String> id);
}
