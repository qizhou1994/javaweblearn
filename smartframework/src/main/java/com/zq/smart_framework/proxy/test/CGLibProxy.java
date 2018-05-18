package com.zq.smart_framework.proxy.test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Author zq
 * Created by CTSIG on 2018/5/17.
 * Email : qizhou1994@126.com
 */
public class CGLibProxy implements MethodInterceptor {


    private static CGLibProxy cgLibProxy;

    public static synchronized CGLibProxy getCgLibProxy() {
        if (cgLibProxy == null) {
            synchronized (CGLibProxy.class) {
                cgLibProxy = new CGLibProxy();
            }
        }
        return cgLibProxy;
    }

    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        before();
        o = methodProxy.invokeSuper(o, objects);
        after();
        return o;
    }


    private void before() {
        System.out.println("Before");
    }

    private void after() {
        System.out.println("After");
    }
}
