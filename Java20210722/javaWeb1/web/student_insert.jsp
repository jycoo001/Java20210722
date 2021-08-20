<%@ page import="java.util.List" %>
<%@ page import="com.situ.javaWeb.entity.Banji" %><%--
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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/bootstrap-3.4.1-dist/css/bootstrap.css"/>

</head>
<body>
    <%
        List<Banji> banjiList = (List<Banji>) request.getAttribute("banjiList");
    %>
    <form action="<%=request.getContextPath()%>/student?method=insert" method="post">
        <div class="form-group">
            <label>姓名</label>
            <input type="text" class="form-control" name="sname" placeholder="姓名">
        </div>
        <div class="form-group">
            <label>性别</label>
            <input type="text" class="form-control" name="sex" placeholder="性别">
        </div>
        <div class="form-group">
            <label>年龄</label>
            <input type="text" class="form-control" name="age" placeholder="年龄">
        </div>

        <div class="form-group">
            <label>班级</label>
            <select name="banjiId" class="form-control">
            <%
                for (Banji banji : banjiList) {
            %>

                    <option value="<%=banji.getId()%>"><%=banji.getName()%></option>

            <%
                }
            %>
            </select>
        </div>
        <button type="submit" class="btn btn-success">提交</button>
    </form>

</body>
</html>
