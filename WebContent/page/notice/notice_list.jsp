<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>通知列表</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/themes/icon.css">
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
		      <button class="btn btn-default" type="button" id="Notice_edit" onclick="notice_edit()"><span class="glyphicon glyphicon-pencil"></span>编辑</button>
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
		<div id="Notice_div_alert"></div>
		<!-- 数据grid表格 -->
		<div class="tableGridbox">
			<div id="dataListGrid" data-options="border:false"></div>
		</div>

	</div>
</div>


<!-- 弹出窗 -->
<div class="pad19" id="notice_add" style="display: none;padding-right: 10px;padding-top: 20px;" >
<form role="form" class="form-horizontal form-valite" name="NOTICE" method="post" action="">
<input type="hidden" name="id" id="id" value="">
	<table style="width: 400px;">
		<tbody>
			<tr>
				<th style="text-align: center;font-size: 14px;">标&emsp;题：</th>
				<td><input type="text" class="form-control" name="title" id="title"></td>
			</tr>
			<tr style="height: 5px;"></tr>
			<tr>
				<th style="text-align: center;font-size: 14px;">时间段：</th>
				<td><input type="text" class="form-control" name="time_limit" id="time_limit"></td>
			</tr>
			<tr style="height: 5px;"></tr>
			<tr>
				<th style="text-align: center;font-size: 14px;">内&emsp;容：</th>
				<td><textarea cols="5" rows="5" class="form-control" name="content" id="content"></textarea></td>
			</tr>
			<tr style="height: 5px;"></tr>
		</tbody>
		<tfoot>
			<tr>
				<td></td>
				<td style="text-align: right;">
					<input type="submit" class="btn btn-primary" value="确定" id="QD_SURE"/>
		   			<input type="button" class="btn btn-info" value="取消" id="PQ_QX"/>
				</td>
			</tr>
		</tfoot>
	</table>
</form>
</div>


<script type="text/javascript">
$(function(){
	noticeListGrid();
	NoticeCheckBtn(null);
});

function noticeListGrid(){
	$('#dataListGrid').datagrid({
		method : 'get',//默认是post
		pagination:true,
		pageSize:10,
		loadMsg : '数据加载中...',
		checkOnSelect:false,
		singleSelect:true,
		view: myview,
		nowrap:false,
		fitColumns:true,
		emptyMsg: '暂无数据...',
		columns:[[
			{field:'ck',checkbox:true}, //开启多选框 必须：singleSelect : false
			//{field:'no',title:"编号",hidden:true},
			{
				field:'title',
				title:'标题',
				width:100,
				align:'center',
			    formatter:function (value, row, index){
						return"<div style=\"word-wrap:break-word;\">"+row.title  +"</div>"
					}
			},{
				field:'content',
				title:'内容',
				width:200,
				align:'center',
				formatter:function (value, row, index){
					return"<div style=\"word-wrap:break-word;\">"+isForty(row.content,35)  +"</div>"
				}
			},{
				field:'time',
				title:'发布时间',
				align:'center',
				width:70,
				formatter:function (value, row, index){
					
					return"<div style=\"word-wrap:break-word;\">"+row.time +"</div>"
				}
			}
	  	]],
		onBeforeLoad:function(){
			Search();
		},onCheck: function () {
			NoticeCheckBtn(null);
        },
        onUncheck: function () {
        	NoticeCheckBtn(null);
        },
        onCheckAll: function (rows) {
        	NoticeCheckBtn(rows);
        },
        onUncheckAll: function () {
        	NoticeCheckBtn(null);
        }
	});
}

function Search(pagenum){
	var postdata = getPostData(pagenum);
	$.post(path + '/system/notice/list',postdata, function(json) {
		var result = eval("(" + json + ")");
		if(result.status == '1'){
			AlertSuccess("Notice_div_alert",result.msg);
			$('#dataListGrid').datagrid('loadData', result.data);
			$("#dataListGrid").datagrid("loaded");
		}
		else{
			layer.msg('数据加载失败！', {icon: 2});
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
	batchDel : function (grid) {//批量删除
		
		var selRows = $(grid).datagrid('getChecked');
		
		var ids = [];
		$.each(selRows,function () {
			ids.push(this.id);
		});
		
		$.sobox.confirm('提示','你是否删除选中的'+selRows.length+'条记录？',function () {
			$.post(path+'/system/notice/del',{"id":ids[0]},function(json){
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
	$("#notice_add").show().dialog({
		title:'通知',
		closed: false,    
	    cache: false,    
	    modal: true 
	});
	$("#QD_SURE").unbind();
	$("#QD_SURE").bind("click",function(){
		var params = $(document.forms['NOTICE']).serialize();
		$.post(path +'/system/notice/save',params, function (json) {
			if(json=="true"){
				$('#notice_add').dialog('close');
				layer.msg('保存成功！', {icon: 1});
				$('#dataListGrid').datagrid('reload');
				$("#dataListGrid").datagrid("loaded");
			}else{
				$('#notice_add').dialog('close');
				layer.msg('保存失败！', {icon: 2});
			}
		});
		
	});
	$("#PQ_QX").bind("click",function(){
		$('#notice_add').dialog('close');
	});
}

function NoticeDisableEdit() {
    $("#Notice_edit").attr("disabled", true);
    $("#Notice_edit").removeAttr("onclick");
}
function NoticeEnableEdit() {
    $("#Notice_edit").attr("disabled", false);
    $("#Notice_edit").attr("onclick", "notice_edit()");
}
function NoticeCheckBtn(ckrows) {
	NoticeEnableEdit();
    var ck = null;
    if (ckrows == null) {
        ck = $("#dataListGrid").datagrid("getSelections");
    }
    else {
        ck = ckrows;
    }
    if (ck != null && ck.length > 0) {
    	if(ck.length > 1){
    		AlertWarn("Notice_div_alert", "只可编辑一条记录！");
    		NoticeDisableEdit();
    	}
    }
    else {
    	NoticeDisableEdit();
    }
}
//编辑
function notice_edit(){
	var ck = $("#dataListGrid").datagrid("getSelections");
	var id = ck[0].id;
	$("#notice_add").show().dialog({
		title:'通知',
		closed: false,    
	    cache: false,    
	    modal: true 
	});
	$.post(path+'/system/notice/getone',{"id":id},function(json){
		var result = eval("("+ json +")");
		if(result.status == "1"){
			var notice = result.data;
			$("#id").val(notice.id);
			$("#title").val(notice.title);
			$("#time_limit").val(notice.time_limit);
			$("#content").val(notice.content);
		}
	});
	$("#QD_SURE").unbind();
	$("#QD_SURE").bind("click",function(){
		var params = $(document.forms['NOTICE']).serialize();
		$.post(path +'/system/notice/save',params, function (json) {
			if(json=="true"){
				$('#notice_add').dialog('close');
				layer.msg('保存成功！', {icon: 1});
				$('#dataListGrid').datagrid('reload');
				$("#dataListGrid").datagrid("loaded");
			}else{
				$('#notice_add').dialog('close');
				layer.msg('保存失败！', {icon: 2});
			}
		});
		
	});
	$("#PQ_QX").bind("click",function(){
		$('#notice_add').dialog('close');
	});
}


$('.grid-refresh').click(function () {//刷新
	opGrid.refresh('#dataListGrid');
});
$('.grid-batchDel').click(function() {//删除
	opGrid.batchDel('#dataListGrid');
});
$('.btn-search').click(function () {//搜索重新加载grid
	opGrid.serarchReload('.form-search','#dataListGrid');
});

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