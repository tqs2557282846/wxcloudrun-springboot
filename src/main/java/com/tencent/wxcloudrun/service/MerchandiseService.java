package com.tencent.wxcloudrun.service;
import com.tencent.wxcloudrun.model.Merchandise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 竞价商品表(Merchandise)表服务接口
 *
 * @author tanqiusheng
 * @since 2023-03-29 13:49:50
 */
public interface MerchandiseService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Merchandise queryById(Long id);

    /**
     * 分页查询
     *
     * @param merchandise 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Merchandise> queryByPage(Merchandise merchandise, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param merchandise 实例对象
     * @return 实例对象
     */
    Merchandise insert(Merchandise merchandise);

    /**
     * 修改数据
     *
     * @param merchandise 实例对象
     * @return 实例对象
     */
    Merchandise update(Merchandise merchandise);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
