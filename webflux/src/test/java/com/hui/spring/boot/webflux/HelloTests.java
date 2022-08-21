package com.hui.spring.boot.webflux;

import com.hui.spring.boot.webflux.controller.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers = HelloController.class)
public class HelloTests {

    @Autowired
    WebTestClient client;

    @Test
    public void getHello() {

        System.out.println(client.get().uri("/hello").exchange().expectStatus().isOk());
    }
}
