<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title> 业务管理系统</title>
    <script type="text/javascript" src="<%=path%>/resources/js/layer/layer.js"></script>
</head>
<body class="easyui-layout" style="overflow-y: hidden;" fit="true" scroll="no">

    <!-- 禁用脚本提示 -->
    <noscript>
        <div id="noscriptTip">
            <img src="<%=path %>/resources/images/noscript.gif" alt="抱歉，请开启脚本支持！" /></div>
    </noscript>

    <!-- 预加载 -->
    <div id="loading-mask">
        <div id="pageloading">
            <img src="<%=path %>/resources/images/loading.gif" align="absmiddle" />
            正在加载中,请稍候...</div>
    </div>

    <!-- head begin-->
    <div id="index-head" region="north">
        <div class="headlogo">
            <!-- <img src="<%=path %>/resources/images/logo.png" height="40" width="200" alt="" /> -->
        <font style="height: 40px;width: 200px;font-size: 30px;color: #fff;margin-left: 20px">管理系统</font>    
        </div>
        <div class="headtools">
            <p class="p-tools">欢迎你，<strong class="strong-user">admin</strong> <span onclick="Change_Pwd()" class="s-tools a-userSetting " style="cursor: pointer">账号设置</span><span class="s-tools s-loginout" style="cursor: pointer">退出</span></p>
        </div>
    </div>
    <!-- head end -->

    <!-- side begin -->
    <div id="index-side" region="west">
        <div id="nav">
            <ul class="ul-nav">

                <li class="li-nav">
                    <span class="s-nav">
                        <em class="em-icon">
                            <img src="<%=path %>/resources/images/icon/stack-25.png" height="25" width="25" alt="" /></em>
                        <b class="b-nav">首页</b>
                    </span>
                    <div class="sidemenu">
                        <h5 class="h5-menu">网站概况</h5>
                        <ul class="ul-subnav">
                            <li class="li-subnav"><span class="s-subnav s-newwin" rel="<%=path %>/page/home/list.jsp">网站预览</span></li>
                        </ul>
                        <%-- <h5 class="h5-menu">信息管理</h5>
                        <ul class="ul-subnav">
                            <li class="li-subnav"><span class="s-subnav s-newwin" rel="<%=path %>/page/home/list.jsp">信息单</span></li>
                            <li class="li-subnav"><span class="s-subnav s-newwin" rel="<%=path %>/page/home/list.jsp">信息保存</span></li>
                        </ul> --%>
                    </div>
                </li>
                <li class="li-nav">
                    <span class="s-nav">
                        <em class="em-icon">
                            <img src="<%=path %>/resources/images/icon/medium_volume-25.png" height="25" width="25" alt="" /></em>
                        <b class="b-nav">通知</b>
                    </span>
                    <div class="sidemenu">
                        <h5 class="h5-menu">通知</h5>
                        <ul class="ul-subnav">
                            <li class="li-subnav"><span class="s-subnav s-newwin" rel="<%=path %>/page/notice/notice_list.jsp">通知管理</span></li>
                        </ul>
                    </div>
                </li>
                <li class="li-nav">
                    <span class="s-nav">
                        <em class="em-icon">
                            <img src="<%=path %>/resources/images/icon/dog_house-25.png" height="25" width="25" alt="" /></em>
                        <b class="b-nav">公司相关</b>
                    </span>
                    <div class="sidemenu">
                        <h5 class="h5-menu">公司</h5>
                        <ul class="ul-subnav">
                            <li class="li-subnav"><span class="s-subnav s-newwin" rel="<%=path %>/page/introduction/intro.jsp">公司简介</span></li>
                        </ul>
                         <ul class="ul-subnav">
                            <li class="li-subnav"><span class="s-subnav s-newwin" rel="<%=path %>/page/introduction/phoneNum.jsp">联系方式</span></li>
                        </ul>
                        <ul class="ul-subnav">
                            <li class="li-subnav"><span class="s-subnav s-newwin" rel="<%=path %>/page/introduction/pictures.jsp">公司相册</span></li>
                        </ul>
                        <ul class="ul-subnav">
                            <li class="li-subnav"><span class="s-subnav s-newwin" rel="<%=path %>/page/introduction/intro.jsp">公司动态</span></li>
                        </ul>
                    </div>
                </li>
                <li class="li-nav">
                    <span class="s-nav">
                        <em class="em-icon">
                            <img src="<%=path %>/resources/images/icon/document-25.png" height="25" width="25" alt="" /></em>
                        <b class="b-nav">新闻中心</b>
                    </span>
                    <div class="sidemenu">
                        <h5 class="h5-menu">新闻</h5>
                        <ul class="ul-subnav">
                            <li class="li-subnav"><span class="s-subnav s-newwin" rel="<%=path %>/page/news/news_list.jsp">新闻发布</span></li>
                        </ul>
                    </div>
                </li>
                <li class="li-nav">
                    <span class="s-nav">
                        <em class="em-icon">
                            <img src="<%=path %>/resources/images/icon/layers-25.png" height="25" width="25" alt="" /></em>
                        <b class="b-nav">招聘</b>
                    </span>
                    <div class="sidemenu">
                        <h5 class="h5-menu">招聘管理</h5>
                        <ul class="ul-subnav">
                            <li class="li-subnav"><span class="s-subnav s-newwin" rel="<%=path %>/page/recruit/recruit_list.jsp">招聘内容</span></li>
                        </ul>
                    </div>
                </li>
				<li class="li-nav">
                    <span class="s-nav">
                        <em class="em-icon">
                            <img src="<%=path %>/resources/images/icon/layers-25.png" height="25" width="25" alt="" /></em>
                        <b class="b-nav">合作</b>
                    </span>
                    <div class="sidemenu">
                        <h5 class="h5-menu">合作信息</h5>
                        <ul class="ul-subnav">
                            <li class="li-subnav"><span class="s-subnav s-newwin" rel="<%=path %>/page/cooperation/cooperation_list.jsp">合作内容</span></li>
                        </ul>
                    </div>
                </li>
                <li class="li-nav">
                    <span class="s-nav">
                        <em class="em-icon">
                            <img src="<%=path %>/resources/images/icon/group-25.png" height="25" width="25" alt="" /></em>
                        <b class="b-nav">用户</b>
                    </span>
                    <div class="sidemenu">
                        <h5 class="h5-menu">用户管理</h5>
                        <ul class="ul-subnav">
                            <li class="li-subnav"><span class="s-subnav s-newwin" rel="<%=path %>/page/user/user_edit.jsp">用户添加</span></li>
                            <%-- <li class="li-subnav"><span class="s-subnav s-newwin" rel="<%=path %>/page/home/list.jsp">用户管理</span></li>
                            <li class="li-subnav"><span class="s-subnav s-newwin" rel="<%=path %>/page/home/list.jsp">用户管理</span></li> --%>
                        </ul>
                    </div>
                </li>
                
            </ul>
        </div>
    </div>
    <!-- side end -->

    <!-- main begin -->
    <div id="index-main" region="center" style="background: #eee; overflow-y: hidden">

        <div id="tabs" class="easyui-tabs" fit="true" border="false">
            <div title="欢迎使用 " style="overflow-x: hidden;">
                <iframe scrolling="auto" frameborder="0" src="<%=path %>/page/home/list.jsp" style="width: 100%; height: 100%;"></iframe>
            </div>
        </div>
    </div>
    <!-- main end -->

    <!-- footer begin -->
    <!-- <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
	<div class="footer">底部说明</div>
