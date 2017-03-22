/* ---------------标题栏添加右键显示隐藏和排序列功能 begin---------------- */
var createGridHeaderContextMenu = function(e, field) {
        e.preventDefault();
        var grid = $(this);/* grid本身 */
        var headerContextMenu = this.headerContextMenu;/* grid上的列头菜单对象 */
        if (!headerContextMenu) {
                var tmenu = $('<div style="width:100px;"></div>').appendTo('body');
                //var asc = $('<div iconCls="icon-asc" field="asc">升序</div>').appendTo(tmenu);
               // var desc = $('<div iconCls="icon-desc" field="desc">降序</div>').appendTo(tmenu);
                var filedHTML = $('<div iconCls="icon-columns"></div>');
                var span = $('<span>显示列/隐藏列</span>');
                var spdiv = $('<div></div>');
                var fields = grid.datagrid('getColumnFields');
                for ( var i = 0; i < fields.length; i++) {
                        var fildOption = grid.datagrid('getColumnOption', fields[i]);
                        if (!fildOption.hidden) {
                                $('<div iconCls="icon-checked" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(spdiv);
                        } else {
                                $('<div iconCls="icon-unchecked" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(spdiv);
                        }
                }
                span.appendTo(filedHTML);
                spdiv.appendTo(filedHTML);
                filedHTML.appendTo(tmenu);
                headerContextMenu = this.headerContextMenu = tmenu.menu({
                        onClick : function(item) {
                                var f = $(this).attr('field')
                                var fieldProperty = $(item.target).attr('field');
                                if (item.iconCls == 'icon-checked') {
                                        grid.datagrid('hideColumn', fieldProperty);
                                        $(this).menu('setIcon', {
                                                target : item.target,
                                                iconCls : 'icon-unchecked'
                                        });
                                }
                                if (item.iconCls == 'icon-unchecked') {
                                        grid.datagrid('showColumn', fieldProperty);
                                        $(this).menu('setIcon', {
                                                target : item.target,
                                                iconCls : 'icon-checked'
                                        });
                                }
//                                if (item.iconCls == 'icon-asc') {
//                                        var options = grid.datagrid('options');
//                                        options.sortName = f;
//                                        options.sortOrder =fieldProperty;
//                                        grid.datagrid('reload');
//                                }
//                                if (item.iconCls == 'icon-desc') {
//                                        var options = grid.datagrid('options');
//                                        options.sortName = f;
//                                        options.sortOrder =fieldProperty;
//                                        grid.datagrid('reload');
//                                }
                        }
                });
        }
        headerContextMenu.attr('field',field);
        headerContextMenu.menu('show', {
                left : e.pageX,
                top : e.pageY
        });
};
$.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
$.fn.treegrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
/* ---------------标题栏添加右键显示隐藏和排序列功能 end---------------- */

var myview = $.extend({},$.fn.datagrid.defaults.view,{
    onAfterRender:function(target){
        $.fn.datagrid.defaults.view.onAfterRender.call(this,target);
        var opts = $(target).datagrid('options');
        var vc = $(target).datagrid('getPanel').children('div.datagrid-view');
        vc.children('div.datagrid-empty').remove();
        var gridBodyH = vc.find('div.datagrid-body').height();
        if (!$(target).datagrid('getRows').length){
            var d = $('<div class="datagrid-empty"></div>').html(opts.emptyMsg || 'no records').appendTo(vc);
            d.css({
                position:'absolute',
                left:0,
                top:36,
                width:'100%',
                height:gridBodyH,
                textAlign:'center',
                backgroundColor:'#fafafa',
                lineHeight:'120px',
                color:'#999999',
                fontSize: '2em'
            });
        }
    }
    });



/**
 * @author 孙宇
 *
 * @requires jQuery,EasyUI
 *
 * 防止panel/window/dialog组件超出浏览器边界
 * @param left
 * @param top
 */
var easyuiPanelOnMove = function(left, top) {
	var l = left;
	var t = top;
	if (l < 1) {
		l = 1;
	}
	if (t < 1) {
		t = 1;
	}
	var width = parseInt($(this).parent().css('width')) + 14;
	var height = parseInt($(this).parent().css('height')) + 14;
	var right = l + width;
	var buttom = t + height;
	var browserWidth = $(window).width();
	var browserHeight = $(window).height();
	if (right > browserWidth) {
		l = browserWidth - width;
	}
	if (buttom > browserHeight) {
		t = browserHeight - height;
	}
	$(this).parent().css({/* 修正面板位置 */
		left : l,
		top : t
	});
};
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.panel.defaults.onMove = easyuiPanelOnMove;


/**
 * 字符串模版替换
 *
 * @param this 需要替换的字符串
 * @param data 替换的数据。json格式的数据或者数组。
 * 			eg：
 * 			  str：我是{{key1}}替换的字符串{{key2}}。 data：{key1:"替换",key2:"替换2"}
 * 			  str：我是{{key.subkey}}替换的字符串{{key.subkey2}}。 data：{key{subkey:"替换",subkey2:"替换2"}}
 * 			  str：我是{{0}}替换的字符串{{1}}。 data：["替换","替换2"]
 * @returns
 */
String.prototype.template = function(data) {
	var str = this;
	if(data && data.sort){
		for(var i=0; i<data.length; i++) {
			str = str.replace(new RegExp("{\\{"+i+"}}", "gm"), data[i]);
		}
		return str;
	}

	var placeholder = str.match(new RegExp("{{.+?}}", 'ig'));
	if (data && placeholder) {
		for ( var i = 0; i < placeholder.length; i++) {
			var key = placeholder[i];
			var value = proxy.call(data, key.replace(new RegExp("[{,}]", "gm"), ""));
			key = key.replace(new RegExp("\\\.", "gm"), "\\.").replace("{{","{\\{");
			if (value == null)
				value = "&nbsp;";
			str = str.replace(new RegExp(key, "gm"), value);
		}
	}
	return str;

	function proxy(key) {
		try {
			return eval('this.' + key);
		} catch (e) {
			return "";
		}
	}
};



/*
	$("#vd").validatebox("remove"); //移除验证
	$("#vd").validatebox("reduce");//恢复验证
*/
$.extend($.fn.validatebox.methods, {
	remove: function(jq, newposition){
		return jq.each(function(){
			$(this).removeClass("validatebox-text validatebox-invalid").unbind('focus.validatebox').unbind('blur.validatebox');
		});
	},
	reduce: function(jq, newposition){
		return jq.each(function(){
		   var opt = $(this).data().validatebox.options;
		   $(this).addClass("validatebox-text").validatebox(opt);
		});
	}
});


/*
*获取grid工具栏用法
var toolbar = $(this).datagrid('getToolbar');
*/
$.extend($.fn.datagrid.methods, {
    getToolbar:function(jq){
        return jq.datagrid('getPanel').find('a.easyui-linkbutton').linkbutton();
    }
});


/**
 * Datagrid扩展方法tooltip 基于Easyui 1.3.3，可用于Easyui1.3.3+
 * 简单实现，如需高级功能，可以自由修改
 * 使用说明:
 *   在easyui.min.js之后导入本js
 *   代码案例:
 *		$("#dg").datagrid({....}).datagrid('tooltip'); 所有列
 *		$("#dg").datagrid({....}).datagrid('tooltip',['productid','listprice']); 指定列
 * @author ____′↘夏悸
 */
$.extend($.fn.datagrid.methods, {
	tooltip : function (jq, fields) {
		return jq.each(function () {
			var panel = $(this).datagrid('getPanel');
			if (fields && typeof fields == 'object' && fields.sort) {
				$.each(fields, function () {
					var field = this;
					bindEvent($('.datagrid-body td[field=' + field + '] .datagrid-cell', panel));
				});
			} else {
				bindEvent($(".datagrid-body .datagrid-cell", panel));
			}
		});

		function bindEvent(jqs) {
			jqs.mouseover(function () {
				var content = $(this).text();
				$(this).tooltip({
					content : content,
					trackMouse : true,
					onHide : function () {
						$(this).tooltip('destroy');
					}
				}).tooltip('show');
			});
		}
	}
});


/* ---------------表单验证扩展---------------- */
$.extend($.fn.validatebox.defaults.rules, {
	CHS: {//<input id="txtTruename" validType="CHS" required="true" type="text" />
		validator: function (value, param) {
			return /^[\u0391-\uFFE5]+$/.test(value);
		},
		message: '请输入汉字'
	},
	ZIP: {//<input id="txtTruename" validType="ZIP" required="true" type="text" />
		validator: function (value, param) {
			return /^[1-9]\d{5}$/.test(value);
		},
		message: '邮政编码不存在'
	},
	QQ: {
		validator: function (value, param) {
			return /^[1-9]\d{4,10}$/.test(value);
		},
		message: 'QQ号码不正确'
	},
	mobile: {
		validator: function (value, param) {
			return /^((\(\d{2,3}\))|(\d{3}\-))?13\d{9}$/.test(value);
		},
		message: '手机号码不正确'
	},
	baseCode: {
		validator: function (value, param) {
			return /^[\u0391-\uFFE5\w]+$/.test(value);
		},
		message: '登录名称只允许汉字、英文字母、数字及下划线。'
	},
	safepass: {
		validator: function (value, param) {
			return safePassword(value);
		},
		message: '密码由字母和数字组成，至少6位'
	},
	equalTo: {//<input id="pwd-agin" validType="equalTo['#pwd']" required="true" type="text" />
		validator: function (value, param) {
			return value == $(param[0]).val();
		},
		message: '两次输入的字符不一至'
	},
	number: {
		validator: function (value, param) {
			return /^\d+$/.test(value);
		},
		message: '请输入数字'
	},
	idcard: {
		validator: function (value, param) {
			return idCard(value);
		},
		message:'请输入正确的身份证号码'
	}
});

/* 密码由字母和数字组成，至少6位 */
var safePassword = function (value) {
	return !(/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/.test(value));
}

var idCard = function (value) {
	if (value.length == 18 && 18 != value.length) return false;
	var number = value.toLowerCase();
	var d, sum = 0, v = '10x98765432', w = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2], a = '11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91';
	var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/);
	if (re == null || a.indexOf(re[1]) < 0) return false;
	if (re[2].length == 9) {
		number = number.substr(0, 6) + '19' + number.substr(6);
		d = ['19' + re[4], re[5], re[6]].join('-');
	} else d = [re[9], re[10], re[11]].join('-');
	if (!isDateTime.call(d, 'yyyy-MM-dd')) return false;
	for (var i = 0; i < 17; i++) sum += number.charAt(i) * w[i];
	return (re[2].length == 9 || number.charAt(17) == v.charAt(sum % 11));
}

