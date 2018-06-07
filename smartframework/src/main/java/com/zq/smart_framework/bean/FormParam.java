package com.zq.smart_framework.bean;

import java.util.List;

/**
 * Author zq
 * Created by CTSIG on 2018/5/31.
 * Email : qizhou1994@126.com
 * 封装表单参数
 */
public class FormParam {

    private String fieldName;
    private Object fieldValue;

    public FormParam(String fieldName,Object fieldValue){
        this.fieldValue = fieldValue;
        this.fieldName = fieldName;

    }


    public String getFieldName() {
        return fieldName;
    }


    public Object getFieldValue() {
        return fieldValue;
    }

}
