<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
<title>1元整形，就是这么任性！--美分期</title>
<script src="${pageContext.request.contextPath}/js/activity/jquery-1.8.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/activity/items.js"></script>
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
.STYLE3 {color: #FFFFFF; font-weight: bold; font-size: 24px; }
a:link,a:visited{
 text-decoration:none;  /*超链接无下划线*/
}
-->
</style></head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td bgcolor="ea093f">
    <a href="${pageContext.request.contextPath}/activity/register">
    <img src="${pageContext.request.contextPath}/images/activity/s/zdms_01.png" width="100%" />
    </a>
    </td>
  </tr>
  <tr>
  	<td bgcolor="ea093f" style="font-weight:bold; cursor:pointer;text-align: center; color:#FFF; font-size:16px;" onclick="window.location.href='${pageContext.request.contextPath}/activity/f_share';">
		<img src="${pageContext.request.contextPath}/images/activity/s/mss.png"  width="100%"/>
  	</td>
  </tr>
  <tr>
    <td valign="top">
    <a href="${pageContext.request.contextPath}/activity/register">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">

      <tr>
        <td colspan="2" bgcolor="f03548"><img src="${pageContext.request.contextPath}/images/activity/s/ml_hlyj.jpg" width="100%" border="0" /></td>
      </tr>
      <tr>
        <td width="35%" height="30" align="center" bgcolor="f77487"><span class="STYLE1">秒杀倒计时</span></td>
        <td height="30" align="center" valign="middle" bgcolor="f03548"><span class="STYLE3 countdown">00:00:00</span></td>
      </tr>
      <tr>
        <td height="10" align="center">&nbsp;</td>
        <td height="10" align="center" valign="middle">&nbsp;</td>
      </tr>
    </table>
    </a>
    <a href="${pageContext.request.contextPath}/activity/register">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="10" colspan="2" bgcolor="f03548">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="2" bgcolor="f03548"><img src="${pageContext.request.contextPath}/images/activity/s/hm_csjb.jpg" width="100%" /></td>
        </tr>
        <tr>
          <td width="35%" height="30" align="center" bgcolor="f77487"><span class="STYLE1">秒杀倒计时</span></td>
          <td height="30" align="center" valign="middle" bgcolor="f03548"><span class="STYLE3 countdown">00:00:00</span></td>
        </tr>
        <tr>
          <td height="10" align="center">&nbsp;</td>
          <td height="10" align="center" valign="middle">&nbsp;</td>
        </tr>
      </table>
      </a>
      <a href="${pageContext.request.contextPath}/activity/register">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="10" colspan="2" bgcolor="f03548">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="2" bgcolor="f03548"><img src="${pageContext.request.contextPath}/images/activity/s/ml_qm.jpg" width="100%" /></td>
        </tr>
        <tr>
          <td width="35%" height="30" align="center" bgcolor="f77487"><span class="STYLE1">秒杀倒计时</span></td>
          <td height="30" align="center" valign="middle" bgcolor="f03548"><span class="STYLE3 countdown">00:00:00</span></td>
        </tr>
        <tr>
          <td height="10" align="center">&nbsp;</td>
          <td height="10" align="center" valign="middle">&nbsp;</td>
        </tr>
      </table>
      </a>
      <a href="${pageContext.request.contextPath}/activity/register">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="10" colspan="2" bgcolor="f03548">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="2" bgcolor="f03548"><img src="${pageContext.request.contextPath}/images/activity/s/jh_wm.jpg" width="100%" /></td>
        </tr>
        <tr>
          <td width="35%" height="30" align="center" bgcolor="f77487"><span class="STYLE1">秒杀倒计时</span></td>
          <td height="30" align="center" valign="middle" bgcolor="f03548"><span class="STYLE3 countdown">00:00:00</span></td>
        </tr>
        <tr>
          <td height="10" align="center">&nbsp;</td>
          <td height="10" align="center" valign="middle">&nbsp;</td>
        </tr>
      </table>
      </a>
      </td>
  </tr>
  <tr>
    <td valign="bottom"><img src="${pageContext.request.contextPath}/images/activity/s/ms_zhu_10.png" width="100%" /></td>
  </tr>
</table>
<script>
fnTimeCountDown('${time}', $(".countdown"));
</script>
</body>
</html>
