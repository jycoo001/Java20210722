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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/bootstrap-3.4.1-dist/css/bootstrap.css"/>

</head>
<body>
    <a class="btn btn-success" href="student_insert.jsp">添加</a>
    <hr/>
    <table class="table table-bordered table-striped table-hover tab-content">
        <tr>
            <th>ID</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>删除</th>
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
        <%--<td><a href="<%=request.getContextPath()%>/student?method=deleteById&id=<%=student.getId()%>">删除</a></td>--%>
            <td>
                <a class="btn btn-danger" href="javascript:void(0)" onclick="deleteById(<%=student.getId()%>)">删除</a>
                <a class="btn btn-warning" href="<%=request.getContextPath()%>/student?method=selectOne&id=<%=student.getId()%>">修改</a>
            </td>
        </tr>
                    <%
                        }
                    %>
    </table>

    <script type="text/javascript">
            function deleteById (id) {
                var isDelete = confirm("您确认要删除吗？");
                if (isDelete) {
                location.href = "<%=request.getContextPath()%>/student?method=deleteById&id=" + id;
                }
            }
    </script>
</body>
</html>
