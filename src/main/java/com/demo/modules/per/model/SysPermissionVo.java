package com.demo.modules.per.model;

import java.util.List;

public class SysPermissionVo {
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
    /**权限图标*/
    private java.lang.String permissionIcon;
    /**子权限*/
    private List<SysPermissionVo> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    public String getPermissionIcon() {
        return permissionIcon;
    }

    public void setPermissionIcon(String permissionIcon) {
        this.permissionIcon = permissionIcon;
    }

    public List<SysPermissionVo> getChildren() {
        return children;
    }

    public void setChildren(List<SysPermissionVo> children) {
        this.children = children;
    }
}
