package com.demo.modules.permission.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.demo.core.base.BasePojo;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *  SysPermission  
 *
 *  @author fdh
 */
public class SysPermission extends BasePojo{
    /**id*/
    private java.lang.Long id;
    /**权限名称*/
    private java.lang.String permissionName;
    /**父级权限id*/
    private java.lang.String parentId;
    /**权限URL*/
    private java.lang.String permissionUrl;
    /**次序*/
    private java.lang.Integer sequence;
    /**层级*/
    private java.lang.Integer layer;
    /**图标*/
    private java.lang.String permissionIcon;
    /**是否有效0.无效 1.有效*/
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

    public java.lang.String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(java.lang.String permissionName) {
        this.permissionName = permissionName;
    }

    public java.lang.String getParentId() {
        return parentId;
    }

    public void setParentId(java.lang.String parentId) {
        this.parentId = parentId;
    }

    public java.lang.String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(java.lang.String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    public java.lang.Integer getSequence() {
        return sequence;
    }

    public void setSequence(java.lang.Integer sequence) {
        this.sequence = sequence;
    }

    public java.lang.String getPermissionIcon() {
        return permissionIcon;
    }

    public void setPermissionIcon(java.lang.String permissionIcon) {
        this.permissionIcon = permissionIcon;
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

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }
}