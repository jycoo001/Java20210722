$(function() {
	//提示
	if (detail) {
		layer.alert(detail);
	}

	//执行一个laydate实例
	laydate.render({
		elem: '#test1' //指定元素
	});
});