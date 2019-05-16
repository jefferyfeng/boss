package com.demo.modules.permission.dao;

import com.demo.modules.per.model.SysPermissionVo;
import com.demo.modules.permission.entity.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  SysPermissionDao  接口
 *
 *  @author fdh
 */
public interface SysPermissionDao {
    /**
     * 新增SysPermission
     * @param sysPermission
     */
    void insert(SysPermission sysPermission);

    /**
     * 根据主键 删除SysPermission
     * @param id
     */
    void delete(java.lang.Long id);

    /**
     * 修改SysPermission
     * @param sysPermission
     */
    void update(SysPermission sysPermission);

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
     * 查询当前菜单下最大的子菜单次序
     * @param parentId
     * @return
     */
    Integer queryMaxSequence(@Param("parentId") Long parentId);

    /**
     * 根据权限ids查询权限
     * @param permissionIds
     * @return
     */
    List<SysPermission> queryByPermissionIds(@Param("permissionIds") Long[] permissionIds);

    /**
     * 根据父id查询权限菜单
     * @param parentId
     * @return
     */
    List<SysPermissionVo> queryPermissionByParentId(@Param("parentId") Long parentId);

    /**
     * 获取多级菜单
     * @param parentId
     * @return sysPermissionVos
     */
    List<SysPermissionVo> listAllPermissionsByParentId(@Param("parentId") Long parentId);

    /**
     * 获取子菜单个数
     * @param parentId
     * @return
     */
    int countChildrenByParentId(@Param("parentId") Long parentId);
}