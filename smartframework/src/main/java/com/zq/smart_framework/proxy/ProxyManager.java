package com.zq.smart_framework.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 代理管理器
 * Author zq
 * Created by CTSIG on 2018/5/18.
 * Email : qizhou1994@126.com
 */
public class ProxyManager {

    public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList){
        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

                return new ProxyChain(targetClass,o,method,methodProxy,objects,proxyList);
            }
        });
    }
}
