<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE html>
<html>
<head id="Head1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理系统</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/bootstrap-theme.css">
<link rel="stylesheet" type="text/css" media="all" href="<%=path %>/resources/css/easyui-repair.css" />
<link rel="stylesheet" type="text/css" media="all" href="<%=path %>/resources/css/style.css" />

</head>
<body>

<div class="pad20">

		<div role="alert" class="alert alert-success alert-dismissible"><button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <strong>1ok！</strong> 您的信息已更新成功！
	      </div>
		<div class="alert alert-warning alert-dismissible" role="alert">
		  <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		  <strong>注意！</strong>有部分信息未提交！
		</div>
		<div class="row">
			<div class="col-sm-6">
				<!-- style1 -->
				<div class="list-group">
					<h5 class="list-group-item active">标题</h5>
					<a class="list-group-item" href="#">内容1<span class="badge">14</span></a>
					<a class="list-group-item" href="#">内容2</a>
					<p class="list-group-item"><a href="#">内容3</a></p>
					<p class="list-group-item"><a href="#">内容4</a></p>
				</div>

				<!-- style2 -->
				<div class="panel panel-primary">
					<h5 class="panel-heading">标题</h5>
					<div class="panel-body">
						内容
					</div>
				</div>

				<!-- style3 -->
				<div class="list-group">
					<a class="list-group-item active" href="#">
						<h5 class="list-group-item-heading">List group item heading</h5>
						<p class="list-group-item-text">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.</p>
					</a>
					<a class="list-group-item" href="#">
						<h5 class="list-group-item-heading">List group item heading</h5>
						<p class="list-group-item-text">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.</p>
					</a>
					<a class="list-group-item" href="#">
						<h5 class="list-group-item-heading">List group item heading</h5>
						<p class="list-group-item-text">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.</p>
					</a>
				</div>

				<!-- style4 -->
				<div class="panel panel-warning">
					<h5 class="panel-heading">标题</h5>
					<div class="panel-body">
						内容
					</div>
				</div>

				<!-- style5 -->
				<div class="panel panel-success">
					<h5 class="panel-heading">标题</h5>
					<div class="panel-body">
						内容
					</div>
				</div>

				<!-- style6 -->
				<div class="panel panel-info">
					<h5 class="panel-heading">标题</h5>
					<div class="panel-body">
						内容
					</div>
				</div>

				<!-- style7 -->
				<div class="panel panel-danger">
					<h5 class="panel-heading">标题</h5>
					<div class="panel-body">
						内容
					</div>
				</div>


			</div><!-- /.col-sm-4 -->

			<div class="col-sm-6">

				<!-- style8 -->
				<div class="tabgroup">
					<ul class="nav nav-tabs" role="tablist">
					  <li role="presentation" class="active"><a href="#home" role="tab" data-toggle="tab">Home</a></li>
					  <li role="presentation"><a href="#profile" role="tab" data-toggle="tab">Profile</a></li>
					  <li role="presentation"><a href="#messages" role="tab" data-toggle="tab">Messages</a></li>
					  <li role="presentation"><a href="#settings" role="tab" data-toggle="tab">Settings</a></li>
					</ul>

					<!-- Tab panes -->
					<div class="tab-content">
					  <div role="tabpanel" class="tab-pane active" id="home">

						<table class="table table-hover table-responsive">
							<thead>
								<tr>
									<th>#</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Username</th>
								</tr>
							</thead>
							<tbody>
							<tr>
								<td>1</td>
								<td>Mark</td>
								<td>Otto</td>
								<td>@mdo</td>
							</tr>
							<tr>
								<td>2</td>
								<td>Jacob</td>
								<td>Thornton</td>
								<td>@fat</td>
							</tr>
							<tr class="success">
								<td>3</td>
								<td>Larry</td>
								<td>the Bird</td>
								<td>@twitter</td>
							</tr>
							</tbody>
						</table>

					  </div>
					  <div role="tabpanel" class="tab-pane pad-t10" id="profile">
						<div class="list-group">
							<h5 class="list-group-item active">标题</h5>
							<a class="list-group-item" href="#">内容1<span class="badge">14</span></a>
							<a class="list-group-item" href="#">内容2</a>
							<p class="list-group-item"><a href="#">内容3</a></p>
							<p class="list-group-item"><a href="#">内容4</a></p>
						</div>
					  </div>
					  <div role="tabpanel" class="tab-pane" id="messages">

						<table class="table table-hover table-responsive">
							<thead>
								<tr>
									<th>#</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Username</th>
								</tr>
							</thead>
							<tbody>
							<tr class="danger">
								<td>1</td>
								<td>Jacob</td>
								<td>Thornton</td>
								<td>@fat</td>
							</tr>
							<tr>
								<td>2</td>
								<td>Larry</td>
								<td>the Bird</td>
								<td>@twitter</td>
							</tr>
							</tbody>
						</table>

					  </div>
					  <div role="tabpanel" class="tab-pane pad20" id="settings">这是最基本的 Bootstrap 文件组织形式：未压缩版的文件可以在任意web项目中直接使用。我们同时提供了压缩（bootstrap.min.*）与未压缩 (bootstrap.*) 的 CSS 和 JS 文件。字体图标文件来自于 Glyphicons。Bootstrap 源码
