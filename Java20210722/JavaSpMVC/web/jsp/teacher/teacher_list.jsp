<%@ page import="java.util.ArrayList" %>
<%@ page import="com.jyc.entity.Student" %>
<%@ page import="com.jyc.util.PageInfo" %>
<%@ page import="com.jyc.entity.Teacher" %><%--
  Created by IntelliJ IDEA.
  User: 12430
  Date: 2021/8/17
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>teacher List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-3.4.1-dist/css/bootstrap.css"/>

</head>
<body>
    <a class="btn btn-success" href="${pageContext.request.contextPath}/jsp/teacher/teacher_insert.jsp?pageSize=${pageInfo.pageSize}">添加</a>
    <a class="btn btn-success" href="${pageContext.request.contextPath}/teacher/selectAll.action?pageNumber=1&pageSize=5">返回首页</a>
    <hr/>
    <table class="table table-bordered table-striped table-hover tab-content">
        <tr>
            <th>ID</th>
            <th>姓名</th>
            <th>地址</th>
            <th>年龄</th>
            <th>删除/修改</th>
        </tr>
        <c:forEach items="${pageInfo.list}" var="teacher">
            <tr>
                <td>${teacher.id}</td>
                <td>${teacher.name}</td>
                <td>${teacher.address}</td>
                <td>${teacher.age}</td>
                <%--<td><a href="<%=request.getContextPath()%>/student?method=deleteById&id=<%=student.getId()%>">删除</a></td>--%>
                <td>
                    <a class="btn btn-danger" href="javascript:void(0)" onclick="deleteById(${teacher.id})">删除</a>
                    <a class="btn btn-warning" href="${pageContext.request.contextPath}/teacher/selectById.action?id=${teacher.id}&pageNumber=${pageInfo.pageNumber}&pageSize=${pageInfo.pageSize}">修改</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <%--分页--%>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:choose>
                <c:when test="${pageInfo.pageNumber > 1}">
                    <li>
                        <a href="${pageContext.request.contextPath}/teacher/selectAll.action?pageNumber=${pageInfo.pageNumber-1}&pageSize=${pageInfo.pageSize}"
                           aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="disabled">
                        <a href="" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
            <c:forEach begin="1" end="${pageInfo.totalPage}" var="i">
                <li>
                    <a href="${pageContext.request.contextPath}/teacher/selectAll.action?pageNumber=${i}&pageSize=${pageInfo.pageSize}">${i}</a>
                </li>
            </c:forEach>
            <c:choose>
                <c:when test="${pageInfo.pageNumber<pageInfo.totalPage}">
                    <li>
                        <a href="${pageContext.request.contextPath}/teacher/selectAll.action?pageNumber=${pageInfo.pageNumber+1}&pageSize=${pageInfo.pageSize}"
                           aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="disabled">
                        <a href="" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>

    <script type="text/javascript">
            function deleteById (id) {
                var isDelete = confirm("您确认要删除吗？");
                if (isDelete) {
                location.href = "${pageContext.request.contextPath}/teacher/deleteById.action?id=" + id + "&pageNumber=" + ${pageInfo.pageNumber} + "&pageSize=" + ${pageInfo.pageSize};
                }
            }
    </script>
</body>
</html>
