package com.demo.modules.permission.controller;

import com.demo.core.base.BaseResult;
import com.demo.core.base.LayuiData;
import com.demo.core.base.PageBean;
import com.demo.core.constants.ResultConstants;
import com.demo.core.util.ResultUtil;
import com.demo.modules.per.model.RoleVo;
import com.demo.modules.permission.entity.SysRole;
import com.demo.modules.permission.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 *  SysRoleController  控制器
 *
 *  @author fdh
 */
@RequestMapping("/sysRole")
@RestController
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;
    /**
     * 新增SysRole
     * @param sysRole
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public BaseResult add(SysRole sysRole){
        sysRoleService.add(sysRole);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 删除SysRole
     * @param sysRole
     */
    @RequestMapping(value="/remove",method=RequestMethod.DELETE)
    public void remove(SysRole sysRole){
        sysRoleService.remove(sysRole);
    }

    /**
     * 修改SysRole
     * @param sysRole
     */
    @RequestMapping(value="/modify",method=RequestMethod.POST)
    public BaseResult modify(SysRole sysRole){
        sysRoleService.modify(sysRole);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 查询单个SysRole
     * @param id
     * @return sysRole
     */
    @RequestMapping(value="/queryOne/{id}",method=RequestMethod.GET)
    public SysRole queryOne(@PathVariable(value="id")java.lang.Long id){
        return sysRoleService.queryOne(id);
    }

    /**
     * 查全部SysRole
     * @return sysRoles
     */
    @RequestMapping(value="/queryAll",method=RequestMethod.GET)
    public List<SysRole> queryAll(){
        return sysRoleService.queryAll();
    }

    /**
     * 角色状态修改
     * @return
     */
    @RequestMapping("/batchModifyStatus")
    public BaseResult batchModifyStatus(@RequestParam("ids[]") Long[] ids,@RequestParam(value = "status",required = true)Integer status){
        sysRoleService.batchModifyStatus(ids,status);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 角色批量删除
     * @return
     */
    @RequestMapping("/batchRemoveRoles")
    public BaseResult batchRemoveRoles(@RequestParam("ids[]") Long[] ids){
        sysRoleService.batchRemoveRoles(ids);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 根据字段查询（如需分页请setPageBean）
     * @param sysRole
     * @return sysRoles
     */
    @RequestMapping(value="/queryByFieldsAndPage",method=RequestMethod.GET)
    public List<SysRole> queryByFieldsAndPage(SysRole sysRole){
        return sysRoleService.queryByFieldsAndPage(sysRole);
    }

    /**
     * 跳转到角色列表页面
     */
    @RequestMapping("/toRoles")
    public ModelAndView toRoles(ModelAndView modelAndView){
        modelAndView.setViewName("modules/per/sysrole/sysRole_list");
        return modelAndView;
    }


    /**
     * 跳转到角色添加页面
     */
    @RequestMapping("/toAddRole")
    public ModelAndView toAddRoles(ModelAndView modelAndView){
        modelAndView.setViewName("modules/per/sysrole/sysRole_add");
        return modelAndView;
    }

    /**
     * 跳转到角色修改页面
     */
    @RequestMapping("/toEditRole")
    public ModelAndView toEditRole(@RequestParam("roleId") Long roleId,ModelAndView modelAndView){
        SysRole role = sysRoleService.queryOne(roleId);
        modelAndView.addObject("roleVo",role);
        modelAndView.setViewName("modules/per/sysrole/sysRole_edit");
        return modelAndView;
    }

    /**
     * 跳转到角色配置页面
     */
    @RequestMapping("/toEditRolePermission")
    public ModelAndView toEditRolePermission(@RequestParam("roleId") Long roleId,ModelAndView modelAndView){
        modelAndView.addObject("roleId",roleId);
        modelAndView.setViewName("modules/per/sysrole/sysRole_permission_edit");
        return modelAndView;
    }

    /**
     * 获取角色数据表格
     *
     * @param role
     * @param pageBean
     * @return
     */
    @RequestMapping("/listRoles")
    public LayuiData listRoles(RoleVo role, PageBean pageBean){
        LayuiData layuiData = new LayuiData();
        try {
            List<RoleVo> rolesList = sysRoleService.listRolesByPage(role,pageBean);
            layuiData.setCode(0);
            layuiData.setMsg("成功!");
            layuiData.setCount(pageBean.getTotal());
            layuiData.setData(rolesList);
            return layuiData;
        } catch (Exception e) {
            e.printStackTrace();
            layuiData.setCode(1);
            layuiData.setMsg("失败!");
            return layuiData;
        }
    }
}