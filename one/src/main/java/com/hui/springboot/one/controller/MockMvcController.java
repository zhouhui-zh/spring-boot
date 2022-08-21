package com.hui.springboot.one.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class MockMvcController {
    @RequestMapping("mockmvc/{id}")
    public String getId(@PathVariable("id") String id) {
        return id;
    }

    @RequestMapping("mockmvc/name")
    public String getPathParam(@PathParam("name") String name) {
        return name;
    }


}
