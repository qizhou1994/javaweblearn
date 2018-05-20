package com.zq.smart_framework.test.aop;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Author zq
 * Created by CTSIG on 2018/5/17.
 * Email : qizhou1994@126.com
 */
@Component
public class GreetingThrowAdvice implements ThrowsAdvice {

    public void afterThrowing(Method method,Object[] objects,Object target,Exception e){
        System.out.println("----Throw Exception ---");
        System.out.println(" Target Class : " + target.getClass().getName());
        System.out.println(" Method Name :" + method.getName());
        System.out.println("Exception Message :" + e.getMessage());
        System.out.println("--------- ");

    }
}
