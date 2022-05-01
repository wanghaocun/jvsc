package com.lagou.edu.factory;

import com.lagou.edu.annotation.Autowired;
import com.lagou.edu.annotation.Repository;
import com.lagou.edu.util.TransactionManager;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;

/**
 * 代理对象工厂：生成代理对象的
 *
 * @author 应癫
 */
@Repository(value = "proxyFactory")
public class ProxyFactory {

    /**
     * 通过自定义注解 autowired 实现自动装配
     */
    @Autowired("transactionManager")
    private TransactionManager transactionManager;

    /**
     * 在 BeanFactory 初始化 bean 时，通过反射调用 set 方法传值
     *
     * @param transactionManager TransactionManager
     */
    @SuppressWarnings("unused")
    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /**
     * 使用 jdk 动态代理生成代理对象
     *
     * @param obj 委托对象
     * @return 代理对象
     */
    public Object getJdkProxy(Object obj) {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), (proxy, method, args) -> invoke(obj, method, args));
    }

    /**
     * 使用 cglib 动态代理生成代理对象
     *
     * @param obj 委托对象
     * @return Object
     */
    @SuppressWarnings("unused")
    public Object getCglibProxy(Object obj) {
        return Enhancer.create(obj.getClass(),
                (MethodInterceptor) (o, method, objects, methodProxy) -> invoke(obj, method, objects));
    }

    /**
     * 动态代理通用 invoke 方法
     *
     * @param obj    Object
     * @param method Method
     * @param args   Object[]
     * @return Object
     * @throws SQLException              e
     * @throws IllegalAccessException    e
     * @throws InvocationTargetException e
     */
    private Object invoke(Object obj, Method method, Object[] args)
            throws SQLException, IllegalAccessException, InvocationTargetException {
        Object result;

        try {
            // 开启事务(关闭事务的自动提交)
            transactionManager.beginTransaction();
            result = method.invoke(obj, args);
            // 提交事务
            transactionManager.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 回滚事务
            transactionManager.rollback();
            // 抛出异常便于上层servlet捕获
            throw e;
        }

        return result;
    }

}
