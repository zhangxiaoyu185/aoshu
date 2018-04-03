var passwordStr = 	'<div id="changePassword">'+
						'<div class="changePasswrd_bg">'+
							'<div class="changePasswrd_item" style="top: 120px;">'+
								'<div class="flag">输入原密码：</div>'+
								'<input id="oldPassword" type="text" class="changePasswrd_input"/>'+
							'</div>'+
							'<div class="changePasswrd_item" style="top: 120px;">'+
								'<div class="flag">输入新密码：</div>'+
								'<input id="newPassword" type="password" class="changePasswrd_input" />'+
							'</div>'+
							'<div class="changePasswrd_item" style="top: 120px;">'+
								'<div class="flag">重输新密码：</div>'+
								'<input id="surePassword" type="password" class="changePasswrd_input" />'+
							'</div>'+
							'<div class="changePasswrd_sure btn" onclick="changePas()"></div>'+
							'<div class="changePasswrd_exit btn" onclick="layerCloseAll()"></div>'+
						'</div>'+
					'</div>';

function changePassword() {
	layer.open({
	  type: 1,
	  shadeClose: false,
	  shade: 0.8,
	  content: passwordStr, //iframe的url
	   style: 'border:none; background: transparent;',
	});
}

var infoStr = '<div id="changeInfo">'+
				'<div class="changeInfo_bg">'+
					'<div class="changeInfo_item" style="top: 120px;width: 478px; margin-left: 66px;">'+
						'<div class="flag">学生姓名：</div>'+
						'<input id="studentName" type="text" class="changeInfo_input" readOnly="true" />'+
					'</div>'+
					'<div id="studentSex" class="changeInfo_item" style="top: 120px;width: 478px; margin-left: 66px;">'+
						'<div class="flag">学生性别：</div>'+
						'<div id="sex1" class="changeInfo_checked_sex" onclick="radio(this,\'changeInfo_nocheck_sex\',\'changeInfo_checked_sex\')" value="1">男孩</div>'+
						'<div id="sex0" class="changeInfo_nocheck_sex" onclick="radio(this,\'changeInfo_nocheck_sex\',\'changeInfo_checked_sex\')" value="0">女孩</div>'+
					'</div>'+
					'<div class="changeInfo_item" style="top: 120px;width: 478px; margin-left: 66px;">'+
						'<div class="flag">联系方式：</div>'+
						'<input id="phoneNo" type="text" class="changeInfo_input" value="15157133641" />'+
					'</div>'+
					'<div class="changeInfo_item" style="top: 120px; margin-left: 66px;">'+
						'<div class="flag">就读学校：</div>'+
						'<input id="studentAddress" type="text" class="changeInfo_input" value="" />'+
					'</div>'+
					'<div id="studentGrade">'+
						'<div class="changeInfo_item" style="top: 120px; margin-left: 66px;">'+
							'<div class="flag">目前在读：</div>'+
							'<div id="grade1" class="changeInfo_nocheck" onclick="radio(this,\'changeInfo_nocheck\',\'changeInfo_checked\')" value="1">一年级</div>'+
							'<div id="grade2" class="changeInfo_nocheck" onclick="radio(this,\'changeInfo_nocheck\',\'changeInfo_checked\')" value="2">二年级</div>'+
							'<div id="grade3" class="changeInfo_nocheck" onclick="radio(this,\'changeInfo_nocheck\',\'changeInfo_checked\')" value="3">三年级</div>'+
						'</div>'+
						'<div class="changeInfo_item " style="top: 120px; margin-left: 66px;">'+
							'<div id="grade4" class="changeInfo_nocheck" onclick="radio(this,\'changeInfo_nocheck\',\'changeInfo_checked\')" value="4">四年级</div>'+
							'<div id="grade5" class="changeInfo_nocheck" onclick="radio(this,\'changeInfo_nocheck\',\'changeInfo_checked\')" value="5">五年级</div>'+
							'<div id="grade6" class="changeInfo_nocheck" onclick="radio(this,\'changeInfo_nocheck\',\'changeInfo_checked\')" value="6">六年级</div>'+
							'<div id="grade7" class="changeInfo_nocheck" onclick="radio(this,\'changeInfo_nocheck\',\'changeInfo_checked\')" value="7">其它</div>'+
						'</div>'+
					'</div>'+
					'<div class="changeInfo_sure btn" onclick="changePersonInfo()"></div>'+
					'<div class="changeInfo_exit btn" onclick="layerCloseAll()"></div>'+
				'</div>'+
			'</div> ';


