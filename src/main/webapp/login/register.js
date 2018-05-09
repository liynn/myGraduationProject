
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
        var nickName = $.trim($("#form-nickname").val());
        var password2 = $.trim($("#form-password2").val());

        if(userName==""){
            alert("请输入您的账号");
            $("#form-username").focus();
            return;
        }
        if(nickName==""){
            alert("请输入您的昵称");
            $("#form-nickname").focus();
            return;
        }
        if(password=="" || password2==""){
            alert("请输入密码");
            $("#form-password").focus();
            return;
        }
        if(password != password2){
            alert("两次密码不一致");
            $("#form-password").focus();
            return;
        }
        $.post("/user/register",{
            userName:userName,
            nickName:nickName,
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
