package com.demo.modules.per.controller;

import com.demo.core.annotation.OperateLog;
import com.demo.core.base.BaseResult;
import com.demo.core.constants.Constants;
import com.demo.core.constants.ResultConstants;
import com.demo.core.util.CreateValidateCode;
import com.demo.core.util.MyUtil;
import com.demo.core.util.ResultUtil;
import com.demo.modules.per.exception.UserException;
import com.demo.modules.per.model.*;
import com.demo.modules.per.service.PerService;
import com.demo.modules.permission.entity.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 权限控制相关
 */
@RequestMapping("/per")
@RestController
public class PerController {
    @Autowired
    private PerService perService;

    /**
     * 跳转到首页
     */
    @RequestMapping("/toPortal")
    public ModelAndView toPortal(ModelAndView modelAndView){
        UserVo user = (UserVo) MyUtil.getSession().getAttribute("user");
        //顶部菜单
        List<LayuiNav> topMenus = perService.listNavsByParentId(0L, user.getPermissions());
        modelAndView.addObject("topMenus",topMenus);
        modelAndView.setViewName("modules/per/portal");
        return modelAndView;
    }

    /**
     * 获取验证码
     * @param request
     * @param response
     */
    @RequestMapping("/getVerifyCode")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        CreateValidateCode cvc = new CreateValidateCode(130,40,4);
        String code = cvc.getCode();
        session.setAttribute(Constants.VERIFY_CODE,code);
        cvc.write(response.getOutputStream());
    }

    /**
     * 校验验证码
     * @param code
     */
    @RequestMapping("/validateVerifyCode")
    public BaseResult validateVerifyCode(@RequestParam(value = "code",required = false)String code,HttpServletRequest request){
        if(StringUtils.isNotBlank(code)){
            return ResultUtil.result(ResultConstants.FAILED,"验证码不能为空，请重新输入！");
        }
        String sessionCode = (String)request.getAttribute(Constants.VERIFY_CODE);
        if(code.equals(sessionCode)){
            return ResultUtil.result(ResultConstants.SUCCESS);
        }else{
            return ResultUtil.result(ResultConstants.FAILED,"验证码不正确，请重新输入！");
        }
    }

    /**
     * 登陆
     */
    @RequestMapping("/login")
    @OperateLog(description = "登陆系统")
    public BaseResult login(SysUser user,String code,HttpServletRequest request){
        try {
            String sessionVerifyCode = (String) request.getSession().getAttribute(Constants.VERIFY_CODE);
            if(!sessionVerifyCode.equals(code)){
                return ResultUtil.result(ResultConstants.FAILED,"验证码不正确，请重新输入！");
            }
            UserVo userVo = perService.login(user);
            request.getSession().setAttribute("user",userVo);
            return ResultUtil.result(ResultConstants.SUCCESS);
        } catch (UserException e){
            return ResultUtil.result(ResultConstants.FAILED,e.getMessage());
        }
    }

    /**
     * 跳转到首页
     */
    @RequestMapping("/toMain")
    public ModelAndView toMain(ModelAndView modelAndView){
        modelAndView.setViewName("modules/per/main");
        return modelAndView;
    }


    /**
     * 跳转到日志管理
     */
    @RequestMapping("/toSysLog")
    public ModelAndView toSysLog(ModelAndView modelAndView){
        modelAndView.setViewName("static/page/systemSetting/sys_log");
        return modelAndView;
    }

    /**
     * 跳转到图标管理
     */
    @RequestMapping("/toIcons")
    public ModelAndView toIcons(ModelAndView modelAndView){
        modelAndView.setViewName("modules/per/iconCommon");
        return modelAndView;
    }

}
