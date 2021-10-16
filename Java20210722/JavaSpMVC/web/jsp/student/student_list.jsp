<%@ page import="java.util.ArrayList" %>
<%@ page import="com.jyc.entity.Student" %>
<%@ page import="com.jyc.util.PageInfo" %>
<%@ page import="com.jyc.vo.StudentBanji" %><%--
  Created by IntelliJ IDEA.
  User: 12430
  Date: 2021/8/17
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>student List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-3.4.1-dist/css/bootstrap.css"/>

</head>
<body>
    <a class="btn btn-success" href="${pageContext.request.contextPath}/jsp/student/student_insert.jsp?pageSize=${pageInfo.pageSize}">添加</a>
    <a class="btn btn-success" href="${pageContext.request.contextPath}/student/selectAll.action?pageNumber=1&pageSize=5">返回首页</a>

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
        <c:forEach items="${pageInfo.list}" var="student">
            <tr>
                <td>${student.id}</td>
                <td>${student.sname}</td>
                <td>${student.sex}</td>
                <td>${student.age}</td>
                <td>${student.banjiName}</td>
                <%--<td><a href="<%=request.getContextPath()%>/student?method=deleteById&id=<%=student.getId()%>">删除</a></td>--%>
                <td>
                    <a class="btn btn-danger" href="javascript:void(0)" onclick="deleteById(${student.id})">删除</a>
                    <a class="btn btn-warning" href="${pageContext.request.contextPath}/student/selectById.action?id=${student.id}&pageNumber=${pageInfo.pageNumber}&pageSize=${pageInfo.pageSize}">修改</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:choose>
                <c:when test="${pageInfo.pageNumber>1}">
                    <li>
                        <a href="${pageContext.request.contextPath}/student/selectAll.action?pageNumber=${pageInfo.pageNumber-1}&pageSize=${pageInfo.pageSize}"
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
                    <a href="${pageContext.request.contextPath}/student/selectAll.action?pageNumber=${i}&pageSize=${pageInfo.pageSize}">${i}</a>
                </li>
            </c:forEach>
            <c:choose>
                <c:when test="${pageInfo.pageNumber < pageInfo.totalPage}">
                    <li>
                        <a href="${pageContext.request.contextPath}/student/selectAll.action?pageNumber=${pageInfo.pageNumber+1}&pageSize=${pageSize}"
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
                location.href = "<%=request.getContextPath()%>/student/deleteById.action?id=" + id + "&pageNumber=" + ${pageInfo.pageNumber} + "&pageSize=" + ${pageInfo.pageSize};
                }
            }
    </script>
</body>
</html>
