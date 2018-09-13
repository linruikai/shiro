package com.douban.shiro;

/**
 * Created by ruikai.lin  on 2018/9/13 下午5:54.
 * Email: ruikai.lin@plusx.cn
 * Copyright (c) 2014 承影互联(科技)有限公司 版权所有
 */
public enum RoleTypeEnum {
    ADMIN("管理员",101),
    USER("用户",102),
    GUEST("游客",103);

    private String role;
    private Integer type;

    RoleTypeEnum(String role, Integer type) {
        this.role = role;
        this.type = type;
    }
}
