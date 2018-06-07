package com.zq.smart_framework.helper;

import com.zq.smart_framework.annotation.Aspect;
import com.zq.smart_framework.annotation.Service;
import com.zq.smart_framework.proxy.AspectProxy;
import com.zq.smart_framework.proxy.Proxy;
import com.zq.smart_framework.proxy.ProxyManager;
import com.zq.smart_framework.proxy.TransactionProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Author zq
 * Created by CTSIG on 2018/5/18.
 * Email : qizhou1994@126.com
 */
public final class AopHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);

    /**
     * 获取代理类及目标类集合的映射关系
     */
    static {

        try {
            Map<Class<?>,Set<Class<?>>> proxyMap = createProxyMap();
            Map<Class<?>,List<Proxy>> targetMap = createTargetMap(proxyMap);
            for( Map.Entry<Class<?>,List<Proxy>> targetEntry : targetMap.entrySet()){
                Class<?> targetClass = targetEntry.getKey();
                List<Proxy> proxyList = targetEntry.getValue();
                Object proxy = ProxyManager.createProxy(targetClass,proxyList);
                //对象放入bean map中
                BeanHelper.setBean(targetClass,proxy);
            }
        } catch (Exception e) {

            LOGGER.error("aop fail : ", e);
        }

    }

    /**
     *
     * @param aspect
     * @return 获取Aspect注解中的注解类
     */
    private static Set<Class<?>> createTargetClassSet(Aspect aspect){
        Set<Class<?>> targetClassSet = new HashSet<>();
        Class<? extends Annotation> annotation = aspect.value();
        if(annotation!=null && !annotation.equals(Aspect.class)){
            targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }
        return targetClassSet;
    }


    /**
     * 获取切面
     * @return 代理类与目标类集合映射关系
     */
    private static Map<Class<?>,Set<Class<?>>> createProxyMap( ){
        Map<Class<?>,Set<Class<?>>> proxyMap = new HashMap<>();
        addAspectProxy(proxyMap);
        addTransactionProxy(proxyMap);
        return proxyMap;
    }

    /**
     * ，
     * @return
     */
    /**
     *
     * @param proxyMap 根据代理类与目标类集合映射关系
     * @return 分析出目标类与代理对象列表的映射关系
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private static Map<Class<?>,List<Proxy>> createTargetMap(Map<Class<?>,Set<Class<?>>> proxyMap) throws IllegalAccessException, InstantiationException {
        Map<Class<?>,List<Proxy>> targetMap = new HashMap<>();

        for(Map.Entry<Class<?>,Set<Class<?>>> proxyEntry : proxyMap.entrySet()){
            Class<?> proxyClass = proxyEntry.getKey();
            Set<Class<?>> targetClassSet = proxyEntry.getValue();
            for(Class<?> targetClass : targetClassSet){
                Proxy proxy = (Proxy) proxyClass.newInstance();
                if(targetMap.containsKey(targetClass)){
                    targetMap.get(targetClass).add(proxy);
                }else {
                    List<Proxy> proxyList = new ArrayList<>();
                    proxyList.add(proxy);
                    targetMap.put(targetClass,proxyList);
                }
            }
        }

        return targetMap;
    }


    /**
     * 添加带aspect注解的代理
     * @param proxyMap
     */
    private static void addAspectProxy(Map<Class<?>,Set<Class<?>>> proxyMap){
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        for(Class<?> proxyClass : proxyClassSet){
            if(proxyClass.isAnnotationPresent(Aspect.class)){
                Aspect aspect = proxyClass.getAnnotation(Aspect.class);
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                proxyMap.put(proxyClass,targetClassSet);
            }
        }
    }

    /**
     * 添加带Transaction事务注解的代理
     * @param proxyMap
     */
    private static void addTransactionProxy(Map<Class<?>,Set<Class<?>>> proxyMap){
        Set<Class<?>> serviceClassSet = ClassHelper.getClassSetByAnnotation(Service.class);
        proxyMap.put(TransactionProxy.class,serviceClassSet);
    }
}
