package com.demo.modules.per.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.management.relation.Role;
import java.util.Date;
import java.util.List;

public class UserVo {
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
    /**用户所有的角色*/
    private java.util.List<RoleVo> rolesList;
    /**用户所有的角色ids*/
    private Long[] roles;
    /**用户所有的权限ids*/
    private Long[] permissions;
    /**角色 用于查询*/
    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public List<RoleVo> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<RoleVo> rolesList) {
        this.rolesList = rolesList;
    }

    public Long[] getRoles() {
        return roles;
    }

    public void setRoles(Long[] roles) {
        this.roles = roles;
    }

    public Long[] getPermissions() {
        return permissions;
    }

    public void setPermissions(Long[] permissions) {
        this.permissions = permissions;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
