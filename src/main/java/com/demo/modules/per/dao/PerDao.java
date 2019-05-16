package com.demo.modules.per.dao;

import com.demo.core.base.PageBean;
import com.demo.modules.per.model.RoleVo;
import com.demo.modules.per.model.SysPermissionVo;
import com.demo.modules.per.model.UserVo;
import com.demo.modules.permission.entity.SysPermission;
import com.demo.modules.permission.entity.SysUser;
import com.demo.modules.permission.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PerDao {
    List<Long> queryPermissionByUserName(@Param("username") String username);
    List<SysPermission> queryPermissionByPermissionIds(@Param("permissionIds") List<Long> permissionIds);
    List<UserVo> queryByUsername(@Param("username") String username);
    List<SysPermission> queryPermissionsByPid(Long parentId, Long userId);

    List<RoleVo> queryRolesByUserId(@Param("userId") Long userId);

    /**
     * 获取一级菜单
     * @param parentId
     * @return sysPermissionVos
     */
    List<SysPermissionVo> listPermissionsByParentId(@Param("parentId") Long parentId, @Param("permissions") Long[] permissions);

    /**
     * 获取多级菜单
     * @param parentId
     * @return sysPermissionVos
     */
    List<SysPermissionVo> listAllPermissionsByParentId(@Param("parentId") Long parentId);

    List<SysPermissionVo> queryPermissionByParentId(@Param("parentId") Long parentId);

    List<SysPermission> queryPermissionsByRole(@Param("roles") List<Long> roles);

    Long[] listRolesByUserId(@Param("userId") Long userId);

    Long[] listPermissionByRoleIds(@Param("roles") Long[] roles);

    List<SysPermissionVo> listPermissions(@Param("parentId") Long parentId, @Param("permissions") Long[] permissions);

    String listRoleNames(@Param("roles") Long[] roles);

    Integer countListUsers(@Param("user") UserVo user);
    List<UserVo> listUsersByPage(@Param("user") UserVo user, @Param("pageBean")PageBean pageBean);

    Integer countListRoles(@Param("role") RoleVo role);

    List<RoleVo> listRolesByPage(@Param("role")RoleVo role, @Param("pageBean") PageBean pageBean);

    void deleteUsers(@Param("ids") Long[] ids, @Param("userId") Long userId, @Param("updateDate") Date updateDate);

    void updateUserStatus(@Param("ids") Long[] ids, @Param("status") Integer status, @Param("userId") Long userId, @Param("updateDate") Date updateDate);

    UserVo queryUser(@Param("userId") Long userId);

    void updateRoleStatus(@Param("ids") Long[] ids, @Param("status") Integer status, @Param("userId") Long userId, @Param("updateDate") Date updateDate);

    void batchDelete(@Param("ids") Long[] ids,@Param("userId") Long userId, @Param("updateDate") Date updateDate);

    List<SysUserRole> queryUserRoleByUserIdAndRoleId(@Param("roleId") Long roleId, @Param("userId") Long userId);

    void batchDeleteUserRole(@Param("ids") Long[] ids,@Param("userId") Long userId, @Param("updateDate") Date updateDate);

    /**
     * 删除permission与角色的绑定关系
     * @param permissionId
     */
    void deletePermissionRole(Long permissionId);

    /**
     * 删除角色与权限的绑定关系
     * @param ids
     */
    void deleteRolePermission(Long[] ids);

}