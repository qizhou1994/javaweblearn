package com.zq.smart_framework.proxy.test;


import com.zq.smart_framework.helper.AopHelper;
import com.zq.smart_framework.proxy.test.aop.Apology;
import com.zq.smart_framework.proxy.test.aop.Greeting;
import com.zq.smart_framework.proxy.test.aop.GreetingImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Author zq
 * Created by CTSIG on 2018/5/17.
 * Email : qizhou1994@126.com
 */
public class Main {

    public  static void main(String[] args){

   /*     ProxyFactory proxyFactory = new ProxyFactory();//创建代理工厂
        proxyFactory.setTarget(new HelloImpl());//射入目标类对象
        proxyFactory.addAdvice(new GreetingBeforeAdvice());//添加前置增强
        proxyFactory.addAdvice(new GreetingAfterAdvice());//添加后置增强

        Hello hello = (Hello) proxyFactory.getProxy();//从代理工厂中获取代理

        hello.say("MC");*/

       /* ApplicationContext context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/classes/applicationContext.xml");;//获取spring context

        Greeting  greeting = (Greeting) context.getBean(Greeting.class);

        greeting.sayHello("aaaa");

        Apology apology = (Apology) greeting;

        apology.saySorry("aaaaaaaaaaaaaa");
*/


    }
}
