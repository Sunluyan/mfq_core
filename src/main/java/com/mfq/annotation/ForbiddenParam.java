package com.mfq.annotation;

import java.lang.annotation.*;

/**
 * Created by xingyongshan on 15/8/4.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ForbiddenParam {
    String forbid();


}
