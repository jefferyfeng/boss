package com.demo.modules.permission.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.demo.core.base.BasePojo;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *  SysUser  
 *
 *  @author fdh
 */
public class SysUser extends BasePojo{
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

    public java.lang.Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public java.lang.String getUsername() {
        return username;
    }

    public void setUsername(java.lang.String username) {
        this.username = username;
    }

    public java.lang.Integer getSex() {
        return sex;
    }

    public void setSex(java.lang.Integer sex) {
        this.sex = sex;
    }

    public java.lang.String getEmail() {
        return email;
    }

    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    public java.lang.String getPassword() {
        return password;
    }

    public void setPassword(java.lang.String password) {
        this.password = password;
    }

    public java.lang.String getPhone() {
        return phone;
    }

    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }

    public java.lang.String getSalt() {
        return salt;
    }

    public void setSalt(java.lang.String salt) {
        this.salt = salt;
    }

    public java.lang.Integer getStatus() {
        return status;
    }

    public void setStatus(java.lang.Integer status) {
        this.status = status;
    }

    public java.lang.Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(java.lang.Integer isValid) {
        this.isValid = isValid;
    }

    public java.util.Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    public java.lang.Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(java.lang.Long createUser) {
        this.createUser = createUser;
    }

    public java.util.Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(java.util.Date updateDate) {
        this.updateDate = updateDate;
    }

    public java.lang.Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(java.lang.Long updateUser) {
        this.updateUser = updateUser;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}