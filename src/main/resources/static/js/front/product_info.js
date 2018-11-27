//API调用 public/api/backend/goods/{gid} 后台查询单个商品
$(function () {
    var uid = getQueryString('uid');
    var gid = getQueryString('gid');

    /**
     * 渲染商品详情
     * @type {*|string}
     */

     var url = "/public/api/backend/goods/"+gid;

   $.getJSON(url,function (result) {
        if(result.status){
            var product = result.data;
            $("#pro_img").html('<img style="opacity: 1;width:400px;height:350px;" title="" class="medium" src="../../products/hao/'+product.pic+'">');
            $("#title").html("<strong>"+product.title+"</strong>");
            $("#gid").html("编号："+product.gid);
            $("#introduce").html(product.detail);
            $("#sotck").html("库存："+product.stock);
            $("#price").html("￥："+product.price+"元/份");
            $("#introduce_img").html('<img src="../../products/hao/'+product.pic+'" width="100%">');
        }
    })

    /**
     * 加入购物车
     */
    $("#addCart").click(function () {
        if(uid == null || uid == ""){
            alert("请先登录后再操作哦！");
            window.location.href = "../slogin.html";
        }else{
            $.ajax({
                url:"/public/api/user/"+uid+"/cart/"+gid,
                type: "POST",
                success:function (result) {
                    if(result.status){
                        alert("成了，添加上了。");
                    }else{
                        alert("失败！");
                    }
                }
            })
        }
    })


})