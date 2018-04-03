var problemJsonArray = [];
var pageNum_search = 1;
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
	getClass();
	getInfo(getQueryString("id"));
	//是否显示查询条件
	showSearchBox(true);

	getDataCode();
	getDataNum();
	
	var obj = {};//查询条件对象
	searchContent(obj);
	showList(obj,pageNum_search);

	//上移
	$("body").delegate(".up","click",function(){
		var index = $(this).attr("data-index");
		if(index<1){
			alert("试题为第一题，无法上移");
			return;
		}
		var temp = problemJsonArray[parseInt(index-1)];
		problemJsonArray[parseInt(index-1)] = problemJsonArray[index];
		problemJsonArray[index] = temp;
		listProblem(problemJsonArray);
	});

	//下移
	$("body").delegate(".next","click",function(){
		var index = $(this).attr("data-index");
		index =parseInt(index);
		if(index>=problemJsonArray.length-1){
			alert("试题为最后一题，无法下移");
			return;
		}
		var temp = problemJsonArray[index+1];
		problemJsonArray[index+1] = problemJsonArray[index];
		problemJsonArray[index] = temp;
		listProblem(problemJsonArray);
	});
	
	//取消
	$("body").delegate(".delit","click",function(){
		var r=confirm("是否确认取消？");
		if (r==true){
			var questionCode = encodeURIComponent($(this).attr("data-id"));
			for ( var i in problemJsonArray) {
				if(problemJsonArray[i].crqtsCode == questionCode) {
					problemJsonArray.splice(i,i+1);
				}
			}
			alert("取消成功！");
			listProblem(problemJsonArray);
		}
	});
	//全选
	$("body").delegate("input[name='checkboxAll']","click",function(){
		if($(this).prop("checked")){
			$("input[name='checkbox']").each(function(){
				$(this).prop("checked",true);
			});
		}else{
			$("input[name='checkbox']").each(function(){
				$(this).removeAttr("checked");
			});
		}
	});
	//批量新增
	$("body").delegate(".addit","click",function(){
//		var r=confirm("是否确认添加所选的记录？");
//		if (r==true){
			$("input[name='checkbox']").each(function(){
				if($(this).prop("checked")){
					var flag = true;			
					var str = 'crqtsUuid='+encodeURIComponent($(this).attr("data-id"));
					getOData(str,"coreQuestions/views",{fn:function(oData){
						if(oData.code == 1) {
							var problrmObj = oData.data;
	//						if(!problrmObj.crqtsProblem) {
	//							flag = false;
	//						}
	//						if(!problrmObj.crqtsAnswer) {
	//							flag = false;
	//						}
							if(flag) {
								for ( var i in problemJsonArray) {
									if(problemJsonArray[i].crqtsCode == problrmObj.crqtsCode) {
										flag = false;
										break;
									}
								}
							}
							if(flag) {
								problemJsonArray.splice(problemJsonArray.length, 0, problrmObj);
							}
						} else {
						}					
					}},true);
				}
			});
			alert("添加成功！");
			listProblem(problemJsonArray);
			
			var obj = {};//查询条件对象
			searchContent(obj);
			showList(obj,pageNum_search);
//		}
	});
	//添加
	$("body").delegate('.modifyit','click', function(){
		var str = 'crqtsUuid='+encodeURIComponent($(this).attr("data-id"));
		getOData(str,"coreQuestions/views",{fn:function(oData){
			if(oData.code == 1) {
				var problrmObj = oData.data;
//				if(!problrmObj.crqtsProblem) {
//					alert("该题目问题不存在，请先编辑再添加！");
//					return;
//				}
//				if(!problrmObj.crqtsAnswer) {
//					alert("该题目答案不存在，请先编辑再添加！");
//					return;
//				}
				for ( var i in problemJsonArray) {
					if(problemJsonArray[i].crqtsCode == problrmObj.crqtsCode) {
						alert("试卷中已存在该题目！");
						return;
					}
				}
				problemJsonArray.splice(problemJsonArray.length, 0, problrmObj);
				alert("添加成功！");
				listProblem(problemJsonArray);
			} else {
				alert(data.errMsg);
			}
		}});
	});
	
	//tr高亮显示
	$("body").delegate(".trHighLight","mouseleave",function(){
		$(this).find("td").css("background-color","#fff");
	});
	//tr高亮显示并显示图
	$("body").delegate(".trHighLight","mouseenter",function(){
		$(this).find("td").css("background-color","#c1ebff");
	});
	//查询
	$("body").delegate(".searchBtn","click",function(){
		var obj = {};//查询条件对象
		searchContent(obj);
		showList(obj,1);

		ModifyData('code_work',  $(".crqtsCode").val());
		ModifyData('class_work',  $(".crqtsClass").val());
		pageNum_search = 1;
		ModifyData('num_work',  pageNum_search);
	});
	//重置
	$("body").delegate(".resetBtn","click",function(){
		var obj = {};//查询条件对象
		$(".crqtsCode").val('');
		$(".crqtsClass").val(0);
		searchContent(obj);
		showList(obj,1);		

		ModifyData('code_work',  $(".crqtsCode").val());
		ModifyData('class_work',  $(".crqtsClass").val());
		pageNum_search = 1;
		ModifyData('num_work',  pageNum_search);
	});
	//上一页
	$('.manageBox').delegate(".backpage","click", function() {
		var obj = {};//查询条件对象
		searchContent(obj);
		pagenum = parseInt($(this).attr("data-pagenum"));
		showList(obj,pagenum);
		
		pageNum_search = pagenum;
		ModifyData('num_work',  pageNum_search);
	});
	//下一页
	$('.manageBox').delegate(".nextpage","click", function() {
		var obj = {};//查询条件对象
		searchContent(obj);
		var pagenum = parseInt($(this).attr("data-pagenum"));
		showList(obj,pagenum);
		
		pageNum_search = pagenum;
		ModifyData('num_work',  pageNum_search);
	});
	//首页
	$('.manageBox').delegate(".firstpage","click", function() {
		var obj = {};//查询条件对象
		searchContent(obj);
		var pagenum = parseInt($(this).attr("data-pagenum"));
		showList(obj,pagenum);
		
		pageNum_search = pagenum;
		ModifyData('num_work',  pageNum_search);
	});
	//末页
	$('.manageBox').delegate(".lastpage","click", function() {
		var obj = {};//查询条件对象
		searchContent(obj);
		var pagenum = parseInt($(this).attr("data-pagenum"));
		showList(obj,pagenum);
		
		pageNum_search = pagenum;
		ModifyData('num_work',  pageNum_search);
	});
	//跳转至
	$('.manageBox').delegate(".jumppage","click", function() {
		var obj = {};//查询条件对象
		searchContent(obj);
		var pagenum = parseInt($('.jumppagetext').val());
		if(pagenum>0 && pagenum <=parseInt($(this).attr("data-pagemax"))){
			showList(obj,pagenum);
		}else{
			alert("查无此页！");
		}
		
		pageNum_search = pagenum;
		ModifyData('num_work',  pageNum_search);
	});
	
}
//加载列表下拉框内容
function getClass(){
	var str = '';
	getOData(str,"coreClass/find/all/nothing",{fn:function(oData){
		if(oData.code == 1) {
			var strhtml_select = '<option value ="0" selected = "selected">全部</option>';
			var arrData = oData.data;
			var ln = arrData.length;
			for(var i=0;i<ln;i++){
				strhtml_select += '<option value="'+arrData[i].crcasUuid+'">'+arrData[i].crcasName+'</option>';
			}
			$('.crqtsClass').html(strhtml_select);
		} else {
			alert(data.errMsg);
		}
	}},true);
	getDataClass();	
}

