package com.demo.modules.syslog.service;

import com.demo.modules.syslog.entity.SysLog;
import java.util.List;

/**
 *  SysLogService  接口
 *
 *  @author fdh
 */
public interface SysLogService {
    /**
     * 新增SysLog
     * @param sysLog
     */
    void add(SysLog sysLog);

    /**
     * 根据主键 删除SysLog (物理删除)
     * @param id
     */
    void delete(java.lang.Long id);

    /**
     * 根据主键 删除SysLog (逻辑删除)
     * @param id
     */
    void remove(java.lang.Long id);

    /**
     * 修改SysLog
     * @param sysLog
     */
    void modify(SysLog sysLog);

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
     * 批量删除SysLogs
     * @param ids
     */
    void batchRemove(Long[] ids);


}