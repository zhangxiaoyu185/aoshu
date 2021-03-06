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
	$(".crpesName").val("");
	$(".crpesScore").val("");
	getClass();
}
//加载列表下拉框内容
function getClass(){
	var str = '';
	getOData(str,"coreClass/find/all/nothing",{fn:function(oData){
		if(oData.code == 1) {
			var strhtml_select = '';
			var arrData = oData.data;
			var ln = arrData.length;
			for(var i=0;i<ln;i++){
					strhtml_select += '<option value="'+arrData[i].crcasUuid+'">'+arrData[i].crcasName+'</option>';
			}
			$('.crpesClass').html(strhtml_select);
		} else {
			alert(data.errMsg);
		}
	}});
}

//检查提交
function checkAdd(){
	if($.trim($(".crpesName").val()) == ""){
		alert("试卷名不能为空，请填写完再提交！");
		$(".crpesName").focus();
		return false;
	}
	if($.trim($(".crpesScore").val()) == ""){
		alert("分数不能为空，请填写完再提交！");
		$(".crpesScore").focus();
		return false;
	}
	if($.trim($(".crpesClass").val()) == ""){
		alert("所属年级不能为空，请填写完再提交！");
		$(".crpesClass").focus();
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
	var crpesName = $(".crpesName").val();
	var crpesScore = $(".crpesScore").val();
	var crpesClass = $(".crpesClass").val();
	var str = 'crpesName='+encodeURIComponent(crpesName)+'&crpesScore='+encodeURIComponent(crpesScore)+'&crpesClass='+encodeURIComponent(crpesClass);
	getOData(str,"corePapers/add/corePapers",{
		fn:function(oData){
			//window.parent.refreshList();
			parent.layer.close(msgObject);
			alert("增加成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
			alert("增加失败！");
		}
	});
}