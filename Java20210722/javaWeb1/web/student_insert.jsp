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
</head>
<body>
    <form action="<%=request.getContextPath()%>/student?method=insert" method="post">
        姓名:<input type="text" name="sname"><br/>
        性别:<input type="text" name="sex"> <br/>
        年龄:<input type="text" name="age"><br/>
        <input type="submit" value="提交">
    </form>

</body>
</html>
