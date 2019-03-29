$(function () {

    var loginid = getQueryString("loginid");
    console.log(loginid);
    $.ajax({
        type:"GET",
        url:"/public/api/user/find/"+loginid,
        success:function (res) {
            var user = res.data;
            if (user!=null){
                $("#uid").val(user.uid);
                $("#pwd").val(user.pwd);
                $("#username").val(user.username);
                $("#email").val(user.email);
                $("#loginid").val(user.loginid);
                $("#loginid").attr("readonly",true);

                var status = user.status;
                $("#status").find("option[value="+status+"]").attr("selected",true);
            }
        }
    });

    $("#userAction_save_do_submit").click(function () {
        var formData = new FormData();
        formData.append("uid",$("#uid").val());
        formData.append("pwd",$("#pwd").val());
        formData.append("loginid",$("#loginid").val());
        formData.append("email",$("#email").val());
        formData.append("username",$("#username").val());
        formData.append("_method",$("#method").val());
        var status = $("#status option").not(function(){
            return !this.selected;
        }).val();
        formData.append("status",status);

        $.ajax({
            type:"POST",
            url:"/public/api/user/update",
            data:formData,
            contentType:false,
            processData:false,
            cache:false,
            success:function (res) {
                alert(res.msg);
                window.location.href="/Administer/user/list.html";
            }
        })
    });
});
