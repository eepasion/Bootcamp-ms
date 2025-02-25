package com.pragma.bootcamp.infrastructure.adapters.clientadapter;

import com.pragma.bootcamp.domain.model.Capability;
import com.pragma.bootcamp.domain.spi.CapabilityClientPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class CapabilityClientAdapter implements CapabilityClientPort {
    private final RSocketRequester rSocketRequester;

    @Override
    public Mono<List<Capability>> getAllCapabilitiesById(List<String> id) {
        return rSocketRequester
                .route("get.capabilities")
                .data(id)
                .retrieveMono(new ParameterizedTypeReference<List<Capability>>() {
                });
    }
}
