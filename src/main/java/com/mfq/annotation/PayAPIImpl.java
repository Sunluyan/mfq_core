package com.mfq.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.mfq.payment.PayAPIType;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PayAPIImpl {
    PayAPIType payAPIType();
}