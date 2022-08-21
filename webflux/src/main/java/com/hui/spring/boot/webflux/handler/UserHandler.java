package com.hui.spring.boot.webflux.handler;

import com.hui.spring.boot.webflux.domain.User;
import com.hui.spring.boot.webflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class UserHandler {
    @Autowired
    private UserRepository userRepository;

    public Mono<ServerResponse> getUserById(ServerRequest serverRequest) {
        printThread("获取单个用户");
        return ServerResponse.status(HttpStatus.OK.value())
                .body(Mono.just(userRepository.getUserByUserId().get(Integer.valueOf(serverRequest.pathVariable("userId")))), User.class);

    }

    public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
        printThread("获取所有用户");

        Flux<User> userFlux =
                Flux.fromStream(userRepository.getUsers().entrySet().stream().map(Map.Entry::getValue));
        return ServerResponse.status(HttpStatus.OK.value()).body(
                userFlux, User.class
        );
    }

    private void printThread(Object obj) {
        String threadName = Thread.currentThread().getName();
        System.out.println("HelloworldAsyncController[" + threadName + "]:" + obj);
    }
}
