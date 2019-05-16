package com.demo.modules.permission.service;

import com.demo.core.base.PageBean;
import com.demo.modules.per.model.RoleVo;
import com.demo.modules.permission.entity.SysUserRole;
import java.util.List;

/**
 *  SysUserRoleService  接口
 *
 *  @author fdh
 */
public interface SysUserRoleService {
    /**
     * 新增SysUserRole
     * @param sysUserRole
     */
    void add(SysUserRole sysUserRole);

    /**
     * 根据主键 删除SysUserRole
     * @param id
     */
    void remove(java.lang.Long id);

    /**
     * 修改SysUserRole
     * @param sysUserRole
     */
    void modify(SysUserRole sysUserRole);

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
     * 批量删除
     * @param userIds
     */
    void batchRemove(Long[] userIds);

    /**
     * 根据用户ids删除用户角色的绑定关系
     * @param userIds
     */
    void batchRemoveUserRoles(Long[] userIds,Long[] roleIds);

    /**
     * 根据用户id查询角色
     * @param userId 用户id
     * @return rolesArr 角色id的数组
     */
    Long[] listRolesByUserId(Long userId);

    /**
     * 根据角色id和用户的id查询sysUserRole(逻辑有效无效都查)
     * @param roleId
     * @param userId
     * @return
     */
    List<SysUserRole> listUserRoleByUserIdAndRoleId(Long roleId, Long userId);

    /**
     * 获取用户角色配置列表
     * @param pageBean
     * @param userId
     * @return
     */
    Object listUserRolesByPage(PageBean pageBean, Long userId);

    /**
     * 修改用户的角色
     * @param checkedIds 选中的角色ids
     * @param uncheckedIds 未选中的角色ids
     * @param userId 用户的id
     */
    void editUserRole(String checkedIds, String uncheckedIds, Long userId);

}