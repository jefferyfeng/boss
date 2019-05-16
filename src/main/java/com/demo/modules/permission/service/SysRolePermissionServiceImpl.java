package com.demo.modules.permission.service;

import com.demo.core.constants.Constants;
import com.demo.core.util.MyUtil;
import com.demo.modules.per.model.SysPermissionVo;
import com.demo.modules.per.model.UserVo;
import com.demo.modules.per.model.ZTree;
import com.demo.modules.permission.entity.SysRolePermission;
import com.demo.modules.permission.dao.SysRolePermissionDao;
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
 *  SysRolePermissionServiceImpl  实现类
 *
 *  @author fdh
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
public class SysRolePermissionServiceImpl implements SysRolePermissionService{
    @Autowired
    private SysRolePermissionDao sysRolePermissionDao;
    @Autowired
    private SysPermissionService sysPermissionService;

    /**
     * 新增SysRolePermission
     * @param sysRolePermission
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void add(SysRolePermission sysRolePermission){
        sysRolePermission.setCreateDate(new Date());
        sysRolePermission.setIsValid(1);
        sysRolePermissionDao.insert(sysRolePermission);
    }

    /**
     * 根据主键 删除SysRolePermission (逻辑删除)
     * @param sysRolePermission
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void remove(SysRolePermission sysRolePermission){
        //sysRolePermissionDao.delete(sysRolePermission.getId());
        sysRolePermission.setIsValid(0);
        sysRolePermissionDao.update(sysRolePermission);
    }

    /**
     * 修改SysRolePermission
     * @param sysRolePermission
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void modify(SysRolePermission sysRolePermission){
        sysRolePermissionDao.update(sysRolePermission);
    }

    /**
     * 根据主键查询SysRolePermission
     * @param id
     * @return sysRolePermission
     */
    @Override
    public SysRolePermission queryOne(java.lang.Long id){
        return sysRolePermissionDao.queryOne(id);
    }

    /**
     * 根据主键查询SysRolePermission
     * @return sysRolePermissions
     */
    @Override
    public List<SysRolePermission> queryAll(){
        return sysRolePermissionDao.queryAll();
    }

    /**
     * 根据字段查询（如需分页请setPageBean）
     * @param sysRolePermission
     * @return sysRolePermissions
     */
    @Override
    public List<SysRolePermission> queryByFieldsAndPage(SysRolePermission sysRolePermission){
        return sysRolePermissionDao.queryByFieldsAndPage(sysRolePermission);
    }

    /**
     * 删除角色菜单绑定关系
     * @param roleIds
     * @param permissionIds
     */
    @Override
    public void batchRemoveRolePermission(Long[] roleIds, Long[] permissionIds) {
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute(Constants.SESSIOIN_USER);
        sysRolePermissionDao.batchRemoveRolePermission(roleIds, permissionIds, userVo.getId(), new Date());
    }

    /**
     * 获取角色所有权限菜单
     * @param roleId
     * @return
     */
    @Override
    public List<ZTree> queryZTreeAllPermissions(Long roleId) {
        //当前所有的权限菜单
        List<SysPermissionVo> permissionVos = sysPermissionService.listAllPermissionsByParentId(0L);
        Long[] roles = {roleId};
        Long[] permissions = sysRolePermissionDao.listPermissionByRoleIds(roles);

        return formatZtree(permissionVos, Arrays.asList(permissions));

    }

    /**
     * 修改角色菜单
     * @param checkedPermissionIds
     * @param uncheckedPermissionIds
     * @param roleId
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED, readOnly = false)
    public void editRolePermission(String checkedPermissionIds, String uncheckedPermissionIds, Long roleId) {
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute("user");
        Date date = new Date();

        //先获取到当前要修改角色下的所有权限菜单
        Long[] roles = {roleId};
        Long[] permissions = sysRolePermissionDao.listPermissionByRoleIds(roles);
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
}