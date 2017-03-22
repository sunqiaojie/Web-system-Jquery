window.onload = function () {
    $('#loading-mask').fadeOut();
}
function OpenNewWin(url) {
    var curl = window.location.href;
    curl = curl.substring(0, curl.indexOf('/'));
    curl = curl + "/" + url;
    window.open(curl);
}

function GridHiddenColumns(grid, cols)
{
    if (cols != null && cols != undefined && cols.length > 0) {
        for (var i = 0; i < cols.length; i++) {
            $("#" + grid).datagrid("hideColumn", cols[i]);
        }
    }
}
function TreeGridHiddenColumns(treegrid, cols) {
    if (cols != null && cols != undefined && cols.length > 0) {
        for (var i = 0; i < cols.length; i++) {
            $("#" + treegrid).treegrid("hideColumn", cols[i]);
        }
    }
}

var AlertWarn = function (divid, content) {
    $("#" + divid).empty();
    var html = "<div class=\"alert\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button><strong>提示： </strong> " + content + "</div>";
    $("#" + divid).html(html);
    $("body").oneTime("10s", "AlertWarn", function () { $("#" + divid).empty(); })
    //$(".SearchDiv").show();
}

var AlertError = function (divid, content) {
    $("#" + divid).empty();
    var html = "<div class=\"alert alert-error\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button><strong>错误： </strong> " + content + "</div>";
    $("#" + divid).html(html);
    //$("body").oneTime("3s", "AlertError", function () { $("#" + divid).empty(); })
    //$(".SearchDiv").show();
}

var AlertSuccess = function (divid, content) {
    $("#" + divid).empty();
    var html = "<div class=\"alert alert-success\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button><strong>成功： </strong> " + content + "</div>";
    $("#" + divid).html(html);
    $("body").oneTime("5s", "AlertSuccess", function () { $("#" + divid).empty(); })
    //$(".SearchDiv").show();
}

var AlertAppend = function (divid, content, type) {
    var html = "<div class=\"alert alert-" + type + "\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button><strong>提示： </strong> " + content + "</div>";
    $("#" + divid).append(html);
    $("body").stopTime("AlertAppend");
    $("body").oneTime("3s", "AlertAppend", function () { $("#" + divid).empty(); })
    //$(".SearchDiv").show();
}

var GetParam = function (paramName) {
    paramValue = "";
    isFound = false;
    if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) {
        arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&");
        i = 0;
        while (i < arrSource.length && !isFound) {
            if (arrSource[i].indexOf("=") > 0) {
                if (arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase()) {
                    paramValue = arrSource[i].split("=")[1];
                    isFound = true;
                }
            }
            i++;
        }
    }
    return paramValue;
}

function DataGridGetRowDataByRowIndex(gridid, rowindex) {
    var rowData = null;
    var rows = $("#" + gridid).datagrid("getRows");
    if (rows != null && rows != undefined && rows.length > 0) {
        for (var i = 0; i < rows.length; i++) {
            var rindex = $("#" + gridid).datagrid("getRowIndex", rows[i]);
            if (rindex == rowindex) {
                rowData = rows[i];
                break;
            }
        }
    }
    return rowData;
}

Array.prototype.in_array = function (e) {
    for (i = 0; i < this.length && this[i] != e; i++);
    return !(i == this.length);
}
String.prototype.stringToDate = function () {
    return new Date(Date.parse(this.replace(/-/g, "/")));
}


function ChangeDateFormat(jsondate) {
    jsondate = jsondate.replace("/Date(", "").replace(")/", "");

    if (jsondate.indexOf("+") > 0) {
        jsondate = jsondate.substring(0, jsondate.indexOf("+"));
    } else if (jsondate.indexOf("-") > 0) {
        jsondate = jsondate.substring(0, jsondate.indexOf("-"));
    }
    var date = new Date(parseInt(jsondate, 10));
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    return date.getFullYear() + "-" + month + "-" + currentDate + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
}

function ChangeDateFormat2YMD(jsondate) {

    if (jsondate == null || jsondate == undefined) {
        return "";
    }


    jsondate = jsondate.replace("/Date(", "").replace(")/", "");

    if (jsondate.indexOf("+") > 0) {
        jsondate = jsondate.substring(0, jsondate.indexOf("+"));
    } else if (jsondate.indexOf("-") > 0) {
        jsondate = jsondate.substring(0, jsondate.indexOf("-"));
    }
    var date = new Date(parseInt(jsondate, 10));
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    return date.getFullYear() + "-" + month + "-" + currentDate;
}


/*取得当前时间*/
function getdate() {
    var date = new Date(); //日期对象
    var now = "";
    now = date.getFullYear() + "-"; //读英文就行了
    now = now + (parseInt(date.getMonth() + 1) < 10 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1)) + "-"; //取月的时候取的是当前月-1如果想取当前月+1就可以了
    now = now + (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + " ";
    now = now + (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":";
    now = now + (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) + ":";
    now = now + (date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds()) + "";
    return now; //div的html是now这个字符串

}

function FormatDate2String(date) {
    var now = "";
    now = date.getFullYear() + "-"; //读英文就行了
    now = now + (parseInt(date.getMonth() + 1) < 10 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1)) + "-"; //取月的时候取的是当前月-1如果想取当前月+1就可以了
    now = now + (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + " ";
    now = now + (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":";
    now = now + (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) + ":";
    now = now + (date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds()) + "";
    return now;
}


/*弹出层显示图片*/
var ShowPict = function (url) {
    $.layer({
        type: 1,
        title: false,
        fix: false,
        offset: ['10%', '50%'],
        area: ['500px', 'auto'],
        border: false,
        move: ['.imgmove', true],
        page: { html: '<img src="' + url + '" style="width:500px;" class="imgmove">' }
    });
}

var HideOperation = function (document, pagename) {
    //$(this).find("form[action='" + pagename + "'] .OperationDiv").hide();
    $(document).find("form[action='" + pagename + "'] .OperationDiv").css("display", "none");
}

var ShowOperation = function (document, pagename) {
    $(document).find("form[action='" + pagename + "'] .OperationDiv").show();
}

var HideSearch = function (document, pagename) {
    //$(this).find("form[action='" + pagename + "'] .OperationDiv").hide();
    $(document).find("form[action='" + pagename + "'] .SearchDiv").css("display", "none");
    $(document).find("form[action='" + pagename + "'] .DivSearchDivShow").css("display", "none");
}

var ShowSearch = function (document, pagename) {
    $(document).find("form[action='" + pagename + "'] .SearchDiv").show();//.css("display", "");
    $(document).find("form[action='" + pagename + "'] .DivSearchDivShow").empty();//.css("display", "");
}

var HideContent = function (document, pagename) {
    //$(this).find("form[action='" + pagename + "'] .OperationDiv").hide();
    $(document).find("form[action='" + pagename + "'] .ContentDiv").css("display", "none");
}

var ShowContent = function (document, pagename) {
    $(document).find("form[action='" + pagename + "'] .ContentDiv").css("display", "");
}

function isMobel(value) {
    if (/^13\d{9}$/g.test(value) || (/^15\d{9}$/g.test(value)) ||
        (/^18\d{9}$/g.test(value)) || (/^17\d{9}$/g.test(value))) {
        return true;
    } else {
        return false;
    }
}

function isTel(value) {
    return (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/.test(value)) || (/^(\d{7,8})(-(\d{3,}))?$/.test(value));
}

function isMail(mail) {
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if (filter.test(mail)) return true;
    else {
        return false;
    }
}

function setAjaxAsync(value) {
    $.ajaxSetup({
        async: value
    });
}
