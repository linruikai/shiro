package com.douban.shiro.config;

import com.douban.shiro.bean.User;
import com.douban.shiro.service.ResourceService;
import com.douban.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ruikai.lin  on 2018/9/13 下午3:52.
 * Email: ruikai.lin@plusx.cn
 * Copyright (c) 2014 承影互联(科技)有限公司 版权所有
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String phone = (String) authenticationToken.getPrincipal();
        User user = userService.getByPhone(phone);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(phone), getName());
        //验证通过后，把用户信息放入session中
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("user", user);
        return info;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取session中的user
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<String> resources = resourceService.getResource(user.getId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (String resource :resources) {
            info.addStringPermission(resource);
        }
        return info;
    }
}
