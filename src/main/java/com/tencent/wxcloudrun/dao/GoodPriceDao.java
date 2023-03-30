package com.tencent.wxcloudrun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.wxcloudrun.model.GoodPrice;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品竞价表(GoodPrice)表数据库访问层
 *
 * @author tanqiusheng
 * @since 2023-03-29 10:34:32
 */
public interface GoodPriceDao extends BaseMapper<GoodPrice> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GoodPrice queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param goodPrice 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<GoodPrice> queryAllByLimit(GoodPrice goodPrice, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param goodPrice 查询条件
     * @return 总行数
     */
    long count(GoodPrice goodPrice);

    /**
     * 新增数据
     *
     * @param goodPrice 实例对象
     * @return 影响行数
     */
    int insert(GoodPrice goodPrice);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<GoodPrice> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<GoodPrice> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<GoodPrice> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<GoodPrice> entities);

    /**
     * 修改数据
     *
     * @param goodPrice 实例对象
     * @return 影响行数
     */
    int update(GoodPrice goodPrice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

