package com.jmalltech.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


@TableName(value = "t_user", schema = "public")
@Getter//用细颗粒度,而不是用@Data 这个大而全
@Setter
@ToString
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer age;

    private String email;

    @TableField(value = "created_date", fill = FieldFill.INSERT)
    private Date createdDate;

    @TableField(value = "updated_date", fill = FieldFill.INSERT_UPDATE)
    private Date updatedDate;

    private static final long serialVersionUID = 1L;



}