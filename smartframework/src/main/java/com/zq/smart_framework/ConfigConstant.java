package com.zq.smart_framework;

import com.zq.smart_framework.util.PropsUtil;

import java.util.Properties;

public interface ConfigConstant {

    /*smart.framework.jdbc.driver=com.mysql.jdbc.Driver
    smart.framework.jdbc.url=jdbc:mysql://localhost:3306/demo
    smart.framework.jdbc.username=root
    smart.framework.jdbc.password=root

    smart.framework.app.base_package=com.zq.chapter3
    smart.framework.app.jsp_path=/WEB-INF/view/
    smart.framework.app.asset_path=/asset/*/
 /*   String CONFIG_FILE = "smart.properties";
    Properties conf = PropsUtil.loadProps("smart.properties");
    String driver = conf.getProperty("jdbc.driver");
    String url = conf.getProperty("jdbc.url");
    String username = conf.getProperty("jdbc.username");
    String password = conf.getProperty("jdbc.password");
    String JDBC_DRIVER = driver;
    String JDBC_URL = url;
    String JDBC_USERNAME = username;
    String JDBC_PASSWORD =password;


    String APP_BASE_PACKAGE = "smart.framework.app.base_package";
    String APP_JSP_PATH = "C:\\zq\\privateWork\\javaidea\\smartframework\\src\\main\\webapp\\WEB-INF\\view";
    String APP_ASSET_PATH = "C:\\zq\\privateWork\\javaidea\\smartframework\\src\\main\\webapp\\asset";
    String APP_UPLOAD_LIMIT = "2";

*/

    String CONFIG_FILE = "smart.properties";

    String JDBC_DRIVER = "smart.framework.jdbc.driver";
    String JDBC_URL = "smart.framework.jdbc.url";
    String JDBC_USERNAME = "smart.framework.jdbc.username";
    String JDBC_PASSWORD = "smart.framework.jdbc.password";

    String APP_BASE_PACKAGE = "smart.framework.app.base_package";
    String APP_JSP_PATH = "smart.framework.app.jsp_path";
    String APP_ASSET_PATH = "smart.framework.app.asset_path";
    String APP_UPLOAD_LIMIT = "smart.framework.app.upload_limit";




/*    String CONFIG_FILE = "smart.properties";

    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String JDBC_URL = "jdbc:mysql://localhost:3306/demo";
    String JDBC_USERNAME = "root";
    String JDBC_PASSWORD = "root";

    String APP_BASE_PACKAGE = "C:\\zq\\privateWork\\javaidea\\smartframework\\src\\main\\java\\com\\zq\\smart_framework";
    String APP_JSP_PATH = "C:\\zq\\privateWork\\javaidea\\smartframework\\src\\main\\webapp\\WEB-INF\\view";
    String APP_ASSET_PATH = "C:\\zq\\privateWork\\javaidea\\smartframework\\src\\main\\resources";
    String APP_UPLOAD_LIMIT = "1" ;*/
}
