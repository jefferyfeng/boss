package com.demo.modules.sysdemo.service;

import com.demo.modules.sysdemo.entity.SysDemo;
import com.demo.modules.sysdemo.dao.SysDemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Date;

/**
 *  SysDemoServiceImpl  测试实现类
 *
 *  @author fdh
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
public class SysDemoServiceImpl implements SysDemoService{
    @Autowired
    private SysDemoDao sysDemoDao;

    /**
     * 新增SysDemo
     * @param sysDemo
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void add(SysDemo sysDemo){
        Date date = new Date();
        sysDemo.setCreateDate(date);
        sysDemo.setUpdateDate(date);
        sysDemo.setIsValid(1);
        sysDemoDao.insert(sysDemo);
    }

    /**
     * 根据主键 删除SysDemo (物理删除)
     * @param id
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void delete(java.lang.Long id){
        sysDemoDao.delete(id);
    }

    /**
     * 根据主键 删除SysDemo (逻辑删除)
     * @param id
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void remove(java.lang.Long id){
        SysDemo sysDemo = new SysDemo();
        sysDemo.setId(id);
        sysDemo.setIsValid(0);
        sysDemo.setUpdateDate(new Date());
        sysDemoDao.update(sysDemo);
    }

    /**
     * 修改SysDemo
     * @param sysDemo
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void modify(SysDemo sysDemo){
        sysDemo.setUpdateDate(new Date());
        sysDemoDao.update(sysDemo);
    }

    /**
     * 根据主键查询SysDemo
     * @param id
     * @return sysDemo
     */
    @Override
    public SysDemo queryOne(java.lang.Long id){
        return sysDemoDao.queryOne(id);
    }

    /**
     * 根据主键查询SysDemo
     * @return sysDemos
     */
    @Override
    public List<SysDemo> queryAll(){
        return sysDemoDao.queryAll();
    }

    /**
     * 根据字段查询（如需分页请setPageBean）
     * @param sysDemo
     * @return sysDemos
     */
    @Override
    public List<SysDemo> queryByFieldsAndPage(SysDemo sysDemo){
        return sysDemoDao.queryByFieldsAndPage(sysDemo);
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void batchRemove(Long[] ids) {
        sysDemoDao.batchRemove(ids);
    }

    /**
     * 批量修改状态
     * @param ids 修改的ids
     * @param status 修改的状态
     */
    @Override
    public void batchModifyStatus(Long[] ids, Integer status) {
        sysDemoDao.batchModifyStatus(ids,status);
    }
}