package com.zq.smart_framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Author zq
 * Created by CTSIG on 2018/5/11.
 * Email : qizhou1994@126.com
 *
 * 返回视图对象
 */
public class View {

    /**
     * 返回视图路径
     */
    private String path;


    /**
     * 模型数据
     */
    private Map<String,Object> model;

    private View(String path){
        this.path = path;
        model = new HashMap<String, Object>();
    }

    public View addModel(String key,Object value){
        model.put(key,value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}