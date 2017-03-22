<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>联系方式</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap-theme.css">
<link rel="stylesheet" type="text/css" media="all" href="<%=path %>/resources/css/easyui-repair.css" />
<link rel="stylesheet" type="text/css" media="all" href="<%=path %>/resources/css/style.css" />
</head>
<body>
<div class="col-sm-10">
	<div class="col-sm-5">
		<div class="panel panel-info">
			<h5 class="panel-heading">地址</h5>
			<div class="panel-body">
				<input class="form-control" type="text" placeholder="请输入公司地址" name="address" id="address"/>
			</div>
		</div>
	</div>
	<div class="col-sm-1"></div>
	<div class="col-sm-5">
		<div class="panel panel-info">
			<h5 class="panel-heading">联系方式</h5>
			<div class="panel-body">
				<input class="form-control" type="text" placeholder="请输入联系方式" name="tellphone" id="tellphone"/>
			</div>
		</div>
	</div>
</div>
<div class="col-sm-10">
	<div class="col-sm-5">
		<div class="panel panel-info">
			<h5 class="panel-heading">邮箱</h5>
			<div class="panel-body">
				<input class="form-control" type="text" placeholder="请输入邮箱地址" name="email" id="email"/>
			</div>
		</div>
	</div>
	<div class="col-sm-1"></div>
	<div class="col-sm-5">
		<div class="panel panel-info">
			<h5 class="panel-heading">公司网站地址</h5>
			<div class="panel-body">
				<input class="form-control" type="text" placeholder="请输入网站地址" name="portals" id="portals"/>
			</div>
		</div>
	</div>
</div>
<div class="col-sm-10">
 <div class="col-sm-9"></div>
	<div class="col-sm-2">
		<button type="button" class="btn btn-primary navbar-btn" onclick="save()">确定</button>
		<button type="button" class="btn btn-danger navbar-btn" onclick="clear_phone()">清空</button>
</div>
</div>

<script type="text/javascript">
$(function(){
	check();
});
var content = ""; 
var id="";

function check(){
	$.post(path+"/system/synopsis/getSynopsis",{},function(json){
		var result = eval("("+json+")");
		$("#address").val(result.data.address);
		$("#tellphone").val(result.data.tellphone);
		$("#email").val(result.data.email);
		$("#portals").val(result.data.portals);
		content = result.data.content;
	});
}

function save(){
	var address = $("#address").val();
	var tellphone = $("#tellphone").val();
	var email = $("#email").val();
	var portals = $("#portals").val();
	var params = "id=1"+"&address="+address+"&tellphone="+tellphone+"&email="+email+"&portals="+portals+"&content="+content;
	$.post(path+'/system/synopsis/save',params,function(json){
		var result = eval("("+json+")");
		if(result.status == "1"){
			layer.msg('保存成功！', {icon: 1});
		}else{
			layer.msg('保存失败，请重试！', {icon: 2});
		}		
	});
}

function clear_phone(){
	$("#address").val("");
	$("#tellphone").val("");
	$("#email").val("");
	$("#portals").val("");
}
</script>
</body>
</html>