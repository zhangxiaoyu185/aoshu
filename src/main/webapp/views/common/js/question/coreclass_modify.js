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
	$(".crcasName").val("");
	/*CKEDITOR.replace( 'crcseContent' , { height: '240px', 
		width: '480px',
		toolbar: [[ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo', 'Bold',
						'Italic',"Format","FontSize","TextColor" ,"CodeSnippet","Table"]],
		extraPlugins: 'codesnippet',
		uiColor: '#f3f3f3'
	} );*/
	getInfo(getQueryString("id"));
}
//获取详情
function getInfo(id){
	var str = 'crcasUuid='+encodeURIComponent(id);
	getOData(str,"coreClass/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crcasName").val(oData.data.crcasName || "");
			//CKEDITOR.instances.crcseContent.setData(oData.data.crcseContent);
		} else {
			alert(data.errMsg);
		}
	}});
}

//检查提交
function checkModify(){
	if($.trim($(".crcasName").val()) == ""){
		alert("年级名称不能为空，请填写完再提交！");
		$(".crcasName").focus();
		return false;
	}
	/*if($.trim(CKEDITOR.instances.crcseContent.getData()) == ""){
		alert("内容不能为空，请填写完再提交！");
		return false;
	}*/

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
	//var crcseContent = CKEDITOR.instances.crcseContent.getData();
	var crcasUuid = getQueryString("id");
	var crcasName = $(".crcasName").val();
	var str = 'crcasUuid='+encodeURIComponent(crcasUuid)+'&crcasName='+encodeURIComponent(crcasName);
	getOData(str,"coreClass/update/coreClass",{
		fn:function(oData){
			window.parent.refreshList();
			alert("修改成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}