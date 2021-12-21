<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.jyc.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<base href="${path}/">
<meta charset="utf-8">
<title></title>
<link rel="stylesheet" type="text/css"
	href="static/public/css/button.css" />
<link rel="stylesheet" type="text/css"
	href="static/project/employee/css/update.css" />
<script src="static/public/js/jquery-3.6.0.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="static/public/layer/layer.js" type="text/javascript"
	charset="utf-8"></script>
<script src="static/public/laydate/laydate.js" type="text/javascript"
	charset="utf-8"></script>
<script src="static/project/employee/js/update.js"
	type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
	var id = "${department.id}";
	var name = "${department.name}";
	var path = "department/";
	var detail = "${detail}";
</script>
</head>
<body>
	<fieldset>
		<legend>部门信息添加与修改</legend>
		<form action="" method="post" enctype="multipart/form-data">
		<input name="count" value="${department.count}" type="hidden">
		<input name="startTime" value="${department.startTime}" type="hidden">
			<input name="id" type="hidden" value="${department.id}">
			<table>
				<tr>
					<td>用户名：</td>
					<td colspan="2"><input type="text" name="name"
						placeholder="请输入姓名" autocomplete="off"></td>
				</tr>
				<tr>
					<td>上传LOGO：</td>
					<td><input type="file" name="picture" autocomplete="off" /></td>
				</tr>
				<tr>
					<td></td>
					<td><button type="submit" class="large blue button">提交</button></td>
					<td><button type="reset" class="large green button">重置</button></td>
				</tr>
			</table>
		</form>

	</fieldset>
</body>
</html>
