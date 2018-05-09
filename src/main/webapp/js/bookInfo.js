//用户评分分页
var index_state = new Object();
index_state.page = 1;
index_state.pageCount = 1;

$(function(){
    initBookInfo();
    initUserRating();


    $('#star1').click(function() {
        rating(1);
    });
    $('#star2').click(function() {
        rating(2);
    });
    $('#star3').click(function() {
        rating(3);
    });
    $('#star4').click(function() {
        rating(4);
    });
    $('#star5').click(function() {
        rating(5);
    });

});


/*加载评分数量高的书籍*/
//1000072
//2039931
function initBookInfo() {
    var url = window.location.pathname;
    $.post(url, {
    }, function(data) {
        /*左上角图书信息填充*/
        initBookBaseInfo(data);
        /*右上角评分信息填充*/
        initRatingInfo(data);

        /*书籍标签*/
        initTag(data);

        //初始化书籍介绍
        initBookIntro(data);

        initAuthorIntro(data);

        initCatalog(data);

        initBuyInfo(data);

        initAllUserRating(1);

        getSimilarityBook();

    }, 'json');

}

/*左上角书籍信息填充*/
function initBookBaseInfo (data) {
    $("title").html(data.title);

    //左上角书籍信息数据

    $("#title").text(data.title);
    $("#mainpic a").attr({
        "href" : data.image,
        "title" : data.title
    });
    $("#mainpic a img").attr({
        "src" : data.image,
        "alt" : data.title
    });
    var translator="";
    if(data.translator!="")
        translator = "<span class=\"pl\">译者:</span> " + data.translator+"<br>\n";

    var html =
        "                                <span>\n" +
        "                                    <span class=\"pl\"> 作者</span>:\n" +data.author +
        "                                </span>\n" +
        "                                <br>\n" +
        "                                <span class=\"pl\">出版社:</span> " + data.publisher + "<br>\n" +
        "                                <span class=\"pl\">出版年:</span>" + data.pubdate + "<br>\n" + translator +
        "                                <span class=\"pl\">页数:</span> " + data.pages + "<br>\n" +
        "                                <span class=\"pl\">定价:</span> " + data.price + "<br>\n" +
        "                                <span class=\"pl\">装帧:</span> 平装<br>\n" +
        "                                <span class=\"pl\">ISBN:</span> 缺失 <br>";

    $("#info").html(html);
}

/*右上角评分信息填充*/
function initRatingInfo (data) {
//右上角评分数据
    $("#avg_raitng").text(data.avgRating);
    $(".rating_people span").text(data.total);

    $(".rating_per:eq(0)").text(data.ratio5 + "%");
    $(".rating_per:eq(1)").text(data.ratio4 + "%");
    $(".rating_per:eq(2)").text(data.ratio3 + "%");
    $(".rating_per:eq(3)").text(data.ratio2 + "%");
    $(".rating_per:eq(4)").text(data.ratio1 + "%");

    $(".power:eq(0)").attr("style", "width:" + data.ratio5 + "px");
    $(".power:eq(1)").attr("style", "width:" + data.ratio4 + "px");
    $(".power:eq(2)").attr("style", "width:" + data.ratio3 + "px");
    $(".power:eq(3)").attr("style", "width:" + data.ratio2 + "px");
    $(".power:eq(4)").attr("style", "width:" + data.ratio1 + "px");

    var avg = data.avgRating * 10;
    var remainder = avg%10;
    if (remainder<2)
        avg = avg-remainder;
    else if (remainder>8)
        avg = avg-remainder+10;
    else
        avg = avg-remainder+5;
    $("#rating_star").addClass("bigstar" + avg);
}

//初始化书籍标签
function initTag(data){
    var tags = data.tags.split(",");
    var html = " <ul class=\"clearfix hot-tags-col5 s\">";
    var length = tags.length;
    for(i= 0; i< length; i++) {

        html += "<li>" +
                "  <a href=\"/tag/" +tags[i]+ "\" class=\"tag\">" +tags[i]+ "</a>" +
                "</li>"
    }
    html += "</ul>";
    $("#tag").html(html);

}

//初始化书籍介绍
function initBookIntro(data) {
    if(data.summary.length > 200){
        var intro_short = data.summary.substr(0, 200)
            + "<p>· · · · · ·(<a href=\"javascript:$('#intro_short').hide();$('#intro_full').show();void(0);\">更多</a>)</p>";
        $("#intro_short div").html(intro_short);

        var intro_full = data.summary + "<p>· · · · · ·(<a href=\"javascript:$('#intro_full').hide();$('#intro_short').show();void(0);\">收起</a>)</p>";
        $("#intro_full div").html(intro_full);
    }else if (data.summary==""){
        $("#intro_short div").text("暂无");
    }else {
        $("#intro_short div").html(data.summary);
    }
}


//初始化书籍介绍
function initAuthorIntro(data) {
    if(data.authorIntro!=""){
        $("#author_intro_content").html(data.authorIntro);
    }else
        $("#author_intro_content").text("无");
}

