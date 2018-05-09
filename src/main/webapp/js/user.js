$(function(){
    getUserReadedBook();
    getRecommendBook();
});


function getUserReadedBook() {
    var url = window.location.pathname;
    $.post(url+"/readed", {
    }, function(data) {
        // $(".topReadBook").html("");
        var html = ""
        var length = data.list.length, i;
        for(i= 0; i< length; i++) {
            var rating = data.list[i].rating * 10;
            html += "<div class=\"col-md-4 column\">\n" +
                "            <div class=\"media well\" style='height: 185px'>\n" +
                "                <a class=\"pull-left\" href=\"/book/" + data.list[i].bookId+ "\"><img src=\""+data.list[i].image+"\" class=\"media-object\" alt=\""+data.list[i].title+"\"  width=\"100\" /></a>\n" +
                "                    <div class=\"media-body\">\n" +
                "                    <h4 class=\"media-heading\"><a href=\"/book/"+data.list[i].bookId+ "\">"+ data.list[i].title +
                "                    </a></h4>\n" +
                "                    用户评分：<div class=\"ll bigstar"+rating+"\"></div>" +
                "                    <div>作者："+data.list[i].author+"</div>" +
                "                    <div>出版社："+ data.list[i].publisher +"</div>\n" +
                "                    <div>标签："+ data.list[i].tags +"</div>" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>"

        }
        html = "<h2>用户看过的书籍</h2>"+html;
        $("#user_readed_book").html(html);
    }, 'json');

}


function getRecommendBook() {
    var url = window.location.pathname;
    $.post(url+"/recommend", {
    }, function(data) {
        var html = ""
        var length = data.list.length, i;
        for(i= 0; i< length; i++) {

            html += "<div class=\"col-md-4 column\">\n" +
                "            <div class=\"media well\" style='height: 185px'>\n" +
                "                <a class=\"pull-left\" href=\"/book/" + data.list[i].bookId+ "\"><img src=\""+data.list[i].image+"\" class=\"media-object\" alt=\""+data.list[i].title+"\"  width=\"100\" /></a>\n" +
                "                    <div class=\"media-body\">\n" +
                "                    <h4 class=\"media-heading\"><a href=\"/book/"+data.list[i].bookId+ "\">"+ data.list[i].title +
                "                    </a></h4><br>\n" +
                // "                    用户：<div class=\"ll bigstar"+avg+"\"></div>" +
                "                    <div>作者："+data.list[i].author+"</div>" +
                "                    <div>出版社："+ data.list[i].publisher +"</div>\n" +
                "                    <div>标签："+ data.list[i].tags +"</div>" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>"

        }
        html = "<h2>推荐书籍</h2>"+html;
        $("#recommend").html(html);
    }, 'json');
}