$(function() {
	//提示
	if (detail) {
		layer.alert(detail);
	}


	if (!id) {
		$("form")[0].action = path + "insert";
	} else {
		$("form")[0].action = path + "update";
	}
	$("form [name=name]").val(name);
	$("form [name=sex]").val(sex);
	$("form [name=birthday]").val(birthday);
	$("form [name=salary]").val(salary);
	$("form [name=name]").val();

	lay('#version').html('-v' + laydate.v);
	//执行一个laydate实例
	laydate.render({
		elem: '#test1' //指定元素
	});
});