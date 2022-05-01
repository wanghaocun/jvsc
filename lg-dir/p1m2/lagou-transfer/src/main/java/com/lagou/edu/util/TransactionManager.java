package com.lagou.edu.util;

import com.lagou.edu.annotation.Autowired;
import com.lagou.edu.annotation.Repository;

import java.sql.SQLException;

/**
 * 事务管理器类：负责手动事务的开启、提交、回滚
 *
 * @author 应癫
 */
@Repository(value = "transactionManager")
public class TransactionManager {

    /**
     * 通过自定义注解 autowired 实现自动装配
     */
    @Autowired("connectionUtils")
    private ConnectionUtils connectionUtils;

    /**
     * 在 BeanFactory 初始化 bean 时，通过反射调用 set 方法传值
     *
     * @param connectionUtils ConnectionUtils
     */
    @SuppressWarnings("unused")
    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /**
     * 开启手动事务控制
     */
    public void beginTransaction() throws SQLException {
        connectionUtils.getCurrentThreadConn().setAutoCommit(false);
    }

    /**
     * 提交事务
     */
    public void commit() throws SQLException {
        connectionUtils.getCurrentThreadConn().commit();
    }

    /**
     * 回滚事务
     */
    public void rollback() throws SQLException {
        connectionUtils.getCurrentThreadConn().rollback();
    }

}
