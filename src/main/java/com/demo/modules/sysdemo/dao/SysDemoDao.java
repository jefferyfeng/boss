package com.demo.modules.sysdemo.dao;

import com.demo.modules.sysdemo.entity.SysDemo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 *  SysDemoDao  测试接口
 *
 *  @author fdh
 */
public interface SysDemoDao {
    /**
     * 新增SysDemo
     * @param sysDemo
     */
    void insert(SysDemo sysDemo);

    /**
     * 根据主键 删除SysDemo
     * @param id
     */
    void delete(java.lang.Long id);

    /**
     * 修改SysDemo
     * @param sysDemo
     */
    void update(SysDemo sysDemo);

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
     * 批量删除（逻辑删除）
     * @param ids 操作的ids
     */
    void batchRemove(@Param("ids") Long[] ids);

    /**
     * 批量修改状态
     * @param ids
     * @param status
     */
    void batchModifyStatus(@Param("ids") Long[] ids, @Param("status") Integer status);
}