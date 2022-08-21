package com.hui.springboot.poi;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhouhui
 * @Description desc
 * @date 2022-05-17 15:21
 */
public class Test {
    public static void main(String[] args) {
        SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd 12:11:10");
        System.out.println(sd.format(new Date()));
    }
}
