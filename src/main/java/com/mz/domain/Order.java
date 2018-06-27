package com.mz.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author mz
 * @Description：订单
 * @date 2018/6/27
 * @time 16:59
 */
@Entity
@Table(name = "orders")
@Data
public class Order {

    /**
     * 订单id
     */
    @Id
    private String oid;
    /**
     * 订单创建时间
     */
    private Date createTime;
    /**
     * 订单状态
     */
    private int state;
    /**
     * 订单支付总额
     */
    private double total;
    /**
     * 订单属于哪个用户
     */
    private String uid;
    /**
     * 记录所有的订单项
     */
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "oid")
    private List<OrderItem> orderItems;
}
