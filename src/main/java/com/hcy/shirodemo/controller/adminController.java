package com.hcy.shirodemo.controller;

import com.hcy.shirodemo.config.BaseResponse;
import com.hcy.shirodemo.entity.User;
import com.hcy.shirodemo.service.UserService;
import com.hcy.shirodemo.util.JWTUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.Subject;

/**
 * @ClassName: adminController
 * @Author: hcy
 * @Description:
 * @Date: 2019-05-08 23:54
 * @Version: 1.0
 **/

@RestController
@RequestMapping("/admin")
public class adminController {

    @Autowired
    UserService userService;

    @RequestMapping("/data")
    public BaseResponse lookAdmin(){
        return new BaseResponse(true,null,"look admin");
    }

    @RequestMapping("/edit")
    public BaseResponse editAdmin(){
        return new BaseResponse(true,null,"edit admin");
    }

    @RequestMapping("/permissions/add")
    @RequiresRoles("admin")
    public BaseResponse addPermissions(@RequestParam("name") String  name, @RequestParam("permissions") String  permissions){

        User user = userService.findUserByName(name);
        if (user != null){
            boolean b = userService.addUserPermissions(user.getId(), permissions);
            if(b==true){
                return new BaseResponse(true,null,"add permissions: "+permissions );
            }else{
                return new BaseResponse(true,null,"have permissions: "+permissions );
            }
        }else{
            return new BaseResponse(false,null,"没有该用户");
        }
    }

    @RequestMapping("/role/edit")
    @RequiresRoles("admin")
    public BaseResponse editUserRole(@RequestParam("name") String  name, @RequestParam("role") String  role){
        User user = userService.findUserByName(name);
        if (user != null){
            userService.editUserRole(user.getId(),role);
            return new BaseResponse(true,null,"edit role: "+ role );
        }else{
            return new BaseResponse(false,null,"没有该用户");
        }
    }

}
