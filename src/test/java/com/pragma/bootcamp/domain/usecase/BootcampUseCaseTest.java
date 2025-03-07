package com.pragma.bootcamp.domain.usecase;

import com.pragma.bootcamp.domain.model.Bootcamp;
import com.pragma.bootcamp.domain.model.BootcampWithCapabilities;
import com.pragma.bootcamp.domain.model.Capability;
import com.pragma.bootcamp.domain.model.Technology;
import com.pragma.bootcamp.domain.spi.BootcampPersistencePort;
import com.pragma.bootcamp.domain.spi.CapabilityClientPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BootcampUseCaseTest {
    @Mock
    BootcampPersistencePort bootcampPersistencePort;
    @Mock
    CapabilityClientPort capabilityClientPort;

    @InjectMocks
    BootcampUseCase bootcampUseCase;

    @Test
    void testSave() {
        Technology technology = new Technology(1L, "java");
        Technology technology2 = new Technology(2L, "javascript");
        Technology technology3 = new Technology(3L, "aws");
        List<Technology> existingTechnologies = List.of(technology, technology2, technology3);
        Capability capability = new Capability("id", "name", existingTechnologies);
        List<String> capabilitiesId = List.of("id");
        Bootcamp bootcamp = new Bootcamp("id", "name", "description", capabilitiesId);

        when(capabilityClientPort.getAllCapabilitiesById(capabilitiesId)).thenReturn(Mono.just(List.of(capability)));
        when(bootcampPersistencePort.save(bootcamp)).thenReturn(Mono.just(bootcamp));

        StepVerifier.create(bootcampUseCase.save(bootcamp))
                .expectNext(bootcamp)
                .verifyComplete();

        verify(capabilityClientPort, times(1)).getAllCapabilitiesById(capabilitiesId);
        verify(bootcampPersistencePort, times(1)).save(bootcamp);
    }

    @Test
    void testGetAllBootcampsBy() {
        Bootcamp bootcamp = new Bootcamp("id", "name", "description", List.of("id"));
        Technology technology = new Technology(1L, "java");
        Capability capability = new Capability("id", "name", List.of(technology));
        List<Capability> capabilities = List.of(capability);
        BootcampWithCapabilities bootcampWithCapabilities = new BootcampWithCapabilities("id", "name", "description", List.of(capability));

        when(bootcampPersistencePort.getAllBootcampsBy(anyInt(), anyInt(), anyString(), anyString())).thenReturn(Flux.just(bootcamp));
        when(capabilityClientPort.getAllCapabilitiesById(anyList())).thenReturn(Mono.just(capabilities));
        StepVerifier.create(bootcampUseCase.gettAllBootcampsBy(1,1,"name","asc"))
                .expectNext(bootcampWithCapabilities)
                .verifyComplete();
        verify(bootcampPersistencePort, times(1)).getAllBootcampsBy(anyInt(), anyInt(), anyString(), anyString());
    }
}