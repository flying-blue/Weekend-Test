package com.fishpro.demo.mybatis.dao;

import com.fishpro.demo.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @ClassName : UserDao
 * @Author : Administrator
 * @Date: 2021/1/9 18:08
 * @Description : UserDao
 */
@Mapper
@Repository
public interface UserDao {

    void insert(@Param("user") User user);
}
