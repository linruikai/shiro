package com.douban.shiro.service.impl;

import com.douban.shiro.mapper.ResourceMapper;
import com.douban.shiro.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ruikai.lin  on 2018/9/13 下午5:49.
 * Email: ruikai.lin@plusx.cn
 * Copyright (c) 2014 承影互联(科技)有限公司 版权所有
 */
@Service
public class ResourceServiceImpl implements ResourceService{
    @Autowired
    private ResourceMapper resourceMapper;
    @Override
    public List<String> getResource(Integer id) {
        return resourceMapper.getResource(id);
    }
}
