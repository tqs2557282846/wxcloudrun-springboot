package com.tencent.wxcloudrun.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tencent.wxcloudrun.Util.HelpUtil;
import com.tencent.wxcloudrun.dao.MerchandiseDao;
import com.tencent.wxcloudrun.dto.PageDto;
import com.tencent.wxcloudrun.model.GoodPrice;
import com.tencent.wxcloudrun.model.Merchandise;
import com.tencent.wxcloudrun.model.User;
import com.tencent.wxcloudrun.service.MerchandiseService;
import com.tencent.wxcloudrun.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户信息表(User)表控制层
 *
 * @author makejava
 * @since 2023-04-04 13:34:57
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @Resource
    private MerchandiseDao merchandiseDao;

    HelpUtil helpUtil = new HelpUtil();

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<User>> queryByPage(User user, PageRequest pageRequest) {
        return ResponseEntity.ok(this.userService.queryByPage(user, pageRequest));
    }
    /**
     * 分页查询
     *
     * @param user 筛选条件
     *
     * @return 查询结果
     */
    @PostMapping("/page")
    public ResponseEntity<Map<User, List<Merchandise>>> queryByPage(
            User user,
            @RequestBody PageDto pageDto) {
        Sort.Direction a = null;
        if (pageDto.getArray()==1){
            a=Sort.Direction.DESC;
        }else a=Sort.Direction.ASC;
        if (pageDto.getArrayType()==null){
            pageDto.setArrayType("createTime");
        }

        PageRequest pageRequest=PageRequest.of(pageDto.getPage(),pageDto.getSize(), Sort.by(a,pageDto.getArrayType()));
        //Merchandise merchandise = new Merchandise();pageDto.getArrayType(),
        Map<User, List<Merchandise>> map = new HashMap<>();
        LambdaQueryWrapper<Merchandise> lambdaQueryWrapper;
        for (User u : this.userService.queryByPage(user, pageRequest).getContent()) {
            lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Merchandise::getOpenpid,u.getOpenpid());
            map.put(u,merchandiseDao.selectList(lambdaQueryWrapper));
        }
        return ResponseEntity.ok(map);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<User> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.userService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResponseEntity<User> add(User user) {
        user.setOpenpid(helpUtil.getOpenPid(user.getOpenpid()));
        return ResponseEntity.ok(this.userService.insert(user));
    }

    /**
     * 编辑数据
     *
     * @param user 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<User> edit(User user) {
        return ResponseEntity.ok(this.userService.update(user));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.userService.deleteById(id));
    }

}