function initCatalog(data) {
    data.catalog.replace()
    if(data.catalog.length > 200){
        var catalog_short =
            // toShort(data.catalog, 200)
            data.catalog.substr(0, 200)
            + "<p>· · · · · ·(<a href=\"javascript:$('#catalog_short').hide();$('#catalog_full').show();void(0);\">更多</a>)</p>";
        $("#catalog_short").html(catalog_short);

        var catalog_full = data.catalog + "<p>· · · · · ·(<a href=\"javascript:$('#catalog_full').hide();$('#catalog_short').show();void(0);\">收起</a>)</p>";
        $("#catalog_full").html(catalog_full);
    }else if (data.catalog==""){
        $("#catalog_row").hide();
    }else {
        $("#catalog_short").html(data.catalog);
    }
}

//购买链接
function initBuyInfo(data) {
    $("#buy_info li:eq(0) a").attr("href","http://opac.lib.szu.edu.cn/opac/searchresult.aspx?anywords=" +data.title+ "&ecx=1&efz=1&dt=ALL&cl=ALL&dp=20&sf=M_PUB_YEAR&ob=DESC&sm=table&dept=ALL&code=utf-8");
    $("#buy_info li:eq(1) a").attr("href","https://search.jd.com/Search?keyword=" + data.title + "&enc=utf-8&wq=" + data.title);
    $("#buy_info li:eq(2) a").attr("href","http://search.dangdang.com/?key="+ data.title +"&act=input");
    $("#buy_info li:eq(3) a").attr("href","https://s.taobao.com/search?q="+ data.title +"&commend=all&search_type=item&cat=33");
}

function initAllUserRating(pageNum) {
    index_state.page = pageNum;
    var url = window.location.pathname;
    var id = url.substring(url.lastIndexOf("/")+1);
    $.post("/book/getUserRatingByBookId", {
        bookId: id,
        pageNum: pageNum,
        pageSize: 10
    }, function(data) {
        // $(".topReadBook").html("");
        var html = "<a href=\"#\" class=\"list-group-item active\">已评分用户<span class=\"badge\">"+data.rowCount+"人</span> </a>"
        var length = data.list.length, i;
        var userId, pic;
        for (i= 0; i< length; i++){
            userId =  data.list[i].userId;
            pic = "/image/pic/" + (userId%12+1)+".png";
            var starClass = data.list[i].rating*10;
            html += "<div class=\"list-group-item\">\n" +
                "                        <div class=\"\">\n" +
                "                            <div class=\"ll\"><a href=\"/user/"+data.list[i].userId+"\"><img src=\"" + pic + "\" height=\"48\" width=\"48\" class=\"pil\" alt=\"" + data.list[i].nickName + "\"></a></div>\n" +
                "                            <div style=\"padding-left:60px\"><a class=\"\" href=\"/user/"+data.list[i].userId+"\">" + data.list[i].nickName + "</a><br>\n" +
                "                                <!--<span style=\"float: left\">打分：</span>-->\n" +
                "                                <div class=\"ll bigstar"+starClass+"\"></div>\n" +
                "                                <br>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>"
        }

        if (data.pageCount>1){
            var str = " <button class='btn' onclick=\"initAllUserRating("+(pageNum+1)+")\" id='nextPage'>下一页</button>";
            if (pageNum!=1){
                str += " <button class='btn' style='float: left' onclick=\"initAllUserRating("+(pageNum-1)+")\" id='prePage'>上一页</button>";
            }

            html += "<div class=\"list-group-item\">\n" + str + "</div>"
            index_state.pageCount = data.pageCount;
        }
        $("#user_rating").html(html);

    }, 'json');

}


function rating(rating) {
    var url = window.location.pathname;
    var id = url.substring(url.lastIndexOf("/")+1);
    $.post("/user/rating", {
        bookId: id,
        rating: rating,
    }, function(data) {
        if(data.success==true)
            alert("评分成功");
        else
            alert(data.info);

    }, 'json');

}

function initUserRating() {
    var url = window.location.pathname;
    $.post("/user"+url, {
    }, function(data) {
        if(data.success==true){
            $("#interest_sect_level").hide();

            $("#userRating").html("你对此书的评分<div class=\" bigstar" + data.data*10 + "\"></div>");
        }
    }, 'json');

}


/*加载相似书籍*/
function getSimilarityBook() {
    var url = window.location.pathname;
    var id = url.substring(url.lastIndexOf("/")+1);
    $.post("/book/similarity/"+id, {
    }, function(data) {
        var html = ""
        var length = data.list.length, i;
        if (length==0){
            $("#similarity_book").html("暂无");
            return;
        }
        for(i= 0; i< length; i++) {
            html = html+"<div class=\"col-md-3 col-sm-6 column5\">" +
                "<div class=\"thumbnail\">" +
                "<a href=\"/book/"+data.list[i].bookId+ "\" > <img alt=\"101x146\" src=\""+data.list[i].image+ "\"></a>" +
                "<a href=\"/book/" +data.list[i].bookId+ "\"><p>" + data.list[i].title+"</p></a>" +
                "</div></div>"
        }
        $("#similarity_book").html(html);
    }, 'json');

}