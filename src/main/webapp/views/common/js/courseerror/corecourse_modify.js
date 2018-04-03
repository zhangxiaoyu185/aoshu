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
	$(".crcreName").val("");
	$(".crcreContent").val("");
	getClass();
	getInfo(getQueryString("id"));
}

//加载列表下拉框内容
function getClass(){
	var str = '';
	getOData(str,"coreClass/find/all/nothing",{fn:function(oData){
		if(oData.code == 1) {
			var strhtml_select = '';
			var arrData = oData.data;
			var ln = arrData.length;
			for(var i=0;i<ln;i++){
				strhtml_select += '<option value="'+arrData[i].crcasUuid+'">'+arrData[i].crcasName+'</option>';
			}
			$('.crcreClass').html(strhtml_select);
		} else {
			alert(data.errMsg);
		}
	}},true);
}
//获取详情
function getInfo(id){
	var str = 'crcreUuid='+encodeURIComponent(id);
	getOData(str,"coreCourse/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crcreName").val(oData.data.crcreName || "");
			$(".crcreContent").val(oData.data.crcreContent || "");
			$(".crcreClass").val(oData.data.crcreClass || "");
		} else {
			alert(data.errMsg);
		}
	}});
}

//检查提交
function checkModify(){
	if($.trim($(".crcreName").val()) == ""){
		alert("课程名不能为空，请填写完再提交！");
		$(".crcreName").focus();
		return false;
	}
	if($.trim($(".crcreClass").val()) == ""){
		alert("所属年级不能为空，请填写完再提交！");
		$(".crcreClass").focus();
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
	var crcreUuid = getQueryString("id");
	var crcreName = $(".crcreName").val();
	var crcreContent = $(".crcreContent").val();
	var crcreClass = $(".crcreClass").val();
	var str = 'crcreUuid='+encodeURIComponent(crcreUuid)+'&crcreName='+encodeURIComponent(crcreName)+'&crcreContent='+encodeURIComponent(crcreContent)+'&crcreClass='+encodeURIComponent(crcreClass);
	getOData(str,"coreCourse/update/coreCourse",{
		fn:function(oData){
			window.parent.refreshList();
			alert("修改成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}