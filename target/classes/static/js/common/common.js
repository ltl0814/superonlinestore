/**
 * 检测系统是否有用户登录
 */
$(function () {
    var currentUid = getQueryString("uid");
    var url = "/public/api/user/session";
    $.getJSON(url,function (result) {
        if(result.status){
            var currentUser = null;
            var sessions = result.data;
            if(sessions.length > 0) {
                $.each(sessions, function (index, item) {
                    if (item.uid == currentUid) {
                        currentUser = item;
                        return false;
                    }
                })
            }
            if(currentUser != null){
                var loginedId = currentUser.loginid;
                var loginedUid = currentUser.uid;
                $("#logo").html('<a href="../index.html?uid='+loginedUid+'">' +
                    '<img src="img/logo2.png" />' +
                    '</a>');
                $("#checking_login").html('<ol class="list-inline">' +
                    '<li>欢迎您，<a href="#">'+currentUser.username+'</a></li>' +
                    '<li><a href="../slogin.html" id="logout">退出登录</a> </li>'+
                    '<li><a href="../order_list.html?uid='+loginedUid+'">我的订单</a></li>' +
                    '<li><a href="../cart.html?uid='+loginedUid+'">购物车</a></li>' +
                    '</ol>');

                $("#logout").click(function () {
                    var url = "/public/api/user/out?loginId="+loginedId;
                    $.getJson(url,function (result) {
                        alert(result);
                        if(result.status){
                            alert("congraduations,成功退出！");
                            window.location.href = "../slogin.html";
                            return false;
                        }else{
                            alert(result.msg);
                        }
                    });

                });
            }else{
                $("#logo").html('<a href="../index.html">' +
                    '<img src="img/logo2.png" />' +
                    '</a>');
                $("#checking_login").html('<ol class="list-inline">' +
                    '<li><a href="../slogin.html">登录</a></li>' +
                    '<li><a href="../register.html">注册</a></li>' +
                    '<li><a href="../cart.html" id="go_cart">购物车</a></li>' +
                    '</ol>');
                $("#go_cart").click(function(){
                    alert("您还为登录，请先登录！");
                    window.location.href = "../slogin.html";
                    return false;
                });
            }

        }
    })

})


    /**
     * 请求的url的参数截取
     */
    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return decodeURIComponent(r[2]);
        }
        return '';
    }

