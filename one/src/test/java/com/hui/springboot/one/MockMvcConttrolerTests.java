package com.hui.springboot.one;

import com.hui.springboot.one.controller.HelloWorldController;
import com.hui.springboot.one.controller.MockMvcController;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class MockMvcConttrolerTests {

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new MockMvcController()).build();
    }

    @Test
    public void mockmvc() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/mockmvc/1")//使用get方式来调用接口。
                .param("id", "1")
                .accept(MediaType.APPLICATION_JSON))//请求参数的类型
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())//打印
                .andReturn();
        System.out.println("---------------");
        System.out.println(mvcResult.getRequest());//得到执行的请求；
        System.out.println(mvcResult.getResponse().getContentAsString());//得到执行后的 数据


        System.out.println(mvcResult.getHandler());//得到执行的处理器，一般就是控制器
        System.out.println(mvcResult.getInterceptors());//得到对处理器进行拦截的拦截器
        System.out.println(mvcResult.getModelAndView());//得到执行后的ModelAndView；
        System.out.println(mvcResult.getResolvedException());//得到HandlerExceptionResolver解析后的异常；
        System.out.println(mvcResult.getFlashMap());//得到FlashMap；
        try {

            JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
            JSONArray jsonArrayData = (JSONArray) jsonObject.get("data");
//获取第一个Array中的值,判断查询到的结果。
            JSONObject jsonObject_data = null;
            if (jsonArrayData.length() > 0) {
                jsonObject_data = (JSONObject) jsonArrayData.get(0);
            }
            //asyncDispatch需要设置
            System.out.println(mvcResult.getAsyncResult());//得到异步执行的结果
            System.out.println(mvcResult.getAsyncResult(5000l));//得到异步执行的结果

            //加断言，判断属性值的问题。
            Assert.assertNotNull(jsonObject.get("error_code"));
            Assert.assertEquals(jsonObject.get("error_code"), 0);
            Assert.assertNotNull(jsonObject.get("error_msg"));
            Assert.assertEquals(jsonObject.get("error_msg"), "操作成功");
            Assert.assertNotNull(jsonObject.get("data"));
            Assert.assertNotNull(jsonObject_data);
            Assert.assertEquals(jsonObject_data.get("equipmentty"), 1);
            Assert.assertEquals(jsonObject_data.get("equipmenttypename"), "xxxxx");
        } catch (Exception e) {
            // e.printStackTrace();
        }


    }

    @Test
    public void getPathParam() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/mockmvc/name")//使用get方式来调用接口。
                .param("name", "zhangsan")
                .accept(MediaType.APPLICATION_JSON))//请求参数的类型
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())//打印
                .andReturn();
        System.out.println("---------------");
        System.out.println(mvcResult.getRequest());//得到执行的请求；
        System.out.println(mvcResult.getResponse().getContentAsString());//得到执行后的 数据


        System.out.println(mvcResult.getHandler());//得到执行的处理器，一般就是控制器
        System.out.println(mvcResult.getInterceptors());//得到对处理器进行拦截的拦截器
        System.out.println(mvcResult.getModelAndView());//得到执行后的ModelAndView；
        System.out.println(mvcResult.getResolvedException());//得到HandlerExceptionResolver解析后的异常；
        System.out.println(mvcResult.getFlashMap());//得到FlashMap；
        try {

            JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
            JSONArray jsonArrayData = (JSONArray) jsonObject.get("data");
//获取第一个Array中的值,判断查询到的结果。
            JSONObject jsonObject_data = null;
            if (jsonArrayData.length() > 0) {
                jsonObject_data = (JSONObject) jsonArrayData.get(0);
            }
            //asyncDispatch需要设置
            System.out.println(mvcResult.getAsyncResult());//得到异步执行的结果
            System.out.println(mvcResult.getAsyncResult(5000l));//得到异步执行的结果

            //加断言，判断属性值的问题。
            Assert.assertNotNull(jsonObject.get("error_code"));
            Assert.assertEquals(jsonObject.get("error_code"), 0);
            Assert.assertNotNull(jsonObject.get("error_msg"));
            Assert.assertEquals(jsonObject.get("error_msg"), "操作成功");
            Assert.assertNotNull(jsonObject.get("data"));
            Assert.assertNotNull(jsonObject_data);
            Assert.assertEquals(jsonObject_data.get("equipmentty"), 1);
            Assert.assertEquals(jsonObject_data.get("equipmenttypename"), "xxxxx");
        } catch (Exception e) {
            // e.printStackTrace();
        }


    }

    public void getTrace() {

    }
}
