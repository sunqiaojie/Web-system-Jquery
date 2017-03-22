<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>合作意向列表</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap-theme.css">
<link rel="stylesheet" type="text/css" media="all" href="<%=path %>/resources/css/easyui-repair.css" />
<link rel="stylesheet" type="text/css" media="all" href="<%=path %>/resources/css/style.css" />
</head>
<body>
<div class="pad20">
	<nav id="searchbar" class="navbar navbar-default">
		<div class="fl pad-l10 pad-t5 btn-group">
		      <button class="btn btn-default" type="button" onclick="coopera_read()" id="Coopera_read"><span class="glyphicon glyphicon-search"></span>查看</button>
		      <button class="btn btn-default grid-batchDel" type="button"><span class="glyphicon glyphicon-remove"></span>删除</button>
		      <button class="btn btn-default grid-refresh" type="button"><span class="glyphicon glyphicon-refresh"></span>刷新</button>
	    </div>
	</nav>

	<div id="mainbox">
		<!-- 数据grid工具栏 -->
		<div id="Coopera_div_alert"></div>
		<!-- 数据grid表格 -->
		<div class="tableGridbox">
			<div id="dataListGrid" data-options="border:false"></div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(function(){
	cooperaListGrid();
	CooperaCheckBtn();
});

function cooperaListGrid(){
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
				field:'name',
				title:'联系人姓名',
				width:100,
				align:'center',
				formatter:function (value, row, index){
					return"<div style=\"word-wrap:break-word;\">"+row.name+"</div>"
				}
			},{
				field:'company',
				title:'公司名称',
				width:180,
				align:'center',
				formatter:function (value, row, index){
					return"<div style=\"word-wrap:break-word;\">"+row.company+"</div>"
				}
			},{
				field:'telephone',
				title:'联系方式',
				width:100,
				align:'center',
				formatter:function (value, row, index){
					return"<div style=\"word-wrap:break-word;\">"+row.telephone+"</div>"
				}
			},{
				field:'intention',
				title:'意向',
				width:80,
				align:'center',
				formatter:function (value, row, index){
					return"<div style=\"word-wrap:break-word;\">"+row.intention+"</div>"
				}
			},{
				field:'time',
				title:'时间',
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
			CooperaCheckBtn(null);
        },
        onUncheck: function () {
        	CooperaCheckBtn(null);
        },
        onCheckAll: function (rows) {
        	CooperaCheckBtn(rows);
        },
        onUncheckAll: function () {
        	CooperaCheckBtn(null);
        }
	});
}

function Search(pagenum){
	var postdata = getPostData(pagenum);
	$.post(path + '/system/visitorinfo/list',postdata, function(json) {
		var result = eval("(" + json + ")");
		if(result.status == '1'){
			AlertSuccess("Coopera_div_alert",result.msg);
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
				$.post(path+'/system/visitorinfo/del',{"id":ids[0]},function(json){
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
	
//查看
function coopera_read(){
	ck = $("#dataListGrid").datagrid("getSelections");
	var id = ck[0].id;
	window.location.href = path +"/page/cooperation/cooperation_read.jsp?id="+id;
}


function CooperaDisableRead() {
    $("#Coopera_read").attr("disabled", true);
    $("#Coopera_read").removeAttr("onclick");
}
function CooperaEnableRead() {
    $("#Coopera_read").attr("disabled", false);
    $("#Coopera_read").attr("onclick", "coopera_read()");
}

function CooperaCheckBtn(ckrows) {
	CooperaEnableRead();
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
    		CooperaDisableRead();
    	}
    }
    else {
    	CooperaDisableRead();
    }
}
//刷新
$('.grid-refresh').click(function () {
	opGrid.refresh('#dataListGrid');
});
//删除
$('.grid-batchDel').click(function() {
	opGrid.batchDel('#dataListGrid');
})
</script>
</body>
</html>