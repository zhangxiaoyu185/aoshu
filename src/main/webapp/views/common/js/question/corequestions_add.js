// JavaScript Document
$(function () {
	initAdd();
	//提交
	$(".submit").on("click",function(){
		checkAdd();
	});
});
//初始化
function initAdd(){
	$(".crqtsCode").val("");
	$(".crqtsCategory").val("");
	$(".crqtsLevel").val("");
	$(".crqtsClass").val("");
	$(".crqtsKnowledge").val("");
	$(".crqtsQuesUrl").val("");
	$(".crqtsQuesFont").val("");
	$(".crqtsAnalysisUrl").val("");
	$(".crqtsAnalysisFont").val("");
	$(".crqtsProblem").val("");
	$(".crqtsAnswer").val("");
	$(".crqtsColor").val("");
	$(".crqtsMovie").val("");
	$(".crqtsRemarks").val("");
	$(".crqtsColor").bigColorpicker("crqtsColor");
	categoryColumns();
	classColumns();
	knowledgeColumns();
	levelColumns();
	movieColumns();
	dirColumns();
	dirMovieColumns();
	$(".wenzi1").hide();
	$(".wenzi2").hide();
}
function changQuesType(obj){
	var crkleQuesType=obj.value;
	//文字
	if(crkleQuesType == 0) {
		$(".wenzi1").show();
	}
	//图片
	if(crkleQuesType == 1) {
		$(".wenzi1").hide();
	}
}
function changAnalysisType(obj){
	var crkleAnalysisType=obj.value;
	//文字
	if(crkleAnalysisType == 0) {
		$(".wenzi2").show();
	}
	//图片
	if(crkleAnalysisType == 1) {
		$(".wenzi2").hide();
	}	
}
//加载类别下拉框内容
function categoryColumns(){
	var str = '';
	getOData(str,"coreCategory/find/all",{fn:function(oData){
		var strhtml_goodColumns = '';
		var arrData = oData.data;
		var ln = arrData.length;
		for(var i=0;i<ln;i++){
			strhtml_goodColumns += '<option value ="'+arrData[i].crceyUuid+'" >'+arrData[i].crceyName+'</option>';
		}
		$(".crqtsCategory").html(strhtml_goodColumns);
	}});
}
//类型改变获取对应的是知识点
function changCategory(obj){
	var crkleCategory=obj.value;
	var str = 'crkleCategory='+crkleCategory;
	getOData(str,"coreKnowledge/find/all/bys",{fn:function(oData){
		var strhtml_goodColumns = '';
		var arrData = oData.data;
		var ln = arrData.length;
		for(var i=0;i<ln;i++){
			var crkleFatherNameCH='#';
			if(undefined!=arrData[i].crkleFatherName){
				crkleFatherNameCH=arrData[i].crkleFatherName;
			}
			strhtml_goodColumns += '<option value ="'+arrData[i].crkleUuid+'" >'+crkleFatherNameCH+'|'+arrData[i].crkleName+'</option>';
		}
		$(".crqtsKnowledge").html(strhtml_goodColumns);
	}});
}
//加载知识点下拉框内容
function knowledgeColumns(){
	var str = '';
	getOData(str,"coreKnowledge/find/all",{fn:function(oData){
		var strhtml_goodColumns = '';
		var arrData = oData.data;
		var ln = arrData.length;
		for(var i=0;i<ln;i++){
			var crkleFatherNameCH='#';
			if(undefined!=arrData[i].crkleFatherName){
				crkleFatherNameCH=arrData[i].crkleFatherName;
			}
			strhtml_goodColumns += '<option value ="'+arrData[i].crkleUuid+'" >'+crkleFatherNameCH+'|'+arrData[i].crkleName+'</option>';
		}
		$(".crqtsKnowledge").html(strhtml_goodColumns);
	}});
}
//加载年级下拉框内容
function classColumns(){
	var str = '';
	getOData(str,"coreClass/find/all",{fn:function(oData){
		var strhtml_goodColumns = '';
		var arrData = oData.data;
		var ln = arrData.length;
		for(var i=0;i<ln;i++){
			strhtml_goodColumns += '<option value ="'+arrData[i].crcasUuid+'" >'+arrData[i].crcasName+'</option>';
		}
		$(".crqtsClass").html(strhtml_goodColumns);
	}});
}
//加载图片目录下拉框内容
function dirColumns(){
	var str = '';
	getOData(str,"coreDir/find/all",{fn:function(oData){
		var strhtml_goodColumns = '';
		var arrData = oData.data;
		var ln = arrData.length;
		for(var i=0;i<ln;i++){
			strhtml_goodColumns += '<option value ="'+arrData[i].crdirName+'" >'+arrData[i].crdirName+'</option>';
		}
		$(".crqtsDir").html(strhtml_goodColumns);
	}});
}
//加载视频目录下拉框内容
function dirMovieColumns(){
	var str = '';
	getOData(str,"coreDirMovie/find/all",{fn:function(oData){
		var strhtml_goodColumns = '';
		var arrData = oData.data;
		var ln = arrData.length;
		for(var i=0;i<ln;i++){
			strhtml_goodColumns += '<option value ="'+arrData[i].crdirName+'" >'+arrData[i].crdirName+'</option>';
		}
		$(".crqtsDirMovies").html(strhtml_goodColumns);
	}});
}
//加载难易程度下拉框内容
function levelColumns(){
	var strhtml_goodColumns = '';
	strhtml_goodColumns += '<option value ="1" selected="selected">1</option>';
	strhtml_goodColumns += '<option value ="2">2</option>';
	strhtml_goodColumns += '<option value ="3">3</option>';
	strhtml_goodColumns += '<option value ="4">4</option>';
	strhtml_goodColumns += '<option value ="5">5</option>';
	strhtml_goodColumns += '<option value ="6">6</option>';
	strhtml_goodColumns += '<option value ="0">无</option>';
	$(".crqtsLevel").html(strhtml_goodColumns);
}
//加载视频路径下拉框内容
function movieColumns(){
	var strhtml_goodColumns = '';
	strhtml_goodColumns += '<option value ="MP4" selected="selected">MP4</option>';
	$(".crqtsMovie").html(strhtml_goodColumns);
}
//检查提交
function checkAdd(){
	if($.trim($(".crqtsCode").val()) == ""){
		alert("题目编号不能为空，请填写完再提交！");
		$(".crqtsCode").focus();
		return false;
	}
	if($.trim($(".crqtsCategory").val()) == ""){
		alert("类别不能为空，请填写完再提交！");
		$(".crqtsCategory").focus();
		return false;
	}
	if($.trim($(".crqtsLevel").val()) == ""){
		alert("难易程度不能为空，请填写完再提交！");
		$(".crqtsLevel").focus();
		return false;
	}
	if($.trim($(".crqtsClass").val()) == ""){
		alert("年级不能为空，请填写完再提交！");
		$(".crqtsClass").focus();
		return false;
	}
	if($.trim($(".crqtsKnowledge").val()) == ""){
		alert("知识点不能为空，请填写完再提交！");
		$(".crqtsKnowledge").focus();
		return false;
	}
	if($.trim($(".crqtsQuesType").val()) == ""){
		alert("题目类型不能为空，请填写完再提交！");
		$(".crqtsQuesType").focus();
		return false;
	}
	if($.trim($(".crqtsAnalysisType").val()) == ""){
		alert("解析类型不能为空，请填写完再提交！");
		$(".crqtsAnalysisType").focus();
		return false;
	}

	var r=confirm("是否确认增加？");
	if (r==true){
		var msgObject = parent.layer.msg('处理中，请等待……', {
			icon: 16,
			shade: 0.4,
			time: waitImgTime //（如果不配置，默认是3秒）
		}, function(index){
			//do something
			parent.layer.close(index);
		});
		Add(msgObject);
	}
}

