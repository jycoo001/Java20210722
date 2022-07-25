<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<base href="${path}/">
<meta charset="UTF-8">
<title>Register</title>
<link rel="stylesheet" type="text/css"
	href="static/public/css/button.css" />
<link rel="stylesheet" type="text/css"
	href="static/project/user/css/register.css" />
<script src="static/public/js/jquery-3.6.0.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="static/public/layer/layer.js" type="text/javascript"
	charset="utf-8"></script>
<script src="static/project/user/js/register.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript">
	var detail = "${detail}";
</script>
</head>
<body>
	<div class="hidden"></div>
	<div class="container">

		<div class="span">
			<span>注册</span>
		</div>
		<div class="login">
			<form action="user/register" method="post">
				<div class="name">
					<label for="username">用户名：</label> <input type="text" id="username"
						name="username" value="${user.username}" placeholder="用户名"
						autocomplete="none" />
				</div>
				<div>
					<label for="password">密码：</label> <input type="password"
						name="password" value="${user.passwordOr}" placeholder="密码" />
				</div>
				<div>
					<label for="phone">手机号：</label> <input type="text" name="phone"
						value="${user.phone }" placeholder="手机号" />
				</div>
				<div class="div-but">
					<button class="large button blue" type="sunbmit">注册</button>
					<button class="large button green" type="reset">重置</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>