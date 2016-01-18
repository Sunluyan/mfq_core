<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
<title>注册</title>
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
-->
</style>
</head>

<body>
<form action="${pageContext.request.contextPath}/activity/register" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td bgcolor="ea093f"><img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/s-zdms_01.png" width="100%" /></td>
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
            <td align="left" valign="middle"><input name="mobile" type="text" style="width:auto; height:20px" /></td>
          </tr>
          <tr>
            <td align="right" valign="middle"><span class="STYLE1">验证码</span></td>
            <td align="left" valign="middle"><input name="code" type="text" style="width:auto; height:20px" /> 
            <label>
              <input type="button" name="Submit" value="点击获取" />
            </label></td>
          </tr>
          <tr>
            <td align="right" valign="middle"><span class="STYLE1">设置密码</span></td>
            <td align="left" valign="middle"><input name="passwd" type="password" style="width:auto; height:20px" /></td>
          </tr>
          <tr>
            <td colspan="2" align="center" valign="middle"><input type="submit" name="Submit2" value="确认注册" style="width:80%; height:25px"/></td>
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
    <td valign="bottom"><img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/s-ms_zhu_10.png" width="100%" /></td>
  </tr>
</table>
</form>
</body>
</html>