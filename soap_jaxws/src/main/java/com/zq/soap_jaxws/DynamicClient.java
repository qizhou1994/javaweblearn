package com.zq.soap_jaxws;


import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;


/**
 * Author zq
 * Created by CTSIG on 2018/6/14.
 * Email : qizhou1994@126.com
 */
public class DynamicClient {

    public static void main(String[] args){
        DynamicClientFactory factory =  DynamicClientFactory.newInstance();
        Client client = factory.createClient("http://localhost:8080/ws/soap/hello?wsdl");
        try {
            Object[] results = client.invoke("say","word");
            System.out.println("soap ws is published  DynamicClientFactory. result = " + results[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
