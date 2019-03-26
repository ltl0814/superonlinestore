$(function () {
    $.ajax({
        type:"GET",
        url:"/public/api/backend/order",
        success:function (res) {
            console.log(res);
            var list=res.data;
            var data="";
            $.each(list,function (index,item) {
                data+='<tr>'
                    +'<td style="CURSOR: hand; HEIGHT: 22px;" align="center" width="18%">'+(index+1)+'</td>'
                    +'<td style="CURSOR: hand; HEIGHT: 22px;" align="center" width="18%">'+item.oid+'</td>'
                    +'<td style="CURSOR: hand; HEIGHT: 22px;" align="center" width="18%">'+item.sum+'</td>'
                    +'<td style="CURSOR: hand; HEIGHT: 22px;" align="center" width="18%">'+item.recipients+'</td>'
                    +'<td style="CURSOR: hand; HEIGHT: 22px;" align="center" width="17%" class="status" desc="'+item.oid+'" id="status">'+item.status+'</td>'
                    +'<td align="center" style="HEIGHT: 22px"><input type="button" class="btn btn-info btn-xs" desc="'+item.oid+'" value="订单详情" id="but"/>'
                    +'</td></tr><tr></tr>';
            });
            $("#DataGrid1").append(data);
        }
    });

        $("#DataGrid1").on("click","#status",function () {
                $(this).attr("id","newStatus");
                $(this).html("<select id='status'><option selected='selected'>-请选择-</option><option >待付款</option><option>已付款</option><option>待发货</option><option>已发货</option><option>已收货</option></select>");
            });

        $("#DataGrid1").on("change","#newStatus",function () {
                var oid = $(this).parent().attr("desc");
                var status = $(this).val();
                $.ajax({
                    type:"POST",
                    url:"/public/api/backend/order/"+oid,
                    data:{_method:"put",status:status},
                    success:function (res) {
                        alert(res.status);
                    }
                });
            $(this).parent().attr("id","status");
            $(this).parent().html(""+status);
            return false;
        });

        $("#DataGrid1").on("click","#but",function () {
        var val = $(this).val();
        var oid = $(this).attr("desc");
        var tr = $(this).parent().parent().next();
        if(val == "订单详情"){
            $.ajax({
                type:"GET",
                url:"/pulic/api/user/order/"+oid,
                success:function (res) {
                    var data = res.data[0].list;
                    console.log(data);
                    $.each(data,function (index,item) {
                        tr.append("<td align='center'><img width='60' height='65' src='../../products/hao/"+item.pic+"'></td>" +
                            "<td align='center'><b>商品名称 :</b>"+item.title+"</td>" +
                            "<td align='center'><b>购买数量 :</b>"+item.count+"</td>" +
                            "<td align='center'><b>商品价格 :</b>"+item.price+"<br/></td>" +
                            "<td></td><td></td>");
                    })
                }
            });
            $(this).val("关闭");
            $(this).css("background-color","red");
        }else{
            tr.html("");
            $(this).val("订单详情");
            $(this).css("background-color","");
        }
    });
});