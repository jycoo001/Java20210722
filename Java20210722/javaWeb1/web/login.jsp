<%--
  Created by IntelliJ IDEA.
  User: 12430
  Date: 2021/8/23
  Time: 8:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/user?method=login" method="post">
        用户名：<input type="text" name="name"><br/>
        密码：<input type="password" name="password"><br/>
        验证码：<input type="text" name="code"><img id="imageCode" onclick="refishCode()" src="<%=request.getContextPath()%>/code"/><br/>
        <input type="submit" value="登录">
    </form>

    <script src="<%=request.getContextPath()%>/static/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
        function refishCode() {
            $('#imageCode').attr('src', '<%=request.getContextPath()%>/code?'+Math.random());
        }
    </script>
</body>
</html>
