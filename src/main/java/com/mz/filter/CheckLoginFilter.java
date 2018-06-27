package com.mz.filter;

import com.mz.domain.User;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mz
 * @Description：
 * @date 2018/6/26
 * @time 20:26
 */
@WebFilter(urlPatterns = {"/note/publish", "/comment/add","/order/*"})
public class CheckLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        User user = (User) req.getSession().getAttribute("user");

        if (user == null) {
            res.sendRedirect("/user/loginUI");
        } else {
            chain.doFilter(req, res);
        }

    }

    @Override
    public void destroy() {

    }
}
