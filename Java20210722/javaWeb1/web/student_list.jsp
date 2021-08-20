<%@ page import="java.util.ArrayList" %>
<%@ page import="com.situ.javaWeb.entity.Student" %>
<%@ page import="com.situ.javaWeb.util.pageInfo" %>
<%@ page import="com.situ.javaWeb.vo.StudentBanji" %><%--
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
    <a class="btn btn-success" href="<%=request.getContextPath()%>/student?method=getBanjiInsert">添加</a>
    <hr/>
    <table class="table table-bordered table-striped table-hover tab-content">
        <tr>
            <th>ID</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>班级</th>
            <th>删除</th>
        </tr>
            <%
                        pageInfo list = (pageInfo) request.getAttribute("pageInfo1");
                        ArrayList<StudentBanji> students = list.getList();
                        for (StudentBanji student : students) {
            %>
        <tr>
            <td><%=student.getStudentId()%></td>
            <td><%=student.getStudentName()%></td>
            <td><%=student.getStudentSex()%></td>
            <td><%=student.getStudentAge()%></td>
            <td><%=student.getBanjiName()%></td>
        <%--<td><a href="<%=request.getContextPath()%>/student?method=deleteById&id=<%=student.getId()%>">删除</a></td>--%>
            <td>
                <a class="btn btn-danger" href="javascript:void(0)" onclick="deleteById(<%=student.getStudentId()%>)">删除</a>
                <a class="btn btn-warning" href="<%=request.getContextPath()%>/student?method=selectOne&id=<%=student.getStudentId()%>">修改</a>
            </td>
        </tr>
                    <%
                        }
                    %>
    </table>

    <nav aria-label="Page navigation">
        <ul class="pagination">
                <%
                if (list.getPageNumber()>1) {
                %>
                    <li>
                            <a href="<%=request.getContextPath()%>/student?method=selectAll&pageNumber=<%=list.getPageNumber()-1%>"
                               aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                    </li>
                <%
                }else {
                %>
                    <li class="disabled">
                            <a href="" aria-label="Previous">
                                 <span aria-hidden="true">&laquo;</span>
                            </a>
                    </li>
                <%
                }
                %>
            <%
                for (int i = 1; i <= list.getTotalPage(); i++) {
            %>
                    <li>
                        <a href="<%=request.getContextPath()%>/student?method=selectAll&pageNumber=<%=i%>"><%=i%></a>
                    </li>
            <%
                }
            %>
                <%
                    if (list.getPageNumber()<list.getTotalPage()) {
                %>
                    <li>
                        <a href="<%=request.getContextPath()%>/student?method=selectAll&pageNumber=<%=list.getPageNumber()+1%>"
                           aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                <%
                }else {
                %>
                    <li class="disabled">
                        <a href="" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                <%
                    }
                %>
        </ul>
    </nav>








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
