var nowPage = 1;
var countProblems = 1;
var dataJson;
var noProblems="false";

var contentFontSize = 21;

$(function() {
	$('.content_testPaper').css("font-size", contentFontSize+ "px");

	FastClick.attach(document.body);
	answerBtnPosition();
	initPromple();
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
				
				$("#pageNos").html(pageNosStr);
				$("#sutraTest_rate").html("" + nowPage + "/" + countProblems);
				htmlContent();
			}
		}
	);
}

function htmlContent() {

	for(var i=0; i<countProblems; i++) {
		if(nowPage==(i+1)) {
			$("#noselect"+(i+1)).removeClass("noselect").addClass("selected");
		}else {
			$("#noselect"+(i+1)).removeClass("selected").addClass("noselect");
		}
	}

	if(problemJson[nowPage-1].crqtsDirMovie){
		$('#videoAlyze').show();
	}else{
		$('#videoAlyze').hide();
	}

	var NoString = "";
	var problemStr = "";

	if(problemJson[nowPage-1].crqtsQuesType=="0") {
		problemStr = NoString + problemJson[nowPage-1].crqtsQuesFont;
		problemStr = "题目：" + problemStr.replace(/\r/ig, "").replace(/\n/ig, "<br/>");
		$('#problem').css("margin-left", 60);
	}else {
		problemStr = NoString +'<img style="max-width: 100%;min-width: 90%;" src="'+ urlfile +'coreAttachment/image/get/'+ problemJson[nowPage-1].crqtsQuesUrl + timestamp()+'"/>';
		$('#problem').css("margin-left", 15);
	}


	var answerString = "解答：";
	var crqtsProblemStr;
	crqtsProblemStr = problemJson[nowPage-1].crqtsProblem;
	if(!crqtsProblemStr) {
		crqtsProblemStr = '';
		answerString = '';
	}
	crqtsProblemStr = crqtsProblemStr.replace(/\r/ig, "").replace(/\n/ig, "<br/>");
	var re=new RegExp("#","g");
	if(re.test(crqtsProblemStr)) {
		noProblems="false";
	}else {
		noProblems="true";
	}

	if(noProblems=="false") {
		var newstart=crqtsProblemStr.replace(re,'<div class="div_select  btn" style="display: -webkit-inline-box;padding-bottom: 15px;"><span id="answerSpan" class="div_noSelect btn" ></span></div>');
		var answerStr = '<div style="padding-top: 13px;padding-left: 26px;">' + answerString + newstart + '</div>';
		$("#answer").html(answerStr);
	}else{
		var answerStr = '<div style="padding-top: 13px;padding-left: 26px;font-size: 20px;">' + answerString + crqtsProblemStr + '</div>';
		$("#answer").html(answerStr);
	}

	$("#problem").html(problemStr);
	
}


function sutraTestSubmit() {

	var div_noSelects = $("body").find(".div_noSelect");
	var result = "";
	for(var i=0; i<div_noSelects.length; i++) {
		result += div_noSelects.eq(i).html();
		if(i!=div_noSelects.length-1){
			result += ",";
		}
	}

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
				nextView("aoshuIndex.html");
			}
		}
	);
}

