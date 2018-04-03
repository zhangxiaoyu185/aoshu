$(function() {
	var gradeUuid = dataGet("uuid");
	var gradeName = dataGet("gradeName");

	$("#lecture_grade").html("("+ gradeName +")");
	dataSave("pageNum",1);
	dataSave("errTypeVar",1);

	// var data = "&userUuid="+dataGet("crusrUuid")+"&crpesClass="+gradeUuid+"&pageNum="+1+"&pageSize="+8;
	// var url = urlfile + "corePapers/find/by/cnd";
	// ajaxTool("post",data,url,
	// 	function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
	// 		alert("error:" + data);
	// 	},
	// 	function success(data){
	// 		if(!data.success) {
	// 			alert(data.errMsg);
	// 		}else{
				
	// 		}
	// 	}
	// );
})

// String , String , String crpesName, Integer , Integer 

function backPageLectureIndex() {
	nextView("../home/homeIndex.html");
}