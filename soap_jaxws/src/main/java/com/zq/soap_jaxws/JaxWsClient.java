package com.zq.soap_jaxws;


import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;


/**
 * Author zq
 * Created by CTSIG on 2018/6/14.
 * Email : qizhou1994@126.com
 */
public class JaxWsClient {

    public static void main(String[] args){
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setAddress("http://localhost:8080/ws/soap/hello");
        factory.setServiceClass(HelloService.class);
//        factory.setServiceBean(new HelloServiceImpl());
        HelloService helloService = factory.create(HelloService.class);
        String result = helloService.say("helloSay");
        System.out.println("soap ws is published  . result = " + result);
    }
}
