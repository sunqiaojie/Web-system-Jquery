<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE html>
<html>
<head id="Head1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>列表页-后台管理系统</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap-theme.css">
<link rel="stylesheet" type="text/css" media="all" href="<%=path %>/resources/css/easyui-repair.css" />
<link rel="stylesheet" type="text/css" media="all" href="<%=path %>/resources/css/style.css" />
</head>
<body>

<div class="pad20">

<!-- 	<p class="grid-toolbar">
		<button class="btn btn-sm btn-default" type="button">增加</button>
		<button class="btn btn-sm btn-primary" type="button">编辑</button>
		<button class="btn btn-sm btn-info" type="button">快速编辑</button>
		<button class="btn btn-sm btn-danger" type="button">删除</button>
	</p> -->

	<nav id="searchbar" class="navbar navbar-default">
		<div class="fl pad-l10 pad-t5 btn-group">
		      <button class="btn btn-default grid-addNew" type="button"><span class="glyphicon glyphicon-plus"></span>增加</button>
		      <!-- <button class="btn btn-default grid-edit" type="button"><span class="glyphicon glyphicon-pencil"></span>编辑</button> -->
		      <button class="btn btn-default grid-batchDel" type="button"><span class="glyphicon glyphicon-remove"></span>删除</button>
		      <button class="btn btn-default grid-refresh" type="button"><span class="glyphicon glyphicon-refresh"></span>刷新</button>
			<div class="btn-group">

			<button data-toggle="dropdown" class="btn btn-default dropdown-toggle" type="button" id="btnGroupDrop1" aria-expanded="false">其他<span class="caret"></span></button>
				<ul aria-labelledby="btnGroupDrop1" role="menu" class="dropdown-menu">
					<li><a href="#"><span class="glyphicon glyphicon-asterisk"></span>快速编辑</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-upload"></span>快速编辑</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-flag"></span>快速编辑</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-book"></span>快速编辑</a></li>
				</ul>
			</div>
	    </div>

		<div class="fr pad-r10 pad-t5">
			<form role="form" class="form-inline form-search">
				<div class="form-group">
					<div class="input-group">
						<select class="form-control input-sm" name="sel">
							<option value="1">选项1</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<input type="text" name="date" placeholder="请选择日期" class="form-control input-sm hk-date">
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<input type="text" name="email" placeholder="Enter email" class="form-control input-sm">
						<div class="input-group-addon">@</div>
					</div>
				</div>
				<div class="form-group">
					<label for="exampleInputEmail2" class="sr-only">关键词</label>
					<input type="text" name="key" placeholder="关键词" id="exampleInputEmail2" class="form-control input-sm">
				</div>
				<input class="btn btn-sm btn-primary btn-search" type="button" value="搜 索" />
				<button type="button" class="btn btn-sm btn-default" data-toggle="collapse" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">高级搜索<span class="caret"></span></button>
			</form>
		</div>
	</nav>

	<div id="collapseOne" class="searchmore panel-collapse collapse mag-b10" role="tabpanel">
		<div class="panel-body bg-warning">
			<form class="form-moeSearch">
				<div class="row form-group">
					<div class="col-md-4">
						<label class="col-sm-4 control-label right">订单号：</label><span class="col-sm-8"><input type="text" name="no" placeholder="" class="form-control input-sm"></span>
					</div>
					<div class="col-md-8">
						<label class="col-sm-2 control-label right">下单时间：</label><span class="col-sm-4"><input type="text" name="starttime" placeholder="" class="form-control input-sm hk-time"></span><span class="col-sm-1">-</span><span class="col-sm-4"><input type="text" name="endtime" placeholder="" class="form-control input-sm hk-time"></span>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-md-4">
						<label class="col-sm-4 control-label right">付款状态：</label><span class="col-sm-8"><input type="text" name="booktype" placeholder="" class="form-control input-sm"></span>
					</div>
					<div class="col-md-4">
						<label class="col-sm-4 control-label right">配送方式：</label><span class="col-sm-8"><input type="text" name="deliverystyle" placeholder="" class="form-control input-sm"></span>
					</div>
					<div class="col-md-4">
						<label class="col-sm-4 control-label right">支付方式：</label><span class="col-sm-8"><input type="text" name="paystyle" placeholder="" class="form-control input-sm"></span>
					</div>
				</div>
				<div class="row center">
					<input class="btn btn-primary btn-moreSearch" type="button" value="搜 索" />
				</div>
			</form>
		</div>
	</div>

	<div id="mainbox">
		<!-- 数据grid工具栏 -->

		<!-- 数据grid表格 -->
		<div class="tableGridbox">
			<div id="dataListGrid" data-options="border:false"></div>
		</div>

	</div>
</div>


