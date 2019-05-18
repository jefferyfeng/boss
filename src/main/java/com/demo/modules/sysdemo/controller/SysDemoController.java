package com.demo.modules.sysdemo.controller;

import com.demo.core.base.BaseResult;
import com.demo.core.base.LayuiData;
import com.demo.core.base.PageBean;
import com.demo.core.util.ResultUtil;
import com.demo.core.constants.Constants;
import com.demo.core.constants.ResultConstants;
import com.demo.modules.sysdemo.entity.SysDemo;
import com.demo.core.constants.Constants;

import com.demo.modules.sysdemo.service.SysDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  SysDemoController  测试控制器
 *
 *  @author fdh
 */
@RequestMapping("/sysDemo")
@RestController
public class SysDemoController {
    @Autowired
    private SysDemoService sysDemoService;

    /**
     * 新增SysDemo
     * @param sysDemo
     */
    @RequestMapping(value="/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public BaseResult add(SysDemo sysDemo){
        sysDemoService.add(sysDemo);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 删除SysDemo (物理删除)
     * @param id
     */
    /*
    @RequestMapping(value="/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public BaseResult delete(java.lang.Long id){
        sysDemoService.delete(id);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }
    */

    /**
     * 删除SysDemo (逻辑删除)
     * @param id
     */
    @RequestMapping(value="/remove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public BaseResult remove(java.lang.Long id){
        sysDemoService.remove(id);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 修改SysDemo
     * @param sysDemo
     */
    @RequestMapping(value="/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public BaseResult modify(SysDemo sysDemo){
        sysDemoService.modify(sysDemo);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 查询单个SysDemo
     * @param id
     */
    @RequestMapping(value="/queryOne/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
    public BaseResult queryOne(@PathVariable(value="id")java.lang.Long id){
        Map<String,Object> bodyMap = new HashMap<String,Object>();
        SysDemo sysDemo = sysDemoService.queryOne(id);
        bodyMap.put("sysDemo",sysDemo);
        return ResultUtil.result(ResultConstants.SUCCESS,bodyMap);
    }

    /**
     * 查全部SysDemo
     */
    @RequestMapping(value="/queryAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
    public BaseResult queryAll(){
        Map<String,Object> bodyMap = new HashMap<String,Object>();
        List<SysDemo> sysDemos = sysDemoService.queryAll();
        bodyMap.put("sysDemos",sysDemos);
        return ResultUtil.result(ResultConstants.SUCCESS,bodyMap);
    }

    /**
     * 根据字段查询（如需分页请setPageBean）
     * @param sysDemo
     */
    @RequestMapping(value="/queryByFieldsAndPage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
    public BaseResult queryByFieldsAndPage(SysDemo sysDemo){
        Map<String,Object> bodyMap = new HashMap<String,Object>();
        List<SysDemo> sysDemos = sysDemoService.queryByFieldsAndPage(sysDemo);
        bodyMap.put("sysDemos",sysDemos);
        bodyMap.put("pageBean",sysDemo.getPageBean());
        return ResultUtil.result(ResultConstants.SUCCESS,bodyMap);
    }

    /**
     * 批量删除
     * @param ids
     */
    @RequestMapping(value="/batchRemove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public BaseResult batchRemove(@RequestParam("ids[]") Long[] ids){
        sysDemoService.batchRemove(ids);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 跳转到列表页面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/sysDemoList")
    public ModelAndView sysDemoList(ModelAndView modelAndView){
        modelAndView.setViewName("modules/sysdemo/sysDemo_list");
        return modelAndView;
    }

    /**
     * 跳转到添加页面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/toAddSysDemo")
    public ModelAndView toAddSysDemo(ModelAndView modelAndView){
        modelAndView.setViewName("modules/sysdemo/sysDemo_add");
        return modelAndView;
    }

    /**
     * 跳转到修改页面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/toEditSysDemo")
    public ModelAndView toEditSysDemo(ModelAndView modelAndView, @RequestParam("id")java.lang.Long id){
        SysDemo sysDemo = sysDemoService.queryOne(id);
        modelAndView.addObject("sysDemo", sysDemo);
        modelAndView.setViewName("modules/sysdemo/sysDemo_edit");
        return modelAndView;
    }

    /**
     * 获取数据表格
     * @param sysDemo
     * @param pageBean
     * @return
     */
    @RequestMapping("/listSysDemos")
    public LayuiData listSysDemos(SysDemo sysDemo, PageBean pageBean){
        LayuiData layuiData = new LayuiData();
        try {
            sysDemo.setPageBean(pageBean);
            List<SysDemo> sysDemoList = sysDemoService.queryByFieldsAndPage(sysDemo);
            layuiData.setCode(ResultConstants.SUCCESS.getCode());
            layuiData.setMsg(ResultConstants.SUCCESS.getMsg());
            layuiData.setCount(pageBean.getTotal());
            layuiData.setData(sysDemoList);
            return layuiData;
        } catch (Exception e) {
            e.printStackTrace();
            layuiData.setCode(ResultConstants.SUCCESS.getCode());
            layuiData.setMsg(ResultConstants.FAILED.getMsg());
            return layuiData;
        }
    }

    /**
     * 批量状态修改
     * @return
     */
    @RequestMapping(value="/batchModifyStatus", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public BaseResult batchModifyStatus(@RequestParam("ids[]") Long[] ids,@RequestParam(value = "status",required = true)Integer status){
        sysDemoService.batchModifyStatus(ids,status);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

}