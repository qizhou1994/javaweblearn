<%--
  Created by IntelliJ IDEA.
  User: CTSIG
  Date: 2018/5/10
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.zq.chapter3.service.CustomerService" %>
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
        <td>photo</td>
    </tr>

<form action="/customer_create"  method="post" id="customer_from" enctype="multipart/form-data">
    <tr>
        <td><input type="text" name="id" value="${customer.id}"/></td>
        <td><input type="text" name="name" value="${customer.name}"/></td>
        <td><input type="text" name="contact" value="${customer.contact}"/></td>
        <td><input type="text" name="telephone" value="${customer.telephone}"/></td>
        <td><input type="text" name="email" value="${customer.email}"/></td>
        <td><input type="text" name="remark" value="${customer.remark}"/></td>
        <td><input type="text" name="photo" value="${customer.photo}"/></td>
    </tr>
<input type="submit" value="Submit" />
</form>
</table>
<script src="${BASE}/asset/lib/jquery/jquery.min.js"/>
<script src="${BASE}/asset/lib/jquery-form/jquery.form.min.js"/>
<script>
    $(function () {
        $('#customer_form').ajaxForm({
            type:'post',
            url:'${BASE}/customer_create',
            success:function (data) {
                if(data){
                    location.href='${BASE}/customer';
                }
            }
        });
    });
</script>
</body>
</html>
