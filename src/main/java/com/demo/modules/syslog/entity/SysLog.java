package com.demo.modules.syslog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.demo.core.base.BasePojo;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *  SysLog  
 *
 *  @author fdh
 */
public class SysLog extends BasePojo{
    /**id*/
    private java.lang.Long id;
    /**操作用户id*/
    private java.lang.Long userId;
    /**操作用户名*/
    private java.lang.String username;
    /**操作者角色*/
    private java.lang.String userRoles;
    /**操作模块*/
    private java.lang.String logModule;
    /**操作内容*/
    private java.lang.String operation;
    /**操作结果*/
    private java.lang.String result;
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

    public java.lang.Long getUserId() {
        return userId;
    }

    public void setUserId(java.lang.Long userId) {
        this.userId = userId;
    }

    public java.lang.String getUsername() {
        return username;
    }

    public void setUsername(java.lang.String username) {
        this.username = username;
    }

    public java.lang.String getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(java.lang.String userRoles) {
        this.userRoles = userRoles;
    }

    public java.lang.String getLogModule() {
        return logModule;
    }

    public void setLogModule(java.lang.String logModule) {
        this.logModule = logModule;
    }

    public java.lang.String getOperation() {
        return operation;
    }

    public void setOperation(java.lang.String operation) {
        this.operation = operation;
    }

    public java.lang.String getResult() {
        return result;
    }

    public void setResult(java.lang.String result) {
        this.result = result;
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