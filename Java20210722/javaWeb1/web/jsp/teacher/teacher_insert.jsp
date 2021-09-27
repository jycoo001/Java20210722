<%--
  Created by IntelliJ IDEA.
  User: 12430
  Date: 2021/8/18
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-3.4.1-dist/css/bootstrap.css"/>

</head>
<body>
    <form action="${pageContext.request.contextPath}/teacher?method=insert" method="post">
        <input type="hidden" value="${param.pageSize}" name="pageSize">
        <div class="form-group">
            <label>姓名</label>
            <input type="text" class="form-control" name="name" placeholder="姓名">
        </div>
        <div class="form-group">
            <label>地址</label>
            <input type="text" class="form-control" name="address" placeholder="地址">
        </div>
        <div class="form-group">
            <label>年龄</label>
            <input type="text" class="form-control" name="age" placeholder="年龄">
        </div>
        <button type="submit" class="btn btn-success">提交</button>
    </form>

</body>
</html>
