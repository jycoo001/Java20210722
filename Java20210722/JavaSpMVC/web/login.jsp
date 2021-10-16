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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-3.4.1-dist/css/bootstrap.css"/>
    <style type="text/css" >
        .div1{
            width: 100%;
            height: 100px;
        }
        body{
            background-image: url("${pageContext.request.contextPath}/static/jpg/1.jpg");
        }
        .fl{
            float: left;
        }
        .fr{
            float: right;
        }
        .clear{
            clear: both;
        }

    </style>
</head>
<body>
    <div class="div1">
        <h1 align="center">登录</h1>
    </div>
    <div>
        <form action="${pageContext.request.contextPath}/user/login.action" method="post" class="form-horizontal">
            <div class="form-group">
                <label for="inputName1" class="col-sm-2 control-label">姓名：</label>
                <div class="col-sm-7">
                    <input type="text" name="name" class="form-control" id="inputName1" placeholder="name">
                </div>
            </div>

            <div class="form-group">
                <label for="inputPassword1" class="col-sm-2 control-label">密码：</label>
                <div class="col-sm-7">
                    <input type="password" name="password" class="form-control" id="inputPassword1" placeholder="password">
                </div>
            </div>

            <div class="form-group">
                <label for="inputCode1" class="col-sm-2 control-label">验证码：</label>
                <div class="col-sm-5">
                    <input type="text" name="code" class="form-control" id="inputCode1" placeholder="imageCode">
                </div>
                <img id="imageCode" onclick="refishCode()" src="${pageContext.request.contextPath}/code"/>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10 fl">
                    <input type="submit"  class="btn btn-success " value="登录">
                    <input type="button" onclick="location.href='${pageContext.request.contextPath}/register.jsp'" class="btn btn-warning" value="注册">
                </div>
            </div>

        </form>
    </div>

    <script src="${pageContext.request.contextPath}/static/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
        function refishCode() {
            $('#imageCode').attr('src', '${pageContext.request.contextPath}/code?'+Math.random());
        }
    </script>
</body>
</html>
