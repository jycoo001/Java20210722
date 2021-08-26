<%@ page import="com.situ.javaWeb.entity.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.situ.javaWeb.entity.Teacher" %><%--
  Created by IntelliJ IDEA.
  User: 12430
  Date: 2021/8/18
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher-Edit</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-3.4.1-dist/css/bootstrap.css"/>

</head>
<body>
    <form action="${pageContext.request.contextPath}/teacher?method=update" method="post">
        <input type="hidden" name="id" value="${teacher.id}">
        <div class="form-group">
            <label>姓名</label>
            <input type="text" class="form-control" name="name" value="${teacher.name}">
        </div>
        <div class="form-group">
            <label>地址</label>
            <input type="text" class="form-control" name="address" value="${teacher.address}">
        </div>
        <div class="form-group">
            <label>年龄</label>
            <input type="text" class="form-control" name="age" value="${teacher.age}">
        </div>
        <button type="submit" class="btn btn-success">提交</button>
    </form>

</body>
</html>
