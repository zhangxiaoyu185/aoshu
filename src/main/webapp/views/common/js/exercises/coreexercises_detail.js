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
	var str = 'crecsUuid='+encodeURIComponent(id);
	getOData(str,"coreExercises/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crecsCourseName").text(oData.data.crecsCourseName || "");
			$(".crecsClass").text(oData.data.crecsClassName || "");
			$(".crecsCdate").text(oData.data.crecsCdate || "");
			$(".crecsUdate").text(oData.data.crecsUdate || "");
			var crecsContent = oData.data.crecsContent;
			if(''!=crecsContent && undefined!=crecsContent){
				var parsedJson = jQuery.parseJSON(crecsContent);
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
