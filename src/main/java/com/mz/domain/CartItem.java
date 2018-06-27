package com.mz.domain;

import lombok.Data;

/**
 * @author mz
 * @Description：商品
 * @date 2018/6/27
 * @time 7:53
 */
@Data
public class CartItem {

    /**
     * 红球
     */
    private String red;

    /**
     * 蓝球
     */
    private String blue;

    /**
     * 购买注数
     */
    private int count;

    /**
     * 单价
     */
    private double price = 2;

    /**
     * 总价
     */
    private double subTotal;

    public double getSubtotal() {
        return count * price;
    }
}
