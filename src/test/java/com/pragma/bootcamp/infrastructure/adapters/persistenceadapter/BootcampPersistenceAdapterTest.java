package com.pragma.bootcamp.infrastructure.adapters.persistenceadapter;

import com.pragma.bootcamp.domain.model.Bootcamp;
import com.pragma.bootcamp.infrastructure.adapters.persistenceadapter.entity.BootcampEntity;
import com.pragma.bootcamp.infrastructure.adapters.persistenceadapter.mapper.BootcampEntityMapper;
import com.pragma.bootcamp.infrastructure.adapters.persistenceadapter.repository.BootcampRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BootcampPersistenceAdapterTest {
    @Mock
    private BootcampRepository bootcampRepository;

    @Mock
    private BootcampEntityMapper bootcampMapper;

    @InjectMocks
    private BootcampPersistenceAdapter bootcampPersistenceAdapter;
    @Test
    void testSaveSuccessful() {
        Bootcamp bootcamp = new Bootcamp("id", "name", "description", List.of("id"));
        BootcampEntity bootcampEntity = new BootcampEntity("id", "name", "description", new String[]{"id","id"});
        when(bootcampRepository.save(any())).thenReturn(Mono.just(bootcampEntity));
        when(bootcampMapper.toEntity(any())).thenReturn(bootcampEntity);
        when(bootcampMapper.toModel(any())).thenReturn(bootcamp);

        StepVerifier.create(bootcampPersistenceAdapter.save(bootcamp))
                .expectNext(bootcamp)
                .verifyComplete();
        verify(bootcampRepository, times(1)).save(any());
        verify(bootcampMapper, times(1)).toEntity(any());
        verify(bootcampMapper, times(1)).toModel(any());
    }
}