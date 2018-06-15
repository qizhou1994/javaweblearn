package com.zq.soap_jaxws;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * Author zq
 * Created by CTSIG on 2018/6/14.
 * Email : qizhou1994@126.com
 */
public class JaxWsServer {

    public static void main(String[] args){
        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        factory.setAddress("http://localhost:8080/ws/soap/hello");
        factory.setServiceClass(HelloService.class);
        factory.setServiceBean(new HelloServiceImpl());
        factory.create();
        System.out.println("soap ws is published ");
    }
}
