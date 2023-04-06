package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户信息表(User)实体类
 *
 * @author makejava
 * @since 2023-04-04 13:34:58
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -27779688864317712L;
    /**
     * 微信用户唯一标识
     */
    private String openpid;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createtime;
}

