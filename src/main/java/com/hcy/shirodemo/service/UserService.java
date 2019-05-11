package com.hcy.shirodemo.service;

import com.hcy.shirodemo.entity.User;
import org.apache.shiro.authz.annotation.RequiresRoles;

import java.util.List;

public interface UserService {
     List<User> findAllUser();
     User findUserById(long id);
     User findUserByName(String name);
     boolean insertUser(User user);
     boolean addUserPermissions(long id,String permissions);
     boolean deleteUserPermissions(long id,String permissions);
     boolean editUserRole(long id,String role);
}
