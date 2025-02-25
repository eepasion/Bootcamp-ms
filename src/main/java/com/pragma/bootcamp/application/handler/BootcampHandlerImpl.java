package com.pragma.bootcamp.application.handler;

import com.pragma.bootcamp.application.dto.BootcampRequestDto;
import com.pragma.bootcamp.application.mapper.BootcampMapper;
import com.pragma.bootcamp.domain.api.BootcampServicePort;
import com.pragma.bootcamp.domain.enums.SuccessMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class BootcampHandlerImpl {
    private final BootcampMapper bootcampMapper;
    private final BootcampServicePort bootcampServicePort;


    public Mono<ServerResponse> saveBootcamp(ServerRequest request){
        return request.bodyToMono(BootcampRequestDto.class)
                .map(dto->{
                    dto.setName(dto.getName().trim());
                    dto.setDescription(dto.getDescription().trim());
                    return dto;
                })
                .flatMap(dto -> bootcampServicePort.save(bootcampMapper.toModel(dto)))
                .flatMap(result ->{
                    Map<String,String> response = Map.of("message", SuccessMessages.BOOTCAMP_CREATED.getMessage());
                    return ServerResponse.status(HttpStatus.CREATED).bodyValue(response);
                });
    }
}
