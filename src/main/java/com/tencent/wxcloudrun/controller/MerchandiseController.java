package com.tencent.wxcloudrun.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tencent.wxcloudrun.Util.HelpUtil;
import com.tencent.wxcloudrun.Util.MapStruct;
import com.tencent.wxcloudrun.dao.GoodPriceDao;
import com.tencent.wxcloudrun.dao.MerchandiseDao;
import com.tencent.wxcloudrun.dto.PageDto;
import com.tencent.wxcloudrun.dto.merchandiseDto;
import com.tencent.wxcloudrun.imageupload.FileUploadController;
import com.tencent.wxcloudrun.imageupload.FileUploadService;
import com.tencent.wxcloudrun.model.GoodPrice;
import com.tencent.wxcloudrun.model.Merchandise;
import com.tencent.wxcloudrun.service.GoodPriceService;
import com.tencent.wxcloudrun.service.MerchandiseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    private static final Logger logger = LoggerFactory.getLogger(MerchandiseController.class);
    /**
     * 服务对象
     */
    @Resource
    private MerchandiseService merchandiseService;
    @Resource
    private GoodPriceService goodPriceService;
    @Resource
    private GoodPriceDao goodPriceDao;
    @Resource
    private FileUploadService fileUploadService;

    private HelpUtil helpUtil = new HelpUtil();
    @GetMapping("/test")
    public String test(){
        return "成功";
    }

    /**
     * 分页查询
     *
     * @param merchandise 筛选条件
     *
     * @return 查询结果
     */
    @PostMapping("/page")
    //map<Merchandise,List<GoodPrice>>
    public ResponseEntity<Map<Merchandise, List<GoodPrice>>> queryByPage(
            Merchandise merchandise,
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
    public ResponseEntity<Map<Merchandise,List<GoodPrice>>> queryById(@PathVariable("id") Long id) {
        Map<Merchandise,List<GoodPrice>> map = new HashMap<>();
        Merchandise merchandise = merchandiseService.queryById(id);
        List<GoodPrice> goodPrices = goodPriceDao.selectList(Wrappers.<GoodPrice>lambdaQuery().eq(GoodPrice::getGoodId,id));
        map.put(merchandise,goodPrices);
        return ResponseEntity.ok(map);
    }

    /**
     * 新增数据
     *
     *
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResponseEntity<Merchandise> add(merchandiseDto m,HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("merchandisePictures");
        String imageUrl =path + fileUploadService.upload(m.getPictures(), path);
        logger.info("文件存储路径：{}", path);
        Merchandise merchandise = MapStruct.INSTANCES.merchandiseDtoToMerchandise(m);
        merchandise.setPictures(imageUrl);
        merchandise.setOpenpid(helpUtil.getOpenPid(merchandise.getOpenpid()));
        return ResponseEntity.ok(this.merchandiseService.insert(merchandise));
    }

    /**
     * 编辑数据
     *
     *
     * @return 编辑结果
     */
    @PutMapping("/update")
    public ResponseEntity<Boolean> edit(@RequestBody merchandiseDto dto,HttpServletRequest request) {
        Merchandise merchandise = MapStruct.INSTANCES.merchandiseDtoToMerchandise(dto);
        if(dto.getPictures() != null){
            String path = request.getSession().getServletContext().getRealPath("merchandisePictures");
            String imageUrl =path + fileUploadService.upload(dto.getPictures(), path);
            logger.info("文件存储路径：{}", path);
            merchandise.setPictures(imageUrl);
        }
        UpdateWrapper<Merchandise> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",merchandise.getId());
        return  ResponseEntity.ok(merchandiseService.update(merchandise,wrapper));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestParam("id") Long id) {
        boolean a = true;
        try{
            Merchandise merchandise = merchandiseService.queryById(id);
            LambdaQueryWrapper<GoodPrice> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(GoodPrice::getGoodId,merchandise.getId());
            this.goodPriceDao.delete(lambdaQueryWrapper);
            this.merchandiseService.removeById(id);
        }catch (Exception e){
            a= false;
            logger.error(e.getMessage());
        }
        return ResponseEntity.ok(a);
    }

}

