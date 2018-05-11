<%--
  Created by IntelliJ IDEA.
  User: CTSIG
  Date: 2018/5/10
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.zq.chapter2.service.CustomerService" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<html>
<head>
    <title>客户管理 - 创建客户</title>
</head>
<body>

<h1>创建客户界面</h1>

<%-- TODO --%>
<table>
    <tr>
        <td>id</td>
        <td>name</td>
        <td>contact</td>
        <td>telephone</td>
        <td>email</td>
        <td>remark</td>
    </tr>

<form action="/customer_create"  method="post">
    <tr>
        <td><input type="text" name="id"/></td>
        <td><input type="text" name="name"/></td>
        <td><input type="text" name="contact"/></td>
        <td><input type="text" name="telephone"/></td>
        <td><input type="text" name="email"/></td>
        <td><input type="text" name="remark"/></td>
    </tr>
<input type="submit" value="Submit" />
</form>
</table>
</body>
</html>
