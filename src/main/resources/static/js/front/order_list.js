//查询用户个人订单
$(function(){
    var uid = getQueryString('uid');
    queryOrderInfoByUid(uid);

    function queryOrderInfoByUid(uid){
        $.ajax({
            url:"/public/api/uer/"+uid+"/order",
            type:"GET",
            success: function (result) {
                if(result.status){
                     var orders= result.data;
                     $.each(orders,function (index, item) {

                         $("#order").append('<tbody>' +
                                                 '<tr class="success">' +
                                                 '<th colspan="5">订单编号:'+item.oid+' </th>' +
                                                 '</tr>' +
                                                 '<tr class="warning">' +
                                                 '<th>图片</th>' +
                                                 '<th>商品</th>' +
                                                 '<th>价格</th>' +
                                                 '<th>数量</th>' +
                                                 '<th>小计</th>' +
                                                 '</tr>');
                        for(var i=0;i<item.list.length;i++){
                            $("#order").append('<tr class="active">' +
                                '<td width="60" width="40%">' +
                                '<img src="'+item.list[i].pic+'" width="70" height="60">' +
                                '</td>' +
                                '<td width="30%">' +
                               // '<a target="_blank">'+v.title+'</a>' +
                                '<a target="_blank">'+item.list[i].title+'</a>' +
                                '</td>' +
                                '<td width="20%">￥'+item.list[i].price +
                                '</td>' +
                                '<td width="10%">'+item.list[i].count +
                                '</td>' +
                                '<td width="15%">' +
                                '<span class="subtotal">￥'+item.list[i].suatotal+'</span>' +
                                '</td>' +
                                '</tr>');
                        }
                         //$.each(item.list,function (i, v) {

                        // })

                       $("#order").append("</tbody>");

                     })
                }



            }
        })
    }
})