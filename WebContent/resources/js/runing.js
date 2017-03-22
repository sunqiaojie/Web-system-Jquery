
/* 分页函数 */
// function pagerFilter(data){
// 	if (typeof data.length == 'number' && typeof data.splice == 'function'){	// is array
// 		data = {
// 			total: data.length,
// 			rows: data
// 		}
// 	}
// 	var dg = $(this);
// 	var opts = dg.datagrid('options');
// 	var pager = dg.datagrid('getPager');
// 	pager.pagination({
// 		onSelectPage:function(pageNum, pageSize){
// 			opts.pageNumber = pageNum;
// 			opts.pageSize = pageSize;
// 			pager.pagination('refresh',{
// 				pageNumber:pageNum,
// 				pageSize:pageSize
// 			});
// 			dg.datagrid('loadData',data);
// 		}
// 	});
// 	if (!data.originalRows){
// 		data.originalRows = (data.rows);
// 	}
// 	var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
// 	var end = start + parseInt(opts.pageSize);
// 	data.rows = (data.originalRows.slice(start, end));
// 	return data;
// }

var soPro = soPro ||{}
soPro.runing = {
	init : function () {
		var me = this;
		me.initDateAndTime();//加载日历
	},
	initDateAndTime : function () {//初始化日期和时间下拉框
		if ($('.hk-date').length) {
			$('.hk-date').addClass('Wdate').each(function(){
				//window.console && console.log(this);
				$(this).click(function(){
				    //window.console && console.log(this); 
				    var fmt = new Object();
				    fmt.dateFmt = 'yyyy-MM-dd';
				    fmt.readOnly = true; 
				    var data =  $T.data(this)||{};
				    data = $.extend(fmt, data); 
					//window.console && console.log(data);
				    WdatePicker(data);
				     
				});
			});
		};
		if ($('.hk-time').length) {
			$('.hk-time').addClass('Wdate').css('width',140).each(function(){
				$(this).click(function(){
					var data = $T.data(this)||{};
					data = $.extend({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true},data);
					WdatePicker(data);
				});
			});
		};
	}

}

$(function () {
	soPro.runing.init();
});


