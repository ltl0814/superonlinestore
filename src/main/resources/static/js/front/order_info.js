$(function () {


    var oid = getQueryString("oid");
    var uid = getQueryString("uid");

    $.ajax({
        type:"GET",
        url:"/pulic/api/user/order/"+oid,
        success:function (res) {
            if(res.status){
                var goods = res.data;
                console.log(goods);
                if (goods[0].status=='待支付'){
                    $("#bank").css("display","block");
                }else {
                    $("#location").val("四川省资阳市雁江区东福小区").attr("disabled",true);
                    $("#receiver").val("李天乐").attr("disabled",true);
                    $("#tel").val("152XXXXX924").attr("disabled",true);
                    $("#container").append('<img style="padding-left: 100px" src="../../images/MTQ2OTY5MTUyNjcwMy04OTI3MDg1NDk=.jpg">')
                }
                $.each(goods,function (index,item) {
                    $("#goods").append('<tbody><tr class="warning">'+
                    '<th colspan="5">订单编号:<b style="color: red"> '+item.oid+
                    '</b><b style="display: inline-block;float: right">下单时间：<b style="color:red">'+item.orderCreated+'</b></b></th>'+
                    '</tr>'+
                    '<tr class="warning">' +
                    '<th>图片</th>' +
                    '<th>商品</th>' +
                    '<th>价格</th>' +
                    '<th>数量</th>' +
                    '<th>小计</th>' +
                    '</tr>');
                    for (var i=0;i<item.list.length;i++){
                        $("#goods").append('<tr class="active">' +
                            '<td width="60" width="40%">' +
                            '<img src="../../products/hao/'+item.list[i].pic+'" width="70" height="60">' +
                            '</td>' +
                            '<td width="30%">' +
                            '<a href="product_info.html?gid='+item.list[i].gid+'&uid='+uid+'">'+item.list[i].title+'</a>' +
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
                    $("#order").append("</tbody>");
                    $("#sum").text('￥'+item.sum);
                });
            }
        }
    });
})