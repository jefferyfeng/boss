package com.demo.modules.permission.service;

import com.demo.modules.per.model.LayuiNav;
import com.demo.modules.per.model.SysPermissionVo;
import com.demo.modules.per.model.ZTree;
import com.demo.modules.permission.entity.SysPermission;
import java.util.List;

/**
 *  SysPermissionService  接口
 *
 *  @author fdh
 */
public interface SysPermissionService {
    /**
     * 新增SysPermission
     * @param sysPermission
     */
    void add(SysPermission sysPermission);

    /**
     * 根据主键 删除SysPermission
     * @param permissionId
     */
    void remove(Long permissionId);

    /**
     * 修改SysPermission
     * @param sysPermission
     */
    void modify(SysPermission sysPermission);

    /**
     * 根据主键查询SysPermission
     * @param id
     * @return sysPermission
     */
    SysPermission queryOne(java.lang.Long id);

    /**
     * 根据主键查询SysPermission
     * @return sysPermissions
     */
    List<SysPermission> queryAll();

    /**
     * 根据字段查询（如需分页请setPageBean）
     * @param sysPermission
     * @return sysPermissions
     */
    List<SysPermission> queryByFieldsAndPage(SysPermission sysPermission);

    /**
     * 查询当前parentId下最大的次序id
     * @param parentId
     * @return
     */
    Integer queryMaxSequence(Long parentId);

    /**
     * 根据权限ids查询权限
     * @param permissionIds
     * @return
     */
    List<SysPermission> queryByPermissionIds(Long[] permissionIds);

    /**
     * 根据父级获取权限菜单
     * @param parentId
     * @return
     */
    List<ZTree> queryZTreePermissions(Long parentId);

    /**
     * 删除权限菜单
     * @param permissionId
     */
    void removePermission(Long permissionId);

    /**
     * 获取多级菜单
     * @param parentId
     * @return
     */
    List<SysPermissionVo> listAllPermissionsByParentId(long parentId);

    /**
     * 获取当前parentId下的菜单 递归
     * @param parentId
     * @param permissions
     * @return
     */
    List<LayuiNav> listAllNavsByParentId(Long parentId, Long[] permissions);

    /**
     * 查看当前权限菜单是否有子权限菜单
     * @param permissionId
     */
    boolean hasChildrenByParentId(Long permissionId);
}