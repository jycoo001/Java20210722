<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<base href="${path}/">
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="static/public/index/assets/css/layui.css">
<link rel="stylesheet" href="static/public/index/assets/css/admin.css">
<link rel="icon" href="/favicon.ico">
<script src="static/public/js/jquery-3.6.0.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="static/public/layer/layer.js" type="text/javascript"
	charset="utf-8"></script>
<title>管理后台</title>
<script type="text/javascript">
	var detail = "${detail}";
	var path="${path}/";
	$(function() {
		if (detail) {
			layer.alert(detail);
		}
	});
</script>
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header custom-header">

			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item slide-sidebar" lay-unselect><a
					href="javascript:;" class="icon-font"><i class="ai ai-menufold"></i></a>
				</li>
			</ul>

			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;">${user.username}</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">帮助中心</a>
						</dd>
						<dd>
							<a href="user/outlogin">退出</a>
						</dd>
					</dl></li>
			</ul>
		</div>

		<div class="layui-side custom-admin">
			<div class="layui-side-scroll">

				<div class="custom-logo">
					<img src="static/public/index/assets/images/logo.png" alt="" />
					<h1>后台系统</h1>
				</div>
				<ul id="Nav" class="layui-nav layui-nav-tree">
					<li class="layui-nav-item"><a href="javascript:;"> <i
							class="layui-icon">&#xe609;</i> <em>主页</em>
					</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="index/echart">百度echart</a>
							</dd>
							<dd>
								<a href="employee/list">员工管理</a>
							</dd>
							<dd>
								<a href="department/list">部门管理</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;"> <i
							class="layui-icon">&#xe857;</i> <em>组件</em>
					</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="views/form.html">表单</a>
							</dd>
							<dd>
								<a href="javascript:;">页面</a>
								<dl class="layui-nav-child">
									<dd>
										<a href="login.html">登录页</a>
									</dd>
								</dl>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;"> <i
							class="layui-icon">&#xe612;</i> <em>用户</em>
					</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="user/list">用户组</a>
							</dd>
							<dd>
								<a href="views/operaterule.html">权限配置</a>
							</dd>
						</dl></li>
				</ul>

			</div>
		</div>

		<div class="layui-body">
			<div class="layui-tab app-container" lay-allowClose="true"
				lay-filter="tabs">
				<ul id="appTabs" class="layui-tab-title custom-tab"></ul>
				<div id="appTabPage" class="layui-tab-content"></div>
			</div>
		</div>

		<div class="layui-footer">
			<p>
				© 2018 更多模板：<a href="http://www.mycodes.net/"
					target="_blank">源码之家</a>
			</p>
		</div>

		<div class="mobile-mask"></div>
	</div>
	<script src="static/public/index/assets/layui.js"></script>
	<script src="static/public/index/index.js" data-main="home"></script>
</body>
</html>