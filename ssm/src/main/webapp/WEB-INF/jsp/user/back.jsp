<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<base href="${path}/">
<meta charset="UTF-8">
<title>Update</title>
<link rel="stylesheet" type="text/css"
	href="static/public/css/button.css" />
<link rel="stylesheet" type="text/css"
	href="static/project/user/css/update.css" />
<script src="static/public/js/jquery-3.6.0.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="static/public/layer/layer.js" type="text/javascript"
	charset="utf-8"></script>
<script src="static/project/user/js/update.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript">
	var detail = "${detail}";
	var password="${user.password}";
</script>
</head>
<body>
	<div class="hidden"></div>
	<div class="container">
		<div class="span">
			<span>修改</span>
		</div>
		<div class="login">
			<form action="user/update" method="post">
				<div class="name">
					<label for="username">请输入用户名：</label> <input type="text" id="username"
						name="username" value="${user.username}" placeholder="用户名"
						autocomplete="none" />
				</div>
				<div>
					<label for="phone">请输入手机号：</label> <input type="text" name="phone" id="phone"
						value="${user.phone }" placeholder="手机号" />
				</div>
				<div class="password">
					<label for="password">请输入新密码：</label> <input type="password" id="password"
						name="password" placeholder="密码" />
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