function showList(obj,pagenum){
	var aData =[{name:"<input type='checkbox' name='checkboxAll' value='checkbox' />",percent:"5"},
	            {name:"题目编号",percent:"12"},
				{name:"类别",percent:"10"},
				{name:"年级",percent:"8"},
				{name:"知识点",percent:"10"},				
				{name:"问题",percent:"25"},
				{name:"答案",percent:"15"},				
				{name:"操作",percent:"15"}];
	setTableHead(aData);
	var str = '';
	if(obj.crqtsCode==""){
		str = 'pageNum='+pagenum+'&pageSize=15';
	}else{
		str = 'pageNum='+pagenum+'&pageSize=15&crqtsCode='+encodeURIComponent(obj.crqtsCode);
	}
	if(obj.crqtsClass!='0'){
		str += '&crqtsClass='+encodeURIComponent(obj.crqtsClass);
	}
	getOData(str,"coreQuestions/find/by/cnd",{fn:function(oData){
		var strhtml_list = "";
		var arrData = oData.data.result;
		var ln = arrData.length;
		for(var i=0;i<ln;i++){
			strhtml_list += '<tr class="trHighLight">'
				+'<td>'+'<input type="checkbox" name="checkbox" value="checkbox" data-id="'+arrData[i].crqtsUuid+'"/>'+'</td>'
				+'<td>'+(arrData[i].crqtsCode || "")+'</td>'
				+'<td>'+(arrData[i].crceyName || "")+'</td>'
				+'<td>'+(arrData[i].crcasName || "")+'</td>'
				+'<td>'+(arrData[i].crkleName || "")+'</td>'				
				+'<td>'+(arrData[i].crqtsProblem || "")+'</td>'
				+'<td>'+(arrData[i].crqtsAnswer || "")+'</td>'
				+'<td>'
				+'<a  class="p-edit modifyit" data-id="'+arrData[i].crqtsUuid+'">添加</a>'
				+'</td>'
				+'</tr>';
		}
		$(".tb-body").html(strhtml_list);
		setTableFoot(oData.data,aData.length);
		}}
	);
}
function isNull(obj,pagenum){
	var str = '';
	if(obj.crqtsCode==""){
		str = 'pageNum='+pagenum+'&pageSize=15';
	}else{
		str = 'pageNum='+pagenum+'&pageSize=15&crqtsCode='+encodeURIComponent(obj.crqtsCode);
	}
	if(obj.crqtsClass!='0'){
		str += '&crqtsClass='+encodeURIComponent(obj.crqtsClass);
	}
	getOData(str,"coreQuestions/find/by/cnd",{fn:function(oData){
		var arrData = oData.data.result;
		var ln = arrData.length;
		if(ln == 0){
			if (oData.data.totalCount != 0 && pagenum !=1){
				showList(obj,pagenum-1);
			}else{
				showList(obj,1);
			}
		}else{
			showList(obj,pagenum);
		}
		}}
	);
}

