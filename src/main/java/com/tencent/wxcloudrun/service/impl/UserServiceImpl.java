package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.UserDao;
import com.tencent.wxcloudrun.model.User;
import com.tencent.wxcloudrun.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 用户信息表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-04-04 13:35:04
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param openpid 主键
     * @return 实例对象
     */
    @Override
    public User queryById(String openpid) {
        return this.userDao.queryById(openpid);
    }

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<User> queryByPage(User user, PageRequest pageRequest) {
        long total = this.userDao.count(user);
        return new PageImpl<>(this.userDao.queryAllByLimit(user, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getOpenpid());
    }

    /**
     * 通过主键删除数据
     *
     * @param openpid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String openpid) {
        return this.userDao.deleteById(openpid) > 0;
    }
}
