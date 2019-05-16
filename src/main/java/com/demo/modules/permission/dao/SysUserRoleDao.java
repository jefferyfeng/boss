package com.demo.modules.permission.dao;

import com.demo.modules.permission.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 *  SysUserRoleDao  接口
 *
 *  @author fdh
 */
public interface SysUserRoleDao {
    /**
     * 新增SysUserRole
     * @param sysUserRole
     */
    void insert(SysUserRole sysUserRole);

    /**
     * 根据主键 删除SysUserRole
     * @param id
     */
    void delete(java.lang.Long id);

    /**
     * 修改SysUserRole
     * @param sysUserRole
     */
    void update(SysUserRole sysUserRole);

    /**
     * 根据主键查询SysUserRole
     * @param id
     * @return sysUserRole
     */
    SysUserRole queryOne(java.lang.Long id);

    /**
     * 根据主键查询SysUserRole
     * @return sysUserRoles
     */
    List<SysUserRole> queryAll();

    /**
     * 根据字段查询（如需分页请setPageBean）
     * @param sysUserRole
     * @return sysUserRoles
     */
    List<SysUserRole> queryByFieldsAndPage(SysUserRole sysUserRole);

    /**
     * 根据用户id获取角色ids
     * @param userId 用户id
     * @return 角色id的数组
     */
    Long[] listRolesByUserId(Long userId);

    /**
     * 根据角色id和用户的id查询sysUserRole(逻辑有效无效都查)
     * @param roleId
     * @param userId
     * @return
     */
    List<SysUserRole> listUserRoleByUserIdAndRoleId(@Param("roleId") Long roleId, @Param("userId") Long userId);

    /**
     * 根据用户ids删除用户角色的关联关系
     * @param userIds
     * @param updateUser
     * @param updateDate
     */
    void batchRemoveUserRoles(@Param("userIds")Long[] userIds, @Param("roleIds")Long[] roleIds, @Param("updateUser")Long updateUser, @Param("updateDate")Date updateDate);
}