$(function () {

    $.ajax({
        type:"GET",
        url:"/public/api/backend/order",
        success:function (res) {
            console.log(res);
            var list=res.data;
            var data="";
            $.each(list,function (index,item) {
                data+='<tr onmouseover="this.style.backgroundColor = white" onmouseout="this.style.backgroundColor =#F5FAFE;">'
                    +'<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">'+(index+1)+'</td>'
                    +'<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">'+item.oid+'</td>'
                    +'<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">'+item.sum+'</td>'
                    +'<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">'+item.recipients+'</td>'
                    +'<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%"><font color="blue">'+item.status+'</font></td>'
                    +'<td align="center" style="HEIGHT: 22px"><input type="button" class="btn btn-info btn-xs" value="订单详情" id="but" onclick="showDetail(item.list)"/><div id="div"></div></td>'
                    +'</tr>';
            });
            $("#DataGrid1").append(data);
        }
    });

    function showDetail(list){
        alert("111");
        console.log(list);
        var $val = $("#but").val();
        if($val == "订单详情"){
            // ajax 显示图片,名称,单价,数量
            $("#div").append("<img width='60' height='65' src='../../products/hao/small01.jpg'>&nbsp;皮蛋粥*5&nbsp;500<br/>" +
                "<img width='60' height='65' src='../../products/hao/small02.jpg'>&nbsp;冬瓜*1&nbsp;30");
            $("#but").val("关闭");
        }else{
            $("#div").html("");
            $("#but").val("订单详情");
        }
    }

})