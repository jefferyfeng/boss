package com.demo.modules.permission.service;

import com.demo.core.base.PageBean;
import com.demo.core.constants.Constants;
import com.demo.core.util.MyUtil;
import com.demo.modules.per.model.RoleVo;
import com.demo.modules.per.model.UserVo;
import com.demo.modules.per.service.PerService;
import com.demo.modules.permission.entity.SysRole;
import com.demo.modules.permission.entity.SysUserRole;
import com.demo.modules.permission.dao.SysUserRoleDao;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 *  SysUserRoleServiceImpl  实现类
 *
 *  @author fdh
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
public class SysUserRoleServiceImpl implements SysUserRoleService{
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 新增SysUserRole
     * @param sysUserRole
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void add(SysUserRole sysUserRole){
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute(Constants.SESSIOIN_USER);
        Date date = new Date();

        sysUserRole.setCreateDate(date);
        sysUserRole.setCreateUser(userVo.getId());
        sysUserRole.setUpdateDate(date);
        sysUserRole.setUpdateUser(userVo.getId());
        sysUserRole.setIsValid(1);
        sysUserRoleDao.insert(sysUserRole);
    }

    /**
     * 根据主键 删除SysUserRole (逻辑删除)
     * @param id
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void remove(java.lang.Long id){
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute(Constants.SESSIOIN_USER);

        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setId(id);
        sysUserRole.setUpdateDate(new Date());
        sysUserRole.setUpdateUser(userVo.getId());
        sysUserRole.setIsValid(0);
        sysUserRoleDao.update(sysUserRole);
    }

    /**
     * 修改SysUserRole
     * @param sysUserRole
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void modify(SysUserRole sysUserRole){
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute(Constants.SESSIOIN_USER);

        sysUserRole.setUpdateUser(userVo.getId());
        sysUserRole.setUpdateDate(new Date());
        sysUserRoleDao.update(sysUserRole);
    }

    /**
     * 根据主键查询SysUserRole
     * @param id
     * @return sysUserRole
     */
    @Override
    public SysUserRole queryOne(java.lang.Long id){
        return sysUserRoleDao.queryOne(id);
    }

    /**
     * 根据主键查询SysUserRole
     * @return sysUserRoles
     */
    @Override
    public List<SysUserRole> queryAll(){
        return sysUserRoleDao.queryAll();
    }

    /**
     * 根据字段查询（如需分页请setPageBean）
     * @param sysUserRole
     * @return sysUserRoles
     */
    @Override
    public List<SysUserRole> queryByFieldsAndPage(SysUserRole sysUserRole){
        return sysUserRoleDao.queryByFieldsAndPage(sysUserRole);
    }

    /**
     * 批量删除
     * @param userIds
     */
    @Override
    public void batchRemove(Long[] userIds) {

    }

    /**
     * 根据用户ids删除用户角色的绑定关系
     * @param userIds
     */
    @Override
    public void batchRemoveUserRoles(Long[] userIds,Long[] roleIds) {
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute(Constants.SESSIOIN_USER);

        sysUserRoleDao.batchRemoveUserRoles(userIds, roleIds, userVo.getId(), new Date());
    }

    /**
     * 根据用户id查询角色
     * @param userId 用户id
     * @return rolesArr 角色id的数组
     */
    @Override
    public Long[] listRolesByUserId(Long userId) {
        return sysUserRoleDao.listRolesByUserId(userId);
    }

    /**
     * 根据角色id和用户的id查询sysUserRole(逻辑有效无效都查)
     * @param roleId
     * @param userId
     * @return
     */
    @Override
    public List<SysUserRole> listUserRoleByUserIdAndRoleId(Long roleId, Long userId) {
        return sysUserRoleDao.listUserRoleByUserIdAndRoleId(roleId, userId);
    }

    /**
     * 获取用户角色配置列表
     * @param pageBean
     * @param userId
     * @return
     */
    @Override
    public Object listUserRolesByPage(PageBean pageBean, Long userId) {
        SysRole sysRole = new SysRole();
        sysRole.setPageBean(pageBean);
        List<SysRole> roleList = sysRoleService.queryByFieldsAndPage(sysRole);
        List<Map<String,Object>> list = new ArrayList<>();

        //获取当前用户的角色信息
        Long[] roleArr = sysUserRoleService.listRolesByUserId(userId);

        //如果当前用户无角色配置 直接返回
        if(MyUtil.isEmpty(roleArr)) return roleList;

        //如果有角色配置 设置默认选中
        List<Long> roles = Arrays.asList(roleArr);
        for (SysRole role : roleList) {
            Map<String,Object> map = new HashMap<>(5);
            map.put("id",role.getId());
            map.put("roleName",role.getRoleName());
            map.put("description",role.getDescription());
            map.put("status",role.getStatus());
            if(roles.contains(role.getId())){
                map.put("LAY_CHECKED", true);
            }else{
                map.put("LAY_CHECKED", false);
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 修改用户的角色
     * @param checkedIds 选中的角色ids
     * @param uncheckedIds 未选中的角色ids
     * @param userId 用户的id
     */
    @Override
    public void editUserRole(String checkedIds, String uncheckedIds, Long userId) {
        SysUserRoleService sysUserRoleService = (SysUserRoleService) AopContext.currentProxy();

        //用户原始的角色id
        Long[] roleIds = sysUserRoleService.listRolesByUserId(userId);
        List<Long> roleList = Arrays.asList(roleIds);

        //如果已经配置过角色
        if(MyUtil.isNotEmpty(roleList) && MyUtil.isNotEmpty(uncheckedIds)){
            //看此时未选角色中有没有之前配置过的角色 有则删除 没有则不需要处理
            String[] uncheckArr = uncheckedIds.split(",");
            for (String uncheckedIdStr : uncheckArr) {
                Long uncheckedId = Long.valueOf(uncheckedIdStr);
                //如果存在就删除
                if (roleList.contains(uncheckedId)) {
                    List<SysUserRole> sysUserRoles = sysUserRoleService.listUserRoleByUserIdAndRoleId(uncheckedId, userId);
                    SysUserRole sysUserRole = sysUserRoles.get(0);
                    sysUserRoleService.remove(sysUserRole.getId());
                }
            }
        }

        //已选状态
        if(MyUtil.isNotEmpty(checkedIds)) {
            String[] checkArr = checkedIds.split(",");
            for (String checkedIdStr : checkArr) {
                Long checkedId = Long.valueOf(checkedIdStr);
                if (!roleList.contains(checkedId)) {
                    List<SysUserRole> sysUserRoles = sysUserRoleService.listUserRoleByUserIdAndRoleId(checkedId, userId);
                    if (MyUtil.isEmpty(sysUserRoles)) {//如果不存在插入
                        SysUserRole sysUserRole = new SysUserRole();
                        sysUserRole.setUserId(userId);
                        sysUserRole.setRoleId(checkedId);
                        sysUserRoleService.add(sysUserRole);
                    } else {//反之存在即更新
                        SysUserRole sysUserRole = sysUserRoles.get(0);
                        SysUserRole usr = new SysUserRole();
                        usr.setId(sysUserRole.getId());
                        usr.setIsValid(1);
                        sysUserRoleService.modify(usr);
                    }
                }
            }
        }
    }
}