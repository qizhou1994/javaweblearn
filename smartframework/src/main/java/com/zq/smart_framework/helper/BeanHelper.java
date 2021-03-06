package com.zq.smart_framework.helper;

import com.zq.smart_framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class BeanHelper {

     private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<Class<?>, Object>();

     static{
         System.out.println("开始BeanHelper");
         Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
         for (Class<?> beanClass : beanClassSet){
             System.out.println("开始beanClass= " + beanClass.getName());
             Object obj = ReflectionUtil.newInstance(beanClass);
             BEAN_MAP.put(beanClass,obj);
         }
         System.out.println("结束beanClass= " );
     }


    /**
     * 获取 Bean 映射
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    /**
     * 获取 Bean 实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> cls) {
        if (!BEAN_MAP.containsKey(cls)) {
            throw new RuntimeException("can not get bean by class: " + cls);
        }
        return (T) BEAN_MAP.get(cls);
    }

    /**
     * 设置 Bean 实例
     */
    public static void setBean(Class<?> cls, Object obj) {
        BEAN_MAP.put(cls, obj);
    }
}
