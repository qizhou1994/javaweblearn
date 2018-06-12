package com.zq.chapter5.controller;


import com.zq.smart_framework.annotation.Action;
import com.zq.smart_framework.annotation.Controller;
import com.zq.smart_framework.bean.Param;
import com.zq.smart_framework.bean.View;
import com.zq.smartsecurity.SecurityHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author zq
 * Created by CTSIG on 2018/6/7.
 * Email : qizhou1994@126.com
 */
@Controller
public class SystemController {

    private static final Logger logger = LoggerFactory.getLogger(SystemController.class);

    /**
     * 进入首页界面
     * @return
     */
    @Action("get:/")
    public View index(){
        return new View("index.jsp");
    }

    /**
     * 进入登录界面
     * @return
     */
    @Action("get:/login")
    public View login(){
        return new View("login.jsp");
    }

    /**
     * 提交登录表单
     */
    @Action("post:/login")
    public View loginSubmit(Param param){
        String username = param.getString("username");
        String password = param.getString("password");
        System.out.println("user = " + username + ",psw = " + password);
        try {
            SecurityHelper.login(username,password);
        }catch (Exception e){
            logger.error("login failure",e);
            return new View("/login");
        }
        return new View("/customer");
    }

    /**
     * 提交注销请求
     */
    @Action("get:/logout")
    public View logout(){
        SecurityHelper.logout();
        return new View("/");
    }

}
