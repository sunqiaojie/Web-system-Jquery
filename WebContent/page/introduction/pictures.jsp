<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公司相册</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap-theme.css">
<link rel="stylesheet" type="text/css" media="all" href="<%=path %>/resources/css/easyui-repair.css" />
<link rel="stylesheet" type="text/css" media="all" href="<%=path %>/resources/css/style.css" />
<script type="text/javascript" src="<%=path%>/resources/js/ajaxfileupload/ajaxfileupload.js"></script>
</head>
<body>
	<div class="pad20">
		<nav id="searchbar" class="navbar navbar-default">
		<div class="fl pad-l10 pad-t5 btn-group">
			<button class="btn btn-default" type="button" onclick="pic_add()">
				<span class="glyphicon glyphicon-plus"></span>增加
			</button>
			<button class="btn btn-default" type="button" onclick="pic_edit()" id="Pic_edit">
				<span class="glyphicon glyphicon-pencil"></span>编辑
			</button>
			<button class="btn btn-default grid-batchDel" type="button">
				<span class="glyphicon glyphicon-remove"></span>删除
			</button>
			<button class="btn btn-default grid-refresh" type="button">
				<span class="glyphicon glyphicon-refresh"></span>刷新
			</button>
		</div>
		<div class="fr pad-r10 pad-t5">
			<form role ="form" class="form-inline form-search">
				<div class="form-group">
					<label for="exampleInputEmail2" class="sr-only">关键词</label> <input
						type="text" name="key" placeholder="关键词" id="exampleInputEmail2"
						class="form-control input-sm">
				</div>
				<input class="btn btn-sm btn-primary btn-search" type="button" value="搜 索" />
			</form>
		</div>
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
<!-- 新增图片 -->
<div id="picUp" style="display: none;padding-top: 10px;">	
 <div class="col-sm-12">
 	<%-- <div class="col-sm-6">
		<div class="panel panel-info">
			<div id="img" style="width: 200px;height: 200px">
			<img id="imgHead" width="200" height="200" alt="图片" src="<%=path %>/system/picture/readImage">   
			</div>
		</div>
	</div> --%>
	<div class="col-sm-12" style="padding-top: 25px;">
		<div class="panel panel-info" >
			<h5 class="panel-heading">上传图片</h5>
			<div class="panel-body">
				<input type="file" name="SC_file" class="form-control" id="SC_file" onchange="picShow(this)"/>
			</div>
			<div style="margin: 5px 20px;">
				<label>图片名称：</label>
				<input type="text" name="activity_name" id="activity_name">
			</div>
			<div style="margin: 5px 20px;">
				是否显示：
				<input type="radio" name="isshow" id="isshow_yes" value="1" checked="checked"/>是
				<input type="radio" name="isshow" id="isshow_no" value="0"/>否
			</div>
		</div>
		<div class="panel panel-body" style="text-align: right;">
			<button type="button" class="btn btn-primary" id="uploadFile1">上传</button>
		</div>
	</div>
</div>
</div>

<!-- 编辑图片 -->
<div class="pad19" id="pic_edit" style="display: none;padding-right: 10px;padding-top: 20px;" >
<form role="form" class="form-horizontal form-valite" name="PIC" method="post" action="">
<input type="hidden" name="id" id="id_e" value="">
	<table style="width: 400px;">
		<tbody>
			<tr>
				<th style="text-align: center;font-size: 14px;">图片名称：</th>
				<td><label id="name_e"></label></td>
			</tr>
			<tr>
				<th style="text-align: center;font-size: 14px;">图片主题：</th>
				<td><input type="text" class="form-control" name="activity_name" id="activity_name_e"></td>
			</tr>
			<tr style="height: 5px;"></tr>
			<tr>
				<th style="text-align: center;font-size: 14px;">是否显示：</th>
				<td>
					<input type="radio" name="isshow" id="isshow_yes_e" value="1" checked="checked"/>是
					<input type="radio" name="isshow" id="isshow_no_e" value="0"/>否
				</td>
			</tr>
			<tr style="height: 5px;"></tr>
		</tbody>
		<tfoot>
			<tr>
				<td></td>
				<td style="text-align: right;">
					<input type="button" class="btn btn-primary" value="确定" id="QD_SURE" onclick="editPic()"/>
		   			<input type="button" class="btn btn-info" value="取消" id="PQ_QX"/>
				</td>
			</tr>
		</tfoot>
	</table>
