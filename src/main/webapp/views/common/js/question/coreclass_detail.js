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
	var str = 'crcasUuid='+encodeURIComponent(id);
	getOData(str,"coreClass/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crcasName").text(oData.data.crcasName || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
