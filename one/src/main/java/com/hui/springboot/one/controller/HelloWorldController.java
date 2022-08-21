package com.hui.springboot.one.controller;

import com.hui.springboot.one.vo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @RequestMapping("hello")
    public String index() {
        return "hello world afafaasfadaasdasd";
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public User getUser() {
        User user = new User();
        user.setUsername("小明");
        user.setPassword("xxxx");
        return user;
    }
}
