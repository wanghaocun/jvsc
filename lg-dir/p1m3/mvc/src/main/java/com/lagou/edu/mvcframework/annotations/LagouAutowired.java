package com.lagou.edu.mvcframework.annotations;

import java.lang.annotation.*;

/**
 * @author WangHaoCun
 * @since 2020-10-25
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LagouAutowired {
    String value() default "";
}
