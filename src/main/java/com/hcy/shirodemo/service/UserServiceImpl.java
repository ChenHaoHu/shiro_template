package com.hcy.shirodemo.service;

import com.hcy.shirodemo.entity.User;
import com.hcy.shirodemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @Author: hcy
 * @Description:
 * @Date: 2019-05-08 21:53
 * @Version: 1.0
 **/


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> findAllUser() {

        List<User> allUser = userMapper.findAllUser();

        return allUser;
    }


    @Override
    public User findUserById(long id) {
        User userById = userMapper.findUserById(id);
        return userById;
    }

    @Override
    public User findUserByName(String name) {
         User userByName = userMapper.findUserByName(name);
        return userByName;
    }

    @Override
    public boolean insertUser(User user) {
        try {
            long i = userMapper.insertUser(user);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean addUserPermissions(long id, String permissions) {
        int i = userMapper.haveUserPermissions(id, permissions);
        if (i==0){
            userMapper.addUserPermissions(id, permissions);
        }else{
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteUserPermissions(long id, String permissions) {
        userMapper.deleteUserPermissions(id, permissions);
        return true;
    }

    @Override
    public boolean editUserRole(long id, String role) {
        userMapper.editUserRole(id,role);
        return true;
    }


}
