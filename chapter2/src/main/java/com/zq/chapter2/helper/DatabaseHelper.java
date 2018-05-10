package com.zq.chapter2.helper;

import com.zq.chapter2.util.CollectionUtil;
import com.zq.chapter2.util.PropsUtil;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public final class DatabaseHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);




    /**
     * 确保一个线程只有以俄格connection 且确保线程安全
     */
    private static final ThreadLocal<Connection> CONNECTION_HOLEDR = new ThreadLocal<Connection>();

    /**
     * DBUtil 优化赋值实体类
     */
    private static final QueryRunner QUERY_RUNNER = new QueryRunner();


    private static final BasicDataSource DATA_SOURCE;

    /**
     * 获取数据库登录信息
     * 连接数据库驱动
     */

    static {
        Properties conf = PropsUtil.loadProps("config.properties");
        String driver = conf.getProperty("jdbc.driver");
        String url = conf.getProperty("jdbc.url");
        String username = conf.getProperty("jdbc.username");
        String password = conf.getProperty("jdbc.password");



        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(driver);
        DATA_SOURCE.setUrl(url);
        DATA_SOURCE.setUsername(username);
        DATA_SOURCE.setPassword(password);


    }





    /**
     * 连接数据库
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = CONNECTION_HOLEDR.get();
        if(conn == null) {
            try {
                conn = DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
                LOGGER.error("conn jdbc fail", e);
            } finally{
                CONNECTION_HOLEDR.set(conn);
        }
    }
        return conn;
    }

    /**
     * 关闭数据库
     *
     * @return
     */
    public static void closeConnection() {
        Connection conn = CONNECTION_HOLEDR.get();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                LOGGER.error("close connection fail", e);
            }finally {
                CONNECTION_HOLEDR.remove();
            }
        }
    }



    /**
     * 查询实体列表
     *
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
        List<T> entityList;
        try {
            Connection conn = getConnection();
            entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass), params);
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("query entity list fail", e);
            throw new RuntimeException();
        } /*finally {
            closeConnection();
        }*/
        return entityList;
    }


    /**
     * 查询实体
     * new BeanHandler - 决定返回的实体类型 集合还是单个对象
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
        T entity;
        try {
            Connection conn = getConnection();
            entity = QUERY_RUNNER.query(conn, sql, new BeanHandler<>(entityClass), params);
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("query entity list fail", e);
            throw new RuntimeException();
        } /*finally {
            closeConnection();
        }*/
        return entity;
    }


    /**
     * 查询语句
     * @param sql
     * @param params
     * @return
     */
    public static  List<Map<String,Object>> executeQuery(String sql, Object... params) {
        List<Map<String,Object>> mapList;
        try {
            Connection conn = getConnection();
            mapList = QUERY_RUNNER.query(conn, sql, new MapListHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("query entity list fail", e);
            throw new RuntimeException();
        }/* finally {
            closeConnection();
        }*/
        return mapList;
    }


    /**
     * 更新数据
     * @param sql
     * @param params
     * @return  更新的条数
     */
    public static  int executeUpdate(String sql, Object... params) {
        int updateNum;
        try {
            Connection conn = getConnection();
            updateNum = QUERY_RUNNER.update(conn, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("query entity list fail", e);
            throw new RuntimeException();
        } /*finally {
            closeConnection();
        }*/
        return updateNum;
    }


    /**
     * 执行sql文件语句
     * @param filePath
     */
    public static  void executeSqlFile(String filePath) {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);

        BufferedReader bReader = new BufferedReader(new InputStreamReader(is));
        String sql;
        try {
            while ((sql = bReader.readLine())!= null){
                DatabaseHelper.executeUpdate(sql);
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("excute sql fail",e);
        }

    }

    /**
     * 插入实体
     * @param entityClass
     * @param fieldMap
     * @param <T>
     * @return
     */
    public static  <T> boolean insertEntity(Class<T>entityClass,Map<String,Object> fieldMap) {

        if(CollectionUtil.isEmpty(fieldMap)){
            LOGGER.error("can not insert entity: fieldMap is empty");
            return false;
        }
        String sql = "INSERT INTO " + getTableName(entityClass);

        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for (String fieldName : fieldMap.keySet()){
            columns.append(fieldName).append(", ");
            values.append("?, ");
        }

        columns.replace(columns.lastIndexOf(", "),columns.length(),")");
        values.replace(values.lastIndexOf(", "),values.length(),")");

        sql += columns + " VALUES " + values;
        System.out.println("sql = " + sql);
        Object[] params = fieldMap.values().toArray();
        return executeUpdate(sql,params) == 1;
    }


    /**
     * 更新实体类
     * @param entityClass
     * @param id
     * @param fieldMap
     * @param <T>
     * @return
     */
    public static <T> boolean updateEntity(Class<T>entityClass,long id,Map<String,Object> fieldMap) {

        if(CollectionUtil.isEmpty(fieldMap)){
            LOGGER.error("can not update entity: fieldMap is empty");
            return false;
        }
        String sql = "UPDATE " + getTableName(entityClass) + " SET ";

        StringBuilder columns = new StringBuilder();
        for (String fieldName : fieldMap.keySet()){
            columns.append(fieldName).append(" = ?, ");
        }

        sql += columns.substring(0,columns.lastIndexOf(", ")) + " WHERE id = ?";

        List<Object> paramList = new ArrayList<>();
        paramList.addAll(fieldMap.values());
        paramList.add(id);
        Object[] params = paramList.toArray();

        return executeUpdate(sql,params) == 1;
    }


    /**
     * 删除实体
     * @param entityClass
     * @param id
     * @param <T>
     * @return
     */
    public static <T> boolean deteleEntity(Class<T> entityClass,long id){
        String sql = "DELETE FROM " + getTableName(entityClass) + " WHERE id=?";
        return executeUpdate(sql,id) == 1;
    }


    /**
     * 得到表名
     * @param entityClass
     * @param <T>
     * @return
     */
    private static <T> String getTableName(Class<T> entityClass) {
        return  entityClass.getSimpleName();
    }


}
