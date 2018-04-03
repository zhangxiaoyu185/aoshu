// JavaScript Document
$(function () {
	$(".cur_postion").html("当前位置： [ "+sessionStorage.getItem("pmenuname")+" ] - [ "+sessionStorage.getItem("cmenuname")+" ]");

	//查询条件
	var strhtml_searchContent = '<div class="inline-block margin">'
		+'<span>课程名称:</span>'
		+'<input type="text" class="inputStyle_condition crwkaCourseName"/>'
		+'<span>用户姓名:</span>'
		+'<input type="text" class="inputStyle_condition crwkaUserCode"/>'
		+'<span>所属年级:</span>'
		+'<select class="inputStyle_condition crwkaClass">'
		+'<option value ="0" selected = "selected">全部</option>';
	getOData('',"coreClass/find/all/nothing",{fn:function(oData){
		if(oData.code == 1) {
			var strhtml_select = '';
			var arrData = oData.data;
			var ln = arrData.length;
			for(var i=0;i<ln;i++){
				strhtml_select += '<option value="'+arrData[i].crcasUuid+'">'+arrData[i].crcasName+'</option>';
			}
			$('.crwkaClass').append(strhtml_select);
		} else {
			alert(data.errMsg);
		}
	}});
	strhtml_searchContent+='</select>'
		+'</div>';
	$(".searchContent").html(strhtml_searchContent);
	//是否显示查询条件
	showSearchBox(true);

	var obj = {};//查询条件对象
	searchContent(obj);
	showList(obj,1);

	//详情
	$("body").delegate('.detailit','click', function(){
		layer.open({
			type: 2,
			title: '详情',
			scrollbar: false,
			maxmin: true,
			shadeClose: false, //点击遮罩关闭层
			area : [widthLayer , heightLayer],
			content: '../work/coreworkanswer_detail.html?id='+$(this).attr("data-id")
		});
	});
	//新增
	$('.addit').on('click', function(){
		layer.open({
			type: 2,
			title: '新增',
			scrollbar: false,
			maxmin: true,
			shadeClose: false, //点击遮罩关闭层
			area : [widthLayer , heightLayer],
			content: '../work/coreworkanswer_add.html'
		});
	});
	//修改
	$("body").delegate('.modifyit','click', function(){
		layer.open({
			type: 2,
			title: '修改',
			scrollbar: false,
			maxmin: true,
			shadeClose: false, //点击遮罩关闭层
			area : [widthLayer , heightLayer],
			content: '../work/coreworkanswer_modify.html?id='+$(this).attr("data-id")
		});
	});
	//全选
	$("body").delegate("input[name='checkboxAll']","click",function(){
		if($(this).attr("checked")){
			$("input[name='checkbox']").each(function(){
				$(this).attr("checked",true);
			});
		}else{
			$("input[name='checkbox']").each(function(){
				$(this).removeAttr("checked");
			});
		}
	});

	//批量删除
	$("body").delegate(".delthese","click",function(){
		var ids = '';
		$("input[name='checkbox']").each(function(){
			if($(this).attr("checked")){
				ids += $(this).attr("data-id")+"|";
			}
		});
		if(ids == ""){
			alert("未选择删除对象！");
		}else{
			var r=confirm("是否确认删除所选的记录？");
			if (r==true){
				var str = 'crwkaUuids='+encodeURIComponent(ids);
				getOData(str,"coreWorkAnswer/delete/batch",{fn:function(oData){
						var obj = {};//查询条件对象
						searchContent(obj);
						pagenum = parseInt($(".curpage").text());
						isNull(obj,pagenum);
						alert("删除成功！");
					}}
				);
			}
		}
	});

	//删除
	$("body").delegate(".delit","click",function(){
		var r=confirm("是否确认删除？");
		if (r==true){
			var str = 'crwkaUuid='+encodeURIComponent($(this).attr("data-id"));
			getOData(str,"coreWorkAnswer/delete/one",{fn:function(oData){
					var obj = {};//查询条件对象
					searchContent(obj);
					pagenum = parseInt($(".curpage").text());
					isNull(obj,pagenum);
					alert("删除成功！");
				}}
			);
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
		$(".crwkaUserCode").val('');
		$(".crwkaCourseName").val('');
		$(".crwkaClass").val(0);
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
				{name:"课程名称",percent:"15"},
				{name:"用户姓名",percent:"15"},
				{name:"所属年级",percent:"10"},
				{name:"得分",percent:"10"},
				{name:"状态",percent:"10"},
				{name:"修改时间",percent:"15"},
				{name:"操作",percent:"20"}];
	setTableHead(aData);
	if(obj.crwkaUserCode==""){
		str = 'pageNum='+pagenum+'&pageSize=20';
	}else{
		str = 'pageNum='+pagenum+'&pageSize=20&crwkaUserCode='+encodeURIComponent(obj.crwkaUserCode);
	}
	if(obj.crwkaCourseName!=""){
		str += '&crwkaCourseName='+encodeURIComponent(obj.crwkaCourseName);
	}
	if(obj.crwkaClass!='0'){
		str += '&crwkaClass='+encodeURIComponent(obj.crwkaClass);
	}
	getOData(str,"coreWorkAnswer/find/by/cnd",{fn:function(oData){
		var strhtml_list = "";
		var arrData = oData.data.result;
		var ln = arrData.length;
		for(var i=0;i<ln;i++){
			var crwkaStateCh='需订正';
			var crwkaState = arrData[i].crwkaState;
			if(0==crwkaState){
				crwkaStateCh='已完结';
			}
			strhtml_list += '<tr class="trHighLight">'
				+'<td>'+'<input type="checkbox" name="checkbox" value="checkbox" data-id="'+arrData[i].crwkaUuid+'"/>'+'</td>'
				+'<td>'+(arrData[i].crwkaCourseName || "")+'</td>'
				+'<td>'+(arrData[i].crwkaUserCode || "")+'</td>'
				+'<td>'+(arrData[i].crwkaClassName || "")+'</td>'
				+'<td>'+(arrData[i].crwkaScore || 0)+'</td>'
				+'<td>'+(crwkaStateCh)+'</td>'
				+'<td>'+(arrData[i].crwkaUdate || "")+'</td>'
				+'<td>'
				+'<a  class="p-edit detailit" data-id="'+arrData[i].crwkaUuid+'">查看</a>'
				+'<a  class="p-edit delit" data-id="'+arrData[i].crwkaUuid+'">删除</a>'
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
	if(obj.crwkaUserCode==""){
		str = 'pageNum='+pagenum+'&pageSize=20';
	}else{
		str = 'pageNum='+pagenum+'&pageSize=20&crwkaUserCode='+encodeURIComponent(obj.crwkaUserCode);
	}
	if(obj.crwkaCourseName!=""){
		str += '&crwkaCourseName='+encodeURIComponent(obj.crwkaCourseName);
	}
	if(obj.crwkaClass!='0'){
		str += '&cresaClass='+encodeURIComponent(obj.crwkaClass);
	}
	getOData(str,"coreWorkAnswer/find/by/cnd",{fn:function(oData){
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
	obj.crwkaCourseName = $(".crwkaCourseName").val();
	obj.crwkaUserCode = $(".crwkaUserCode").val();
	obj.crwkaClass = $(".crwkaClass").val();
}
