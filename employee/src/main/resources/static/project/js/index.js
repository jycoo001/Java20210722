$(function() {
	if (detail) {
		layer.alert(detail);
	}



	$(".div").on("click", function() {
		location.href = path + "list";
	});

});