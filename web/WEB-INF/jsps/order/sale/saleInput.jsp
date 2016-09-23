<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>

<script type="text/javascript">
	function intToFloat(val){
		return new Number(val).toFixed(2);
	}
	$(function () {
		$("#submitOrder").click(function(){
			//将要发送的数据设置为可发送状态
			$(".goodsType").attr("disabled",false);
			$(".goods").attr("disabled",false);
			$("form:first").submit();
		});

		$(".goodsType").live("change",function(){

			var $nowTr = $(this).parent().parent();
			var $storage = $nowTr.children("td:eq(0)").children("select");
			var $gmSelect =	$nowTr.children("td:eq(1)").children("select");
			var $totalNum = $nowTr.children("td:eq(2)");
			var $num = $nowTr.children("td:eq(3)").children("input");
			var $prices = $nowTr.children("td:eq(4)").children("input");
			var $totalPrice = $nowTr.children("td:eq(5)");

			//发送类别的uuid到后台，获取商品信息进行展示
			var storageUuid = $(this).val();

			var goodsArr = $(".goods");
			var used = "";
			for(var i = 0;i<goodsArr.length;i++){
				used = used + "'"+goodsArr[i].value + "',";
			}

			//ajax请求
			$.post("order_ajaxGetGmSale.action",{"storageUuid":storageUuid,"gmUuids":used},function(data){
				$gmSelect.empty();
				//data中包含的数据 data.gmList  data.gm
				//修改商品select
				var s = data;
				for(var i = 0;i<s.length;i++){
					$op = $("<option value='"+s[i][2]+"'>"+s[i][3]+"</option>");
					$gmSelect.append($op);
				}

				var price = intToFloat(s[0][4]);
				$totalNum.html(s[0][1]);
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
			var $storage = $nowTr.children("td:eq(0)").children("select");
			var $gmSelect =	$nowTr.children("td:eq(1)").children("select");
			var $totalNum = $nowTr.children("td:eq(2)");
			var $num = $nowTr.children("td:eq(3)").children("input");
			var $prices = $nowTr.children("td:eq(4)").children("input");
			var $totalPrice = $nowTr.children("td:eq(5)");

			var gmUuid = $(this).val();
			$.post("order_ajaxGetGoods.action",{"gmUuid":gmUuid},function(data){
				//data包含的直接就是  //gmObj 0-num 1-uuid 2-name 3-outPrice
				var price = intToFloat(data[3]);
				$totalNum.html(data[0]);
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
			$(".goodsType").attr("disabled",true);
			$(".goods").attr("disabled",true);

			//load all the selected goods
//			var goodsArr = $(".goods");
//			var used = "";
//			for(var i =0; i <goodsArr.length;i++) {
//				used +="'"+goodsArr[i].value+"',";
//			}
			$tr = $('<tr align="center" bgcolor="#FFFFFF"></tr>');
			$td1 = $('<td height="30"></td>');
			$sel = $('<select name="storageUuids"  class="goodsType" style="width:160px"></select>');
			<s:iterator value="storageList" var="s">
				$op = $('<option value="${s.uuid}">${s.name}</option>')
				$sel.append($op);
			</s:iterator>
			$td1.append($sel);
			$tr.append($td1);
			$td2 = $('<td></td>');
			$sel2  = $('<select style="width:160px" name="goodsUuids" class="goods"></select>');
			<s:iterator value="sdmList" var="sdm">
				$op = $('<option value="${sdm[2]}">${sdm[3]}</option>');
				$sel2.append($op);
			</s:iterator>
			$td2.append($sel2);
			$tr.append($td2);
			$td3 = $('<td>${sdmList[0][1]}</td>');
			$tr.append($td3);
			$td4 = $('<td><input name="nums" class="num order_num" style="width:67px;border:1px solid black;text-align:right;padding:2px" value="1" type="text"></td>');
			$tr.append($td4);
			var price = ${sdmList[0][4]};
			price = intToFloat(price)
			$td5 = $('<td><input name="prices" class="prices order_num" style="width:93px;border:1px solid black;text-align:right;padding:2px" value="'+price+'" type="text"> 元</td>');
			$tr.append($td5);
			$td6 = $('<td class="total" align="right">'+price+'&nbsp;元</td>');
			$tr.append($td6);
			$td7 = $('<td><a class="deleteBtn delete xiu" value="${sdmList[0][2]}"><img src="images/icon_04.gif"> 删除</a></td>');
			$tr.append($td7);
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
			var $storage = $nowTr.children("td:eq(0)").children("select");
			var $gmSelect =	$nowTr.children("td:eq(1)").children("select");
			var $totalNum = $nowTr.children("td:eq(2)");
			var $num = $nowTr.children("td:eq(3)").children("input");
			var $prices = $nowTr.children("td:eq(4)").children("input");
			var $totalPrice = $nowTr.children("td:eq(5)");

			var total = $num.val() * $prices.val();
			//显示数据必须保留两位小数
			$totalPrice.html(intToFloat(total)+ " 元");
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
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">订单管理</span>
		</div>
	</div>
	<div class="content-text">
		<s:form action="order_sale" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td height="24">&nbsp;</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table id="order" width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(images/table_bg.gif) repeat-x;">
						<td width="20%" height="30">仓库名称</td>
						<td width="20%">商品名称</td>
						<td width="10%">储量</td>
						<td width="10%">采购数量</td>
						<td width="15%">单价</td>
						<td width="15%">合计</td>
						<td width="10%">操作</td>
					</tr>
					<tr align="center" bgcolor="#FFFFFF">
						<td height="30">
							<s:select name="storageUuids" cssClass="goodsType" list="storageList" listKey="uuid" listValue="name" cssStyle="width:160px"/>
						</td>
						<td>
							<select name="goodsUuids" style="width:160px" class="goods">
								<s:iterator value="sdmList" var="i">
								<option value="${i[2]}">${i[3]}</option>
								</s:iterator>
							</select>
						</td>
						<td>
							${sdmList[0][1]}
						</td>
						<td><input name="nums" class="num order_num" style="width:67px;border:1px solid black;text-align:right;padding:2px" type="text" value="1"/></td>
						<td><input name="prices" class="prices order_num" style="width:93px;border:1px solid black;text-align:right;padding:2px" type="text" value="${sdmList[0][4]}"/> 元</td>
						<td class="total" align="right">${sdmList[0][4]}&nbsp;元</td>
						<td>
							<a class="deleteBtn delete xiu" value="4"><img src="images/icon_04.gif" /> 删除</a>
						</td>
					</tr>
					<tr id="finalTr" align="center"
						style="background:url(images/table_bg.gif) repeat-x;">
						<td height="30" colspan="5" align="right">总&nbsp;计:&nbsp;</td>
						<td class="all" width="16%" align="right">${sdmList[0][4]}&nbsp;元</td>
						<td>
						<a id="add"><img src="images/can_b_02.gif" border="0" /> </a>
					</td>
					</tr>
				</table>
				<br>
				收货人:<input type="text" name="info.contact" style="width:150px"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				联系方式:<input type="text" name="info.tel" style="width:150px"/></br></br>
				地&nbsp;&nbsp;&nbsp;&nbsp;址:<input type="text" name="info.address" style="width:378px"/>
				<div class="order-botton">
				<div style="margin:1px auto auto 1px;">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td>
					    	<a href="javascript:void(0)" id="submitOrder"><img src="images/order_tuo.gif" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="images/order_tuo.gif" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="images/order_tuo.gif" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>
			</div>
		</s:form>
	</div>
	
	<div class="content-bbg"></div>
</div>
