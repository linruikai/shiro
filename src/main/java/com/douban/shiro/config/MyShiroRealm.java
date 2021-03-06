package com.douban.shiro.config;

import com.douban.shiro.bean.User;
import com.douban.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruikai.lin  on 2018/9/13 下午3:52.
 * Email: ruikai.lin@plusx.cn
 * Copyright (c) 2014 承影互联(科技)有限公司 版权所有
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("认证--MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User userInfo = userService.getByUsername(username);
        if (userInfo == null) throw new UnknownAccountException(); //未注册用户
        if (userInfo.getEnable() == 0) throw new LockedAccountException(); //用户已锁定
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPassword(), //密码
               // ByteSource.Util.bytes(username),//salt=username+salt
                getName()  //realm name
        );
//        Session session = SecurityUtils.getSubject().getSession();
//        session.touch();
//        session.setAttribute("currentUser", userInfo);
//        User currentUser =(User) session.getAttribute("currentUser");
//        System.out.println(currentUser);
        return authenticationInfo;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权--权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();

        // 以下内容根据自己设计的表结构，添加进用户的角色和对应的权限
        List<String> roles = new ArrayList<>();
        roles.add("user");
        roles.add("manager");
        authorizationInfo.addRoles(roles);


        List<String> permissions = new ArrayList<>();
        permissions.add("/add");
        permissions.add("/del");
        authorizationInfo.addStringPermissions(permissions);

        return authorizationInfo;
    }
}
