package com.zq.chapter3.service;



import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author zq
 * Created by CTSIG on 2018/5/31.
 * Email : qizhou1994@126.com
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] arg){
     //初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //获取当前用户
        Subject subject = SecurityUtils.getSubject();


        //登录
        UsernamePasswordToken token = new UsernamePasswordToken("shiro","201314");

        try {
            subject.login(token);
        }catch (Exception e){
            logger.debug("登录失败 e= " + e);
            e.printStackTrace();
        }

        logger.info("登录成功 " + subject.getPrincipal());
        //注销
        subject.logout();



    }
}
