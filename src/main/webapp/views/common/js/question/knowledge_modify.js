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
	goodColumns();
	getInfo(getQueryString("id"));
}

//加载模块下拉框内容
function goodColumns(){
	var str = '';
	getOData(str,"coreCategory/find/all",{fn:function(oData){
		var strhtml_goodColumns = '';
		var arrData = oData.data;
		var ln = arrData.length;
		for(var i=0;i<ln;i++){
			strhtml_goodColumns += '<option value ="'+arrData[i].crceyUuid+'" >'+arrData[i].crceyName+'</option>';
		}
		$(".crkleCategory").html(strhtml_goodColumns);
	}});
	str = 'pageNum=1&pageSize=100';
	getOData(str,"coreKnowledge/find/page/top",{fn:function(oData){
		var strhtml_goodColumns = '';
		var arrData = oData.data.result;
		var ln = arrData.length;
		for(var i=0;i<ln;i++){
			strhtml_goodColumns += '<option value ="'+arrData[i].crkleUuid+'" >'+arrData[i].crkleName+'</option>';
		}
		$(".crkleTop").html(strhtml_goodColumns);
	}});
}

//获取详情
function getInfo(id){
	var str = 'crkleUuid='+encodeURIComponent(id);
	getOData(str,"coreKnowledge/views",{fn:function(oData){
		if(oData.code == 1) {
			var crkleCategory=oData.data.crkleCategory;
			$(".crkleCategory").find("option[value='"+crkleCategory+"']").attr("selected",true);
			var crkleTop=oData.data.crkleTop;
			$(".crkleTop").find("option[value='"+crkleTop+"']").attr("selected",true);
			$(".crkleName").val(oData.data.crkleName || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
//检查提交
function checkModify(){
	if($.trim($(".crkleCategory").val()) == ""){
		alert("所属类别不能为空，请选择再提交！");
		$(".crkleCategory").focus();
		return false;
	}
	if($.trim($(".crkleName").val()) == ""){
		alert("知识点名称不能为空，请填写完再提交！");
		$(".crkleName").focus();
		return false;
	}
	if($.trim($(".crkleTop").val()) == ""){
		alert("所属大知识点不能为空，请填写完再提交！");
		$(".crkleTop").focus();
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
	var crkleUuid = getQueryString("id");
	var crkleCategory = $(".crkleCategory").val();
	var crkleName = $(".crkleName").val();
	var crkleTop = $(".crkleTop").val();
	var str = 'crkleUuid='+encodeURIComponent(crkleUuid)+'&crkleName='+encodeURIComponent(crkleName)+'&crkleTop='+encodeURIComponent(crkleTop)+'&crkleCategory='+encodeURIComponent(crkleCategory);
	getOData(str,"coreKnowledge/update/coreKnowledge",{
		fn:function(oData){
			window.parent.refreshList();
			alert("修改成功！");		
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}		
	});
}