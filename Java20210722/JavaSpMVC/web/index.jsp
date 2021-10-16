<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
	<title>主页</title>
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">JAVA-0000</div>
			<ul class="layui-nav layui-layout-left">   </ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item">
				    <a href="javascript:;"> 
<%-- 				        <img src="<%=path%>/img/fei.png" --%>
<!-- 				        class="layui-nav-img"/> -->
				        	用户
<%-- 				        	[${user.name}] --%>
				    </a>
					<dl class="layui-nav-child">
						<dd>
							<a href="javascript:openUser()">基本资料</a>
						</dd>
						<dd>
							<a href="javascript:openPass()">修改密码</a>
						</dd>
						<dd>
                            <a href="javascript:openPic()">修改头像</a>
                        </dd>
					</dl>
				</li>
				<li class="layui-nav-item">
				    <a href="javascript:logout()">注销</a>
				</li>
			</ul>
		</div>
		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" >
					<li class="layui-nav-item layui-nav-itemed">
					    <a href="javascript:;">信息维护</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;" 
								data-url="${pageContext.request.contextPath}/student/selectAll.action?pageNumber=1&pageSize=5"
								class="site-demo-active">学生管理</a>
							</dd>
							<dd>
								<a href="javascript:;" 
								data-url="${pageContext.request.contextPath}/teacher/selectAll.action?pageNumber=1&pageSize=5"
								class="site-demo-active">教师管理</a>
							</dd>
							<dd>
								<a href="javascript:;"
								   data-url="${pageContext.request.contextPath}/echarts.jsp"
								   class="site-demo-active">统计图表</a>
							</dd>
						</dl>
					</li>
					<li class="layui-nav-item">
					    <a href="javascript:;">基础配置</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;" 
								class="site-demo-active" 
								data-url="/web/page/dict/dictlist.jsp">
								基础数据维护</a>
							</dd>
							<dd>
								<a href="javascript:;" class="site-demo-active" 
								data-url="/web/404.jsp">404-1</a>
							</dd>
						</dl>
					</li>
				</ul>
			</div>
		</div>
		<div class="layui-body"><!-- 内容主体区域 -->
			<iframe name="rightframe" width="99%" height="97%" src="/company_web/web/404.jsp"></iframe>
		</div>
		<div class="layui-footer">© JAVA - 底部固定区域</div>
	</div>
	<script type="text/javascript">
		var $ = layui.jquery;
		var layer = layui.layer;
		var element = layui.element;
		$('.site-demo-active').click(function() {
			window.open($(this).data('url'), "rightframe");
		});
		element.render();// element.init();
		function openURL(url){
			window.open('${path}' + url, "rightframe");
		}

		function logout() {
			layer.confirm(
					'您确认要退出么',
					{icon:3},
					function() {
						location.href = '${pageContext.request.contextPath}/user?method=logout'
					}
			);
		}

	</script>
</body>
</html>