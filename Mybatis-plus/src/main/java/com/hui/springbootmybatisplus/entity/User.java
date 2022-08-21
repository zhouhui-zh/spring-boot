package com.hui.springbootmybatisplus.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("user")
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //@TableField("user_name")
    private String name;

    private Integer age;

    private String email;

    /**
     * 逻辑删除
     */
    //@TableLogic
    // private Integer isDeleted;
}