package com.douban.shiro.mapper;

import com.douban.shiro.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by ruikai.lin  on 2018/9/13 下午6:07.
 * Email: ruikai.lin@plusx.cn
 * Copyright (c) 2014 承影互联(科技)有限公司 版权所有
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where phone = #{phone}")
    User getByPhone(String phone);
}
