package com.zq.smart_framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Author zq
 * Created by CTSIG on 2018/5/18.
 * Email : qizhou1994@126.com
 */
public abstract class AspectProxy  implements Proxy{

    private static final Logger logger = LoggerFactory.getLogger(AspectProxy.class);

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;

        Class<?> cls = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();
        
        begin();
        try {
            if(intercept(cls,method,params)){
                before(cls,method,params);
                result = proxyChain.doProxyChain();
                after(cls,method,params);
            }else {
                result = proxyChain.doProxyChain();
            }
        } catch (Throwable throwable) {
            logger.error("proxy fail : ",throwable);
            error(cls,method,params,throwable);
            throwable.printStackTrace();
        }finally {
            end();
        }

        return result;
    }

    private boolean intercept(Class<?> cls,Method method,Object[] params) throws Throwable {
        return true;
    }

    public void begin() {
    }

    public void end() {
    }

    public  void before(Class<?> cls,Method method,Object[] params) throws Throwable {

    }

    public  void after(Class<?> cls,Method method,Object[] params) throws Throwable {

    }

    public  void error(Class<?> cls,Method method,Object[] params,Throwable e) throws Throwable {

    }
}
