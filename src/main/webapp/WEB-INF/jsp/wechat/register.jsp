<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<title>会员注册-美分期</title>
<meta
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"
	name="viewport" id="viewport" />

<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/wx/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/wx/register.js"></script>

<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #b00309;
}
.STYLE1 {
	color: #FFFFFF;
	font-weight: bold;
}
.error{
	font-size: 12px;
}
-->
</style>

</head>

<body>

		<form name="reg" id="reg" action="${pageContext.request.contextPath}/wechat/register"
			method="post">
			<input type="hidden" value="${UID}" name="uid" id="uid" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td bgcolor="ea093f"><img src="${pageContext.request.contextPath}/images/activity/s/zdms_01.png" width="100%" /></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="30" colspan="2" align="center" valign="middle" bgcolor="#F77487"><span class="STYLE1">注　册</span></td>
      </tr>
      <tr>
        <td colspan="2" align="center" valign="middle" bgcolor="f03548"><table width="98%" border="0" cellspacing="3" cellpadding="2">
          <tr>
            <td align="right" valign="middle"><span class="STYLE1">手机号</span></td>
            <td align="left" valign="middle">
            <input type="text" maxlength="11" name="phone" class="us_phone" style="width:auto; height:20px" />
            <label for="phone" class="error" id="phone-error"></label>
            </td>
          </tr>
          <tr>
            <td align="right" valign="middle"><span class="STYLE1">验证码</span></td>
            <td align="left" valign="middle">
            <input type="text" maxlength="4" class="v_code" name="vcode" style="width:auto; height:20px" />            
            <label>
              <input type="button" name="Submit" class="v_line" style="width:53pt" id="btnSendCode" value="点击获取" />
            </label>
            <br/>
            <label class="error" id="vcode-error"></label>
            </td>
          </tr>
          <tr>
            <td align="right" valign="middle"><span class="STYLE1">设置密码</span></td>
            <td align="left" valign="middle">
            <input name="passwd" type="password" class="passwd" style="width:auto; height:20px" />
            <label class="error" id="pwd-error"></label>
            </td>
          </tr>
          <tr>
            <td colspan="2" align="center" valign="middle"><input type="button" name="Submit2" class="quren" value="确认注册" style="width:80%; height:25px"/></td>
            </tr>

        </table></td>
      </tr>
      <tr>
        <td width="35%" height="10" align="center">&nbsp;</td>
        <td height="10" align="center" valign="middle">&nbsp;</td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td valign="bottom"><img src="${pageContext.request.contextPath}/images/activity/s/ms_zhu_10.png" width="100%" /></td>
  </tr>
</table>
		</form>
	</div>

	
	<!--js come-->
	<script type="text/javascript">
		window.thisapp='${pageContext.request.contextPath}';
		userRegister();
	</script>


	<!--js end-->
</body>
</html>
