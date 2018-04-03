// JavaScript Document
$(function () {
	//查询条件
	var strhtml_searchContent = '<div class="inline-block margin">'
		+'<span>试卷名:</span>'
		+'<input type="text" class="inputStyle_condition crpesName"/>'
		+'<span>所属年级:</span>'
		+'<select class="inputStyle_condition crpesClass">'
		+'<option value ="0" selected = "selected">全部</option>';
		getOData('',"coreClass/find/all/nothing",{fn:function(oData){
			if(oData.code == 1) {
				var strhtml_select = '';
				var arrData = oData.data;
				var ln = arrData.length;
				for(var i=0;i<ln;i++){
					strhtml_select += '<option value="'+arrData[i].crcasUuid+'">'+arrData[i].crcasName+'</option>';
				}
				$('.crpesClass').append(strhtml_select);
			} else {
				alert(data.errMsg);
			}
		}});
	strhtml_searchContent += '</select></div>';
	$(".searchContent").html(strhtml_searchContent);
	//是否显示查询条件
	showSearchBox(true);

	var obj = {};//查询条件对象
	searchContent(obj);
	showList(obj,1);

	//全选
	$("body").delegate("input[name='checkboxAll']","click",function(){
		if($(this).prop("checked")){
			$("input[name='checkbox']").each(function(){
				$(this).attr("checked",true);
			});
		}else{
			$("input[name='checkbox']").each(function(){
				$(this).removeAttr("checked");
			});
		}
	});
	//批量新增
	$("body").delegate(".addit","click",function(){
		var r=confirm("是否确认添加所选的记录？");
		if (r==true){
			var msgObject = parent.layer.msg('处理中，请等待……', {
				icon: 16,
				shade: 0.4,
				time: waitImgTime //（如果不配置，默认是3秒）
			}, function(index){
				//do something
				parent.layer.close(index);
			});
			$("input[name='checkbox']").each(function(){
				if($(this).prop("checked")){	
					var crgpsGrade = getQueryString("grade");
					var crgpsPapers = $(this).attr("data-id");
					var crpesClass = $(this).attr("data-name");
					var str = 'crgpsGrade='+encodeURIComponent(crgpsGrade)+'&crgpsPapers='+encodeURIComponent(crgpsPapers)+'&crpesClass='+encodeURIComponent(crpesClass);
					getOData(str,"coreGradePapers/add/coreGradePapers",{fn:function(oData){
					}},true);
				}
			});
			alert("添加成功！");
			parent.layer.close(msgObject);
			var obj = {};//查询条件对象
			searchContent(obj);
			pagenum = parseInt($(".curpage").text());
			isNull(obj,pagenum);
		}
	});
	//添加
	$("body").delegate(".modifyit","click",function(){
		var r=confirm("是否确认添加？");
		if (r==true){
			var msgObject = parent.layer.msg('处理中，请等待……', {
				icon: 16,
				shade: 0.4,
				time: waitImgTime //（如果不配置，默认是3秒）
			}, function(index){
				//do something
				parent.layer.close(index);
			});
			var crgpsGrade = getQueryString("grade");
			var crgpsPapers = $(this).attr("data-id");
			var crpesClass = $(this).attr("data-name");
			var str = 'crgpsGrade='+encodeURIComponent(crgpsGrade)+'&crgpsPapers='+encodeURIComponent(crgpsPapers)+'&crpesClass='+encodeURIComponent(crpesClass);
			getOData(str,"coreGradePapers/add/coreGradePapers",{fn:function(oData){
				alert("添加成功！");
				parent.layer.close(msgObject);
				var obj = {};//查询条件对象
				searchContent(obj);
				pagenum = parseInt($(".curpage").text());
				isNull(obj,pagenum);
			}});
		}
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
	});
	//重置
	$("body").delegate(".resetBtn","click",function(){
		var obj = {};//查询条件对象
		$(".crpesName").val('');
		$(".crpesClass").val(0);
		searchContent(obj);
		showList(obj,1);
	});
	//上一页
	$('.manageBox').delegate(".backpage","click", function() {
		var obj = {};//查询条件对象
		searchContent(obj);
		pagenum = parseInt($(this).attr("data-pagenum"));
		showList(obj,pagenum);
	});
	//下一页
	$('.manageBox').delegate(".nextpage","click", function() {
		var obj = {};//查询条件对象
		searchContent(obj);
		var pagenum = parseInt($(this).attr("data-pagenum"));
		showList(obj,pagenum);
	});
	//首页
	$('.manageBox').delegate(".firstpage","click", function() {
		var obj = {};//查询条件对象
		searchContent(obj);
		var pagenum = parseInt($(this).attr("data-pagenum"));
		showList(obj,pagenum);
	});
	//末页
	$('.manageBox').delegate(".lastpage","click", function() {
		var obj = {};//查询条件对象
		searchContent(obj);
		var pagenum = parseInt($(this).attr("data-pagenum"));
		showList(obj,pagenum);
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
	});

});
function showList(obj,pagenum){
	var aData =[{name:"<input type='checkbox' name='checkboxAll' value='checkbox' />",percent:"5"},
				{name:"试卷名",percent:"20"},
				{name:"分数",percent:"10"},
				{name:"所属年级",percent:"15"},
				{name:"创建时间",percent:"15"},
				{name:"修改时间",percent:"15"},
				{name:"操作",percent:"20"}];
	setTableHead(aData);
	var str = '';
	if(obj.crpesName==""){
		str = 'pageNum='+pagenum+'&pageSize=20&crgpsGrade='+getQueryString("grade");
	}else{
		str = 'pageNum='+pagenum+'&pageSize=20&crgpsGrade='+getQueryString("grade")+'&crpesName='+encodeURIComponent(obj.crpesName);
	}
	if(obj.crpesClass!='0'){
		str += '&crpesClass='+encodeURIComponent(obj.crpesClass);
	}
	
	getOData(str,"corePapers/find/by/cnd/wtj",{fn:function(oData){
		var strhtml_list = "";
		var arrData = oData.data.result;
		var ln = arrData.length;
		for(var i=0;i<ln;i++){
			strhtml_list += '<tr class="trHighLight">'
				+'<td>'+'<input type="checkbox" name="checkbox" value="checkbox" data-id="'+arrData[i].crpesUuid+'" data-name="'+arrData[i].crpesClass+'"/>'+'</td>'
				+'<td>'+(arrData[i].crpesName || "")+'</td>'
				+'<td>'+(arrData[i].crpesScore || 0)+'</td>'
				+'<td>'+(arrData[i].crpesClassName || "")+'</td>'
				+'<td>'+(arrData[i].crpesCdate || "")+'</td>'
				+'<td>'+(arrData[i].crpesUdate || "")+'</td>'
				+'<td>'
				+'<a  class="p-edit modifyit" data-id="'+arrData[i].crpesUuid+'" data-name="'+arrData[i].crpesClass+'">添加</a>'
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
	if(obj.crpesName==""){
		str = 'pageNum='+pagenum+'&pageSize=20&crgpsGrade='+getQueryString("grade");
	}else{
		str = 'pageNum='+pagenum+'&pageSize=20&crgpsGrade='+getQueryString("grade")+'&crpesName='+encodeURIComponent(obj.crpesName);
	}
	if(obj.crpesClass!='0'){
		str += '&crpesClass='+encodeURIComponent(obj.crpesClass);
	}
	getOData(str,"corePapers/find/by/cnd/wtj",{fn:function(oData){
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
	obj.crpesName = $(".crpesName").val();
	obj.crpesClass = $(".crpesClass").val();
}
