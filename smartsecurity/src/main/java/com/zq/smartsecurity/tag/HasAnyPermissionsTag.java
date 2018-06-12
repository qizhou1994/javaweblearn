package com.zq.smartsecurity.tag;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.PermissionTag;


/**
 * Author zq
 * Created by CTSIG on 2018/6/12.
 * Email : qizhou1994@126.com
 */
public class HasAnyPermissionsTag extends PermissionTag {

    private static final String PERMISSION_NAMES_DELIMITER = ",";

    @Override
    protected boolean showTagBody(String permissionNames) {
        boolean hasAnyPermission = false;
        Subject subject = getSubject();
        if (subject != null) {
            for (String permissionName : permissionNames.split(PERMISSION_NAMES_DELIMITER)) {
                if (subject.isPermitted(permissionName.trim())) {
                    hasAnyPermission = true;
                    break;
                }
            }
        }
        return hasAnyPermission;
    }
}
