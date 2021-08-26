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
            <select id="banjiId" name="banjiId" class="form-control">
            </select>
        </div>
        <button type="submit" class="btn btn-success">提交</button>
    </form>
    <script src="<%=request.getContextPath()%>/static/jquery-2.1.4.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
                $.post(
                    '<%=request.getContextPath()%>/banji?method=selectAll',
                    function (jsonList) {
                        $(jsonList).each(function () {
                            $('#banjiId').append('<option value="' + this.id + '">' + this.name + '</option>');
                        });
                    },
                    'json'
                );
        });
    </script>

</body>
</html>
