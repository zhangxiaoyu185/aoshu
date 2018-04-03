var nowPage = 1;
var countProblems = 1;
var dataJson;
var noProblems="false";

$(function() {
	answerBtnPosition();
	initPromple();
	$("#sutraTest_rate").html("" + nowPage + "/" + countProblems);
});

function initPromple() {
	var data = "&crecsUuid="+ GetParpam("jindianUuid");
	var url = urlfile + "coreExercises/views";
	ajaxTool("post",data,url,
		function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
			alert("error:" + data);
		},
		function success(data){
			if(data.errMsg=="没有题目") {
				alert(data.errMsg);
				return;
			}
			if(!data.success) {
				alert(data.errMsg);
			}else{
				dataJson = data.data;


				var problemJsonContent = JSON.parse(data.data.crecsContent);
				problemJson = problemJsonContent.problem;
				countProblems = problemJson.length;
				var pageNosStr = "";
				for(var i=0; i<countProblems; i++) {
					pageNosStr += '<div id="noselect'+(i+1)+'" class="noselect">'+(i+1)+'</div>';
				}
				
				// $("#pageNos").html(pageNosStr);
				$("#sutraTest_rate").html("" + nowPage + "/" + countProblems);
				htmlContent();
			}
		}
	);
}

function htmlContent() {
	var NoString = "";
	var problemStr = "";
	if(problemJson[nowPage-1].crqtsQuesType=="0") {
		problemStr = '<div style="padding: 26px;font-size: 19px;">'+NoString + problemJson[nowPage-1].crqtsQuesFont.replace(/\r/ig, "").replace(/\n/ig, "<br/>")+'</div>';
	}else {
		problemStr = NoString +'<img style="max-width: 707px;max-height: 250px;margin-left: 23px;margin-top: 16px;" src="'+ urlfile +'coreAttachment/image/get/'+ problemJson[nowPage-1].crqtsQuesUrl + timestamp() +'"/>';
	}

	var crqtsProblemStr;
	crqtsProblemStr = problemJson[nowPage-1].crqtsProblem;
	if(!crqtsProblemStr) {
		crqtsProblemStr = '';
	}
	crqtsProblemStr = crqtsProblemStr.replace(/\r/ig, "").replace(/\n/ig, "<br/>");
	var re=new RegExp("#","g");
	if(re.test(crqtsProblemStr)) {
		noProblems="false";
	}else {
		noProblems="true";
	}

	if(noProblems=="false") {
		var newstart=crqtsProblemStr.replace(re,'<span id="answerSpan" class="div_noSelect btn" ></span>');
		var answerStr = '<div style="padding-top: 13px;padding-left: 26px;font-size: 20px;">' + newstart + '</div>';
		$("#sutraTest_answer_bg").html(answerStr);
	}else{
		var answerStr = '<div style="padding-top: 13px;padding-left: 26px;font-size: 20px;">' + answerString + crqtsProblemStr + '</div>';
		$("#sutraTest_answer_bg").html(answerStr);
	}
   	
	$("#sutraTest_problem_bg").html(problemStr);
}

function sutraTestSubmit() {

	var div_noSelects = $("body").find(".div_noSelect");
	var result = "";
	for(var i=0; i<div_noSelects.length; i++) {
		result += div_noSelects.eq(i).html();
		if(i!=div_noSelects.length-1){
			result += ",";
		}
   		 // crqtsProblemStr = crqtsProblemStr.replace(re, );
	}

	// var answerSpan = $("#answerSpan").html();
	if(noProblems=="false"&& result.toLowerCase() !=problemJson[nowPage-1].crqtsAnswer.toLowerCase() ) {
		addCoreError();
		alert("回答错误，请检查！");
		return;
	}

	if(nowPage == countProblems) {
		finishSutraTest();
		return;
	}

	nowPage++;
	htmlContent();
	$("#sutraTest_rate").html("" + nowPage + "/" + countProblems);

}

function sutraTestAnalyze() {
	layer.open({
	  type: 1,
	  shadeClose: true,
	  shade: 0.8,
	  content: sutraTestAnalyzeContent(), //iframe的url
	   style: 'border:none; background: transparent;',
	});
}

function sutraTestAnalyzeContent() {
	var analyzeStr = "";
	if(problemJson[nowPage-1].crqtsAnalysisType=="0") {
		analyzeStr += '<div style="background: #fff;padding: 20px;font-size: 20px;">' + problemJson[nowPage-1].crqtsAnalysisFont.replace(/\r/ig, "").replace(/\n/ig, "<br/>");
	}else {
		analyzeStr += '<div>' + '<img style="max-width: 707px;max-height: 250px;margin-left: 23px;margin-top: 16px;" src="'+ urlfile +'coreAttachment/image/get/'+ problemJson[nowPage-1].crqtsAnalysisUrl +'"/>';
	}
	analyzeStr += '</div>';
	return analyzeStr;
}

function addCoreError() {
	var data = "&crerrUser="+ dataGet("crusrUuid")+"&crerrFrom="+GetParpam("jindianUuid") +"&crerrFromName="+(dataJson.crecsCourseName+"经典练习")
				+"&crerrFromType="+2 +"&crerrQues="+problemJson[nowPage-1].crqtsUuid+"&crqtsQuesCode="+problemJson[nowPage-1].crqtsCode
				+"&crqtsQuesCategory="+problemJson[nowPage-1].crqtsCategory+"&crqtsQuesLevel="+problemJson[nowPage-1].crqtsLevel
				+"&crqtsQuesClass="+dataJson.crecsClass+"&crqtsQuesKnowledge="+problemJson[nowPage-1].crqtsKnowledge
				+"&crqtsQuesDir="+problemJson[nowPage-1].crqtsDir+"&crqtsQuesType="+problemJson[nowPage-1].crqtsQuesType
				+"&crqtsQuesUrl="+problemJson[nowPage-1].crqtsQuesUrl+"&crqtsQuesFont="+problemJson[nowPage-1].crqtsQuesFont
				+"&crqtsAnalysisType="+problemJson[nowPage-1].crqtsAnalysisType+"&crqtsAnalysisUrl="+problemJson[nowPage-1].crqtsAnalysisUrl
				+"&crqtsAnalysisFont="+problemJson[nowPage-1].crqtsAnalysisFont+"&crqtsQuesProblem="+problemJson[nowPage-1].crqtsProblem
				+"&crqtsQuesAnswer="+problemJson[nowPage-1].crqtsAnswer+"&crqtsQuesColor="+""
				+"&crerrResult="+($("#answerSpan").html())+"&crerrJudge="+1;

	var url = urlfile + "coreError/add/coreError";
	ajaxTool("post",data,url,
		function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
			alert("error:" + data);
		},
		function success(data){
			if(!data.success) {
				alert(data.errMsg);
			}else{
				
			}
		}
	);
}

function finishSutraTest() {
	var data = "&cresaExercUuid="+GetParpam("jindianUuid")+"&cresaCourseName="+dataJson.crecsCourseName+"&cresaUser="+ dataGet("crusrUuid")
	+"&cresaClass="+dataJson.crecsClass+"&cresaClassName="+dataJson.crecsClassName;

	var url = urlfile + "coreExercisesAnswer/add/coreExercisesAnswer/other";
	ajaxTool("post",data,url,
		function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
			alert("error:" + data);
		},
		function success(data){
			if(!data.success) {
				alert(data.errMsg);
			}else{
				backPage();
			}
		}
	);
}

function backPageSutraTest() {
	nextView("aoshuIndex.html");
}