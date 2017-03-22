
function initRegion(obj_1, val_1, obj_2, val_2, obj_3, val_3) { 
    //清空原来数据
    $("#" + obj_1).empty();
    $("#" + obj_2).empty();
    $("#" + obj_3).empty();

    $("#" + obj_1).unbind("change");
    $("#" + obj_2).unbind("change");
    $("#" + obj_3).unbind("change");

    //定义默认数据
    var ar = ["请选择省份", "请选择城市", "请选择区县"];
    var pindex = 0;
    var cindex = 0;

    //初始化
    $("<option value=''>" + ar[0] + "</option>").appendTo($("#" + obj_1));
    $("<option value=''>" + ar[1] + "</option>").appendTo($("#" + obj_2));
    $("<option value=''>" + ar[2] + "</option>").appendTo($("#" + obj_3));


    //初始化obj_1
    //Ajax获取省列表
    $.post("/Handler/RegionHandler.ashx", { oper: "GetProvinceList" }, function (data) {
        if (data == "") {
            $("#" + obj_1).empty();
            $("#" + obj_2).empty();
            $("#" + obj_3).empty();
            $("<option value=''>" + ar[0] + "</option>").appendTo($("#" + obj_1));
            $("<option value=''>" + ar[1] + "</option>").appendTo($("#" + obj_2));
            $("<option value=''>" + ar[2] + "</option>").appendTo($("#" + obj_3));
        }
        else {
            mp = eval('(' + data + ')');

            for (i = 0; i < mp.length; i++) {
                if (mp[i].id == val_1||mp[i].code==val_1) {
                    pindex = mp[i].id;
                    $("<option selected value='" + mp[i].id + "' code='"+mp[i].code+"'>" + mp[i].text + "</option>").appendTo($("#" + obj_1));
                } else {
                    $("<option value='" + mp[i].id + "' code='" + mp[i].code + "'>" + mp[i].text + "</option>").appendTo($("#" + obj_1));
                }
            }
            //初始化市
            if (pindex != 0) {
                $.post("/Handler/RegionHandler.ashx", { oper: "GetCityList", ProvinceID: pindex }, function (data) {
                    if (data == "") {

                        $("#" + obj_2).empty();
                        $("#" + obj_3).empty();

                        $("<option value=''>" + ar[1] + "</option>").appendTo($("#" + obj_2));
                        $("<option value=''>" + ar[2] + "</option>").appendTo($("#" + obj_3));
                    }
                    else {
                        mc = eval('(' + data + ')');
                        for (n = 0; n < mc.length; n++) {
                            if (mc[n].id == val_2||mc[n].code==val_2) {
                                cindex = mc[n].id;
                                $("<option selected value='" + mc[n].id + "' code='" + mc[n].code + "'>" + mc[n].text + " </option>").appendTo($("#" + obj_2));
                            } else {
                                $("<option value='" + mc[n].id + "' code='" + mc[n].code + "'>" + mc[n].text + " </option>").appendTo($("#" + obj_2));
                            }
                        }
                        //初始化区
                        if (cindex != 0) {
                            $.post("/Handler/RegionHandler.ashx", { oper: "GetAreaList", ProvinceID: pindex, CityID: cindex }, function (data) {
                                if (data == "") {


                                    $("#" + obj_3).empty();


                                    $("<option value=''>" + ar[2] + "</option>").appendTo($("#" + obj_3));
                                }
                                else {
                                    mh = eval('(' + data + ')');
                                    for (h = 0; h < mh.length; h++) {
                                        if (mh[h].id == val_3||mh[h].code==val_3) {
                                            $("<option selected value='" + mh[h].id + "' code='"+mh[h].code+"'>" + mh[h].text + " </option>").appendTo($("#" + obj_3));
                                        } else {
                                            $("<option value='" + mh[h].id + "' code='" + mh[h].code + "'>" + mh[h].text + " </option>").appendTo($("#" + obj_3));
                                        }
                                    }
                                }
                            });
                        }
                    }
                });
            }
        }


    })

    //绑定选择事件
    //响应obj_1的change事件
    $("#" + obj_1).change(function () {
        //获取索引
        //pindex = $("#" + obj_1).get(0).selectedIndex;
        pindex = $("#" + obj_1).val();

        //选择是为选择状态
        if (pindex == "0" || pindex=="") {
            $("#" + obj_2).empty();
            $("<option value=''>" + ar[1] + "</option>").appendTo($("#" + obj_2));

            $("#" + obj_3).empty();
            $("<option value=''>" + ar[2] + "</option>").appendTo($("#" + obj_3));
        }
        else {

            //清空城市和区域
            $("#" + obj_2).empty();
            $("<option value=''>" + ar[1] + "</option>").appendTo($("#" + obj_2));
            //重新给城市填充内容
            $.post("/Handler/RegionHandler.ashx", { oper: "GetCityList", ProvinceID: pindex }, function (data) {
                if (data == "") {

                    $("#" + obj_2).empty();
                    $("#" + obj_3).empty();

                    $("<option value=''>" + ar[1] + "</option>").appendTo($("#" + obj_2));
                    $("<option value=''>" + ar[2] + "</option>").appendTo($("#" + obj_3));
                }
                else {
                    mc = eval('(' + data + ')');
                    for (n = 0; n < mc.length; n++) {
                        $("<option value='" + mc[n].id + "' code='"+mc[n].code+"'>" + mc[n].text + " </option>").appendTo($("#" + obj_2));

                    }
                }
            });
            //清空区域
            $("#" + obj_3).empty();
            $("<option value=''>" + ar[2] + "</option>").appendTo($("#" + obj_3));
        }
    });

    //响应obj_2的change事件
    $("#" + obj_2).change(function () {
        cindex = $("#" + obj_2).val();
        pindex = $("#" + obj_1).val();

        //选择是为选择状态
        if (cindex == "0" || cindex == "") {
            $("#" + obj_3).empty();
            $("<option value=''>" + ar[2] + "</option>").appendTo($("#" + obj_3));
        }
        else {
            //清空h
            $("#" + obj_3).empty();
            //重新给h填充内容
            $("<option value=''>" + ar[2] + "</option>").appendTo($("#" + obj_3));
            if (cindex != 0 && cindex != "") {
                $.post("/Handler/RegionHandler.ashx", { oper: "GetAreaList", ProvinceID: pindex, CityID: cindex }, function (data) {
                    if (data == "") {


                        $("#" + obj_3).empty();


                        $("<option value=''>" + ar[2] + "</option>").appendTo($("#" + obj_3));
                    }
                    else {
                        mh = eval('(' + data + ')');
                        for (h = 0; h < mh.length; h++) {
                            if (mh[h].id == val_3||mh[h].code==val_3) {
                                $("<option selected value='" + mh[h].id + "' code='"+mh[h].code+"'>" + mh[h].text + " </option>").appendTo($("#" + obj_3));
                            } else {
                                $("<option value='" + mh[h].id + "' code='"+mh[h].code+"'>" + mh[h].text + " </option>").appendTo($("#" + obj_3));
                            }
                        }
                    }
                });
            }
        }
    });

   
}