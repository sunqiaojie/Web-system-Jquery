<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>通知列表</title>
<%-- <link rel="stylesheet" type="text/css" href="<%=path %>/resources/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/themes/icon.css"> --%>
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap-theme.css">
<link rel="stylesheet" type="text/css" media="all" href="<%=path %>/resources/css/easyui-repair.css" />
<link rel="stylesheet" type="text/css" media="all" href="<%=path %>/resources/css/style.css" />
</head>

<body>
<div class="pad20">

	<nav id="searchbar" class="navbar navbar-default">
		<div class="fl pad-l10 pad-t5 btn-group">
		      <button class="btn btn-default" type="button" onclick="notice_add()"><span class="glyphicon glyphicon-plus"></span>增加</button>
		      <button class="btn btn-default" type="button" onclick="news_edit()" id="News_edit"><span class="glyphicon glyphicon-pencil"></span>编辑</button>
		      <button class="btn btn-default grid-batchDel" type="button"><span class="glyphicon glyphicon-remove"></span>删除</button>
		      <button class="btn btn-default grid-refresh" type="button"><span class="glyphicon glyphicon-refresh"></span>刷新</button>
	    </div>

		<!-- <div class="fr pad-r10 pad-t5">
			<form role="form" class="form-inline form-search">
				<div class="form-group">
					<label for="exampleInputEmail2" class="sr-only">关键词</label>
					<input type="text" name="key" placeholder="关键词" id="exampleInputEmail2" class="form-control input-sm">
				</div>
				<input class="btn btn-sm btn-primary btn-search" type="button" value="搜 索" />
			</form>
		</div> -->
	</nav>

	<div id="mainbox">
		<!-- 数据grid工具栏 -->
		<div id="News_div_alert"></div>
		<!-- 数据grid表格 -->
		<div class="tableGridbox">
			<div id="dataListGrid" data-options="border:false"></div>
		</div>

	</div>
</div>


<script type="text/javascript">
$(function(){
	newsListGrid();
	NewsCheckBtn();
});
function newsListGrid(){
	$('#dataListGrid').datagrid({
		method : 'get',//默认是post
		pagination:true,
		pageSize:10,
		loadMsg : '数据加载中...',
		view: myview,
		nowrap:false,
		singleSelect:true,
		fitColumns:true,
		emptyMsg: '暂无数据...',
		columns:[[
			{field:'ck',checkbox:true}, //开启多选框 必须：singleSelect : false
			//{field:'no',title:"编号",hidden:true},
			{
				field:'title',
				title:'新闻标题',
				width:140,
				align:'center',
				formatter:function (value, row, index){
					return"<div style=\"word-wrap:break-word;\">"+row.title+"</div>"
				}
			},{
				field:'content',
				title:'内容',
				width:180,
				align:'center',
				formatter:function (value, row, index){
					return"<div style=\"word-wrap:break-word;\">"+isForty(row.content,50)+"</div>"
				}
			},{
				field:'news_source',
				title:'来源',
				width:130,
				align:'center',
				formatter:function (value, row, index){
					return"<div style=\"word-wrap:break-word;\">"+row.news_source+"</div>"
				}
			},{
				field:'time',
				title:'发布时间',
				width:70,
				align:'center',
				formatter:function (value, row, index){
					return"<div style=\"word-wrap:break-word;\">"+FormatDate2String(new Date(row.time.time))+"</div>"
				}
			}
	  	]],
	  	onBeforeLoad:function(){
			Search();
		},onCheck: function () {
			NewsCheckBtn(null);
        },
        onUncheck: function () {
        	NewsCheckBtn(null);
        },
        onCheckAll: function (rows) {
        	NewsCheckBtn(rows);
        },
        onUncheckAll: function () {
        	NewsCheckBtn(null);
        }
	});
}
function Search(pagenum){
	var postdata = getPostData(pagenum);
	$.post(path + '/system/news/list',postdata, function(json) {
		var result = eval("(" + json + ")");
		if(result.status == '1'){
			AlertSuccess("News_div_alert",result.msg);
			$('#dataListGrid').datagrid('loadData', result.data);
			$("#dataListGrid").datagrid("loaded");
		}
		else{
			p_layer.msg('数据加载失败！', {icon: 2});
		} 
	});
}
function getPostData(pagenum){
	var opts = $('#dataListGrid').datagrid('options');
	var page = 1;
	if (pagenum != null) {
		page = pagenum;
    }else{
    	page = opts.pageNumber;
    }
    var rows = opts.pageSize;
    var order = opts.sortOrder;
    var sort = opts.sortName;
	$($('#dataListGrid').datagrid('getPager')).pagination({ pageNumber: page });
	var param ="";
	var param_form = "";
	if(sort==null){
	  	sort = "";
	  }
	//param_form +="&AJBH="+$("#ajbh").val();
	
	param = param_form+"&page=" + page+"&rows="+rows+"&order="+ order+"&sort="+sort;
    return param;
}


var opGrid = {
	refresh : function (grid) {//刷新
		$(grid).datagrid('reload');
	},
	batchDel : function (grid) {//删除
		var selRows = $(grid).datagrid('getChecked');
		var ids = [];
		$.each(selRows,function () {
			ids.push(this.id);
		});
		$.sobox.confirm('提示','你是否删除选中的'+selRows.length+'条记录？',function () {
			$.post(path+'/system/news/del',{"id":ids[0]},function(json){
				var result = eval("("+json+")");
				if(result.status =='1'){
					$.sobox.alert('提示','删除记录成功！');
					opGrid.refresh('#dataListGrid');
				}else{
					$.sobox.alert('提示','删除失败，请重试！');
				}
			});
		});
	},
	serarchReload : function (form,grid) {//搜索重新加载grid
		var searchData = $(form).serializeObject();
		$(grid).datagrid('load',searchData);
	}

}


/* 增加 */
function notice_add(){
	window.location.href = path + "/page/news/news_edit.jsp";
}
function NewsDisableEdit() {
    $("#News_edit").attr("disabled", true);
    $("#News_edit").removeAttr("onclick");
}
function NewsEnableEdit() {
    $("#News_edit").attr("disabled", false);
    $("#News_edit").attr("onclick", "news_edit()");
}

function NewsCheckBtn(ckrows) {
	NewsEnableEdit();
    var ck = null;
    if (ckrows == null) {
        ck = $("#dataListGrid").datagrid("getSelections");
    }
    else {
        ck = ckrows;
    }
    if (ck != null && ck.length > 0) {
    	if(ck.length > 1){
    		AlertWarn("News_div_alert", "只可编辑一条记录！");
    		NewsDisableEdit();
    	}
    }
    else {
    	NewsDisableEdit();
    }
}

$('.grid-refresh').click(function () {//刷新
	opGrid.refresh('#dataListGrid');
});
$('.grid-batchDel').click(function() {//删除
	opGrid.batchDel('#dataListGrid');
})
$('.btn-search').click(function () {//搜索重新加载grid
	opGrid.serarchReload('.form-search','#dataListGrid');
});

//编辑
function news_edit(){
	ck = $("#dataListGrid").datagrid("getSelections");
	var id = ck[0].id;
	window.location.href = path +"/page/news/news_edit.jsp?id="+id;
}

function isForty(str,length){
	var strlength = str.length;
	var html = str;
	var num = 40;
	if(length != null){
		num = parseInt(length);
	}
	if(strlength >= num){
		html = str.substr(0, num);
		html +="...";
	}
	return html;
}
</script>

</body>
</html>