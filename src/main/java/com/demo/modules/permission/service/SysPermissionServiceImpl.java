package com.demo.modules.permission.service;

import com.demo.core.util.MyUtil;
import com.demo.modules.per.model.LayuiNav;
import com.demo.modules.per.model.SysPermissionVo;
import com.demo.modules.per.model.UserVo;
import com.demo.modules.per.model.ZTree;
import com.demo.modules.permission.entity.SysPermission;
import com.demo.modules.permission.dao.SysPermissionDao;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

/**
 *  SysPermissionServiceImpl  实现类
 *
 *  @author fdh
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
public class SysPermissionServiceImpl implements SysPermissionService{
    @Autowired
    private SysPermissionDao sysPermissionDao;
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    /**
     * 新增SysPermission
     * @param sysPermission
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void add(SysPermission sysPermission){
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute("user");
        Date date = new Date();

        sysPermission.setCreateDate(date);
        sysPermission.setUpdateDate(date);
        sysPermission.setCreateUser(userVo.getId());
        sysPermission.setCreateUser(userVo.getId());
        sysPermission.setIsValid(1);

        sysPermissionDao.insert(sysPermission);
    }

    /**
     * 根据主键 删除SysPermission (逻辑删除)
     * @param permissionId
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void remove(Long permissionId){
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute("user");
        SysPermission sysPermission = new SysPermission();
        sysPermission.setId(permissionId);
        sysPermission.setUpdateDate(new Date());
        sysPermission.setUpdateUser(userVo.getId());
        sysPermission.setIsValid(0);
        sysPermissionDao.update(sysPermission);
    }

    /**
     * 修改SysPermission
     * @param sysPermission
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void modify(SysPermission sysPermission){
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute("user");
        sysPermission.setUpdateUser(userVo.getId());
        sysPermission.setUpdateDate(new Date());
        sysPermissionDao.update(sysPermission);
    }

    /**
     * 根据主键查询SysPermission
     * @param id
     * @return sysPermission
     */
    @Override
    public SysPermission queryOne(java.lang.Long id){
        return sysPermissionDao.queryOne(id);
    }

    /**
     * 根据主键查询SysPermission
     * @return sysPermissions
     */
    @Override
    public List<SysPermission> queryAll(){
        return sysPermissionDao.queryAll();
    }

    /**
     * 根据字段查询（如需分页请setPageBean）
     * @param sysPermission
     * @return sysPermissions
     */
    @Override
    public List<SysPermission> queryByFieldsAndPage(SysPermission sysPermission){
        return sysPermissionDao.queryByFieldsAndPage(sysPermission);
    }

    @Override
    public Integer queryMaxSequence(Long parentId) {
        return sysPermissionDao.queryMaxSequence(parentId);
    }

    @Override
    public List<SysPermission> queryByPermissionIds(Long[] permissionIds) {
        return sysPermissionDao.queryByPermissionIds(permissionIds);
    }

    /**
     * 根据父级获取权限菜单
     * @param parentId
     * @return
     */
    @Override
    public List<ZTree> queryZTreePermissions(Long parentId) {
        List<SysPermissionVo> permissions = sysPermissionDao.queryPermissionByParentId(parentId);
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

    /**
     * 删除权限菜单
     * @param permissionId
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED, readOnly = false)
    public void removePermission(Long permissionId) {
        SysPermissionService sysPermissionService = (SysPermissionService) AopContext.currentProxy();

        //删除权限菜单
        sysPermissionService.remove(permissionId);
        //删除菜单与角色的绑定关系
        sysRolePermissionService.batchRemoveRolePermission(null,new Long[]{permissionId});
    }

    /**
     * 获取多级菜单
     * @param parentId
     * @return
     */
    @Override
    public List<SysPermissionVo> listAllPermissionsByParentId(long parentId) {
        return sysPermissionDao.listAllPermissionsByParentId(parentId);
    }

    /**
     * 获取当前parentId下的菜单 递归
     * @param parentId
     * @param permissions
     * @return
     */
    @Override
    public List<LayuiNav> listAllNavsByParentId(Long parentId, Long[] permissions) {
        List<SysPermissionVo> sysPermissionVos = sysPermissionDao.listAllPermissionsByParentId(parentId);
        return getLayuiNavs(sysPermissionVos, Arrays.asList(permissions));
    }

    /**
     * 查看当前权限菜单是否有子权限菜单
     * @param permissionId
     */
    @Override
    public boolean hasChildrenByParentId(Long permissionId) {
        return (sysPermissionDao.countChildrenByParentId(permissionId) > 0 );
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