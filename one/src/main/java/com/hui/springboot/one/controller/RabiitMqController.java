package com.hui.springboot.one.controller;

import com.hui.springboot.one.rabiitmq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.websocket.server.PathParam;

@RestController
public class RabiitMqController {

    @Autowired
    private HelloSender helloSender;
    @Autowired
    private HelloSender2 helloSender2;
    @Autowired
    private HelloSender3 helloSender3;
    @Autowired
    private HelloSenderObj helloSenderObj;
    @Autowired
    private HelloSenderFanout helloSenderFanout;

    @RequestMapping(value = "mq/send/{name}")
    public String sendname(@PathVariable("name") String name) {
        helloSender.send(name);
        return name;
    }

    /**
     * 取请求休中参数
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "mq/send2")//http://127.0.0.1:8080/mq/send2?name=11
    public String sendname2(@PathParam("name") String name) {
        helloSender.send(name);
        return name;
    }

    @GetMapping(value = "mq/send3")//http://127.0.0.1:8080/mq/send3?num=11
    public String sendname3(@PathParam("num") int num) {
        for (int i = 0; i < num; i++) {
            helloSender.send(String.valueOf(i));
        }
        return String.valueOf(num);
    }

    @GetMapping(value = "mq/send4")//http://127.0.0.1:8080/mq/send4?num=11
    public String sendname4(@PathParam("num") int num) {
        for (int i = 0; i < num; i++) {
            helloSender.send(String.valueOf(i));
            helloSender2.send(String.valueOf(i));
        }
        return String.valueOf(num);
    }

    @GetMapping(value = "mq/sendObj")//http://127.0.0.1:8080/mq/send3?name=11
    public String sendObj(@PathParam("name") String name) {
        helloSenderObj.send(name);
        return name;
    }

    @GetMapping(value = "mq/sendMessage")//http://127.0.0.1:8080/mq/sendMessage
    public String sendMessage() {
        helloSender3.send1();
        return "sussce";
    }

    @GetMapping(value = "mq/sendMessages")//http://127.0.0.1:8080/mq/sendMessages
    public String sendMessages() {
        helloSender3.send2();
        return "sussce";
    }

    @GetMapping(value = "mq/sendFanout")//http://127.0.0.1:8080/mq/sendFanout
    public String sendFanout() {
        helloSenderFanout.send();
        return "sussce";
    }


}
