//用户模块api调用
$(function(){

    /**
     * 用户登录
     */
    $("#login").click(function () {
        $.ajax({
           url:"/public/api/user/auth",
            type:"POST",
            data:$("#login_form").serialize(),
            success:function (result) {
                if(result.status){
                    window.location.href="../index.html?uid="+result.data.uid;
                }else{
                    $("#errMsg").html("");
                    if(result.data != null){
                        $("#errMsg").html('<span style="color: red;margin-left: 70px;">'+result.data+'</span>');
                    }else{
                        $("#errMsg").html('<span style="color: red;margin-left: 70px;">'+result.msg+'</span>');
                    }
                    $("#username").val("");
                    $("#inputPassword3").val("");
                }
            }
        });
        return false;
    });
    /**
     * 用户注册
     */
    $("#submit").click(function () {
        $.ajax({
            url:"/public/api/user",
            type:"POST",
            data: $("#register_form").serialize(),
            success: function(result){
                if(result.status){
                    alert("oh ni gei,安排上了！");
                    window.location.href="../slogin.html";
                }else{
                    alert(result.msg);
                }
            }
        });
        return false;
    });

    /**
     * 密码比对
     */
    $("#confirmpwd").blur(function(){
        $("#pwd_fadeback").html("");
        if($(this).val()!=$("#inputPassword3").val()){
             $("#pwd_fadeback").html('<span style="color: red;margin-top: 10px;" class="glyphicon glyphicon-remove">&nbsp;两次输入密码不一致！</span>');
        }
    });

    /*
      * loginId校验
     */
    $("#loginid").blur(function () {
       var loginId = $(this).val();
       var url = "/public/api/user/"+loginId;
       $.getJSON(url,function(result){
           $("#loginId_fadeback").html("");
            if(result.status){
                $("#loginId_fadeback").html('<span style="color: green;margin-top: 10px;" class="glyphicon glyphicon-ok">&nbsp;'+result.msg+'</span>');
            }else {
                if(result.msg!=null){
                    $("#loginId_fadeback").html('<span style="color: red;margin-top: 10px;" class="glyphicon glyphicon-remove">&nbsp;'+result.msg+'</span>');
                }else{
                    $("#loginId_fadeback").html('<span style="color: red;margin-top: 10px;" class="glyphicon glyphicon-remove">&nbsp;输入内容不能为空！</span>');
                }
            }
       })
    });

})