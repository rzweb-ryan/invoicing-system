<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<link href="css/index.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
function intToFloat(val){
return new Number(val).toFixed(2);
}
//修改供应商
$(document).ready(function() {
    $("#supplier").change(function () {
        var uuid = $(this).val();
        $.post("order_ajaxGetGtmBySm.action", {"smUuid": uuid}, function (data) {
            $(".goodsType").empty();
            for(var i=0;i<data.gtmList.length;i++) {
                $option = "<option value='"+data.gtmList[i].uuid+"'>"+data.gtmList[i].name+"</option>"
                $(".goodsType").append($option);
            }
            $(".goods").empty();
            for(var i=0;i<data.gmList.length;i++) {
                $option = "<option value='"+data.gmList[i].uuid+"'>"+data.gmList[i].name+"</option>"
                $(".goods").append($option);
            }
            $("[name='nums']").val(1);
            $("[name='prices']").val(data.gmList[0].inPriceView);
            $(".total").html(data.gmList[0].inPriceView+" 元");

        });
    });
    $(".goodsType").live("change",function(){
        //load all the selected goods
        var goodsArr = $(".goods");
        var used = "";
        for(var i =0; i <goodsArr.length;i++) {
            used +="'"+goodsArr[i].value+"',";
        }


        var $nowTr = $(this).parent().parent();
        var $gmSelect =	$nowTr.children("td:eq(1)").children("select");
        var $num = $nowTr.children("td:eq(2)").children("input");
        var $prices = $nowTr.children("td:eq(3)").children("input");
        var $totalPrice = $nowTr.children("td:eq(4)");

        //发送类别的uuid到后台，获取商品信息进行展示
        var gtmUuid = $(this).val();

        var goodsArr = $(".goods");
        var used = "";
        for(var i = 0;i<goodsArr.length;i++){
            used = used + "'"+goodsArr[i].value + "',";
        }

        //ajax请求
        $.post("order_ajaxGetGmByGtm.action",{"gtmUuid":gtmUuid,"gmUuids":used},function(data){
            $gmSelect.empty();
            //data中包含的数据 data.gmList  data.gm
            //修改商品select
            var gmList = data.gmList;
            for(var i = 0;i<gmList.length;i++){
                var gm = gmList[i];
                $op = $("<option value='"+gm.uuid+"'>"+gm.name+"</option>");
                $gmSelect.append($op);
            }

            var price = data.gmList[0].inPriceView;
            //修改数量为1
            $num.val(1);
            //修改单价
            $prices.val(price);
            //修改合计
            $totalPrice.html(price +" 元");
            //计算总价
            total2();
        });

    });
    $(".goods").live("change",function(){

    var $nowTr = $(this).parent().parent();
    var $num = $nowTr.children("td:eq(2)").children("input");
    var $prices = $nowTr.children("td:eq(3)").children("input");
    var $totalPrice = $nowTr.children("td:eq(4)");

    var gmUuid = $(this).val();
    $.post("order_ajaxGetGm.action",{"gmUuid":gmUuid},function(data){
        //data包含的直接就是gm对象
        var price = data.inPriceView;
        //修改数量为1
        $num.val(1);
        //修改单价
        $prices.val(price);
        //修改合计
        $totalPrice.html(price +" 元");
        //计算总价
        total2();
    });
});




    $("#add").click(function () {
        //lock
        $("#supplier").attr("disabled",true);
        $(".goodsType").attr("disabled",true);
        $(".goods").attr("disabled",true);

        //load all the selected goods
        var goodsArr = $(".goods");
        var used = "";
        for(var i =0; i <goodsArr.length;i++) {
            used +="'"+goodsArr[i].value+"',";
        }


        $.post("order_ajaxGetGtmAndGm.action", {"smUuid": $("#supplier").val(),"gmUuids":used}, function (data) {

            if(data.gtmList.length==1&&data.gmList.length==1) {
                $("#add").css("display","none");
            }
            $tr = $('<tr bgcolor="#FFFFFF" align="center"></tr>');

            $td1 = $('<td height="30"></td>');
            //类别select:class="goodsType"
            $gtmSelect = $('<select style="width:200px" class="goodsType"></select>');
            var gtmList = data.gtmList;
            for(var i = 0;i<gtmList.length;i++){
                var gtm = gtmList[i];
                $op = $('<option value="'+gtm.uuid+'">'+gtm.name+'</option>');
                $gtmSelect.append($op);
            }
            $td1.append($gtmSelect);
            $tr.append($td1);

            $td2 = $('<td></td>');
            //类别select:class="goods"
            $gmSelect = $('<select name="goodsUuids" style="width:200px" class="goods"></select>');
            var gmList = data.gmList;
            for(var i = 0;i<gmList.length;i++){
                var gm = gmList[i];
                $op = $('<option value="'+gm.uuid+'">'+gm.name+'</option>');
                $gmSelect.append($op);
            }
            $td2.append($gmSelect);
            $tr.append($td2);

            $td3 = $('<td><input type="text" value="1" style="width:67px;border:1px solid black;text-align:right;padding:2px" class="num order_num" name="nums"></td>');
            $tr.append($td3);

            var price = data.gm.inPriceView;
            $td4 = $('<td><input type="text" value="'+price+'" style="width:93px;border:1px solid black;text-align:right;padding:2px" class="prices order_num" name="prices"> 元</td>');
            $tr.append($td4);

            $td5 = $('<td align="right" class="total">'+price+'&nbsp;元</td>');
            $tr.append($td5);

            $td6 = $('<td><a value="4" class="deleteBtn delete xiu"><img src="images/icon_04.gif"> 删除</a></td>');
            $tr.append($td6);
            $("#finalTr").before($tr);
            total2();
        });
    });

    $(".deleteBtn").live("click", function () {
        if($(".deleteBtn").length == 1){
            return;
        }
        $(this).parent().parent().remove();
        $("#add").css("display","inline");
        total2();
    });
    $(".num").live("keyup",function(){
        //先把非数字的都替换掉，除了数字
        $(this).val($(this).val().replace(/[^\d]/g,""));
        totalPrice($(this));
        //计算总价
        total2();
    });
    $(".prices").live("keyup",function(){
        //先把非数字的都替换掉，除了数字和.
        $(this).val($(this).val().replace(/[^\d.]/g,""));
        //必须保证第一个为数字而不是.
        $(this).val($(this).val().replace(/^\./g,"0."));
        //保证只有出现一个.而没有多个.
        $(this).val($(this).val().replace(/\.{2,}/g,"."));
        //保证.只出现一次，而不能出现两次以上
        $(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$","."));
        totalPrie($(this));
        //计算总价
        total2();
    });

    function totalPrice(obj){
        var $nowTr = obj.parent().parent();
        var $num =   $nowTr.children("td:eq(2)").children("input");
        var $price = $nowTr.children("td:eq(3)").children("input");
        var $total = $nowTr.children("td:eq(4)");

        var total = $num.val() * $price.val();
        //显示数据必须保留两位小数
        $total.html(intToFloat(total)+ " 元");
    }

    //求总计
    function total2(){
        //获取所有的采购数量
        var numArr = $(".num");
        //获取所有的采购单价
        var priceArr = $(".prices");
        //相乘求和
        var sum = 0;
        for(var i = 0;i<numArr.length;i++){
            var total = numArr[i].value * priceArr[i].value;
            sum += total;
        }
        //将最终结果放置到总计位置
        $(".all").html(intToFloat(sum)+" 元");
    }

    $("#submitOrder").click(function(){
        //将要发送的数据设置为可发送状态
        $("#supplier").attr("disabled",false);
        $(".goods").attr("disabled",false);
        $("form:first").submit();
    });
});
</script>
<div class="content-right">
    <div class="content-r-pic_w">
        <div style="margin:8px auto auto 12px;margin-top:6px">
            <span class="page_title">订单管理</span>
        </div>
    </div>
    <div class="content-text">
        <form action="order_buy.action" method="post">
            <div class="square-o-top">
                <table width="100%" border="0" cellpadding="0" cellspacing="0"
                       style="font-size:14px; font-weight:bold; font-family:'黑体';">
                <tr>
                    <td height="24">&nbsp;</td>
                </tr>

                <tr>
                    <td width="68px" height="30">供应商：</td>
                    <td width="648px">
                        <s:select id="supplier" name="om.sm.uuid" cssClass="kuan" cssStyle="width:190px" list="supplierList"
                                  listKey="uuid" listValue="name"/>
                    </td>
                    <td height="30">
                        <a id="add"><img src="images/can_b_02.gif" border="0"/> </a>
                    </td>
                </tr>
                </table>
            </div>
            <!--"square-o-top"end-->
            <div class="square-order">
                <table id="order" width="100%" border="1" cellpadding="0" cellspacing="0">
                    <tr align="center"
                        style="background:url(images/table_bg.gif) repeat-x;">
                        <td width="25%" height="30">商品类别</td>
                        <td width="25%">商品名称</td>
                        <td width="10%">采购数量</td>
                        <td width="15%">单价</td>
                        <td width="15%">合计</td>
                        <td width="10%">操作</td>
                    </tr>
                    <tr align="center" bgcolor="#FFFFFF">
                        <td height="30">
                            <s:select cssClass="goodsType" cssStyle="width:200px" list="goodsTypeList" listKey="uuid"
                                      listValue="name"/>
                        </td>
                        <td>
                            <s:select name="goodsUuids" cssClass="goods" cssStyle="width:200px" list="goodsList" listKey="uuid"
                                      listValue="name"/>
                        </td>
                        <td><input name="nums" class="num order_num"
                                   style="width:67px;border:1px solid black;text-align:right;padding:2px" type="text"
                                   value="1"/></td>
                        <td><input name="prices" class="prices order_num"
                                   style="width:93px;border:1px solid black;text-align:right;padding:2px" type="text"
                                   value="${goodsList[0].inPrice}"/> 元
                        </td>
                        <td class="total" align="right">${goodsList[0].inPrice}&nbsp;元</td>
                        <td>
                            <a class="deleteBtn delete xiu" value="4"><img src="images/icon_04.gif"/> 删除</a>
                        </td>
                    </tr>
                    <tr id="finalTr" align="center"
                        style="background:url(images/table_bg.gif) repeat-x;">
                        <td height="30" colspan="4" align="right">总&nbsp;计:&nbsp;</td>
                        <td class="all" width="16%" align="right">${goodsList[0].inPrice}&nbsp;元</td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
                <div class="order-botton">
                    <div style="margin:1px auto auto 1px;">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
                                    <a href="javascript:void(0)" id="submitOrder"><img src="images/order_tuo.gif"
                                                                                       border="0"/></a>
                                </td>
                                <td>&nbsp;</td>
                                <td><a href="#"><img src="images/order_tuo.gif" border="0"/></a></td>
                                <td>&nbsp;</td>
                                <td><a href="javascript:history.back();"><img src="images/order_tuo.gif" border="0"/></a></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <div class="content-bbg"></div>
</div>
