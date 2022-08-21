package com.hui.spring.boot.webflux.controller;

import com.hui.spring.boot.webflux.domain.User;
import com.hui.spring.boot.webflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/annotated/")
public class WebFluxAnnotatedController {
    @Autowired
    private UserRepository userRepository;

    //https://www.jianshu.com/p/2db1ecacb770
    @GetMapping("user/{id}")
    public Mono<User> getUserByUserId(@PathVariable("id") int id) {
        pringThread("获取单个用户");
        return Mono.just(userRepository.getUserByUserId().get(id));
    }

    @GetMapping("users")
    public Flux<User> getAllUsers() {
        pringThread("获取所有用户");
        return Flux.fromStream(userRepository.getUsers().entrySet().stream().map(Map.Entry::getValue));
    }

    private void pringThread(Object obj) {
        String threadName = Thread.currentThread().getName();
        System.out.println("WebFluxAnnotatedController[" + threadName + "]:" + obj);
    }

}