</form>
</div>
	<script type="text/javascript">
		$(function() {
			pictureListGrid();
			PictureCheckBtn();
		});
		function pictureListGrid() {
			$('#dataListGrid').datagrid(
							{
								method : 'get',//默认是post
								pagination : true,
								pageSize : 10,
								loadMsg : '数据加载中...',
								view : myview,
								nowrap : false,
								fitColumns : true,
								emptyMsg : '暂无数据...',
								columns : [ [
										{
											field : 'ck',
											checkbox : true
										}, //开启多选框 必须：singleSelect : false
										//{field:'no',title:"编号",hidden:true},
										{
											field : 'pic',
											title : '图片',
											width : 80,
											align : 'center',
											formatter : function(value, row,index) {
												return '<div style="word-wrap:break-word;"><img id="imgHead" width="80" height="80" alt="头像" src="<%=path %>/system/picture/readImage?id='+ row.id +'" onclick="showPic(this.src)">'
														+'</div>';
											}
										},
										{
											field : 'activity_name',
											title : '图片主题',
											width : 170,
											align : 'center',
											formatter : function(value, row,index) {
												return "<div style=\"word-wrap:break-word;\">"
														+ row.activity_name + "</div>";
											}
										},
										{
											field : 'name',
											title : '图片名称',
											width : 170,
											align : 'center',
											formatter : function(value, row,index) {
												return "<div style=\"word-wrap:break-word;\">"
														+ row.name + "</div>";
											}
										},
										{
											field : 'path',
											title : '路径',
											width : 100,
											align : 'center',
											formatter : function(value, row,
													index) {
												return "<div style=\"word-wrap:break-word;\">"
														+ row.path
														+ "</div>";
											}
										},
										{
											field : 'time',
											title : '发布时间',
											width : 70,
											align : 'center',
											formatter : function(value, row,
													index) {
												return "<div style=\"word-wrap:break-word;\">"
														+ FormatDate2String(new Date(row.time.time))
														+ "</div>";
											}
										} ] ],
								onBeforeLoad : function() {
									Search();
								},
								onCheck : function() {
									PictureCheckBtn(null);
								},
								onUncheck : function() {
									PictureCheckBtn(null);
								},
								onCheckAll : function(rows) {
									PictureCheckBtn(rows);
								},
								onUncheckAll : function() {
									PictureCheckBtn(null);
								}
							});
		}
		function Search(pagenum) {
			var postdata = getPostData(pagenum);
			$.post(path + '/system/picture/list', postdata, function(json) {
				var result = eval("(" + json + ")");
				if (result.status == '1') {
					AlertSuccess("News_div_alert", result.msg);
					$('#dataListGrid').datagrid('loadData', result.data);
					$("#dataListGrid").datagrid("loaded");
				} else {
					p_layer.msg('数据加载失败！', {
						icon : 2
					});
				}
			});
		}
		function getPostData(pagenum) {
			var opts = $('#dataListGrid').datagrid('options');
			var page = 1;
			if (pagenum != null) {
				page = pagenum;
			} else {
				page = opts.pageNumber;
			}
			var rows = opts.pageSize;
			var order = opts.sortOrder;
			var sort = opts.sortName;
			$($('#dataListGrid').datagrid('getPager')).pagination({
				pageNumber : page
			});
			var param = "";
			var param_form = "";
			if (sort == null) {
				sort = "";
			}
			//param_form +="&AJBH="+$("#ajbh").val();

			param = param_form + "&page=" + page + "&rows=" + rows + "&order="
					+ order + "&sort=" + sort;
			return param;
		}

		var opGrid = {
			refresh : function(grid) {//刷新
				$(grid).datagrid('reload');
			},
			batchDel : function(grid) {//删除
				var selRows = $(grid).datagrid('getChecked');
				var ids = [];
				$.each(selRows, function() {
					ids.push(this.id);
				});
				$.sobox.confirm('提示', '你是否删除选中的' + selRows.length + '条记录？',
						function() {
							$.post(path + '/system/picture/del', {
								"id" : ids[0]
							}, function(json) {
								var result = eval("(" + json + ")");
								if (result.status == '1') {
									$.sobox.alert('提示', '删除记录成功！');
									opGrid.refresh('#dataListGrid');
								} else {
									$.sobox.alert('提示', '删除失败，请重试！');
								}
							});
						});
			},
			serarchReload : function(form, grid) {//搜索重新加载grid
				var searchData = $(form).serializeObject();
				$(grid).datagrid('load', searchData);
			}

		}

		function NewsDisableEdit() {
			$("#Pic_edit").attr("disabled", true);
			$("#Pic_edit").removeAttr("onclick");
		}
		function NewsEnableEdit() {
			$("#Pic_edit").attr("disabled", false);
			$("#Pic_edit").attr("onclick", "pic_edit()");
		}

		function PictureCheckBtn(ckrows) {
			NewsEnableEdit();
			var ck = null;
			if (ckrows == null) {
				ck = $("#dataListGrid").datagrid("getSelections");
			} else {
				ck = ckrows;
			}
			if (ck != null && ck.length > 0) {
				if (ck.length > 1) {
					AlertWarn("News_div_alert", "只可编辑一条记录！");
					NewsDisableEdit();
				}
			} else {
				NewsDisableEdit();
			}
		}

		$('.grid-refresh').click(function() {//刷新
			opGrid.refresh('#dataListGrid');
		});
		$('.grid-batchDel').click(function() {//删除
			opGrid.batchDel('#dataListGrid');
		})
		$('.btn-search').click(function() {//搜索重新加载grid
			opGrid.serarchReload('.form-search', '#dataListGrid');
		});

		
