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
<script type="text/javascript" src="<%=path%>/resources/js/layer/layer.js"></script>
</head>
<body>
<div class="pad20">
		<h1 class="h1-pagetitle">新闻信息</h1>
		<form role="form" class="form-horizontal form-valite" name="NEWS" method="post" action="">
		<input type="hidden" name="id" id="id" value="${param.id}">
			<div class="formbox-a">
				<div class="row">
					<div class="col-sm-10">
						<div class="form-group">
							<label class="col-sm-3 control-label" for="quarters_name">新闻标题：</label>
							<div class="item-txt col-sm-9"><input type="text" class="form-control" name="title" id="title"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="salary">来源（URL）：</label>
							<div class="item-txt col-sm-9"><input type="text" class="form-control" name="news_source" id="news_source"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="time">发布时间：</label>
							<div class="item-txt col-sm-9"><input type="text" class="form-control" name="time" id="time" validate="{required:''}" disabled="disabled"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="demand">新闻概要：</label>
							<div class="col-sm-9">
								<textarea class="form-control" name="content" id="content" rows="6"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">发布者：</label>
							<div class="item-txt col-sm-9"><input type="text" class="form-control" name="publisher" id="publisher" disabled="disabled"></div>
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
	   	<input type="button" class="btn btn-danger" value="重置" onclick="news_clear()"/>
	   	<input type="button" class="btn btn-info" value="返回" onclick="window.history.back()"/>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		var id = '${param.id}';
		check_news(id);
		setTime();
	});
	function save(){
		var params = $(document.forms['NEWS']).serialize();
		$.post(path+'/system/news/save',params,function(json){
			var result = eval("("+json+")");
			if(result.status  == "1"){
				layer.msg('保存成功！', {icon: 1});
				var url = path+'/page/news/news_list.jsp';
				document.location = url;
			}else{
				layer.msg('保存失败，请重试！', {icon: 2});
			}
		});
	}
	
	function check_news(id){
		$.post(path+'/system/news/getone',{"id":id},function(json){
			var result = eval("("+json+")");
			$("#title").val(result.data.title);
			$('#content').val(result.data.content);
			$("#news_source").val(result.data.news_source);
			$("#publisher").val(result.data.publisher);
		});
	}
	
	function news_clear(){
		$("#title").val("");
		$("#news_source").val("");
		$("#content").val("");
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