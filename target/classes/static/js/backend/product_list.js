//后台，查询所有上架商品列表
$(function () {
    var page = getQueryString("page");
    //查询所有在售商品
    queryAll(page-1,10);



    /**
     * 查询所有在售的商品即status为1的商品
     */
    var currentPage = 1;
    var totalPage = 5;
    function queryAll(currentNum,size) {
        $.ajax({
            url:"/public/api/backend/goods?start="+currentNum+"&size="+size,
            type:"GET",
            success: function (result) {
                if(result.status){
                    var productList = result.data.content;
                    var products = "";
                    $.each(productList,function(index,item){
                        products += '<tr onmouseover="this.style.backgroundColor = white"' +
                            'onmouseout="this.style.backgroundColor = #F5FAFE;">' +
                            '<td style="CURSOR: hand; HEIGHT: 22px" align="center"' +
                            'width="10%">'+(index+1) +
                            '</td>' +
                            '<td style="CURSOR: hand; HEIGHT: 22px" align="center"' +
                            'width="10%">' +
                            '<img width="40" height="45" src="../../products/hao/'+item.pic+'">' +
                            '</td>' +
                            '<td style="CURSOR: hand; HEIGHT: 22px" align="center"' +
                            'width="10%%">'+item.title +
                            '</td>' +
                            '<td style="CURSOR: hand; HEIGHT: 22px" align="center"' +
                            'width="10%">' +
                            '￥'+item.price +
                            '</td>' +
                            '<td style="CURSOR: hand; HEIGHT: 22px" align="center"' +
                            'width="10%%">'+item.stock +
                            '</td>' +
                            '<td style="CURSOR: hand; HEIGHT: 22px;" align="center" width="40%">' +
                            '<p style="width:650px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">' +item.detail+'</p>' +
                            '</td>' +
                            '<td align="center" style="HEIGHT: 22px" width="5%">' +
                            '<a href="../../Administer/product/operation.html?gid='+item.gid+'">' +
                            '<img src="../../img/admin/i_edit.gif" border="0" style="CURSOR: hand">' +
                            '</a>' +
                            '</td>' +
                            '<td align="center" style="HEIGHT: 22px" width="5%">' +
                            '<a href="javasctipt:void(0);" onclick="deleteByGid('+item.gid+','+item.title+')">' +
                            '<img src="../..//img/admin/button_clock.gif" width="16" height="16" border="0" style="CURSOR: hand">' +
                            '</a>' +
                            '</td>' +
                            '</tr>'

                    });
                    $("#onsaleProducts").append(products);

                    //下架商品
                    function deleteByGid(gid,title){
                        if(confirm("您将下架"+title+"商品")){
                            $.ajax({
                                url:"/public/api/goods/"+gid,
                                type:"delete",
                                success: function(result){
                                    if(result.status){
                                        alert("下架商品成功！");
                                    }
                                }
                            })
                        }
                    }

                    //分页
                    currentPage = result.data.pageable.pageNumber+1;
                    totalPage = result.data.totalPages;

                    if(totalPage != 1){
                        $("#pagination").html("");
                        $("#pagination").html('<li>当前 '+currentPage+' 页' +
                            '<a href="javascript:;" id="upper"><span aria-hidden="true">上一页</span></a>' +
                            '<input value="'+currentPage+'" class="to_page" style="width: 30px;display: inline-block;" type="text" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,"");}).call(this)" onblur="this.v();" >' +
                            '<input type="button" value="Go" />' +
                            '<a href="javascript:;" id="next"><span aria-hidden="true">下一页</span></a>' +
                            '共 '+totalPage+' 页' +
                            '</li>');
                    }

                    //上一页
                    $("#upper").click(function(){
                        if(currentPage == 1){
                            alert("您已处在第一页");
                            return false;
                        }else{
                            window.location.href = "../../Administer/product/list.html?page="+(currentPage-1);
                        }
                    });
                    //下一页
                    $("#next").click(function(){
                        if(currentPage == totalPage){
                            alert("您已处在最后页");
                            return false;
                        }else{
                            window.location.href = "../../Administer/product/list.html?page="+(currentPage+1);
                        }
                    });
                }

            }
        })
    }
})
