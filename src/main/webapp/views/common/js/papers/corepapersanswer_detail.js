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
	var str = 'crpsaUuid='+encodeURIComponent(id);
	getOData(str,"corePapersAnswer/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crpsaCdate").text(oData.data.crpsaCdate || "");
			$(".crpsaUdate").text(oData.data.crpsaUdate || "");
			var crpsaStateCh='需订正';
			var crpsaState=oData.data.crpsaState;
			if(0==crpsaState){
				crpsaStateCh='已完结';
			}
			$(".crpsaPapersName").text(oData.data.crpsaPapersName || "");
			$(".crpsaUserCode").text(oData.data.crpsaUserCode || "");			
			$(".crpsaScore").text(oData.data.crpsaScore || 0);
			$(".crpsaState").text(crpsaStateCh);
			var crpsaContent = oData.data.crpsaContent;//crpsaContent
			if(''!=crpsaContent && undefined!=crpsaContent){
				var parsedJson = jQuery.parseJSON(crpsaContent);
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
