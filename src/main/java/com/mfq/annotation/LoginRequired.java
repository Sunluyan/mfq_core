package com.mfq.annotation;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * Created by xingyongshan on 15/8/4.
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface LoginRequired {
    /**
     * 是否需要登录，默认情况下需要，如果某一个方法不需要可以将此值设置为false
     * @return 是否需要登录
     */
    boolean value() default true;

}
