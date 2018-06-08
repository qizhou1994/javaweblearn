package com.zq.smartsecurity;

import java.util.Set;

/**
 * Author zq
 * Created by CTSIG on 2018/6/7.
 * Email : qizhou1994@126.com
 */
public interface SmartSecurity {

    /**
     *
     * @param username
     * @return
     */
    String getPassword(String username);


    /**
     * 根据用户名获取角色名集合
     * @param username
     * @return
     */
    Set<String> getRoleNameSet(String username);


    /**
     * 根据角色名称获取权限名集合
     * @param roleName
     * @return
     */
    Set<String> getPermissionNameSet(String roleName);

}
