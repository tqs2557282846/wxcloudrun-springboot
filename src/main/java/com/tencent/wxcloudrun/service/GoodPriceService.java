package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.model.GoodPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 商品竞价表(GoodPrice)表服务接口
 *
 * @author tanqiusheng
 * @since 2023-03-29 10:34:34
 */
public interface GoodPriceService extends IService<GoodPrice> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GoodPrice queryById(Long id);

    /**
     * 分页查询
     *
     * @param goodPrice 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<GoodPrice> queryByPage(GoodPrice goodPrice, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param goodPrice 实例对象
     * @return 实例对象
     */
    GoodPrice insert(GoodPrice goodPrice);

    /**
     * 修改数据
     *
     * @param goodPrice 实例对象
     * @return 实例对象
     */
    GoodPrice update(GoodPrice goodPrice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
