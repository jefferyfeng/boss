package com.demo.modules.permission.controller;

import com.demo.core.base.BaseResult;
import com.demo.core.constants.ResultConstants;
import com.demo.core.util.ResultUtil;
import com.demo.modules.per.model.ZTree;
import com.demo.modules.permission.entity.SysRolePermission;
import com.demo.modules.permission.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  SysRolePermissionController  控制器
 *
 *  @author fdh
 */
@RequestMapping("/sysRolePermission")
@RestController
public class SysRolePermissionController {
    @Autowired
    private SysRolePermissionService sysRolePermissionService;
    /**
     * 新增SysRolePermission
     * @param sysRolePermission
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public void add(SysRolePermission sysRolePermission){
        sysRolePermissionService.add(sysRolePermission);
    }

    /**
     * 删除SysRolePermission
     * @param sysRolePermission
     */
    @RequestMapping(value="/remove",method=RequestMethod.DELETE)
    public void remove(SysRolePermission sysRolePermission){
        sysRolePermissionService.remove(sysRolePermission);
    }

    /**
     * 修改SysRolePermission
     * @param sysRolePermission
     */
    @RequestMapping(value="/modify",method=RequestMethod.PUT)
    public void modify(SysRolePermission sysRolePermission){
        sysRolePermissionService.modify(sysRolePermission);
    }

    /**
     * 查询单个SysRolePermission
     * @param id
     * @return sysRolePermission
     */
    @RequestMapping(value="/queryOne/{id}",method=RequestMethod.GET)
    public SysRolePermission queryOne(@PathVariable(value="id")java.lang.Long id){
        return sysRolePermissionService.queryOne(id);
    }

    /**
     * 查全部SysRolePermission
     * @return sysRolePermissions
     */
    @RequestMapping(value="/queryAll",method=RequestMethod.GET)
    public List<SysRolePermission> queryAll(){
        return sysRolePermissionService.queryAll();
    }

    /**
     * 根据字段查询（如需分页请setPageBean）
     * @param sysRolePermission
     * @return sysRolePermissions
     */
    @RequestMapping(value="/queryByFieldsAndPage",method=RequestMethod.GET)
    public List<SysRolePermission> queryByFieldsAndPage(SysRolePermission sysRolePermission){
        return sysRolePermissionService.queryByFieldsAndPage(sysRolePermission);
    }

    /**
     * 获取角色所有权限菜单
     *
     * @return
     */
    @RequestMapping("/listRolePermissions/{roleId}")
    public List<ZTree> listRolePermissions(@PathVariable("roleId") Long roleId){
        List<ZTree> permissionList = sysRolePermissionService.queryZTreeAllPermissions(roleId);
        return permissionList;
    }

    /**
     * @description 根据角色配置菜单权限
     *
     * @param uncheckedPermissionIds 未选中的权限菜单
     * @param checkedPermissionIds 选中的权限菜单
     * @param roleId 配置权限菜单的角色id
     * @return
     */
    @RequestMapping("/editRolePermission")
    public BaseResult deletePermission(@RequestParam("checkedPermissionIds") String checkedPermissionIds,
                                       @RequestParam("uncheckedPermissionIds")String uncheckedPermissionIds,
                                       @RequestParam("roleId") Long roleId){
        sysRolePermissionService.editRolePermission(checkedPermissionIds,uncheckedPermissionIds,roleId);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }
}