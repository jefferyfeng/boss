package com.demo.modules.demosharding.dao;

import com.demo.modules.demosharding.entity.DemoSharding;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *  DemoShardingDao  接口
 *
 *  @author fdh
 */
public interface DemoShardingDao {
    /**
     * 新增DemoSharding
     * @param demoSharding
     */
    void insert(DemoSharding demoSharding);

    /**
     * 根据主键 删除DemoSharding
     * @param id
     */
    void delete(java.lang.Long id);

    /**
     * 修改DemoSharding
     * @param demoSharding
     */
    void update(DemoSharding demoSharding);

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
     * 批量删除（逻辑删除）
     * @param ids 操作的ids
     */
    void batchRemove(@Param("ids") Long[] ids);

    BigDecimal getAvg();

    List<Map<String,String>> getGroupBy();

    List<String> getDistinct();
}