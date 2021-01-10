package com.fishpro.demo;

import com.fishpro.demo.jpa.dao.PersonRepository;
import com.fishpro.demo.jpa.dao.UserRepository;
import com.fishpro.demo.jpa.domain.UserDO;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private UserRepository userRepository;


    /**
     * 初始化一个对象 UserDO 测试Insert过程
     * */
    public void before(){
        UserDO userDO=new UserDO();
        userDO.setUserName("fishpro");
        userDO.setSex(1);
//        userDO.setLastLoginTime(new Date(System.currentTimeMillis()));
        userDO.setPassword("passWord");
        userRepository.save(userDO);
    }

    public void testFind(){
        Optional<UserDO> optionalUserDO=userRepository.findById("1");
        if(optionalUserDO.isPresent()){
            UserDO userDO=optionalUserDO.get();
            System.out.println("testFind user:"+userDO.getUserName());
        }

    }

    public void testFindAll(){
        List<UserDO> list=userRepository.findAll();
        for (UserDO user:list) {
            System.out.println("user_name:"+user.getUserName());
        }
    }

    @Test
    public void testUpdate(){
        Optional<UserDO> optionalUserDO=userRepository.findById("4028818176c611e50176c611ec5f0000");
        if(optionalUserDO.isPresent()){
            UserDO userDO=optionalUserDO.get();
            userDO.setUserName("fishpro001");
            userRepository.save(userDO);
            System.out.println("testFind user:"+userDO.getUserName());
        }

    }

    @After
    public void after(){
//        userRepository.deleteById(1);
    }

    @Autowired
    private PersonRepository personRepository;

    /**
     * 初始化一个对象 UserDO 测试Insert过程
     * */
    public void before2(){
        personRepository.test();
    }

    public void update(){
        personRepository.update();
    }


    public void findByName(){
        personRepository.findByName();
    }

    public void findUpdate(){
        personRepository.findUpdate();
    }

    public void findDelete(){
        personRepository.findDelete();
    }
}
