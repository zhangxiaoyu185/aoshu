var nowPage = dataGet("pageNum");
var lastPageNumber;
var resultJson;

$(function() {
	loadData(1);
})

	/**
	* 获取列表<Page>
	* 
	* @param crgpsGrade 所属班级

	* @param crpesClass 所属年级
	* @param crpesName 试卷名称（后端）
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/

function loadData(nowPage) {
	var gradeUuid = dataGet("uuid");
	var gradeName = dataGet("gradeName");
	var crgpsGrade = dataGet("crgaeUuid");

	// var data = "&userUuid="+dataGet("crusrUuid")+"&crpesClass="+gradeUuid+"&pageNum="+dataGet("pageNum")+"&pageSize="+8;
	// var url = urlfile + "corePapers/find/by/cnd";
	var data = "&userUuid="+dataGet("crusrUuid")+"&crgpsGrade="+crgpsGrade+"&crpesClass="+gradeUuid+"&pageNum="+dataGet("pageNum")+"&pageSize="+8;
	var url = urlfile + "coreGradePapers/find/by/cnd";
	ajaxTool("post",data,url,
		function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
			alert("error:" + data);
		},
		function success(data){
			if(!data.success) {
				alert(data.errMsg);
			}else{
				lastPageNumber = data.data.lastPageNumber;

				if(nowPage>=lastPageNumber){
					$("#nextPageBtn").removeClass("simulate_page_en").addClass("simulate_page_dis");
				}

				var contentSelectStr = "";
				resultJson = data.data.result;
				for(var i=0; i<resultJson.length; i++){
					var isTestClass = (resultJson[i].isTest=="N"?"simulate_checking":"simulate_checked") + " btn";
					if(resultJson[i].isTest=="Y") {
						
						contentSelectStr += '<div class="'+ isTestClass +'" onclick="toTestPaper(\''+i+'\')">'+resultJson[i].crpesName+
												'<div class="gainScore"><span class="span_black">得分：</span><span>'+resultJson[i].score+'</span></div>'+
												'<div class="gainTime"><span class="span_black">时间：</span><span>'+resultJson[i].time+'</span></div>'+
											'</div>';
					}else {
						contentSelectStr += '<div class="'+ isTestClass +'" onclick="toTestPaper(\''+i+'\')">'+resultJson[i].crpesName+'</div>';
					}

					
				}

				$('#content_select').html(contentSelectStr);
			}
		}
	);
}

// function toTestPaper(index) {
// 	nextView("testPaper.html?uuid="+resultJson[index].crpesUuid +"&timestamp=" + (new Date()).valueOf());
// }

function upPage() {
	if($("#upPageBtn").hasClass("simulate_page_dis")){
		// alert("uuxxx");
		return;
	}
	nowPage--;
	if(nowPage<=1){
		$("#upPageBtn").removeClass("simulate_page_en").addClass("simulate_page_dis");
	}
	if(nowPage<lastPageNumber){
		$("#nextPageBtn").removeClass("simulate_page_dis").addClass("simulate_page_en");
	}
	dataSave("pageNum",nowPage);
	loadData(nowPage);
}

function nextPage() {
	if($("#nextPageBtn").hasClass("simulate_page_dis")){
		// alert("nnxxx");
		return;
	}
	nowPage++;
	if(nowPage>1){
		$("#upPageBtn").removeClass("simulate_page_dis").addClass("simulate_page_en");
	}
	if(nowPage>=lastPageNumber){
		$("#nextPageBtn").removeClass("simulate_page_en").addClass("simulate_page_dis");
	}
	dataSave("pageNum",nowPage);
	loadData(nowPage);
}

function toTestPaper(index) {
	dataSave("crpesName",resultJson[index].crpesName);
	dataSave("time",resultJson[index].time);
	dataSave("isTest",resultJson[index].isTest);
	dataSave("answerUuid",resultJson[index].answerUuid);
	dataSave("score",resultJson[index].score);

	if(!resultJson[index].answerDate) {
		nextView("testPaper.html?uuid="+resultJson[index].crpesUuid +"&timestamp=" + (new Date()).valueOf());
		return;
	}
	
	var data = "&crcdaUuid=zjjgsj";
	var url = urlfile + "coreCacheData/views";
	ajaxTool("post",data,url,
		function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
			alert("error:" + data);
		},
		function success(data){
			if(!data.success) {
				alert(data.errMsg);
			}else{
				// alert(resultJson[index].answerDate)
				var now = (new Date()).Format("yyyy-MM-dd hh:mm:ss");
				var timeC = timeCompare(now, resultJson[index].answerDate, 'h');
				var zjjgsj = data.data.crcdaValue;
				zjjgsj = parseInt(zjjgsj);
				if(zjjgsj==0){
					var zjjgsjItem = resultJson[index].crgpsGqsj;

					zjjgsjItem = parseInt(zjjgsjItem);
					if(zjjgsjItem == 0){
						nextView("testPaper.html?uuid="+resultJson[index].crpesUuid +"&timestamp=" + (new Date()).valueOf());
						return;
					}

					if(timeC<zjjgsjItem) {
						alert("间隔"+zjjgsjItem+"小时之内不能重做");
						return;
					}
				}else{
					if(timeC<zjjgsj) {
						alert("间隔"+zjjgsj+"小时之内不能重做");
						return;
					}
				}
				
				nextView("testPaper.html?uuid="+resultJson[index].crpesUuid +"&timestamp=" + (new Date()).valueOf());
			}
		}
	);

	// nextView("testPaper.html?uuid="+resultJson[index].crpesUuid);
	// nextView("testPaper.html?uuid="+resultJson[index].crpesUuid +"&timestamp=" + (new Date()).valueOf());
}

function backPageSimulateIndex() {
	nextView("lectureIndex.html");
}