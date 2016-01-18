<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="${pageContext.request.contextPath}/js/activity/jquery-1.8.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/activity/items.js"></script>
<title>活动详情</title>

<style type="text/css">
<!--
body {
	background-color: #b00309;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {
	font-size: 24px;
	font-family: "微软雅黑";
}
-->
</style></head>

<body>
<div style="position: relative; text-align:center;">
<div style="position: fixed;  width:100%; top:1%;">
<span class="STYLE1">倒计时 - <span id="countdown">00:00:00</span></span>
</div>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/s-ms_hdxq_07.png" width="100%" border="0" usemap="#Map" /></td>
    
  </tr>
  <tr>
  <td><span style="color: rgb(255, 255, 255); font-size: 13px; margin: 0px 10px; display: block; margin-bottom: 10px;">
  	也可分享此链接<a href="t.cn/RUpnBOP" style="color: rgb(255, 255, 255);">t.cn/RUpnBOP</a><br/>至朋友圈参与本活动</span></td>
  </tr>
</table>

<div style="text-align:center; width:100%; height:45pt; position:absolute; bottom:13%;"  >
	<a href="activity:share"><div style="width:100%; height: 100%;"></div></a>
</div>
</div>
<script>
fnTimeCountDown('${time}', $("#countdown"));
</script>
</body>
</html>
