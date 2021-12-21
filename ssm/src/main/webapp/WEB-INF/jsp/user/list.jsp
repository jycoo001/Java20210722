<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.jyc.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<base href="${path}/">
<meta charset="utf-8" />
<title>用户列表</title>
<link rel="stylesheet" type="text/css"
	href="static/public/css/button.css" />
<link rel="stylesheet" type="text/css"
	href="static/project/employee/css/list.css" />
<script src="static/public/js/jquery-3.6.0.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="static/public/layer/layer.js" type="text/javascript"
	charset="utf-8"></script>
<script src="static/public/laydate/laydate.js" type="text/javascript"
	charset="utf-8"></script>
<script src="static/project/employee/js/list.js" type="text/javascript"
	charset="utf-8"></script>
</head>
<script type="text/javascript">
	var pageNumber = "${page.pageNumber}";
	var pageSize = "${page.pageSize}";
	var totalPage = "${page.totalPage}";
	var path = "${path}" + "/user/";
	var detail = "${detail}";
</script>
<body>
	<div class="con">
		<div class="div-form">
			<div class="hid-form">
				<form action="" method="post">
					<input type="hidden" name="pageNumber" value="${page.pageNumber }" />
					<input type="hidden" name="pageSize" value="${page.pageSize }" />
					id：<input class="id" name="id" value="${user.id}"
						placeholder="id" autocomplete="off" /> 用户名：<input class="select"
						name="username" value="${user.username}" placeholder="请输入查询的姓名"
						autocomplete="off" />  电话：<input type="text" name="phone"
						value="${user.phone}" autocomplete="off">
					</select>
					<button class="large green button find" type="submit">查询</button>
				</form>
				<buttton class="large green button insert">添加</buttton>
				<buttton class="large green button updateBox">修改</buttton>
			</div>
		</div>
		<div class="div-table">
			<table class="tab">
				<thead>
					<tr>
						<th><input type="checkbox" class="checkAll" /></th>
						<th>用户ID</th>
						<th>用户名</th>
						<th>联系方式</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="userl">
						<tr>
							<td><input type="checkbox" class="check" /></td>
							<td>${userl.id}</td>
							<td>${userl.username }</td>
							<td>${userl.phone }</td>
							<td><button class="large green button edit">修改</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>



		<div class="div-page">
			<div class="page">
				<span class="first">首页</span> <span class="prev">上一页</span>
				<ul>
					<c:forEach var="page1" begin="${page.beginPage }"
						end="${page.endPage}">
						<c:choose>
							<c:when test="${page1 == page.pageNumber}">
								<li class="sel">${page1}</li>
							</c:when>
							<c:otherwise>
								<li>${page1}</li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
				<span class="next">下一页</span> <span class="last">尾页</span> <select
					class="select">
					<option value="8">8</option>
					<option value="10">10</option>
					<option value="15">15</option>
					<option value="20">20</option>
					<option value="30">30</option>
					<option value="50">50</option>
					<option value="100">100</option>
				</select>
			</div>
		</div>
	</div>
</body>
</html>
