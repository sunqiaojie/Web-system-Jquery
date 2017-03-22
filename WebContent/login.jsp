<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台登录</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap-theme.css">
<style type="text/css">
* {
	padding: 0;
	margin: 0;
}

#center {
	width: 500px;
	margin: 100px auto;
	padding: 20px;
}
</style>
</head>
<body>
	<div id="center">
		<form class="form-horizontal" role="form" action="<%=path%>/system/user/login" method="post">

				<div class="form-group">
					<label for="username" class="col-sm-2 control-label">用户名</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="username"
							name="username" placeholder="请输入名字">
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-2 control-label">密 码</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="password"
							name="password" placeholder="请输入密码">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">登录</button>
						<button type="button" class="btn btn-primary"
							onclick="portalUrl()">进入门户网站</button>
					</div>
				</div>
		</form>
	</div>
	<script type="text/javascript">
		function portalUrl() {
			window.location.href = path + '/portal/index.html';
		}
	</script>
</body>
</html>