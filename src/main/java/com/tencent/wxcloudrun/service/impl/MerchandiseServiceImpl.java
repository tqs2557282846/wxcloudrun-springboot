package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.MerchandiseDao;
import com.tencent.wxcloudrun.model.Merchandise;
import com.tencent.wxcloudrun.service.MerchandiseService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 竞价商品表(Merchandise)表服务实现类
 *
 * @author tanqiusheng
 * @since 2023-03-29 13:49:50
 */
@Service("merchandiseService")
public class MerchandiseServiceImpl implements MerchandiseService {
    @Resource
    private MerchandiseDao merchandiseDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Merchandise queryById(Long id) {
        return this.merchandiseDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param merchandise 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Merchandise> queryByPage(Merchandise merchandise, PageRequest pageRequest) {
        long total = this.merchandiseDao.count(merchandise);
        return new PageImpl<>(this.merchandiseDao.queryAllByLimit(merchandise, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param merchandise 实例对象
     * @return 实例对象
     */
    @Override
    public Merchandise insert(Merchandise merchandise) {
        this.merchandiseDao.insert(merchandise);
        return merchandise;
    }

    /**
     * 修改数据
     *
     * @param merchandise 实例对象
     * @return 实例对象
     */
    @Override
    public Merchandise update(Merchandise merchandise) {
        this.merchandiseDao.update(merchandise);
        return this.queryById(merchandise.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.merchandiseDao.deleteById(id) > 0;
    }
}
