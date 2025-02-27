package com.pragma.bootcamp.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeType;
import reactor.util.retry.Retry;

import java.time.Duration;

@Configuration
public class RsocketConfiguration {

    @Bean
    public RSocketRequester rSocketRequester(RSocketRequester.Builder builder,@Value("${security.api-key}") String apiKey) {
        return builder
                .setupMetadata(apiKey, MimeType.valueOf("application/text"))
                .rsocketConnector(connector ->
                        connector.reconnect(Retry.fixedDelay(2, Duration.ofSeconds(2)))
                                .dataMimeType("application/json"))
                .tcp("localhost", 7001);
    }

    @Bean
    public RSocketStrategies rSocketStrategies() {
        ObjectMapper objectMapper = new ObjectMapper();
        return RSocketStrategies.builder()
                .encoders(encoders -> encoders.add(new Jackson2JsonEncoder(objectMapper)))
                .decoders(decoders -> decoders.add(new Jackson2JsonDecoder(objectMapper)))
                .build();
    }
}


