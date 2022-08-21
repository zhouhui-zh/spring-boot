package com.hui.springbootmybatisplus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hui.springbootmybatisplus.entity.User;
import com.hui.springbootmybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhouhui
 * @Description desc
 * @date 2022-07-18 22:41
 */


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 根据ID获取用户信息
     *
     * @Author Sans
     * @CreateTime 2019/6/8 16:34
     * @Param userId  用户ID
     * @Return UserInfoEntity 用户实体
     */
    @RequestMapping("/getInfo")
    public User getInfo(String userId) {
        //http://localhost:8080/user/getInfo?userId=1
        return userService.getById(userId);
    }

    /**
     * 查询全部信息
     *
     * @Author Sans
     * @CreateTime 2019/6/8 16:35
     * @Param userId  用户ID
     * @Return List<UserInfoEntity> 用户实体集合
     */
    @RequestMapping("/getList")
    public List<User> getList() {
        //http://localhost:8080/user/getList
        return userService.list();
    }

    /**
     * 分页查询全部数据
     *
     * @Author Sans
     * @CreateTime 2019/6/8 16:37
     * @Return IPage<UserInfoEntity> 分页数据
     */
    @RequestMapping("/getInfoListPage")
    public IPage<User> getInfoListPage() {
        //http://localhost:8080/user/getInfoListPage
        //需要在Config配置类中配置分页插件
        IPage<User> page = new Page<>();
        page.setCurrent(5); //当前页
        page.setSize(1);    //每页条数
        return userService.page(page);
    }

    /**
     * 根据指定字段查询用户信息集合
     *
     * @Author Sans
     * @CreateTime 2019/6/8 16:39
     * @Return Collection<UserInfoEntity> 用户实体集合
     */
    @RequestMapping("/getListMap")
    public Collection<User> getListMap() {
        //http://localhost:8080/user/getListMap
        Map<String, Object> map = new HashMap<>();
        //kay是字段名 value是字段值
        map.put("age", 20);
        return userService.listByMap(map);
    }

    /**
     * 新增用户信息
     *
     * @Author Sans
     * @CreateTime 2019/6/8 16:40
     */
    @RequestMapping("/saveInfo")
    public Boolean saveInfo() {
        //http://localhost:8080/user/saveInfo
        User user = new User();
        user.setName("name1");
        user.setAge(1234);
        user.setEmail("name1@xx.com");
        return userService.save(user);
    }

    /**
     * 批量新增用户信息
     *
     * @Author Sans
     * @CreateTime 2019/6/8 16:42
     */
    @RequestMapping("/saveInfoList")
    public void saveInfoList() {
        //http://localhost:8080/user/saveInfoList
        //创建对象
        User user2 = new User();
        user2.setName("name2");
        user2.setAge(23);
        //user.setId(1);
        user2.setEmail("name2@xx.com");

        User user = new User();
        user.setName("name3");
        user.setAge(25);
        //user.setId(1);
        user.setEmail("name3@xx.com");
        //批量保存
        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user2);
        userService.saveBatch(list);
    }

    /**
     * 更新用户信息
     *
     * @Author Sans
     * @CreateTime 2019/6/8 16:47
     */
    @RequestMapping("/updateInfo")
    public Boolean updateInfo() {
        //http://localhost:8080/user/updateInfo
        //根据实体中的ID去更新,其他字段如果值为null则不会更新该字段,参考yml配置文件
        User userInfoEntity = new User();
        userInfoEntity.setId(19L);
        userInfoEntity.setName("更新名字");
        return userService.updateById(userInfoEntity);
    }

    /**
     * 新增或者更新用户信息
     *
     * @Author Sans
     * @CreateTime 2019/6/8 16:50
     */
    @RequestMapping("/saveOrUpdateInfo")
    public Boolean saveOrUpdate() {
        //传入的实体类userInfoEntity中ID为null就会新增(ID自增)
        //实体类ID值存在,如果数据库存在ID就会更新,如果不存在就会新增
        User userInfoEntity = new User();
        //userInfoEntity.setId(1L);
        userInfoEntity.setName("新增或者更新");
        return userService.saveOrUpdate(userInfoEntity);
    }

    /**
     * 根据ID删除用户信息
     *
     * @Author Sans
     * @CreateTime 2019/6/8 16:52
     */
    @RequestMapping("/deleteInfo")
    public Boolean deleteInfo(String userId) {
        //http://localhost:8080/user/deleteInfo?userId=19
        return userService.removeById(userId);
    }

    /**
     * 根据ID批量删除用户信息
     *
     * @Author Sans
     * @CreateTime 2019/6/8 16:55
     */
    @RequestMapping("/deleteInfoList")
    public Boolean deleteInfoList() {
        //http://localhost:8080/user/deleteInfoList
        List<String> userIdlist = new ArrayList<>();
        userIdlist.add("13");
        userIdlist.add("14");
        return userService.removeByIds(userIdlist);
    }

    /**
     * 根据指定字段删除用户信息
     *
     * @Author Sans
     * @CreateTime 2019/6/8 16:57
     */
    @RequestMapping("/deleteInfoMap")
    public Boolean deleteInfoMap() {
        //http://localhost:8080/user/deleteInfoMap
        //kay是字段名 value是字段值
        Map<String, Object> map = new HashMap<>();
        map.put("name", "name2");
        map.put("age", 23);
        // and
        return userService.removeByMap(map);
    }


}
