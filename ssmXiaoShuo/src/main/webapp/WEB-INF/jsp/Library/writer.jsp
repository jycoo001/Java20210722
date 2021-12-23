<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../libs/layui/css/layui.css" rel="stylesheet">
<script type="text/javascript" src="../libs/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="../libs/layui/layui.all.js"></script>
<script type="text/javascript" src="../libs/js/util.js"></script>
<script type="text/javascript" src="../libs/js/mylayer.js"></script>
<link href="../css/css.css" rel="stylesheet">
<style type="text/css">
.layui-table {
	margin-left: 30px !important;
}

.layui-table-main .page {
	margin-left: 25px;
}
</style>
</head>
<body>

	<div class="layui-form layui-border-box layui-table-view">
		<div class="layui-table-box">
			<div class="layui-table-body layui-table-main">
				<hr>
				<h1 align="center">${list.name_one}</h1>
				<hr>
				<p style="text-indent:2em">${list.name_zhengwen}</p>
				<br>
				<hr>
				<h2 align="center">
					<c:if test="${list.id>1}">
						<button>
							<a style="color: blue;" class="page"
								href="lastzhango?id=${list.id}">上一章</a>
						</button>
					</c:if>
					<button>
						<a style="color: blue;" class="page" href="index?pageno=1">返回文章导引</a>
					</button>
					<c:if test="${list.id<count}">
						<button>
							<a style="color: blue;" class="page" href="nextzhango?id=${list.id}">下一章</a>
						</button>
					</c:if>
				</h2>
				<br>


			</div>
		</div>



	</div>

</body>
</html>