$(function() {
	if (detail) {
		layer.alert(detail);
	}

	if(password) {
		$(".password").css("display","block");
		$(".login>form>.div-but>button.blue").text("修改");
	} else {
		$(".password").css("display","none");
		$(".login>form>.div-but>button.blue").text("验证");
	}
});