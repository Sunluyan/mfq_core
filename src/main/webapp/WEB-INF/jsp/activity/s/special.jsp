<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
<title>限时特惠11.9-11.11</title>
<style type="text/css">
<!--
body,td,th {
	font-family: 微软雅黑;
	font-size: 14px;
}
body {
	background-color: #de0f1b;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #DB131F;
	font-size: 24px;
}
.STYLE2 {
	font-size: 16px;
	color: #FFFFFF;
	font-weight: bold;
}
.STYLE4 {
	font-size: 12px;
	color: #FFFFFF;
}
.STYLE5 {
	color: #FFFF00;
	font-size: 17px;
	font-weight: bold;
}
.STYLE6 {font-size: 12px}
.STYLE7 {color: #FFFFFF; font-size: 16px;}
.STYLE8 {font-size: 10px}
a:link,a:visited{
 text-decoration:none;  /*超链接无下划线*/
}
-->
</style></head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/s-xsg_banner.png" width="100%" /></td>
  </tr>
  <tr>
    <td height="50" align="center" valign="middle" bgcolor="#FFF41A"><span class="STYLE1"><span id="countdown">00 : 00 : 00</span></span></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <c:forEach items="${products }" var="product">
  <tr>
    <td align="center" valign="middle">
    <c:if test="${isstart == 1}"><a href="${pageContext.request.contextPath}/activity/product/special?pid=${product.id}"></c:if>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle"><img src="${product.img }" width="100%" border="0" /></td>
      </tr>
      <tr>
        <td><table width="100%" height="30" border="0" cellpadding="3" cellspacing="0">
          <tr>
            <td bgcolor="#333333"><span class="STYLE2">${product.name }<br />
            </span><span class="STYLE7"><span class="STYLE6">${product.hospitalName }</span></span></td>
            <td width="28%" align="center" bgcolor="e63f52" class="STYLE4"><span class="STYLE8"><s>原价：${product.marketPrice }</s> <br/> </span><span class="STYLE5"><span class="STYLE6">特价</span> ${product.price } </span></td>
          </tr>
        </table>
        </td>
      </tr>
      <tr>
        <td><img name="" src="" width="3" height="3" alt="" style="background-color: #DE0F1B" /></td>
      </tr>
    </table>
	<c:if test="${isstart == 1}"></a></c:if>
    </td>
  </tr>
  </c:forEach>

</table>
</body>
<script src="${pageContext.request.contextPath}/js/activity/jquery-1.8.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/activity/item.js"></script>
<script>
fnTimeCountDown('${time}', $("#countdown"));
</script>
</html>