function changePersonInfo() {
	var studentName = $("#studentName").val();
	var studentSex = $("#studentSex").find(".changeInfo_checked_sex").attr("value");
	var phoneNo = $("#phoneNo").val();
	var studentAddress = $("#studentAddress").val();
	var studentGrade = $("#studentGrade").find(".changeInfo_checked");
	if(studentGrade.length==0) {
		alert("请选择年级！");
		return;
	}else{
		studentGrade = studentGrade.attr("value");
	}
	var password = dataGet("login_password");
	var passwordMd5 = hex_md5(password);
	


	var data = "&crusrUuid="+dataGet("crusrUuid")+"&crusrName="+dataGet("crusrName")+"&crusrPassword="+passwordMd5+"&crusrCode="+studentName+"&crusrMobile="+phoneNo
				+"&crusrGender="+studentSex+"&crusrReadClass="+studentGrade +"&crusrSchool="+studentAddress ;
	var url = urlfile + "coreUser/update/coreUser";
	ajaxTool("post",data,url,
		function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
			alert("error:" + data);
		},
		function success(data){
			if(!data.success) {
				alert(data.errMsg);
			}else{
				dataSave("crusrGender",studentSex);
				dataSave("crusrCode",studentName);
				dataSave("crusrMobile",phoneNo);
				dataSave("crusrSchool",studentAddress);
				dataSave("crusrReadClass",studentGrade);

				// var userName = dataGet("crusrCode")?dataGet("crusrCode"):dataGet("crusrName");
				$("#userName").html(dataGet("crusrCode")?dataGet("crusrCode"):dataGet("crusrName"));

				alert("修改信息成功");
				layerCloseAll();
			}
		}
	);
}


function changeInfo() {
	layer.open({
	  type: 1,
	  shadeClose: false,
	  shade: 0.8,
	  content: infoStr, //iframe的url
	   style: 'border:none; background: transparent;',
	});

	$("#studentName").val(dataGet("crusrCode")!="undefined"?dataGet("crusrCode"):dataGet("crusrName"));
	$("#phoneNo").val(dataGet("crusrMobile")!="undefined"?dataGet("crusrMobile"):"");
	$("#studentAddress").val(dataGet("crusrSchool")!="undefined"?dataGet("crusrSchool"):"");
	var crusrGender = dataGet("crusrGender");
	if(crusrGender == 0) {
		$("#sex0").removeClass("changeInfo_nocheck_sex").addClass("changeInfo_checked_sex");
		$("#sex1").removeClass("changeInfo_checked_sex").addClass("changeInfo_nocheck_sex");
	}

	var grade = dataGet("crusrReadClass");
	for(var i=1; i<8; i++) {
		if(grade==i) {
			$("#grade"+i).removeClass("changeInfo_nocheck").addClass("changeInfo_checked");
		}else{
			$("#grade"+i).removeClass("changeInfo_checked").addClass("changeInfo_nocheck");
		}
	}
}


function changePas() {
	var oldPassword = $("#oldPassword").val();
	var newPassword = $("#newPassword").val();
	var surePassword = $("#surePassword").val();
	if(newPassword.length<6 || oldPassword.length<6){
		alert("密码不能小于6位！");
		return;
	}

	if(newPassword != surePassword) {
		alert("两次输入密码不同！");
		return;
	}


	var oldPasswordMd5 = hex_md5(oldPassword);
	var newPasswordMd5 = hex_md5(newPassword);
	
	var data = "&crusrUuid="+dataGet("crusrUuid")+"&oldPwd="+oldPasswordMd5+"&newPwd="+newPasswordMd5+"&confirmPwd="+newPasswordMd5;
	var url = urlfile + "coreUser/update/pwd";
	ajaxTool("post",data,url,
		function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
			alert("error:" + data);
		},
		function success(data){
			if(!data.success) {
				alert(data.errMsg);
			}else{
				alert("密码修改成功");
				dataSave("login_password", newPassword);
				layerCloseAll();
			}
		}
	);
}

$(function() {
	var userName = dataGet("crusrCode")?dataGet("crusrCode"):dataGet("crusrName");
	$("#userName").html(userName);
	$("#welcomeFlag").html(dataGet("crusrGradeName"));

	// var data = "&crusrUuid="+dataGet("crusrUuid");
	// var url = urlfile + "coreClass/find/all/nothing/user";

	var data = "&crgaeUuid="+dataGet("crusrGrade");
	var url = urlfile + "coreGrade/views";
	ajaxTool("post",data,url,
		function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
			alert("error:" + data);
		},
		function success(data){
			if(!data.success) {
				alert(data.errMsg);
			}else{
				dataSave("crgaeUuid", data.data.crgaeUuid);
				var gradesItems = "";
				var pageClassesName =  data.data.crgaeClasssName.split(",");
				var pageClasss = data.data.crgaeClasss.split(",");
				for(var i=0; i<pageClassesName.length; i++) {
					if(pageClassesName[i]!="") {
						gradesItems += '<div class="border_right_item_bg" onclick="toLectureIndex(\''+pageClasss[i]+'\',\''+pageClassesName[i]+'\')">'+ pageClassesName[i] +'</div>';
					}
				}
				$('#grades').html(gradesItems);
			}
		}
	);
});

function toLectureIndex(grade, crcasName) {
	// var crusrClasss = dataGet("crusrClasss");
	// if(!crusrClasss) {
	// 	alert("未获得年级权限，开通请联系管理员");
	// 	return;
	// }
	// var grades = crusrClasss.split(",");
	// for(var i=0; i<grades.length; i++) {
	// 	if(grade == grades[i]){
			dataSave("uuid",grade);
			dataSave("gradeName",crcasName);
			nextView("../classroom/lectureIndex.html");
	// 		return;
	// 	}
	// }
	// alert("未获得年级权限，开通请联系管理员");
}

function backPageHome() {
	nextView("../login/login.html");
}