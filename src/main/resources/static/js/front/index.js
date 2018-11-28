//index api invoking
$(function () {

    initIndexNewly();

    /**
     * 模糊查找功能-->页面跳转
     */
    $("#search_btn").click(function () {
        var content = $("#search_content").val();
        if(content != null){
            window.location.href = "../product_list.html?name="+content;
        }else{
            window.location.href = "../product_list.html";
        }
        return false;
    });

    /*
     *  动态渲染首页最新商品的数据
     */
    function initIndexNewly() {
        $.ajax({
            url: "/public/api/goods/recent",
            type: "get",
            success: function (result) {
                if(result.status){
                    var productList = result.data;
                    var products = "";
                    $.each(productList,function (index,item) {
                        products += '<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">' +
                                        '<a href="product_info.html?gid='+item.gid+'">' +
                                            '<img src="'+item.pic+'" width="130" height="130" style="display: inline-block;">' +
                                        '</a>'+
                                        '<p><a href="product_info.html?gid='+item.gid+'" style="color:#666">'+item.title+'</a></p>'+
                                        '<p><font color="#E4393C" style="font-size:16px">&yen;'+item.price+'</font></p>'+
                                    '</div>' ;
                    });
                    $("#newly").append(products);
                }
            },
            datatype: "json"
        })
    }
})