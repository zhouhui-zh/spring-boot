package com.hui.springboot.mysql;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author zhouhui
 * @Description desc
 * @date 2022-04-19 9:03
 */
public class ComplexHeadData {

    private String string="{\"主标题\", \"字符串标题\"}";
    private String date="{\"主标题\", \"日期标题\"}";
    private String doubleData="{\"主标题\", \"数字标题\"}";

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoubleData() {
        return doubleData;
    }

    public void setDoubleData(String doubleData) {
        this.doubleData = doubleData;
    }
}
