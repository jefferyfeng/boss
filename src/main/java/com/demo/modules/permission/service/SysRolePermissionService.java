package com.demo.modules.permission.service;

import com.demo.modules.per.model.ZTree;
import com.demo.modules.permission.entity.SysRolePermission;
import java.util.List;

/**
 *  SysRolePermissionService  接口
 *
 *  @author fdh
 */
public interface SysRolePermissionService {
    /**
     * 新增SysRolePermission
     * @param sysRolePermission
     */
    void add(SysRolePermission sysRolePermission);

    /**
     * 根据主键 删除SysRolePermission
     * @param sysRolePermission
     */
    void remove(SysRolePermission sysRolePermission);

    /**
     * 修改SysRolePermission
     * @param sysRolePermission
     */
    void modify(SysRolePermission sysRolePermission);

    /**
     * 根据主键查询SysRolePermission
     * @param id
     * @return sysRolePermission
     */
    SysRolePermission queryOne(java.lang.Long id);

    /**
     * 根据主键查询SysRolePermission
     * @return sysRolePermissions
     */
    List<SysRolePermission> queryAll();

    /**
     * 根据字段查询（如需分页请setPageBean）
     * @param sysRolePermission
     * @return sysRolePermissions
     */
    List<SysRolePermission> queryByFieldsAndPage(SysRolePermission sysRolePermission);

    /**
     * 删除角色菜单绑定关系
     * @param roleIds
     * @param permissionIds
     */
    void batchRemoveRolePermission(Long[] roleIds, Long[] permissionIds);

    /**
     * 获取角色所有权限菜单
     * @param roleId
     * @return
     */
    List<ZTree> queryZTreeAllPermissions(Long roleId);

    /**
     * 修改角色菜单
     * @param checkedPermissionIds
     * @param uncheckedPermissionIds
     * @param roleId
     */
    void editRolePermission(String checkedPermissionIds, String uncheckedPermissionIds, Long roleId);
}