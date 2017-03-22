window.onload = function(){
	$('#loading-mask').fadeOut();
}

var onlyOpenTitle="欢迎使用";//不允许关闭的标签的标题

var soPro = soPro||{};
soPro.index = {
	init : function () {
		var me =this;
		me.openWinInMain();
		me.tabClose();
		me.tabCloseEven();
	},
	openWinInMain : function () {
		var me =this;
		$('.s-newwin').click(function() {
			var tabTitle = $(this).attr('title');
			var tabTitle = tabTitle||$(this).text();
			var url = $(this).attr('rel');
			me.addTab(tabTitle,url);
		});
	},
	addTab : function(tabTitle,url){
		var me = this;
		if(!$('#tabs').tabs('exists',tabTitle)){
			$('#tabs').tabs('add',{
				title:tabTitle,
				content:me._createFrame(url),
				closable:true
			});
		}else{
			$('#tabs').tabs('select',tabTitle);
			$('#refresh').click( );
		}
		me.tabClose();
	},
	tabClose : function(){
		/*双击关闭TAB选项卡*/
		$(".tabs-inner").dblclick(function(){
			var subtitle = $(this).children(".tabs-closable").text();
			$('#tabs').tabs('close',subtitle);
		})
		
		/*为选项卡绑定右键*/
		$(".tabs-inner").bind('contextmenu',function(e){
			$('#mm').menu('show', {
				left: e.pageX,
				top: e.pageY
			});

			var subtitle =$(this).children(".tabs-closable").text();

			$('#mm').data("currtab",subtitle);
			$('#tabs').tabs('select',subtitle);
			return false;
		});
	},
	tabCloseEven : function() {//绑定右键菜单事件
		var me = this;
		$('#mm').menu({
			onClick: function (item) {
				me._closeTab(item.id);
			}
		});
		return false;
	},
	_createFrame : function(url){
		var iframe = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:99%;"></iframe>';
		return iframe;
	},
	_closeTab : function(action){
		var me = this;
		var alltabs = $('#tabs').tabs('tabs');
		var currentTab =$('#tabs').tabs('getSelected');
		var allTabtitle = [];
		$.each(alltabs,function(i,n){
			allTabtitle.push($(n).panel('options').title);
		})

		switch (action) {
			case "refresh":
				var iframe = $(currentTab.panel('options').content);
				var src = iframe.attr('src');
				$('#tabs').tabs('update', {
					tab: currentTab,
					options: {
						content: me._createFrame(src)
					}
				})
				break;
			case "close":
				var currtab_title = currentTab.panel('options').title;
				$('#tabs').tabs('close', currtab_title);
				break;
			case "closeall":
				$.each(allTabtitle, function (i, n) {
					if (n != onlyOpenTitle){
						$('#tabs').tabs('close', n);
					}
				});
				break;
			case "closeother":
				var currtab_title = currentTab.panel('options').title;
				$.each(allTabtitle, function (i, n) {
					if (n != currtab_title && n != onlyOpenTitle)
					{
						$('#tabs').tabs('close', n);
					}
				});
				break;
			case "closeright":
				var tabIndex = $('#tabs').tabs('getTabIndex', currentTab);

				if (tabIndex == alltabs.length - 1){
					alert('亲，后边没有啦 ^@^!!');
					return false;
				}
				$.each(allTabtitle, function (i, n) {
					if (i > tabIndex) {
						if (n != onlyOpenTitle){
							$('#tabs').tabs('close', n);
						}
					}
				});

				break;
			case "closeleft":
				var tabIndex = $('#tabs').tabs('getTabIndex', currentTab);
				if (tabIndex == 1) {
					alert('亲，前边那个上头有人，咱惹不起哦。 ^@^!!');
					return false;
				}
				$.each(allTabtitle, function (i, n) {
					if (i < tabIndex) {
						if (n != onlyOpenTitle){
							$('#tabs').tabs('close', n);
						}
					}
				});

				break;
			case "exit":
				$('#closeMenu').menu('hide');
				break;
		}
	}
}


$(function(){
	soPro.index.init();
});



//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}
