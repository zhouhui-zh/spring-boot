package com.hui.springboot.one.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 自定义配置类
 */
@Component
public class HuiProperties {
    @Value("${com.hui.title}")
    private String description;

    @Value("${com.hui.description}")
    private String title;
}
