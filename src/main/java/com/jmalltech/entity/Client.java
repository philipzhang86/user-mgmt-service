package com.jmalltech.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@TableName(value = "mwms_client", schema = "public")
@Setter
@Getter
@ToString
public class Client implements Serializable, IUser{
    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String email;

    @TableField(value = "company_name")
    private String companyName;

    @TableField(value = "area_code")
    private String areaCode;

    private String phone;

    private String address;

    private String city;

    private String state;

    private String country;

    private Long createdById;

    private Long updatedById;

    @TableField(value = "created_date", fill = FieldFill.INSERT)
    private Date createdDate;

    @TableField(value = "updated_date", fill = FieldFill.INSERT_UPDATE)
    private Date updatedDate;

    private static final long serialVersionUID = 1L;
}