<!-- 弹出窗 -->
<div class="easyui-dialog popBox" title="编辑信息" style="width:360px;height:230px;" data-options="iconCls:'icon-edit',resizable:true,closable:false,closed:true,cache: false,modal: true">
	<form id="form-editbox" class="popForm pad15" method="post">
		<div class="fluidbox"><label class="lab-item">序号：</label><input type="text" class="txt easyui-validatebox"  data-options="required:true" name="code" /></div>
		<div class="fluidbox"><label class="lab-item">名称：</label><input type="text" class="txt easyui-validatebox"  data-options="required:true" name="name" /></div>
		<div class="fluidbox"><label class="lab-item">地址：</label><input type="text" class="txt easyui-validatebox"  data-options="required:true" name="addr" /></div>
		<div class="fluidbox"><label class="lab-item">第四列：</label><input type="text" class="txt easyui-validatebox"  data-options="required:true" name="col4" /></div>
		<p class="p-item p-btn"><a href="#" class="easyui-linkbutton a-popSubmit" data-options="iconCls:'icon-save'">确定</a> <a id="btn" href="#" class="easyui-linkbutton a-popClose" data-options="iconCls:'icon-cancel'">取消</a></p>
	</form>
</div>


<script type="text/javascript" src="<%=path %>/resources/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/resources/js/my97/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path %>/resources/js/jquery.easyui.ext.js"></script>
<script type="text/javascript" src="<%=path %>/resources/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path %>/resources/js/base.js"></script>
<script type="text/javascript" src="<%=path %>/resources/js/tools.js"></script>
<script type="text/javascript" src="<%=path %>/resources/js/runing.js"></script>

<script type="text/javascript">

$('#dataListGrid').datagrid({
	//title:'DataGrid',
	url:'json.json',
	 //url:'js/datagrid_data_empty.json',
	method : 'get',//默认是post
	//width:700,
	// height:500,
	//fitColumns:true,
	pagination:true,
	//singleSelect : true,
	pageSize:10,
	loadMsg : '数据加载中...',
	view: myview,
	emptyMsg: '暂无数据...',
	columns:[[
		{field:'ck',checkbox:true}, //开启多选框 必须：singleSelect : false
		//{field:'no',title:"编号",hidden:true},
		{field:'no',title:'设备存放位置',width:100,align:'center'},
		{field:'color',title:'负责人',width:100},
		{field:'price',title:'联系电话',width:100,align:'center'},
		{field:'arriveNumber',title:'设备数量',width:100,align:'center'},
		{field:'size',title:'工作备注',width:100,align:'center'},
		{field:'op',title:'操作',width:150,align:'center',formatter : function (value,row,index) {
			var no = row.no;
			return '<a class="op-grid" data-opt={"no":'+no+'} href="#"><span class="glyphicon glyphicon-search"></span>查看</a><a class="op-grid" data-opt={"no":'+no+'} href="#"><span class="glyphicon glyphicon-pencil"></span>修改</a><a class="op-grid" data-opt={"no":'+no+'} href="#"><span class="glyphicon glyphicon-remove"></span>删除</a>';
		//return value;
		}}
  	]],
	onClickRow:function (rowIndex, rowData) {
		//nowSel = rowData;
	},
	onLoadSuccess : function () {

	}
});

var opGrid = {
	refresh : function (grid) {//刷新
		$(grid).datagrid('reload');
	},
	batchDel : function (grid) {//批量删除
		var selRows = $(grid).datagrid('getChecked');
		window.console && console.log(selRows);
		var ids = [];
		$.each(selRows,function () {
			ids.push(this.no);
		});
		window.console && console.log(ids);
		$.sobox.confirm('提示','你是否删除选中的'+selRows.length+'条记录？',function () {
			$.sobox.alert('提示','通过ajax请求删除记录成功！');
		});
	},
	addNew : function () {

	},
	serarchReload : function (form,grid) {//搜索重新加载grid
		var searchData = $(form).serializeObject();
		$(grid).datagrid('load',searchData);
	}

}


/* 增加 */
$('.grid-addNew').click(function () {
	$.messager.alert('提示','添加操作');
});

$('.grid-batchDel').click(function() {//批量删除
	opGrid.batchDel('#dataListGrid');
});

$('.grid-refresh').click(function () {//刷新
	opGrid.refresh('#dataListGrid');
});

$('.btn-search').click(function () {//搜索重新加载grid
	opGrid.serarchReload('.form-search','#dataListGrid');
});

$('.btn-moreSearch').click(function() {//高级搜索重新加载grid
	opGrid.serarchReload('.form-moeSearch','#dataListGrid');
});

/* 删除 */
$('.grid-del').click(function () {
	if (nowSel) {//是否选择行
		$.sobox.confirm('提示','你确认删除这些记录？')
		$.messager.confirm('请确认','你确认删除此记录？',function(r){//confirm 是否删除
			if (r){
				$.messager.show({//删除提示
					title:'确认',
					msg:'你已删除此记录，提示消息5秒后消失。',
					timeout:5000,
					showType:'slide'
				});
			}
		});
	}else {//没有选择行
		$.messager.alert('提示','请选择操作行');
	}
});

/* 快速编辑 */
	function girdQuickEdit() {
		if (nowSel) {
			$('.popBox').window('open');
			$('#form-editbox').form('clear').form('setVal',nowSel);
		}else{
			$.messager.alert('提示','请选择操作行');
		}
	}

	$('.a-popSubmit').click(function () {//弹出框提交
		$('#form-editbox').submit();
		return false;
	});

	$('.a-popClose').click(function () {//弹出框关闭
		//$(this).parents('.popForm').form('clear');
		$(this).parents('.popBox').window('close');
		return false;
	});




</script>

</body>
</html>