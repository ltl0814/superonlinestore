/**
 用户登录
 */
$(function(){

    var verifycodevalidate = false;

    if ($.cookie("rmbUser") == "true") {
        $("#rememberMe").attr("checked", true);
        $("#username").val($.cookie("username"));
    }

    $("#codeImg").click(function () {
        $("#codeImg").attr("src","http://localhost:8080/public/api/user/getCode?"+Math.random());
    })

    // 用户登陆
    $("#login").click(function () {
        if(verifycodevalidate){
            $.ajax({
                url:"/public/api/user/auth",
                type:"POST",
                data:$("#login_form").serialize(),
                success:function (result) {
                    if(result.status){
                        save();
                        window.location.href="../index.html?uid="+result.data.uid;
                    } else{
                        $("#errMsg").html("");
                        if(result.data != null){
                            $("#errMsg").html('<span style="color: red;margin-left: 70px;">'+result.data+'</span>');
                        }else{
                            $("#errMsg").html('<span style="color: red;margin-left: 70px;">'+result.msg+'</span>');
                        }
                        $("#verifyCode").val("");
                        $("#inputPassword3").val("");
                        $("#codeImg").attr("src","http://localhost:8080/public/api/user/getCode?"+Math.random());
                    }
                }
            });
            verifycodevalidate=false;
            console.log($("#login_form").serialize());
            console.log(verifycodevalidate);
            return false;
        }else {
            alert("验证码不正确！")
            return false;
        }
    });

    /**
     * 用户注册
     */
    $("#submit").click(function () {
        if(verifycodevalidate) {
            $.ajax({
                url: "/public/api/user",
                type: "POST",
                data: $("#register_form").serialize(),
                success: function (result) {
                    if (result.status) {
                        alert("注册成功！");
                        verifycodevalidate = false;
                        window.location.href = "../slogin.html";
                    } else {
                        console.log("1111");
                        $("#regist_feedback").html('<span style="color: red;margin-top: 10px;font-size: 150%;margin-left: 200px" >' + result.data[0] + '</span>');
                    }
                },
                error: function(XMLHttpRequest, textStatus,errorThrown) {
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                    },
            });
        }else {
            alert("验证码不正确！");
            return false;
        }
    });

    /**
     * 密码比对
     */
    $("#confirmpwd").blur(function(){
        $("#pwd_feedback").html("");
        if($(this).val()!=$("#inputPassword3").val()){
             $("#pwd_feedback").html('<span style="color: red;margin-top: 10px;" class="glyphicon glyphicon-remove">&nbsp;两次输入密码不一致！</span>');
        }else {
            $("#pwd_feedback").html('<span style="color: green;margin-top: 10px;" class="glyphicon glyphicon-ok">&nbsp;两次密码一致！</span>')
        }
    });

    /**
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
                        verifycodevalidate = true;
                        $("#code_feedback").html('<span style="color: green;margin-top: 10px;" class="glyphicon glyphicon-ok">&nbsp;'+result.msg+'</span>');
                    }else{
                        verifycodevalidate = false;
                        $("#code_feedback").html('<span style="color: red;margin-top: 10px;" class="glyphicon glyphicon-remove">&nbsp;'+result.msg+'</span>');
                    }
                }
            })
        }
    })

    $("#back").click(function () {
        window.location.href="../index.html";
    })

    //记住用户名密码
    function save() {
        if ($("#rememberMe").prop("checked")) {
            var str_username = $("#username").val();//用户名
            var str_password = $("#inputPassword3").val();//密码
            alert(str_username);
            $.cookie("rmbUser", "true", { expires: 7 }); //存储一个带7天期限的cookie
            $.cookie("username", str_username, { expires: 7 });
            $.cookie("password", str_password, { expires: 7 });
        } else {
            $.cookie("rmbUser", "false", { expire: -1 });
            $.cookie("username", "", { expires: -1 });
            $.cookie("password", "", { expires: -1 });
        }
    };
})