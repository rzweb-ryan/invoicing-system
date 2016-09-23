<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/Calendar.js"></script>
<script type="text/javascript">
	$(function() {
		$("#all").click(function() {
			$("[name=roles]:checkbox").attr("checked",$("#all").attr("checked")=="checked");
		});
		$("#reverse").click(function() {
			$("[name=roles]:checkbox").each(function () {
                $(this).attr("checked", !$(this).attr("checked"));
            });
			checkSelect();
		});
		$("[name=roles]:checkbox").click(
				function () {
					checkSelect();
				}
		);
	});
	function checkSelect() {
		var flag = true;
		$("[name=roles]").each(function () {
			flag  = flag && ($(this).attr("checked")=="checked")
			$("#all").attr("checked",flag);
		})
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">员工管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<s:form action="emp_save" method="post">
			<s:hidden name="em.uuid"/>
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">用&nbsp;户&nbsp;名</td>
				      <td width="32%">
						  <s:textfield name="em.username" type="text" size="25"/>
					  </td>
				      <td width="18%"align="center">真实姓名</td>
				      <td width="32%">
						  <s:textfield name="em.name" type="text" size="25"/>
					  </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td align="center">密&nbsp;&nbsp;&nbsp;&nbsp;码</td>
				      <td>
						  <s:password name="em.pwd" showPassword="true" type="text" size="25"/>

					  </td>
				      <td  align="center">确认密码</td>
				      <td >
						  <s:password name="em.pwd" type="text" size="25"/>

					  </td>
				    </tr>
				     <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">电子邮箱</td>
				      <td>
							  <s:textfield name="em.email" type="text" size="25"/>


						<td align="center">电话号码</td>
				      <td>
						  <s:textfield name="em.tel" type="text" size="25"/>

					  </td>
				     </tr>
				      <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">性&nbsp;&nbsp;&nbsp;&nbsp;别</td>
				      <td>
						 <s:select list="@auth.emp.vo.EmpModel@genderMap" name="em.gender" cssStyle="width:190px"/>
					  </td>
				      <td align="center">地&nbsp;&nbsp;&nbsp;&nbsp;址</td>
				      <td>
						  <s:textfield name="em.address" type="text" size="25"/>
					  </td>
				    </tr>
				     <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">出生日期</td>
				      <td>
				      	<input type="text" size="25" onfocus="c.showMoreDay=false;c.show(this);" readonly="true" value="${em.birthdayView}"/>
						  <input type="hidden" name="em.birthday" value="${em.birthday}">
					  </td>
				      <td align="center">所属部门</td>
						<td>
						<s:select name="em.dm.uuid" list="deptList" listKey="uuid" listValue="name" cssStyle="width:190px">

						</s:select>
					  </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">角色名称</td>
				      <td width="82%" colspan="3">
				      	<input type="checkbox" id="all">全选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				      	<input type="checkbox" id="reverse">反选
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">&nbsp;</td>
				      <td width="82%" colspan="3">
						  <s:checkboxlist list="roleList" name="roles" listKey="uuid" listValue="name"/>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				</table>
			</div>
			<div class="order-botton">
				<div style="margin:1px auto auto 1px;">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td>
					    	<a href="javascript:document.forms[0].submit()"><img src="images/order_tuo.gif" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="images/order_tuo.gif" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="javascript:history.back()"><img src="images/order_tuo.gif" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>
			</s:form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="images/content_bbg.jpg" /></div>
</div>
