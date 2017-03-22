<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻新增／编辑</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap-theme.css">
<link rel="stylesheet" type="text/css" media="all" href="<%=path %>/resources/css/easyui-repair.css" />
<link rel="stylesheet" type="text/css" media="all" href="<%=path %>/resources/css/style.css" />
<style type="text/css">
.biaoge02 {
	color:#575e6e;
	margin:40px;
	line-height:24px;
}
.biaoge02 tbody td {
	color:#575e6e;
	padding:10px 20px 10px 10px;
	text-align:left;
}
.biaoge02 th {
	color:#575e6e;
	font-size:14px;
	width: 100px;
}
.biaoge02 thead th {
	font-size:16px;
	border-bottom:2px solid #d7d7d7;
}
.biaoge02 tfoot td {
	border:none;
	text-align:center;
	padding-top:20px;
}
.inp{
	width: 400px;
}
</style>
<link rel="stylesheet" href="<%=path%>/resources/js/kindeditor-4.1.10/themes/default/default.css" />
<link rel="stylesheet" href="<%=path%>/resources/js/kindeditor-4.1.10/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=path%>/resources/js/kindeditor-4.1.10/kindeditor.js"></script>
<script charset="utf-8" src="<%=path%>/resources/js/kindeditor-4.1.10/plugins/code/prettify.js"></script>
</head>
<body>
<div class="pad20">

		<h1 class="h1-pagetitle">用户添加</h1>

		<form role="form" class="form-horizontal form-valite" name="RECRUIT" method="post" action="">
			<div class="formbox-a">
				<div class="row">
					<div class="col-sm-10">
						<div class="form-group">
							<label class="col-sm-3 control-label" for="username">用户名：</label>
							<div class="item-txt col-sm-9"><input type="text" class="form-control" name="username" id="username" validate="{required:'请输入用户名'}"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="salary">密  码：</label>
							<div class="item-txt col-sm-9"><input type="text" class="form-control" name="password" id="password" validate="{required:'请输入密码'}"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="address">确认密码：</label>
							<div class="item-txt col-sm-9"><input type="text" class="form-control" name="rpwd" id="rpwd" validate="{required:'请输入确认密码'}"></div>
						</div>
					</div>
				</div>
			</div>
			<br>
			<div class="col-sm-5"> </div>
			<div class="col-sm-4">
			   	<input type="submit" class="btn btn-primary" name="button" value="提交" />
			   	<input type="button" class="btn btn-danger" value="重置" onclick="user_clear()"/>
			   	<!-- <input type="button" class="btn btn-info" value="返回" onclick="window.history.back()"/> -->
			</div>
		</form>
	</div>

<script type="text/javascript">
$(function(){
	
	
});
function user_clear(){
	
	$("#username").val("");
	$("#password").val("");
	$("#rpwd").val("");
}


var $formA = $('.form-valite').soValidate({
	inputPar:'.item-txt',
	submit : function (form) {
		var user = {
				username:$("#username").val(),
				password:$("#password").val(),
				rpwd:$("#password").val()
			};
		if(user.password != user.rpwd){
			p_layer.alert('用户输入密码与验证密码不一致！', {
			    skin: 'layui-layer-lan'
			    ,closeBtn: 0
			    ,shift: 4 //动画类型
			  });
		}else{
			//$.messager.progress({width:100,text:'提交中...'});
			$.post(path+'/system/user/save',user,function(json){
				var result = eval("("+ json +")");
				if(result.status == "1"){
					p_layer.msg('保存成功！');
				}else{
					p_layer.alert(result.msg, {
					    skin: 'layui-layer-lan'
					    ,closeBtn: 0
					    ,shift: 1 //动画类型
					  });
				}
				
			});
			
		}
		//alert('success');
	},
	fail : function (form,failInputs) {
		window.console && console.log(failInputs);
		var $failF = $(failInputs[0]);
		$("html,body").stop().animate({'scrollTop': $failF.offset().top});//定位到第一个验证失败的对象
		$failF.focus();
	}
});
</script>
</body>
</html>