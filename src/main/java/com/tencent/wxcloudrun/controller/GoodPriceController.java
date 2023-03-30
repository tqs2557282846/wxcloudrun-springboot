package com.tencent.wxcloudrun.controller;



import com.tencent.wxcloudrun.model.GoodPrice;
import com.tencent.wxcloudrun.service.GoodPriceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 商品竞价表(GoodPrice)表控制层
 *
 * @author tanqiusheng
 * @since 2023-03-29 10:34:30
 */
@RestController
@RequestMapping("/goodPrice")
public class GoodPriceController {
    /**
     * 服务对象
     */
    @Resource
    private GoodPriceService goodPriceService;

    /**
     * 分页查询
     *
     * @param goodPrice 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping("/page")
    public ResponseEntity<Page<GoodPrice>> queryByPage(GoodPrice goodPrice, PageRequest pageRequest) {
        return ResponseEntity.ok(this.goodPriceService.queryByPage(goodPrice, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public ResponseEntity<GoodPrice> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.goodPriceService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param goodPrice 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResponseEntity<GoodPrice> add(GoodPrice goodPrice) {
        return ResponseEntity.ok(this.goodPriceService.insert(goodPrice));
    }

    /**
     * 编辑数据
     *
     * @param goodPrice 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public ResponseEntity<GoodPrice> edit(GoodPrice goodPrice) {
        return ResponseEntity.ok(this.goodPriceService.update(goodPrice));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.goodPriceService.deleteById(id));
    }

}

