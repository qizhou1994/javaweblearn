package com.zq.smart_framework;

import com.zq.smart_framework.helper.BeanHelper;
import com.zq.smart_framework.helper.ClassHelper;
import com.zq.smart_framework.helper.ControllerHelper;
import com.zq.smart_framework.helper.IocHelper;
import com.zq.smart_framework.util.ClassUtil;

/**
 * Author zq
 * Created by CTSIG on 2018/5/11.
 * Email : qizhou1994@126.com
 * 加载相应的helper类
 */
public final class HelperLoader {

    public static void init(){
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };

        for(Class<?> cls : classList){
            ClassUtil.loadClass(cls.getName());
        }
    }
}
