var nowPage = dataGet("pageNum");
var lastPageNumber=1;
var dataJson;

$(function() {
	$("#div_page").html(""+nowPage + "/" + lastPageNumber);
	$("#div_title").html(dataGet("gradeName"));

	initAoshuIndex();
})

	/**
	* 获取列表<Page>
	* 
	* @param crgceGrade 所属班级
	* @param crcreName 课程名
	* @param crcreClass 所属年级
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/

function initAoshuIndex() {
	// var data = "&crcreUser="+dataGet("crusrUuid") + "&crcreClass="+dataGet("uuid") + "&pageNum="+dataGet("pageNum") + "&pageSize="+6;
	// var url = urlfile + "coreCourse/find/by/cnd/before";
	var gradeUuid = dataGet("uuid");
	var gradeName = dataGet("gradeName");
	var crgceGrade = dataGet("crgaeUuid");

	
	var data = "&crcreUser="+dataGet("crusrUuid") + "&crgceGrade="+crgceGrade + "&crcreClass="+gradeUuid +"&pageNum="+dataGet("pageNum") + "&pageSize="+6;
	var url = urlfile + "coreGradeCourse/find/by/cnd";
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

				var aoshuIndexContent = "";
				for(var i=0; i<dataJson.result.length; i++) {
					var crwokScoreStr = '<span style="font-size: 18px;color: #CF1E1E;font-weight: bold;font-family: "楷体","楷体_GB2312";">'+(dataJson.result[i].crwokScore!=undefined?' '+dataJson.result[i].crwokScore+'分':"")+'</span>';

					aoshuIndexContent += 	'<div class="aoshuIndex_item">'+
												'<div class="aoshuIndex_flag">'+ (dataJson.result[i].isWork=="N"?"未学习":"已学习") +'</div>'+
												'<div class="aoshuIndex_item_title">'+ dataJson.result[i].crcreName +crwokScoreStr+'</div>'+
												'<div class="aoshuIndex_item_content div_center">'+ dataJson.result[i].crcreContent +'</div>'+
												'<div class="aoshuIndex_item_bottom">'+
													'<div class="aoshuIndex_jindian btn" onclick="toSutraTest(\''+dataJson.result[i].crecsUuid+'\','+i+')"></div>'+
													'<div class="aoshuIndex_qinghua_en btn" onclick="toQinghua(\''+dataJson.result[i].crwokUuid+'\','+i+')"></div>'+
												'</div>'+
											'</div>';
				}

				$("#aoshuIndex_content").html(aoshuIndexContent);
				$("#div_page").html(""+nowPage + "/" + lastPageNumber);
			}
		}
	);
}


function errListCorrectSelect(thisVar, errType) {
	radio(thisVar,'errList_nocorrect','errList_corrected');
	initErrList(errType)
}

function aoshuIndex_up_page() {
	if(nowPage<=1){
		return;
	}
	nowPage--;
	dataSave("pageNum",nowPage);
	initAoshuIndex();
}

function aoshuIndex_next_page() {
	if(nowPage>=lastPageNumber){
		return;
	}
	nowPage++;
	dataSave("pageNum",nowPage);
	initAoshuIndex();
}

function toSutraTest(jindianUuid, index) {
	if(jindianUuid==""|| jindianUuid=="undefined"){
		alert("还未开放，敬请期待");
		return;
	}
	// nextView("sutraTest.html?jindianUuid=" + jindianUuid);
	nextView("sutraTest2.html?jindianUuid=" + jindianUuid + "&timestamp=" + (new Date()).valueOf());
}

function toQinghua(qianghuaUuid, index) {
	if(dataJson.result[index].isWork=="N"){
		alert("还未通过经典联系测试，请先完成经典联系");
		return;
	}

	if(!dataJson.result[index].answerDate) {
		nextView("testQianghua.html?qianghuaUuid=" + qianghuaUuid + "&timestamp=" + (new Date()).valueOf());
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
				var timeC = timeCompare(now, dataJson.result[index].answerDate, 'h');
				var zjjgsj = data.data.crcdaValue;

				zjjgsj = parseInt(zjjgsj);
				if(zjjgsj==0){
					var zjjgsjItem = dataJson.result[index].crgceGqsj;

					zjjgsjItem = parseInt(zjjgsjItem);
					if(zjjgsjItem == 0){
						nextView("testQianghua.html?qianghuaUuid=" + qianghuaUuid + "&timestamp=" + (new Date()).valueOf());
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
				nextView("testQianghua.html?qianghuaUuid=" + qianghuaUuid + "&timestamp=" + (new Date()).valueOf());
			}
		}
	);

	
	
}

function backPageAoshuIndex() {
	nextView("lectureIndex.html");
}
