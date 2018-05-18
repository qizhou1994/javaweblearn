package com.zq.smart_framework.proxy.test.aop.asp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author zq
 * Created by CTSIG on 2018/5/18.
 * Email : qizhou1994@126.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Tag {
}
