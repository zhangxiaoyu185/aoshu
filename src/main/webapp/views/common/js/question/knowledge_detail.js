// JavaScript Document
$(function () {
	initDetail();
});
//初始化
function initDetail(){
	getInfo(getQueryString("id"));
	
}
//获取详情
function getInfo(id){
	var str = 'crkleUuid='+encodeURIComponent(id);
	getOData(str,"coreKnowledge/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crkleCategory").text(oData.data.crceyName || "");
			$(".crkleName").text(oData.data.crkleName || "");
			$(".crkleTop").text(oData.data.crkleFatherName || "");
		} else {
			alert(data.errMsg);
		}
	}});
}