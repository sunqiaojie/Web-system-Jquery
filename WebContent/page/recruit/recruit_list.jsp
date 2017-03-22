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
		      <button class="btn btn-default" type="button" onclick="recruit_add()"><span class="glyphicon glyphicon-plus"></span>增加</button>
		      <button class="btn btn-default" type="button" id="Recruit_edit" onclick="recruit_edit()"><span class="glyphicon glyphicon-pencil"></span>编辑</button>
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
 		<div id="Recruit_div_alert"></div>
		<!-- 数据grid表格 -->
		<div class="tableGridbox">
			<div id="dataListGrid" data-options="border:false"></div>
		</div>

	</div>
</div>

<script type="text/javascript">
$(function(){
	recruitListGrid();
	RecruitCheckBtn(null); 
});
function recruitListGrid(){
	$('#dataListGrid').datagrid({
		method : 'get',//默认是post
		pagination:true,
		pageSize:10,
		loadMsg : '数据加载中...',
		checkOnSelect :false,
		singleSelect:true,
		view: myview,
		fitColumns:true,
		emptyMsg: '暂无数据...',
		columns:[[
			{field:'ck',checkbox:true}, //开启多选框 必须：singleSelect : false
			//{field:'no',title:"编号",hidden:true},
			{
				field:'quarters_name',
				title:'岗位名称',
				width:100,
				align:'center',
				formatter:function (value, row, index){
					return"<div style=\"word-wrap:break-word;\">"+row.quarters_name  +"</div>"
				}
			},{
				field:'degree',
				title:'学历',
				width:100,
				align:'center',
				formatter:function (value, row, index){
					return"<div style=\"word-wrap:break-word;\">"+row.degree+"</div>"
				}
			},{
				field:'ispublish',
				title:' 是否发布',
				width:100,
				align:'center',
				formatter:function (value, row, index){
					var html = "";
					if(row.ispublish == "1"){
						html = "发布";
					}else{
						html = "不发布";
					}
					return"<div style=\"word-wrap:break-word;\">"+html+"</div>"
				}
			},{
				field:'time',
				title:'发布时间',
				align:'center',
				width:100,
				formatter:function (value, row, index){
					return"<div style=\"word-wrap:break-word;\">"+FormatDate2String(new Date(row.time.time))+"</div>"
				}
			}
	  	]],
	  	onBeforeLoad:function(){
			Search();
		},onCheck: function () {
			RecruitCheckBtn(null);
        },
        onUncheck: function () {
        	RecruitCheckBtn(null);
        },
        onCheckAll: function (rows) {
        	RecruitCheckBtn(rows);
        },
        onUncheckAll: function () {
        	RecruitCheckBtn(null);
        }
	});
}
function Search(pagenum){
	var postdata = getPostData(pagenum);
	$.post(path + '/system/recruit/list',postdata, function(json) {
		var result = eval("(" + json + ")");
		if(result.status == '1'){
			AlertSuccess("Recruit_div_alert",result.msg);
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
	batchDel : function (grid) {//删除
		var selRows = $(grid).datagrid('getChecked');
		
		var ids = [];
		$.each(selRows,function () {
			ids.push(this.id);
		});
		$.sobox.confirm('提示','你是否删除选中的'+selRows.length+'条记录？',function () {
			$.post(path+'/system/recruit/del',{"id":ids[0]},function(json){
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
$('.grid-refresh').click(function () {//刷新
	opGrid.refresh('#dataListGrid');
});
$('.grid-batchDel').click(function() {//删除
	opGrid.batchDel('#dataListGrid');
})
$('.btn-search').click(function () {//搜索重新加载grid
	opGrid.serarchReload('.form-search','#dataListGrid');
});

/* 增加 */
function recruit_add(){
	window.location.href = path +"/page/recruit/recruit_edit.jsp";
}

function RecruitDisableEdit() {
    $("#Recruit_edit").attr("disabled", true);
    $("#Recruit_edit").removeAttr("onclick");
}
function RecruitEnableEdit() {
    $("#Recruit_edit").attr("disabled", false);
    $("#Recruit_edit").attr("onclick", "recruit_edit()");
}

function RecruitCheckBtn(ckrows) {
	RecruitEnableEdit();
    var ck = null;
    if (ckrows == null) {
        ck = $("#dataListGrid").datagrid("getSelections");
    }
    else {
        ck = ckrows;
    }
    if (ck != null && ck.length > 0) {
    	if(ck.length > 1){
    		AlertWarn("Recruit_div_alert", "只可编辑一条记录！");
    		RecruitDisableEdit();
    	}
    }
    else {
    	RecruitDisableEdit();
    }
}


//编辑
function recruit_edit(){
	ck = $("#dataListGrid").datagrid("getSelections");
	var id = ck[0].id;
	window.location.href = path +"/page/recruit/recruit_edit.jsp?id="+id;
}

</script>

</body>
</html>