var isDateTime = function (format, reObj) {
	format = format || 'yyyy-MM-dd';
	var input = this, o = {}, d = new Date();
	var f1 = format.split(/[^a-z]+/gi), f2 = input.split(/\D+/g), f3 = format.split(/[a-z]+/gi), f4 = input.split(/\d+/g);
	var len = f1.length, len1 = f3.length;
	if (len != f2.length || len1 != f4.length) return false;
	for (var i = 0; i < len1; i++) if (f3[i] != f4[i]) return false;
	for (var i = 0; i < len; i++) o[f1[i]] = f2[i];
	o.yyyy = s(o.yyyy, o.yy, d.getFullYear(), 9999, 4);
	o.MM = s(o.MM, o.M, d.getMonth() + 1, 12);
	o.dd = s(o.dd, o.d, d.getDate(), 31);
	o.hh = s(o.hh, o.h, d.getHours(), 24);
	o.mm = s(o.mm, o.m, d.getMinutes());
	o.ss = s(o.ss, o.s, d.getSeconds());
	o.ms = s(o.ms, o.ms, d.getMilliseconds(), 999, 3);
	if (o.yyyy + o.MM + o.dd + o.hh + o.mm + o.ss + o.ms < 0) return false;
	if (o.yyyy < 100) o.yyyy += (o.yyyy > 30 ? 1900 : 2000);
	d = new Date(o.yyyy, o.MM - 1, o.dd, o.hh, o.mm, o.ss, o.ms);
	var reVal = d.getFullYear() == o.yyyy && d.getMonth() + 1 == o.MM && d.getDate() == o.dd && d.getHours() == o.hh && d.getMinutes() == o.mm && d.getSeconds() == o.ss && d.getMilliseconds() == o.ms;
	return reVal && reObj ? d : reVal;
	function s(s1, s2, s3, s4, s5) {
		s4 = s4 || 60, s5 = s5 || 2;
		var reVal = s3;
		if (s1 != undefined && s1 != '' || !isNaN(s1)) reVal = s1 * 1;
		if (s2 != undefined && s2 != '' && !isNaN(s2)) reVal = s2 * 1;
		return (reVal == s1 && s1.length != s5 || reVal > s4) ? -10000 : reVal;
	}
};



/*
* serialize:
* var data = $('form').form('serialize');
* 以json格式返回form表单数据 {"user":"user","email":"email@e.com"}

* getVal:
* $('form').form('getValue','email');

* setVal:
* $('form').form('setValue',{"user":"user","email":"email@e.com"});
*/
$.extend($.fn.form.methods, {
	serialize: function(jq){
		var arrayValue = $(jq[0]).serializeArray();
		var json = {};
		$.each(arrayValue, function() {
			var item = this;
			if (json[item['name']]) {
				json[item['name']] = json[item['name']] + ',' + item['value'];
			} else {
				json[item['name']] = item['value'];
			}
		});
		return json;
	},
	getVal:function(jq,name){
		var jsonValue = $(jq[0]).form('serialize');
		return jsonValue[name];
	},
	setVal:function(jq,data){
		return jq.each(function () {
				$(this).form('load',data);
		});
	}
});


