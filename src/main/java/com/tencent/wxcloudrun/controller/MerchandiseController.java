package com.tencent.wxcloudrun.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tencent.wxcloudrun.Util.HelpUtil;
import com.tencent.wxcloudrun.dao.GoodPriceDao;
import com.tencent.wxcloudrun.dto.PageDto;
import com.tencent.wxcloudrun.model.GoodPrice;
import com.tencent.wxcloudrun.model.Merchandise;
import com.tencent.wxcloudrun.service.GoodPriceService;
import com.tencent.wxcloudrun.service.MerchandiseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.io.ObjectStreamClass.lookup;

/**
 * 竞价商品表(Merchandise)表控制层
 *
 * @author tanqiusheng
 * @since 2023-03-29 13:49:46
 */
@RestController
@RequestMapping("/merchandise")
public class MerchandiseController {
    /**
     * 服务对象
     */
    @Resource
    private MerchandiseService merchandiseService;
    @Resource
    private GoodPriceService goodPriceService;
    @Resource
    private GoodPriceDao goodPriceDao;

    private HelpUtil helpUtil = new HelpUtil();
    @GetMapping("/test")
    public String test(){
        return "成功";
    }

    /**
     * 分页查询
     *
     * @param merchandise 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @PostMapping("/page")
    //map<Merchandise,List<GoodPrice>>
    public ResponseEntity<Map<Merchandise, List<GoodPrice>>> queryByPage(
            Merchandise merchandise,
            @RequestBody PageDto pageDto) {
        Sort.Direction a = null;
        if (pageDto.getArray()==0){
            a=Sort.Direction.ASC;
        }
        if (pageDto.getArray()==1){
            a=Sort.Direction.DESC;
        }

        PageRequest pageRequest=PageRequest.of(pageDto.getPage(),pageDto.getSize(), Sort.by(a,"status"));
        //Merchandise merchandise = new Merchandise();pageDto.getArrayType(),
        Map<Merchandise, List<GoodPrice>> map = new HashMap<>();
        LambdaQueryWrapper<GoodPrice> lambdaQueryWrapper;
        for (Merchandise m : this.merchandiseService.queryByPage(merchandise, pageRequest).getContent()) {
            lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(GoodPrice::getGoodId,m.getId());
            map.put(m,goodPriceDao.selectList(lambdaQueryWrapper));
        }
        return ResponseEntity.ok(map);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<Merchandise> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.merchandiseService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param merchandise 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResponseEntity<Merchandise> add(Merchandise merchandise) {
        merchandise.setOpenpid(helpUtil.getOpenPid(merchandise.getOpenpid()));
        return ResponseEntity.ok(this.merchandiseService.insert(merchandise));
    }

    /**
     * 编辑数据
     *
     * @param merchandise 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public ResponseEntity<Merchandise> edit(Merchandise merchandise) {
        return ResponseEntity.ok(this.merchandiseService.update(merchandise));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.merchandiseService.deleteById(id));
    }

}

