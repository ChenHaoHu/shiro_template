package com.hcy.shirodemo.mapper;

import com.hcy.shirodemo.service.UserService;
import com.hcy.shirodemo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserService userService;

    @Test
    public void testFindAllUser(){

        List<User> allUser = userService.findAllUser();

        allUser.stream().forEach(user -> {
            System.out.println(user);
        });



    }



}