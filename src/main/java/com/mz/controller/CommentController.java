package com.mz.controller;

import com.mz.domain.Comment;
import com.mz.domain.User;
import com.mz.service.CommentService;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

import javax.servlet.http.HttpSession;

/**
 * @author mz
 * @Description：
 * @date 2018/6/26
 * @time 18:39
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public String add(String nid, String content, HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute("user");

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setIpaddress(request.getRemoteAddr());
        comment.setNid(nid);
        comment.setTime(new Date().toLocaleString());
        comment.setUsername(user.getUsername());

        System.out.println("comment:"+comment.toString());
        commentService.save(comment);

        return "redirect:/note/detail?nid=" + nid;
    }
}
