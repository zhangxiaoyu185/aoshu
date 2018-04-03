var judgeBoolean = "false";
var isTest = "false";
var restWork="false";
var noProblems="false";

var countTime = 0;
var timer;
var HH = 0;
var mm = 0;
var ss = 0;
var str = '';

var contentFontSize = 21;
// window.onload = function(){
function timeStart() {
	
	timer = setInterval(function(){
		str = "";
		countTime ++ ;

		if(++ss==60) {
			if(++mm==60) {
				HH++;
				mm=0;
			}
			ss=0;
		}

		str+=HH<10?"0"+HH:HH;
		str+=":";
		str+=mm<10?"0"+mm:mm;
		str+=":";
		str+=ss<10?"0"+ss:ss;
		$('#testPaper_time').html(str);
		// document.getElementById("d").innerHTML = str;
	},1000);
};

function handInClose() {
	// clearInterval
	timer = setInterval(function(){
		str = "";
		countTime ++ ;

		if(++ss==60) {
			if(++mm==60) {
				HH++;
				mm=0;
			}
			ss=0;
		}

		str+=HH<10?"0"+HH:HH;
		str+=":";
		str+=mm<10?"0"+mm:mm;
		str+=":";
		str+=ss<10?"0"+ss:ss;
		$('#testPaper_time').html(str);
		// document.getElementById("d").innerHTML = str;
	},1000);
	layerCloseAll();
}

function handIn_sure(){
	papersAnswer(true);
}

var handInStr = '<div id="handIn_bg" class="handIn_bg">'+
					'<div class="handIn_btn">'+
						'<div class="handIn_sure btn" onclick="handIn_sure()"></div>'+
						'<div class="handIn_continue btn" onclick="handInClose()"></div>'+
					'</div>'+
				'</div>';

var resultStrJson;

function handInAlert(force) {
	var handInContent = "";
	if(countTime>10||force){
		var resultStr = '<div class="result_bg">'+
					// '<div class="result_back"  onclick="layerCloseAll()"></div>'+
					'<div class="result_back"  onclick="handInAlertLayerCloseAll()"></div>'+
					'<div class="result_title"></div>'+
					'<div class="result_item">'+
						'<div class="result_score"></div>'+
						'<div class="result_content_score">'+resultStrJson.crpsaScore+'</div>'+
						'<div class="result_100_flag"></div>'+
					'</div>'+
					'<div class="result_item">'+
						'<div class="result_time"></div>'+
						'<div class="result_content_time">'+resultStrJson.crpsaTime+'</div>'+
					'</div>'+
					'<div class="result_item" style="margin-top: 58px;">'+
						// '<div class="result_reform" onclick="reset()"></div>'+
						'<div class="result_details1" onclick="details()"></div>'+
					'</div>'+
				'</div>';

		$('#testPaper_score').html(resultStrJson.crpsaScore);

		handInContent = resultStr;
	}else{
		handInContent = handInStr;
	}

	layer.open({
	  type: 1,
	  shadeClose: false,
	  shade: 0.8,
	  content: handInContent, //iframe的url
	   style: 'border:none; background: transparent;',
	});
}


var problemJson;
var countProblems = 0;
var nowPage = 1;
var dataJson;

$(function() {
	FastClick.attach(document.body);
	answerBtnPosition();

	$('#testPaper_title').html(dataGet("crpesName"));

	$('.content_testPaper').css("font-size", contentFontSize+ "px");
	
	// if(dataGet("isTest")=="N") {
		isTest = "false";
		initTest();
	// }else {
	// 	isTest = "true";
	// 	details();
	// 	$('#testPaper_time').html(dataGet("time"));
	// 	$('#testPaper_score').html(dataGet("score"));
		
	// 	// dataSave("score",resultJson[index].score);
	// }
	// initTest();
});

