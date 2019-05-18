package com.demo.modules.syslog.controller;

import com.demo.core.base.BaseResult;
import com.demo.core.base.LayuiData;
import com.demo.core.base.PageBean;
import com.demo.core.util.ResultUtil;
import com.demo.core.constants.Constants;
import com.demo.core.constants.ResultConstants;
import com.demo.modules.syslog.entity.SysLog;
import com.demo.core.constants.Constants;

import com.demo.modules.syslog.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  SysLogController  控制器
 *
 *  @author fdh
 */
@RequestMapping("/sysLog")
@RestController
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 新增SysLog
     * @param sysLog
     */
    @RequestMapping(value="/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public BaseResult add(SysLog sysLog){
        sysLogService.add(sysLog);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 删除SysLog (物理删除)
     * @param id
     */
    /*
    @RequestMapping(value="/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public BaseResult delete(java.lang.Long id){
        sysLogService.delete(id);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }
    */

    /**
     * 删除SysLog (逻辑删除)
     * @param id
     */
    @RequestMapping(value="/remove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public BaseResult remove(java.lang.Long id){
        sysLogService.remove(id);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 修改SysLog
     * @param sysLog
     */
    @RequestMapping(value="/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public BaseResult modify(SysLog sysLog){
        sysLogService.modify(sysLog);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 查询单个SysLog
     * @param id
     */
    @RequestMapping(value="/queryOne/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
    public BaseResult queryOne(@PathVariable(value="id")java.lang.Long id){
        Map<String,Object> bodyMap = new HashMap<String,Object>();
        SysLog sysLog = sysLogService.queryOne(id);
        bodyMap.put("sysLog",sysLog);
        return ResultUtil.result(ResultConstants.SUCCESS,bodyMap);
    }

    /**
     * 查全部SysLog
     */
    @RequestMapping(value="/queryAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
    public BaseResult queryAll(){
        Map<String,Object> bodyMap = new HashMap<String,Object>();
        List<SysLog> sysLogs = sysLogService.queryAll();
        bodyMap.put("sysLogs",sysLogs);
        return ResultUtil.result(ResultConstants.SUCCESS,bodyMap);
    }

    /**
     * 根据字段查询（如需分页请setPageBean）
     * @param sysLog
     */
    @RequestMapping(value="/queryByFieldsAndPage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
    public BaseResult queryByFieldsAndPage(SysLog sysLog){
        Map<String,Object> bodyMap = new HashMap<String,Object>();
        List<SysLog> sysLogs = sysLogService.queryByFieldsAndPage(sysLog);
        bodyMap.put("sysLogs",sysLogs);
        bodyMap.put("pageBean",sysLog.getPageBean());
        return ResultUtil.result(ResultConstants.SUCCESS,bodyMap);
    }

    /**
     * 批量删除
     * @param ids
     */
    @RequestMapping(value="/batchRemove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public BaseResult batchRemove(@RequestParam("ids[]") Long[] ids){
        sysLogService.batchRemove(ids);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 跳转到列表页面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/sysLogList")
    public ModelAndView sysLogList(ModelAndView modelAndView){
        modelAndView.setViewName("modules/syslog/sysLog_list");
        return modelAndView;
    }

    /**
     * 跳转到添加页面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/toAddSysLog")
    public ModelAndView toAddSysLog(ModelAndView modelAndView){
        modelAndView.setViewName("modules/syslog/sysLog_add");
        return modelAndView;
    }

    /**
     * 跳转到修改页面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/toEditSysLog")
    public ModelAndView toEditSysLog(ModelAndView modelAndView, @RequestParam("id")java.lang.Long id){
        SysLog sysLog = sysLogService.queryOne(id);
        modelAndView.addObject("sysLog", sysLog);
        modelAndView.setViewName("modules/syslog/sysLog_edit");
        return modelAndView;
    }

    /**
     * 获取数据表格
     * @param sysLog
     * @param pageBean
     * @return
     */
    @RequestMapping("/listSysLogs")
    public LayuiData listSysLogs(SysLog sysLog, PageBean pageBean){
        LayuiData layuiData = new LayuiData();
        try {
            sysLog.setPageBean(pageBean);
            List<SysLog> sysLogList = sysLogService.queryByFieldsAndPage(sysLog);
            layuiData.setCode(ResultConstants.SUCCESS.getCode());
            layuiData.setMsg(ResultConstants.SUCCESS.getMsg());
            layuiData.setCount(pageBean.getTotal());
            layuiData.setData(sysLogList);
            return layuiData;
        } catch (Exception e) {
            e.printStackTrace();
            layuiData.setCode(ResultConstants.SUCCESS.getCode());
            layuiData.setMsg(ResultConstants.FAILED.getMsg());
            return layuiData;
        }
    }


}