var nowPage = 1;
var lastPageNumber;
var resultJson;

$(function() {
	errTypeVar = dataGet("errTypeVar");
	if(errTypeVar==1) {
		$('#errDui').addClass('errList_nocorrect');
		$('#errCuo').addClass('errList_corrected');
	}else{
		$('#errDui').addClass('errList_corrected');
		$('#errCuo').addClass('errList_nocorrect');
	}
	initErrList(errTypeVar);
})

function initErrList(errType) {//errType 0对-》已订正;1错-未订正
	var data = "&crerrUser="+dataGet("crusrUuid") + "&crqtsQuesClass="+dataGet("uuid") + "&crerrJudge="+errType + "&pageNum="+nowPage + "&pageSize="+8;
	var url = urlfile + "coreError/find/by";
	ajaxTool("post",data,url,
		function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
			alert("error:" + data);
		},
		function success(data){
			if(!data.success) {
				alert(data.errMsg);
			}else{
				dataJson = data.data;
				lastPageNumber = data.data.lastPageNumber;
				if(nowPage>=lastPageNumber){
					$("#nextPageBtn").removeClass("errList_page_en").addClass("errList_page_dis");
				}

				if(nowPage<=1){
					$("#upPageBtn").removeClass("errList_page_en").addClass("errList_page_dis");
				}

				var errListHtml = "";
				for(var i=0; i<dataJson.result.length; i++) {
					var ques = "";
					if(dataJson.result[i].crqtsQuesType == "1"){
						ques = "内容为图片";
					}else{
						ques = dataJson.result[i].crqtsQuesFont;
					}
					errListHtml += 	'<div class="errList_item_type'+(i%2+1)+' btn" onclick="toErrDesc(\''+dataJson.result[i].crerrUuid+'\')">'+
										'<div id="errList_no" class="errList_no">'+(i+1)+'</div>'+
										'<div class="errList_item_issue_bg">'+
											'<div id="div_first" class="div_first">'+dataJson.result[i].crerrFromName+'</div>'+
											'<div id="div_second" class="div_second">'+dataGet("gradeName")+'</div>'+
										'</div>'+
										'<div id="item_tim" class="errList_item_time_bg">'+dataJson.result[i].crerrUdate+'</div>'+
									'</div>';
				}

				$("#errList_content_content").html(errListHtml);
				$("#errList_page_rate").html(""+nowPage + "/" + lastPageNumber);
			}
		}
	);
}
// gradeName
// crerrCdate: "2016-06-04 23:51:00"
// crerrFrom: "0523222856044ULq"
// crerrFromName: "一年级(模拟试卷1)"
// crerrFromType: 1
// crerrJudge: 1
// crerrQues: "0519220021439XqD"
// crerrResult: ""
// crerrUdate: "2016-06-04 23:51:04"
// crerrUuid: "0604222506275vVN"
// crqtsAnalysisFont: ""
// crqtsAnalysisType: 1
// crqtsAnalysisUrl: "0519220021591rY0"
// crqtsQuesAnswer: "1"
// crqtsQuesCategory: "6"
// crqtsQuesClass: "1"
// crqtsQuesCode: "a91001"
// crqtsQuesColor: "#101010"
// crqtsQuesDir: "一年级模拟卷"
// crqtsQuesFont: ""
// crqtsQuesKnowledge: "061"
// crqtsQuesLevel: 0
// crqtsQuesProblem: "你好#"
// crqtsQuesType: 1
// crqtsQuesUrl: "0519220021465yRO"
// crusrName: "15157135185"
var errTypeVar;
function errListCorrectSelect(thisVar, errType) {
	nowPage = 1;
	lastPageNumber = 1;
	$("#upPageBtn").removeClass("errList_page_dis").addClass("errList_page_en");
	$("#nextPageBtn").removeClass("errList_page_dis").addClass("errList_page_en");
	
	radio(thisVar,'errList_nocorrect','errList_corrected');
	dataSave("errTypeVar",errType);
	initErrList(errType);
	errTypeVar = errType;

}

function errList_upPage_btn() {
	if($("#upPageBtn").hasClass("errList_page_dis")){
		return;
	}
	nowPage--;
	if(nowPage<=1){
		$("#upPageBtn").removeClass("errList_page_en").addClass("errList_page_dis");
	}
	if(nowPage<lastPageNumber){
		$("#nextPageBtn").removeClass("errList_page_dis").addClass("errList_page_en");
	}
	initErrList(errTypeVar);
}

function errList_nextPage_btn() {
	if($("#nextPageBtn").hasClass("errList_page_dis")){
		return;
	}
	nowPage++;
	if(nowPage>1){
		$("#upPageBtn").removeClass("errList_page_dis").addClass("errList_page_en");
	}
	if(nowPage>=lastPageNumber){
		$("#nextPageBtn").removeClass("errList_page_en").addClass("errList_page_dis");
	}
	initErrList(errTypeVar);
}

function toErrDesc(crerrUuid) {
	nextView("errDesc.html?crerrUuid="+crerrUuid + "&type=" + errTypeVar + "&timestamp=" + (new Date()).valueOf());
}

function backPageErrList() {
	nextView("lectureIndex.html");
}