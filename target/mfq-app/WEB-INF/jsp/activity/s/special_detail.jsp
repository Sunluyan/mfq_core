<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String title = (String) request.getAttribute("title");
String name = (String) request.getAttribute("name");
String viewNum = (String) request.getAttribute("viewNum");
String endTime = (String) request.getAttribute("endTime");
String price = (String) request.getAttribute("price");
String online_price = (String) request.getAttribute("online_price");
String market_price = (String) request.getAttribute("market_price");
String purl = (String) request.getAttribute("purl");

String consume_step = (String) request.getAttribute("consume_step");
String reserve = (String) request.getAttribute("reserve");
String special_note = (String) request.getAttribute("special_note");
String body = (String) request.getAttribute("body");
String p_price = (String) request.getAttribute("p_price");
String p_num = (String) request.getAttribute("p_num");

String hospital_name = (String) request.getAttribute("hospital_name");
String hospital_addr = (String) request.getAttribute("hospital_addr");
String hospital_img = (String) request.getAttribute("hospital_img");

%>
<!DOCTYPE">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=title %></title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {
	font-size: 18px;
	font-weight: bold;
}
body,td,th {
	font-size: 14px;
	font-family: 微软雅黑;
}
.STYLE2 {color: #333333}
.STYLE5 {font-size: 18px; font-weight: bold; color: #dd2628; }
.STYLE6 {
	color: #dd2628;
	font-weight: bold;
}
.STYLE7 {color: #dd2628}
.STYLE8 {
	color: #FFFFFF;
	font-weight: bold;
}
-->
</style></head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><img src="<%=purl %>" width="100%" /></td>
  </tr>
  <tr>
    <td align="center"><table width="100%" border="0" cellspacing="5" cellpadding="2">
      <tr>
        <td><span class="STYLE1"><%=name %></span></td>
        <td align="right"> <span class="STYLE7">❤ <%=viewNum %></span></td>
      </tr>
      <tr>
        <td><span class="STYLE2">特惠价：</span><span class="STYLE5">￥ <%=price %></span></td>
        <td align="right"><span class="STYLE2">预付款：</span><span class="STYLE6"><%=online_price %>元</span></td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="100%" height="30" border="0" cellpadding="5" cellspacing="5" bgcolor="#E63F52">
  <tr>
    <td bgcolor="e63f52"><span class="STYLE8">购买须知</span></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="5" cellpadding="2">
  <tr>
    <td><strong>有效期：</strong>优惠价格截止到<%=endTime %></td>
  </tr>
  <tr>
    <td><strong>消费流程：</strong><%=consume_step %></td>
  </tr>
  <tr>
    <td><strong>如何预约：</strong><%=reserve %></td>
  </tr>
  <tr>
    <td><strong>特殊说明：</strong><%=special_note %></td>
  </tr>
</table>
<table width="100%" height="30" border="0" cellpadding="5" cellspacing="5" bgcolor="#E63F52">
  <tr>
    <td bgcolor="e63f52"><span class="STYLE8">医院简介</span></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="5" cellpadding="2">
  <tr>
    <td><img src="<%=hospital_img %>" width="100%" /></td>
  </tr>
  <tr>
    <td><strong><%=hospital_name %></strong></td>
  </tr>
  <tr>
    <td>地址：<%=hospital_addr %></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td style="padding: 6pt;"><%=body %></td>
  </tr>
</table>
</body>
</html>
