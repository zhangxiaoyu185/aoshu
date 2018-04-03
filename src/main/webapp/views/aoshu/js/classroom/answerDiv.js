var div_selected=null;
var answerStr="";

function answerBtnPosition() {
	// $('body').on('click','.div_noSelect',function(){
	$('body').on('click','.div_select',function(){
	 	$('body').find(".div_selected").removeClass("div_selected");
	 	$(this).children().eq(0).addClass("div_selected");

	 	div_selected = $(this).children().eq(0);

	 	div_selected = $('body').find(".div_selected");
	 	answerStr = "";
	});

}

function answer(str){
	if(!div_selected){
		alert("请选择答题位置");
		return;
	}
	answerStr += str;
	div_selected.html(answerStr);
}

function claer() {
	answerStr = "";
	div_selected.html("");
}
