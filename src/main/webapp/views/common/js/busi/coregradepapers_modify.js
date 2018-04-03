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
	$(".crgpsOrd").val("");
	$(".crgpsGqsj").val("");
	getInfo(getQueryString("id"));
}
//获取详情
function getInfo(id){
	var str = 'crgpsUuid='+encodeURIComponent(id);
	getOData(str,"coreGradePapers/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crgpsOrd").val(oData.data.crgpsOrd);
			$(".crgpsGqsj").val(oData.data.crgpsGqsj);
		} else {
			alert(data.errMsg);
		}
	}});
}

//检查提交
function checkModify(){
	if($.trim($(".crgpsOrd").val()) == ""){
		alert("排序号不能为空，请填写完再提交！");
		$(".crgpsOrd").focus();
		return false;
	}
	if($.trim($(".crgpsGqsj").val()) == ""){
		alert("过期时间不能为空，请填写完再提交！");
		$(".crgpsGqsj").focus();
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
	var crgpsUuid = getQueryString("id");
	var crgpsOrd = $(".crgpsOrd").val();
	var crgpsGqsj = $(".crgpsGqsj").val();
	var str = 'crgpsUuid='+encodeURIComponent(crgpsUuid)+'&crgpsOrd='+encodeURIComponent(crgpsOrd)+'&crgpsGqsj='+encodeURIComponent(crgpsGqsj);
	getOData(str,"coreGradePapers/update/coreGradePapers",{
		fn:function(oData){
			window.parent.refreshList();
			alert("修改成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}