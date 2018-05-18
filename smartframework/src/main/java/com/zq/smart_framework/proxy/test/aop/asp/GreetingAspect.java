package com.zq.smart_framework.proxy.test.aop.asp;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Author zq
 * Created by CTSIG on 2018/5/18.
 * Email : qizhou1994@126.com
 */
@Aspect
@Component
public class GreetingAspect {

    /*@Around("execution(* com.zq.smart_framework.proxy.test.aop.GreetingImpl.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        before();
        Object result = pjp.proceed();
        after();

        return result;
    }*/

    @Around("@annotation(Tag)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        before();
        Object result = pjp.proceed();
        after();

        return result;
    }

    private void before() {
        System.out.println("Before");
    }

    private void after() {
        System.out.println("After");
    }
}
