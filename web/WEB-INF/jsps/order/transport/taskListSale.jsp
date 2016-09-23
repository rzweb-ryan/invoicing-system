<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
	});
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">任务查询</span>
		</div>
	</div>
	<div class="content-text">
		<form action="taskList.jsp" method="post">
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(images/table_bg.gif) repeat-x;">
						<td width="8%" height="30">订单类别</td>
						<td width="6%">联系人</td>
						<td width="12%">联系方式</td>
						<td width="27%">地址</td>
						<td width="8%">状态</td>
						<td width="9%">详情</td>
					</tr>
					<s:iterator value="infoList">
						<tr align="center" bgcolor="#FFFFFF">
							<td height="30">${om.orderTypeView}</td>
							<td>${contact}</td>
							<td>${tel}</td>
							<td align="left">&nbsp;${address}</td>
							<td><b>${om.statusView}</b></td>
							<td>
								<a href="transport_taskDetailSale.action?info.uuid=${uuid}">
									<img src="images/icon_3.gif" />详情
								</a>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
