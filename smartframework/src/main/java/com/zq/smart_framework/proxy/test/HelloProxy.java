package com.zq.smart_framework.proxy.test;

/**
 * Author zq
 * Created by CTSIG on 2018/5/17.
 * Email : qizhou1994@126.com
 */
public class HelloProxy implements Hello {

    private Hello hello;

    public HelloProxy(){
        hello = new HelloImpl();
    }


    @Override
    public void say(String name) {

        before();
        hello.say(name);
        after();
    }


    private void before(){
        System.out.println("Before");
    }

    private void after(){
        System.out.println("After");
    }
}
