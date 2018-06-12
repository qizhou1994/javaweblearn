package com.zq.smartsecurity.realm;

import com.zq.smart_framework.helper.DatabaseHelper;
import com.zq.smartsecurity.SecurityConfig;

import com.zq.smartsecurity.password.Md5CredentialsMatcher;
import org.apache.shiro.realm.jdbc.JdbcRealm;

/**
 * 基于 Smart 的 JDBC Realm（需要提供相关 smart.plugin.security.jdbc.* 配置项）
 *
 * @author huangyong
 * @since 1.0.0
 */
public class SmartJdbcRealm extends JdbcRealm {

    public SmartJdbcRealm() {
        super.setDataSource(DatabaseHelper.getDataSource());
        super.setAuthenticationQuery(SecurityConfig.getJdbcAuthcQuery());
        super.setUserRolesQuery(SecurityConfig.getJdbcRolesQuery());
        super.setPermissionsQuery(SecurityConfig.getJdbcPermissionsQuery());
        super.setPermissionsLookupEnabled(true);
        super.setCredentialsMatcher(new Md5CredentialsMatcher());//使用MD5加密算法
    }
}
