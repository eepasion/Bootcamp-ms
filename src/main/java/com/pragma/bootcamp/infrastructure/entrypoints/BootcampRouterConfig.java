package com.pragma.bootcamp.infrastructure.entrypoints;

import com.pragma.bootcamp.application.dto.BootcampRequestDto;
import com.pragma.bootcamp.application.handler.BootcampHandlerImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BootcampRouterConfig {
    @RouterOperations({

            @RouterOperation(
                    path = "/bootcamp",
                    beanClass = BootcampHandlerImpl.class,
                    beanMethod = "saveBootcamp",
                    method = RequestMethod.POST,
                    operation = @Operation(
                            operationId = "opSaveBootcamp",
                            summary = "Guarda un nuevo bootcamp",
                            requestBody = @RequestBody(
                                    description = "Estructura de un bootcamp",
                                    required = true,
                                    content = @Content(
                                            schema = @Schema(implementation = BootcampRequestDto.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(responseCode = "201", description = "Bootcamp creado"),
                                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/bootcamp", beanClass = BootcampHandlerImpl.class, beanMethod = "getAllBootcamps",
                    method = RequestMethod.GET,
                    operation = @Operation(
                            operationId = "opGetAllBootcamps",
                            summary = "Obtiene todas los bootcamps permitiendo ordenar por parametros",
                            parameters = {
                                    @Parameter(in = ParameterIn.QUERY, name = "size", required = false,description = "Cantidad de elementos por página"),
                                    @Parameter(in = ParameterIn.QUERY, name = "page", required = false,description = "Número de página"),
                                    @Parameter(in = ParameterIn.QUERY, name = "sort", required = false,description = "Orden ascendente o descendente el valor es asc o desc"),
                                    @Parameter(in = ParameterIn.QUERY, name = "sortBy", required = false, description = "Ordenar por name o cap")
                            },
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Bootcamps obtenidos exitosamente"),
                                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
                            }
                    )
            )
    })
    @Bean
    public RouterFunction<ServerResponse> routerFunction(BootcampHandlerImpl bootcampHandler){
        return route(POST("/bootcamp"), bootcampHandler::saveBootcamp)
                .andRoute(GET("/bootcamp"), bootcampHandler::getAllBootcamps);
    }
}
