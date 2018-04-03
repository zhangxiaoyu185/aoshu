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
	var str = 'crqtsUuid='+encodeURIComponent(id);
	getOData(str,"coreQuestions/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crqtsCode").text(oData.data.crqtsCode || "");
			$(".crqtsCategory").text(oData.data.crceyName || "");
			if(oData.data.crqtsLevel == 0) {
				$(".crqtsLevel").text("无");
			}
			else {
				$(".crqtsLevel").text(oData.data.crqtsLevel || "");
			}			
			$(".crqtsClass").text(oData.data.crcasName || "");
			$(".crqtsKnowledge").text(oData.data.crkleName || "");
			$(".crqtsDir").text(oData.data.crqtsDir || "");
			$(".crqtsDirMovie").text(oData.data.crqtsDirMovie || "");
			if(oData.data.crqtsQuesType == 0) {
				$(".class1").hide();
				$(".crqtsQuesType").text("文字");
				var textQuesFont = oData.data.crqtsQuesFont.replace(/\n/ig, '<br>').replace(new RegExp(/(&nbsp;)/g),'    ');
				$(".crqtsQuesFont").html(textQuesFont || "");
			}
			else {
				$(".class2").hide();
				$(".crqtsQuesType").text("图片");
				if(oData.data.crqtsQuesUrl){
					getImageWidthHeight(urlfile+"/coreAttachment/image/get/"+oData.data.crqtsQuesUrl+"?timestamp="+(new Date()).valueOf(),function(realWidth,realHeight){
						var width = 0;
						var height = 150;
						//如果真实的宽度大于浏览器的宽度就按照150显示
						if(realHeight>=height){
							width = realWidth/realHeight*height;
							$(".crqtsQuesUrl").css("width",width).css("height",height);
						}
						else{//如果小于浏览器的宽度按照原尺寸显示
							$(".crqtsQuesUrl").css("width",realWidth+'px').css("height",realHeight+'px');
						}
						$(".crqtsQuesUrl").attr("src",urlfile+"/coreAttachment/image/get/"+oData.data.crqtsQuesUrl+"?timestamp="+(new Date()).valueOf());
					});
				}
			}			
			if(oData.data.crqtsAnalysisType == 0) {
				$(".class3").hide();
				$(".crqtsAnalysisType").text("文字");
				var textAnalysisFont = oData.data.crqtsAnalysisFont.replace(/\n/ig, '<br>').replace(new RegExp(/(&nbsp;)/g),'    ');				
				$(".crqtsAnalysisFont").text(textAnalysisFont || "");
			}
			else {
				$(".class4").hide();
				$(".crqtsAnalysisType").text("图片");
				if(oData.data.crqtsAnalysisUrl){
					getImageWidthHeight(urlfile+"/coreAttachment/image/get/"+oData.data.crqtsAnalysisUrl+"?timestamp="+(new Date()).valueOf(),function(realWidth,realHeight){
						var width = 0;
						var height = 150;
						//如果真实的宽度大于浏览器的宽度就按照150显示
						if(realHeight>=height){
							width = realWidth/realHeight*height;
							$(".crqtsAnalysisUrl").css("width",width).css("height",height);
						}
						else{//如果小于浏览器的宽度按照原尺寸显示
							$(".crqtsAnalysisUrl").css("width",realWidth+'px').css("height",realHeight+'px');
						}
						$(".crqtsAnalysisUrl").attr("src",urlfile+"/coreAttachment/image/get/"+oData.data.crqtsAnalysisUrl+"?timestamp="+(new Date()).valueOf());
					});
				}
			}
			var textProblem = oData.data.crqtsProblem.replace(/\n/ig, '<br>').replace(new RegExp(/(&nbsp;)/g),'    ');
			$(".crqtsProblem").html(textProblem || "");
			$(".crqtsAnswer").text(oData.data.crqtsAnswer || "");
			$(".crqtsCdate").text(oData.data.crqtsCdate || "");
			$(".crqtsColor").text(oData.data.crqtsColor || "");
			$(".crqtsMovie").text(oData.data.crqtsMovie || "");
			$(".crqtsUdate").text(oData.data.crqtsUdate || "");
			if(oData.data.crqtsMovie) {
				var voideUrl="../../../movie/"+oData.data.crqtsDirMovie+"/"+oData.data.crqtsCode+'.'+oData.data.crqtsMovie.toLowerCase()+ "&typePlay="+oData.data.crqtsMovie.toLowerCase();
				var str_html = '<a target="_blank" href="test.html?id='+voideUrl+'" >播放</a>';
				$(".movieSapn").html(str_html);
			}
		} else {
			alert(data.errMsg);
		}
	}});
}
