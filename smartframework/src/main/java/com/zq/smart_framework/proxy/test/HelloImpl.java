package com.zq.smart_framework.proxy.test;

/**
 * Author zq
 * Created by CTSIG on 2018/5/17.
 * Email : qizhou1994@126.com
 */
public class HelloImpl implements Hello {
    public void say(String name) {
        System.out.println("hello" + name);
    }
}
