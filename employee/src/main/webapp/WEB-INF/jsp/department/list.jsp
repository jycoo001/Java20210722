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
<title>部门列表</title>
<link rel="stylesheet" type="text/css"
	href="static/public/css/button.css" />
<link rel="stylesheet" type="text/css"
	href="static/project/department/css/list.css" />
<script src="static/public/js/jquery-3.6.0.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="static/public/layer/layer.js" type="text/javascript"
	charset="utf-8"></script>
<script src="static/public/laydate/laydate.js" type="text/javascript"
	charset="utf-8"></script>
<script src="static/project/department/js/list.js"
	type="text/javascript" charset="utf-8"></script>
</head>
<script type="text/javascript">
	var pageNumber = "${page.pageNum}";
	var pageSize = "${page.pageSize}";
	var totalPage = "${page.pages}";
	var path = "${path}" + "/department/";
	var pathP = "${path}/" + "picture/";
	var detail = "${detail}";
</script>
<body>
	<div class="con">
		<div class="div-form">
			<div class="hid-form">
				<form action="" method="post">
					<input type="hidden" name="pageNumber" value="${page.pageNum }" />
					<input type="hidden" name="pageSize" value="${page.pageSize }" />
					部门id：<input class="id" name="id" value="${department.id}"
						placeholder="id" autocomplete="off" /> 部门名：<input class="select"
						name="name" value="${department.name}" placeholder="请输入查询的姓名"</select>
					建立日期： <input type="text" name="fromTo" value="${department.fromTo}"
						class="demo-input" placeholder="请选择日期范围" id="test1" lay-key="1"
						autocomplete="off">
					<button class="large green button find" type="submit">查询</button>
				</form>
				<buttton class="large green button insert">添加</buttton>
				<buttton class="large green button updateBox">修改</buttton>
				<button class="deleteMany large red button">删除</button>
			</div>
		</div>
		<div class="div-table">
			<table class="tab">
				<thead>
					<tr>
						<th><input type="checkbox" class="checkAll" /></th>
						<th>部门ID</th>
						<th>部门名</th>
						<th>建立日期</th>
						<th>部门人数</th>
						<th>部门地址</th>
						<th colspan="2">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${departmentList}" var="d">
						<tr>
							<td><input type="checkbox" class="check" /></td>
							<td data-address="${d.logo}">${d.id}</td>
							<td>${d.name }</td>
							<td>${d.localStartTime }</td>
							<td>${d.count}</td>
							<td>${d.address.name}</td>
							<td><button class="large green button edit">修改</button></td>
							<td><button class="delete large red button">删除</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>



		<div class="div-page">
			<div class="page">
				<span class="first">首页</span> <span class="prev">上一页</span>
				<ul>
					<c:forEach var="page1" begin="${page.navigateFirstPage }"
						end="${page.navigateLastPage}">
						<c:choose>
							<c:when test="${page1 == page.pageNum}">
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
