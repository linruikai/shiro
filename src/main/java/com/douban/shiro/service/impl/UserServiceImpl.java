package com.douban.shiro.service.impl;

import com.douban.shiro.bean.User;
import com.douban.shiro.mapper.UserMapper;
import com.douban.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by ruikai.lin  on 2018/9/13 下午5:06.
 * Email: ruikai.lin@plusx.cn
 * Copyright (c) 2014 承影互联(科技)有限公司 版权所有
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getByUsername(String phone) {
        return userMapper.getByUsername(phone);
    }

    @Override
    public Set<Integer> getRoles(Integer id) {
        return userMapper.getRoles(id);
    }
}
