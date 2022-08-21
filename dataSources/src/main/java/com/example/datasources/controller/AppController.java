package com.example.datasources.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author zhouhui
 * @Description desc
 * @date 2022-08-07 23:33
 */

@RestController
@Slf4j
@Data
public class AppController {

    @Value("${server.port}")
    String port;

    @GetMapping("/shutdown")
    public String shutdown() {
        String url = "http://127.0.0.1:%s/actuator/shutdown";
        url = String.format(url, port);
        log.info("shutdown url = {}", url);
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            String strResult = EntityUtils.toString(httpResponse.getEntity());
            log.info("response = {}", strResult);
            return strResult;
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


}
