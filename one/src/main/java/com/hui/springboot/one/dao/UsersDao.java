package com.hui.springboot.one.dao;

import com.hui.springboot.one.entity.UserEntity;

import java.util.List;

public interface UsersDao {

    List<UserEntity> getAll();

    UserEntity getOne(Long id);

    void insert(UserEntity user);

    void update(UserEntity user);

    void delete(Long id);
}