function initTest() {
	timeStart();
	judgeBoolean = "false";
	var data = "&crpesUuid="+GetParpam("uuid");
	var url = urlfile + "corePapers/views";
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
				problemJsonContent = JSON.parse(data.data.crpesContent);
				problemJson = problemJsonContent.problem;
				countProblems = problemJson.length;

				var pageNosStr = "";
				for(var i=0; i<countProblems; i++) {
					pageNosStr += '<div id="noselect'+(i+1)+'" class="noselect">'+(i+1)+'</div>';
				}
				$("#pageNos").html(pageNosStr);

				htmlContent();
			}
		}
	);
}

function details() {
	judgeBoolean = "true";
	restWork = "true";
	$("#handIn").hide();
	dataSave("isTest","Y");

	var data = "";
	if(isTest == "true") {
		data = "&crpsaUuid="+dataGet("answerUuid");
	}else {
		data = "&crpsaUuid="+resultStrJson.crpsaUuid;
	}
	var url = urlfile + "corePapersAnswer/views/other";
	ajaxTool("post",data,url,
		function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
			alert("error:" + data);
		},
		function success(data){
			if(!data.success) {
				alert(data.errMsg);
			}else{//judge 0 对  1错
				// $("#resetBtn").show();
				// $("#seeAnswer").show();

				layerCloseAll();
				dataJson = data.data;

				problemJsonContent = JSON.parse(data.data.crpsaContent);
				problemJson = problemJsonContent.problem;
				// problemJson = JSON.parse(data.data.crpsaContent);
				countProblems = problemJson.length;
				var pageNosStr = "";
				for(var i=0; i<countProblems; i++) {
					pageNosStr += '<div id="noselect'+(i+1)+'" class="noselect">'+(i+1)+'</div>';
				}
				
				$("#pageNos").html(pageNosStr);
				htmlContent();
			}
		}
	);
}

