package com.zq.smart_framework.test.aop;



import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * Author zq
 * Created by CTSIG on 2018/5/17.
 * Email : qizhou1994@126.com
 */
public class GreetingAfterAdvice implements AfterReturningAdvice {


  @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("After");
    }
}
