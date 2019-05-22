package com.demo.modules.demosharding.service;

import com.demo.modules.demosharding.entity.DemoSharding;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *  DemoShardingService  接口
 *
 *  @author fdh
 */
public interface DemoShardingService {
    /**
     * 新增DemoSharding
     * @param demoSharding
     */
    void add(DemoSharding demoSharding);

    /**
     * 根据主键 删除DemoSharding (物理删除)
     * @param id
     */
    void delete(java.lang.Long id);

    /**
     * 根据主键 删除DemoSharding (逻辑删除)
     * @param id
     */
    void remove(java.lang.Long id);

    /**
     * 修改DemoSharding
     * @param demoSharding
     */
    void modify(DemoSharding demoSharding);

    /**
     * 根据主键查询DemoSharding
     * @param id
     * @return demoSharding
     */
    DemoSharding queryOne(java.lang.Long id);

    /**
     * 根据主键查询DemoSharding
     * @return demoShardings
     */
    List<DemoSharding> queryAll();

    /**
     * 根据字段查询（如需分页请setPageBean）
     * @param demoSharding
     * @return demoShardings
     */
    List<DemoSharding> queryByFieldsAndPage(DemoSharding demoSharding);

    /**
     * 批量删除DemoShardings
     * @param ids
     */
    void batchRemove(Long[] ids);

    BigDecimal getAvg();

    List<Map<String,String>> getGroupBy();

    List<String> getDistinct();
}