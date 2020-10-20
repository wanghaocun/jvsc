package com.lagou.edu.util;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author 应癫
 */
public class DruidUtils {

    private DruidUtils(){
    }

    private static final DruidDataSource DRUID_DATA_SOURCE = new DruidDataSource();


    static {
        DRUID_DATA_SOURCE.setDriverClassName("com.mysql.cj.jdbc.Driver");
        DRUID_DATA_SOURCE.setUrl("jdbc:mysql://localhost:3306/bank");
        DRUID_DATA_SOURCE.setUsername("root");
        DRUID_DATA_SOURCE.setPassword("123456");

    }

    public static DruidDataSource getInstance() {
        return DRUID_DATA_SOURCE;
    }

}
