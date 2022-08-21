package com.example.datasources.controller;

import com.example.datasources.dbs.DynamicDataSourceAspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhouhui
 * @Description desc
 * @date 2022-07-31 12:41
 */

@RestController
public class TestController {

    @Autowired
    private DynamicDataSourceAspect dynamicDataSourceAspect;

    @GetMapping(path = "/")
    public String get(String targetDataSource) {
        dynamicDataSourceAspect.changeDataSource(targetDataSource);

        dynamicDataSourceAspect.clearDataSource(targetDataSource);
        return "hello, SpringBoot";
    }

    @GetMapping(path = "/getDataSourceIds")
    public List<String> getDataSourceIds() {
        return dynamicDataSourceAspect.getDataSourceIds();
    }


}