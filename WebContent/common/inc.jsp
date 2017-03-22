<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%-- <link rel="stylesheet" href="<%=path %>/resources/css/style.css"> --%>

<!-- bootstrap -->
<%-- <link rel="stylesheet" href="<%=path %>/resources/plugins/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=path %>/resources/plugins/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
<script src="<%=path %>/resources/js/jquery.min.js"></script>
<script src="<%=path %>/resources/plugins/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>

<script src="<%=path %>/resources/plugins/layer/layer.js"></script>

<script src="<%=path %>/resources/plugins/my97/WdatePicker.js"></script> --%>



<link rel="stylesheet" type="text/css" href="<%=path %>/resources/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/themes/icon.css" />
<link rel="stylesheet" type="text/css" media="all" href="<%=path %>/resources/css/easyui-repair.css" />
<link rel="stylesheet" type="text/css" media="all" href="<%=path %>/resources/css/style.css" />



<script type="text/javascript" src="<%=path %>/resources/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/resources/js/jquery.easyui.ext.js"></script>
<script type="text/javascript" src="<%=path %>/resources/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path %>/resources/js/base.js"></script>
<script type="text/javascript" src="<%=path %>/resources/js/Global.js"></script>
<script type="text/javascript" src="<%=path %>/resources/js/jquery.timers-1.2.js"></script>
<script type="text/javascript" src="<%=path %>/resources/js/tools.js"></script>
<script type="text/javascript" src="<%=path %>/resources/js/runing.js"></script>

<%-- <script type="text/javascript" src="<%=path%>/resources/js/layer/layer.js"></script> --%>
<script type="text/javascript" src="<%=path %>/resources/js/index.js"> </script>

<script type="text/javascript">
	var path = "<%=path%>";
	var p_layer = parent.layer;//layer实现遮罩整个浏览器
</script>