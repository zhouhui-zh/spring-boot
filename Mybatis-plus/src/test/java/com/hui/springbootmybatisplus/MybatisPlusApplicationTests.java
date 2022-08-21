package com.hui.springbootmybatisplus;

import com.hui.springbootmybatisplus.dao.UserMapper;
import com.hui.springbootmybatisplus.entity.User;
import com.hui.springbootmybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;


    /**
     * 查询所有
     * SELECT id,name,age,email FROM user
     */
    @Test
    void testSelectList() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    /**
     * 插入
     * INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
     */
    @Test
    void testInsert() {
        User user = new User();
        user.setName("张三");
        user.setAge(23);
        user.setEmail("zhangsan@163.com");
        int result = userMapper.insert(user);
        System.out.println("受影响的行数:" + result);
        System.out.println("新建的ID：" + user.getId());
    }

    /**
     * 根据ID删除
     * DELETE FROM user WHERE id=?
     */
    @Test
    void testDeleteById() {
        int result = userMapper.deleteById(1525835539440345090L);
        System.out.println("受影响的行数:" + result);
    }


    /**
     * 根据ID批量删除
     * DELETE FROM user WHERE id IN ( ? , ? )
     */
    @Test
    void testDeleteBatchIds() {
        int result = userMapper.deleteBatchIds(Arrays.asList(1L, 2L));
        System.out.println("受影响的行数:" + result);
    }


    /**
     * 根据Map删除
     * DELETE FROM user WHERE name = ? AND age = ?
     */
    @Test
    void testDeleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("age", 21);
        map.put("name", "Sandy");
        int result = userMapper.deleteByMap(map);
        System.out.println("受影响的行数:" + result);
    }


    /**
     * 根据ID更新
     * UPDATE user SET name=?, age=? WHERE id=?
     */
    @Test
    void testUpdateById() {
        User user = new User();
        user.setId(5L);
        user.setName("admin");
        user.setAge(33);

        int result = userMapper.updateById(user);
        System.out.println("受影响的行数:" + result);
    }


    /**
     * 根据ID查询
     * SELECT id,name,age,email FROM user WHERE id=?
     */
    @Test
    void testSelectById() {
        User user = userMapper.selectById(5L);
        System.out.println(user);
    }


    /**
     * 根据ID批量查询
     * SELECT id,name,age,email FROM user WHERE id IN ( ? , ? )
     */
    @Test
    void testSelectByBatchIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(3L, 5L));
        users.forEach(System.out::println);
    }


    /**
     * 根据Map查询
     * SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
     */
    @Test
    void testSelectByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "admin");
        map.put("age", 33);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    /**
     * 查询总记录数
     * SELECT COUNT( * ) FROM user
     */
    @Test
    void testGetCount() {
        long count = userService.count();
        System.out.println("总记录数:" + count);
    }


    /**
     * 批量插入：执行10次
     * INSERT INTO user ( id, name, age ) VALUES ( ?, ?, ? )
     */
    @Test
    void testSaveBatch() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("zl" + i);
            user.setAge(20 + i);
            userList.add(user);
        }

        boolean flag = userService.saveBatch(userList);
        System.out.println("插入是否成功:" + flag);
    }

}
