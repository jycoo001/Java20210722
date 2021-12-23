<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

<title>- 注册</title>

<link href="libs/css/bootstrap.min.css" rel="stylesheet">
<link href="libs/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="libs/css/animate.css" rel="stylesheet">
<link href="libs/css/style.css" rel="stylesheet">
<link href="libs/css/login.css" rel="stylesheet">

<script>
	if (window.top !== window.self) {
		window.top.location = window.location;
	}
	var a = 0;
	function changerimg(o) {
		o.src = "img?a=" + (a++);
	}
</script>
<script type="text/javascript">
function on_click() {
	if()
}
</script>
</head>

<body class="signin">

	<div class="signinpanel">
		<div class="row">
			<div class="col-sm-12">
				<form method="post" action="Admin/insert">
					<h4 class="no-margins">注册：</h4>
					<p class="m-t-md">注册用户</p>
					<input type="text" name="name" class="form-control uname"
						placeholder="用户名" /> <input type="password" name="password"
						class="form-control pword m-b" placeholder="密码" /> <select
						id="login_type " name="login_type">
						<option value="Admin">管理员</option>
						<option value="Writer">作家</option>
						<option value="Reader">读者</option>

					</select>

					<button class="btn btn-success btn-block" type="submit">注册</button>
				</form>


			</div>
		</div>
		<div class="signup-footer">
			<div class="pull-left">&copy; yongchen</div>
		</div>
	</div>

</body>
<!--

//-->
</script>
</html>
