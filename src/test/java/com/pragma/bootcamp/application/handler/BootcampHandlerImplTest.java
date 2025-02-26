package com.pragma.bootcamp.application.handler;

import com.pragma.bootcamp.application.dto.BootcampRequestDto;
import com.pragma.bootcamp.application.mapper.BootcampMapper;
import com.pragma.bootcamp.domain.api.BootcampServicePort;
import com.pragma.bootcamp.domain.enums.SuccessMessages;
import com.pragma.bootcamp.domain.model.Bootcamp;
import com.pragma.bootcamp.domain.model.BootcampWithCapabilities;
import com.pragma.bootcamp.domain.model.Capability;
import com.pragma.bootcamp.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
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
                route(POST("/bootcamp"), bootcampHandler::saveBootcamp).andRoute(GET("/bootcamp"), bootcampHandler::getAllBootcamps)
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

    @Test
    void getAllBootcampsBy() {
        Technology technology = new Technology(1L, "java");
        Technology technology2 = new Technology(2L, "javascript");
        Technology technology3 = new Technology(3L, "aws");
        Capability capability = new Capability("id", "name", List.of(technology, technology2, technology3));
        List<Capability> capabilities = List.of(capability);
        BootcampWithCapabilities bootcamp = new BootcampWithCapabilities("id", "name", "description", capabilities);
        when(bootcampServicePort.gettAllBootcampsBy(anyInt(), anyInt(), anyString(), anyString())).thenReturn(Flux.just(bootcamp));

        webTestClient.get().uri(uriBuilder -> uriBuilder.path("/bootcamp")
                        .queryParam("page", 1)
                        .queryParam("size", 10)
                        .queryParam("sortBy", "name")
                        .queryParam("sort", "asc")
                        .build()).exchange()
                .expectStatus().isOk();
    }
}