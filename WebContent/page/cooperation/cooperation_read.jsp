<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>合作详情信息</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap-theme.css">
<link rel="stylesheet" type="text/css" media="all" href="<%=path %>/resources/css/easyui-repair.css" />
<link rel="stylesheet" type="text/css" media="all" href="<%=path %>/resources/css/style.css" />
</head>
<body>
<div class="pad20">
		<h1 class="h1-pagetitle">合作信息</h1>
		<form role="form" class="form-horizontal form-valite" name="RECRUIT" method="post" action="">
			<div class="formbox-a">
				<div class="row">
					<div class="col-sm-10">
						<div class="form-group">
							<label class="col-sm-3 control-label">联系人姓名：</label>
							<div class="item-txt col-sm-9"><input type="text" class="form-control" name="name" id="name"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">公司名称：</label>
							<div class="item-txt col-sm-9"><input type="text" class="form-control" name="company" id="company"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">工作职务：</label>
							<div class="item-txt col-sm-9"><input type="text" class="form-control" name="job" id="job"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">联系方式：</label>
							<div class="item-txt col-sm-9"><input type="text" class="form-control" name="tellphone" id="tellphone"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">提交日期：</label>
							<div class="item-txt col-sm-9"><input type="text" class="form-control" name="time" id="time"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">意向：</label>
							<div class="item-txt col-sm-9"><input type="text" class="form-control" name="intention" id="intention"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="demand">项目介绍：</label>
							<div class="col-sm-9">
								<textarea class="form-control" name="project" id="project" rows="6"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			</form>
	</div>
<div>
	<div class="col-sm-5"></div>
	<div class="col-sm-4">
	   	<input type="button" class="btn btn-info" value="返回" onclick="window.history.back()"/>
	</div>
</div>

<script type="text/javascript">
$(function(){
	var id="${param.id}";
	check_coopera(id);
});

function check_coopera(id){
	$.post(path+'/system/visitorinfo/getone',{"id":id},function(json){
		var result = eval("("+json+")");
		result = result.data;
		$("#name").val(result.name);
		$("#company").val(result.company);
		$("#job").val(result.job);
		$("#tellphone").val(result.telephone);
		$("#time").val(result.time);
		$("#intention").val(result.intention);
		$("#project").val(result.project);
	});
}
</script>
</body>
</html>