package com.lagou.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author WangHaoCun
 * @since 2020-10-26
 **/
@Controller
public class RouterController {
    public static final String ADMIN = "admin";

    /**
     * 向用户登录页面跳转
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    /**
     * 校验登录信息
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, String username, String password, HttpSession session) {
        //获取用户名和密码后进行判断
        if (ADMIN.equals(username) && ADMIN.equals(password)) {
            //重定向到主页面的跳转方法
            session.setAttribute("USER_SESSION", username);
            return "redirect:resume";
        }
        request.setAttribute("msg", "用户名或密码错误，请重新登录！");

        return "login";
    }

    /**
     * 登陆成功重定向至主页
     */
    @RequestMapping(value = "/resume")
    public String toMain() {
        return "resume";
    }

    /**
     * 退出并重定向至登录页
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        //清除session
        session.invalidate();
        //重定向到登录页面的跳转方法
        return "redirect:login";
    }

}
