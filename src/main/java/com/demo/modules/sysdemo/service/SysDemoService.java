package com.demo.modules.sysdemo.service;

import com.demo.modules.sysdemo.entity.SysDemo;
import java.util.List;

/**
 *  SysDemoService  测试接口
 *
 *  @author fdh
 */
public interface SysDemoService {
    /**
     * 新增SysDemo
     * @param sysDemo
     */
    void add(SysDemo sysDemo);

    /**
     * 根据主键 删除SysDemo (物理删除)
     * @param id
     */
    void delete(java.lang.Long id);

    /**
     * 根据主键 删除SysDemo (逻辑删除)
     * @param id
     */
    void remove(java.lang.Long id);

    /**
     * 修改SysDemo
     * @param sysDemo
     */
    void modify(SysDemo sysDemo);

    /**
     * 根据主键查询SysDemo
     * @param id
     * @return sysDemo
     */
    SysDemo queryOne(java.lang.Long id);

    /**
     * 根据主键查询SysDemo
     * @return sysDemos
     */
    List<SysDemo> queryAll();

    /**
     * 根据字段查询（如需分页请setPageBean）
     * @param sysDemo
     * @return sysDemos
     */
    List<SysDemo> queryByFieldsAndPage(SysDemo sysDemo);

    /**
     * 批量删除SysDemos
     * @param ids
     */
    void batchRemove(Long[] ids);

    /**
     * 批量修改状态
     * @param ids 修改的ids
     * @param status 修改的状态
     */
    void batchModifyStatus(Long[] ids, Integer status);

}