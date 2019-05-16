package com.demo.modules.permission.service;

import com.demo.core.base.PageBean;
import com.demo.modules.per.model.RoleVo;
import com.demo.modules.permission.entity.SysRole;
import java.util.List;

/**
 *  SysRoleService  接口
 *
 *  @author fdh
 */
public interface SysRoleService {
    /**
     * 新增SysRole
     * @param sysRole
     */
    void add(SysRole sysRole);

    /**
     * 根据主键 删除SysRole
     * @param sysRole
     */
    void remove(SysRole sysRole);

    /**
     * 修改SysRole
     * @param sysRole
     */
    void modify(SysRole sysRole);

    /**
     * 根据主键查询SysRole
     * @param id
     * @return sysRole
     */
    SysRole queryOne(java.lang.Long id);

    /**
     * 根据主键查询SysRole
     * @return sysRoles
     */
    List<SysRole> queryAll();

    /**
     * 根据字段查询（如需分页请setPageBean）
     * @param sysRole
     * @return sysRoles
     */
    List<SysRole> queryByFieldsAndPage(SysRole sysRole);

    /**
     * 获取角色列表
     * @param role 角色查询条件
     * @param pageBean 分页条件
     * @return
     */
    List<RoleVo> listRolesByPage(RoleVo role, PageBean pageBean);

    /**
     * 批量修改状态
     * @param ids 修改的ids
     * @param status 修改的状态
     */
    void batchModifyStatus(Long[] ids, Integer status);

    /**
     * 批量删除
     * @param ids
     */
    void batchRemove(Long[] ids);

    /**
     * 批量删除角色（逻辑删除）
     * @param ids
     */
    void batchRemoveRoles(Long[] ids);
}