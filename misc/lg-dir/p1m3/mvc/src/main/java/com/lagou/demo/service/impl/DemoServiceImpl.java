package com.lagou.demo.service.impl;

import com.lagou.demo.service.IDemoService;
import com.lagou.edu.mvcframework.annotations.LagouService;

/**
 * @author WangHaoCun
 * @since 2020-10-25
 **/
@SuppressWarnings("unused")
@LagouService("demoService")
public class DemoServiceImpl implements IDemoService {

    @Override
    public String get(String name) {
        System.out.println("service 实现类中的name参数：" + name) ;
        return name;
    }

}