Bootstrap 源码包含了预先编译的 CSS、JavaScript 和图标字体文件，并且还有 LESS、JavaScript 和文档的源码。具体来说，主要文件组织结构如下：

					  </div>
					</div>

				</div>

				<div class="tabgroup">


					<ul class="nav nav-tabs" role="tablist">
					  <li role="presentation" class="active"><a href="#home2" role="tab" data-toggle="tab">Home</a></li>
					  <li role="presentation"><a href="#profile2" role="tab" data-toggle="tab">Profile</a></li>
					  <li role="presentation"><a href="#messages2" role="tab" data-toggle="tab">Messages</a></li>
					  <li role="presentation"><a href="#settings2" role="tab" data-toggle="tab">Settings</a></li>
					</ul>

					<!-- Tab panes -->
					<div class="tab-content">
					  <div role="tabpanel" class="tab-pane active" id="home2">

						<table class="table table-hover table-responsive">
							<thead>
								<tr>
									<th>#</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Username</th>
								</tr>
							</thead>
							<tbody>
							<tr>
								<td>1</td>
								<td>Mark</td>
								<td>Otto</td>
								<td>@mdo</td>
							</tr>
							<tr class="danger">
								<td>2</td>
								<td>Jacob</td>
								<td>Thornton</td>
								<td>@fat</td>
							</tr>
							<tr>
								<td>3</td>
								<td>Larry</td>
								<td>the Bird</td>
								<td>@twitter</td>
							</tr>
							</tbody>
						</table>

					  </div>
					  <div role="tabpanel" class="tab-pane pad20" id="profile2">Bootstrap （当前版本 v3.3.0）提供以下几种方式帮你快速上手，每一种方式针对具有不同技能等级的开发者和不同的使用场景。继续阅读下面的内容，看看哪种方式适合你的需求吧。</div>
					  <div role="tabpanel" class="tab-pane pad20" id="messages2">Bootstrap 提供了两种形式的压缩包，在下载下来的压缩包内可以看到以下目录和文件，这些文件按照类别放到了不同的目录内，并且提供了压缩与未压缩两种版本。</div>
					  <div role="tabpanel" class="tab-pane pad20" id="settings2">这是最基本的 Bootstrap 文件组织形式：未压缩版的文件可以在任意web项目中直接使用。我们同时提供了压缩（bootstrap.min.*）与未压缩 (bootstrap.*) 的 CSS 和 JS 文件。字体图标文件来自于 Glyphicons。Bootstrap 源码
Bootstrap 源码包含了预先编译的 CSS、JavaScript 和图标字体文件，并且还有 LESS、JavaScript 和文档的源码。具体来说，主要文件组织结构如下：</div>
					</div>


				</div>

			</div>


    		</div>

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

</body>
</html>