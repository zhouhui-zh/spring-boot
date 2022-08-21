package com.hui.springbootmybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hui.springbootmybatisplus.entity.User;
import com.hui.springbootmybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class CardInfoPlusController {
    @Autowired
    private UserService userService;

    /**
     * MP扩展演示
     *
     * @Author Sans
     * @CreateTime 2019/6/8 16:37
     * @Return Map<String, Object> 返回数据
     */
    @RequestMapping("/getInfoListPlus")
    public Map<String, Object> getInfoListPage() {
        //初始化返回类
        Map<String, Object> result = new HashMap<>();
        //查询年龄等于18岁的学生
        //等价SQL: SELECT id,name,age,skill,evaluate,fraction FROM user_info WHERE age = 18
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.lambda().eq(User::getAge, 18);
        List<User> userInfoEntityList1 = userService.list(queryWrapper1);
        result.put("studentAge18", userInfoEntityList1);
        //查询年龄大于5岁的学生且小于等于18岁的学生
        //等价SQL: SELECT id,name,age,skill,evaluate,fraction FROM user_info WHERE age > 5 AND age <= 18
        QueryWrapper<User> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.lambda().gt(User::getAge, 5);
        queryWrapper2.lambda().le(User::getAge, 18);
        List<User> userInfoEntityList2 = userService.list(queryWrapper2);
        result.put("studentAge5", userInfoEntityList2);
        //模糊查询技能字段带有"画"的数据,并按照年龄降序
        //等价SQL: SELECT id,name,age,skill,evaluate,fraction FROM user_info WHERE skill LIKE '%画%' ORDER BY age DESC
        QueryWrapper<User> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.lambda().like(User::getName, "画");
        queryWrapper3.lambda().orderByDesc(User::getAge);
        List<User> userInfoEntityList3 = userService.list(queryWrapper3);
        result.put("studentAgeSkill", userInfoEntityList3);
        //模糊查询名字带有"小"或者年龄大于18的学生
        //等价SQL: SELECT id,name,age,skill,evaluate,fraction FROM user_info WHERE name LIKE '%小%' OR age > 18
        QueryWrapper<User> queryWrapper4 = new QueryWrapper<>();
        queryWrapper4.lambda().like(User::getName, "name");
        queryWrapper4.lambda().or().gt(User::getAge, 18);
        List<User> userInfoEntityList4 = userService.list(queryWrapper4);
        result.put("studentOr", userInfoEntityList4);
        //查询评价不为null的学生,并且分页
        //等价SQL: SELECT id,name,age,skill,evaluate,fraction FROM user_info WHERE evaluate IS NOT NULL LIMIT 0,5
        IPage<User> page = new Page<>();
        page.setCurrent(1);
        page.setSize(5);
        QueryWrapper<User> queryWrapper5 = new QueryWrapper<>();
        queryWrapper5.lambda().isNotNull(User::getEmail);
        page = userService.page(page, queryWrapper5);
        result.put("studentPage", page);
        return result;
    }
}
