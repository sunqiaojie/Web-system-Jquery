<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>招聘新增／编辑</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap-theme.css">
<link rel="stylesheet" type="text/css" media="all" href="<%=path %>/resources/css/easyui-repair.css" />
<link rel="stylesheet" type="text/css" media="all" href="<%=path %>/resources/css/style.css" />
</head>
<body>

<div class="pad20">

		<h1 class="h1-pagetitle">招聘信息</h1>

		<form role="form" class="form-horizontal form-valite" name="RECRUIT" method="post" action="">
		<input type="hidden" name="id" id="id" value="${param.id}">
			<div class="formbox-a">
				<div class="row">
					<div class="col-sm-10">
						<div class="form-group">
							<label class="col-sm-3 control-label" for="quarters_name">岗位名称：</label>
							<div class="item-txt col-sm-9"><input type="text" class="form-control" name="quarters_name" id="quarters_name" validate="{required:'请输入用户名'}"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="salary">月薪(元)：</label>
							<div class="item-txt col-sm-9"><input type="text" class="form-control" name="salary" id="salary" validate="{required:''}"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="address">工作地点：</label>
							<div class="item-txt col-sm-9"><input type="text" class="form-control" name="address" id="address" validate="{required:''}"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="degree">学历：</label>
							<div class="item-txt col-sm-9"><input type="text" class="form-control" name="degree" id="degree" validate="{required:''}"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="seniority">工作年限：</label>
							<div class="item-txt col-sm-9"><input type="text" class="form-control" name="seniority" id="seniority" validate="{required:''}"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="time">发布时间：</label>
							<div class="item-txt col-sm-9"><input type="text" class="form-control" name="time" id="time" validate="{required:''}" disabled="disabled"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="quarters_content">岗位职责：</label>
							<div class="col-sm-9">
								<textarea class="form-control" name="quarters_content" id="quarters_content" rows="6"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="demand">任职要求：</label>
							<div class="col-sm-9">
								<textarea class="form-control" name="demand" id="demand" rows="6"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="demand">是否发布：</label>
							<div class="col-sm-9">
								<input type="radio" name="ispublish" id="publish_yes" value="1"/>是
								<input type="radio" name="ispublish" id="publish_no" value="0" checked="checked" />否
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
	   	<input type="submit" class="btn btn-primary" name="button" value="提交" onclick="save()"/>
	   	<input type="button" class="btn btn-danger" value="重置" onclick="recruit_clear()"/>
	   	<input type="button" class="btn btn-info" value="返回" onclick="window.history.back()"/>
	</div>
</div>

<script type="text/javascript">
	$(function(){
		var id = '${param.id}';
		setParam(id);
		setTime();
	});
	
	function setParam(id){
		$.post(path+'/system/recruit/getone',{"id":id},function(json){
			var result = eval("("+ json +")");
			if(result.status == "1"){
				var recruit = result.data;
				$("#id").val(id);
				$("#quarters_name").val(recruit.quarters_name);
				$("#salary").val(recruit.salary);
				$("#degree").val(recruit.degree);
				$("#seniority").val(recruit.seniority);
				$("#quarters_content").val(recruit.quarters_content);
				$("#demand").val(recruit.demand);
				$("#address").val(recruit.address);
				$("#time").val(recruit.time);
				if(recruit.ispublish == '1'){
					$("#publish_yes").attr('checked','checked');
				}else if(recruit.ispublish == '0'){
					$("#publish_no").attr('checked','checked');
				}
			}
		});
	}
	
	function recruit_clear(){
		$("#quarters_name").val("");
		$("#salary").val("");
		$("#degree").val("");
		$("#seniority").val("");
		$("#quarters_content").val("");
		$("#demand").val("");
		$("#address").val("");
		$("#time").val("");
	} 
	
	function save(){
		var params = $(document.forms['RECRUIT']).serialize();
		$.post(path+'/system/recruit/saver',params,function(json){
			var result = eval("("+json+")");
			if(result.status  == "1"){
				layer.msg('保存成功！', {icon: 1});
				var url = path+'/page/recruit/recruit_list.jsp';
				document.location = url;
			}else{
				layer.msg('保存失败，请重试！', {icon: 2});
			}
		});
	}
	
	function setTime(){
		var date=new Date;
		var year=date.getFullYear(); 
		var month=date.getMonth()+1;
		var day=date.getDate();
		var hour = date.getHours(); //获取当前小时数(0-23)
		var min = date.getMinutes(); //获取当前分钟数(0-59)
		var sec = date.getSeconds(); //获取当前秒数(0-59)
		var mydate = (year.toString()+ '-' +month.toString()+ '-'+day.toString() + ' ' + hour.toString() + ':' + min.toString() + ':' +sec.toString());
		$("#time").val(mydate);
	}
</script>
</body>
</html>