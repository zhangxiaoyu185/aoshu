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
	var str = 'crwokUuid='+encodeURIComponent(id);
	getOData(str,"coreWork/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crwokCourse").text(oData.data.crwokCourseName || "");
			$(".crwokClass").text(oData.data.crwokClassName || "");
			$(".crwokCdate").text(oData.data.crwokCdate || "");
			$(".crwokUdate").text(oData.data.crwokUdate || "");
			var crwokContent = oData.data.crwokContent;
			if(''!=crwokContent && undefined!=crwokContent){
				var parsedJson = jQuery.parseJSON(crwokContent);
				var problems = parsedJson.problem;
				var strhtml_list = "";
				for ( var i in problems) {
					strhtml_list += '<tr class="trHighLight">'
						+'<td>'+(problems[i].crqtsCode || "")+'</td>'
						+'<td>'+(problems[i].crceyName|| "")+'</td>'
						+'<td>'+(problems[i].crcasName || "")+'</td>'
						+'<td>'+(problems[i].crkleName || "")+'</td>'
						+'<td>'+(problems[i].crqtsProblem || "")+'</td>'
						+'<td>'+(problems[i].crqtsAnswer || "")+'</td>'
						+'</tr>';
				}
				$(".tb-body").html(strhtml_list);
			}
		} else {
			alert(data.errMsg);
		}
	}});
}
