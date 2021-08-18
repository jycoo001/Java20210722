<%@ page import="com.situ.javaWeb.entity.Student" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 12430
  Date: 2021/8/18
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<%
    Student student = (Student)request.getAttribute("student");
%>
<form action="<%=request.getContextPath()%>/student?method=update" method="post">
    <input type="hidden" name="id" value="<%=student.getId()%>">
    姓名:<input type="text" name="sname" value="<%=student.getSname()%>"><br/>
    性别:<input type="text" name="sex" value="<%=student.getSex()%>"> <br/>
    年龄:<input type="text" name="age" value="<%=student.getAge()%>"><br/>
    <input type="submit" value="提交">
</form>

</body>
</html>