</div> -->
    <!-- footer end -->

    <!-- mainTab rightMenu -->
    <div id="mm" class="easyui-menu" style="width: 150px;">
        <div id="refresh">刷新</div>
        <div class="menu-sep"></div>
        <div id="close">关闭</div>
        <div id="closeall">全部关闭</div>
        <div id="closeother">除此之外全部关闭</div>
        <div class="menu-sep"></div>
        <div id="closeright">当前页右侧全部关闭</div>
        <div id="closeleft">当前页左侧全部关闭</div>
        <div class="menu-sep"></div>
        <div id="exit">退出</div>
    </div>

<script type="text/javascript">

$(function(){
	$(".s-loginout").bind("click",function(){
		window.location.href = path + '/system/user/loginout';
	});
});


//修改密码
function Change_Pwd(){
	var user = {};
	user.id = "${sessionScope['SESSION_LOGIN_USER'].id}";
	user.username = "${sessionScope['SESSION_LOGIN_USER'].username}";
	user.password = "${sessionScope['SESSION_LOGIN_USER'].password}";
	layer.open({
	    type: 1, //page层
	    area: ['400px', '260px'],
	    title: '修改密码',
	    //shade: 0.5, //遮罩透明度
	    moveType: 0, //拖拽风格，0是默认，1是传统拖动
	    shift: 1, //0-6的动画形式，-1不开启
	   // content: path+'/page/mainframe/pwd.jsp',
	   content : '<div style="padding:0 auto;width:100%;height: 100%;background:#eee;">'
					+'<table style="width:100%;height: 100%">'
						+'<tr style="height: 40xp;line-height: 40px;">'
							+'<td style="text-align:right;">旧密码：</td>'
							+'<td><input type="password" name="oldpwd" style="height:30px;width:200px;line-height:30px;"/></td>'
						+'</tr>'
							+'<tr style="height: 40xp;line-height: 40px;">'
							+'<td style="text-align:right;">新密码：</td>'
						+'	<td><input type="password" name="oldpwd1" style="height:30px;width:200px;line-height:30px;"/></td>'
						+'</tr>'
						+'<tr style="height: 40xp;line-height: 40px;">'
							+'<td style="text-align:right;">确认新密码：</td>'
							+'<td><input type="password" name="oldpwd2" style="height:30px;width:200px;line-height:30px;"/></td>'
						+'</tr>'
					+'</table>'
				+'</div>',
	    btn : ['确认','取消'],
	    yes: function(index, layero){
	    	var opwd = layero.find("input[name=oldpwd]").val();
	    	var newpwd = layero.find("input[name=oldpwd1]").val();
	    	var rnewpwd = layero.find("input[name=oldpwd2]").val();
	    	if(opwd ==""){
	    		layer.msg('旧密码不可为空！');
	    	}else if(newpwd == ""){
	    		layer.msg('新密码不可为空！');
	    	}else if(rnewpwd == ""){
	    		layer.msg('确认新密码不可为空！');
	    	}else{
	    		if(newpwd != rnewpwd){
	    			layer.msg('确认密码不一致');
		    	}else if(opwd !=user.password){
		    		layer.msg('旧密码不正确');
		    	}else{
					if(newpwd == rnewpwd){
					user.password = newpwd;
		    		$.post(path+'/system/user/changePwd',user,function(json){
		    			var result = eval("("+ json +")");
		    			if(result.status=="1"){
		    				layer.close(index);
				    		layer.alert('密码修改成功，请重新登陆', {
				    		    skin: 'layui-layer-lan', //样式类名
				    		    closeBtn : false,
				    		    btn : ['重新登录'],
				    		    yes : function(){
				    		    	window.location.href = path +'/system/user/loginout';
				    		    }
				    		});
		    			}
		    		});
		    		layer.alert('密码修改失败，请重新修改！');
		    		//layer.msg('修改成功');	
		    	}
		   	 }
	    	}
	    	
	    	//alert(opwd+newpwd+rnewopwd);
	    },
	    cancel: function(index){
	        //按钮【按钮二】的回调
	    },
	    success : function(layero, index){
	    	layero.find("input[name=oldpwd]").val("");
	    	layero.find("input[name=oldpwd1]").val("");
	    	layero.find("input[name=oldpwd2]").val("");
	    }
	});      
}


/* function loginout(){
	window.location.href = path + '/system/user/loginout';
} */

</script>
  
</body>
</html>
