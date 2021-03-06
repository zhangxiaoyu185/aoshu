// JavaScript Document
$(function () {
	initModify();
	//年级、课程联动
	$(".crecsClass").on("change",function(){
		var crcreClass=$(".crecsClass").val();
		getCourse(crcreClass);
	});
	//提交
	$(".submit").on("click",function(){
		checkModify();
	});
});
//初始化
function initModify(){
	getClass();
	getInfo(getQueryString("id"));
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
			$('.crecsClass').html(strhtml_select);
		} else {
			alert(data.errMsg);
		}
	}});
}

//加载课程列表下拉框内容
function getCourse(crcreClass){
	var str ='crcreClass='+encodeURIComponent(crcreClass);
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
			$('.crecsCourse').html(strhtml_select_course);
		} else {
			alert(data.errMsg);
		}
	}});
}

//获取详情
function getInfo(id){
	var str = 'crecsUuid='+encodeURIComponent(id);
	getOData(str,"coreExercises/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crecsCourse").val(oData.data.crecsCourse);
			$(".crecsClass").val(oData.data.crecsClass);
			getCourse(oData.data.crecsClass);
		} else {
			alert(data.errMsg);
		}
	}});
}

//检查提交
function checkModify(){
	if($.trim($(".crecsClass").val()) == ""){
		alert("所属年级不能为空，请填写完再提交！");
		$(".crecsClass").focus();
		return false;
	}
	if($.trim($(".crecsCourse").val()) == ""){
		alert("所属课程不能为空，请填写完再提交！");
		$(".crecsCourse").focus();
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
	var crecsUuid = getQueryString("id");
	var crecsCourse = $(".crecsCourse").val();
	var crecsCourseName = $(".crecsCourse").find("option:selected").text();
	var crecsClass = $(".crecsClass").val();
	var str = 'crecsUuid='+encodeURIComponent(crecsUuid)+'&crecsCourse='+encodeURIComponent(crecsCourse)+'&crecsCourseName='+encodeURIComponent(crecsCourseName)+'&crecsClass='+encodeURIComponent(crecsClass);
	getOData(str,"coreExercises/update/coreExercises",{
		fn:function(oData){
			window.parent.refreshList();
			alert("修改成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}