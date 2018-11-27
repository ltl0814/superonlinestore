//购物车模块api调用
$(function(){
    var uid = getQueryString('uid');

    /**
     * 结算购物车物品
     */
    $("#buy").click(function () {
        $.ajax({
            url:"/public/api/user/"+uid+"/cart/list",
            type:"GET",
            success: function(result){
                if(result.status){
                    alert("结算成功！");
                    window.location.href = "../order_list.html?uid="+uid;
                }
            }
        })
        return false;
    });
    /**
     * 查询购物车
     */
    var url = "/public/api/user/"+uid+"/cart";
    $.getJSON(url,function (result) {
    if(result.status){
        var products = result.data.list;
        if(products.length > 0){
        $.each(products,function (index,item) {
            $("#cart_item").append('<tr class="active"> '+
                '<td width="60" width="40%">'+
                '<img src="../../products/hao/'+item.pic+'" width="70" height="60">'+
                '</td><td width="30%">'+
                '<a target="_blank">'+item.title+'</a></td>'+
            '<td width="20%"> ￥'+item.price+'</td>'+
            '<td width="10%">'+
                '<input type="text" name="quantity" value="'+item.count+'" maxlength="4" size="10"></td>'+
                '<td width="15%">'+
                '<span class="subtotal">￥'+item.suatotal+'</span>'+
            '</td><td>'+
            '<a href="javascript:;" class="delete" id="del_'+item.gid+'">删除</a></td></tr>');
            $("#del_"+item.gid).click(function () {
                $.ajax({
                    url:"/public/api/user/"+uid+"/cart/"+item.gid,
                    type:"DELETE",
                    success:function(result){
                        if(result.status){
                            alert("OK，安排上了！");
                            window.location.href = "../cart.html?uid="+uid;
                        }else{
                            alert("sorry,操作失败！");
                        }
                    }
                })
            });
        })


        $("#credits").html(result.data.sum);
        $("#sum").html("￥"+result.data.sum+"元");
        }else{
            var url = "../index.html?uid="+uid;
            $("#cart_content").html("<h3 style='display: inline-block'>购物车暂时没有任何商品哦亲！</h3><a href="+url+">点击前往购物</a>");
            $("#cart_content_bottom").html("");
        }
    }
    })
})