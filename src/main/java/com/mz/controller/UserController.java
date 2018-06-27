package com.mz.controller;

import com.mz.domain.User;
import com.mz.service.UserService;
import com.mz.utils.BallUtil;
import com.mz.utils.SMSUtil;
import com.mz.utils.UUIDUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @author mz
 * @Description：
 * @date 2018/6/23
 * @time 21:17
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService mUserService;

    @GetMapping("/loginUI")
    public String login() {
        return "login";
    }

    @GetMapping("/registerUI")
    public String show(Model model) {
        //防止出现空指针异常
        model.addAttribute("user",new User());
        return "register";
    }

    @GetMapping("/resetUI")
    public String reset() {
        return "reset";
    }

    /**
     * 发送验证码
     *
     * @param mobile
     * @param session
     * @return
     */
    @GetMapping("/sendSMS")
    @ResponseBody
    public String check(String mobile, HttpSession session) {
        try {
            String code = SMSUtil.sendMsg(mobile, 0);
            //将生成的验证码存入session中
            session.setAttribute("code", code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @PostMapping("/register")
    public String register(User user, String code, HttpSession session,Model model) {
        //从session中取出验证码
        String sessionCode = (String) session.getAttribute("code");
        //比较输入的验证码和生成的验证码，如果不匹配则留在注册页面
        if (!code.equals(sessionCode)) {
            model.addAttribute("msg","验证码错误");
            //数据回填
            model.addAttribute("user",user);
            return "register";
        }
        user.setUid(UUIDUtil.getId());
        user.setHeadimg("/img/icon.jpg");
        user.setMoney(100);
        user.setState(1);
        mUserService.save(user);
        return "redirect:/user/loginUI";
    }

    @PostMapping("/login")
    public String login(HttpSession session, String email, String password,Model model) {
        User user = mUserService.findOne(email, password);
        if (user != null) {
            //保存登录状态
            session.setAttribute("user", user);
            return "redirect:/";
        }
        model.addAttribute("msg","用户名或密码错误");

        return "login";
    }


    @GetMapping("userInfo")
    public String userInfo(HttpSession session,Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            String blue = BallUtil.randomBlue();
            List<String> redList = BallUtil.randomRed();
            model.addAttribute("red",redList);
            model.addAttribute("blue",blue);
            return "userInfo";
        }
        return "login";
    }

    @GetMapping("/restPwd")
    @ResponseBody
    public Model restPwd(String mobile, Model model) {
        try {
            String code = SMSUtil.sendMsg(mobile, 1);
            model.addAttribute("code", code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    @GetMapping("/test")
    public String test() {

        return "test";
    }
}
