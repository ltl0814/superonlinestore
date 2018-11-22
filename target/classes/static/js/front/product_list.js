//商品列表信息查询api调用
$(function(){
    var name = getQueryString("uid");
    var name = getQueryString("name");
    if(name == null||name.length == 0){
        queryAll(0,2);
    } else{
        queryByName(name);
    }

    /**
     * 带分页的查询所有商品信息
     */
    function queryAll(currentNum,size){
        $.ajax({
            url:"/public/api/backend/goods?start="+currentNum+"&size="+size,
            type:"GET",
            success:function (result) {
                if(result.status){
                    //遍历显示商品
                     $("#product").html("");
                    $("#product").html('<div class="col-md-12">' +
                                            '<ol class="breadcrumb">' +
                                                '<li><a href="../index.html">首页</a></li>' +
                                            '</ol>' +
                                        '</div>');
                    var productList = result.data.content;
                    var products = "";
                    $.each(productList,function(index,item){
                        products += '<div class="col-md-2">' +

                                        '<a href="product_info.html?gid='+item.gid+'&uid='+uid+'">' +
                                            '<img src="'+item.pic+'" width="170" height="170" style="display: inline-block;">' +
                                         '</a>' +
                                         '<p><a href="product_info.html?gid='+item.gid+'&uid='+uid+'" style="color:green">'+item.title+'</a></p>' +
                                         '<p><font color="#FF0000">商城价：￥'+item.price+'</font></p>' +
                                    '</div>';
                    });
                    $("#product").append(products);
                    //分页
                    var currentPage = result.data.pageable.pageNumber+1;
                    // $("#currentNum").html(currentPage);
                    // $("#upper").click(function(){
                    //     queryAll(currentPage-1,2);
                    // });
                    // $("#next").click(function(){
                    //     queryAll(currentPage+1,2);
                    // });
                    // $("#upper").attr("href","/public/api/backend/goods?start="+(currentPage-1)+"&size=2");
                    // $("#next").attr("href","/public/api/backend/goods?start="+(currentPage+1)+"&size=2");
                // <a href="?start='+(currentPage-1)+'&size=2"><span aria-hidden="true">上一页</span></a>
                    $("#totalPages").html(result.data.totalPages);
                    $("#pagination").html("");
                    $("#pagination").html('<li>当前 '+currentPage+' 页' +
                        '<a href="" onclick="queryAll('+(currentPage-1)+',2)"><span aria-hidden="true">上一页</span></a>' +
                        '<input value="'+currentPage+'" class="to_page" style="width: 30px;display: inline-block;" type="text" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,"");}).call(this)" onblur="this.v();" >' +
                        '<input type="button" value="Go" onclick="queryAll('+($(".to_page").val()-1)+',2)" />' +
                        '<a href="" onclick="queryAll('+(currentPage+1)+',2)"><span aria-hidden="true">下一页</span></a>' +
                        '共 '+result.data.totalPages+' 页' +
                        '</li>');
                }
            }
        })
    }

    /**
     * 暂时不带分页的模糊查询商品信息
     */
    function queryByName(name) {
        $.ajax({
            url: "/public/api/goods/title?name=" + name,
            type: "GET",
            success: function (result) {
                if(result.status){
                    var products = "";
                    var productList = result.data;
                    $.each(productList,function(index,item){
                        products += '<div class="col-md-2">' +
                            '<a href="product_info.html?gid='+item.gid+'">' +
                            '<img src="'+item.pic+'" width="170" height="170" style="display: inline-block;">' +
                            '</a>' +
                            '<p><a href="product_info.html?gid='+item.gid+'" style="color:green">'+item.title+'</a></p>' +
                            '<p><font color="#FF0000">商城价：￥'+item.price+'</font></p>' +
                            '</div>';
                    });
                    $("#product").append(products);
                }
            }

        })
    }

})
