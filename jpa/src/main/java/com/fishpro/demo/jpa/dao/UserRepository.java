package com.fishpro.demo.jpa.dao;

import com.fishpro.demo.jpa.domain.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @ClassName : UserRepository
 * @Author : Administrator
 * @Date: 2021/1/2 18:52
 * @Description : UserRepository
 */
@Repository
@Component
public interface UserRepository extends JpaRepository<UserDO,String>  {
}
