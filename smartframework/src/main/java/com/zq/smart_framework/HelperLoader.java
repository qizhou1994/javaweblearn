package com.zq.smart_framework;

import com.zq.smart_framework.helper.*;
import com.zq.smart_framework.util.ClassUtil;

/**
 * Author zq
 * Created by CTSIG on 2018/5/11.
 * Email : qizhou1994@126.com
 * 加载相应的helper类
 */
public final class HelperLoader {

    public static void init(){
        // AopHelper.class要在   IocHelper.class之前加载，因为需要使用AopHelper获取代理，才可注入
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class

        };

        for(Class<?> cls : classList){
            ClassUtil.loadClass(cls.getName());
        }
    }
}
