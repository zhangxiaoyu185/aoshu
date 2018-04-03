function login_sure() {
	var login_name = $('#login_name').val();
	var login_password = $('#login_password').val();
	var passwordMd5 = hex_md5(login_password);

	var check = $('.login_checked').length;
	if(check>0){
		dataSave("login_name", login_name);
		dataSave("login_password", login_password);
		dataSave("login_flag", 1);
	}else{
		dataSave("login_name", "");
		dataSave("login_password", "");
		dataSave("login_flag", 0);
	}

	var data = "&crusrName="+login_name+"&crusrPassword="+passwordMd5;
	var url = urlfile + "coreUser/login";
	ajaxTool("post",data,url,
		function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
			alert("error:" + data);
		},
		function success(data){
			if(!data.success) {
				alert(data.errMsg);
			}else{
				dataSave("crusrGender",data.data.crusrGender);
				dataSave("crusrUuid",data.data.crusrUuid);
				dataSave("crusrName",data.data.crusrName);

				dataSave("crusrCode",data.data.crusrCode);
				dataSave("crusrMobile",data.data.crusrMobile);
				dataSave("crusrSchool",data.data.crusrSchool);
				dataSave("crusrReadClass",data.data.crusrReadClass);
				dataSave("crusrClasss",data.data.crusrClasss);

				dataSave("crusrGradeName", data.data.crusrGradeName);
				dataSave("crusrGrade", data.data.crusrGrade);

				nextView("../home/homeIndex.html");
			}
		}
	);
}

// crgaeClasss: "1,2,3,4,5,6,"
// crgaeClasssName: "一年级,二年级,三年级,四年级,五年级,六年级,"
// crgaeName: "超级班"
// crgaeUuid: "06251831256941XT"

$(function() {
	$('#login_name').val(dataGet("login_name"));
	$('#login_password').val(dataGet("login_password"));
	if(dataGet("login_flag")==1){
		$('#login_flag').removeClass("login_nocheck").addClass("login_checked");
	}else{
		$('#login_flag').removeClass("login_checked").addClass("login_nocheck");
	}
})