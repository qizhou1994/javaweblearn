package com.zq.smart_framework.proxy;

/**
 * Author zq
 * Created by CTSIG on 2018/5/18.
 * Email : qizhou1994@126.com
 */
public interface Proxy {

    /**
     * 执行链式代理
     * @param proxyChain
     * @return
     * @throws Throwable
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
