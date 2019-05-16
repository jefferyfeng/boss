package com.demo.modules.per.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

public class UserRoleModel {
    /**id*/
    private java.lang.Long id;
    /**用户名*/
    private java.lang.String username;
    /**性别 0.未知 1.男 2.女*/
    private java.lang.Integer sex;
    /**头像*/
    private java.lang.String photo;
    /**邮箱*/
    private java.lang.String email;
    /**密码*/
    private java.lang.String password;
    /**手机号*/
    private java.lang.String phone;
    /**盐值*/
    private java.lang.String salt;
    /**用户状态0.禁用 1.启用*/
    private java.lang.Integer status;
    /**是否有效 0.无效 1.有效*/
    private java.lang.Integer isValid;
    /**创建时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//前端To后端
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//后端To前端
    private java.util.Date createDate;
    /**创建人*/
    private java.lang.Long createUser;
    /**修改时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//前端To后端
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//后端To前端
    private java.util.Date updateDate;
    /**更新人*/
    private java.lang.Long updateUser;
    private java.lang.Long roleId;
}
