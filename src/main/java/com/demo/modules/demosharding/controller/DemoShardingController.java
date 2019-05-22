package com.demo.modules.demosharding.controller;

import com.demo.core.base.BaseResult;
import com.demo.core.base.LayuiData;
import com.demo.core.base.PageBean;
import com.demo.core.util.ResultUtil;
import com.demo.core.constants.Constants;
import com.demo.core.constants.ResultConstants;
import com.demo.modules.demosharding.entity.DemoSharding;
import com.demo.core.constants.Constants;
import com.demo.core.util.MyUtil;

import com.demo.modules.demosharding.service.DemoShardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  DemoShardingController  控制器
 *
 *  @author fdh
 */
@RequestMapping("/demoSharding")
@RestController
public class DemoShardingController {
    @Autowired
    private DemoShardingService demoShardingService;

    /**
     * 新增DemoSharding
     * @param demoSharding
     */
    @RequestMapping(value="/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public BaseResult add(DemoSharding demoSharding){
        demoShardingService.add(demoSharding);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 删除DemoSharding (物理删除)
     * @param id
     */
    /*
    @RequestMapping(value="/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public BaseResult delete(java.lang.Long id){
        demoShardingService.delete(id);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }
    */

    /**
     * 删除DemoSharding (逻辑删除)
     * @param id
     */
    @RequestMapping(value="/remove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public BaseResult remove(java.lang.Long id){
        demoShardingService.remove(id);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 修改DemoSharding
     * @param demoSharding
     */
    @RequestMapping(value="/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public BaseResult modify(DemoSharding demoSharding){
        demoShardingService.modify(demoSharding);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 查询单个DemoSharding
     * @param id
     */
    @RequestMapping(value="/queryOne/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
    public BaseResult queryOne(@PathVariable(value="id")java.lang.Long id){
        Map<String,Object> bodyMap = new HashMap<String,Object>();
        DemoSharding demoSharding = demoShardingService.queryOne(id);
        bodyMap.put("demoSharding",demoSharding);
        return ResultUtil.result(ResultConstants.SUCCESS,bodyMap);
    }

    /**
     * 查全部DemoSharding
     */
    @RequestMapping(value="/queryAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
    public BaseResult queryAll(){
        Map<String,Object> bodyMap = new HashMap<String,Object>();
        List<DemoSharding> demoShardings = demoShardingService.queryAll();
        bodyMap.put("demoShardings",demoShardings);
        return ResultUtil.result(ResultConstants.SUCCESS,bodyMap);
    }

    /**
     * 根据字段查询（如需分页请setPageBean）
     * @param demoSharding
     */
    @RequestMapping(value="/queryByFieldsAndPage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
    public BaseResult queryByFieldsAndPage(DemoSharding demoSharding){
        Map<String,Object> bodyMap = new HashMap<String,Object>();
        List<DemoSharding> demoShardings = demoShardingService.queryByFieldsAndPage(demoSharding);
        bodyMap.put("demoShardings",demoShardings);
        bodyMap.put("pageBean",demoSharding.getPageBean());
        return ResultUtil.result(ResultConstants.SUCCESS,bodyMap);
    }

    /**
     * 批量删除
     * @param ids
     */
    @RequestMapping(value="/batchRemove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public BaseResult batchRemove(@RequestParam("ids[]") Long[] ids){
        demoShardingService.batchRemove(ids);
        return ResultUtil.result(ResultConstants.SUCCESS);
    }

    /**
     * 跳转到列表页面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/demoShardingList")
    public ModelAndView demoShardingList(ModelAndView modelAndView){
        modelAndView.setViewName("modules/demosharding/demoSharding_list");
        return modelAndView;
    }

    /**
     * 跳转到添加页面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/toAddDemoSharding")
    public ModelAndView toAddDemoSharding(ModelAndView modelAndView){
        modelAndView.setViewName("modules/demosharding/demoSharding_add");
        return modelAndView;
    }

    /**
     * 跳转到修改页面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/toEditDemoSharding")
    public ModelAndView toEditDemoSharding(ModelAndView modelAndView, @RequestParam("id")java.lang.Long id){
        DemoSharding demoSharding = demoShardingService.queryOne(id);
        modelAndView.addObject("demoSharding", demoSharding);
        modelAndView.setViewName("modules/demosharding/demoSharding_edit");
        return modelAndView;
    }

    /**
     * 获取数据表格
     * @param demoSharding
     * @param pageBean
     * @return
     */
    @RequestMapping("/listDemoShardings")
    public LayuiData listDemoShardings(DemoSharding demoSharding, PageBean pageBean){
        LayuiData layuiData = new LayuiData();
        try {
            demoSharding.setPageBean(pageBean);
            List<DemoSharding> demoShardingList = demoShardingService.queryByFieldsAndPage(demoSharding);
            layuiData.setCode(ResultConstants.SUCCESS.getCode());
            layuiData.setMsg(ResultConstants.SUCCESS.getMsg());
            layuiData.setCount(pageBean.getTotal());
            layuiData.setData(demoShardingList);
            return layuiData;
        } catch (Exception e) {
            e.printStackTrace();
            layuiData.setCode(ResultConstants.SUCCESS.getCode());
            layuiData.setMsg(ResultConstants.FAILED.getMsg());
            return layuiData;
        }
    }


    @RequestMapping(value = "/avg",produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public BigDecimal avgDemo(){
        BigDecimal score = demoShardingService.getAvg();
        return score;
    }

    @RequestMapping(value = "/groupby",produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public List<Map<String,String>> groupby(){
        List<Map<String,String>> res = demoShardingService.getGroupBy();
        return res;
    }

    @RequestMapping(value = "/distinct",produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
    public List<String> distinct(){
        List<String> res = demoShardingService.getDistinct();
        return res;
    }
}