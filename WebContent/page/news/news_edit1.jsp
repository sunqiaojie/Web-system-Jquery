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
<script>
KindEditor.ready(function(K) {
	var Keditor = K.create('textarea[name="content"]', {
		cssPath : path + '/resources/plugin/kindeditor-4.1.10/plugins/code/prettify.css',
		uploadJson : path + '/resources/plugin/kindeditor-4.1.10/jsp/upload_json.jsp',
		fileManagerJson : path + '/resources/plugin/kindeditor-4.1.10/jsp/file_manager_json.jsp',
		allowFileManager : true,
		width:'100%',
		height:'500px',
		items : ['source', '|', 
		         'fullscreen','cut', 'copy', 'paste','plainpaste', 'wordpaste', '|',
		         'justifyleft', 'justifycenter', 'justifyright','justifyfull', 'insertorderedlist', 
		         'insertunorderedlist', '|', 'selectall','title', 'fontname',
		         'fontsize', '|', 'bold','italic', 'forecolor','hilitecolor',
		         'underline', 'strikethrough', 'removeformat', '|', 'image',
		         'advtable', 'hr', 'emoticons', 'link', 'unlink', 'insertfile','|', 'about'],
		afterCreate : function() {
			var self = this;
			K.ctrl(document, 13, function() {
				self.sync();
				document.forms['news'].submit();
			});
			K.ctrl(self.edit.doc, 13, function() {
				self.sync();
				document.forms['news'].submit();
			});
		}
	});
});
</script>

<body>
<div class="col-sm-8">
<div class="panel panel-info">
<h5 class="panel-heading">新闻发布</h5>
<form name="news" method="post" action="<%=path%>/system/news/save">
<input type="hidden" name="id" value="${param.id }">
<div class="panel-body">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="biaoge02" style="margin:2px auto;width:620px;">
	<tbody>
		<tr>
			<th>标题：</th>
			<td><input id="title" class="inp" name="title" type="text"/></td>
		</tr>
		<tr>
		    <th>来源：</th>
		    <td><input id="news_source" class="inp" name="news_source" type="text"></td>
		</tr>
		<tr>
			<th>内容：</th>
			<td>
				<textarea id="content_id" name="content" rows="3" cols="8"></textarea>
			</td>
		</tr>
		 <tr>
		    <th>发布人：</th>
		    <td>
		    	<input name ="publisher" class="inp" id="publisher" type="text">
		    </td>
		  </tr>
	</tbody>
	<tfoot>
		<tr>
	      	<td colspan="2">
		      	<input type="submit" class="btn btn-primary" name="button" value="提交"/>
		      	<input type="button" class="btn btn-info" value="返回" onclick="history.back()"/>
	      	</td>
      </tr>
	</tfoot>
</table>
</div>
</form>
</div>
</div>

<script type="text/javascript">
$(function(){
	var id = '${param.id}';
	check_news(id);
});

function check_news(id){
	$.post(path+'/system/news/getone',{"id":id},function(json){
		var result = eval("("+json+")");
		$("#title").val(result.data.title);
		KindEditor.html('#content_id', result.data.content);
		$("#news_source").val(result.data.news_source);
		$("#publisher").val(result.data.publisher);
	});
}

</script>
</body>
</html>