package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.math.BigDecimal;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * 商品竞价表(GoodPrice)实体类
 *
 * @author tanqiusheng
 * @since 2023-03-29 10:34:33
 */
@Data
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

    @TableField(fill = FieldFill.INSERT)
    private Timestamp CreateTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp UpdateTime;

}

