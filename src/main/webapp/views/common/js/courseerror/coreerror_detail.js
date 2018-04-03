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
	var str = 'crerrUuid='+encodeURIComponent(id);
	getOData(str,"coreError/views",{fn:function(oData){
		if(oData.code == 1) {	
			
			$(".crusrName").text(oData.data.crusrName || "");
			$(".crerrCdate").text(oData.data.crerrCdate || "");
			$(".crerrUdate").text(oData.data.crerrUdate || "");
			$(".crqtsQuesCode").text(oData.data.crqtsQuesCode || "");
			$(".crqtsQuesCategory").text(oData.data.crqtsQuesCategory || "");
			
			if(oData.data.crqtsQuesLevel == 0) {
				$(".crqtsQuesLevel").text("无");
			}
			else {
				$(".crqtsQuesLevel").text(oData.data.crqtsQuesLevel || "");
			}
			$(".crqtsQuesClass").text(oData.data.crqtsQuesClassName || "");
			$(".crqtsQuesKnowledge").text(oData.data.crqtsQuesKnowledgeName || "");
			$(".crqtsQuesDir").text(oData.data.crqtsQuesDir || "");
			
			if(oData.data.crqtsQuesType == 0) {
				$(".class1").hide();
				$(".crqtsQuesType").text("文字");
				$(".crqtsQuesFont").text(oData.data.crqtsQuesFont || "");
			} else {
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
				$(".crqtsAnalysisFont").text(oData.data.crqtsAnalysisFont || "");
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
			$(".crqtsQuesProblem").text(oData.data.crqtsQuesProblem || "");
			$(".crqtsQuesAnswer").text(oData.data.crqtsQuesAnswer || "");
			$(".crqtsQuesColor").text(oData.data.crqtsQuesColor || "");
			var crerrJudgeCh = '已订正';
			var crerrJudge = oData.data.crerrJudge;
			if(1==crerrJudge){
				crerrJudgeCh = '未订正';
			}
			$(".crerrJudge").text(crerrJudgeCh);
			$(".crerrResult").text(oData.data.crerrResult || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
