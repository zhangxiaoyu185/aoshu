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
	var str = 'cresaUuid='+encodeURIComponent(id);
	getOData(str,"coreExercisesAnswer/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".cresaCdate").text(oData.data.cresaCdate || "");
			$(".cresaUdate").text(oData.data.cresaUdate || "");
			var cresaStateCh='需订正';
			var cresaState=oData.data.cresaState;
			if(0==cresaState){
				cresaStateCh='已完结';
			}
			$(".cresaCourseName").text(oData.data.cresaCourseName || "");
			$(".cresaUserCode").text(oData.data.cresaUserCode || "");
			$(".cresaClass").text(oData.data.cresaClassName || "");
			$(".cresaState").text(cresaStateCh);
			var cresaContent = oData.data.cresaContent;
			if(''!=cresaContent && undefined!=cresaContent){
				var parsedJson = jQuery.parseJSON(cresaContent);
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
