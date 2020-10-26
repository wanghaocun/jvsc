package com.lagou.demo.controller;

import com.lagou.demo.service.IDemoService;
import com.lagou.edu.mvcframework.annotations.LagouAutowired;
import com.lagou.edu.mvcframework.annotations.LagouController;
import com.lagou.edu.mvcframework.annotations.LagouRequestMapping;
import com.lagou.edu.mvcframework.annotations.LagouSecurity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author WangHaoCun
 * @since 2020-10-25
 **/
@LagouSecurity("wangwu")
@LagouController
@LagouRequestMapping("/demo")
@SuppressWarnings("unused")
public class DemoController {

    @LagouAutowired
    private IDemoService demoService;

    /**
     * URL: /demo/query?name=lisi
     */
    @LagouRequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String name) {
        return demoService.get(name);
    }

    /**
     * 测试权限校验
     * http://localhost:8080/demo/handle01?username=zhangsan
     */
    @LagouSecurity({"zhangsan", "lisi"})
    @LagouRequestMapping("/handler01")
    public String handler01(HttpServletRequest request, HttpServletResponse response, String username) {
        return demoService.get(username);
    }

}
