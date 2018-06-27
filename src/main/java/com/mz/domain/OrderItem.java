package com.mz.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author mz
 * @Description：订单项
 * @date 2018/6/27
 * @time 16:59
 */
@Entity
@Data
public class OrderItem {
    /**
     * 订单项id
     */
    @Id
    private String itemId;
    /**
     * 购买的红球的号码
     */
    private String red;
    /**
     * 购买的蓝球的号码
     */
    private String blue;
    /**
     * 购买的期数
     */
    private String code;
    /**
     * 购买的注数
     */
    private int count;
    /**
     * 属于哪一个订单
     */
    private String oid;
}
