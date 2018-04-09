

$(function(){
    if($("#userId").val()==""){
        $("#userId").val("3")
        formatRecommendBooks();
    }
    $('#getRecdBooksByUserId').click(formatRecommendBooks);
});



/*加载用户已经看过的书籍*/
function formatReadedBooks() {
    var userId = $("#userId").val();
    $.get("/getReadedBooksByUserId", {
        userId: userId,
    }, function(data) {
        $(".userReadedBook").html("");
        var html = ""
        var length = data.readedBook.length, i;
        for(i= 0; i< length; i++) {
            html = html+
                "<dl class=\"readedBook\">\n" +
                "            <dt>\n" +
                "                <a href=\"https://book.douban.com/subject/"+data.readedBook[i].id+"\" onclick=\"moreurl(this, {'total': 10, 'clicked': '30143057', 'pos': 0, 'identifier': 'book-rec-books'})\">" +
                "                <img class=\"m_sub_img\" src=\""+data.readedBook[i].image+"\"></a>\n" +
                "            </dt>\n" +
                "            <dd>\n" +
                "                <a href=\"https://book.douban.com/subject/"+data.readedBook[i].id+" \" onclick=\"moreurl(this, {'total': 10, 'clicked': '30143057', 'pos': 0, 'identifier': 'book-rec-books'})\" class=\"\">\n"
                +                   data.readedBook[i].title+
                "                </a>\n" +
                "                <button type=\"button\" class=\"btn-xs btn-info\">"+data.readedBook[i].rating+"分</button>\n" +
                "            </dd>\n" +
                "</dl>"
            if ((i+1)%5==0){
                html = html+"<dl class=\"clear\"></dl>";
            }
        }
        if(i == 0) {
            html="<h3>该用户没有看过的书籍</h3>"
        }
        $(".userReadedBook").html(html);
    }, 'json');
}


}
