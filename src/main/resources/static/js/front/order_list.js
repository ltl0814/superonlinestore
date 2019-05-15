//查询用户个人订单
$(function(){
    var uid = getQueryString('uid');
    queryOrderInfoByUid(uid);

    function queryOrderInfoByUid(uid){
        $.ajax({
            url:"/public/api/user/"+uid+"/order",
            type:"GET",
            success: function (result) {
                if(result.status){
                     var orders= result.data;
                     if(orders.length>0) {
                         $.each(orders, function (index, item) {
                             if (item.status == '待支付') {
                                 var oid = item.oid;
                                 var aabb = '<div style="float: right;"><button><a href="../../order_info.html?oid=' + item.oid + '&uid=' + uid + '">支付订单</a></button><button onclick="cancel()" id="cancel" value="' + oid + '">取消订单</button></div>';
                                 $("#order").append('<tbody id=' + item.oid + '>' +
                                     '<tr class="success">' +
                                     '<th colspan="5">订单编号:<a href="../../order_info.html?oid=' + item.oid + '&uid=' + uid + '">' + item.oid + '</a>' +
                                     '&nbsp;&nbsp;&nbsp;&nbsp;<b>下单时间：<b style="color:#c0c0c0">' + item.orderCreated + '</b></b>' + aabb +
                                     '<div style="float: right">订单状态:<b style="color: red">' + item.status + '&nbsp;&nbsp;</b></div>' + '</th></tr>' +
                                     '<tr class="warning">' +
                                     '<th>图片</th>' +
                                     '<th>商品</th>' +
                                     '<th>价格</th>' +
                                     '<th>数量</th>' +
                                     '<th>小计</th>' +
                                     '</tr>');
                             }
                             else {
                                 $("#order").append('<tbody id=' + item.oid + '>' +
                                     '<tr class="success">' +
                                     '<th colspan="5">订单编号:<a href="../../order_info.html?oid=' + item.oid + '&uid=' + uid + '">' + item.oid + '</a>' +
                                     '&nbsp;&nbsp;&nbsp;&nbsp;<b>下单时间：<b style="color:#c0c0c0">' + item.orderCreated + '</b></b>' +
                                     '<div style="float: right">订单状态:<b style="color: red">' + item.status + '</b></div>' + '</th></tr>' +
                                     '<tr class="warning">' +
                                     '<th>图片</th>' +
                                     '<th>商品</th>' +
                                     '<th>价格</th>' +
                                     '<th>数量</th>' +
                                     '<th>小计</th>' +
                                     '</tr>');


                             }
                             for (var i = 0; i < item.list.length; i++) {
                                 $("#" + item.oid).append('<tr class="active">' +
                                     '<td width="60" width="40%">' +
                                     '<img src="../../products/hao/' + item.list[i].pic + '" width="70" height="60">' +
                                     '</td>' +
                                     '<td width="30%">' +
                                     // '<a target="_blank">'+v.title+'</a>' +
                                     '<a href="product_info.html?gid=' + item.list[i].gid + '&uid=' + uid + '">' + item.list[i].title + '</a>' +
                                     '</td>' +
                                     '<td width="20%">￥' + item.list[i].price +
                                     '</td>' +
                                     '<td width="10%">' + item.list[i].count +
                                     '</td>' +
                                     '<td width="15%">' +
                                     '<span class="subtotal">￥' + item.list[i].suatotal + '</span>');
                             }
                             //$.each(item.list,function (i, v) {
                             // })
                             $("#order").append("</tbody>");
                         })
                     }else {
                         $("#order_content").html("<h3 style='display: inline-block'>暂时没有任何订单哦亲！</h3>");
                     }
                }
            }
        })
    }
})