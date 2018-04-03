// JavaScript Document
$(function () {
	initModify();
	//提交
	$(".submit").on("click",function(){
		checkModify();
	});
});
//初始化
function initModify(){
	categoryColumns();
	classColumns();
	levelColumns();
	movieColumns();
	dirColumns();
	dirMovieColumns();
	getInfo(getQueryString("id"));
	$(".crqtsColor").bigColorpicker("crqtsColor");
}
function changQuesType(obj){
	var crkleQuesType=obj.value;
	//文字
	if(crkleQuesType == 0) {
		$(".wenzi1").show();
		$(".class1").hide();
	}
	//图片
	if(crkleQuesType == 1) {
		$(".wenzi1").hide();
		$(".class1").show();
	}
}
function changAnalysisType(obj){
	var crkleAnalysisType=obj.value;
	//文字
	if(crkleAnalysisType == 0) {
		$(".wenzi2").show();
		$(".class2").hide();
	}
	//图片
	if(crkleAnalysisType == 1) {
		$(".wenzi2").hide();
		$(".class2").show();
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
	}},true);
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
	}},true);
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
	}},true);
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
	}},true);
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
		$(".crqtsDirMovie").html(strhtml_goodColumns);
	}},true);
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
//获取详情
function getInfo(id){
	var str = 'crqtsUuid='+encodeURIComponent(id);
	getOData(str,"coreQuestions/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crqtsCode").val(oData.data.crqtsCode || "");
			$(".crqtsCategory").val(oData.data.crqtsCategory || "");
			$(".crqtsLevel").val(oData.data.crqtsLevel);
			$(".crqtsMovie").val(oData.data.crqtsMovie);
			$(".crqtsClass").val(oData.data.crqtsClass || "");
			var str = 'crkleCategory='+oData.data.crqtsCategory;
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
			}},true);
			$(".crqtsKnowledge").val(oData.data.crqtsKnowledge || "");
			$(".crqtsDir").val(oData.data.crqtsDir || "");
			$(".crqtsDirMovie").val(oData.data.crqtsDirMovie || "");
			if(oData.data.crqtsQuesType == 0) {
				$(".class1").hide();				
			}
			else {
				$(".wenzi1").hide();
				if(oData.data.crqtsQuesUrl){
					getImageWidthHeight(urlfile+"/coreAttachment/image/get/"+oData.data.crqtsQuesUrl+"?timestamp="+(new Date()).valueOf(),function(realWidth,realHeight){
						var width = 0;
						var height = 150;
						//如果真实的宽度大于浏览器的宽度就按照150显示
						if(realHeight>=height){
							width = realWidth/realHeight*height;
							$(".img1").css("width",width).css("height",height);
						}
						else{//如果小于浏览器的宽度按照原尺寸显示
							$(".img1").css("width",realWidth+'px').css("height",realHeight+'px');
						}
						$(".img1").attr("src",urlfile+"/coreAttachment/image/get/"+oData.data.crqtsQuesUrl+"?timestamp="+(new Date()).valueOf());
					});
				}
			}			
			if(oData.data.crqtsAnalysisType == 0) {
				$(".class2").hide();
			}
			else {
				$(".wenzi2").hide();
				if(oData.data.crqtsAnalysisUrl){
					getImageWidthHeight(urlfile+"/coreAttachment/image/get/"+oData.data.crqtsAnalysisUrl+"?timestamp="+(new Date()).valueOf(),function(realWidth,realHeight){
						var width = 0;
						var height = 150;
						//如果真实的宽度大于浏览器的宽度就按照150显示
						if(realHeight>=height){
							width = realWidth/realHeight*height;
							$(".img2").css("width",width).css("height",height);
						}
						else{//如果小于浏览器的宽度按照原尺寸显示
							$(".img2").css("width",realWidth+'px').css("height",realHeight+'px');
						}
						$(".img2").attr("src",urlfile+"/coreAttachment/image/get/"+oData.data.crqtsAnalysisUrl+"?timestamp="+(new Date()).valueOf());
					});
				}
			}
			$(".crqtsQuesType").val(oData.data.crqtsQuesType);
			if(oData.data.crqtsQuesFont) {
				$(".crqtsQuesFont").val(oData.data.crqtsQuesFont.replace(new RegExp(/(&nbsp;)/g),' ') || "");
			}
			$(".crqtsAnalysisType").val(oData.data.crqtsAnalysisType);
			if(oData.data.crqtsAnalysisFont) {
				$(".crqtsAnalysisFont").val(oData.data.crqtsAnalysisFont.replace(new RegExp(/(&nbsp;)/g),' ') || "");
			}
			$(".crqtsQuesUrl").val(oData.data.crqtsQuesUrl || "");
			$(".crqtsAnalysisUrl").val(oData.data.crqtsAnalysisUrl || "");
			if(oData.data.crqtsProblem) {
				$(".crqtsProblem").val(oData.data.crqtsProblem.replace(new RegExp(/(&nbsp;)/g),' ') || "");
			}
			$(".crqtsAnswer").val(oData.data.crqtsAnswer || "");
			$(".crqtsColor").val(oData.data.crqtsColor || "");
			$(".crqtsMovie").val(oData.data.crqtsMovie || "");
			$(".crqtsRemarks").val(oData.data.crqtsRemarks || "");
		} else {
			alert(data.errMsg);
		}
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

