var userUuids = '';
// JavaScript Document
$(function () {
	initModify();
});
//初始化
function initModify(){
	getInfo(getQueryString("id"));
	//是否显示查询条件
	showSearchBox(true);

	var obj = {};//查询条件对象
	searchContent(obj);
	showList(obj,1);
	
	//取消
	$("body").delegate(".delit","click",function(){
		var r=confirm("是否确认取消？");
		if (r==true){
			var userUuid = encodeURIComponent($(this).attr("data-id")) +',';
			userUuids = userUuids.replace(new RegExp(userUuid.trim(),'g'), '');
			Modify();
			alert("取消成功！");
			var obj = {};//查询条件对象
			searchContent(obj);
			showList(obj,1);
		}
	});
	
	//添加
	$("body").delegate('.modifyit','click', function(){
		var userUuid = encodeURIComponent($(this).attr("data-id")) +',';
		if(userUuids.indexOf(userUuid) >= 0) {
			alert("该学生已存在！");
			return;
		}
		userUuids = userUuids + userUuid;
		Modify();
		alert("添加成功！");
		var obj = {};//查询条件对象
		searchContent(obj);
		showList(obj,1);	
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
		$(".crqtsCode").val('');
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
}

function showList(obj,pagenum){
	var aData =[{name:"<input type='checkbox' name='checkboxAll' value='checkbox' />",percent:"5"},
				{name:"帐户名称",percent:"15"},
				{name:"真实姓名",percent:"15"},
				{name:"手机号码",percent:"15"},
				{name:"年级权限",percent:"15"},
				{name:"在读年级",percent:"10"},
				{name:"状态",percent:"12"},
				{name:"操作",percent:"13"}];
	setTableHead(aData);
	var str = '';
	if(obj.crusrName==""){
		str = 'pageNum='+pagenum+'&pageSize=20';
	}else{
		str = 'pageNum='+pagenum+'&pageSize=20&crusrName='+encodeURIComponent(obj.crusrName);
	}
	if(obj.crusrReadClass!='0'){
		str += '&crusrReadClass='+encodeURIComponent(obj.crusrReadClass);
	}
	getOData(str,"coreUser/find/by/cnd",{fn:function(oData){
		var strhtml_list = "";
		var arrData = oData.data.result;
		var ln = arrData.length;
		for(var i=0;i<ln;i++){
			//1一年级2二年级3三年级4四年级5五年级6六年级7其他
			var crusrReadClassCh="一年级";
			var crusrReadClass=arrData[i].crusrReadClass;
			if(1==crusrReadClass){
				crusrReadClassCh="一年级";
			}else if(2==crusrReadClass){
				crusrReadClassCh="二年级";
			}else if(3==crusrReadClass){
				crusrReadClassCh="三年级";
			}else if(4==crusrReadClass){
				crusrReadClassCh="四年级";
			}else if(5==crusrReadClass){
				crusrReadClassCh="五年级";
			}else if(6==crusrReadClass){
				crusrReadClassCh="六年级";
			}else if(7==crusrReadClass){
				crusrReadClassCh="其他";
			}
			strhtml_list += '<tr class="trHighLight">'
				+'<td>'+'<input type="checkbox" name="checkbox" value="checkbox" data-id="'+arrData[i].crusrUuid+'"/>'+'</td>'
				+'<td>'+(arrData[i].crusrName || "")+'</td>'
				+'<td>'+(arrData[i].crusrCode || "")+'</td>'
				+'<td>'+(arrData[i].crusrMobile || "")+'</td>'
				+'<td>'+(arrData[i].crusrClasssName || "")+'</td>'
				+'<td>'+(crusrReadClassCh || "")+'</td>';				
			if(userUuids.indexOf(arrData[i].crusrUuid) < 0) {
				strhtml_list += '<td>未添加</td><td><a class="p-edit modifyit" data-id="'+arrData[i].crusrUuid+'">添加</a>';
			} else {
				strhtml_list += '<td>已添加</td><td><a class="p-edit delit" data-id="'+arrData[i].crusrUuid+'">取消</a>';
			}
			strhtml_list += '</td></tr>';
		}
		$(".tb-body").html(strhtml_list);
		setTableFoot(oData.data,aData.length);
		}}
	);
}
function isNull(obj,pagenum){
	var str = '';
	if(obj.crusrName==""){
		str = 'pageNum='+pagenum+'&pageSize=20';
	}else{
		str = 'pageNum='+pagenum+'&pageSize=20&crusrName='+encodeURIComponent(obj.crusrName);
	}
	if(obj.crusrReadClass!='0'){
		str += '&crusrReadClass='+encodeURIComponent(obj.crusrReadClass);
	}
	getOData(str,"coreUser/find/by/cnd",{fn:function(oData){
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
	obj.crusrName = $(".crusrName").val();
	obj.crusrReadClass = $(".crusrReadClass").val();
}

//获取详情
function getInfo(id){
	var str = 'crcreUuid='+encodeURIComponent(id);
	getOData(str,"coreCourse/views",{fn:function(oData){
		if(oData.code == 1) {
			userUuids = oData.data.crcreUser;
			if(!userUuids) {
				userUuids = '';
			}
		} else {
			alert(data.errMsg);
		}
	}});
}

//提交
function Modify(msgObject){	
	var crcreUuid = getQueryString("id");
	var crcreUser = userUuids;
	var str = 'crcreUuid='+encodeURIComponent(crcreUuid)+'&crcreUser='+encodeURIComponent(crcreUser);
	getOData(str,"coreCourse/update/coreCourse/user",{
		fn:function(oData){
		},
		fnerr:function(oData){
		}
	});
}