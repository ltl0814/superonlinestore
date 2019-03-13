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
                    +'<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">'+(index+1)+'</td>'
                    +'<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">'+item.oid+'</td>'
                    +'<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">'+item.sum+'</td>'
                    +'<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">'+item.recipients+'</td>'
                    +'<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%"><font color="blue">'+item.status+'</font></td>'
                    +'<td align="center" style="HEIGHT: 22px"><input type="button" class="btn btn-info btn-xs" desc="'+item.oid+'" value="订单详情" id="but"/>'
                    +'</td></tr>';
            });
            $("#DataGrid1").append(data);
        }
    });

    $("#DataGrid1").on("click","#but",function () {
        var val = $(this).val();
        var oid = $(this).attr("desc");
        if(val == "订单详情"){
            $(this).val("关闭");
        }else{
            $(this).val("订单详情");
        }
    });
})