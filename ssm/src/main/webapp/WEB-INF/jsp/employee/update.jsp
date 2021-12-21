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
	var id = "${employee.id}";
	var name = "${employee.name}";
	var sex = "${employee.sex}";
	var birthday = "${employee.localBirthday}";
	var salary = "${employee.salary}";
	var path = "employee/";
	var detail = "${detail}";
</script>
</head>
<body>
	<fieldset>
		<legend>员工信息添加与修改</legend>
		<form action="" method="post" enctype="multipart/form-data">
			<input name="id" type="hidden" value="${employee.id}">
			<table>
				<tr>
					<td>姓名：</td>
					<td colspan="2"><input type="text" name="name"
						placeholder="请输入姓名" autocomplete="off"></td>
				</tr>
				<tr>
					<td>性别：</td>
					<td colspan="2"><select name="sex">
							<option value="男">男</option>
							<option value="女">女</option>
					</select></td>
				</tr>
				<tr>
					<td>出生日期：</td>
					<td colspan="2"><input type="text" name="birthday"
						class="demo-input" placeholder="请选择日期" id="test1" lay-key="1"
						autocomplete="off"></td>
				</tr>
				<tr>
					<td>工资：</td>
					<td colspan="2"><input type="text" name="salary"
						autocomplete="off"></td>
				</tr>
				<tr>
					<td>部门选择：</td>
					<td colspan="2"><select class="department" name="departmentId">
							<c:forEach items="${list}" var="department">
								<c:choose>
									<c:when test="${employee.department.name!=department.name }">
										<option value="${department.id}">${department.name}</option>
									</c:when>
									<c:otherwise>
										<option selected value="${department.id}">${department.name}</option>
									</c:otherwise>
								</c:choose>

							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>上传头像：</td>
					<td><input type="file" name="picturex" autocomplete="off" /></td>
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
