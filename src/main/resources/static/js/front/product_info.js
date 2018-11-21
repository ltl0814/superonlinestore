//API调用 public/api/backend/goods/{gid} 后台查询单个商品
$(function () {
    var gid = getQueryString('gid');
    var url = " public/api/backend/goods/"+gid;
    $.getJSON(url,function (result) {
        if(result.status){
            var product = result.data;
            $("#pro_img").html('<img style="opacity: 1;width:400px;height:350px;" title="" class="medium" src="'+product.pic+'">');
            $("#title").html("<strong>"+product.title+"</strong>");
            $("#gid").html("编号："+product.gid);
            $("#introduce").html(product.detail);
            $("#sotck").html("库存："+product.stock);
            $("#price").html("￥："+product.price+"元/份");
            $("#introduce_img").html('<img src="'+product.pic+'" width="100%">');
        }else{
            alert(result.msg);
        }
    })
})