package com.douban.shiro.service;

import com.douban.shiro.bean.User;

/**
 * Created by ruikai.lin  on 2018/9/13 下午5:05.
 * Email: ruikai.lin@plusx.cn
 * Copyright (c) 2014 承影互联(科技)有限公司 版权所有
 */
public interface UserService {
    User getByPhone(String phone);
}
