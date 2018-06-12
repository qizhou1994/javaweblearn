package com.zq.smartsecurity.tag;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.PermissionTag;



/**
 * Author zq
 * Created by CTSIG on 2018/6/12.
 * Email : qizhou1994@126.com
 */
public class HasAllPermissionsTag extends PermissionTag {

    private static final String PERMISSION_NAMES_DELIMITER = ",";

    @Override
    protected boolean showTagBody(String permNames) {
        boolean hasAllPermission = false;
        Subject subject = getSubject();
        if (subject != null) {
            if (subject.isPermittedAll(permNames.split(PERMISSION_NAMES_DELIMITER))) {
                hasAllPermission = true;
            }
        }
        return hasAllPermission;
    }
}
