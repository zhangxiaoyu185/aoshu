// JavaScript Document
$(function () {
	$(".cur_postion").html("当前位置： [ "+sessionStorage.getItem("pmenuname")+" ] - [ "+sessionStorage.getItem("cmenuname")+" ]");

	//查询条件
	var strhtml_searchContent = '<div class="inline-block margin">'
		+'<span>学生账号:</span>'
		+'<input type="text" class="inputStyle_condition crusrName"/>'
		+'<span>所属班级:</span>'
		+'<select class="inputStyle_condition crusrGrade">'
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
		strhtml_searchContent += '</select>'
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
			content: '../busi/coreuser_detail.html?id='+$(this).attr("data-id")
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
			content: '../busi/coreuser_add.html'
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
			content: '../busi/coreuser_modify.html?id='+$(this).attr("data-id")
		});
	});
	//重置密码
	$("body").delegate(".resetit","click",function(){
		var r=confirm("是否确认重置密码？");
		if (r==true){
			var str = 'crusrUuid='+encodeURIComponent($(this).attr("data-id"));
			getOData(str,"coreUser/reset/pwd",{fn:function(oData){
					var obj = {};//查询条件对象
					searchContent(obj);
					pagenum = parseInt($(".curpage").text());
					isNull(obj,pagenum);
					alert("重置密码成功！");
				}}
			);
		}
		
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
				var str = 'crusrUuids='+encodeURIComponent(ids);
				getOData(str,"coreUser/delete/batch",{fn:function(oData){
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
			var str = 'crusrUuid='+encodeURIComponent($(this).attr("data-id"));
			getOData(str,"coreUser/delete/one",{fn:function(oData){
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
		/*var imgurl = $(this).attr("data-imageurl");
		if(imgurl != "#"){
			var that = this;
			$("<img/>").attr("src", imgurl).load(function() {

				var width = 150;
				var realWidth = this.width;
				var realHeight = this.height;
				var height = realHeight/realWidth*width;
				if(realWidth>=width){
					var bannerimg = '<img id="bannerimg" src="'+imgurl+'" width="'+width+'px" height="'+height+'px" />';
				}
				else{//如果小于浏览器的宽度按照原尺寸显示
					$("#bannerimg").css("width",realWidth+'px').css("height",realHeight+'px');
					var bannerimg = '<img id="bannerimg" src="'+imgurl+'" width="'+realWidth+'px" height="'+realHeight+'px" />';
				}

				layer.tips(bannerimg, that ,{
					tips: 3
				});
			});
		}*/
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
		$(".crusrName").val('');
		$(".crusrGrade").val('0');
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
				{name:"帐户名称",percent:"11"},
				{name:"真实姓名",percent:"11"},
				{name:"手机号码",percent:"11"},
				{name:"所属班级",percent:"12"},
				{name:"就读学校",percent:"15"},
				{name:"在读年级",percent:"9"},
				{name:"状态",percent:"8"},
				{name:"操作",percent:"18"}];
	setTableHead(aData);
	var str = '';
	if(obj.crusrName==""){
		str = 'pageNum='+pagenum+'&pageSize=20';
	}else{
		str = 'pageNum='+pagenum+'&pageSize=20&crusrName='+encodeURIComponent(obj.crusrName);
	}
	if(obj.crusrGrade!=0){
		str += '&crusrGrade='+encodeURIComponent(obj.crusrGrade);
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
			var crusrStatusCh="启用";
			var crusrStatus=arrData[i].crusrStatus;
			if(0==crusrStatus){
				crusrStatusCh="禁用";
			}
			strhtml_list += '<tr class="trHighLight">'
				+'<td>'+'<input type="checkbox" name="checkbox" value="checkbox" data-id="'+arrData[i].crusrUuid+'"/>'+'</td>'
				+'<td>'+(arrData[i].crusrName || "")+'</td>'
				+'<td>'+(arrData[i].crusrCode || "")+'</td>'
				+'<td>'+(arrData[i].crusrMobile || "")+'</td>'
				+'<td>'+(arrData[i].crusrGradeName || "")+'</td>'
				+'<td>'+(arrData[i].crusrSchool || "")+'</td>'
				+'<td>'+(crusrReadClassCh || "")+'</td>'
				+'<td>'+(crusrStatusCh)+'</td>'
				+'<td>'
				+'<a  class="p-edit detailit" data-id="'+arrData[i].crusrUuid+'">查看</a>'
				+'<a  class="p-edit modifyit" data-id="'+arrData[i].crusrUuid+'">修改</a>'
				+'<a  class="p-edit resetit" data-id="'+arrData[i].crusrUuid+'">重置密码</a>'
				+'<a  class="p-edit delit" data-id="'+arrData[i].crusrUuid+'">删除</a>'
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
	if(obj.crusrName==""){
		str = 'pageNum='+pagenum+'&pageSize=20';
	}else{
		str = 'pageNum='+pagenum+'&pageSize=20&crusrName='+encodeURIComponent(obj.crusrName);
	}
	if(obj.crusrGrade!=0){
		str += '&crusrGrade='+encodeURIComponent(obj.crusrGrade);
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
	obj.crusrGrade = $(".crusrGrade").val();
}
