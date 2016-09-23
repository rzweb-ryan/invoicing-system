<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(function () {

//
//				<option value="11">1号仓库</option>
//				<option value="22">2号仓库</option>
//				<option value="33">3号仓库</option>
//
//
		var uuid = new Array();
		var name = new Array();
		var i =0;
		<s:iterator value="storageList">
			uuid[i] = ${uuid}
			name[i] = "${name}";
			i++;
		</s:iterator>
		$(".oper").click(function () {
			$(".in").remove();
			$nowTr = $(this).parent().parent();
			$tr = $('<tr class="in"></tr>');
			$td1 = $('<td align="right">仓库：</td>');
			$tr.append($td1);
			$td2 = $('<td height="30"></td>');
			$select = $('<select id="storage" style="width:200px"></select>');
			for(var i=0;i<uuid.length;i++) {
				$op = $('<option value="'+uuid[i]+'">'+name[i]+'</option>');
				$select.append($op);
			}
			$td2.append($select);
			$tr.append($td2);
			$td3 = $('<td align="right">入库量：</td>');
			$tr.append($td3);
			$td4 = $('<td></td>');
			$in = $('<input id="inNum" value="'+$nowTr.children("td:eq(3)").text()+'" type="text">');
			$td4.append($in);
			$tr.append($td4);
			$td5 = $('<td align="center"><a href="javascript:void(0)" class="ajaxIn xiu"><img src="images/icon_3.gif">确定</a></td>');
			$tr.append($td5);
			$nowTr.after($tr);
		});
		$(".ajaxIn").live("click", function () {
			$nowTr = $(this).parent().parent();
			$prevTr = $nowTr.prev();
			var jsonParam = {};
			jsonParam["odmUuid"] = $prevTr.attr("odm");
			jsonParam["inNum"] = $("#inNum").val();
			jsonParam["storageUuid"] =$("#storage").val();
			$.post("store_ajaxIn.action",jsonParam,function (data) {
				var totalNum = data.num;
				var surPlus = data.surPlus;
				if(surPlus==0) {
					if($(".row").length==1) {
						$("#inOrder").css("display","none");
						$("#allInTitle").css("display","block");
						$("#return").css("display","block");
					}
					$nowTr.remove();
					$prevTr.remove();
					return;
				}
				$prevTr.children("td:eq(2)").html(totalNum-surPlus);
				$prevTr.children("td:eq(3)").html(surPlus);
				$("#inNum").val(surPlus);
			})
		});
	});
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">入库</span>
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
						<td width="10%">已入库数量</td>
						<td width="30%">剩余数量</td>
						<td width="10%">入库</td>
					</tr>
					<s:iterator value="om.odms">
						<s:if test="surPlus > 0">
							<tr odm="${uuid}" aa="bb" align="center" bgcolor="#FFFFFF" class="row">
								<input type="hidden" value=1/>
								<input type="hidden" value=2/>
								<td height="30">${gm.name}</td>
								<td>${num}</td>
								<td>${num-surPlus}</td>
								<td>${surPlus}</td>
								<td>
									<s:if test="surPlus==0">
										<b>已完成</b>
									</s:if><s:else>
									<a href="javascript:void(0)" class="oper xiu"><img src="images/icon_3.gif" />入库</a>
								</s:else>
								</td>
							</tr>
						</s:if>
					</s:iterator>
				</table>

				<center id="allInTitle" style="display:none;font-size:16px; font-weight:bold; font-family:"黑体";">&nbsp;&nbsp;&nbsp;&nbsp;全&nbsp;&nbsp;部&nbsp;&nbsp;入&nbsp;&nbsp;库&nbsp;&nbsp;&nbsp;&nbsp;</center>
				<table id="return" style="display:none" >
					<tr>
						<td>&nbsp;</td>
						<td width="100%" align="center">
							<a href="store_storeIn.action" style="color:#f00;font-size:20px;padding-top:2px;font-weight:bold;text-decoration:none;width:82px;height:28px;display:block;background:url(images/btn_bg.jpg)">
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
