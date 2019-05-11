package com.hcy.shirodemo.service;

import com.hcy.shirodemo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserService userService;
    @Test
    public void findAllUser() {


    }

    @Test
    public void findUserById() {
        System.out.println(userService.findUserById(1));

    }

    @Test
    public void findUserByName() {
        System.out.println(userService.findUserByName("hcy"));

    }


    @Test
    public void insertUser() {
        User user = new User();
        user.setName("hcy");
        user.setPasswd("passwd");
        System.out.println(userService.insertUser(user));
        User user2 = new User();
        user2.setName("hcy");
        user2.setPasswd("p11asswd");
        System.out.println(userService.insertUser(user2));
    }

    @Test
    public void addUserPermissions() {

        userService.addUserPermissions(1,"aa");
        userService.addUserPermissions(1,"bb");
    }

    @Test
    public void deleteUserPermissions() {
        userService.deleteUserPermissions(1,"bb");
    }
}