//检查提交
function checkModify(){
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

	var r=confirm("是否确认修改？");
	if (r==true){
		var msgObject = parent.layer.msg('处理中，请等待……', {
			icon: 16,
			shade: 0.4,
			time: waitImgTime //（如果不配置，默认是3秒）
		}, function(index){
			//do something
			parent.layer.close(index);
		});
		Modify(msgObject);
	}
}
//提交
function Modify(msgObject){
	var crqtsUuid = getQueryString("id");
	var crqtsCode = $(".crqtsCode").val();
	var crqtsCategory = $(".crqtsCategory").val();
	var crqtsLevel = $(".crqtsLevel").val();
	var crqtsClass = $(".crqtsClass").val();
	var crqtsKnowledge = $(".crqtsKnowledge").val();
	var crqtsDir = $(".crqtsDir").val();
	var crqtsDirMovie = $(".crqtsDirMovie").val(); 
	var crqtsQuesType = $(".crqtsQuesType").val();
	var crqtsQuesUrl = $(".crqtsQuesUrl").val();
	var crqtsQuesFont = $(".crqtsQuesFont").val();
	var crqtsAnalysisType = $(".crqtsAnalysisType").val();
	var crqtsAnalysisUrl = $(".crqtsAnalysisUrl").val();
	var crqtsAnalysisFont = $(".crqtsAnalysisFont").val();
	var crqtsProblem = $(".crqtsProblem").val();
	var crqtsAnswer = $(".crqtsAnswer").val();
	var crqtsColor = $(".crqtsColor").val();
	var crqtsMovie = $(".crqtsMovie").val();
	var crqtsRemarks = $(".crqtsRemarks").val();
	var str = 'crqtsUuid='+encodeURIComponent(crqtsUuid)+'&crqtsCode='+encodeURIComponent(crqtsCode)+'&crqtsCategory='+encodeURIComponent(crqtsCategory)+'&crqtsLevel='+encodeURIComponent(crqtsLevel)+'&crqtsClass='+encodeURIComponent(crqtsClass)+'&crqtsKnowledge='+encodeURIComponent(crqtsKnowledge)+'&crqtsDir='+encodeURIComponent(crqtsDir)+'&crqtsQuesType='+encodeURIComponent(crqtsQuesType)+'&crqtsQuesUrl='+encodeURIComponent(crqtsQuesUrl)+'&crqtsQuesFont='+encodeURIComponent(crqtsQuesFont)+'&crqtsAnalysisType='+encodeURIComponent(crqtsAnalysisType)+'&crqtsAnalysisUrl='+encodeURIComponent(crqtsAnalysisUrl)+'&crqtsAnalysisFont='+encodeURIComponent(crqtsAnalysisFont)+'&crqtsProblem='+encodeURIComponent(crqtsProblem)+'&crqtsAnswer='+encodeURIComponent(crqtsAnswer)+'&crqtsColor='+encodeURIComponent(crqtsColor)+'&crqtsMovie='+encodeURIComponent(crqtsMovie)+'&crqtsRemarks='+encodeURIComponent(crqtsRemarks)+'&crqtsDirMovie='+encodeURIComponent(crqtsDirMovie);
	getOData(str,"coreQuestions/update/coreQuestions",{
		fn:function(oData){
			window.parent.refreshList();
			alert("修改成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}