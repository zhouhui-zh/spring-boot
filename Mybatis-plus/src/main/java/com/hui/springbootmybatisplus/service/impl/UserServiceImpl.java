package com.hui.springbootmybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hui.springbootmybatisplus.dao.UserMapper;
import com.hui.springbootmybatisplus.entity.User;
import com.hui.springbootmybatisplus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author zhouhui
 * @Description desc
 * @date 2022-07-25 22:58
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
