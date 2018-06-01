package com.zq.smart_framework.helper;

import com.zq.smart_framework.bean.FileParam;
import com.zq.smart_framework.bean.FormParam;
import com.zq.smart_framework.bean.Param;
import com.zq.smart_framework.util.CollectionUtil;
import com.zq.smart_framework.util.FileUtil;
import com.zq.smart_framework.util.StreamUtil;
import com.zq.smart_framework.util.StringUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author zq
 * Created by CTSIG on 2018/5/31.
 * Email : qizhou1994@126.com
 */
public final class UploadHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadHelper.class);

    /**
     * Apache Commons FileUpload 提供得Servlet 文件上传对象
     */
    private static ServletFileUpload servletFileUpload;

    /**
     * 初始化
     * @param servletContext
     */
    public static void init(ServletContext servletContext){
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        servletFileUpload = new ServletFileUpload(new DiskFileItemFactory(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD,repository));
        int uploadLimit = ConfigHelper.getAppUploadLimit();
        if(uploadLimit != 0){
            servletFileUpload.setFileSizeMax(uploadLimit * 1024 * 1024);
        }
    }

    /**
     * 判断请求是否为 multipart
     */
    public static boolean isMultipart(HttpServletRequest request){
        return ServletFileUpload.isMultipartContent(request);
    }

    /**
     * 创建请求对象
     * @param request
     * @return
     * @throws FileUploadException
     */
    public static Param createParam(HttpServletRequest request)  {
        List<FormParam> formParamList = new ArrayList<>();
        List<FileParam> fileParamList = new ArrayList<>();
        try {
        Map<String,List<FileItem>> fileItemListMap = servletFileUpload.parseParameterMap(request);
        //数据是否为空
        if(CollectionUtil.isNotEmpty(fileItemListMap)){
            //遍历单个数据
            for(Map.Entry<String,List<FileItem>> fileItemListEntry : fileItemListMap.entrySet()){
                String fieldName = fileItemListEntry.getKey();
                List<FileItem> fileItemList = fileItemListEntry.getValue();
                //单个数据列中得数据是否为空
                if(CollectionUtil.isNotEmpty(fileItemList)){
                    for(FileItem fileItem : fileItemList){
                        //判断时表单数据还是文件数据
                        if(fileItem.isFormField()){
                                String fieldValue = fileItem.getString("UTF-8");
                                formParamList.add(new FormParam(fieldName,fieldValue));
                        }else {
                            String fileName = FileUtil.getRealFileName(new String(fileItem.getName().getBytes(),"UTF-8"));
                            if(StringUtil.isNotEmpty(fieldName)){
                                long fileSize = fileItem.getSize();
                                String contentType = fileItem.getContentType();
                                InputStream inputStream = fileItem.getInputStream();
                                fileParamList.add(new FileParam(fieldName,fileName,fileSize,contentType,inputStream));
                            }
                        }
                    }
                }
            }
        }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        //表单数据与文件数据返回
        return new Param(formParamList,fileParamList);
    }

    /**
     * 上传文件
     * @param basePath
     * @param fileParam
     */
    public static void uploadFile(String basePath,FileParam fileParam){
        try {
        if(fileParam!=null){
            String filePath = basePath + fileParam.getFileName();
            //创建文件地址
            FileUtil.createFile(filePath);
            InputStream inputStream = new BufferedInputStream(fileParam.getInputStream());
                OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
            StreamUtil.copyStream(inputStream,outputStream);

        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 批量上传 也就是遍历单个上传
     * @param basePath
     * @param fileParamList
     */
    public static void uploadFile(String basePath,List<FileParam> fileParamList){
        try {
            if(CollectionUtil.isNotEmpty(fileParamList)){
                for(FileParam fileParam : fileParamList){
                    uploadFile(basePath,fileParam);
                }
            }
        }catch (Exception e){
            LOGGER.error("upload file failure" , e);
            throw  new RuntimeException(e);
        }
    }
}
