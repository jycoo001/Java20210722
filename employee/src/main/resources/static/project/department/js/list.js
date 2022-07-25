$(function() {
	
	
	//显示头像
	$(".div-table>.tab tr>td:nth-child(2)").hover(function() {
		var url = $(this).data("address");
		if (url) {
			var a = $(this).offset();
			var le = a.left + $(this).width();
			var to = a.top;
			$("<div>").addClass("picture").css({
				left: le + "px",
				top: to + "px",
				background: "#333 url(" + pathP + url + ") no-repeat center center / cover"
			}).appendTo(document.body);
		}
	}, function() {
		$(".picture").remove();
	});

	//执行一个laydate实例
	laydate.render({
		elem: '#test1', //指定元素
		range: true
	});
	//设置修改
	$(".tab").on("click", ".edit", function() {
		var a = $(this).closest("tr").children("td:nth-child(2)").text();
		location.href = path + "update/" + a;
	});

	//多选框修改
	$(".updateBox").click(function() {
		var check = $(".check:checked").parent().next();
		if (check.length > 1) {
			layer.alert("只能选择一个！")
		} else if (check.length == 1) {
			location.href = path + "update/" + check.text();
		} else {
			layer.alert("请选择记录");
		}
	});

	//设置删除
	$(".tab").on("click", ".delete", function() {
		var a = $(this).closest("tr").children("td:nth-child(2)").text();
		layer.confirm("是否确认删除？", {
			btn: ["确定", "取消"],
			title: "警告"
		}, function(index) {
			if($(this).closest("tr").children("td:nth-child(5)").text!="0") {
				layer.confirm("该部门人数不为空，是否确认删除？",{
					btn:["确定","取消"],
					title:"警告"
				},function(idx) {
					location.href=path+"updateAndDelete/"+a;
					layer.close(idx);
				},function() {
					layer.close(idx);
				});
			} else {
				location.href = path + "delete/" + a;
			}
			layer.close(index);
		}, function(index) {
			layer.close(index);
		});
	});
	//删除、修改、添加提示
	if (detail) {
		layer.alert(detail);
	}

	//多选
	$(".checkAll").click(function() {
		var check = $(".checkAll").prop("checked");
		$(".check").prop("checked", check);
	});
	//ajax多删
	$(".deleteMany").click(function() {
		var check = $(".check:checked").parent().next();
		var b = [];
		for (var i = 0; i < check.length; i++) {
			b[i] = check[i].innerText;
		}

		if (b.length == 0) {
			layer.alert("请选择要删除的记录");
		} else {
			layer.confirm("是否确认删除？", {
				btn: ["确定", "取消"],
				title: "提示"
			}, function(index) {

				$.ajax({
					url: path + "delete",
					method: "post",
					data: {
						che: b
					},
					dataType: "json",
					tranditional: true,
					success: function(resp) {
						console.log("aaa");
						if (resp.success) {
							layer.msg("成功删除" + resp.rows + "条记录");
							check.parent().remove();
						} else {
							layer.alert(resp.error || "删除失败！")
						}
					}
				});
				layer.close(index);
			}, function(index) {
				layer.close(index);
			});
		}
	});

	//设置导向添加页面的连接
	$(".insert").on("click", function() {
		location.href = path + "insert";
	});

	//设置分页表单的action属性
	$(".hid-form>form")[0].action = path + "list";
	//设置表单pageSize，下拉框value
	$(".hid-form [name=pageSize]").val(pageSize);
	$(".page .select").val(pageSize);

	$(".hid-form>form>button").on("click", function() {
		$(".hid-form>form").submit();
	});

	//下拉框选择显示一页显示条数
	$(".page .select").change(function() {
		var value = $(this).val();
		$(".hid-form [name=pageSize]").val(value);
		$(".hid-form>form").submit();
	});
	//设置动画，鼠标滑过透明度改变
	$(".tab tr").not(".tab tr:first").hover(function() {
		$(this).stop().animate({
			opacity: "1"
		});
	}, function() {
		$(this).stop().animate({
			opacity: "0.8"
		});
	});
	//点击下一页上一页等
	$(".page>span").click(function() {
		var th = $(this);
		if (th.is(".first")) {
			$(".hid-form [name=pageNumber]").val(1);
		} else if (th.is(".prev")) {
			pageNumber = parseInt(pageNumber) - 1;
			if (pageNumber < 1) {
				pageNumber = 1;
			}
			$(".hid-form [name=pageNumber]").val(pageNumber);
		} else if (th.is(".next")) {
			pageNumber = parseInt(pageNumber) + 1;
			if (pageNumber > totalPage) {
				pageNumber = totalPage;
			}
			$(".hid-form [name=pageNumber]").val(pageNumber);
		} else if (th.is(".last")) {
			$(".hid-form [name=pageNumber]").val(totalPage);
		}
		$(".hid-form>form").submit();
	});
	//点击第几页
	$(".page ul").on("click", "li", function() {
		var text = $(this).text();
		$(".hid-form [name=pageNumber]").val(parseInt(text));
		$(".hid-form>form").submit();
	});

});