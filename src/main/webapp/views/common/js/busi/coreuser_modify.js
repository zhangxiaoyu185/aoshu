// JavaScript Document
$(function () {
	initModify();
	//提交
	$(".submit").on("click",function(){
		checkModify();
	});
});
//初始化
function initModify(){
	$(".crusrCode").val("");
	$(".crusrMobile").val("");
	$(".crusrStatus").val("1");
	$(".crusrGender").val("1");
	$(".crusrRemarks").val("");
	$(".crusrSchool").val("");
	gradeColumns();
	getInfo(getQueryString("id"));
}
//获取详情
function getInfo(id){
	var str = 'crusrUuid='+encodeURIComponent(id);
	getOData(str,"coreUser/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crusrCode").val(oData.data.crusrCode || "");
			$(".crusrMobile").val(oData.data.crusrMobile || "");
			$(".crusrStatus").val(oData.data.crusrStatus);
			$(".crusrGender").val(oData.data.crusrGender);
			$(".crusrRemarks").val(oData.data.crusrRemarks || "");
			$(".crusrSchool").val(oData.data.crusrSchool || "");
			$(".crusrReadClass").val(oData.data.crusrReadClass);
			$(".crusrGrade").val(oData.data.crusrGrade || "");
		} else {
			alert(data.errMsg);
		}
	}},true);
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
	}},true);
}
//检查提交
function checkModify(){
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

	var r=confirm("是否确认修改？");
	if (r==true){
		var msgObject = parent.layer.msg('处理中，请等待……', {
			icon: 16,
			shade: 0.4,
			time: waitImgTime //（如果不配置，默认是3秒）
		}, function(index){
			//do something
			parent.layer.close(index);
		});
		Modify(msgObject);
	}
}
//提交
function Modify(msgObject){
	var crusrUuid = getQueryString("id");
	var crusrCode = $(".crusrCode").val();
	var crusrMobile = $(".crusrMobile").val();
	var crusrStatus = $(".crusrStatus").val();
//	var crusrBirthday = $(".crusrBirthday").val();
	var crusrGender = $(".crusrGender").val();
//	var crusrQq = $(".crusrQq").val();
//	var crusrAddress = $(".crusrAddress").val();
	var crusrRemarks = $(".crusrRemarks").val();
//	var crusrClasss = $(".crusrClasss").val();
	var crusrGrade = $(".crusrGrade").val();
	var crusrSchool = $(".crusrSchool").val();
	var crusrReadClass = $(".crusrReadClass").val();
	var str = 'crusrUuid='+encodeURIComponent(crusrUuid)+'&crusrCode='+encodeURIComponent(crusrCode)+'&crusrMobile='+encodeURIComponent(crusrMobile)+'&crusrStatus='+encodeURIComponent(crusrStatus)+'&crusrGender='+encodeURIComponent(crusrGender)+'&crusrRemarks='+encodeURIComponent(crusrRemarks)+'&crusrGrade='+encodeURIComponent(crusrGrade)+'&crusrSchool='+encodeURIComponent(crusrSchool)+'&crusrReadClass='+encodeURIComponent(crusrReadClass);
	getOData(str,"coreUser/update/coreUser",{
		fn:function(oData){
			window.parent.refreshList();
			alert("修改成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}