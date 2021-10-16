<%@ page import="com.jyc.entity.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.jyc.entity.Banji" %><%--
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-3.4.1-dist/css/bootstrap.css"/>

</head>
<body>
<form action="${pageContext.request.contextPath}/student/update.action" method="post">
    <input type="hidden" name="id" value="${student.id}">
    <input type="hidden" name="pageNumber" value="${pageNumber}">
    <input type="hidden" name="pageSize" value="${pageSize}">
    <div class="form-group">
        <label>姓名</label>
        <input type="text" class="form-control" name="sname" value="${student.sname}">
    </div>
    <div class="form-group">
        <label>性别</label>
        <input type="text" class="form-control" name="sex" value="${student.sex}">
    </div>
    <div class="form-group">
        <label>年龄</label>
        <input type="text" class="form-control" name="age" value="${student.age}">
    </div>
    <div class="form-group">
        <label>班级</label>
        <select id="banjiId" name="banjiId" class="form-control">
        </select>
    </div>
    <button type="submit" class="btn btn-success">提交</button>
    <script src="${pageContext.request.contextPath}/static/jquery-2.1.4.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            $.post(
                '${pageContext.request.contextPath}/banji/selectAll.action',
                function (jsonList) {
                    $(jsonList).each(function () {
                        if (this.id == ${student.banjiId}) {
                            $('#banjiId').append('<option selected value="' + this.id + '">' + this.name + '</option>');
                        }else {
                            $('#banjiId').append('<option value="' + this.id + '">' + this.name + '</option>');
                        }
                    });
                }
            );
        });

    </script>
</form>

</body>
</html>
