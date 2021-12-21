$(function() {
	if (detail) {
		layer.alert(detail);
	}
	$("#imageCode").click(function() {
		$("#imageCode").attr("src", path+"code?"+Math.random());
	});
});