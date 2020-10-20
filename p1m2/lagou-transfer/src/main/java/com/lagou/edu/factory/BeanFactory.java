package com.lagou.edu.factory;

import com.lagou.edu.annotation.Autowired;
import com.lagou.edu.annotation.Repository;
import com.lagou.edu.annotation.Service;
import com.lagou.edu.annotation.Transactional;
import com.lagou.edu.service.TransferService;
import com.lagou.edu.util.ClassUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义 BeanFactory 实现类似 IOC 容器自动管理 bean 的功能
 *
 * @author WangHaoCun
 * @since 2020-10-21
 */
@SuppressWarnings("deprecation")
public class BeanFactory {

    public static void main(String[] args) {
        ConcurrentHashMap<String, Object> beans = BeanFactory.BEANS;

        TransferService transferService = (TransferService) BeanFactory.getBean("transferService");

        System.out.printf("%s==>%d...%s", beans.toString(), beans.size(), transferService.toString());
    }

    /**
     * 存放使用自定义注解对象的容器
     */
    private static final ConcurrentHashMap<String, Object> BEANS = new ConcurrentHashMap<>();

    /*
     * 使用静态代码块在类加载时即初始化 bean 信息
     */
    static {
        //包路径
        Properties properties = readPropertiesFile();
        assert properties != null;
        final String basePackage = properties.getProperty("base-package");
        //扫描表 加载指定类
        List<Class<?>> classes = ClassUtils.getClasses(basePackage);

        // 存放beans
        if (classes.size() > 0) {

            // 1. 获取 @Service 注解类，并将其实例化
            for (Class<?> clazz : classes) {
                Service annotation = clazz.getDeclaredAnnotation(Service.class);
                if (annotation != null) {
                    String beanId = annotation.value();

                    if ("".equals(beanId.trim())) {
                        String className = clazz.getInterfaces()[0].getSimpleName();
                        beanId = toLowerCaseFirstOne(className);
                    }

                    Object bean = null;
                    try {
                        bean = clazz.newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    assert bean != null;
                    BEANS.put(beanId, bean);
                }
            }

            // 2. 获取 @Repository 注解类，并将其实例化
            for (Class<?> clazz : classes) {
                Repository annotation = clazz.getDeclaredAnnotation(Repository.class);
                if (annotation != null) {
                    String beanId = annotation.value();
                    if ("".equals(beanId.trim())) {
                        String className = clazz.getSimpleName();
                        beanId = toLowerCaseFirstOne(className);
                    }

                    Object bean = null;
                    try {
                        bean = clazz.newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    assert bean != null;
                    BEANS.put(beanId, bean);
                }
            }

            // 3. 获取 @Autowired 注解变量，并将其实例化
            Set<Map.Entry<String, Object>> entrySet = BEANS.entrySet();
            //遍历所有的注解对象
            for (Map.Entry<String, Object> entry : entrySet) {
                //获取注解对象，判断是否有属性的依赖注入
                Object value = entry.getValue();
                try {
                    //使用反射机制获取当前类的所有属性
                    Class<?> clazz = value.getClass();
                    Field[] fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        Autowired autowired = field.getDeclaredAnnotation(Autowired.class);
                        if (autowired != null) {
                            //获取属性名称
                            String name = autowired.value();
                            if ("".equals(name.trim())) {
                                String className = field.getName();
                                name = toLowerCaseFirstOne(className);
                            }
                            Object bean = getBean(name);
                            field.setAccessible(true);
                            //给属性赋值
                            field.set(value, bean);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // 4. 获取 @Transactional 注解对象，并配置其代理对象
            for (Class<?> clazz : classes) {
                Transactional transactional = clazz.getDeclaredAnnotation(Transactional.class);
                if (transactional != null) {
                    Service serviceAnnotation = clazz.getDeclaredAnnotation(Service.class);
                    //获取原对象
                    String beanId = serviceAnnotation.value();
                    if ("".equals(beanId.trim())) {
                        String className = clazz.getInterfaces()[0].getSimpleName();
                        beanId = toLowerCaseFirstOne(className);
                    }
                    //被代理对象
                    Object beanObj = BEANS.get(beanId);
                    //转为代理对象
                    ProxyFactory proxyFactory = (ProxyFactory) BEANS.get("proxyFactory");
                    //代理对象
                    Object proxy = proxyFactory.getJdkProxy(beanObj);
                    //代理对象替换原对象
                    BEANS.put(beanId, proxy);
                }
            }
        }

    }

    /**
     * 根据 beanId 从 map 对象池中获取 bean
     *
     * @param beanId String
     * @return Object
     */
    public static Object getBean(String beanId) {
        return BEANS.get(beanId);
    }

    /**
     * 首字母转小写
     *
     * @param s String
     * @return String
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return Character.toLowerCase(s.charAt(0)) + s.substring(1);
        }
    }

    /**
     * 读取 application.properties 文件
     *
     * @return Properties
     */
    private static Properties readPropertiesFile() {
        Properties pros = new Properties();
        try {
            InputStream resourceAsStream = BeanFactory.class.getClassLoader()
                    .getResourceAsStream("application.properties");

            assert resourceAsStream != null;
            pros.load(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8));

            return pros;
        } catch (Exception e) {
            System.err.println("读取 application.properties 文件失败");
            e.printStackTrace();
        }

        return null;
    }

}

