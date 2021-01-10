package com.fishpro.demo.mybatis.entity;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName : User
 * @Author : Administrator
 * @Date: 2021/1/9 18:20
 * @Description : User
 */
@Data
public class User{

    private int id;
    private String name;
    private String sex;
    private Date createTime;
}
