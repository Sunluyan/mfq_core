<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>美分期APP彩色</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
<style type="text/css">
<!--
body,td,th {
	font-family: 方正细圆简体;
	font-size: 13px;
	color: #999999;
}
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #E5E5E5;
}
.title {
	font-size: 16px;
	color: #000000;
}
.name {
	font-size: 17px;
	color: #000000;
	font-weight: bold;
}
.round_l {
	font-size: 9px;
	border: 1px solid #FF5254;
	color:#FF5254;
	border-radius: 2px;
}
.pri_s {
	color: #FF5254;
	font-size: 12px;
}
.pri_l {font-size: 18px;color: #FF5254}
.STYLE6 {color: #FF5254; font-size: 14px; }
.left_line {
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: none;
	border-left-style: dashed;
	border-top-color: #aeaeae;
	border-right-color: #aeaeae;
	border-bottom-color: #aeaeae;
	border-left-color: #aeaeae;
}
.STYLE9 {color: #FF5254; font-family: "方正细黑一简体"; }
.down_line {
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: solid;
	border-left-style: none;
	border-top-color: #cacaca;
	border-right-color: #cacaca;
	border-bottom-color: #cacaca;
	border-left-color: #cacaca;
}
.STYLE11 {
	font-size: 10px;
	color: #FFFFFF;
}
-->
</style>
</head>
<body>
<!-- Save for Web Slices (美分期APP彩色 副本.psd) -->
<table width="100%" border="0" align="center" cellpadding="6" cellspacing="0" bgcolor="#FFFFFF" class="down_line">
  <tr>
    <td align="center" valign="middle">

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center">
        <a href="classify:${classify_0.id }-${classify_0.name }">
		<table width="60" border="0" cellpadding="2" cellspacing="1" onClick="">
          <tr>
            <td align="center" valign="middle"><img src="${classify_0.hgImage }" width="43" alt=""> </td>
          </tr>
          <tr>
            <td align="center" valign="middle">${classify_0.name }</td>
          </tr>
        </table>
        </a>
        </td>
        <td align="center">
        <a href="classify:${classify_1.id }-${classify_1.name }">
        <table width="60" border="0" cellpadding="2" cellspacing="1">
          <tr>
            <td align="center" valign="middle"><img src="${classify_1.hgImage }" alt="" width="43"> </td>
          </tr>
          <tr>
            <td align="center" valign="middle">${classify_1.name }</td>
          </tr>
        </table>
        </a>
        </td>
		<td align="center">
		<a href="classify:${classify_2.id }-${classify_2.name }">
		<table width="60" border="0" cellpadding="2" cellspacing="1">
          <tr>
            <td align="center" valign="middle"><img src="${classify_2.hgImage }" alt="" width="43"> </td>
          </tr>
          <tr>
            <td align="center" valign="middle">${classify_2.name }</td>
          </tr>
        </table>
        </a>
        </td>
		<td align="center">
		<a href="classify:${classify_3.id }-${classify_3.name }">
		<table width="60" border="0" cellpadding="2" cellspacing="1">
          <tr>
            <td align="center" valign="middle"><img src="${classify_3.hgImage }" alt="" width="43"> </td>
          </tr>
          <tr>
            <td align="center" valign="middle">${classify_3.name }</td>
          </tr>
        </table>
        </a>
        </td>
		<td align="center">
		<a href="classify:${classify_4.id }-${classify_4.name }">
		<table width="60" border="0" cellpadding="2" cellspacing="1">
          <tr>
            <td align="center" valign="middle"><img src="${classify_4.hgImage }" alt="" width="43"> </td>
          </tr>
          <tr>
            <td align="center" valign="middle">${classify_4.name }</td>
          </tr>
        </table>
        </a>
        </td>
      </tr>
    </table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center">
        <a href="classify:${classify_5.id }-${classify_5.name }">
        <table width="60" border="0" cellpadding="2" cellspacing="1">
          <tr>
            <td align="center" valign="middle"><img src="${classify_5.hgImage }" alt="" width="43"> </td>
          </tr>
          <tr>
            <td align="center" valign="middle">${classify_5.name }</td>
          </tr>
        </table>
        </a>
        </td>
        <td align="center">
        <a href="classify:${classify_6.id }-${classify_6.name }">
        <table width="60" border="0" cellpadding="2" cellspacing="1">
            <tr>
              <td align="center" valign="middle"><img src="${classify_6.hgImage }" alt="" width="43"> </td>
            </tr>
            <tr>
              <td align="center" valign="middle">${classify_6.name }</td>
            </tr>
        </table>
        </a>
        </td>
        <td align="center">
        <a href="classify:${classify_7.id }-${classify_7.name }">
        <table width="60" border="0" cellpadding="2" cellspacing="1">
            <tr>
              <td align="center" valign="middle"><img src="${classify_7.hgImage }" alt="" width="43"> </td>
            </tr>
            <tr>
              <td align="center" valign="middle">${classify_7.name }</td>
            </tr>
        </table>
        </a>
        </td>
        <td align="center">
        <a href="classify:${classify_8.id }-${classify_8.name }">
        <table width="60" border="0" cellpadding="2" cellspacing="1">
            <tr>
              <td align="center" valign="middle"><img src="${classify_8.hgImage }" alt="" width="43"> </td>
            </tr>
            <tr>
              <td align="center" valign="middle">${classify_8.name }</td>
            </tr>
        </table>
        </a>
        </td>
        <td align="center">
        <a href="classify:${classify_9.id }-${classify_9.name }">
        <table width="60" border="0" cellpadding="2" cellspacing="1">
          <tr>
            <td align="center" valign="middle"><img src="${classify_9.hgImage }" alt="" width="43"> </td>
          </tr>
          <tr>
            <td align="center" valign="middle">${classify_9.name }</td>
          </tr>
        </table>
        </a>
        </td>
      </tr>
    </table>
    </td>
  </tr>
</table>
<img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/app-home-1px.png" width="1" height="3">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center" valign="middle" class="down_line"><a href="${pageContext.request.contextPath}/xmei/withyou/"><img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/app-home-mianfeipei.jpg" width="100%"></a></td>
  </tr>
</table>
<img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/app-home-1px.png" width="1" height="5">
<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
  <tr>
    <td align="center" valign="middle" class="down_line"><img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/images/app-home-1px.png" width="1" height="8">
<table width="97%" border="0" cellpadding="2" cellspacing="0" bgcolor="#FFFFFF">
      <tr>
        <td width="20" valign="middle"><img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/app-home-mfq_qqqindex_26.png" width="20"></td>
        <td align="left" valign="middle" class="title"><span class="STYLE1">精品推荐</span></td>
        <td width="53" align="right" valign="middle"><a href="#"></a></td>
      </tr>
    </table>
<img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/app-home-1px.png" width="1" height="5"> </td>
  </tr>
</table>

<c:forEach items="${products }" var="product">
<a href="product:${product.id }-${product.name}" style="text-decoration:none;">
<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
  <tr>
    <td align="center" valign="bottom" class="down_line">
    <img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/app-home-1px.png" width="1" height="8"> <br>
    <img src="${product.img }" width="96%"></td>
  </tr>
  <tr>
    <td height="60" align="center" valign="middle" class="down_line"><table width="96%" border="0" cellspacing="0" cellpadding="2">
      <tr>
        <td valign="bottom" class="name">${product.name }</td>
        <td align="right" valign="bottom"><img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/app-home-mfq_qqqindex_37.png" width="9">${product.city }</td>
        <td width="20" rowspan="2" align="right"><img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/app-home-kfq.png" width="23" height="52"></td>
      </tr>
      <tr>
        <td valign="middle"><table width="0" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top"><img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/app-home-mfq_qqqindex_41.png" width="18"></td>
            <td valign="bottom"><span class="pri_s">￥</span><span class="STYLE3 pri_l">

			<fmt:formatNumber  value="${product.price }" pattern="###,###"/>
			 </span><del>原价￥<fmt:formatNumber  value="${product.marketPrice }" pattern="###,###"/></del></td>
          </tr>
        </table></td>
        <td align="right" valign="middle"><table border="0" cellpadding="1" cellspacing="0" class="round_l">
          <tr>
            <td width="25" align="center" valign="middle" bgcolor="#FF5254"><span class="STYLE9"><span class="STYLE11">补贴</span></span></td>
            <td align="left" valign="bottom" bgcolor="#FFFFFF"><span class="STYLE6"><fmt:formatNumber  value="${product.marketPrice - product.price}" pattern="###,###" /></span></td>
          </tr>
        </table></td>
        </tr>
    </table></td>
  </tr>
</table>
</a>
</c:forEach>
<!-- End Save for Web Slices -->
</body>
</html>