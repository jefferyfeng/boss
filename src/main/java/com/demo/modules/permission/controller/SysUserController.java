package com.demo.modules.permission.controller;

import com.demo.core.base.BaseResult;
import com.demo.core.base.LayuiData;
import com.demo.core.base.PageBean;
import com.demo.core.constants.ResultConstants;
import com.demo.core.util.ResultUtil;
import com.demo.modules.per.exception.UserException;
import com.demo.modules.per.model.UserVo;
import com.demo.modules.per.service.PerService;
import com.demo.modules.permission.entity.SysRole;
import com.demo.modules.permission.entity.SysUser;
import com.demo.modules.permission.service.SysRoleService;
import com.demo.modules.permission.service.SysUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 *  SysUserController  控制器
 *
 *  @author fdh
 */
@RequestMapping("/sysUser")
@RestController
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 新增SysUser
     * @param sysUser
     */
    @RequestMapping(value="/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public BaseResult add(SysUser sysUser) {
        sysUserService.add(sysUser);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 删除SysUser (逻辑删除)
     * @param id
     */
    @RequestMapping(value="/remove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.PUT)
    public BaseResult remove(java.lang.Long id){
        sysUserService.remove(id);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 删除SysUser (物理删除)
     * @param id
     */
    @RequestMapping(value="/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.DELETE)
    public BaseResult delete(java.lang.Long id){
        sysUserService.delete(id);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 修改SysUser
     * @param sysUser
     */
    @RequestMapping(value="/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public BaseResult modify(SysUser sysUser){
        sysUserService.modify(sysUser);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 查询单个SysUser
     * @param id
     * @return sysUser
     */
    @RequestMapping(value="/queryOne/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE ,method=RequestMethod.GET)
    public SysUser queryOne(@PathVariable(value="id")java.lang.Long id){
        return sysUserService.queryOne(id);
    }

    /**
     * 查全部SysUser
     * @return sysUsers
     */
    @RequestMapping(value="/queryAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
    public List<SysUser> queryAll(){
        return sysUserService.queryAll();
    }

    /**
     * 根据字段查询（如需分页请setPageBean）
     * @param sysUser
     * @return sysUsers
     */
    @RequestMapping(value="/queryByFieldsAndPage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
    public List<SysUser> queryByFieldsAndPage(SysUser sysUser){
        return sysUserService.queryByFieldsAndPage(sysUser);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value="/batchRemove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public BaseResult batchRemove(@RequestParam("ids[]") Long[] ids){
        sysUserService.batchRemove(ids);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 批量修改状态
     * @param ids
     * @return
     */
    @RequestMapping(value="/batchModifyStatus", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public BaseResult batchModifyStatus(@RequestParam("ids[]") Long[] ids, @RequestParam("status")Integer status){
        sysUserService.batchModifyStatus(ids,status);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 跳转到用户管理页面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/toUsers")
    public ModelAndView toUsers(ModelAndView modelAndView){
        List<SysRole> rolesList = sysRoleService.queryAll();
        modelAndView.addObject("rolesList",rolesList);
        modelAndView.setViewName("modules/per/sysuser/sysUser_list");
        return modelAndView;
    }

    /**
     * 跳转到用户添加页面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/toAddUser")
    public ModelAndView toAddUser(ModelAndView modelAndView){
        modelAndView.setViewName("modules/per/sysuser/sysUser_add");
        return modelAndView;
    }

    /**
     * 跳转到用户管理页面
     * @param userId 修改的用户id
     * @param modelAndView
     * @return
     */
    @RequestMapping("/toEditUser")
    public ModelAndView toEditUser(@Param("userId") Long userId, ModelAndView modelAndView){
        UserVo userVo = sysUserService.queryUser(userId);
        modelAndView.addObject("userVo",userVo);
        modelAndView.setViewName("modules/per/sysuser/sysUser_edit");
        return modelAndView;
    }

    /**
     * 跳转到角色配置页面
     * @param userId 修改的用户id
     * @param modelAndView
     * @return
     */
    @RequestMapping("/toUserRoleEdit")
    public ModelAndView toUserRoleEdit(@RequestParam("userId")Long userId, ModelAndView modelAndView){
        modelAndView.addObject("userId",userId);
        modelAndView.setViewName("modules/per/sysuser/sysUser_role_edit");
        return modelAndView;
    }

    /**
     * 获取用户数据表格
     * @param user
     * @param pageBean
     * @return
     */
    @RequestMapping(value = "/listUsers",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.GET)
    public LayuiData listUsers(UserVo user, PageBean pageBean){
        LayuiData layuiData = new LayuiData();
        List<UserVo> usersList = sysUserService.listUsersByPage(user,pageBean);
        layuiData.setCode(ResultConstants.SUCCESS.getCode());
        layuiData.setMsg(ResultConstants.SUCCESS.getMsg());
        layuiData.setCount(pageBean.getTotal());
        layuiData.setData(usersList);
        return layuiData;
    }

    /**
     * 新增SysUser
     * @param sysUser
     */
    @RequestMapping(value="/addUser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public BaseResult addUser(SysUser sysUser) {
        sysUserService.addUser(sysUser);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 重置密码
     * @param ids
     * @return
     */
    @RequestMapping(value="/resetPassword", produces = MediaType.APPLICATION_JSON_UTF8_VALUE ,method = RequestMethod.POST)
    public BaseResult resetPassword(@RequestParam("ids[]") Long[] ids){
        sysUserService.resetPassword(ids);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }


    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    @RequestMapping(value="/batchRemoveUsers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE ,method = RequestMethod.POST)
    public BaseResult batchRemoveUsers(@RequestParam("ids[]") Long[] ids){
        sysUserService.batchRemoveUsers(ids);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 跳转到修改密码
     */
    @RequestMapping("/toChangePwd")
    public ModelAndView toChangePwd(ModelAndView modelAndView){
        modelAndView.setViewName("modules/per/sysuser/sysUser_pwd_edit");
        return modelAndView;
    }

    /**
     * 跳转到用户信息
     */
    @RequestMapping("/toUserInfo")
    public ModelAndView toUserInfo(ModelAndView modelAndView){
        modelAndView.setViewName("modules/per/sysuser/sysUser_info");
        return modelAndView;
    }


    /**
     * 修改密码
     * @param userId
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @RequestMapping(value="/changePwd", produces = MediaType.APPLICATION_JSON_UTF8_VALUE ,method = RequestMethod.POST)
    public BaseResult resetPassword(Long userId,String oldPwd,String newPwd){
        try {
            sysUserService.changePwd(userId, oldPwd, newPwd);
            return ResultUtil.result(ResultConstants.SUCCESS);
        } catch (UserException e) {
            return ResultUtil.result(ResultConstants.FAILED.getCode(),e.getMessage());
        }
    }

}