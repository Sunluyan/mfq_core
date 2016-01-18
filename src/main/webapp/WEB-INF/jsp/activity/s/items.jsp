<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<title>双十一</title>
<script src="${pageContext.request.contextPath}/js/activity/jquery-1.8.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/activity/items.js"></script>
<title>秒杀项目</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #b00309;
}
body,td,th {
	font-size: 14px;
	color: #FFFFFF;
}
.STYLE5 {font-size: 20px}
.STYLE9 {font-size: 24px}
.STYLE10 {font-size: 12px}
.STYLE11 {
	font-size: 16px;
	font-weight: bold;
}
.t{cursor: pointer;}
a:link,a:visited{
 text-decoration:none;  /*超链接无下划线*/
}

-->
</style>
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/s-ms_zhu_01.png" width="100%" /></td>
  </tr>
  <tr>
    <td align="center" valign="top" bgcolor="#FFE303"><table width="80%" height="50" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="40%" align="right" valign="middle" bgcolor="#0373FD"><img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/s-ms_zhu_03.png" width="100" /></td>
          <td align="center" bgcolor="#0373FD"><span class="STYLE5" id="countdown">00:00:00</span></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td><img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/s-ms_zhu_06.png" width="100%" /></td>
  </tr>
	<c:forEach items="${products }" var="product">
  <tr>
    <td align="center" valign="middle">
    <a href="${pageContext.request.contextPath}/activity/seckilling?id=${product.id}">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
        <img src="${product.img}" width="100%" border="0" class="t" />
        </td>
      </tr>
      <tr>
        <td><table width="100%" height="50" border="0" cellpadding="5" cellspacing="0">
          <tr>
            <td bgcolor="#333333" class="t"><span class="STYLE11">${product.name }</span> <br/> ${product.hospitalName }</td>
            <td width="28%" class="t" bgcolor="#FF9900" style="text-align:center;"><span class="STYLE9"><span class="STYLE10">仅剩</span> ${product.remainNum }<span class="STYLE10">/${product.totalNum } </span></span></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="15">&nbsp;</td>
      </tr>
    </table>
    </a>
    </td>
  </tr>
  </c:forEach>
  <tr>
    <td height="20" align="center" valign="middle">&nbsp;</td>
  </tr>
  <tr>
    <td><span style="width:100%; height:146px; z-index:0;position:relative;"><img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/s-ms_zhu_10.png" width="100%" /></span></td>
  </tr>
</table>
</body>
<script>
fnTimeCountDown('${time}', $("#countdown"));
</script>
</html>

