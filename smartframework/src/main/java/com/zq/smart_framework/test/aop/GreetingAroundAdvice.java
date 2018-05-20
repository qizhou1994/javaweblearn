package com.zq.smart_framework.test.aop;


import com.zq.smart_framework.annotation.Service;
import net.sf.cglib.proxy.MethodProxy;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Author zq
 * Created by CTSIG on 2018/5/17.
 * Email : qizhou1994@126.com
 */
@Component
public class GreetingAroundAdvice implements MethodInterceptor {



    private void before() {
        System.out.println("Before");
    }

    private void after() {
        System.out.println("After");
    }



    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        before();
        Object result = methodInvocation.proceed();
        after();
        return result;
    }
}
