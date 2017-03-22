<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="zh-CN" />
<meta content="all" name="robots" />
<meta name="author" content="" />
<meta name="Copyright" content="" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<link rel="icon" href="/favicon.ico" type="image/x-icon" />
<title>表单页-后台管理系统</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap-theme.css">
<link rel="stylesheet" type="text/css" media="all" href="<%=path %>/resources/css/easyui-repair.css" />
<link rel="stylesheet" type="text/css" media="all" href="<%=path %>/resources/css/style.css" />

</head>
<body>
<div class="wrapper">

<!-- 流式布局 p1-p12，将页面宽度分为12份， 如p4+p4+p4三等分页面，p6+p6二等分页面，p6+p3+p3 将宽度分3块，前50%，后2块25%  -->
<!-- 示例
		<div class="fluidbox">
			<div class="p4"></div>
			<div class="p4"></div>
			<div class="p4"></div>
		</div>
-->
	<div class="pad20">

		<h1 class="h1-pagetitle">用户信息</h1>

		<form role="form" class="form-horizontal form-valite" method="post" action="">
			<div class="formbox-a">

				<div class="row">
					<div class="col-sm-5">
						<div class="form-group">
							<label class="col-sm-3 control-label" for="username">用户名：</label>
							<div class="item-txt col-sm-9"><input type="text" class="form-control" name="username" id="username" validate="{required:'请输入用户名'}"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="phone">联系方式：</label>
							<div class="item-txt col-sm-9"><input type="text" class="form-control" name="phone" id="phone" validate="{required:'请输入联系方式'}"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="company">公司名称：</label>
							<div class="item-txt col-sm-9"><input type="text" class="form-control" name="company" id="company" validate="{required:'请输入公司名称'}"></div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" for="inputSuccess4">用户名：</label>
							<div class="item-txt col-sm-9">
								<select class="form-control">
									<option value="1">数值1</option>
									<option value="2">数值2</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">选项：</label>
							<div class="col-sm-9">
								<textarea class="form-control" rows="3"></textarea>
							</div>
						</div>
					</div>

					<div class="col-sm-5">
						<div class="form-group">
							<label class="col-sm-3 control-label" for="value4">开始日期：</label>
							<div class="item-txt col-sm-9"><input type="text" class="form-control hk-date" name="value4" id="value4" validate="{required:'请输入开始日期'}"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="value5">结束日期：</label>
							<div class="item-txt col-sm-9"><input type="text" class="form-control hk-date" name="value5" id="value5" validate="{required:'请输入结束日期'}"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="inputSuccess4">多选：</label>
							<div class="item-txt col-sm-9">
								<label class="checkbox-inline"><input type="checkbox" value="option1" id="inlineCheckbox1"> 选项1</label>
								<label class="checkbox-inline"><input type="checkbox" value="option2" id="inlineCheckbox2"> 选项2</label>
								<label class="checkbox-inline"><input type="checkbox" value="option3" id="inlineCheckbox3"> 选项3</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">单选：</label>
							<div class="item-txt col-sm-9">
								<label class="radio-inline"><input type="radio" value="option1" name="rad-1"> 选项1</label>
								<label class="radio-inline"><input type="radio" value="option2" name="rad-1"> 选项2</label>
								<label class="radio-inline"><input type="radio" value="option3" name="rad-1"> 选项3</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">密码：</label>
							<div class="item-txt col-sm-9">
								<div class="item-txt col-sm-9"><input type="text" id="password" class="form-control txt" validate="{len:{opt:[3,17],msg:'请设置4到16位的密码'}}" name="password" value="" /></>
							</div>
						</div>
					</div>

				</div>

				<p class="center col-sm-10">

					<input class="btn btn-primary" type="submit" value="提 交" />
					<input class="btn btn-default" type="submit" value="取 消" />
				</p>
			</div>
		</form>
	</div>
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
	var $formA = $('.form-valite').soValidate({
		inputPar:'.item-txt',
		submit : function (form) {
			window.console && console.log(form.serializeObject());
			$.messager.progress({width:100,text:'提交中...'});
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