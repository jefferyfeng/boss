package com.demo.modules.permission.controller;

import com.demo.core.base.BaseResult;
import com.demo.core.base.LayuiData;
import com.demo.core.base.PageBean;
import com.demo.core.constants.ResultConstants;
import com.demo.core.util.ResultUtil;
import com.demo.modules.per.model.RoleVo;
import com.demo.modules.per.service.PerService;
import com.demo.modules.permission.entity.SysUserRole;
import com.demo.modules.permission.service.SysUserRoleService;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  SysUserRoleController  控制器
 *
 *  @author fdh
 */
@RequestMapping("/sysUserRole")
@RestController
public class SysUserRoleController {
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private PerService perService;

    /**
     * 新增SysUserRole
     * @param sysUserRole
     */
    @RequestMapping(value="/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public BaseResult add(SysUserRole sysUserRole){
        sysUserRoleService.add(sysUserRole);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 删除SysUserRole
     * @param id
     */
    @RequestMapping(value="/remove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.DELETE)
    public BaseResult remove(Long id){
        sysUserRoleService.remove(id);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 修改SysUserRole
     * @param sysUserRole
     */
    @RequestMapping(value="/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.PUT)
    public BaseResult modify(SysUserRole sysUserRole){
        sysUserRoleService.modify(sysUserRole);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 查询单个SysUserRole
     * @param id
     */
    @RequestMapping(value="/queryOne/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
    public BaseResult queryOne(@PathVariable(value="id")java.lang.Long id){
        Map<String,Object> bodyMap = new HashMap<String,Object>(1);
        SysUserRole sysUserRole = sysUserRoleService.queryOne(id);
        bodyMap.put("sysUserRole",sysUserRole);
        return ResultUtil.result(ResultConstants.SUCCESS, bodyMap);
    }

    /**
     * 查全部SysUserRole
     */
    @RequestMapping(value="/queryAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
    public BaseResult queryAll(){
        Map<String,Object> bodyMap = new HashMap<String,Object>(1);
        List<SysUserRole> sysUserRoles = sysUserRoleService.queryAll();
        bodyMap.put("sysUserRoles",sysUserRoles);
        return ResultUtil.result(ResultConstants.SUCCESS, bodyMap);
    }

    /**
     * 根据字段查询（如需分页请setPageBean）
     * @param sysUserRole
     * @return sysUserRoles
     */
    @RequestMapping(value="/queryByFieldsAndPage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
    public BaseResult queryByFieldsAndPage(SysUserRole sysUserRole){
        Map<String,Object> bodyMap = new HashMap<String,Object>(1);
        List<SysUserRole> sysUserRoles = sysUserRoleService.queryByFieldsAndPage(sysUserRole);
        bodyMap.put("sysUserRoles",sysUserRoles);
        return ResultUtil.result(ResultConstants.SUCCESS, bodyMap);
    }

    /**
     * 获取用户角色配置数据表格
     * @param pageBean 分页bean
     * @param userId 用户id
     * @return
     */
    @RequestMapping(value="/listUserRoles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
    public LayuiData listUserRoles(PageBean pageBean, Long userId){
        LayuiData layuiData = new LayuiData();
        Object roleList = sysUserRoleService.listUserRolesByPage(pageBean, userId);
        layuiData.setCode(ResultConstants.SUCCESS.getCode());
        layuiData.setMsg(ResultConstants.SUCCESS.getMsg());
        layuiData.setCount(pageBean.getTotal());
        layuiData.setData(roleList);
        return layuiData;
    }

    /**
     * 修改用户的角色
     * @param checkedIds 选中的角色ids
     * @param uncheckedIds 未选中的角色ids
     * @param userId 用户的id
     * @return
     */
    @RequestMapping(value="/editUserRole", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public BaseResult editUserRole(@RequestParam("checkedIds") String checkedIds,
                                   @RequestParam("uncheckedIds") String uncheckedIds,
                                   @RequestParam(value = "userId",required = true)Long userId){
        sysUserRoleService.editUserRole(checkedIds, uncheckedIds, userId);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }
}