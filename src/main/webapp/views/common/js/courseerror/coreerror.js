// JavaScript Document
$(function () {
	$(".cur_postion").html("当前位置： [ "+sessionStorage.getItem("pmenuname")+" ] - [ "+sessionStorage.getItem("cmenuname")+" ]");

	//查询条件
	var strhtml_searchContent = '<div class="inline-block margin">'
		+'<span>题目编号:</span>'
		+'<input type="text" class="inputStyle_condition crqtsQuesCode"/>'
		+'<span>所属班级:</span>'
		+'<select class="inputStyle_condition crusrGrade" onchange="changeGrade(this)">'
		+'<option value ="0" selected = "selected">全部</option>';
		getOData('',"coreGrade/find/select",{fn:function(oData){
			if(oData.code == 1) {
				var strhtml_select = '';
				var arrData = oData.data;
				var ln = arrData.length;
				for(var i=0;i<ln;i++){
					strhtml_select += '<option value="'+arrData[i].crgaeUuid+'">'+arrData[i].crgaeName+'</option>';
				}
				$('.crusrGrade').append(strhtml_select);
			} else {
				alert(data.errMsg);
			}
		}});
		strhtml_searchContent += '</select>';		
		strhtml_searchContent += '<span>用户姓名:</span>'
		+'<select class="inputStyle_condition crusrCode">'
		+'<option value ="0" selected = "selected">全部</option>'
		+'</select><br />'
		+'<span>课程或试卷:</span>'
		+'<input type="text" class="inputStyle_condition crerrFromName"/>'
		+'<span>所属年级:</span>'
		+'<select class="inputStyle_condition crqtsQuesClass">'
		+'<option value ="0" selected = "selected">全部</option>';
		getOData('',"coreClass/find/all/nothing",{fn:function(oData){
			if(oData.code == 1) {
				var strhtml_select = '';
				var arrData = oData.data;
				var ln = arrData.length;
				for(var i=0;i<ln;i++){
					strhtml_select += '<option value="'+arrData[i].crcasUuid+'">'+arrData[i].crcasName+'</option>';
				}
				$('.crqtsQuesClass').append(strhtml_select);
			} else {
				alert(data.errMsg);
			}
		}});
		strhtml_searchContent += '</select>';		
		strhtml_searchContent += '</div>';
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
			content: '../courseerror/coreerror_detail.html?id='+$(this).attr("data-id")
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
				var str = 'crerrUuids='+encodeURIComponent(ids);
				getOData(str,"coreError/delete/batch",{fn:function(oData){
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
			var str = 'crerrUuid='+encodeURIComponent($(this).attr("data-id"));
			getOData(str,"coreError/delete/one",{fn:function(oData){
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
		$(".crqtsQuesCode").val('');
		$(".crusrCode").val('0');
		$(".crerrFromName").val('');	
		$(".crusrGrade").val('0');
		$(".crqtsQuesClass").val('0');		
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

//班级学生联动查询
function changeGrade(obj){
	var value = obj.value;
	getCoreUser(value);	
}

function getCoreUser(crusrGrade) {
	if(crusrGrade == '0') {
		$('.crusrCode').html('<option value ="0" selected = "selected">全部</option>');
		return;
	}
	var str = 'crusrGrade='+encodeURIComponent(crusrGrade);
	getOData(str,"coreUser/find/all",{fn:function(oData){
		if(oData.code == 1) {
			var strhtml_select = '';
			var arrData = oData.data;
			var ln = arrData.length;
			for(var i=0;i<ln;i++){
				strhtml_select += '<option value="'+arrData[i].crusrUuid+'">'+arrData[i].crusrCode+'</option>';
			}
			$('.crusrCode').html(strhtml_select);
		} else {
			alert(data.errMsg);
		}
	}});
}
function showList(obj,pagenum){
	var aData =[{name:"<input type='checkbox' name='checkboxAll' value='checkbox' />",percent:"5"},
				{name:"来源名称",percent:"25"},
				{name:"年级",percent:"10"},
				{name:"题目编号",percent:"15"},
				{name:"用户名",percent:"15"},
				{name:"判断",percent:"15"},
				{name:"操作",percent:"15"}];
	setTableHead(aData);
	var str = '';
	if(obj.crusrCode=="0"){
		str = 'pageNum='+pagenum+'&pageSize=20';
	}else{
		str = 'pageNum='+pagenum+'&pageSize=20&crusrCode='+encodeURIComponent(obj.crusrCode);
	}
	if(obj.crqtsQuesCode!=""){
		str += '&crqtsQuesCode='+encodeURIComponent(obj.crqtsQuesCode);
	}
	if(obj.crerrFromName!=""){
		str += '&crerrFromName='+encodeURIComponent(obj.crerrFromName);
	}
	if(obj.crusrGrade!=0){
		str += '&crusrGrade='+encodeURIComponent(obj.crusrGrade);
	}
	if(obj.crqtsQuesClass!='0'){
		str += '&crqtsQuesClass='+encodeURIComponent(obj.crqtsQuesClass);
	}	
	getOData(str,"coreError/find/by/cnd",{fn:function(oData){
		var strhtml_list = "";
		var arrData = oData.data.result;
		var ln = arrData.length;
		for(var i=0;i<ln;i++){			
			var crerrJudgeCh="未订正";
			var crerrJudge=arrData[i].crerrJudge;
			if(0==crerrJudge){
				crerrJudgeCh="已订正";
			}
			
			strhtml_list += '<tr class="trHighLight">'
				+'<td>'+'<input type="checkbox" name="checkbox" value="checkbox" data-id="'+arrData[i].crerrUuid+'"/>'+'</td>'
				+'<td>'+(arrData[i].crerrFromName || "")+'</td>'
				+'<td>'+(arrData[i].crqtsQuesClassName || "")+'</td>'
				+'<td>'+(arrData[i].crqtsQuesCode || "")+'</td>'
				+'<td>'+(arrData[i].crusrCode || "")+'</td>'
				+'<td>'+(crerrJudgeCh)+'</td>'
				+'<td>'
				+'<a  class="p-edit detailit" data-id="'+arrData[i].crerrUuid+'">查看</a>'
				+'<a  class="p-edit delit" data-id="'+arrData[i].crerrUuid+'">删除</a>'
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
	if(obj.crusrCode=="0"){
		str = 'pageNum='+pagenum+'&pageSize=20';
	}else{
		str = 'pageNum='+pagenum+'&pageSize=20&crusrCode='+encodeURIComponent(obj.crusrCode);
	}
	if(obj.crqtsQuesCode!=""){
		str += '&crqtsQuesCode='+encodeURIComponent(obj.crqtsQuesCode);
	}
	if(obj.crerrFromName!=""){
		str += '&crerrFromName='+encodeURIComponent(obj.crerrFromName);
	}
	if(obj.crusrGrade!=0){
		str += '&crusrGrade='+encodeURIComponent(obj.crusrGrade);
	}
	if(obj.crqtsQuesClass!='0'){
		str += '&crqtsQuesClass='+encodeURIComponent(obj.crqtsQuesClass);
	}
	getOData(str,"coreError/find/by/cnd",{fn:function(oData){
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
	obj.crqtsQuesCode = $(".crqtsQuesCode").val();
	obj.crusrCode = $(".crusrCode").val();
	obj.crerrFromName = $(".crerrFromName").val();
	obj.crusrGrade = $(".crusrGrade").val();
	obj.crqtsQuesClass = $(".crqtsQuesClass").val();
}
