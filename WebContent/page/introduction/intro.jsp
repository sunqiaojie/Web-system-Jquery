<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公司简介</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap-theme.css">
<link rel="stylesheet" type="text/css" media="all" href="<%=path %>/resources/css/easyui-repair.css" />
<link rel="stylesheet" type="text/css" media="all" href="<%=path %>/resources/css/style.css" />
<script type="text/javascript" src="<%=path%>/resources/js/layer/layer.js"></script>
</head>
<body>
<div class="col-sm-10">
	<div class="panel panel-info">
		<h5 class="panel-heading">公司简介</h5>
		<div class="panel-body">
			<textarea rows="20" cols="20" style="width: 920px;height: 300px;text-indent: 2em;padding-top: 20px;" id="content"></textarea>
		</div>
	</div>
	<div class="col-sm-10"></div>
	<div class="col-sm-2">
		<button type="button" class="btn btn-primary navbar-btn" onclick="save()">确定</button>
		<button type="button" class="btn btn-danger navbar-btn" onclick="clear_intro()">清空</button>
	</div>
</div>

<script type="text/javascript">
$(function(){
	check();
});
var address = "";
var tellphone = "";
var email = "";
var portals = "";
var id="";

function check(){
	$.post(path+"/system/synopsis/getSynopsis",{},function(json){
		var result = eval("("+json+")");
		$("#content").val(result.data.content);
		address = result.data.address;
		tellphone = result.data.tellphone;
		email = result.data.email;
		portals = result.data.portals;
		id = result.data.id;
	});
	
}
function save(){
	var content = encodeURI($("#content").val());
	var params = "id=1"+"&content="+content+"&portals="+portals+"&address="+address+"&tellphone="+tellphone+"&email="+email;
	$.ajax({ 
	       type: "post", 
	       url: path+"/system/synopsis/save", 
	       data : params,
	       cache:false, 
	       success: function(json){ 
	    	   var result = eval("("+json+")");
	   			if(result.status  == "1"){
	   				layer.msg('保存成功！', {icon: 1});
	   			}else{
	   				layer.msg('保存失败，请重试！', {icon: 2});
	   			}
	        } 
		});
	/* $.post(path+"/system/synopsis/save",params,function(json){
		var result = eval("("+json+")");
		if(result.status  == "1"){
			layer.msg('保存成功！', {icon: 1});
		}else{
			layer.msg('保存失败，请重试！', {icon: 2});
		}
	}); */
}
function clear_intro(){
	$("#content").val("");
}

String.prototype.replaceAll = function(s1,s2){ 
	return this.replace(new RegExp(s1,"gm"),s2); 
}
function html_encode(str) {
    var s = "";
    if (str.length == 0)
        return "";
    s = str.replaceAll(/&/g, "&gt;");
    s = s.replaceAll(/</g, "&lt;");
    s = s.replaceAll(/>/g, "&gt;");
    s = s.replaceAll(/ /g, "&nbsp;");
    s = s.replaceAll(/\'/g, "&#39;");
    s = s.replaceAll(/\"/g, "&quot;");
    s = s.replaceAll(/\n/g, "<br>");
    s = s.replaceAll(/%/g, "%25");
    return s;
}

</script>
</body>
</html>