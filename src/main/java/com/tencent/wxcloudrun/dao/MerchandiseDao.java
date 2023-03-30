package com.tencent.wxcloudrun.dao;
import com.tencent.wxcloudrun.model.Merchandise;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 竞价商品表(Merchandise)表数据库访问层
 *
 * @author tanqiusheng
 * @since 2023-03-29 13:49:48
 */
public interface MerchandiseDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Merchandise queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param merchandise 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Merchandise> queryAllByLimit(Merchandise merchandise, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param merchandise 查询条件
     * @return 总行数
     */
    long count(Merchandise merchandise);

    /**
     * 新增数据
     *
     * @param merchandise 实例对象
     * @return 影响行数
     */
    int insert(Merchandise merchandise);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Merchandise> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Merchandise> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Merchandise> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Merchandise> entities);

    /**
     * 修改数据
     *
     * @param merchandise 实例对象
     * @return 影响行数
     */
    int update(Merchandise merchandise);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

