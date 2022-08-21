package com.hui.springboot.one.controller;

import com.hui.springboot.one.dao.UserRepository;
import com.hui.springboot.one.dao.UsersDao;
import com.hui.springboot.one.entity.UserEntity;
import com.hui.springboot.one.entity.UserSexEnum;
import com.hui.springboot.one.mapper.UserMapper;
import com.hui.springboot.one.mapper2.UserMapper2;
import com.hui.springboot.one.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserMapper2 userMapper2;
    @Autowired
    private UsersDao usersDao;

    /**
     * 取url中参数
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "user/{id}")
    @Cacheable(value = "user-key")
    public User getId(@PathVariable("id") Long id) {
        System.out.println(id);
        User user = userRepository.findById(id).get();
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }

    /**
     * 取请求休中参数
     *
     * @param name
     * @return
     */
    @RequestMapping("user/byName")//http://127.0.0.1:8080/user/byName?name=bb2
    public User getPathParam(@PathParam("name") String name) {
        //select user0_.id as id1_0_, user0_.age as age2_0_, user0_.email as email3_0_, user0_.nick_name as nick_nam4_0_, user0_.password as password5_0_, user0_.reg_time as reg_time6_0_, user0_.username as username7_0_ from user user0_ where user0_.username=?
        return userRepository.findByUsername(name);
    }

    @RequestMapping("/uid")//http://127.0.0.1:8080/uid
    public String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        System.out.println("uid=" + uid);
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }

    @RequestMapping("/saveuser")
    public void saveUser() {
        UserEntity userEntity = new UserEntity("m1", "a2123456", UserSexEnum.WOMAN);
        userMapper.insert(userEntity);
        System.out.println("saveuser");
    }

    @RequestMapping("/saveuser2")
    public void saveUser2() {
        UserEntity userEntity = new UserEntity("m2", "a2123456", UserSexEnum.WOMAN);
        userMapper2.insert(userEntity);
        System.out.println("saveuser2");
    }


    @RequestMapping("/users/saveuser")
    public void saveUser3() {
        UserEntity userEntity = new UserEntity("a2", "a2123456", UserSexEnum.WOMAN);
        usersDao.insert(userEntity);
        System.out.println("/users/saveuser");
    }

    @RequestMapping("/users/getUser/{id}")
    public UserEntity getUsersById(@PathVariable("id") Long id) {
        System.out.println(id);
        System.out.println("/users/getUser/{id}");
        return usersDao.getOne(id);

    }
}
