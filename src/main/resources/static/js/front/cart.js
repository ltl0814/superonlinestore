//购物车模块api调用
$(function(){
    var uid = getQueryString('uid');
    /**
     * 查询购物车
     */
    var url = "/public/api/user/"+uid+"/cart";
    $.getJSON(url,function (result) {
    if(result.status){
        var products = result.data.list;
        var product = "";
        $("#cart_item").html("");
        $.each(products,function (index,item) {

            product += '<tr class="active"> '+
                '<td width="60" width="40%">'+
                '<img src="'+item.pic+'" width="70" height="60">'+
                '</td><td width="30%">'+
                '<a target="_blank">'+item.title+'</a></td>'+
            '<td width="20%"> ￥200</td>'+
            '<td width="10%">'+
                '<input type="text" name="quantity" value="'+item.count+'" maxlength="4" size="10"></td>'+
                '<td width="15%">'+
                '<span class="subtotal">￥'+item.suatotal+'</span>'+
            '</td><td>'+
            '<a href="javascript:;" class="delete" id="del_'+item.gid+'">删除</a></td></tr>';
            $("#cart_item").append(product);
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
    }
    })
})