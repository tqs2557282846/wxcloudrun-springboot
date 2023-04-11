package com.tencent.wxcloudrun.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
@Data
public class merchandiseDto implements Serializable {

    private Long id;
    /**
     * 竞价商品名
     */
    private String name;
    /**
     * 商品图片
     */
    private MultipartFile pictures;
    /**
     * 用户唯一标识
     */
    private String openpid;


    private String factory;
}
