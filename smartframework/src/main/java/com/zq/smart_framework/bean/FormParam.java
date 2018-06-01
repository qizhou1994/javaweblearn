package com.zq.smart_framework.bean;

import java.util.List;

/**
 * Author zq
 * Created by CTSIG on 2018/5/31.
 * Email : qizhou1994@126.com
 * 封装表单参数
 */
public class FormParam {

    private String fileName;
    private Object fieldValue;

    public FormParam(String fieldName,Object fieldValue){
        this.fieldValue = fieldValue;
        this.fileName = fieldName;

    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }
}
