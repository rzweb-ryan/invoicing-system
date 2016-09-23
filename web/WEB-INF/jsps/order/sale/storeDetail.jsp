<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(function () {
		$(".oper").click(function () {
			$nowTr = $(this).parent().parent();
			var uuid = $nowTr.attr("odm");
			$.post("store_out.action",{odmUuid:uuid},function (data) {
				$nowTr.remove();
				if($(".row").length==0) {
					$("#inOrder").css("display","none");
					$("#allInTitle").css("display","block");
					$("#return").css("display","block");
				}
			})
		});

	});
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">出库</span>
		</div>
	</div>
	<div class="content-text">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td>订 单 号:</td>
						<td class="order_show_msg">${om.orderNum}</td>
						<td>商品总量:</td>
						<td class="order_show_msg">${om.totalNum}</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<center id="inOrderTitle" style="text-decoration:underline;font-size:16px; font-weight:bold; font-family:"黑体";">&nbsp;&nbsp;&nbsp;&nbsp;单&nbsp;&nbsp;据&nbsp;&nbsp;明&nbsp;&nbsp;细&nbsp;&nbsp;&nbsp;&nbsp;</center>
				<br/>
				<table id="inOrder" width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(images/table_bg.gif) repeat-x;">
						<td width="20%" height="30">商品名称</td>
						<td width="30%">总数量</td>
						<td width="10%">已出库数量</td>
						<td width="30%">剩余数量</td>
						<td width="10%">出库</td>
					</tr>
					<s:iterator value="om.odms">
						<s:if test="surPlus > 0">
							<tr odm="${uuid}" aa="bb" align="center" bgcolor="#FFFFFF" class="row">
								<td height="30">${gm.name}</td>
								<td>${num}</td>
								<td>${num-surPlus}</td>
								<td>${surPlus}</td>
								<td>
									<s:if test="surPlus==0">
										<b>已完成</b>
									</s:if><s:else>
									<a href="javascript:void(0)" class="oper xiu"><img src="images/icon_3.gif" />出库</a>
								</s:else>
								</td>
							</tr>
						</s:if>
					</s:iterator>
				</table>

				<center id="allInTitle" style="display:none;font-size:16px; font-weight:bold; font-family:"黑体";">&nbsp;&nbsp;&nbsp;&nbsp;全&nbsp;&nbsp;部&nbsp;&nbsp;出&nbsp;&nbsp;库&nbsp;&nbsp;&nbsp;&nbsp;</center>
				<table id="return" style="display:none" >
					<tr>
						<td>&nbsp;</td>
						<td width="100%" align="center">
							<a href="store_storeOut.action" style="color:#f00;font-size:20px;padding-top:2px;font-weight:bold;text-decoration:none;width:82px;height:28px;display:block;background:url(images/btn_bg.jpg)">
								返回
							</a>
						</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
	</div>
	<div class="content-bbg"></div>
</div>
