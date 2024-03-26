package com.jmalltech.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum StaffRole {
    OPERATOR("OPERATOR"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN");

    @EnumValue // MyBatis Plus将使用注解的值与数据库进行映射
    private final String role;

    StaffRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
