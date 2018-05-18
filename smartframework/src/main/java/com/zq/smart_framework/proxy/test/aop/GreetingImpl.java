package com.zq.smart_framework.proxy.test.aop;

import com.zq.smart_framework.proxy.test.aop.asp.Tag;
import org.springframework.stereotype.Component;

/**
 * Author zq
 * Created by CTSIG on 2018/5/17.
 * Email : qizhou1994@126.com
 */
@Component
public class GreetingImpl implements Greeting,Apology {

    @Override
    public void sayHello(String name) {


        System.out.println("Hello = " + name);
//        throw new RuntimeException("Error");
    }

    @Tag
    @Override
    public void saySorry(String name) {
        System.out.println("saySorry = " + name);
    }

    public void goodMorning(String name){
        System.out.println("Good Morning !:" + name);
    }

    public void goodNight(String name){
        System.out.println("Good Night !:" + name);
    }
}
