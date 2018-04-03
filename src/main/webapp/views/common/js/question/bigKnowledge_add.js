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
	$(".crkleCategory").val("");
	$(".crkleName").val("");
	goodColumns();
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
}

//检查提交
function checkAdd(){	
	if($.trim($(".crkleName").val()) == ""){
		alert("知识点名称不能为空，请填写完再提交！");
		$(".crkleName").focus();
		return false;
	}
	if($.trim($(".crkleCategory").val()) == ""){
		alert("所属类别不能为空，请选择再提交！");
		$(".crkleCategory").focus();
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
	var crkleName = $(".crkleName").val();
	var crkleCategory = $(".crkleCategory").val();
	var str = 'crkleName='+encodeURIComponent(crkleName)+'&crkleCategory='+encodeURIComponent(crkleCategory);
	getOData(str,"coreKnowledge/add/coreKnowledge",{
		fn:function(oData){
			window.parent.refreshList();
			alert("增加成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}