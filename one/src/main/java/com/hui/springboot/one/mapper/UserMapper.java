package com.hui.springboot.one.mapper;

import com.hui.springboot.one.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface UserMapper {
    @Select("select * from users")
    @Results(
            {
                    @Result(property = "userSex", column = "user_sex", javaType = String.class),
                    @Result(property = "nickName", column = "nick_name")
            }
    )
    List<UserEntity> getAll();

    @Select("select  * from users where id = #{id}")
    @Results(
            {
                    @Result(property = "userSex", column = "user_sex", javaType = String.class),
                    @Result(property = "nickName", column = "nick_name")
            }
    )
    UserEntity getOne(Long id);

    @Insert("INSERT INTO users(userName,passWord,user_sex) VALUES(#{userName}, #{passWord}, #{userSex})")
    int insert(UserEntity user);

    @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
    void update(UserEntity user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    void delete(Long id);
}
