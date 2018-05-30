package com.zq.chapter3.service;

import com.zq.chapter3.model.User;

import java.util.List;
import java.util.Map;

/**
 * Author zq
 * Created by CTSIG on 2018/5/28.
 * Email : qizhou1994@126.com
 */
public interface UserService {

    List<User> findUserList();

    User findUser(long id);

    boolean saveUser(Map<String, Object> fieldMap);

    boolean updateUser(long id, Map<String, Object> fieldMap);

    boolean deleteUser(long id);
}
