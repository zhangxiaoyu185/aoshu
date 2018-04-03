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
	var str = 'crceyUuid='+encodeURIComponent(id);
	getOData(str,"coreCategory/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crceyName").text(oData.data.crceyName || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
