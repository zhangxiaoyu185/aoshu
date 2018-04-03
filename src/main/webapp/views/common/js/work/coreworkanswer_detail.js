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
	var str = 'crwkaUuid='+encodeURIComponent(id);
	getOData(str,"coreWorkAnswer/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crwkaCdate").text(oData.data.crwkaCdate || "");
			$(".crwkaUdate").text(oData.data.crwkaUdate || "");
			var crwkaStateCh='需订正';
			var crwkaState=oData.data.crwkaState;
			if(0==crwkaState){
				crwkaStateCh='已完结';
			}
			$(".crwkaCourseName").text(oData.data.crwkaCourseName || "");
			$(".crwkaUserCode").text(oData.data.crwkaUserCode || "");
			$(".crwkaClass").text(oData.data.crwkaClassName || "");
			$(".crwkaScore").text(oData.data.crwkaScore || 0);
			$(".crwkaTime").text(oData.data.crwkaTime || "");
			$(".crwkaState").text(crwkaStateCh);
			var crwkaContent = oData.data.crwkaContent;
			if(''!=crwkaContent && undefined!=crwkaContent){
				var parsedJson = jQuery.parseJSON(crwkaContent);
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
						+'<td>'+(problems[i].result || "")+'</td>'
						+'</tr>';
				}
				$(".tb-body").html(strhtml_list);
			}
		} else {
			alert(data.errMsg);
		}
	}});
}
