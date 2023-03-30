package com.tencent.wxcloudrun.model;

import java.io.Serializable;

/**
 * 竞价商品表(Merchandise)实体类
 *
 * @author tanqiusheng
 * @since 2023-03-29 13:49:50
 */
public class Merchandise implements Serializable {
    private static final long serialVersionUID = -93597377850293911L;
    /**
     * 自增唯一id
     */
    private Long id;
    /**
     * 竞价商品名
     */
    private String name;
    /**
     * 商品图片
     */
    private String pictures;
    /**
     * 用户唯一标识
     */
    private String openpid;
    /**
     * 商品状态0删除1出售中2已出售
     */
    private Integer status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getOpenpid() {
        return openpid;
    }

    public void setOpenpid(String openpid) {
        this.openpid = openpid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}

