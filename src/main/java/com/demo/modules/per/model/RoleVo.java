package com.demo.modules.per.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

public class RoleVo {
    /**id*/
    private java.lang.Long id;
    /**角色名称*/
    private java.lang.String roleName;
    /**角色描述*/
    private java.lang.String description;
    /**状态*/
    private java.lang.Integer status;
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

    public java.lang.String getRoleName() {
        return roleName;
    }

    public void setRoleName(java.lang.String roleName) {
        this.roleName = roleName;
    }

    public java.lang.String getDescription() {
        return description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
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
}
