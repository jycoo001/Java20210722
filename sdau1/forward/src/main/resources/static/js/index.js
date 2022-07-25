$(function() {
    //跳转我的
    $(".first>div,.bod>div").click(function () {
        var url = $(this).attr("data-url");
        console.log(url);
        location.href=url;
    });

    $(".first>span").click(function () {
        location.href="/index";
    });
});