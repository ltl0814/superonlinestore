//open sized window
$(function(){
    queryAll();

    function openWindow(sHref,strWidth,strHeight) {
      var strLeft=(screen.availWidth-strWidth)/2;
      var strTop=(screen.availHeight-strHeight)/2;
      var strRef="";
      strRef=strRef+"width="+strWidth+"px,height="+strHeight+"px,";
      strRef=strRef+"left="+strLeft+"px,top="+strTop+"px,";
      strRef=strRef+"resizable=yes,scrollbars=yes,status=yes,toolbar=no,systemmenu=no,location=no,borderSize=thin";//channelmode,fullscreen
      var openerobj= window.open(sHref,'newwin',strRef,false);
      openerobj.focus();
    }

    function queryAll() {
        $.ajax({
            url:"/public/api/user/all",
            type:"GET",
            success:function (res) {
                var text="";
                $.each(res,function (index,item) {
                    console.log(item);
                    text+='<tr onmouseover="this.style.backgroundColor =white" onmouseout="this.style.backgroundColor =#F5FAFE;">'
                    +'<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">'+(index+1)+'</td>'
                    +'<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">'+item.loginid+'</td>'
                    +'<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">'+item.username+'</td>'
                    +'<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">'+item.status+'</td>'
                    +'<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">'+item.email+'</td>'
                    +'<td align="center" style="HEIGHT: 22px"><a href="edit.html"><img src="../../img/admin/i_edit.gif" border="0" style="CURSOR: hand"></a></td>'
                    +'<td align="center" style="HEIGHT: 22px"><a  class="deleteUser" href="#" ><img src="../..//img/admin/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand"></a></td>'
                    +'</tr>';
                });
                $("#DataGrid1").append(text);
            }
        })
    }
})