var index_state = new Object();
index_state.page = 1;
index_state.pageCount = 1;

$(function(){
    init(1);

    /*翻页*/
    $('#pageOp nav ul li.pageC').click(function() {
        var page = index_state.page;
        var operate = ($(this).attr('id')==='next')?1:0;
        var state = index_state.pageCount;
        if(operate) {
            $('#pageOp nav ul li.pageC').removeClass('disabled');
            if(page < state){
                if(page == state-1){
                    $(this).addClass('disabled');
                }
                init(page+1);
            }
        }else {
            $('#pageOp nav ul li.pageC').removeClass('disabled');
            if(page-1 > 0){
                if(page == 2){
                    $(this).addClass('disabled');
                }
                init(page-1);
            }
        }
        pageDeal();
    });
    /*选页*/
    $('#pageOp nav ul li.pageN').click(function() {
        $('#pageOp nav ul li.pageC').removeClass('disabled');
        var page = $(this).text();
        init(page);
        if(page == 1){
            $('#pageOp nav ul li.pageC:eq(0)').addClass('disabled');
        }else if(page == index_state.pageCount) {
            $('#pageOp nav ul li.pageC:eq(1)').addClass('disabled');
        }
        pageDeal();
    })
});


/*加载评分数量高的书籍*/
function init(pageNum) {
    index_state.page = parseInt(pageNum);
    var url = window.location.pathname;
    if(url.indexOf("search") >= 0 ) {
        var str = getUrlParam("str");
        url="/book/search";
    }else if(url.indexOf("topRead") >= 0 ) {
        $("title").html("阅读数排行榜");
        $("#title").html("<h1>阅读数排行榜</h1>");
        url="/book/topRead";
    }else if(url.indexOf("topRating") >= 0 ) {
        $("title").html("评分排行榜");
        $("#title").html("<h1>评分排行榜</h1>");
        url="/book/topRating";
    }else if(url.indexOf("tag") >= 0 ) {
        var url_2 = window.location.pathname;
        url="/book"+url_2;
    }
    // var title = url.split("=")[1];
    // alert(title)

    $.post(url, {
        str: str,
        pageNum: pageNum,
        pageSize: 10
    }, function(data) {
        if (data.success==false)
            return;
        $('#pageOp').show()
        // $(".topReadBook").html("");
        var html = ""
        var length = data.list.length, i;
        if(url=="/book/search"){
            str = data.str;
            $("title").html("搜索\""+str+"\"的结果");
            $("#title").html("<h1>搜索\""+str+"\"</h1>");
            $("#search_name").attr("placeholder", str);
        }else if(url.indexOf("tag") >= 0 ){
            str = data.str;
            if (str=="@index"){
                $("title").html("标签");
                $("#pageOp").hide();
                return;
            }else {
                $("title").html("标签\""+str+"\"");
                $("#title").html("<h1>标签\""+str+"\"</h1>");
            }

        }
        if (length==0 && url=="/book/search"){
            html = "<p>没有找到关于\" " +str+ " \"的相关内容，换个关键字试试</p>";

            $("#search_content").html(html);
            $("#pageOp").hide();
        }else {
            for(i= 0; i< length; i++) {
                var avg = data.list[i].avgRating * 10;
                var remainder = avg%10;
                if (remainder<2)
                    avg = avg-remainder;
                else if (remainder>8)
                    avg = avg-remainder+10;
                else
                    avg = avg-remainder+5;
                $("#rating_star").addClass("bigstar" + avg);



                html += "<div class=\"col-md-6 column\">\n" +
                    "            <div class=\"media well\" style='height: 185px'>\n" +
                    "                <a class=\"pull-left\" href=\"/book/" + data.list[i].bookId+ "\"><img src=\""+data.list[i].image+"\" class=\"media-object\" alt=\""+data.list[i].title+"\"  width=\"100\" /></a>\n" +
                    "                    <div class=\"media-body\">\n" +
                    "                    <h4 class=\"media-heading\"><a href=\"/book/"+data.list[i].bookId+ "\">"+ data.list[i].title +
                    "                    </a></h4>\n" +
                    "                    <div class=\"ll bigstar"+ avg+"\"></div><span class=\"rating_nums\">"+data.list[i].avgRating+"</span>("+data.list[i].total+"人评价)\n" +
                    "                    <p>作者："+data.list[i].author+" <br>出版社："+ data.list[i].publisher +"</p>\n" +
                    "                    <p>"+ data.list[i].summary.substr(0, 80) +"...</p>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </div>"

            }
            $("#search_content").html(html);

            index_state.pageCount = data.pageCount;
            pageDeal();
        }



    }, 'json');

}


/*分页的一些细节*/
function pageDeal() {
    $('.pageN a').show();
    var page = index_state.page, pageCount = index_state.pageCount, i = -4;
    var arr = [];
    while(i <= 4){
        arr.push(page + i);
        i++;
    }
    i = 8;
    while(arr[0] <= 0){
        arr = arr.map(function(x){
            return x+1;
        });
    }
    while(arr[8] > pageCount){
        arr = arr.map(function(x){
            return x-1;
        });
    }
    $('.pageN').removeClass('active');
    while(i >= 0){
        if(arr[i]<=0) {
            $('.pageN a:eq('+ i +')').hide();
        }else {
            $('.pageN a:eq('+ i +')').html(arr[i]);
            if(arr[i] == page){
                $('.pageN:eq('+ i +')').addClass('active');
            }
        }
        i--;
    }
}


//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg); //匹配目标参数
    if (r != null)
        return unescape(r[2]);
    return null; //返回参数值
}