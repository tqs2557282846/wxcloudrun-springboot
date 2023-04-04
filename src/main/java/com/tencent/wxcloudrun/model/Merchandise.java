package com.tencent.wxcloudrun.model;

import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.io.Serializable;

/**
 * 竞价商品表(Merchandise)实体类
 *
 * @author tanqiusheng
 * @since 2023-03-29 13:49:50
 */
@Data
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

    private String factory;

    private DateTimeLiteralExpression.DateTime CreateTime;

    private DateTimeLiteralExpression.DateTime UpdateTime;


}

