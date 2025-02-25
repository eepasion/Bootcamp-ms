package com.pragma.bootcamp.infrastructure.entrypoints;

import com.pragma.bootcamp.application.handler.BootcampHandlerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BootcampRouterConfig {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(BootcampHandlerImpl bootcampHandler){
        return route(POST("/bootcamp"), bootcampHandler::saveBootcamp);
    }
}
