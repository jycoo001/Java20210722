<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="libs/css/edit.css">
<script type="text/javascript" src="libs/js/jquery.min.js"></script>
<script type="text/javascript" src="libs/layer/layer.js"></script>
</head>
<body>

	<script type="text/javascript">
		function save() {
			var data = $("form").serialize();
			$.post("Admin/repass", data, function(json) {
				if (json.status == -1) {
					layer.alert('新密码录入失败');
				} else if (json.status == -2) {
					layer.alert('原密码录入失败');
				} else if (json.status > 0) {
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index); //再执行关闭
				}
			}, "json");
		}
	</script>
	<form>
		<div class="inputview">
			<span class="inputspan"> <label class="inputtext">原密码:</label>
				<input class="inputinput" name="pass" type="password" />
			</span> <span class="inputspan"> <label class="inputtext">新密码:</label>
				<input class="inputinput" name="pass1" type="password" />
			</span> <span class="inputspan"> <label class="inputtext">新密码:</label>
				<input class="inputinput" name="pass2" type="password" />
			</span>
		</div>
		<div class="inputbtpanel ">
			<button class="seachbt1" type="button" onclick="save()">修改</button>
		</div>
	</form>

</body>
</html>