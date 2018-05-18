package com.zq.smart_framework.proxy;

import com.zq.smart_framework.annotation.Aspect;
import com.zq.smart_framework.annotation.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Author zq
 * Created by CTSIG on 2018/5/18.
 * Email : qizhou1994@126.com
 */
@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    private long begin;

    @Override
    public  void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        LOGGER.debug("------begin-----------");
        LOGGER.debug("-class--- -" + String.format("class: %s",cls.getName()));
        LOGGER.debug("-method---- " + String.format("method: %s",method.getName()));
        begin = System.currentTimeMillis();
    }

    @Override
    public  void after(Class<?> cls,Method method,Object[] params) throws Throwable {
        LOGGER.debug("-time---- " + String.format("time: %dms",System.currentTimeMillis() - begin));
        LOGGER.debug("------after-----------");
    }

}
