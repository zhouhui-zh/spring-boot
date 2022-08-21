package com.example.datasources.controller;

import com.example.datasources.entity.TUser1;
import com.example.datasources.service.impl.TUser1ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hui
 * @since 2022-07-31
 */
@Controller
@RequestMapping("/")
@Slf4j
@ResponseBody
public class TUser1Controller {
    @Autowired
    TUser1ServiceImpl tUser1Service;


    /**
     * 查询全部信息
     *
     * @Author Sans
     * @CreateTime 2019/6/8 16:35
     * @Param userId  用户ID
     * @Return List<UserInfoEntity> 用户实体集合
     */
    @RequestMapping("/getList")
    public List<TUser1> getList() {
        //http://localhost:8080/user/getList
        List us = tUser1Service.list();
        return us;
    }
}