/* 增加 */
function pic_add(){
	$("#picUp").show().dialog({
		title:'图片上传',
		closed: false,    
	    cache: false,    
	    modal: true
	});
	$("#uploadFile1").unbind();
	$("#uploadFile1").bind("click",function(){
		var filestr = $("#SC_file").val();
		var rad = $("input[type='radio']:checked").val();
		var activity_name = $("#activity_name").val();
		if(filestr){
			$.ajaxFileUpload
		    ({
		            url:path+'/system/picture/save',
		            secureuri:false,
		            fileElementId:'SC_file',
		            dataType: 'json',
		            data : {"activity_name":activity_name,"isshow":rad},
		            success: function (json){
		            	$('#picUp').dialog('close');
		            	layer.msg(json.msg, {icon: 1});
						$('#dataListGrid').datagrid('reload');
						$("#dataListGrid").datagrid("loaded");
		            }
		    });
		}else{$.messager.alert('提示',"请上传图片!",'info');}
	});
}

//编辑
function pic_edit(){
	var ck = $("#dataListGrid").datagrid("getSelections");
	var id = ck[0].id;
	$("#pic_edit").show().dialog({
		title:'图片上传',
		closed: false,    
	    cache: false,    
	    modal: true,
	    onBeforeOpen:function(){
	    	$.ajax({
	    		url: path+'/system/picture/getone',
	    		async: false,
	    		data:{"id":id},
	    		success : function(json){
	    			var result = eval("("+ json +")");
		    		if(result.status == "1"){
		    			var pic = result.data;
		    			$("#id_e").val(pic.id);
		    			$("#activity_name_e").val(pic.activity_name);
		    			$("#name_e").text(pic.name);
		    			
		    			if(pic.isshow == '1'){
		    				$("#isshow_yes_e").attr('checked','checked');
		    			}else if(pic.isshow == '0'){
		    				$("#isshow_no_e").attr('checked','checked');
		    			}
		    		}
	    		}
	    		});
	    }
	});
}
function editPic(){
	var params = $(document.forms['PIC']).serialize();
	$.post(path +'/system/picture/edit',params, function (json) {
		var result = eval("("+json+")");
		if(result.status=="1"){
			$('#pic_edit').dialog('close');
			p_layer.msg('保存成功！', {icon: 1});
			$('#dataListGrid').datagrid('reload');
			$("#dataListGrid").datagrid("loaded");
		}else{
			$('#pic_edit').dialog('close');
			p_layer.msg('保存失败！', {icon: 2});
		}
	});
}

$("#PQ_QX").bind("click",function(){
	$('#pic_edit').dialog('close');
});
	
function showPic(url){
	p_layer.open({
		 type: 1,
		  title: false,
		  closeBtn: 0,
		  area: ['630px', '360px'],
		  skin: 'layui-layer-nobg', //没有背景色
		  shadeClose: true,
		  content: "<img src = '" + url + "' width=630 height=360/>"
		});
}
</script>
</body>
</html>