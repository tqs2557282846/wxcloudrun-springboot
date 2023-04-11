package com.tencent.wxcloudrun.controller;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tencent.wxcloudrun.Util.HelpUtil;
import com.tencent.wxcloudrun.model.GoodPrice;
import com.tencent.wxcloudrun.service.GoodPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 商品竞价表(GoodPrice)表控制层
 *
 * @author tanqiusheng
 * @since 2023-03-29 10:34:30
 */
@RestController
@RequestMapping("/goodPrice")
public class GoodPriceController {
    private static final Logger logger = LoggerFactory.getLogger(GoodPriceController.class);
    /**
     * 服务对象
     */
    @Resource
    private GoodPriceService goodPriceService;

    private HelpUtil helpUtil = new HelpUtil();



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
    public ResponseEntity<GoodPrice> add(@RequestBody GoodPrice goodPrice) {
        goodPrice.setOpenpid(helpUtil.getOpenPid(goodPrice.getOpenpid()));
        return ResponseEntity.ok(this.goodPriceService.insert(goodPrice));
    }

    /**
     * 编辑数据
     *
     *
     * @return 编辑结果
     */
    @PutMapping("/update")
    public ResponseEntity<Boolean> edit(
            @RequestBody GoodPrice goodPrice
            //@RequestParam("id") Long id, @RequestParam("price") BigDecimal price
    ) {
        /*
        GoodPrice goodPrice = new GoodPrice();
        goodPrice.setId(id);
        goodPrice.setPrice(price);

         */
        boolean a = false;
        try{
            a = this.goodPriceService.updateById(goodPrice);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return ResponseEntity.ok(a);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestParam("id") Long id) {
        boolean a = false;
        try{
            a = this.goodPriceService.deleteById(id);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return ResponseEntity.ok(a);
    }

}

