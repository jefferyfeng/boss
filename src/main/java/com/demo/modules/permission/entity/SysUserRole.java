package com.demo.modules.permission.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.demo.core.base.BasePojo;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *  SysUserRole  
 *
 *  @author fdh
 */
public class SysUserRole extends BasePojo{
    /**id*/
    private java.lang.Long id;
    /**用户id*/
    private java.lang.Long userId;
    /**角色id*/
    private java.lang.Long roleId;
    /**是否有效 0.无效 1.有效*/
    private java.lang.Integer isValid;
    /**创建时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//前端To后端
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//后端To前端
    private java.util.Date createDate;
    /**创建人*/
    private java.lang.Long createUser;
    /**更新时间*/
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

    public java.lang.Long getUserId() {
        return userId;
    }

    public void setUserId(java.lang.Long userId) {
        this.userId = userId;
    }

    public java.lang.Long getRoleId() {
        return roleId;
    }

    public void setRoleId(java.lang.Long roleId) {
        this.roleId = roleId;
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

}