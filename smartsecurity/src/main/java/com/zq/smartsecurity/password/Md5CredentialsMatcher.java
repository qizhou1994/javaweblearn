package com.zq.smartsecurity.password;

import com.zq.smart_framework.util.CodecUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

/**
 * Author zq
 * Created by CTSIG on 2018/6/12.
 * Email : qizhou1994@126.com
 */
public class Md5CredentialsMatcher implements CredentialsMatcher {
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        //获取从表带提交过来的密码、明文，尚未通过MD5加密
        String submitted = String.valueOf(((UsernamePasswordToken)authenticationToken).getPassword());
        //获取从数据库中存储的密码，已通过MD5加密
        String encrypted = String.valueOf(authenticationInfo.getCredentials());
        System.out.println(" //获取从表带提交过来的密码、明文，尚未通过MD5加密 =" + submitted);
        System.out.println(" //获取从数据库中存储的密码，已通过MD5加密 =" + encrypted);
        System.out.println(" //CodecUtil.encryptMD5(submitted).equals(encrypted) =" + CodecUtil.encryptMD5(submitted).equals(encrypted));
        return CodecUtil.encryptMD5(submitted).equals(encrypted);
    }
}
