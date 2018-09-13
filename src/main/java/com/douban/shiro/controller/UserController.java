package com.douban.shiro.controller;

import com.douban.shiro.bean.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ruikai.lin  on 2018/9/13 下午5:58.
 * Email: ruikai.lin@plusx.cn
 * Copyright (c) 2014 承影互联(科技)有限公司 版权所有
 */
@RestController
@RequestMapping("user")
public class UserController {

    @PostMapping("login")
    public String login(User user){
        if (user.getPhone() == null || user.getPassword()==null){
            return "to login";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getPhone(), user.getPassword());
        try {
            subject.login(token);
            return "ok";
        }catch (AuthenticationException e){
            return "wrong";
        }
    }
}
