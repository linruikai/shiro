package com.douban.shiro.controller;

import com.douban.shiro.bean.User;
import com.douban.shiro.bind.CurrentUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by ruikai.lin  on 2018/9/13 下午5:58.
 * Email: ruikai.lin@plusx.cn
 * Copyright (c) 2014 承影互联(科技)有限公司 版权所有
 */
@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("login")
    public String login(String phone, String password,@RequestParam(value = "remember",required = false) boolean remember) {
        System.out.println("我进来了");
        if (phone == null || password == null) {
            logger.info("手机号或密码为空");
            //return "login";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(phone, password,true);
        try {
            subject.login(token);
            logger.info("登录成功");
            return "index";
        } catch (UnknownAccountException e) {
            logger.info("账户不存在");
        } catch (IncorrectCredentialsException e) {
            logger.info("密码错误");
        } catch (ExcessiveAttemptsException e){
            logger.info("用户名或密码错误次数大于5次,账户已锁定");
        }
        return "login";
    }

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @RequiresPermissions("/add")
    @GetMapping("add")
    public String add(@CurrentUser User user) {
        System.out.println("当前登录的用户信息："+user);
        logger.info("add  add   add   add");
        return "add";
    }

    @RequiresRoles("user")
    @GetMapping("del")
    public String del() {
        logger.info("del del del del del");
        return "del";
    }

    @RequiresAuthentication
    @GetMapping("home")
    @ResponseBody
    public String home(){
        return "home";
    }

    @GetMapping("403")
    public String unauthorized() {
        return "403";
    }

}
