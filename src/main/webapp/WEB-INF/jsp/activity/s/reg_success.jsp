<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
.STYLE2 {	color: #FFFFFF;
	font-size: 16px;
	font-weight: bold;
}
.STYLE7 {
	color: #FFFFFF;
	padding-bottom: 19pt;
}
.STYLE9 {color: #FFFFFF; font-size: 26px; font-weight: bold; }
.STYLE10 {
	color: #B00309;
	font-weight: bold;
	font-size: 16px;
}
.STYLE11 {
	font-size: 12px;
	color: #FFFF00;
}
a:link,a:visited{
 text-decoration:none;  /*超链接无下划线*/
}
-->
</style></head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td bgcolor="ea093f"><img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/s-zdms_01.png" width="100%" /></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="200" colspan="2" align="center" valign="middle" bgcolor="#b3030b">
        <div style="width: 245px;"> 
        <span style="float:left; margin-left: 33px;">
        <c:if test="${code == 0 }">
        <img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/s-dui.png" width="36"/>
        </c:if>
        <c:if test="${code != 0 }">
        <img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/s-cuowu.png" width="36"/>
        </c:if>
        </span>
        <span class="STYLE9">
        	${msg }
        </span>
        </div>
        <br />
          <br />
          <span class="STYLE2">还差一步即可参与[1元秒杀活动]<br />
          </span><span class="STYLE11">更多微整形精彩活动，尽在[美分期APP]</span><br />
          <br />
          <span class="STYLE7"><img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/s-down.png" width="23"/></span></td>
      </tr>
      <tr>
        <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFF00">
        <a href="${pageContext.request.contextPath}/activity/download">
        <span class="STYLE10">猛戳此处 &gt; 下载‘美分期’</span>
        </a>
        </td>
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

</body>
</html>
</body>
</html>
