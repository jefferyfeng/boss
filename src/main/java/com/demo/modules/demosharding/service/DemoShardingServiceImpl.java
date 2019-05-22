package com.demo.modules.demosharding.service;

import com.demo.modules.demosharding.entity.DemoSharding;
import com.demo.modules.demosharding.dao.DemoShardingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import java.util.Map;

/**
 *  DemoShardingServiceImpl  实现类
 *
 *  @author fdh
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
public class DemoShardingServiceImpl implements DemoShardingService{
    @Autowired
    private DemoShardingDao demoShardingDao;

    /**
     * 新增DemoSharding
     * @param demoSharding
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void add(DemoSharding demoSharding){
        Date date = new Date();
        demoSharding.setCreateDate(date);
        demoSharding.setUpdateDate(date);
        demoSharding.setIsValid(1);
        demoShardingDao.insert(demoSharding);
    }

    /**
     * 根据主键 删除DemoSharding (物理删除)
     * @param id
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void delete(java.lang.Long id){
        demoShardingDao.delete(id);
    }

    /**
     * 根据主键 删除DemoSharding (逻辑删除)
     * @param id
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void remove(java.lang.Long id){
        DemoSharding demoSharding = new DemoSharding();
        demoSharding.setId(id);
        demoSharding.setIsValid(0);
        demoSharding.setUpdateDate(new Date());
        demoShardingDao.update(demoSharding);
    }

    /**
     * 修改DemoSharding
     * @param demoSharding
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void modify(DemoSharding demoSharding){
        demoSharding.setUpdateDate(new Date());
        demoShardingDao.update(demoSharding);
    }

    /**
     * 根据主键查询DemoSharding
     * @param id
     * @return demoSharding
     */
    @Override
    public DemoSharding queryOne(java.lang.Long id){
        return demoShardingDao.queryOne(id);
    }

    /**
     * 根据主键查询DemoSharding
     * @return demoShardings
     */
    @Override
    public List<DemoSharding> queryAll(){
        return demoShardingDao.queryAll();
    }

    /**
     * 根据字段查询（如需分页请setPageBean）
     * @param demoSharding
     * @return demoShardings
     */
    @Override
    public List<DemoSharding> queryByFieldsAndPage(DemoSharding demoSharding){
        return demoShardingDao.queryByFieldsAndPage(demoSharding);
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void batchRemove(Long[] ids) {
        demoShardingDao.batchRemove(ids);
    }

    @Override
    public BigDecimal getAvg() {
        return demoShardingDao.getAvg();
    }

    @Override
    public List<Map<String,String>> getGroupBy() {
        return demoShardingDao.getGroupBy();
    }

    @Override
    public List<String> getDistinct() {
        return demoShardingDao.getDistinct();
    }


}