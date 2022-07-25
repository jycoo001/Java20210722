$(function() {
    $(".prev").click(function() {
        var value =  +$("input[name=pageNumber]").val() -1;
        $("input[name=pageNumber]").val(value);
        $(".hidden>form").submit();
    });
    $(".pageN").click(function() {
        var value = $(this).text();
        $("input[name=pageNumber]").val(value);
        $(".hidden>form").submit();
    });
    $(".next").click(function() {
        var value = +$("input[name=pageNumber]").val()+1;
        $("input[name=pageNumber]").val(value);
        $(".hidden>form").submit();
    });
});