layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;

	//加载页面数据
	var newsData = '';
	$.get("../../json/newsList.json", function(data){
		var newArray = [];
		//单击首页“待审核文章”加载的信息
		if($(".top_tab li.layui-this cite",parent.document).text() == "待审核文章"){
			if(window.sessionStorage.getItem("addNews")){
				var addNews = window.sessionStorage.getItem("addNews");
				newsData = JSON.parse(addNews).concat(data);
			}else{
				newsData = data;
			}
			for(var i=0;i<newsData.length;i++){
        		if(newsData[i].newsStatus == "待审核"){
					newArray.push(newsData[i]);
        		}
        	}
        	newsData = newArray;
        	newsList(newsData);
		}else{    //正常加载信息
			newsData = data;
			if(window.sessionStorage.getItem("addNews")){
				var addNews = window.sessionStorage.getItem("addNews");
				newsData = JSON.parse(addNews).concat(newsData);
			}
			//执行加载数据的方法
			newsList();
		}
	})

	//查询
	$(".search_btn").click(function(){
		var str = $("#find").val();
		if($(".search_input").val() != ''){
			var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
            setTimeout(function(){
            	var form1 = $(".hidden-form>form");
            	var inp = $(".hidden-form>form>input:first-child");
            	inp.val(str);
            	console.log(inp.val());
            	form1.submit();
                layer.close(index);
            },2000);
		}else{
			layer.msg("请输入需要查询的内容");
		}
	})

	//添加文章
	//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
	$(window).one("resize",function(){
		$(".newsAdd_btn").click(function(){
			var index = layui.layer.open({
				title : "添加题目",
				type : 2,
				content : "toInsert",
				success : function(layero, index){
					setTimeout(function(){
						layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
							tips: 3
						});
					},500)
				}
			})			
			layui.layer.full(index);
		})
	}).resize();

	//推荐文章
	$(".recommend").click(function(){
		var $checkbox = $(".news_list").find('tbody input[type="checkbox"]:not([name="show"])');
		if($checkbox.is(":checked")){
			var index = layer.msg('推荐中，请稍候',{icon: 16,time:false,shade:0.8});
            setTimeout(function(){
                layer.close(index);
				layer.msg("推荐成功");
            },2000);
		}else{
			layer.msg("请选择需要推荐的文章");
		}
	})

	//审核文章
	$(".audit_btn").click(function(){
		var $checkbox = $('.news_list tbody input[type="checkbox"][name="checked"]');
		var $checked = $('.news_list tbody input[type="checkbox"][name="checked"]:checked');
		if($checkbox.is(":checked")){
			var index = layer.msg('审核中，请稍候',{icon: 16,time:false,shade:0.8});
            setTimeout(function(){
            	for(var j=0;j<$checked.length;j++){
            		for(var i=0;i<newsData.length;i++){
						if(newsData[i].newsId == $checked.eq(j).parents("tr").find(".news_del").attr("data-id")){
							//修改列表中的文字
							$checked.eq(j).parents("tr").find("td:eq(3)").text("审核通过").removeAttr("style");
							//将选中状态删除
							$checked.eq(j).parents("tr").find('input[type="checkbox"][name="checked"]').prop("checked",false);
							form.render();
						}
					}
            	}
                layer.close(index);
				layer.msg("审核成功");
            },2000);
		}else{
			layer.msg("请选择需要审核的文章");
		}
	})


	//批量删除
	$(".del-my").click(function(){
		var $checkbox = $('.news_list tbody input[type="checkbox"][name="checked"]');
		var $checked = $('.news_list tbody input[type="checkbox"][name="checked"]:checked');
		var ids = [];
		for(var i = 0; i < $checked.length;i++) {
			ids[i] = +$($checked[i]).attr("data-id");
		}
		if($checkbox.is(":checked")){
			layer.confirm('确定删除选中的信息？',{icon:3, title:'提示信息'},function(index){
				var index = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
	            setTimeout(function(){
	            	//删除数据
					$.ajax({
						url:"deleteMany",
						type:"get",
						dataType:"json",
						data:{"ids" : ids},
						contentType:'application/json;charset=utf-8',
						success:function (data) {
							if(data.detail=='删除成功') {
								_this.parents("tr").remove();
								layer.msg("删除成功！删除了1行");
							}
						}
					});
	            	for(var j=0;j<$checked.length;j++){
	            		for(var i=0;i<newsData.length;i++){
							if(newsData[i].newsId == $checked.eq(j).parents("tr").find(".news_del").attr("data-id")){
								newsData.splice(i,1);
								newsList(newsData);
							}
						}
	            	}
	            	$('.news_list thead input[type="checkbox"]').prop("checked",false);
	            	form.render();
	                layer.close(index);
					layer.msg("删除成功");
	            },2000);
	        })
		}else{
			layer.msg("请选择需要删除的文章");
		}
	})

	//全选
	form.on('checkbox(allChoose)', function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
		child.each(function(index, item){
			item.checked = data.elem.checked;
		});
		form.render('checkbox');
	});

	//通过判断文章是否全部选中来确定全选按钮是否选中
	form.on("checkbox(choose)",function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
		var childChecked = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"]):checked')
		if(childChecked.length == child.length){
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = true;
		}else{
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = false;
		}
		form.render('checkbox');
	})

	//是否展示
	form.on('switch(isShow)', function(data){
		var index = layer.msg('修改中，请稍候',{icon: 16,time:false,shade:0.8});
		var da = $(data.elem).closest("tr").children();
		var dat = $(da[0]).find("input").attr("data-id");
		$.ajax({
			url:"updateStatus",
			type:"post",
			dataType:"json",
			success:function (data) {
				setTimeout(function(){
					layer.close(index);
					layer.msg(data.detail);
				},2000);
			}
		})
	})
 
	//编辑
	$("body").on("click",".news_edit",function(){  //编辑
		var th = $($(this).closest("tr").children()[0]).find("input").attr("data-id");
		var index = layui.layer.open({
			title : "编辑题目",
			type : 2,
			content : "toUpdate?id="+th,
			success : function(layero, index){
				setTimeout(function(){
					layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
						tips: 3
					});
				},500)
			}
		})
		layui.layer.full(index);
	})

	$("body").on("click",".news_collect",function(){  //收藏.
		if($(this).text().indexOf("已收藏") > 0){
			layer.msg("取消收藏成功！");
			$(this).html("<i class='layui-icon'>&#xe600;</i> 收藏");
		}else{
			layer.msg("收藏成功！");
			$(this).html("<i class='iconfont icon-star'></i> 已收藏");
		}
	})

	$("body").on("click",".news_del",function(){  //删除
		var _this = $(this);
		var id = $(_this.parents("tr").children()[0]).find("input").attr("data-id");
		console.log(id);
		layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(index){
			$.ajax({
				url:"delete",
				type:"get",
				dataType:"json",
				data:{"id" : id},
				contentType:'application/json;charset=utf-8',
				success:function (data) {
					var index = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
					setTimeout(function(){
						layer.close(index);
						layer.msg(data.detail);
					},2000);
					if(data.detail=='删除成功') {
						_this.parents("tr").remove();
						layer.msg("删除成功！删除了1行");
					}
				}
			})
			layer.close(index);
		});
	})

	//我的分页
	$(".first-page").click(function () {
		var name = $("#find").val();
		$("#single").val(name);
		$("#pageNumber").val(1);
		var pageSize = $(".select-pageSize").val();
		$("#pageSize").val(pageSize);
		$("#form-my").submit();
	});
	$(".prev-page").click(function() {
		var name = $("#find").val();
		$("#single").val(name);
		var number = +$("#pageNumber").val()-1;
		$("#pageNumber").val(number);
		var pageSize = $(".select-pageSize").val();
		$("#pageSize").val(pageSize);
		$("#form-my").submit();
	})
	$(".next-page").click(function () {
		var name = $("#find").val();
		$("#single").val(name);
		var number = +$("#pageNumber").val()+1;
		$("#pageNumber").val(number);
		var pageSize = $(".select-pageSize").val();
		$("#pageSize").val(pageSize);
		$("#form-my").submit();
	});
	$(".last-page").click(function() {
		var name = $("#find").val();
		$("#single").val(name);
		$("#pageNumber").val(page);
		var pageSize = $(".select-pageSize").val();
		$("#pageSize").val(pageSize);
		$("#form-my").submit();
	});
	$(".my-ul").on("click",".my-li",function (){
		var name = $("#find").val();
		$("#single").val(name);
		var th = $(this).text();
		$("#pageNumber").val(th);
		var pageSize = $(".select-pageSize").val();
		$("#pageSize").val(pageSize);
		$("#form-my").submit();
	});
	$(".select-pageSize").change(function() {
		var name = $("#find").val();
		$("#single").val(name);
		var pageSize = $(".select-pageSize").val();
		$("#pageSize").val(pageSize);
		$("#form-my").submit();
	});


	function newsList(that){
		//渲染数据
		function renderDate(data,curr){
			var dataHtml = '';
			if(!that){
				currData = newsData.concat().splice(curr*nums-nums, nums);
			}else{
				currData = that.concat().splice(curr*nums-nums, nums);
			}
			if(currData.length != 0){
				for(var i=0;i<currData.length;i++){
					dataHtml += '<tr>'
			    	+'<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
			    	+'<td align="left">'+currData[i].newsName+'</td>'
			    	+'<td>'+currData[i].newsAuthor+'</td>';
			    	if(currData[i].newsStatus == "待审核"){
			    		dataHtml += '<td style="color:#f00">'+currData[i].newsStatus+'</td>';
			    	}else{
			    		dataHtml += '<td>'+currData[i].newsStatus+'</td>';
			    	}
			    	dataHtml += '<td>'+currData[i].newsLook+'</td>'
			    	+'<td><input type="checkbox" name="show" lay-skin="switch" lay-text="是|否" lay-filter="isShow"'+currData[i].isShow+'></td>'
			    	+'<td>'+currData[i].newsTime+'</td>'
			    	+'<td>'
					+  '<a class="layui-btn layui-btn-mini news_edit"><i class="iconfont icon-edit"></i> 编辑</a>'
					+  '<a class="layui-btn layui-btn-normal layui-btn-mini news_collect"><i class="layui-icon">&#xe600;</i> 收藏</a>'
					+  '<a class="layui-btn layui-btn-danger layui-btn-mini news_del" data-id="'+data[i].newsId+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
			        +'</td>'
			    	+'</tr>';
				}
			}else{
				dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
			}
		    return dataHtml;
		}

		//分页
		var nums = 13; //每页出现的数据量
		if(that){
			newsData = that;
		}
		laypage({
			cont : "page",
			pages : Math.ceil(newsData.length/nums),
			jump : function(obj){
				$(".news_content").html(renderDate(newsData,obj.curr));
				$('.news_list thead input[type="checkbox"]').prop("checked",false);
				form.render();
			}
		})
	}

})
