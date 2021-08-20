<%@ page import="com.situ.javaWeb.entity.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.situ.javaWeb.entity.Banji" %><%--
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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/bootstrap-3.4.1-dist/css/bootstrap.css"/>

</head>
<body>
<%
    Student student = (Student)request.getAttribute("student");
    List<Banji> banji = (List<Banji>) request.getAttribute("banjiList");
%>
<form action="<%=request.getContextPath()%>/student?method=update" method="post">
    <input type="hidden" name="id" value="<%=student.getId()%>">
    <div class="form-group">
        <label>姓名</label>
        <input type="text" class="form-control" name="sname" value="<%=student.getSname()%>">
    </div>
    <div class="form-group">
        <label>性别</label>
        <input type="text" class="form-control" name="sex" value="<%=student.getSex()%>">
    </div>
    <div class="form-group">
        <label>年龄</label>
        <input type="text" class="form-control" name="age" value="<%=student.getAge()%>">
    </div>
    <div class="form-group">
        <label>班级</label>
        <select name="banjiId" class="form-control">
        <%
            for (Banji banji1 : banji) {
        %>
            <%
                if (banji1.getId()==student.getBanjiId()) {
            %>
                <option selected value="<%=banji1.getId()%>"><%=banji1.getName()%></option>
            <%
            }%>
                <option value="<%=banji1.getId()%>"><%=banji1.getName()%></option>
        <%
            }
        %>
        </select>
    </div>
    <button type="submit" class="btn btn-success">提交</button>
</form>

</body>
</html>
