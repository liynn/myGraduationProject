$(function(){
    getUserReadedBook();
    // getAlsRecdBook();
    // getUserCFBook();
    // getItemCFBook();
    getAlsRecdBook1();
    getUserCFBook1();
    getItemCFBook1();
});


function getUserReadedBook() {
    var url = window.location.pathname;
    $.post(url.replace("/all","")+"/readed", {
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


function getAlsRecdBook() {
    var url = window.location.pathname;
    $.post(url.replace("/all","")+"/als", {
    }, function(data) {
        var html = ""
        var length = data.list.length, i;
        for(i= 0; i< length; i++) {
            html += "<div class=\"col-md-4 column\">\n" +
                "            <div class=\"media well\" style='height: 185px'>\n" +
                "                <a class=\"pull-left\" href=\"/book/" + data.list[i].bookId+ "\"><img src=\""+data.list[i].image+"\" class=\"media-object\" alt=\""+data.list[i].title+"\"  width=\"100\" /></a>\n" +
                "                    <div class=\"media-body\">\n" +
                "                    <h4 class=\"media-heading\"><a href=\"/book/"+data.list[i].bookId+ "\">"+ data.list[i].title +
                "                    </a></h4><br>" +
                // "                    用户：<div class=\"ll bigstar"+avg+"\"></div>" +
                "                    <div>作者："+data.list[i].author+"</div>" +
                "                    <div>出版社："+ data.list[i].publisher +"</div>\n" +
                "                    <div>标签："+ data.list[i].tags +"</div>" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>"
        }
        html = "<h2>ALS推荐</h2>"+html;
        $("#als_recd_book").html(html);
    }, 'json');

}

function getUserCFBook() {
    var url = window.location.pathname;
    $.post(url.replace("/all","") + "/userCF", {}, function (data) {
        var html = ""
        var length = data.list.length, i;
        for (i = 0; i < length; i++) {
            var avg = data.list[i].rating * 10;
            var remainder = avg % 10;
            if (remainder < 2)
                avg = avg - remainder;
            else if (remainder > 8)
                avg = avg - remainder + 10;
            else
                avg = avg - remainder + 5;

            html += "<div class=\"col-md-4 column\">\n" +
                "            <div class=\"media well\" style='height: 185px'>\n" +
                "                <a class=\"pull-left\" href=\"/book/" + data.list[i].bookId + "\"><img src=\"" + data.list[i].image + "\" class=\"media-object\" alt=\"" + data.list[i].title + "\"  width=\"100\" /></a>\n" +
                "                    <div class=\"media-body\">\n" +
                "                    <h4 class=\"media-heading\"><a href=\"/book/" + data.list[i].bookId + "\">" + data.list[i].title +
                "                    </a></h4><br>" +
                // "                    用户：<div class=\"ll bigstar" + avg + "\"></div>" +
                "                    <div>作者：" + data.list[i].author + "</div>" +
                "                    <div>出版社：" + data.list[i].publisher + "</div>\n" +
                "                    <div>标签：" + data.list[i].tags + "</div>" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>"

        }
        html = "<h2>userCF推荐</h2>" + html;
        $("#userCF_recd_book").html(html);
    }, 'json');
}

function getItemCFBook() {
    var url = window.location.pathname;
    $.post(url.replace("/all","")+"/itemCF", {
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
        html = "<h2>itemCF推荐</h2>"+html;
        $("#itemCF_recd_book").html(html);
    }, 'json');
}



function getAlsRecdBook1() {
    var url = window.location.pathname;
    $.post(url.replace("/all","")+"/als", {
    }, function(data) {
        var html = ""
        var length = data.list.length, i;
        for(i= 0; i< length; i++) {
            html = html+"<div class=\"col-md-3 col-sm-6 column5\">" +
                "<div class=\"thumbnail\">" +
                "<a href=\"/book/"+data.list[i].bookId+ "\" > <img alt=\"101x146\" src=\""+data.list[i].image+ "\"></a>" +
                "<a href=\"/book/" +data.list[i].bookId+ "\"><p>" + data.list[i].title+"</p></a>" +
                "</div></div>"
        }
        html = "<h2>ALS推荐</h2>"+html;
        $("#als_recd_book").html(html);
    }, 'json');

}

function getUserCFBook1() {
    var url = window.location.pathname;
    $.post(url.replace("/all","") + "/userCF", {}, function (data) {
        var html = ""
        var length = data.list.length, i;
        for(i= 0; i< length; i++) {
            html = html+"<div class=\"col-md-3 col-sm-6 column5\">" +
                "<div class=\"thumbnail\">" +
                "<a href=\"/book/"+data.list[i].bookId+ "\" > <img alt=\"101x146\" src=\""+data.list[i].image+ "\"></a>" +
                "<a href=\"/book/" +data.list[i].bookId+ "\"><p>" + data.list[i].title+"</p></a>" +
                "</div></div>"
        }
        html = "<h2>userCF推荐</h2>" + html;
        $("#userCF_recd_book").html(html);
    }, 'json');
}

function getItemCFBook1() {
    var url = window.location.pathname;
    $.post(url.replace("/all","")+"/itemCF", {
    }, function(data) {
        var html = ""
        var length = data.list.length, i;
        for(i= 0; i< length; i++) {
            html = html+"<div class=\"col-md-3 col-sm-6 column5\">" +
                "<div class=\"thumbnail\">" +
                "<a href=\"/book/"+data.list[i].bookId+ "\" > <img alt=\"101x146\" src=\""+data.list[i].image+ "\"></a>" +
                "<a href=\"/book/" +data.list[i].bookId+ "\"><p>" + data.list[i].title+"</p></a>" +
                "</div></div>"
        }
        html = "<h2>itemCF推荐</h2>"+html;
        $("#itemCF_recd_book").html(html);
    }, 'json');
}