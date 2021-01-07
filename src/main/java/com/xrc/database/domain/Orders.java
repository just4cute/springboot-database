package com.xrc.database.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xierongchang
 */
@Data
@TableName("tb_orders")
public class Orders {
    @TableField("id")
    private String id;
    @TableField("no")
    private String no;
    @TableField("name")
    private String name;

    @TableField("price")
    private BigDecimal price;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;
}
