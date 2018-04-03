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
	var str = 'crpesUuid='+encodeURIComponent(id);
	getOData(str,"corePapers/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crpesName").text(oData.data.crpesName || "");
			$(".crpesScore").text(oData.data.crpesScore || 0);
			$(".crpesClass").text(oData.data.crpesClassName || '');
			$(".crpesCdate").text(oData.data.crpesCdate ||'');
			$(".crpesUdate").text(oData.data.crpesUdate ||'');
			var crpesContent = oData.data.crpesContent;
			if(''!=crpesContent && undefined!=crpesContent){
				var parsedJson = jQuery.parseJSON(crpesContent);
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