function htmlContent() {
	// var NoString = "【题目"+nowPage+"】:"
	for(var i=0; i<countProblems; i++) {
		if(nowPage==(i+1)) {
			$("#noselect"+(i+1)).removeClass("noselect").addClass("selected");
		}else {
			$("#noselect"+(i+1)).removeClass("selected").addClass("noselect");
		}
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

   	// var newstart=crqtsProblemStr.replace(re,'<span class="div_noSelect btn"></span>');
	var answerStr = "";

	if(noProblems=="false") {
		var newstart=crqtsProblemStr.replace(re,'<div class="div_select  btn" style="display: -webkit-inline-box;padding-bottom: 15px;"><span class="div_noSelect"></span></div>');
		answerStr = answerString + newstart;
	}else{
		answerStr = '<div style="padding-top: 13px;padding-left: 26px;font-size: 20px;">' + answerString + crqtsProblemStr + '</div>';
	}


	// if(judgeBoolean == "true"){
	// 	var judgeStr = problemJson[nowPage-1].judge;
	// 	if(judgeStr == 0) {
	// 		answerStr += '<span>对</span>';
	// 	}else {
	// 		answerStr += '<span class="span_red">错</span>';
	// 	}
	// }

	$("#answer").html(answerStr);
	$("#problem").html(problemStr);

	var sureAnswer = problemJson[nowPage-1].crqtsAnswer;
	var resultAnswer = problemJson[nowPage-1].result;
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
			if(judgeBoolean == "false"){
				if(results && results[i]) {
					div_noSelects.eq(i).html(results[i]);
				}
				continue;
			}
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

function upPage() {
	$("#checkAnswer").html("");

	var div_noSelects = $("body").find(".div_noSelect");
	var result = "";
	for(var i=0; i<div_noSelects.length; i++) {
		result += div_noSelects.eq(i).html();
		if(i!=div_noSelects.length-1){
			result += ",";
		}
	}
	problemJson[nowPage-1].result = result;
	
	if($("#upPageBtn").hasClass("testPaper_btn_left_dis")){
		// alert("uuxxx");
		return;
	}
	nowPage--;
	if(nowPage<=1){
		$("#upPageBtn").removeClass("testPaper_btn_left").addClass("testPaper_btn_left_dis");
	}
	if(nowPage<countProblems){
		$("#nextPageBtn").removeClass("testPaper_btn_left_dis").addClass("testPaper_btn_left");
		$("#nextPageBtn").show();
		$("#handIn").hide();
	}else {
		$("#handIn").show();
			// if($("#resetBtn").css("display")!='block'){
			// 	$("#handIn").show();
			// }
			
		
	}

	htmlContent(nowPage);
}

function nextPage() {
	$("#checkAnswer").html("");

	//var crqtsProblemStr = problemJson[nowPage-1].crqtsProblem;
	var div_noSelects = $("body").find(".div_noSelect");
	var result = "";
	for(var i=0; i<div_noSelects.length; i++) {
		result += div_noSelects.eq(i).html();
		if(i!=div_noSelects.length-1){
			result += ",";
		}
	}

	problemJson[nowPage-1].result = result;

	if($("#nextPageBtn").hasClass("testPaper_btn_left_dis")){
		// alert("nnxxx");
		return;
	}
	nowPage++;
	if(nowPage>1){
		$("#upPageBtn").removeClass("testPaper_btn_left_dis").addClass("testPaper_btn_left");
	}
	if(nowPage>=countProblems){
		$("#nextPageBtn").removeClass("testPaper_btn_left").addClass("testPaper_btn_left_dis");
		$("#nextPageBtn").hide();
		
		if(restWork=="false"){
			$("#handIn").show();
		}

		// if($("#resetBtn").css("display")!='block'){
		// 		$("#handIn").show();
		// 	}
	}else{
		$("#handIn").hide();
	}
	htmlContent(nowPage);
}

function handIn() {
	//var crqtsProblemStr = problemJson[nowPage-1].crqtsProblem
	var div_noSelects = $("body").find(".div_noSelect");
	var result = "";
	for(var i=0; i<div_noSelects.length; i++) {
		// var re=new RegExp("#","i");
  //  		 crqtsProblemStr = crqtsProblemStr.replace(re, div_noSelects.eq(i).html());
  		result += div_noSelects.eq(i).html();
  		if(i!=div_noSelects.length-1){
			result += ",";
		}
	}

	problemJson[nowPage-1].result = result;
	

	papersAnswer();
	
}

/*判题**/
function papersAnswer(force) {
	clearInterval(timer);
	if(force){
		if(countTime<10){
			handInAlert(force);
			return;
		}
	}
	

	var problemJsonContentStr = JSON.stringify(problemJsonContent);
	problemJsonContentStr = encodeURIComponent(problemJsonContentStr);
	var data = "&crpsaPapersUuid="+dataJson.crpesUuid+"&crpsaPapersName="+dataJson.crpesName+"&crpsaUser="+dataGet("crusrUuid")+"&crpsaContent="+problemJsonContentStr
				+"&crpsaClass="+dataJson.crpesClass+"&crpsaClassName="+dataJson.crpesClassName+"&totalScore="+dataJson.crpesScore+"&crpsaTime="+$('#testPaper_time').html();


	var url = urlfile + "corePapersAnswer/add/corePapersAnswer";
	ajaxTool("post",data,url,
		function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
			alert("error:" + data);
		},
		function success(data){
			if(!data.success) {
				alert(data.errMsg);
			}else{
				resultStrJson = data.data;
				handInAlert(force);
			}
		}
	);
}



function checkAnswer() {
	if(judgeBoolean == "true"){
		$("#checkAnswer").html(problemJson[nowPage-1].crqtsAnswer);
	}
}

/*重做**/
function resetBtn() {
	dataSave("isTest","N");
	refreshPage();
}

/*重做**/
function reset() {
	dataSave("isTest","N");
	refreshPage();
}

function backPageTestPaper() {
	nextView("simulateIndex.html");
}

function handInAlertLayerCloseAll() {
	nextView("simulateIndex.html");
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