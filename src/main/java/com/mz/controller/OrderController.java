package com.mz.controller;

import com.mz.domain.Cart;
import com.mz.domain.CartItem;
import com.mz.domain.Order;
import com.mz.domain.OrderItem;
import com.mz.domain.User;
import com.mz.service.OrderService;
import com.mz.utils.UUIDUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @author mz
 * @Description：
 * @date 2018/6/27
 * @time 17:01
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService mOrderService;

    @GetMapping("/submit")
    public String submit(HttpSession session, Model model) {

        Order order = order = new Order();
        //设置订单创建时间
        order.setCreateTime(new Date());
        //设置订单编号
        order.setOid(UUIDUtil.getId());
        //设置订单状态: 0 未付款, 1:待开奖, 2:已中奖, 3:已兑奖, 4:未中奖
        order.setState(0);
        //设置订单属于哪个用户
        User user = (User) session.getAttribute("user");
        order.setUid(user.getUid());
        //设置订单总金额
        Cart cart = (Cart) session.getAttribute("cart");
        order.setTotal(cart.getTotal());
        //设置订单中所有订单项
        List<OrderItem> orderItems = new ArrayList<>();
        Collection<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems) {
            // 创建新的订单项
            OrderItem orderItem = new OrderItem();

            orderItem.setItemId(UUIDUtil.getId());
            orderItem.setOid(order.getOid());
            orderItem.setCode("2018020");
            orderItem.setRed(cartItem.getRed());
            orderItem.setBlue(cartItem.getBlue());
            orderItem.setCount(cartItem.getCount());
            //添加订单项到集合中
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        //保存订单
        mOrderService.save(order);
        //存储订单对象到model中
        model.addAttribute("order",order);
        //跳转至订单列表页面
        return "order_detail";
    }


    @GetMapping("/myList")
    public String myList(Model model,HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Order> orders = mOrderService.findByUid(user.getUid());
        model.addAttribute("orders",orders);
        return "order_list";
    }
}