function refreshList(){
	var obj = {};//查询条件对象
	searchContent(obj);
	showList(obj,parseInt($(".curpage").text()));
	layer.closeAll();
}

function searchContent(obj){
	obj.crqtsCode = $(".crqtsCode").val();
	obj.crqtsClass = $(".crqtsClass").val();
}

//获取详情
function getInfo(id){
	var str = 'crwokUuid='+encodeURIComponent(id);
	getOData(str,"coreWork/views",{fn:function(oData){
		if(oData.code == 1) {
			var crwokContent = oData.data.crwokContent;
			if(''!=crwokContent && undefined!=crwokContent){
				var parsedJson = jQuery.parseJSON(crwokContent);
				problemJsonArray = parsedJson.problem;
				listProblem(problemJsonArray);
			}
		} else {
			alert(data.errMsg);
		}
	}});
}

//拼装已添加
function listProblem(problems){
	var strhtml_list = "";
	for ( var i in problems) {
		strhtml_list += '<tr class="trHighLight">'
			+'<td>'+(problems[i].crqtsCode || "")+'</td>'
			+'<td>'+(problems[i].crceyName|| "")+'</td>'
			+'<td>'+(problems[i].crkleName || "")+'</td>';
		if(problems[i].crqtsLevel == 0) {
			strhtml_list += '<td>无</td>'; 	
		}
		else {
			strhtml_list += '<td>'+problems[i].crqtsLevel+'</td>';	
		}
		
		strhtml_list += '<td>'+(problems[i].crcasName || "")+'</td>'
			+'<td>'
			+'<a  class="p-edit delit" data-id="'+problems[i].crqtsCode+'">取消</a>'
			+'<a  class="p-edit up" data-index="'+i+'" data-id="'+problems[i].crqtsCode+'">上移</a>'
			+'<a  class="p-edit next" data-index="'+i+'" data-id="'+problems[i].crqtsCode+'">下移</a>'
			+'</td>'
			+'</tr>';
	}
	$(".tb-Ybody").html(strhtml_list);
}
//检查提交
function checkModify(){
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
	var crwokUuid = getQueryString("id");
	var content = {};
	content.problem = problemJsonArray;
	crwokContent = JSON.stringify(content);
	var str = 'crwokUuid='+encodeURIComponent(crwokUuid)+'&crwokContent='+encodeURIComponent(crwokContent);
	getOData(str,"coreWork/update/coreWork/by/ques",{
		fn:function(oData){
			window.parent.refreshList();
			alert("修改成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}

//获取缓存详情
function getDataCode(){
	var str = 'crcdaUuid=code_work';
	getOData(str,"coreCacheData/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crqtsCode").val(oData.data.crcdaValue);
		}
	}},true);
}

//获取缓存详情
function getDataClass(){
	var str = 'crcdaUuid=class_work';
	getOData(str,"coreCacheData/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crqtsClass").val(oData.data.crcdaValue);
		}
	}},true);
}

//获取缓存详情
function getDataNum(){
	var str = 'crcdaUuid=num_work';
	getOData(str,"coreCacheData/views",{fn:function(oData){
		if(oData.code == 1) {
			pageNum_search = oData.data.crcdaValue;
		}
	}},true);
}

//提交
function ModifyData(uuid, msg){
	var crcdaUuid = uuid;
	var crcdaValue = msg;
	var str = 'crcdaUuid='+encodeURIComponent(crcdaUuid)+'&crcdaValue='+encodeURIComponent(crcdaValue);
	getOData(str,"coreCacheData/update/coreCacheData",{fn:function(oData){
	}},true);
}