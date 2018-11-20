$(function(){
	$('.carousel').carousel({
			 interval: 2000
	});
		
	$("#inputUserName").blur(function(){	
		var userName = $(this).val().trim();
		$(this).val(userName);
		$(".checkingInfo").remove();
		if(userName == null || userName.length<1){
			$("#form-group-userName").append("<div class='col-md-2 checkingInfo onError'>" +
					"<font color='red' style='line-height: 40px;'>" +
						"<span class='glyphicon glyphicon-remove' aria-hidden='true'></span>用户名有误!" +
					"</font></div>");
		}else{
			$.post("userServlet?method=userChecking",{"userName":userName},function(data,status){
				if(data ==0 ){
				$("#form-group-userName").append("<div class='col-md-2 checkingInfo onSuccess'>" +
						"<font color='green' style='line-height: 40px;'>" +
							"<span class='glyphicon glyphicon-ok' aria-hidden='true'></span>用户名可用!" +
						"</font></div>");
				}else{
					$("#form-group-userName").append("<div class='col-md-2 checkingInfo onError'>" +
							"<font color='red' style='line-height: 40px;'>" +
								"<span class='glyphicon glyphicon-remove' aria-hidden='true'></span>用户名已存在!" +
							"</font></div>");
				}
			});
		}
		
	});
	
	$("form .register_submit").submit(function(){	
		$("#inputUserName").trigger("blur");
		if($(".onError").length > 0){
			$("#inputUserName").focus();
			return false;
		}
		
		return true;
	});
	
	

	
});
