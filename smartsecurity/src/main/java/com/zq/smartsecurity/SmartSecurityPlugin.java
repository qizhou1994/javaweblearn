package com.zq.smartsecurity;

import org.apache.shiro.web.env.EnvironmentLoaderListener;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * Author zq
 * Created by CTSIG on 2018/6/7.
 * Email : qizhou1994@126.com
 */
public class SmartSecurityPlugin  implements ServletContainerInitializer {
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {

        //初始化参数
        servletContext.setInitParameter("shiroConfigLocations","classpath:smart-security.ini");
        //注册Listener
        servletContext.addListener(EnvironmentLoaderListener.class);
        //注册filter
        FilterRegistration.Dynamic smartSecurityFilter = servletContext.addFilter("SmartSecurityFilter",SmartSecurityFilter.class);
        smartSecurityFilter.addMappingForUrlPatterns(null,false,"/*");
    }
}