//提交
function Add(msgObject){
	var crqtsCode = $(".crqtsCode").val();
	var crqtsCategory = $(".crqtsCategory").val();
	var crqtsLevel = $(".crqtsLevel").val();
	var crqtsClass = $(".crqtsClass").val();
	var crqtsKnowledge = $(".crqtsKnowledge").val();
	var crqtsDir = $(".crqtsDir").val();
	var crqtsDirMovie = $(".crqtsDirMovie").val(); 
	var crqtsQuesType = $(".crqtsQuesType").val();
	var crqtsQuesFont = $(".crqtsQuesFont").val();
	var crqtsAnalysisType = $(".crqtsAnalysisType").val();
	var crqtsAnalysisFont = $(".crqtsAnalysisFont").val();
	var crqtsProblem = $(".crqtsProblem").val();
	var crqtsAnswer = $(".crqtsAnswer").val();
	var crqtsColor = $(".crqtsColor").val();
	var crqtsMovie = $(".crqtsMovie").val();
	var crqtsRemarks = $(".crqtsRemarks").val();
	var str = 'crqtsCode='+encodeURIComponent(crqtsCode)+'&crqtsCategory='+encodeURIComponent(crqtsCategory)+'&crqtsLevel='+encodeURIComponent(crqtsLevel)+'&crqtsClass='+encodeURIComponent(crqtsClass)+'&crqtsKnowledge='+encodeURIComponent(crqtsKnowledge)+'&crqtsDir='+encodeURIComponent(crqtsDir)+'&crqtsQuesType='+encodeURIComponent(crqtsQuesType)+'&crqtsQuesFont='+encodeURIComponent(crqtsQuesFont)+'&crqtsAnalysisType='+encodeURIComponent(crqtsAnalysisType)+'&crqtsAnalysisFont='+encodeURIComponent(crqtsAnalysisFont)+'&crqtsProblem='+encodeURIComponent(crqtsProblem)+'&crqtsAnswer='+encodeURIComponent(crqtsAnswer)+'&crqtsColor='+encodeURIComponent(crqtsColor)+'&crqtsMovie='+encodeURIComponent(crqtsMovie)+'&crqtsRemarks='+encodeURIComponent(crqtsRemarks)+'&crqtsDirMovie='+encodeURIComponent(crqtsDirMovie);
	getOData(str,"coreQuestions/add/coreQuestions",{
		fn:function(oData){
			window.parent.refreshList();
			alert("增加成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}