package com.demo.modules.permission.dao;

import com.demo.core.base.PageBean;
import com.demo.modules.per.model.RoleVo;
import com.demo.modules.permission.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 *  SysRoleDao  接口
 *
 *  @author fdh
 */
public interface SysRoleDao {
    /**
     * 新增SysRole
     * @param sysRole
     */
    void insert(SysRole sysRole);

    /**
     * 根据主键 删除SysRole
     * @param id
     */
    void delete(java.lang.Long id);

    /**
     * 修改SysRole
     * @param sysRole
     */
    void update(SysRole sysRole);

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
     * 获取总条目
     * @param role
     * @return
     */
    Integer countListRoles(@Param("role") RoleVo role);

    /**
     * 获取角色列表
     * @param role
     * @param pageBean
     * @return
     */
    List<SysRole> listRolesByPage(@Param("role")SysRole role, @Param("pageBean") PageBean pageBean);

    /**
     * 批量修改状态
     * @param ids
     * @param status
     * @param userId
     * @param updateDate
     */
    void batchModifyStatus(@Param("ids") Long[] ids, @Param("status") Integer status, @Param("userId") Long userId, @Param("updateDate") Date updateDate);

    /**
     * 批量删除（逻辑删除）
     * @param ids
     * @param userId
     * @param updateDate
     */
    void batchRemove(@Param("ids") Long[] ids,@Param("userId") Long userId, @Param("updateDate") Date updateDate);
}