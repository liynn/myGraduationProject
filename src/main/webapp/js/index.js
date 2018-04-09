$(function(){
    getTopReadBook();
    getTopRatingBook();
});


/*加载评分数量高的书籍*/
function getTopReadBook() {
    $.post("/book/topRead?pageNum=1&pageSize=10", {
    }, function(data) {
        // $(".topReadBook").html("");
        var html = ""
        var length = data.list.length, i;
        for(i= 0; i< length; i++) {
            html = html+"<div class=\"col-md-3 col-sm-6 column5 topReadBook\">" +
                "<div class=\"thumbnail\">" +
            "<a href=\"/book/"+data.list[i].bookId+ "\" > <img alt=\"101x146\" src=\""+data.list[i].image+ "\"></a>" +
            // "<div class=\"caption\">" +
            "<a href=\"/book/\" " +data.list[i].bookId+ "><h3>" + data.list[i].title+"</h3>" +
            "</div></div>"
        }
        $("#topReadBook_body").html(html);
    }, 'json');

}


/*加载评分高的书籍*/
function getTopRatingBook() {
    $.post("/book/topRating?pageNum=1&pageSize=10", {
    }, function(data) {
        // $(".topReadBook").html("");
        var html = ""
        var length = data.list.length, i;
        for(i= 0; i< length; i++) {
            html = html+"<div class=\"col-md-3 col-sm-6 column5 topReadBook\">" +
                "<div class=\"thumbnail\">" +
                "<a href=\"/book/"+data.list[i].bookId+ "\" > <img alt=\"101x146\" src=\""+data.list[i].image+ "\"></a>" +
                // "<div class=\"caption\">" +
                "<a href=\"/book/\" " +data.list[i].bookId+ "><h3>" + data.list[i].title+"</h3>" +
                "</div></div>"
        }
        $("#topRatingBook_body").html(html);
    }, 'json');

}