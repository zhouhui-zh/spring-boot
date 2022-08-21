package com.hui.spring.boot.webflux.router;

import com.hui.spring.boot.webflux.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Configuration
public class WebFluxRoutingConfiguration {
    @Autowired
    private UserHandler userHandler;

    //@Bean
    public RouterFunction<ServerResponse> initRouterFunction() {
        System.out.println("initRouterFunction ....");
        return RouterFunctions.route()
                .GET("/test/**", serverRequest -> {
                    System.out.println("path：" + serverRequest.exchange().getRequest().getPath().pathWithinApplication().value());

                    return ServerResponse.ok().bodyValue("hello world");
                })
                .filter((serverRequest, handlerFunction) -> {
                    System.out.println("custom filter");

                    return handlerFunction.handle(serverRequest);
                })
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        System.out.println("routerFunction  ...");
        return route(RequestPredicates.GET("/webflux/user/{userId}"), userHandler::getUserById)
                .andRoute(RequestPredicates.GET("/webflux/users"), userHandler::getAll);

    }

    public static <T extends ServerResponse> RouterFunction<T> route(RequestPredicate predicate, HandlerFunction handlerFunction) {
        System.out.println("route  ...");
        return new DefaultRouterFunction(predicate, handlerFunction);
    }

    private static final class DefaultRouterFunction implements RouterFunction {
        private final RequestPredicate predicate;
        private final HandlerFunction handlerFunction;

        public DefaultRouterFunction(RequestPredicate predicate, HandlerFunction handlerFunction) {
            Assert.notNull(predicate, "Predicate must not be null");
            Assert.notNull(handlerFunction, "HandlerFunction must not be null");
            this.predicate = predicate;
            this.handlerFunction = handlerFunction;
        }


        @Override
        public Mono<HandlerFunction> route(ServerRequest request) {
            if (this.predicate.test(request)) {
            /*    if (logger.isDebugEnabled()) {
                    logger.debug(String.format("Predicate \"%s\" matches against \"%s\"", this.predicate, request));
                }*/
                System.out.println(String.format("Predicate \"%s\" matches against \"%s\"", this.predicate, request));
                return Mono.just(this.handlerFunction);
            } else {
                return Mono.empty();
            }
        }

        @Override
        public void accept(RouterFunctions.Visitor visitor) {
            visitor.route(this.predicate, this.handlerFunction);
        }
    }
}
