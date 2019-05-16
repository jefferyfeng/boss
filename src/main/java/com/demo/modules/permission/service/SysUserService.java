package com.demo.modules.permission.service;

import com.demo.core.base.PageBean;
import com.demo.modules.per.model.UserVo;
import com.demo.modules.permission.entity.SysUser;
import java.util.List;

/**
 *  SysUserService  接口
 *
 *  @author fdh
 */
public interface SysUserService {
    /**
     * 新增SysUser
     * @param sysUser
     */
    void add(SysUser sysUser);

    /**
     * 根据主键 删除SysUser (逻辑删除)
     * @param id
     */
    void remove(java.lang.Long id);

    /**
     * 根据主键 删除SysUser (物理删除)
     * @param id
     */
    void delete(java.lang.Long id);

    /**
     * 修改SysUser
     * @param sysUser
     */
    void modify(SysUser sysUser);

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
     * 批量删除
     * @param ids
     */
    void batchRemove(Long[] ids);

    /**
     * 批量修改状态
     * @param ids
     * @param status
     */
    void batchModifyStatus(Long[] ids, Integer status);

    /**
     * 重置密码
     * @param userIds 重置密码的用户ids
     */
    void resetPassword(Long[] userIds);

    /**
     * 批量删除用户
     * @param userIds 要删除的用户ids
     */
    void batchRemoveUsers(Long[] userIds);

    /**
     * 添加用户
     * @param sysUser
     */
    void addUser(SysUser sysUser);

    /**
     * 获取用户数据表格 分页
     * @param user 查询条件
     * @param pageBean 分页条件
     * @return
     */
    List<UserVo> listUsersByPage(UserVo user, PageBean pageBean);

    /**
     * 修改密码
     * @param userId 用户id
     * @param oldPwd 原始密码
     * @param newPwd 新密码
     */
    void changePwd(Long userId, String oldPwd, String newPwd);

    /**
     * 查询用户
     * @param userId
     * @return
     */
    UserVo queryUser(Long userId);
}