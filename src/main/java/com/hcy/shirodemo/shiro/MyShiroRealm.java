package com.hcy.shirodemo.shiro;

import com.alibaba.fastjson.JSONArray;
import com.hcy.shirodemo.entity.User;
import com.hcy.shirodemo.service.UserService;
import com.hcy.shirodemo.util.JWTToken;
import com.hcy.shirodemo.util.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

//实现AuthorizingRealm接口用户用户认证
public class MyShiroRealm extends AuthorizingRealm {



    @Autowired
    UserService userService;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 执行授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权");

        String token = (String) principalCollection.getPrimaryPrincipal();
        String username = JWTUtil.getUsername(token);
        User user = userService.findUserByName(username);

        if (user!=null){
            String userPermissions = user.getPermissions();
            JSONArray array = JSONArray.parseArray(userPermissions);
            List<String> permissions = array.toJavaList(String.class);
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addRole(user.getRole());
            simpleAuthorizationInfo.addStringPermissions(permissions);
            return simpleAuthorizationInfo;
        }

        return null;

    }

    /**
     * 执行人证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        User user = userService.findUserByName(username);
        if (user == null) {
            throw new AuthenticationException("token invalid");
        }

        if (! JWTUtil.verify(token, username, user.getPasswd())) {
            throw new AuthenticationException("Username or password error");
        }

        System.out.println(user+"成功登陆");


        SimpleAuthenticationInfo my_realm = new SimpleAuthenticationInfo(token, token, "my_realm");
        //授权
//        doGetAuthorizationInfo(my_realm.getPrincipals());


        return my_realm;
    }
}