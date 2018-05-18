package com.zq.smart_framework.annotation;

import java.lang.annotation.*;

/**
 * Author zq
 * Created by CTSIG on 2018/5/18.
 * Email : qizhou1994@126.com
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    /**
     * 注解
     * @return
     */
    Class<? extends Annotation> value();
}
