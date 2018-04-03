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
	$(".crgceOrd").val("");
	$(".crgceGqsj").val("");
	getInfo(getQueryString("id"));
}
//获取详情
function getInfo(id){
	var str = 'crgceUuid='+encodeURIComponent(id);
	getOData(str,"coreGradeCourse/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crgceOrd").val(oData.data.crgceOrd);
			$(".crgceGqsj").val(oData.data.crgceGqsj);
		} else {
			alert(data.errMsg);
		}
	}});
}

//检查提交
function checkModify(){
	if($.trim($(".crgceOrd").val()) == ""){
		alert("排序号不能为空，请填写完再提交！");
		$(".crgceOrd").focus();
		return false;
	}
	if($.trim($(".crgceGqsj").val()) == ""){
		alert("过期时间不能为空，请填写完再提交！");
		$(".crgceGqsj").focus();
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
	var crgceUuid = getQueryString("id");
	var crgceOrd = $(".crgceOrd").val();
	var crgceGqsj = $(".crgceGqsj").val();
	var str = 'crgceUuid='+encodeURIComponent(crgceUuid)+'&crgceOrd='+encodeURIComponent(crgceOrd)+'&crgceGqsj='+encodeURIComponent(crgceGqsj);
	getOData(str,"coreGradeCourse/update/coreGradeCourse",{
		fn:function(oData){
			window.parent.refreshList();
			alert("修改成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}