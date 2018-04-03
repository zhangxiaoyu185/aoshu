var dataJson;
var errTypeVar;

$(function (){
	FastClick.attach(document.body);
	answerBtnPosition();
	init();
})

function init() {
	var data = "&crerrUuid="+GetParpam("crerrUuid");
	errTypeVar = GetParpam("type");
	var url = urlfile + "coreError/views";
	ajaxTool("post",data,url,
		function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
			alert("error:" + data);
		},
		function success(data){
			if(!data.success) {
				alert(data.errMsg);
			}else{
				dataJson = data.data;
				htmlContent(data.data);
			}
		}
	);

	if(errTypeVar=="0") {
		$("#sutraTest_submit_btn").hide();
		$("#sutraTest_bottom_bg").hide();
	}
}

function htmlContent(dataJson) {
	var NoString = "";
	var problemStr = "";
	if(dataJson.crqtsQuesType=="0") {
		// problemStr = NoString + dataJson.crqtsQuesFont;
		problemStr = '<div style="padding: 26px;font-size: 19px;">'+NoString + dataJson.crqtsQuesFont.replace(/\r/ig, "").replace(/\n/ig, "<br/>")+'</div>';
	}else {
		problemStr = NoString +'<img style="max-width: 707px;max-height: 250px;min-width: 650px;margin-left: 23px;margin-top: 16px;" src="'+ urlfile +'coreAttachment/image/get/'+ dataJson.crqtsQuesUrl +'"/>'
	}

	var errFlag = "";
	// if(GetParpam("type")==1) {
	// 	errFlag = '<span class="span_red">错</span>';
	// }else {
	// 	errFlag = '<span>对</span>';
	// }

	var crqtsProblemStr;
	crqtsProblemStr = dataJson.crqtsQuesProblem;
	var re=new RegExp("#","g");
   	var newstart=crqtsProblemStr.replace(re,'<div class="div_select  btn" style="display: -webkit-inline-box;padding-bottom: 15px;"><span class="div_noSelect btn" ></span></div>');
	var answerStr = '<div style="padding-top: 13px;padding-left: 26px;">' + newstart + errFlag + '</div>';

	$("#sutraTest_problem_bg").html(problemStr);
	$("#sutraTest_answer_bg").html(answerStr);

	// if(dataJson.crerrResult){
	// 	var crerrResults = dataJson.crerrResult.split(',');
	// 	var answerSpans = $('.div_noSelect');
	// 	for(var i=0; i<crerrResults.length; i++) {
	// 		answerSpans.eq(i).html(crerrResults[i]); 
	// 	}
	// }

	// crerrResult  crqtsQuesAnswer

	var sureAnswer = dataJson.crqtsQuesAnswer;
	var resultAnswer = dataJson.crerrResult;
	if(sureAnswer){
		var div_noSelects = $("body").find(".div_noSelect");
		if(resultAnswer)
		var results = resultAnswer.split(",");
		if(sureAnswer)
		var	answers = sureAnswer.split(",");

		var resultsLength = results?results.length:0;
		var answersLength = answers?answers.length:0;

		var length = answersLength < resultsLength? answersLength: resultsLength;
		for(var i=0; i<answers.length; i++) {
			if(i<length&&results[i]){
				var booleanFlag="";
				if(results[i].toLowerCase()==answers[i]) {
					booleanFlag = '<div class="dui"></div>'
				}else {
					booleanFlag = '<div class="cuo"></div>'
				}
				div_noSelects.eq(i).html(results[i]?results[i]:"").after(booleanFlag);
			}else{
				if(results){
					div_noSelects.eq(i).html(results[i]?results[i]:"").after('<div class="cuo"></div>');
				}else{
					div_noSelects.eq(i).after('<div class="cuo"></div>');
				}
				
			}	
		}
	}
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

	if(result.toLowerCase() !=dataJson.crqtsQuesAnswer.toLowerCase() ) {
		alert("回答错误，请检查！");
		return;
	}

	updateCoreError(result);
}

function updateCoreError(crerrResult) {
	var data = "&crerrUuid="+GetParpam("crerrUuid") + "&crerrResult="+encodeURIComponent(crerrResult)+"&crerrJudge="+0;
	var url = urlfile + "coreError/update/coreError";
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

 // @param crerrUuid 标识UUID
	// * @param crerrResult 回答结果
	// * @param crerrJudge 判断（0对-》已订正;1错-未订正）

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

function backPageErrDesc() {
	nextView("errList.html");
}