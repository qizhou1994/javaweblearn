package com.zq.chapter5;

import com.zq.smart_framework.helper.DatabaseHelper;
import com.zq.smartsecurity.SmartSecurity;

import java.util.Set;

/**
 * Author zq
 * Created by CTSIG on 2018/6/7.
 * Email : qizhou1994@126.com
 */
public class AppSecurity implements SmartSecurity {

    public String getPassword(String s) {
        String sql = "SELECT password FROM user WHERE username = ?";
        String  psw = DatabaseHelper.query(sql,s);
        return psw;
    }

    public Set<String> getRoleNameSet(String s) {
        String sql = "SELECT r.role_name FROM user u,user_role ur,role r WHERE u.id= ur.user_id AND" +
                " r.id=ur.role_id AND u.username = ?";
          Set<String> roleSet = DatabaseHelper.querySet(sql,s);
        return roleSet;
    }

    public Set<String> getPermissionNameSet(String s) {
        String sql = "SELECT p.permission_name FROM role r,role_permission rp,permission p " +
                "WHERE r.id = rp.role_id AND p.id = rp.permission_id AND r.role_name = ?";
        Set<String> rolePermissionSet = DatabaseHelper.querySet(sql,s);
        return rolePermissionSet;
    }
}
