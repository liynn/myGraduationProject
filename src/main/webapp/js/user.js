$(function(){
    getUserReadedBook();
    getAlsRecdBook();
});


function getUserReadedBook() {
    var url = window.location.pathname;
    $.post(url+"/readed", {
    }, function(data) {
        // $(".topReadBook").html("");
        var html = ""
        var length = data.list.length, i;
        for(i= 0; i< length; i++) {
            html = html+"<div class=\"col-md-2 col-sm-6 column5 topReadBook\">" +
                "<div class=\"thumbnail\">" +
            "<a href=\"/book/"+data.list[i].bookId+ "\" > <img alt=\"101x146\" src=\""+data.list[i].image+ "\"></a>" +
            // "<div class=\"caption\">" +
            "<a href=\"/book/\" " +data.list[i].bookId+ "><h3>" + data.list[i].title+"</h3>" +
            "</div></div>"
        }
        $("#user_readed_book").html(html);
    }, 'json');

}


function getAlsRecdBook() {
    var url = window.location.pathname;
    $.post(url+"/als", {
    }, function(data) {
        // $(".topReadBook").html("");
        var html = ""
        var length = data.list.length, i;
        for(i= 0; i< length; i++) {
            html = html+"<div class=\"col-md-2 col-sm-6 column5 topReadBook\">" +
                "<div class=\"thumbnail\">" +
                "<a href=\"/book/"+data.list[i].bookId+ "\" > <img alt=\"101x146\" src=\""+data.list[i].image+ "\"></a>" +
                // "<div class=\"caption\">" +
                "<a href=\"/book/\" " +data.list[i].bookId+ "><h3>" + data.list[i].title+"</h3>" +
                "</div></div>"
        }
        $("#als_recd_book").html(html);
    }, 'json');

}