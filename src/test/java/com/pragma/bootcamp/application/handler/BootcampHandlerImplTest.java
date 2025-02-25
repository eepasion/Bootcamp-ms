package com.pragma.bootcamp.application.handler;

import com.pragma.bootcamp.application.dto.BootcampRequestDto;
import com.pragma.bootcamp.application.mapper.BootcampMapper;
import com.pragma.bootcamp.domain.api.BootcampServicePort;
import com.pragma.bootcamp.domain.enums.SuccessMessages;
import com.pragma.bootcamp.domain.model.Bootcamp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@ExtendWith(MockitoExtension.class)
class BootcampHandlerImplTest {
    @Mock
    private BootcampMapper bootcampMapper;

    @Mock
    private BootcampServicePort bootcampServicePort;

    @InjectMocks
    private BootcampHandlerImpl bootcampHandler;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToRouterFunction(
                route(POST("/bootcamp"), bootcampHandler::saveBootcamp)
        ).build();
    }
    @Test
    void saveBootcampTestSuccessful() {
        Bootcamp bootcamp = new Bootcamp("id", "name", "description", List.of("id"));
        BootcampRequestDto bootcampRequestDto = new BootcampRequestDto("name", "description", new String[]{"id"});
        when(bootcampMapper.toModel(any(BootcampRequestDto.class))).thenReturn(bootcamp);
        when(bootcampServicePort.save(bootcamp)).thenReturn(Mono.just(bootcamp));

        webTestClient.post().uri("/bootcamp").bodyValue(bootcampRequestDto).exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.message").isEqualTo(SuccessMessages.BOOTCAMP_CREATED.getMessage());
    }
}