package com.zq.chapter3.controller;




import java.util.List;
import java.util.Map;

/**
 * Author zq
 * Created by CTSIG on 2018/5/28.
 * Email : qizhou1994@126.com
 */
//@Action
public class UserAction {
 /*   @Inject
    private UserService userService;

    @Request.Get("/users")
    public View index() {
        List<User> userList = userService.findUserList();
        DataContext.Request.put("userList", userList);
        return new View("customer.jsp");
    }

    @Request.Get("/user")
    public View create() {
        return new View("customer_create.jsp");
    }

    @Request.Post("/user")
    public Result save(Params params) {
        Map<String, Object> fieldMap = params.getFieldMap();
        boolean result = userService.saveUser(fieldMap);
        return new Result(result);
    }

    @Request.Get("/user/{id}")
    public View edit(long id) {
        User user = userService.findUser(id);
        DataContext.Request.put("user", user);
        return new View("customer_edit.jsp");
    }

    @Request.Put("/user/{id}")
    public Result update(long id, Params params) {
        Map<String, Object> fieldMap = params.getFieldMap();
        boolean result = userService.updateUser(id, fieldMap);
        return new Result(result);
    }

    @Request.Delete("/user/{id}")
    public Result delete(long id) {
        boolean result = userService.deleteUser(id);
        return new Result(result);
    }*/
}
