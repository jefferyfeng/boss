package com.demo.modules.permission.dao;

import com.demo.modules.permission.entity.SysRolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 *  SysRolePermissionDao  接口
 *
 *  @author fdh
 */
public interface SysRolePermissionDao {
    /**
     * 新增SysRolePermission
     * @param sysRolePermission
     */
    void insert(SysRolePermission sysRolePermission);

    /**
     * 根据主键 删除SysRolePermission
     * @param id
     */
    void delete(java.lang.Long id);

    /**
     * 修改SysRolePermission
     * @param sysRolePermission
     */
    void update(SysRolePermission sysRolePermission);

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
     * 根据权限id和角色id查询关联关系
     * @param permissionId 权限id
     * @param roleId 角色id
     * @return sysRolePermissionList 权限id和角色id查询关联关系对象集合
     */
    List<SysRolePermission> queryByRoleAndPermission(@Param("permissionId") Long permissionId, @Param("roleId") Long roleId);

    /**
     * 批量删除角色菜单关联关系
     * @param roleIds
     * @param permissionIds
     * @param updateUser
     * @param updateDate
     */
    void batchRemoveRolePermission(@Param("roleIds") Long[] roleIds, @Param("permissionIds") Long[] permissionIds, @Param("updateUser") Long updateUser, @Param("updateDate") Date updateDate);

    /**
     * 根据角色ids获取权限菜单
     * @param roles
     * @return
     */
    Long[] listPermissionByRoleIds(@Param("roles") Long[] roles);
}