function addCoreError() {

	var data = "&crerrUser="+ dataGet("crusrUuid")+"&crerrFrom="+GetParpam("jindianUuid") +"&crerrFromName="+(dataJson.crecsCourseName+"经典练习")
				+"&crerrFromType="+2 +"&crerrQues="+problemJson[nowPage-1].crqtsUuid+"&crqtsQuesCode="+problemJson[nowPage-1].crqtsCode
				+"&crqtsQuesCategory="+problemJson[nowPage-1].crqtsCategory+"&crqtsQuesLevel="+problemJson[nowPage-1].crqtsLevel
				// +"&crqtsQuesClass="+dataJson.crecsClass+"&crqtsQuesKnowledge="+problemJson[nowPage-1].crqtsKnowledge
				+"&crqtsQuesClass="+dataGet("uuid")+"&crqtsQuesKnowledge="+problemJson[nowPage-1].crqtsKnowledge
				+"&crqtsQuesDir="+problemJson[nowPage-1].crqtsDir+"&crqtsQuesType="+problemJson[nowPage-1].crqtsQuesType
				+"&crqtsQuesUrl="+problemJson[nowPage-1].crqtsQuesUrl+"&crqtsQuesFont="+encodeURIComponent(problemJson[nowPage-1].crqtsQuesFont)
				+"&crqtsAnalysisType="+problemJson[nowPage-1].crqtsAnalysisType+"&crqtsAnalysisUrl="+problemJson[nowPage-1].crqtsAnalysisUrl
				+"&crqtsAnalysisFont="+encodeURIComponent(problemJson[nowPage-1].crqtsAnalysisFont)+"&crqtsQuesProblem="+encodeURIComponent(problemJson[nowPage-1].crqtsProblem)
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


function backPageSutraTest() {
	nextView("aoshuIndex.html");
}


function sutraTestAnalyze() {
	layer.open({
	  type: 1,
	  shadeClose: true,
	  shade: 0.8,
	  content: sutraTestAnalyzeContent(),
	   style: 'border:none; background: transparent;',
	});
}

function sutraTestAnalyzeContent() {
	var analyzeStr = "";
	if(problemJson[nowPage-1].crqtsAnalysisType=="0") {
		analyzeStr += '<div style="background: #fff;padding: 20px;font-size: 20px;">' + problemJson[nowPage-1].crqtsAnalysisFont.replace(/\r/ig, "").replace(/\n/ig, "<br/>");
	}else {
		analyzeStr += '<div>' + '<img style="max-width: 707px;min-width: 680px;margin-left: 23px;margin-top: 16px;" src="'+ urlfile +'coreAttachment/image/get/'+ problemJson[nowPage-1].crqtsAnalysisUrl +'"/>';
	}
	analyzeStr += '</div>';
	return analyzeStr;
}

function moviePlay() {
	var typePlay = problemJson[nowPage-1].crqtsMovie.toLowerCase();
	var movieUrl = "../../../../movie/"+problemJson[nowPage-1].crqtsDirMovie+"/"+problemJson[nowPage-1].crqtsCode+"."+typePlay;
	var htmlStr =  "";
	if(typePlay =='mp4') {
		htmlStr =   '<embed style="width: 0;">'+
							'<video src="'+ movieUrl +'"  width="640" height="480" controls="controls"></video>'+
						'</embed>';
	}else {
		htmlStr = '<embed width="640" height="480" src="'+ movieUrl +'"></embed>';
	}
	
	layer.open({
	  type: 1,
	  shadeClose: true,
	  shade: 0.8,
	  content: htmlStr,
	   style: 'border:none; background: transparent;',
	});
}

// function fontSizeAdd() {
// 	if(contentFontSize>30) return;
// 	contentFontSize++;
// 	$('.content_testPaper').css("font-size", contentFontSize+ "px");
// }

// function fontSizeSubtract() {
// 	if(contentFontSize<18) return;
// 	contentFontSize--;
// 	$('.content_testPaper').css("font-size", contentFontSize+ "px");
// }

function fontBig(){
	contentFontSize = 24;
	$('#fontBig').removeClass("fontSizeChange").addClass("fontSizeChangeSelected");
	$('#fontMiddle').removeClass("fontSizeChangeSelected").addClass("fontSizeChange");
	$('#fontSmall').removeClass("fontSizeChangeSelected").addClass("fontSizeChange");
	$('.content_testPaper').css("font-size", contentFontSize+ "px");
}

function fontMiddle() {
	contentFontSize = 21;
	$('#fontMiddle').removeClass("fontSizeChange").addClass("fontSizeChangeSelected");
	$('#fontBig').removeClass("fontSizeChangeSelected").addClass("fontSizeChange");
	$('#fontSmall').removeClass("fontSizeChangeSelected").addClass("fontSizeChange");
	$('.content_testPaper').css("font-size", contentFontSize+ "px");
}

function fontSmall() {
	contentFontSize = 18;
	$('#fontSmall').removeClass("fontSizeChange").addClass("fontSizeChangeSelected");
	$('#fontMiddle').removeClass("fontSizeChangeSelected").addClass("fontSizeChange");
	$('#fontBig').removeClass("fontSizeChangeSelected").addClass("fontSizeChange");
	$('.content_testPaper').css("font-size", contentFontSize+ "px");
}