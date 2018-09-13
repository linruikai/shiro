package com.douban.shiro.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by ruikai.lin  on 2018/9/13 下午6:09.
 * Email: ruikai.lin@plusx.cn
 * Copyright (c) 2014 承影互联(科技)有限公司 版权所有
 */
@Mapper
public interface ResourceMapper {

    @Select("select resource from role_resource where role_id = #{id}")
    List<String> getResource(Integer id);
}
