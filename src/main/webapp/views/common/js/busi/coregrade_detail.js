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
	var str = 'crgaeUuid='+encodeURIComponent(id);
	getOData(str,"coreGrade/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crgaeName").text(oData.data.crgaeName || "");
			$(".crgaeClasss").text(oData.data.crgaeClasssName || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
