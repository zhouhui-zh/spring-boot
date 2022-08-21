package com.hui.springbootmybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hui.springbootmybatisplus.dao.UserMapper;
import com.hui.springbootmybatisplus.entity.User;
import com.hui.springbootmybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @author zhouhui
 * @Description desc
 * @date 2022-07-26 0:09
 */

@SpringBootTest
public class MybatisPlusWrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;


    /**
     * 查询用户名包含a，年龄在20到30之间，并且邮箱不为null的用户信息
     * SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (user_name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
     */
    @Test
    void test01(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name","a")
                .between("age",20,30)
                .isNotNull("email");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }


    /**
     * 按年龄降序查询用户，如果年龄相同则按id升序排列
     * SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 ORDER BY age DESC,uid ASC
     */
    @Test
    void test02(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age").orderByAsc("uid");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }


    /**
     * 删除email为空的用户
     * UPDATE t_user SET is_deleted=1 WHERE is_deleted=0 AND (email IS NULL)
     */
    @Test
    void test03(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println("影响的行数:"+result);
    }


    /**
     * 将（年龄大于20并且用户名中包含有a）或邮箱为null的用户信息修改
     * UPDATE t_user SET age=?, email=? WHERE is_deleted=0 AND (age >= ? AND user_name LIKE ? OR email IS NULL)
     */
    @Test
    void test04(){
        User user = new User();
        user.setAge(10);
        user.setEmail("zl@163.com");

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("age",20)
                .like("user_name","a")
                .or()
                .isNull("email");

        int result = userMapper.update(user, queryWrapper);
        System.out.println("受影响的行数:"+result);
    }


    /**
     * 将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
     * UPDATE t_user SET age=?, email=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age >= ? OR email IS NULL))
     */
    @Test
    void test05(){
        User user = new User();
        user.setAge(15);
        user.setEmail("zl2@163.com");

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name","a")
                .and(i->i.ge("age",20).or().isNull("email"));

        int result = userMapper.update(user, queryWrapper);
        System.out.println("受影响的行数："+result);
    }

    //todo 组装select子句


    /**
     * 查询用户信息的username和age字段
     * SELECT user_name,age FROM t_user WHERE is_deleted=0
     */
    @Test
    void test06(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_name","age");

        //selectMaps()返回Map集合列表，通常配合select()使用，避免User对象中没有被查询到的列值为null
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }


    /**
     * 将（年龄大于20或邮箱为null）并且用户名中包含有a的用户信息修改
     * UPDATE t_user SET age=?,email=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
     */
    @Test
    void test07(){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("age",18)
                .set("email","zl@163.com")
                .like("user_name","a")
                .and(i->i.gt("age",20).or().isNull("email"));

        int result = userMapper.update(null, updateWrapper);
        System.out.println("影响行数:"+result);
    }


    /**
     * Condition条件
     * SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (age >= ? AND age <= ?)
     */
    @Test
    void test08(){
        //定义查询条件，有可能为null（用户未输入或未选择）
        String username = null;
        Integer ageBegin = 10;
        Integer ageEnd = 24;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username), "username", "a")
                .ge(ageBegin != null, "age", ageBegin)
                .le(ageEnd != null, "age", ageEnd);

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }



    @Test
    public void test09() {
        //定义查询条件，有可能为null（用户未输入）
        String username = "a";
        Integer ageBegin = 10;
        Integer ageEnd = 24;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //避免使用字符串表示字段，防止运行时错误
        queryWrapper
                .like(StringUtils.isNotBlank(username), User::getName, username)
                .ge(ageBegin != null, User::getAge, ageBegin)
                .le(ageEnd != null, User::getAge, ageEnd);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }



    @Test
    public void test10() {
        //组装set子句
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(User::getAge, 18)
                .set(User::getEmail, "user@atguigu.com")
                .like(User::getName, "a")
                .and(i -> i.lt(User::getAge, 24).or().isNull(User::getEmail));
        //lambda表达式内的逻辑优先运算
        User user = new User();
        int result = userMapper.update(user, updateWrapper);
        System.out.println("受影响的行数：" + result);
    }
}
