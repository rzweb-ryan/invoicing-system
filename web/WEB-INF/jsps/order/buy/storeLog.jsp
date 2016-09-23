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
			<span class="page_title">仓库货物明细</span>
		</div>
	</div>
	<div class="content-text">
		<s:form action="store_inList.action" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="10%">仓库名称:</td>
						<td width="20%"><s:textfield name="sdqm.sm.name" size="20"/> </td>
						<td>管理员:</td>
						<td><s:textfield name="sdqm.sm.em.name" size="20"/> </td>
						<td>货物名称:</td>
						<td><s:textfield name="sdqm.gm.name" size="20"/> </td>
						<td width="">
							<a id="query"><img src="images/can_b_01.gif" border="0" /> </a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(images/table_bg.gif) repeat-x;">
						<td width="25%" height="30">仓库名称</td>
						<td width="25%">仓库管理员</td>
						<td width="25%">货物名称</td>
						<td width="25%">当前库存量</td>
					</tr>
						<s:iterator value="sdms">
						<tr align="center" bgcolor="#FFFFFF">
							<td height="30">${sm.name}</td>
							<td>${sm.em.name}</td>
							<td>${gm.name}</td>
							<td>${num}&nbsp;${gm.unit}</td>
						</tr>
						</s:iterator>
				</table>
				<%@include file="/WEB-INF/jsps/tools/paging.jsp"%>
			</div>
		</s:form>
	</div>
	<div class="content-bbg"></div>
</div>
