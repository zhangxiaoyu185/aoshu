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
	var str = 'crusrUuid='+encodeURIComponent(id);
	getOData(str,"coreUser/views",{fn:function(oData){
		if(oData.code == 1) {
			var crusrGenderCh="男";
			var crusrGender=oData.data.crusrGender;
			if(0==crusrGender){
				crusrGenderCh="女";
			} else if (2==crusrGender){
				crusrGenderCh="其他";
			}
			var crusrStatusCh="启用";
			var crusrStatus=oData.data.crusrStatus;
			if(0==crusrStatus){
				crusrStatusCh="禁用";
			}
			//1一年级2二年级3三年级4四年级5五年级6六年级7其他
			var crusrReadClassCh="一年级";
			var crusrReadClass=oData.data.crusrReadClass;
			if(1==crusrReadClass){
				crusrReadClassCh="一年级";
			}else if(2==crusrReadClass){
				crusrReadClassCh="二年级";
			}else if(3==crusrReadClass){
				crusrReadClassCh="三年级";
			}else if(4==crusrReadClass){
				crusrReadClassCh="四年级";
			}else if(5==crusrReadClass){
				crusrReadClassCh="五年级";
			}else if(6==crusrReadClass){
				crusrReadClassCh="六年级";
			}else if(7==crusrReadClass){
				crusrReadClassCh="其他";
			}
			$(".crusrName").text(oData.data.crusrName || "");
			$(".crusrCode").text(oData.data.crusrCode || "");
			$(".crusrMobile").text(oData.data.crusrMobile || "");
			$(".crusrStatus").text(crusrStatusCh);
//			$(".crusrBirthday").text(oData.data.crusrBirthday || "");
			$(".crusrGender").text(crusrGenderCh);
			$(".crusrCdate").text(oData.data.crusrCdate || "");
			$(".crusrUdate").text(oData.data.crusrUdate || "");
//			$(".crusrQq").text(oData.data.crusrQq || "");
//			$(".crusrAddress").text(oData.data.crusrAddress || "");
			$(".crusrRemarks").text(oData.data.crusrRemarks || "");
			$(".crusrGrade").text(oData.data.crusrGradeName || "");
			$(".crusrSchool").text(oData.data.crusrSchool || "");
			$(".crusrReadClass").text(crusrReadClassCh);
			
			/*var imgUrl="/"+oData.data.list[0].cratmDir+"/"+oData.data.list[0].cratmFileName;
			getImageWidthHeight(urlfile+"/coreAttachment/image/get/thumb/"+oData.data.list[0].cratmUuid+"?timestamp="+(new Date()).valueOf(),function(realWidth,realHeight){
				var width = 0;
				var height = 200;
				//如果真实的宽度大于浏览器的宽度就按照200显示
				if(realHeight>=height){
					width = realWidth/realHeight*height;
					$(".preimg").css("width",width).css("height",height);
				}else{//如果小于浏览器的宽度按照原尺寸显示}
					$(".preimg").css("width",realWidth+'px').css("height",realHeight+'px');
				}
				$(".preimg").attr("src",urlfile+"/coreAttachment/image/get/thumb/"+oData.data.list[0].cratmUuid+"?timestamp="+(new Date()).valueOf());
				$(".preimg").attr("data-filename",imgUrl);
			});*/
		} else {
			alert(data.errMsg);
		}
	}});
}
