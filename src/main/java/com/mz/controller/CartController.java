package com.mz.controller;

import com.alibaba.fastjson.JSONArray;
import com.mz.domain.Cart;
import com.mz.domain.CartItem;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

/**
 * @author mz
 * @Description：
 * @date 2018/6/26
 * @time 16:47
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    /**
     * 跳转到选号页面
     * @return
     */
    @GetMapping("/selectUI")
    public String selectUI(String randomOneFlag, Model model) {
        model.addAttribute("randomOneFlag",randomOneFlag);
        return "select";
    }

    /**
     * 跳转到购物车页面
     * @param session
     * @return
     */
    @GetMapping("/cartUI")
    public String showCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart",cart);
        }
        return "cart";
    }

    /**
     * 添加彩票进购物车
     * @param balls
     * @param session
     * @return
     */
    @PostMapping("/addToCart")
    @ResponseBody
    public Map<String,Integer> add(String balls, HttpSession session) {

        List<CartItem> cartItems = JSONArray.parseArray(balls,CartItem.class);
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            session.setAttribute("cart",cart);
        }

        for (CartItem cartItem : cartItems) {
            cartItem.setCount(1);
            cart.add(cartItem);
        }

        int size = cart.getCartItems().size();

        Map<String,Integer> map = new HashMap<>();
        map.put("cartSize",size);
        return map;
    }

    @GetMapping("/clear")
    public String clear(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        cart.clear();
        return "redirect:/cart/cartUI";
    }

    @GetMapping("/delByKey")
    public String delByKey(String key,HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        cart.remove(key);
        return "redirect:/cart/cartUI";
    }
}
