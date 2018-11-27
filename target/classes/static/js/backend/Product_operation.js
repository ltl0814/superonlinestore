/**
  *后台商品操作：添加商品与修改商品
 */
$(function () {
   var gid =  getQueryString('gid');
   if(gid == null || gid == ""){
       insertProduct();
   }else{
       toModifyProductPage(gid);
   }

   //新增商品
   function insertProduct(){
        $("#operation_method").html("添加商品");
        $("#submit").click(function () {

            var price = $("#price").val();
            var title = $("#title").val();
            var detail = $("#detail").val();
            var stock =  $("#stock").val();
            var status = $("#status option").not(function(){
                     return !this.selected;
                  }).val();
            var file = $("#file")[0].files[0];
            var formData = new FormData();
           formData.append("price",price);
           formData.append("title",title);
           formData.append("detail",detail);
           formData.append("stock",stock);
           formData.append("status",status);
            formData.append("file",file);

            $.ajax({
                url:"/public/api/backend/goods",
                type:"POST",
                data:formData,
                contentType:false,
                processData:false,
                cache:false,
                success:function(result){
                    if(result.status){
                        alert("成了，插入成功。");
                        window.location.href = "../../Administer/product/list.html?page=1";
                    }else{
                        alert("没添加成功");
                    }
                }
            });
            return false;
        });


   }
   //跳转商品编辑页面
    function toModifyProductPage(gid) {
        $("#operation_method").html("修改商品");
        alert(gid);
    }

})