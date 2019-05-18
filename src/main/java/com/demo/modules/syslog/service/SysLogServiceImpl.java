package com.demo.modules.syslog.service;

import com.demo.modules.syslog.entity.SysLog;
import com.demo.modules.syslog.dao.SysLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Date;

/**
 *  SysLogServiceImpl  实现类
 *
 *  @author fdh
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
public class SysLogServiceImpl implements SysLogService{
    @Autowired
    private SysLogDao sysLogDao;

    /**
     * 新增SysLog
     * @param sysLog
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void add(SysLog sysLog){
        Date date = new Date();
        sysLog.setCreateDate(date);
        sysLog.setUpdateDate(date);
        sysLog.setIsValid(1);
        sysLogDao.insert(sysLog);
    }

    /**
     * 根据主键 删除SysLog (物理删除)
     * @param id
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void delete(java.lang.Long id){
        sysLogDao.delete(id);
    }

    /**
     * 根据主键 删除SysLog (逻辑删除)
     * @param id
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void remove(java.lang.Long id){
        SysLog sysLog = new SysLog();
        sysLog.setId(id);
        sysLog.setIsValid(0);
        sysLog.setUpdateDate(new Date());
        sysLogDao.update(sysLog);
    }

    /**
     * 修改SysLog
     * @param sysLog
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void modify(SysLog sysLog){
        sysLog.setUpdateDate(new Date());
        sysLogDao.update(sysLog);
    }

    /**
     * 根据主键查询SysLog
     * @param id
     * @return sysLog
     */
    @Override
    public SysLog queryOne(java.lang.Long id){
        return sysLogDao.queryOne(id);
    }

    /**
     * 根据主键查询SysLog
     * @return sysLogs
     */
    @Override
    public List<SysLog> queryAll(){
        return sysLogDao.queryAll();
    }

    /**
     * 根据字段查询（如需分页请setPageBean）
     * @param sysLog
     * @return sysLogs
     */
    @Override
    public List<SysLog> queryByFieldsAndPage(SysLog sysLog){
        return sysLogDao.queryByFieldsAndPage(sysLog);
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void batchRemove(Long[] ids) {
        sysLogDao.batchRemove(ids);
    }

}