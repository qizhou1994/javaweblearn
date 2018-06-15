package com.zq.soap_jaxws;


import org.apache.cxf.frontend.ClientProxyFactoryBean;


/**
 * Author zq
 * Created by CTSIG on 2018/6/14.
 * Email : qizhou1994@126.com
 */
public class SimpleClient {

    public static void main(String[] args){
        ClientProxyFactoryBean factory =  new ClientProxyFactoryBean();
        factory.setAddress("http://localhost:8080/ws/soap/hello");
        factory.setServiceClass(HelloService.class);
//        factory.setServiceBean(new HelloServiceImpl());
        HelloService helloService = factory.create(HelloService.class);
        String result = helloService.say("aaaaaaaa");
        System.out.println("soap ws is published  . result = " + result);
    }
}
