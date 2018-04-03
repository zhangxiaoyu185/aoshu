// JavaScript Document
$(function () {
	initAdd();
	//年级、课程联动
	$(".crwokClass").on("change",function(){
		var crwokClass=$(".crwokClass").val();
		getCourse(crwokClass);
	});
	//提交
	$(".submit").on("click",function(){
		checkAdd();
	});
});
//初始化
function initAdd(){
	getClass();
	getCourse(1);
}
//加载年级列表下拉框内容
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
			$('.crwokClass').html(strhtml_select);
		} else {
			alert(data.errMsg);
		}
	}});
}

//加载课程列表下拉框内容
function getCourse(crwokClass){
	var str ='crcreClass='+encodeURIComponent(crwokClass);
	getOData(str,"coreCourse/find/all",{fn:function(oData){
		if(oData.code == 1) {
			var strhtml_select_course = '';
			var arrData = oData.data;
			var ln = arrData.length;
			for(var i=0;i<ln;i++){
				strhtml_select_course += '<option value="'+arrData[i].crcreUuid+'">'+arrData[i].crcreName+'-'+arrData[i].crcreContent+'</option>';
			}
			if('' == strhtml_select_course){
				strhtml_select_course += '<option value="0">无</option>';
			}
			$('.crwokCourse').html(strhtml_select_course);
		} else {
			alert(data.errMsg);
		}
	}});
}

//检查提交
function checkAdd(){
	if($.trim($(".crwokClass").val()) == ""){
		alert("所属年级不能为空，请填写完再提交！");
		$(".crwokClass").focus();
		return false;
	}
	if($.trim($(".crwokCourse").val()) == ""){
		alert("所属课程不能为空，请填写完再提交！");
		$(".crwokCourse").focus();
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
	var crwokCourse = $(".crwokCourse").val();
	var crwokCourseName = $(".crwokCourse").find("option:selected").text();
	var crwokClass = $(".crwokClass").val();
	var str = 'crwokCourse='+encodeURIComponent(crwokCourse)+'&crwokCourseName='+encodeURIComponent(crwokCourseName)+'&crwokClass='+encodeURIComponent(crwokClass);
	getOData(str,"coreWork/add/coreWork",{
		fn:function(oData){
			window.parent.refreshList();
			alert("增加成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}