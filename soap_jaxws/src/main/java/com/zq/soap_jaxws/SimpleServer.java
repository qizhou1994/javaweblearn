package com.zq.soap_jaxws;


import org.apache.cxf.frontend.ServerFactoryBean;

/**
 * Author zq
 * Created by CTSIG on 2018/6/14.
 * Email : qizhou1994@126.com
 */
public class SimpleServer {
    public static void main(String[] args){
        ServerFactoryBean factory = new ServerFactoryBean();
        factory.setAddress("http://localhost:8080/ws/soap/hello");
        factory.setServiceClass(HelloService.class);
        factory.setServiceBean(new HelloServiceImpl());
        factory.create();
        System.out.println("soap ws is published ");
    }
}
