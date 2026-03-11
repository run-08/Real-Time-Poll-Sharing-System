package com.example.EDA.Routing;

import com.example.EDA.RequestHandler.PollRequestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class FunctionalRouting {
    private final PollRequestHandler pollRequestHandler;
    @Bean
    public RouterFunction<ServerResponse> handleRoute(PollRequestHandler pollRequestHandler){
        return
                RouterFunctions
                        .route()
                        .path("/api",builder -> builder
                        .POST("/savePoll",pollRequestHandler::savePoll)
                                            .GET("/getAllPolls",pollRequestHandler::getAllPolls)
                                        .PUT("/votePoll",pollRequestHandler::votePoll)
                        )
                        .build();
    }
}
