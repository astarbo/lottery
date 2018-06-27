package com.mz.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mz
 * @Description：购物车
 * @date 2018/6/27
 * @time 7:53
 */
public class Cart {
    private Map<String, CartItem> map = new HashMap<>();

    /**
     * 新增商品
     *
     * @param cartItem
     */
    public void add(CartItem cartItem) {
        //将红球和蓝球的号码拼接作为唯一的key
        String key = cartItem.getRed() + "-" + cartItem.getBlue();
        //如果集合中存在商品
        if (map.containsKey(key)) {
            //通过key获得商品对象
            CartItem oldCartItem = map.get(key);
            //彩票注数＋1
            int count = oldCartItem.getCount() + 1;
            oldCartItem.setCount(count);
            //重新将商品添加进map集合
            map.put(key, oldCartItem);
        } else {
            //否则直接将商品加入集合
            map.put(key, cartItem);
        }
    }

    /**
     * 根据key删除彩票
     * @param key
     */
    public void remove(String key) {
        map.remove(key);
    }


    /**
     * 清空购物车
     */
    public void clear() {
        map.clear();
    }

    /**
     * 获取彩票总金额
     * @return
     */
    public double getTotal() {
        double total = 0;
        for (String key : map.keySet()) {
            CartItem cartItem = map.get(key);
            total += cartItem.getSubtotal();
        }
        return total;
    }

    /**
     * 获取map中所有的cartItem
     * @return
     */
    public Collection<CartItem> getCartItems() {
        Collection<CartItem> values = map.values();
        return values;
    }
}
