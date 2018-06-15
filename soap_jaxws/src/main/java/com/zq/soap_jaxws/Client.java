package com.zq.soap_jaxws;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Author zq
 * Created by CTSIG on 2018/6/15.
 * Email : qizhou1994@126.com
 */
public class Client {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-client.xml");

//        factory.setServiceBean(new HelloServiceImpl());
        HelloService helloService = context.getBean("helloService",HelloService.class);
        String result = helloService.say("helloSay");
        System.out.println("  . result = " + result);
    }
}
