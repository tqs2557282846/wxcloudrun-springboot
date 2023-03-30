package com.tencent.wxcloudrun.model;

import java.math.BigDecimal;
import java.io.Serializable;

/**
 * 商品竞价表(GoodPrice)实体类
 *
 * @author tanqiusheng
 * @since 2023-03-29 10:34:33
 */
public class GoodPrice implements Serializable {
    private static final long serialVersionUID = 518140833312678939L;
    /**
     * 唯一id
     */
    private Long id;
    /**
     * 商品id
     */
    private Long goodId;
    /**
     * 出价
     */
    private BigDecimal price;
    /**
     * 竞价用户唯一标识
     */
    private String openpid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getOpenpid() {
        return openpid;
    }

    public void setOpenpid(String openpid) {
        this.openpid = openpid;
    }

}

