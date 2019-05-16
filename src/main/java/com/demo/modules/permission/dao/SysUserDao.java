package com.demo.modules.permission.dao;

import com.demo.core.base.PageBean;
import com.demo.modules.per.model.UserVo;
import com.demo.modules.permission.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 *  SysUserDao  接口
 *
 *  @author fdh
 */
public interface SysUserDao {
    /**
     * 新增SysUser
     * @param sysUser
     */
    void insert(SysUser sysUser);

    /**
     * 根据主键 删除SysUser (物理删除)
     * @param id
     */
    void delete(java.lang.Long id);

    /**
     * 修改SysUser
     * @param sysUser
     */
    void update(SysUser sysUser);

    /**
     * 根据主键查询SysUser
     * @param id
     * @return sysUser
     */
    SysUser queryOne(java.lang.Long id);

    /**
     * 根据主键查询SysUser
     * @return sysUsers
     */
    List<SysUser> queryAll();

    /**
     * 根据字段查询（如需分页请setPageBean）
     * @param sysUser
     * @return sysUsers
     */
    List<SysUser> queryByFieldsAndPage(SysUser sysUser);

    /**
     * 批量修改状态
     * @param ids 操作的ids
     * @param status 操作的状态
     * @param userId 操作的用户id
     * @param updateDate 操作时间
     */
    void batchModifyStatus(@Param("ids") Long[] ids, @Param("status") Integer status, @Param("userId") Long userId, @Param("updateDate") Date updateDate);

    /**
     * 批量删除（逻辑删除）
     * @param ids 操作的ids
     * @param isValid 是否逻辑删除
     * @param userId 操作的用户id
     * @param updateDate 操作时间
     */
    void batchRemove(@Param("ids") Long[] ids, @Param("isValid") Integer isValid, @Param("userId") Long userId, @Param("updateDate") Date updateDate);

    /**
     * 查询总条数
     * @param user
     * @return
     */
    Integer countListUsers(@Param("user") UserVo user);

    /**
     * 根据条件查询数据表格
     * @param user
     * @param pageBean
     * @return
     */
    List<UserVo> listUsersByPage(@Param("user") UserVo user, @Param("pageBean") PageBean pageBean);

    /**
     * 查询
     * @param userId
     * @return
     */
    UserVo queryUser(Long userId);
}