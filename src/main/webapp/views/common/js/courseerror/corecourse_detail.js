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
	var str = 'crcreUuid='+encodeURIComponent(id);
	getOData(str,"coreCourse/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crcreName").text(oData.data.crcreName || "");
			$(".crcreContent").text(oData.data.crcreContent || "");
			$(".crcreClass").text(oData.data.crcreClassName || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
