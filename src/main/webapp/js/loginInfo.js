$(function(){
    initUserInfo();
});


function initUserInfo() {
    $.post("/user/info", {
    }, function(data) {
        if(data.success==true){
            $("#nav_1").html(" <a href=\"/user/"+data.data.userId+"\">个人中心</a>");
            $("#nav_2").html(" <a href=\"/logout\">注销</a>");
        }
    }, 'json');

}

