package com.zq.smartsecurity.realm;

import com.zq.smart_framework.util.CodecUtil;
import com.zq.smartsecurity.SecurityConstant;
import com.zq.smartsecurity.SmartSecurity;
import com.zq.smartsecurity.password.Md5CredentialsMatcher;
import org.apache.shiro.authc.*;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;


import java.util.HashSet;
import java.util.Set;

/**
 * 基于 Smart 的自定义 Realm（需要实现 SmartSecurity 接口）
 *
 * @author huangyong
 * @since 1.0.0
 */
public class SmartCustomRealm extends AuthorizingRealm {

    private final SmartSecurity smartSecurity;

    public SmartCustomRealm(SmartSecurity smartSecurity) {
        this.smartSecurity = smartSecurity;
        super.setName(SecurityConstant.REALMS_CUSTOM);
        super.setCredentialsMatcher(new Md5CredentialsMatcher());//使用MD5加密算法
    }

    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (token == null) {
            throw new AuthenticationException("parameter token is null");
        }

        //通过AuthenticationToken 对象获取从表单中提交过来的用户名
        String username = ((UsernamePasswordToken) token).getUsername();

        //通过SmartSecurity 接口并根据用户名获取数据库中存放的密码
        String password = smartSecurity.getPassword(username);
        password = CodecUtil.encryptMD5(password);
        //将用户名与密码放入AuthenticationInfo对象中，便于后续的认真操作
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo();
        authenticationInfo.setPrincipals(new SimplePrincipalCollection(username, super.getName()));
        authenticationInfo.setCredentials(password);
        return authenticationInfo;
    }

    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("parameter principals is null");
        }

        //获取已认真用户的用户名
        String username = (String) super.getAvailablePrincipal(principals);

        //通过SmartSecurity接口并根据用户名获取角色集合
        Set<String> roleNameSet = smartSecurity.getRoleNameSet(username);

        //通过SmartSecurity接口并根据角色名获取与其对应的权限名集合
        Set<String> permissionNameSet = new HashSet<String>();
        if (roleNameSet != null && roleNameSet.size() > 0) {
            for (String roleName : roleNameSet) {
                Set<String> currentPermissionNameSet = smartSecurity.getPermissionNameSet(roleName);
                permissionNameSet.addAll(currentPermissionNameSet);
            }
        }

        //将角色名集合与权限名集合放入AuthorizationInfo对象中，便于后续的授权操作
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleNameSet);
        authorizationInfo.setStringPermissions(permissionNameSet);
        return authorizationInfo;
    }
}
