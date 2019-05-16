package com.demo.modules.permission.controller;

import com.demo.core.base.BaseResult;
import com.demo.core.constants.ResultConstants;
import com.demo.core.util.MyUtil;
import com.demo.core.util.ResultUtil;
import com.demo.modules.per.model.LayuiNav;
import com.demo.modules.per.model.UserVo;
import com.demo.modules.per.model.ZTree;
import com.demo.modules.permission.entity.SysPermission;
import com.demo.modules.permission.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  SysPermissionController  控制器
 *
 *  @author fdh
 */
@RequestMapping("/sysPermission")
@RestController
public class SysPermissionController {
    @Autowired
    private SysPermissionService sysPermissionService;
    /**
     * 新增SysPermission
     * @param sysPermission
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public BaseResult add(SysPermission sysPermission){
        sysPermissionService.add(sysPermission);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 删除SysPermission
     * @param permissionId
     */
    @RequestMapping(value="/remove",method=RequestMethod.DELETE)
    public void remove(Long permissionId){
        sysPermissionService.remove(permissionId);
    }

    /**
     * 修改SysPermission
     * @param sysPermission
     */
    @RequestMapping(value="/modify",method=RequestMethod.POST)
    public BaseResult modify(SysPermission sysPermission){
        sysPermissionService.modify(sysPermission);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 查询单个SysPermission
     * @param id
     * @return sysPermission
     */
    @RequestMapping(value="/queryOne/{id}",method=RequestMethod.GET)
    public SysPermission queryOne(@PathVariable(value="id")java.lang.Long id){
        return sysPermissionService.queryOne(id);
    }

    /**
     * 查全部SysPermission
     * @return sysPermissions
     */
    @RequestMapping(value="/queryAll",method=RequestMethod.GET)
    public List<SysPermission> queryAll(){
        return sysPermissionService.queryAll();
    }

    /**
     * 根据字段查询（如需分页请setPageBean）
     * @param sysPermission
     * @return sysPermissions
     */
    @RequestMapping(value="/queryByFieldsAndPage",method=RequestMethod.GET)
    public List<SysPermission> queryByFieldsAndPage(SysPermission sysPermission){
        return sysPermissionService.queryByFieldsAndPage(sysPermission);
    }

    /**
     * 获取权限菜单
     * @param parentId
     * @return
     */
    @RequestMapping("/listAllLayuiNavs/{parentId}")
    public BaseResult getAllLayuiNavs(@PathVariable("parentId") Long parentId){
        Map<String,Object> bodyMap = new HashMap<String,Object>(1);

        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute("user");
        List<LayuiNav> navsList = sysPermissionService.listAllNavsByParentId(parentId, userVo.getPermissions());

        bodyMap.put("navsList",navsList);
        return ResultUtil.result(ResultConstants.SUCCESS,bodyMap);
    }


    /**
     * 跳转到权限菜单管理页面
     */
    @RequestMapping("/toPermissions")
    public ModelAndView toPermissions(ModelAndView modelAndView){
        modelAndView.setViewName("modules/per/syspermission/sysPermission_list");
        return modelAndView;
    }

    /**
     * 跳转到添加下级权限菜单页面
     */
    @RequestMapping("/toAddChildPermission")
    public ModelAndView toAddChildPermission(@RequestParam("parentId")Long parentId, ModelAndView modelAndView){
        SysPermission parentPermission = sysPermissionService.queryOne(parentId);
        Integer sequence = sysPermissionService.queryMaxSequence(parentId);
        modelAndView.addObject("parentPermission",parentPermission);
        modelAndView.addObject("sequence",sequence==null ? "无":sequence);
        modelAndView.setViewName("modules/per/syspermission/sysPermission_addChild");
        return modelAndView;
    }

    /**
     * 跳转到添加同级权限菜单页面
     */
    @RequestMapping("/toAddBrotherPermission")
    public ModelAndView toAddBrotherPermission(@RequestParam("brotherId")Long brotherId, ModelAndView modelAndView){
        SysPermission brotherPermission = sysPermissionService.queryOne(brotherId);
        SysPermission parentPermission = null;
        if("0".equals(brotherPermission.getParentId())){
            parentPermission = new SysPermission();
            parentPermission.setId(0L);
            parentPermission.setPermissionName("无");
        }else{
            parentPermission = sysPermissionService.queryOne(Long.valueOf(brotherPermission.getParentId()));
        }
        Integer sequence = sysPermissionService.queryMaxSequence(Long.valueOf(parentPermission.getId()));
        modelAndView.addObject("parentPermission",parentPermission);
        modelAndView.addObject("sequence",sequence==null ? "无":sequence);
        modelAndView.setViewName("modules/per/syspermission/sysPermission_addBrother");
        return modelAndView;
    }

    /**
     * 跳转修改权限菜单页面
     */
    @RequestMapping("/toEditPermission")
    public ModelAndView toEditPermission(@RequestParam("permissionId")Long permissionId, ModelAndView modelAndView){
        SysPermission permission = sysPermissionService.queryOne(permissionId);
        Integer sequence = sysPermissionService.queryMaxSequence(Long.valueOf(permission.getParentId()));
        modelAndView.addObject("permission",permission);
        modelAndView.addObject("sequence",sequence==null ? "无":sequence);
        modelAndView.setViewName("modules/per/syspermission/sysPermission_edit");
        return modelAndView;
    }

    /**
     * 获取所有权限菜单
     */
    @RequestMapping("/listPermissions")
    public List<ZTree> listPermissions(@RequestParam(value = "id",required = false) String id){
        Long parentId = null;
        if(MyUtil.isEmpty(id)){
            parentId = 0L;
        }else{
            parentId = Long.valueOf(id);
        }
        List<ZTree> permissionList = sysPermissionService.queryZTreePermissions(parentId);
        return permissionList;
    }

    /**
     * 删除菜单
     */
    @RequestMapping(value = "/removePermission")
    public BaseResult removePermission(@RequestParam("permissionId") Long permissionId){
        if(sysPermissionService.hasChildrenByParentId(permissionId)){
            return ResultUtil.result(ResultConstants.FAILED.getCode(),"菜单下有子菜单不能删除！");
        }
        sysPermissionService.removePermission(permissionId);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }
}