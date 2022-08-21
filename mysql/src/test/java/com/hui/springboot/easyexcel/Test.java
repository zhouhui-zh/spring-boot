package com.hui.springboot.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.hui.springboot.mysql.ComplexHeadData;

import java.util.List;

/**
 * @author zhouhui
 * @Description desc
 * @date 2022-04-19 9:22
 */
public class Test {
    public static void main(String[] args) {
        String fileName = "F:\\Users\\zh\\Desktop\\easy.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, ComplexHeadData.class).sheet("模板").doWrite(data());
    }

    private static List<List<Object>> data() {


        return null;
    }
}
