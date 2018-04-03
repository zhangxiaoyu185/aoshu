// JavaScript Document
$(function () {
	initAdd();
	//提交
	$(".submit").on("click",function(){
		checkAdd();
	});
});
//初始化
function initAdd(){
	$(".crusrName").val("");
	$(".crusrCode").val("");
	$(".crusrMobile").val("");
	$(".crusrStatus").val("1");
//	$(".crusrBirthday").val("");
	$(".crusrGender").val("1");
//	$(".crusrQq").val("");
//	$(".crusrAddress").val("");
	$(".crusrCrade").val("1");
	$(".crusrSchool").val("");
	$(".crusrReadClass").val("");
	$(".crusrRemarks").val("");
	gradeColumns();
}
//加载班级下拉框内容
function gradeColumns(){
	var str = '';
	getOData(str,"coreGrade/find/select",{fn:function(oData){
		var strhtml_goodColumns = '';
		var arrData = oData.data;
		var ln = arrData.length;
		for(var i=0;i<ln;i++){
			strhtml_goodColumns += '<option value ="'+arrData[i].crgaeUuid+'" >'+arrData[i].crgaeName+'</option>';
		}
		$(".crusrGrade").html(strhtml_goodColumns);
	}});
}
//检查提交
function checkAdd(){
	if($.trim($(".crusrName").val()) == ""){
		alert("帐户名称不能为空，请填写完再提交！");
		$(".crusrName").focus();
		return false;
	}
	if($.trim($(".crusrStatus").val()) == ""){
		alert("状态不能为空，请填写完再提交！");
		$(".crusrStatus").focus();
		return false;
	}
	if($.trim($(".crusrGrade").val()) == ""){
		alert("班级不能为空，请填写完再提交！");
		$(".crusrGrade").focus();
		return false;
	}

	var r=confirm("是否确认增加？");
	if (r==true){
		var msgObject = parent.layer.msg('处理中，请等待……', {
			icon: 16,
			shade: 0.4,
			time: waitImgTime //（如果不配置，默认是3秒）
		}, function(index){
			//do something
			parent.layer.close(index);
		});
		Add(msgObject);
	}
}
//提交
function Add(msgObject){
	var crusrName = $(".crusrName").val();
	var crusrCode = $(".crusrCode").val();
	var crusrMobile = $(".crusrMobile").val();
	var crusrStatus = $(".crusrStatus").val();
//	var crusrBirthday = $(".crusrBirthday").val();
	var crusrGender = $(".crusrGender").val();
//	var crusrQq = $(".crusrQq").val();
//	var crusrAddress = $(".crusrAddress").val();
	var crusrGrade = $(".crusrGrade").val();
	var crusrSchool = $(".crusrSchool").val();
	var crusrReadClass = $(".crusrReadClass").val();
	var crusrRemarks = $(".crusrRemarks").val();
	var str = 'crusrName='+encodeURIComponent(crusrName)+'&crusrCode='+encodeURIComponent(crusrCode)+'&crusrMobile='+encodeURIComponent(crusrMobile)+'&crusrStatus='+encodeURIComponent(crusrStatus)+'&crusrGender='+encodeURIComponent(crusrGender)+'&crusrGrade='+encodeURIComponent(crusrGrade)+'&crusrSchool='+encodeURIComponent(crusrSchool)+'&crusrReadClass='+encodeURIComponent(crusrReadClass)+'&crusrRemarks='+encodeURIComponent(crusrRemarks);
	getOData(str,"coreUser/add/coreUser",{
		fn:function(oData){
			window.parent.refreshList();
			alert("增加成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}