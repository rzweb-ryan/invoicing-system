<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<s:hidden name="pageNum"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="51%">&nbsp;</td>
		<td width="13%">共${totalCount}条记录
		<td width="6%">
			<a id="first" class="sye">首&nbsp;&nbsp;页</a>
		</td>
		<td width="6%">
			<a id="prev" class="sye">上一页</a>
		</td>
		<td width="6%">
			<a id="next" class="sye" id="next">下一页</a>
		</td>
		<td width="6%">
			<a id="last" class="sye">末&nbsp;&nbsp;页</a>
		</td>
		<td width="12%">当前第<span style="color:red;">${pageNum}</span>/${totalPage}页</td>
	</tr>
</table>
<script>
	$(function() {
		var pageNum = ${pageNum};
		var totalPage = ${totalPage};
		if(totalPage==1) {
			$("#first").css("display","none");
			$("#last").css("display","none");
			$("#prev").css("display","none");
			$("#next").css("display","none");
		}else if(pageNum==1) {
			$("#first").css("display","none");
			$("#prev").css("display","none");
			$("#next").css("display","inline");
			$("#last").css("display","inline");
		}else if (pageNum==totalPage) {
			$("#first").css("display","inline");
			$("#prev").css("display","inline");
			$("#next").css("display","none");
			$("#last").css("display","none");
		}else {
			$("#first").css("display","inline");
			$("#prev").css("display","inline");
			$("#next").css("display","inline");
			$("#last").css("display","inline");
		}
		$("#next").click(function() {
			$("[name=pageNum]").val($("[name=pageNum]").val()*1+1);
			$("form:first").submit();
		});
		$("#prev").click(function() {
			$("[name=pageNum]").val($("[name=pageNum]").val()-1);
			$("form:first").submit();
		});
		$("#first").click(function() {
			$("[name=pageNum]").val(1);
			$("form:first").submit();
		});
		$("#last").click(function() {
			$("[name=pageNum]").val(totalPage);
			$("form:first").submit();
		});
	});
</script>
