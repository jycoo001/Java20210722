<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<base href="${path}/">
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css"
	href="static/public/css/button.css" />
<link rel="stylesheet" type="text/css"
	href="static/project/user/css/login.css" />
<script src="static/public/js/jquery-3.6.0.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="static/public/layer/layer.js" type="text/javascript"
	charset="utf-8"></script>
<script src="static/project/user/js/login.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript">
	var detail = "${detail}";
	var path = "${path}/";
</script>
</head>
<body>
	<div class="hidden"></div>
	<div class="container">
		<div class="span">
			<span>登录</span>
		</div>
		<div class="login">
			<form action="user/login" method="post">
				<div class="name">
					<label for="username">用户名：</label> <input type="text" id="username"
						name="username" value="${user.username}" placeholder="用户名"
						autocomplete="none" />
				</div>
				<div>
					<label for="password">密码：</label> <input type="password" id="password"
						name="password" value="${user.password }" placeholder="密码" />
				</div>
				<div>
					<label for="code">验证码：</label> <input type="text" id="code" name="code"
						placeholder="验证码" /> <img id="imageCode" src="${path}/code" />
				</div>
				<div class="div-but">
					<button class="large button blue" type="sunbmit">登录</button>
					<button class="large button green" type="reset">重置</button>
					<a href="user/register">注册新用户</a> <a href="user/back">忘记密码</a>
				</div>
			</form>
		</div>
	</div>

</body>
</html>