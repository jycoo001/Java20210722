<%@ page import="java.util.ArrayList" %>
<%@ page import="com.situ.javaWeb.entity.Student" %><%--
  Created by IntelliJ IDEA.
  User: 12430
  Date: 2021/8/17
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>student List</title>
    <style type="text/css">
        *{
            margin: 0;
            padding: 0;
        }
        .div{
            width: 100%;
            background-color: blue;
        }
        .div1{
            width: 400px;
            height: 130px;
            margin: 0 auto;
        }
        .div2{
            width: 400px;
            margin: 0 auto;
        }
        .bg_color{
            background-color: red;
        }
    </style>
</head>
<body>
    <div class="div">
        <div class="div1 bg_color">
            ${list}
        </div>
        <hr/>
        <div class="div2 bg_color">
            <table>
                <tr>
                    <td>ID</td>
                    <td>姓名</td>
                    <td>性别</td>
                    <td>年龄</td>
                </tr>
                <%
                    ArrayList<Student> list = (ArrayList<Student>) request.getAttribute("list");
                    for (Student student : list) {
                %>
                <tr>
                    <td><%=student.getId()%></td>
                    <td><%=student.getSname()%></td>
                    <td><%=student.getSex()%></td>
                    <td><%=student.getAge()%></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
    </div>
</body>
</html>
