//商品列表信息查询api调用
$(function(){
    var uid = getQueryString("uid");
    var name = getQueryString("name");
    var page = getQueryString("page");
    if(name == null||name.length == 0){
        queryAll(page-1,5);
    } else{
        queryByName(name);
    }

    /**
     * 带分页的查询所有商品信息
     */
    var currentPage = 1;
    var totalPage = 5;
    function queryAll(currentNum,size){
        $.ajax({
            url:"/public/api/backend/goods?start="+currentNum+"&size="+size,
            type:"GET",
            success:function (result) {
                if(result.status){
                    var productList = result.data.content;
                    var products = "";
                    $.each(productList,function(index,item){
                        products += '<div class="col-md-2">' +
                                        '<a href="product_info.html?gid='+item.gid+'&uid='+uid+'">' +
                                            '<img src="../../products/hao/'+item.pic+'" width="170" height="170" style="display: inline-block;">' +
                                         '</a>' +
                                         '<p><a href="product_info.html?gid='+item.gid+'&uid='+uid+'" style="color:green">'+item.title+'</a></p>' +
                                         '<p><font color="#FF0000">商城价：￥'+item.price+'</font></p>' +
                                    '</div>';
                    });
                    $("#product").append(products);
                    //分页
                    currentPage = result.data.pageable.pageNumber+1;
                    totalPage = result.data.totalPages;
                    $("#pagination").html("");
                    $("#pagination").html('<li>当前 '+currentPage+' 页' +
                        '<a href="javascript:;" id="upper"><span aria-hidden="true">上一页</span></a>' +
                        '<input value="'+currentPage+'" class="to_page" style="width: 30px;display: inline-block;" type="text" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,"");}).call(this)" onblur="this.v();" >' +
                        '<input type="button" value="Go" />' +
                        '<a href="javascript:;" id="next"><span aria-hidden="true">下一页</span></a>' +
                        '共 '+totalPage+' 页' +
                        '</li>');

                    //上一页
                    $("#upper").click(function(){
                        if(currentPage == 1){
                            alert("您已处在第一页");
                            return false;
                        }else{
                            window.location.href = "../product_list.html?page="+(currentPage-1)+"&uid="+uid;
                        }
                    });
                    //下一页
                    $("#next").click(function(){
                        if(currentPage == totalPage){
                            alert("您已处在最后页");
                            return false;
                        }else{
                            window.location.href = "../product_list.html?page="+(currentPage+1)+"&uid="+uid;
                        }
                    });

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
            async:false,
            success: function (result) {
                if(result.status){
                    var products = "";
                    var productList = result.data;
                    $.each(productList,function(index,item){
                        products += '<div class="col-md-2">' +
                            '<a href="product_info.html?gid='+item.gid+'&uid='+uid+'">' +
                            '<img src="../../products/hao/'+item.pic+'" width="170" height="170" style="display: inline-block;">' +
                            '</a>' +
                            '<p><a href="product_info.html?gid='+item.gid+'&uid='+uid+'" style="color:green">'+item.title+'</a></p>' +
                            '<p><font color="#FF0000">商城价：￥'+item.price+'</font></p>' +
                            '</div>';
                    });
                    $("#product").append(products);
                }
            }

        })
    }

    /**
     * 模糊查找功能-->页面跳转
     */
    $("#search_btn").click(function () {
        var content = $("#search_content").val().trim();
        if(content == null || content == ""){
            // window.location.href = "../product_list.html?page=1&uid="+uid;
            $("#search_content").attr("placeholder","请勿输入空值！");
        }else{
            window.location.href = "../product_list.html?uid="+uid+"&name="+content;
            $("#search_content").attr("placeholder","请输入需要查询的内容")
            $("#search_content").val("");
        }
        return false;
    });
})
