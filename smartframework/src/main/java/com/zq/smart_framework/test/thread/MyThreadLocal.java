package com.zq.smart_framework.test.thread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zq on 2018/5/20.
 */
public class MyThreadLocal<T> {

    private Map<Thread,T> container = Collections.synchronizedMap(new HashMap<Thread,T>());

    public void set(T value){
        container.put(Thread.currentThread(),value);
    }

    public T get(){
        Thread thread = Thread.currentThread();
        T value = container.get(thread);
        if(value == null && ! container.containsKey(thread)){
            value = initialValue();
            container.put(thread,value);
        }
        return value;
    }

    public void remove(){
        container.remove(Thread.currentThread());
    }

    /**
     * 初始化
     * @return
     */
    protected T initialValue() {
        return  null;
    }
}
