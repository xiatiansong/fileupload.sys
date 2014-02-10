var setSelect = function(id,key,value){
	$("#"+id).append("<option value='" + key + "'>" + value + "</option>");
};

var selected = function(id,key){
	$("#"+id+" option").each(function () {
       var txt = $(this).val(); //获取单个text
       if(txt == key){
		 	$(this).attr("selected",true);return;
		 }else{
		 	$(this).attr("selected",false);
		 }
    });
};