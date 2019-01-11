/**
 用户登录
 */
$(function(){

    $("#codeImg").click(function () {
        $("#codeImg").attr("src","http://localhost:8080/public/api/user/getCode?"+Math.random());
    })

    $("#login").click(function () {
        $.ajax({
           url:"/public/api/user/auth",
            type:"POST",
            data:$("#login_form").serialize(),
            success:function (result) {
                if(result.status){
                    window.location.href="../index.html?uid="+result.data.uid;
                }else if (result.code==404){
                    window.location.href="../"+result.url;
                } else{
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
        console.log($("#login_form").serialize());

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
                    alert(result.msg);
                    window.location.href="../slogin.html";
                }else if (result.code==404){
                    window.location.href="../"+result.url;
                } else{
                    console.log(result.data);
                    $("#regist_feedback").html('<span style="color: red;margin-top: 10px;font-size: 150%;margin-left: 200px" >'+result.data[0]+'</span>');
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
        $("#loginId_fadeback").html("");
       var loginId = $(this).val().trim();
       console.log(loginId);
       if (loginId==""||loginId==undefined||loginId==null){
           $("#loginId_fadeback").html('<span style="color: red;margin-top: 10px;" class="glyphicon glyphicon-remove">&nbsp;用户名不能为空！</span>');
           return false;
       }
       var url = "/public/api/user/"+loginId;
       $.getJSON(url,function(result){
           $("#loginId_fadeback").html("");
            if(result.status){
                $("#loginId_fadeback").html('<span style="color: green;margin-top: 10px;" class="glyphicon glyphicon-ok">&nbsp;'+result.msg+'</span>');
            }else {
                if(result.msg!=null){
                    $("#loginId_fadeback").html('<span style="color: red;margin-top: 10px;" class="glyphicon glyphicon-remove">&nbsp;'+result.msg+'</span>');
                }else{
                    $("#loginId_fadeback").html('<span style="color: red;margin-top: 10px;" class="glyphicon glyphicon-remove">&nbsp;用户名不能为空！</span>');
                }
            }
       })
    });

    $("#username").blur(function () {
        var val=$("#username").val().trim();
        if (val==null||val==undefined||val==""){
            $("#username_feedback").html('<span style="color: red;margin-top: 10px;" class="glyphicon glyphicon-remove">&nbsp;昵称不能为空或空格！</span>');
        }else {
            $("#username_feedback").html('<span style="color: green;margin-top: 10px;" class="glyphicon glyphicon-ok">&nbsp;昵称可用</span>');
        }
    })

    $("#verifyCode").blur(function () {
        var val=$("#verifyCode").val().trim();
        if (val==null||val==undefined||val==""){
            alert("验证码不能为空！");
            return false;
        } else if (val.length!=4){
            alert("验证码只能为四位！");
            return false;
        }else {
            $.ajax({
                url:"/public/api/user/validate",
                type:"POST",
                data: {verifyCode:$("#verifyCode").val()},
                success: function(result){
                    if(result.status){
                        $("#code_feedback").html('<span style="color: green;margin-top: 10px;" class="glyphicon glyphicon-ok">&nbsp;'+result.msg+'</span>');
                    }else{
                        $("#code_feedback").html('<span style="color: red;margin-top: 10px;" class="glyphicon glyphicon-remove">&nbsp;'+result.msg+'</span>');
                    }
                }
            })
        }
    })

    $("#back").click(function () {
        window.location.href="../index.html";
    })
})