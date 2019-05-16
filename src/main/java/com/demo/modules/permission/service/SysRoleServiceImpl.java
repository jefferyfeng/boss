package com.demo.modules.permission.service;

import com.demo.core.base.PageBean;
import com.demo.core.util.MyUtil;
import com.demo.modules.per.model.RoleVo;
import com.demo.modules.per.model.UserVo;
import com.demo.modules.permission.dao.SysRolePermissionDao;
import com.demo.modules.permission.dao.SysUserRoleDao;
import com.demo.modules.permission.entity.SysRole;
import com.demo.modules.permission.dao.SysRoleDao;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Date;

/**
 *  SysRoleServiceImpl  实现类
 *
 *  @author fdh
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
public class SysRoleServiceImpl implements SysRoleService{
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    /**
     * 新增SysRole
     * @param sysRole
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void add(SysRole sysRole){
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute("user");
        Date date = new Date();
        sysRole.setCreateDate(date);
        sysRole.setUpdateDate(date);
        sysRole.setCreateUser(userVo.getId());
        sysRole.setUpdateUser(userVo.getId());
        sysRole.setIsValid(1);
        sysRoleDao.insert(sysRole);
    }

    /**
     * 根据主键 删除SysRole (逻辑删除)
     * @param sysRole
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void remove(SysRole sysRole){
        //sysRoleDao.delete(sysRole.getId());
        sysRole.setIsValid(0);
        sysRoleDao.update(sysRole);
    }

    /**
     * 修改SysRole
     * @param sysRole
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void modify(SysRole sysRole){
        UserVo user = (UserVo) MyUtil.getSession().getAttribute("user");
        Date date = new Date();
        sysRole.setUpdateDate(date);
        sysRole.setUpdateUser(user.getId());
        sysRoleDao.update(sysRole);
    }

    /**
     * 根据主键查询SysRole
     * @param id
     * @return sysRole
     */
    @Override
    public SysRole queryOne(java.lang.Long id){
        return sysRoleDao.queryOne(id);
    }

    /**
     * 根据主键查询SysRole
     * @return sysRoles
     */
    @Override
    public List<SysRole> queryAll(){
        return sysRoleDao.queryAll();
    }

    /**
     * 根据字段查询（如需分页请setPageBean）
     * @param sysRole
     * @return sysRoles
     */
    @Override
    public List<SysRole> queryByFieldsAndPage(SysRole sysRole){
        return sysRoleDao.queryByFieldsAndPage(sysRole);
    }

    /**
     * 获取角色列表
     * @param role 角色查询条件
     * @param pageBean 分页条件
     * @return
     */
    @Override
    public List<RoleVo> listRolesByPage(RoleVo role, PageBean pageBean) {
        pageBean.setAll(sysRoleDao.countListRoles(role));
        return sysRoleDao.listRolesByPage(role,pageBean);
    }

    /**
     * 批量修改状态
     * @param ids 修改的ids
     * @param status 修改的状态
     */
    @Override
    public void batchModifyStatus(Long[] ids, Integer status) {
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute("user");
        sysRoleDao.batchModifyStatus(ids,status,userVo.getId(),new Date());
    }

    @Override
    public void batchRemove(Long[] ids) {
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute("user");
        sysRoleDao.batchRemove(ids,userVo.getId(),new Date());
    }

    /**
     * 批量删除角色（逻辑删除）
     * @param ids
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED, readOnly = false)
    public void batchRemoveRoles(Long[] ids) {
        SysRoleService sysRoleService = (SysRoleService) AopContext.currentProxy();
        //删除角色
        sysRoleService.batchRemove(ids);
        //删除角色与用户的绑定关系
        sysUserRoleService.batchRemoveUserRoles(null,ids);
        //删除角色与菜单的绑定关系
        sysRolePermissionService.batchRemoveRolePermission(ids,null);
    }
}