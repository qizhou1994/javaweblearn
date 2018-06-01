package com.zq.smart_framework.bean;

import com.zq.smart_framework.util.CastUtil;
import com.zq.smart_framework.util.CollectionUtil;
import com.zq.smart_framework.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author zq
 * Created by CTSIG on 2018/5/11.
 * Email : qizhou1994@126.com
 *
 * 所有请求参数对象
 */
public class Param {




    private List<FormParam> formParamList;
    private List<FileParam> fileParamList;


    public Param(List<FormParam> formParamList){

        this.formParamList = formParamList;
    }

    public Param(List<FormParam> formParamList,List<FileParam> fileParamList){

        this.formParamList = formParamList;
        this.fileParamList = fileParamList;
    }


    /**
     * 获取请求参数映射
     * @return
     */
    public Map<String,Object> getFieldMap(){
        Map<String,Object> fileMap = new HashMap<>();
        if(CollectionUtil.isNotEmpty(formParamList)){
            for (FormParam formParam: formParamList){
                String fieldName = formParam.getFileName();
                Object fieldValue = formParam.getFieldValue();
                if(fileMap.containsKey(fieldName)){
                    fieldValue = fileMap.get(fieldName) + StringUtil.SEPARATOR + fieldValue;
                }
                fileMap.put(fieldName,fieldValue);
            }
        }
        return fileMap;
    }


    /**
     * 获取上传文件映射
     * @return
     */
    public Map<String,List<FileParam>> getFileMap(){
        Map<String,List<FileParam>> fileMap = new HashMap<>();
        if(CollectionUtil.isNotEmpty(fileParamList)){
            for (FileParam fileParam: fileParamList){
                String fieldName = fileParam.getFileName();
                List<FileParam> fileParamList;
                if(fileMap.containsKey(fieldName)){
                    fileParamList  = fileMap.get(fieldName);
                }else {
                    fileParamList = new ArrayList<>();
                }
                fileParamList.add(fileParam);
                fileMap.put(fieldName,fileParamList);
            }
        }
        return fileMap;
    }


    /**
     * 获取所有上传文件
     * @param fieldName
     * @return
     */
    public List<FileParam> getFileList(String fieldName){
        return getFileMap().get(fieldName);
    }

    public  FileParam  getFile (String fieldName){
        List<FileParam> fileParamList = getFileList(fieldName);
        if(CollectionUtil.isNotEmpty(fileParamList)&& fileParamList.size() == 1){
            return fileParamList.get(0);
        }
        return null;
    }



 /*   private Map<String,Object> paramMap;

    public Param(Map<String,Object> paramMap){
        this.paramMap = paramMap;
    }
    *//**
     * 获取所有字段信息
     * @return
     *//*
    public Map<String, Object> getParamMap() {
        return paramMap;
    }*/

    /**
     * 验证参数是否为空
     */
    public boolean isEmpty() {
        return CollectionUtil.isEmpty(fileParamList) &&CollectionUtil.isEmpty(formParamList);
    }

    /**
     * 根据参数名获取 String 型参数值
     */
    public String getString(String name) {
        return CastUtil.castString(getFieldMap().get(name));
    }

    /**
     * 根据参数名获取 double 型参数值
     */
    public double getDouble(String name) {
        return CastUtil.castDouble(getFieldMap().get(name));
    }

    /**
     * 根据参数名获取 long 型参数值
     */
    public long getLong(String name) {
        return CastUtil.castLong(getFieldMap().get(name));
    }

    /**
     * 根据参数名获取 int 型参数值
     */
    public int getInt(String name) {
        return CastUtil.castInt(getFieldMap().get(name));
    }

    /**
     * 根据参数名获取 boolean 型参数值
     */
    public boolean getBoolean(String name) {
        return CastUtil.castBoolean(getFieldMap().get(name));
    }


}
