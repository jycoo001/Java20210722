<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../libs/css/edit.css">
<script type="text/javascript" src="../libs/js/jquery.min.js"></script>
</head>
<body>
	<c:if test="${info==null}">
		<form action="save" method="post">
	</c:if>
	<c:if test="${info!=null}">
		<form action="update" method="post">
			<input type="hidden" name="id" value="${info.id}">
	</c:if>
	<div class="inputview">
		<span class="inputspan"> <label class="inputtext">名称:</label> <input
			class="inputinput" name="name" value="${info.name}" />
		</span> 
		
	</div>
	<div class="inputbtpanel ">
		<button class="seachbt1" type="submit">保存</button>
	</div>
	</form>

</body>
</html>