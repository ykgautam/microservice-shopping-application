package com.microservice.gateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.web.servlet.function.ServerRequest;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.common.MvcUtils.GATEWAY_REQUEST_URL_ATTR;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        return GatewayRouterFunctions.route("product-service")
                .route(
                        RequestPredicates.path("/api/product/**"),
                        HandlerFunctions.http()
                )
                .before(request -> {
                    ServerRequest newRequest = ServerRequest.from(request)
                            .attribute(GATEWAY_REQUEST_URL_ATTR, URI.create("http://localhost:8080"))
                            .build();
                    return newRequest;
                })
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {
        return GatewayRouterFunctions.route("order-service")
                .route(
                        RequestPredicates.path("/api/order/**"),
                        HandlerFunctions.http()
                )
                .before(request -> {
                    ServerRequest newRequest = ServerRequest.from(request)
                            .attribute(GATEWAY_REQUEST_URL_ATTR, URI.create("http://localhost:8081"))
                            .build();
                    return newRequest;
                })
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute() {
        return GatewayRouterFunctions.route("inventory-service")
                .route(
                        RequestPredicates.path("/api/inventory/**"),
                        HandlerFunctions.http()
                )
                .before(request -> {
                    ServerRequest newRequest = ServerRequest.from(request)
                            .attribute(GATEWAY_REQUEST_URL_ATTR, URI.create("http://localhost:8082"))
                            .build();
                    return newRequest;
                })
                .build();
    }
}