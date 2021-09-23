<%--
  Created by IntelliJ IDEA.
  User: 12430
  Date: 2021/9/17
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/student/insert.action" method="post">
        <table>
            <tr>
                <td>姓名：</td>
                <td><input type="text" name="sname"></td>
            </tr>
            <tr>
                <td>性别：</td>
                <td><input type="text" name="sex"></td>
            </tr>
            <tr>
                <td>年龄：</td>
                <td><input type="text" name="age"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="提交"></td>
            </tr>
        </table>
    </form>

</body>
</html>
