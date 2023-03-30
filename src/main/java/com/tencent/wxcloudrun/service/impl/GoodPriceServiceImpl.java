package com.tencent.wxcloudrun.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.dao.GoodPriceDao;
import com.tencent.wxcloudrun.model.GoodPrice;
import com.tencent.wxcloudrun.service.GoodPriceService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 商品竞价表(GoodPrice)表服务实现类
 *
 * @author tanqiusheng
 * @since 2023-03-29 10:34:35
 */
@Service("goodPriceService")
public class GoodPriceServiceImpl extends ServiceImpl<GoodPriceDao,GoodPrice> implements GoodPriceService {
    @Resource
    private GoodPriceDao goodPriceDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public GoodPrice queryById(Long id) {
        return this.goodPriceDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param goodPrice 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<GoodPrice> queryByPage(GoodPrice goodPrice, PageRequest pageRequest) {
        long total = this.goodPriceDao.count(goodPrice);
        return new PageImpl<>(this.goodPriceDao.queryAllByLimit(goodPrice, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param goodPrice 实例对象
     * @return 实例对象
     */
    @Override
    public GoodPrice insert(GoodPrice goodPrice) {
        this.goodPriceDao.insert(goodPrice);
        return goodPrice;
    }

    /**
     * 修改数据
     *
     * @param goodPrice 实例对象
     * @return 实例对象
     */
    @Override
    public GoodPrice update(GoodPrice goodPrice) {
        this.goodPriceDao.update(goodPrice);
        return this.queryById(goodPrice.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.goodPriceDao.deleteById(id) > 0;
    }
}
