// JavaScript Document
$(function () {
	$(".cur_postion").html("当前位置： [学习统计] - [学生课程]");

	//查询条件
	var strhtml_searchContent = '<div class="inline-block margin">'
		+'<span>班级:</span>'
		+'<select class="inputStyle_condition grade"></select>'
		+'<span>年级:</span>'
		+'<select class="inputStyle_condition class"></select>';

	$(".searchContent").html(strhtml_searchContent);
	getOData('',"coreGrade/find/select",{fn:function(oData){
		if(oData.code == 1) {
			var strhtml_select = '';
			var arrData = oData.data;
			var ln = arrData.length;
			for(var i=0;i<ln;i++){
				strhtml_select += '<option value="'+arrData[i].crgaeUuid+'">'+arrData[i].crgaeName+'</option>';
			}
			$('.grade').append(strhtml_select);
		} else {
			alert(data.errMsg);
		}
	}},true);

	
	getOData('',"coreClass/find/all/nothing",{fn:function(oData){
		if(oData.code == 1) {
			var strhtml_select = '';
			var arrData = oData.data;
			var ln = arrData.length;
			for(var i=0;i<ln;i++){
				strhtml_select += '<option value="'+arrData[i].crcasUuid+'">'+arrData[i].crcasName+'</option>';
			}
			$('.class').append(strhtml_select);
		} else {
			alert(data.errMsg);
		}
	}},true);
//	strhtml_searchContent+='</select>'
//		+'</div>';
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
			content: '../papers/corepapersanswer_detail.html?id='+$(this).attr("data-id")
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
			content: '../papers/corepapersanswer_add.html'
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
			content: '../papers/corepapersanswer_modify.html?id='+$(this).attr("data-id")
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
				var str = 'crpsaUuids='+encodeURIComponent(ids);
				getOData(str,"corePapersAnswer/delete/batch",{fn:function(oData){
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
			var str = 'crpsaUuid='+encodeURIComponent($(this).attr("data-id"));
			getOData(str,"corePapersAnswer/delete/one",{fn:function(oData){
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
		$(".grade").val(0);
		$(".class").val(0);
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
	var aData =[{name:"序号",percent:"5"},
				{name:"学生姓名",percent:"20"},
				{name:"未做数量",percent:"20"},
				{name:"已做未满分数量",percent:"20"},
				{name:"已做满分数量",percent:"20"}];
	setTableHead(aData);
	var str = '';
	str = 'strGrade='+ obj.strGrade + '&strClass=' + obj.strClass;
	
	getOData(str,"gradeTotal/user/course",{fn:function(oData){
		var strhtml_list = "";
		var arrData = oData.data;
		var ln = arrData.length;
		for(var i=0;i<ln;i++){
			strhtml_list += '<tr class="trHighLight">'
				+'<td>'+(i+1)+'</td>'
				+'<td>'+(arrData[i].columnObj || "")+'</td>'
				+'<td><a class="p-edit seeDes" onclick="seeDes(\'未做课程\',\''+arrData[i].hwzValue+'\')">'+(arrData[i].hwzKey || 0)+'</a></td>'
				+'<td><a class="p-edit seeDes" onclick="seeDes(\'已做未满分课程\',\''+arrData[i].hyzwmfValue+'\')">'+(arrData[i].hyzwmfKey || 0)+'</a></td>'
				+'<td><a class="p-edit seeDes" onclick="seeDes(\'已做满分课程\',\''+arrData[i].hyzmfValue+'\')">'+(arrData[i].hyzmfKey || 0)+'</a></td>'
				+'</tr>';
		}
		$(".tb-body").html(strhtml_list);
		}}
	);
}

function seeDes(title, data) {
	layer.open({
		type: 1,
		title: title,
		scrollbar: false,
		maxmin: true,
		shadeClose: false, //点击遮罩关闭层
		area : ['600px' , '390px'],
		content: '<div style="font-size: 15px;padding: 10px;">' + data + '</div>'
	});
}

function refreshList(){
	var obj = {};//查询条件对象
	searchContent(obj);
	showList(obj,parseInt($(".curpage").text()));
	layer.closeAll();
}

function searchContent(obj){
	obj.strGrade = $(".grade").val();
	obj.strClass = $(".class").val();
}