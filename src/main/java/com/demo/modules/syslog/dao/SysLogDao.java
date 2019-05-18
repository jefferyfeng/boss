package com.demo.modules.syslog.dao;

import com.demo.modules.syslog.entity.SysLog;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 *  SysLogDao  接口
 *
 *  @author fdh
 */
public interface SysLogDao {
    /**
     * 新增SysLog
     * @param sysLog
     */
    void insert(SysLog sysLog);

    /**
     * 根据主键 删除SysLog
     * @param id
     */
    void delete(java.lang.Long id);

    /**
     * 修改SysLog
     * @param sysLog
     */
    void update(SysLog sysLog);

    /**
     * 根据主键查询SysLog
     * @param id
     * @return sysLog
     */
    SysLog queryOne(java.lang.Long id);

    /**
     * 根据主键查询SysLog
     * @return sysLogs
     */
    List<SysLog> queryAll();

    /**
     * 根据字段查询（如需分页请setPageBean）
     * @param sysLog
     * @return sysLogs
     */
    List<SysLog> queryByFieldsAndPage(SysLog sysLog);

    /**
     * 批量删除（逻辑删除）
     * @param ids 操作的ids
     */
    void batchRemove(@Param("ids") Long[] ids);

}