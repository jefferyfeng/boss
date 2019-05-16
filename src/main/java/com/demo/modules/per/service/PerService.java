package com.demo.modules.per.service;

import com.demo.core.base.PageBean;
import com.demo.modules.per.model.*;
import com.demo.modules.permission.entity.SysPermission;
import com.demo.modules.permission.entity.SysRole;
import com.demo.modules.permission.entity.SysUser;

import java.util.List;
import java.util.Map;

public interface PerService {
    List<SysPermission> queryPermissionByUserName(String username);
    SysUser queryByUsername(String username);
    UserVo login(SysUser sysUser);

    //查询 TODO 缓存
    List<SysPermission> queryPermissionsByRole(List<Long> roles);

    /**查询多级*/
    List<SysPermissionVo> queryAllPermissionsByParentId(Long parentId);

    List<SysPermissionVo> queryPermissionByParentId(Long parentId);

    //根据用户id查询角色 需要 TODO 缓存
    List<RoleVo> queryRolesByUserId(Long userId);

    //获取 权限集合
    List<SysPermissionVo> listPermissions(Long parentId,Long[] permissions);

    //获取当前parentId下的一级子菜单，不递归
    List<LayuiNav> listNavsByParentId(Long parentId, Long[] permissions);

    //获取当前parentId下的菜单 递归
    List<LayuiNav> listAllNavsByParentId(Long parentId, Long[] permissions);

    //获取用户数据表格 分页
    List<UserVo> listUsersByPage(UserVo user, PageBean pageBean);

    //获取角色数据表格 分页
    List<RoleVo> listRolesByPage(RoleVo role, PageBean pageBean);

    //添加用户
    Long addUser(SysUser user);

    //重置密码
    void resetPassword(Long[] ids);

    //删除用户
    void deleteUsers(Long[] ids);

    //批量修改状态
    void updateUserStatus(Long[] ids, Integer status);

    //查询用户
    UserVo queryUser(Long userId);

    //修改用户
    void editUser(SysUser user);

    //添加角色
    void addRole(SysRole role);

    //修改角色状态
    void updateRoleStatus(Long[] ids, Integer status);

    //批量删除角色
    void batchDelete(Long[] ids);

    //获取用户角色配置列表
    Object listUserRolesByPage(RoleVo role, PageBean pageBean, Long userId);

    //修改用户的角色
    void editUserRole(String checkedIds,String uncheckedIds, Long userId);

    //获取权限树
    List<ZTree> queryZTreePermissions(Long parentId);

    //删除权限菜单
    void deletePermission(Long permissionId);

    //添加权限菜单
    void addPermission(SysPermission sysPermission);

    //修改权限菜单
    void editPermission(SysPermission sysPermission);

    //获取完成的权限树
    List<ZTree> queryZTreeAllPermissions(Long roleId);

    //修改角色菜单
    void editRolePermission(String checkedPermissionIds, String uncheckedPermissionIds, Long roleId);
}
