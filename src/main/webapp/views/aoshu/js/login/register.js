function register_sure() {
	var name = $("#register_name").val();
	var password = $("#register_password").val();
	var passwordMd5 = hex_md5(password);
	
	var data = "&crusrName="+name+"&crusrPassword="+passwordMd5;
	var url = urlfile + "coreUser/register";
	ajaxTool("post",data,url,
		function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
			alert("error:" + data);
		},
		function success(data){
			if(!data.success) {
				alert(data.errMsg);
			}else{
				alert("注册成功，请登录");
				window.history.go(-1);
			}
		}
	);
}