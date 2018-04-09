$(function(){
    initBookInfo();
});


/*加载评分数量高的书籍*/
//1000072
//2039931
function initBookInfo() {
    $.get("/book/1000034", {
    }, function(data) {
        /*左上角图书信息填充*/
        initBookBaseInfo(data);
        /*右上角评分信息填充*/
        initRatingInfo(data);

        //初始化书籍介绍
        initBookIntro(data);

        initAuthorIntro(data);

        initCatalog(data);

    }, 'json');

}

/*左上角书籍信息填充*/
function initBookBaseInfo (data) {

    //左上角书籍信息数据
    $("h1 span").text(data.title);
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
        "                                    <span class=\"pl\"> 作者</span>:\n" +
        "                                    <a class=\"\" href=\"https://book.douban.com/search/" + data.author + "\"> "+data.author+" </a>\n" +
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

//初始化书籍介绍
function initBookIntro(data) {
    if(data.summary.length > 200){
        var intro_short = data.summary.substr(0, 200)
            + "<p>· · · · · ·(<a href=\"javascript:$('#intro_short').hide();$('#intro_full').show();void(0);\">更多</a>)</p>";
        $("#intro_short div").text(intro_short);

        var intro_full = data.summary + "<p>· · · · · ·(<a href=\"javascript:$('#intro_full').hide();$('#intro_short').show();void(0);\">收起</a>)</p>";
        $("#intro_full div").text(intro_full);
    }else if (data.summary==""){
        $("#intro_short div").text("暂无");
    }else {
        $("#intro_short div").text(data.summary);
    }
}


//初始化书籍介绍
function initAuthorIntro(data) {
    if(data.authorIntro!=""){
        $("#author_intro_content").html(data.authorIntro);
    }else
        $("#author_intro").hide();
}

function initCatalog(data) {
    if(data.catalog.length > 200){
        var catalog_short = toShort(data.catalog, 300)
            + "<p>· · · · · ·(<a href=\"javascript:$('#catalog_short').hide();$('#catalog_full').show();void(0);\">更多</a>)</p>";
        $("#catalog_short").html(catalog_short);

        var catalog_full = data.catalog + "<p>· · · · · ·(<a href=\"javascript:$('#catalog_full').hide();$('#catalog_short').show();void(0);\">收起</a>)</p>";
        $("#intro_full div").html(catalog_full);
    }else if (data.catalog==""){
        $("#catalog_short").hide();
    }else {
        $("#catalog_short").html(data.catalog);
    }
}


function toShort( str, len) {
    var arr = str.splice("<br/>")
    var length = 0;
    var result = "";
    for (i in arr){
        var a = arr[i];
        if (length + a.length <len){
            result += a + "<br/>";
            length += a.length;
        }
    }
    return result;
}
