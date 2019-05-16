package com.demo.modules.per.service;

import com.demo.core.base.PageBean;
import com.demo.core.util.CodeUtil;
import com.demo.core.util.MyUtil;
import com.demo.modules.per.dao.PerDao;
import com.demo.modules.per.exception.UserException;
import com.demo.modules.per.model.*;
import com.demo.modules.permission.dao.*;
import com.demo.modules.permission.entity.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PerServiceImpl implements PerService {

    @Autowired
    private PerDao perDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    @Autowired
    private SysPermissionDao sysPermissionDao;
    @Autowired
    private SysRolePermissionDao sysRolePermissionDao;

    @Override
    public List<SysPermission> queryPermissionByUserName(String username) {
        List<Long> permissionIds = perDao.queryPermissionByUserName(username);
        return perDao.queryPermissionByPermissionIds(permissionIds);
    }

    @Override
    public SysUser queryByUsername(String username) {
        if(StringUtils.isEmpty(username)) return null;
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        List<SysUser> sysUsers = sysUserDao.queryByFieldsAndPage(sysUser);
        if(sysUsers == null || sysUsers.isEmpty()){
            return null;
        }
        return sysUsers.get(0);
    }

    @Override
    public UserVo login(SysUser sysUser) {
        if(StringUtils.isEmpty(sysUser.getUsername())){
            throw new UserException("用户名为空");
        }
        List<UserVo> users = perDao.queryByUsername(sysUser.getUsername());
        if(MyUtil.isEmpty(users)){
            throw new UserException("用户名不存在");
        }
        UserVo userVo = users.get(0);
        String medPwd = DigestUtils.md5Hex(sysUser.getPassword() + userVo.getSalt());
        if(!userVo.getPassword().equals(medPwd)){
            throw new UserException("密码错误");
        }

        //获取用户角色,权限
        Long[] roles = perDao.listRolesByUserId(userVo.getId());
        Long[] permissions = perDao.listPermissionByRoleIds(roles);
        userVo.setRoles(roles);
        userVo.setPermissions(permissions);

        return userVo;
    }

    @Override
    public List<SysPermission> queryPermissionsByRole(List<Long> roles) {
        return perDao.queryPermissionsByRole(roles);
    }

    @Override
    public List<SysPermissionVo> queryAllPermissionsByParentId(Long parentId) {
        return perDao.listAllPermissionsByParentId(parentId);
    }

    @Override
    public List<SysPermissionVo> queryPermissionByParentId(Long parentId) {
        return perDao.queryPermissionByParentId(parentId);
    }

    /*@Override
    public List<SysPermission> queryPermissionsByPid(Long parentId, Long userId) {
        return perDao.queryPermissionsByPid(parentId,userId);
    }*/

    @Override
    public List<RoleVo> queryRolesByUserId(Long userId) {
        return perDao.queryRolesByUserId(userId);
    }

    @Override
    public List<SysPermissionVo> listPermissions(Long parentId,Long[] permissions) {
        return perDao.listPermissions(parentId,permissions);
    }

    @Override
    public List<LayuiNav> listNavsByParentId(Long parentId, Long[] permissions) {
        List<SysPermissionVo> sysPermissionVos = perDao.listPermissionsByParentId(parentId, permissions);
        return getLayuiNavs(sysPermissionVos, Arrays.asList(permissions));
    }

    @Override
    public List<LayuiNav> listAllNavsByParentId(Long parentId,Long[] permissions) {
        List<SysPermissionVo> sysPermissionVos = perDao.listAllPermissionsByParentId(parentId);
        return getLayuiNavs(sysPermissionVos, Arrays.asList(permissions));
    }

    @Override
    public List<UserVo> listUsersByPage(UserVo user, PageBean pageBean) {
        //预先获取总条数
        pageBean.setAll(perDao.countListUsers(user));
        return perDao.listUsersByPage(user,pageBean);
    }

    @Override
    public List<RoleVo> listRolesByPage(RoleVo role, PageBean pageBean) {
        pageBean.setAll(perDao.countListRoles(role));
        return perDao.listRolesByPage(role,pageBean);
    }

    @Override
    public Long addUser(SysUser user) {
        String salt = CodeUtil.buildCode();
        String password = DigestUtils.md5Hex(user.getPassword() + salt);
        Date date = new Date();
        user.setSalt(salt);
        user.setPassword(password);
        user.setCreateDate(date);
        user.setUpdateDate(date);
        user.setIsValid(1);
        sysUserDao.insert(user);
        return user.getId();
    }

    @Override
    public void resetPassword(Long[] ids) {
        if(ids.length>0){
            UserVo user = (UserVo) MyUtil.getSession().getAttribute("user");
            for (Long id : ids) {
                SysUser sysUser = new SysUser();
                String salt = CodeUtil.buildCode();//重置盐值
                String password = DigestUtils.md5Hex("123456" + salt);
                sysUser.setId(id);
                sysUser.setSalt(salt);
                sysUser.setPassword(password);
                sysUser.setUpdateDate(new Date());
                sysUser.setUpdateUser(user.getId());
                sysUserDao.update(sysUser);
            }
        }
    }

    @Override
    public void deleteUsers(Long[] ids) {
        UserVo user = (UserVo) MyUtil.getSession().getAttribute("user");
        perDao.deleteUsers(ids,user.getId(),new Date());
    }

    @Override
    public void updateUserStatus(Long[] ids, Integer status) {
        UserVo user = (UserVo) MyUtil.getSession().getAttribute("user");
        perDao.updateUserStatus(ids,status,user.getId(),new Date());
    }

    @Override
    public UserVo queryUser(Long userId) {
        return perDao.queryUser(userId);
    }

    @Override
    public void editUser(SysUser user) {
        sysUserDao.update(user);
    }

    @Override
    public void addRole(SysRole role) {
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute("user");
        Date date = new Date();
        role.setCreateUser(userVo.getId());
        role.setUpdateUser(userVo.getId());
        role.setCreateDate(date);
        role.setUpdateDate(date);
        role.setIsValid(1);
        sysRoleDao.insert(role);
    }

    @Override
    public void updateRoleStatus(Long[] ids, Integer status) {
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute("user");
        perDao.updateRoleStatus(ids,status,userVo.getId(),new Date());
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED, readOnly = false)
    public void batchDelete(Long[] ids) {
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute("user");
        //删除角色
        perDao.batchDelete(ids,userVo.getId(),new Date());
        //删除角色与用户的绑定关系
        perDao.batchDeleteUserRole(ids,userVo.getId(),new Date());
        //删除角色与菜单的绑定关系
        perDao.deleteRolePermission(ids);
    }

    @Override
    @Transactional(propagation= Propagation.SUPPORTS,isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
    public Object listUserRolesByPage(RoleVo role, PageBean pageBean, Long userId) {
        PerService perService = (PerService) AopContext.currentProxy();
        List<RoleVo> roleVoList = perService.listRolesByPage(role, pageBean);
        List<Map<String,Object>> list = new ArrayList<>();

        //获取当前用户的角色信息
        Long[] roleArr = perDao.listRolesByUserId(userId);

        //如果当前用户无角色配置 直接返回
        if(MyUtil.isEmpty(roleArr)) return roleVoList;

        //如果有角色配置 设置默认选中
        List<Long> roles = Arrays.asList(roleArr);
        for (RoleVo roleVo : roleVoList) {
            Map<String,Object> map = new HashMap<>(5);
            map.put("id",roleVo.getId());
            map.put("roleName",roleVo.getRoleName());
            map.put("description",roleVo.getDescription());
            map.put("status",roleVo.getStatus());
            if(roles.contains(roleVo.getId())){
                map.put("LAY_CHECKED", true);
            }else{
                map.put("LAY_CHECKED", false);
            }
            list.add(map);
        }
        return list;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED, readOnly = false)
    public void editUserRole(String checkedIds, String uncheckedIds, Long userId) {
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute("user");
        Date date = new Date();

        //用户原始的角色id
        Long[] roleIds = perDao.listRolesByUserId(userId);
        List<Long> roleList = Arrays.asList(roleIds);

        //如果已经配置过角色
        if(MyUtil.isNotEmpty(roleList) && MyUtil.isNotEmpty(uncheckedIds)){
            //看此时未选角色中有没有之前配置过的角色 有则删除 没有则不需要处理
            String[] uncheckArr = uncheckedIds.split(",");
            for (String uncheckedIdStr : uncheckArr) {
                Long uncheckedId = Long.valueOf(uncheckedIdStr);
                //如果存在就删除
                if (roleList.contains(uncheckedId)) {
                    List<SysUserRole> sysUserRoles = perDao.queryUserRoleByUserIdAndRoleId(uncheckedId, userId);
                    SysUserRole sysUserRole = sysUserRoles.get(0);
                    SysUserRole usr = new SysUserRole();
                    usr.setId(sysUserRole.getId());
                    usr.setIsValid(0);
                    usr.setUpdateDate(date);
                    usr.setUpdateUser(userVo.getId());
                    sysUserRoleDao.update(usr);
                }
            }
        }

        //已选状态
        if(MyUtil.isNotEmpty(checkedIds)) {
            String[] checkArr = checkedIds.split(",");
            for (String checkedIdStr : checkArr) {
                Long checkedId = Long.valueOf(checkedIdStr);
                if (!roleList.contains(checkedId)) {
                    List<SysUserRole> sysUserRoles = perDao.queryUserRoleByUserIdAndRoleId(checkedId, userId);
                    if (MyUtil.isEmpty(sysUserRoles)) {//如果不存在插入
                        SysUserRole sysUserRole = new SysUserRole();
                        sysUserRole.setUserId(userId);
                        sysUserRole.setRoleId(checkedId);
                        sysUserRole.setIsValid(1);
                        sysUserRole.setCreateDate(date);
                        sysUserRole.setCreateUser(userVo.getId());
                        sysUserRole.setUpdateDate(date);
                        sysUserRole.setUpdateUser(userVo.getId());
                        sysUserRoleDao.insert(sysUserRole);
                    } else {//反之存在即更新
                        SysUserRole sysUserRole = sysUserRoles.get(0);
                        SysUserRole usr = new SysUserRole();
                        usr.setId(sysUserRole.getId());
                        usr.setIsValid(1);
                        usr.setUpdateDate(date);
                        usr.setUpdateUser(userVo.getId());
                        sysUserRoleDao.update(usr);
                    }
                }
            }
        }
    }

    @Override
    public List<ZTree> queryZTreePermissions(Long parentId) {
        List<SysPermissionVo> permissions = perDao.queryPermissionByParentId(parentId);
        List<ZTree> zTrees = new ArrayList<>();
        for (SysPermissionVo sysPermission : permissions) {
            ZTree zTree = new ZTree();
            zTree.setId(String.valueOf(sysPermission.getId()));
            zTree.setName(String.valueOf(sysPermission.getPermissionName()));
            zTree.setpId(String.valueOf(sysPermission.getParentId()));
            zTree.setChecked("false");
            zTree.setChkDisabled("false");
            zTree.setOpen("false");
            if(MyUtil.isNotEmpty(sysPermission.getChildren())){
                zTree.setIsParent("true");
            }else{
                zTree.setIsParent("false");
            }
            zTrees.add(zTree);
        }
        return zTrees;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED, readOnly = false)
    public void deletePermission(Long permissionId) {
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute("user");
        Date date = new Date();

        //删除权限菜单
        SysPermission sysPermission = new SysPermission();
        sysPermission.setId(permissionId);
        sysPermission.setIsValid(0);
        sysPermission.setUpdateDate(date);
        sysPermission.setUpdateUser(userVo.getId());
        sysPermissionDao.update(sysPermission);

        //删除菜单与角色的绑定关系
        perDao.deletePermissionRole(permissionId);
    }

    @Override
    public void addPermission(SysPermission sysPermission) {
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute("user");
        Date date = new Date();

        sysPermission.setCreateDate(date);
        sysPermission.setUpdateDate(date);
        sysPermission.setIsValid(1);
        sysPermission.setCreateUser(userVo.getId());
        sysPermission.setCreateUser(userVo.getId());

        sysPermissionDao.insert(sysPermission);
    }

    @Override
    public void editPermission(SysPermission sysPermission) {
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute("user");
        Date date = new Date();

        sysPermission.setUpdateUser(userVo.getId());
        sysPermission.setUpdateDate(date);
        sysPermissionDao.update(sysPermission);
    }

    @Override
    public List<ZTree> queryZTreeAllPermissions(Long roleId) {
        //当前所有的权限菜单
        List<SysPermissionVo> permissionVos = perDao.listAllPermissionsByParentId(0L);
        Long[] roles = {roleId};
        Long[] permissions = perDao.listPermissionByRoleIds(roles);

        return formatZtree(permissionVos,Arrays.asList(permissions));
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED, readOnly = false)
    public void editRolePermission(String checkedPermissionIds, String uncheckedPermissionIds, Long roleId) {
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute("user");
        Date date = new Date();

        //先获取到当前要修改角色下的所有权限菜单
        Long[] roles = {roleId};
        Long[] permissions = perDao.listPermissionByRoleIds(roles);
        List<Long> permissionList = Arrays.asList(permissions);

        //如果已经配置过权限菜单
        if(MyUtil.isNotEmpty(permissionList) && MyUtil.isNotEmpty(uncheckedPermissionIds)){
            //看此时未选权限中有没有之前配置过的权限 有则删除 没有则不需要处理
            String[] uncheckArr = uncheckedPermissionIds.split(",");
            for (String permissionIdStr : uncheckArr) {
                Long permissionId = Long.valueOf(permissionIdStr);
                if(permissionList.contains(permissionId)){
                    //这里为了避免数据库产生大量无效数据，采用之前逻辑删除的数据
                    List<SysRolePermission> sysRolePermissionList = sysRolePermissionDao.queryByRoleAndPermission(Long.valueOf(permissionId), roleId);
                    SysRolePermission sysRolePermission = new SysRolePermission();
                    sysRolePermission.setId(sysRolePermissionList.get(0).getId());
                    sysRolePermission.setIsValid(0);
                    sysRolePermission.setUpdateUser(userVo.getId());
                    sysRolePermission.setUpdateDate(date);
                    sysRolePermissionDao.update(sysRolePermission);
                }
            }

        }

        //处理已选
        if(MyUtil.isNotEmpty(checkedPermissionIds)){
            String[] checkArr = checkedPermissionIds.split(",");
            for (String permissionIdStr : checkArr) {
                Long permissionId = Long.valueOf(permissionIdStr);
                if(!permissionList.contains(permissionId)){
                    //这里为了避免数据库产生大量无效数据，采用之前逻辑删除的数据
                    List<SysRolePermission> sysRolePermissionList = sysRolePermissionDao.queryByRoleAndPermission(Long.valueOf(permissionId), roleId);
                    //如果不存在删除的数据
                    if(MyUtil.isEmpty(sysRolePermissionList)){
                        SysRolePermission sysRolePermission = new SysRolePermission();
                        sysRolePermission.setPermissionId(Long.valueOf(permissionId));
                        sysRolePermission.setRoleId(roleId);
                        sysRolePermission.setIsValid(1);
                        sysRolePermission.setCreateUser(userVo.getId());
                        sysRolePermission.setCreateDate(date);
                        sysRolePermission.setUpdateUser(userVo.getId());
                        sysRolePermission.setUpdateDate(date);
                        sysRolePermissionDao.insert(sysRolePermission);
                    }else{//如果存在则置为有效
                        SysRolePermission sysRolePermission = new SysRolePermission();
                        sysRolePermission.setId(sysRolePermissionList.get(0).getId());
                        sysRolePermission.setIsValid(1);
                        sysRolePermission.setUpdateUser(userVo.getId());
                        sysRolePermission.setUpdateDate(date);
                        sysRolePermissionDao.update(sysRolePermission);
                    }
                }
            }
        }
    }


    /**
     * 配置为ztree的权限菜单
     *
     * @param permissionVos 数据库查询的菜单
     * @param permissions 用户所拥有的权限id
     * @return ztreeList ZTree对象集合的权限菜单
     */
    private List<ZTree> formatZtree(List<SysPermissionVo> permissionVos, List<Long> permissions){
        if(MyUtil.isEmpty(permissionVos)) return null;
        List<ZTree> zTreeList = new ArrayList<>();
        for (SysPermissionVo permissionVo : permissionVos) {
            ZTree zTree = new ZTree();
            //菜单id
            zTree.setId(String.valueOf(permissionVo.getId()));
            //菜单名称
            zTree.setName(String.valueOf(permissionVo.getPermissionName()));
            //菜单父id
            zTree.setpId(String.valueOf(permissionVo.getParentId()));
            //菜单选中 默认不选中
            if(permissions.contains(permissionVo.getId())){
                zTree.setChecked("true");
            }else{
                zTree.setChecked("false");
            }
            zTree.setChkDisabled("false");
            zTree.setOpen("true");
            if(MyUtil.isNotEmpty(permissionVo.getChildren())){
                zTree.setIsParent("true");
            }else{
                zTree.setIsParent("false");
            }
            if(MyUtil.isNotEmpty(permissionVo.getChildren())){
                zTree.setChildren(formatZtree(permissionVo.getChildren(),permissions));
            }
            zTreeList.add(zTree);
        }
        return zTreeList;
    }



    /**
     * 配置为layui的权限菜单
     *
     * @param permissionVos 数据库查询的菜单
     * @param permissions 用户所拥有的权限id
     * @return layuiNavList LayuiNav对象集合的权限菜单
     */
    private List<LayuiNav> getLayuiNavs(List<SysPermissionVo> permissionVos, List<Long> permissions){
        if(MyUtil.isEmpty(permissionVos)) return null;
        List<LayuiNav> layuiNavs = new ArrayList<>();
        for (SysPermissionVo permissionVo : permissionVos) {
            if(!permissions.contains(permissionVo.getId())){
                continue;
            }
            LayuiNav layuiNav = new LayuiNav();
            //菜单id
            layuiNav.setId(permissionVo.getId());
            //菜单名称
            layuiNav.setTitle(permissionVo.getPermissionName());
            //菜单图标
            layuiNav.setIcon(permissionVo.getPermissionIcon());
            //菜单url
            layuiNav.setHref(permissionVo.getPermissionUrl());
            //菜单选中 默认不选中
            layuiNav.setSpread(false);
            if(MyUtil.isNotEmpty(permissionVo.getChildren())){
                layuiNav.setChildren(getLayuiNavs(permissionVo.getChildren(),permissions));
            }
            layuiNavs.add(layuiNav);
        }
        return layuiNavs;
    }
}
