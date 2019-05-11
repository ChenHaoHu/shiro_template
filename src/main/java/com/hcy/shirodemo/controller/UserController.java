package com.hcy.shirodemo.controller;

import com.hcy.shirodemo.config.BaseResponse;
import com.hcy.shirodemo.entity.User;
import com.hcy.shirodemo.service.UserService;
import com.hcy.shirodemo.util.JWTUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: test
 * @Author: hcy
 * @Description:
 * @Date: 2019-05-07 16:18
 * @Version: 1.0
 **/

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/reg")
    public BaseResponse regUser(@RequestParam("name") String  name, @RequestParam("passwd") String  passwd){
        User usr = new User();
        usr.setName(name);
        usr.setPasswd(passwd);
        boolean b = userService.insertUser(usr);
        return new BaseResponse(true,null,"ok");
    }

    @RequestMapping("/login")
    public BaseResponse loginUser(@RequestParam("name") String  name,@RequestParam("passwd") String  passwd){
        User userByName = userService.findUserByName(name);
        if (userByName == null){
            return new BaseResponse(false,null,"do not have the user!");
        }
        if (passwd!=null && passwd.equals(userByName.getPasswd())){

            return new BaseResponse(true,null,JWTUtil.sign(userByName.getName(),userByName.getPasswd()));
        }
        return new BaseResponse(false,null,"passwd is not correct!");
    }



}
