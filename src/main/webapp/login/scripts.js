
jQuery(document).ready(function() {
	
    /*
        Fullscreen background
    */
    $.backstretch([
                    "/login/2.jpg"
	              , "/login/3.jpg"
	              , "/login/1.jpg"
	             ], {duration: 3000, fade: 750});
    

    $("button").click(function(){
        var userName = $.trim($("#form-username").val());
        var password = $.trim($("#form-password").val());

        if(userName==""){
            alert("请输入您的账号");
            $("#userName").focus();
            return;
        }
        if(password==""){
            alert("请输入密码");
            $("#password").focus();
            return;
        }

        $.post("/user/login",{
            userName:userName,
            password:password
        },function(data){
            console.log("data");
            if(data.success!=true){
                alert(data.info || "用户名或者账号错误，请重新登录");
                $("#password").val("");
            }
            else{
                window.location.href = "/";
            }
        },"json");
    });
    
    
});
