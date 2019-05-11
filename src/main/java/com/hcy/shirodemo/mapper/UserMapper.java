package com.hcy.shirodemo.mapper;

import com.hcy.shirodemo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: UserMapper
 * @Author: hcy
 * @Description:
 * @Date: 2019-05-08 21:36
 * @Version: 1.0
 **/


@Mapper
@Component(value = "userMapper")
public interface UserMapper {

    @Select("select * from user;")
    List<User> findAllUser();

    @Select("select * from user where id = #{id} limit 1;")
    User findUserById(@Param("id") long id);

    @Select("select * from user where name = #{name} limit 1;")
    User findUserByName(@Param("name") String name);

    @Insert("insert user(name,passwd)values(#{user.name},#{user.passwd});update user set permissions =  JSON_ARRAY('my:update') where  user.name = #{user.name};")
    long insertUser(@Param("user") User user);

    @Update("update user set permissions =  JSON_MERGE(user.permissions,JSON_ARRAY(#{permissions}))where user.id  = #{id};")
    void addUserPermissions(@Param("id") long id, @Param("permissions") String permissions);

    @Update("update user set permissions = json_remove(permissions, JSON_UNQUOTE(JSON_SEARCH(permissions, 'one', #{permissions}))) where  user.id = #{id};")
    void deleteUserPermissions(@Param("id") long id, @Param("permissions") String permissions);

    @Update("update user set role = #{role} where  user.id = #{id};")
    void editUserRole(@Param("id") long id, @Param("role") String role);

    //获取是否有该权限
    @Select("select count(JSON_SEARCH(permissions, 'all', #{permissions})) from user where id = #{id};")
    int haveUserPermissions(@Param("id") long id, @Param("permissions") String permissions);

}


