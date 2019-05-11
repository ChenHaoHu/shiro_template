package com.hcy.shirodemo.entity;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: User
 * @Author: hcy
 * @Description:
 * @Date: 2019-05-08 21:34
 * @Version: 1.0
 **/


@Data
public class User {

    private long id;
    private String name;
    private String passwd;
    private String role;
    private String permissions;
    private String creat_time;
    private String update_time;

   public User(){

    }
}
