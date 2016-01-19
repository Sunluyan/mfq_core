<%@page import="java.math.BigDecimal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String title = (String) request.getAttribute("title");
String name = (String) request.getAttribute("name");
String viewNum = (String) request.getAttribute("viewNum");
String endTime = (String) request.getAttribute("endTime");
String price = (String) request.getAttribute("price");
String market_price = (String) request.getAttribute("market_price");
String purl = (String) request.getAttribute("purl");

String p_price = (String) request.getAttribute("p_price");
String p_num = (String) request.getAttribute("p_num");

String hospital_name = (String) request.getAttribute("hospital_name");
String hid = (String) request.getAttribute("hid");
String hospital_addr = (String) request.getAttribute("hospital_addr");
String hospital_img = (String) request.getAttribute("hospital_img");

String cure_means = (String) request.getAttribute("cureMeans");

%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>

<title>商品详情</title>

<link href="css/tipbox.css" rel="stylesheet" type="text/css" />
<meta name="keywords" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/app/n/style.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/app/n/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/app/n/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/app/n/jquery_a.js"/>
<script type="text/javascript">
function banner(){	
	var bn_id = 0;
	var bn_id2= 1;
	var speed33=3000;
	var qhjg = 1;
    var MyMar33;
	$("#banner .d1").hide();
	$("#banner .d1").eq(0).fadeIn("slow");
	if($("#banner .d1").length>1)
	{
		$("#banner_id li").eq(0).addClass("nuw");
		function Marquee33(){
			bn_id2 = bn_id+1;
			if(bn_id2>$("#banner .d1").length-1)
			{
				bn_id2 = 0;
			}
			$("#banner .d1").eq(bn_id).css("z-index","2");
			$("#banner .d1").eq(bn_id2).css("z-index","1");
			$("#banner .d1").eq(bn_id2).show();
			$("#banner .d1").eq(bn_id).fadeOut("slow");
			$("#banner_id li").removeClass("nuw");
			$("#banner_id li").eq(bn_id2).addClass("nuw");
			bn_id=bn_id2;
		};
	
		MyMar33=setInterval(Marquee33,speed33);
		
		$("#banner_id li").click(function(){
			var bn_id3 = $("#banner_id li").index(this);
			if(bn_id3!=bn_id&&qhjg==1)
			{
				qhjg = 0;
				$("#banner .d1").eq(bn_id).css("z-index","2");
				$("#banner .d1").eq(bn_id3).css("z-index","1");
				$("#banner .d1").eq(bn_id3).show();
				$("#banner .d1").eq(bn_id).fadeOut("slow",function(){qhjg = 1;});
				$("#banner_id li").removeClass("nuw");
				$("#banner_id li").eq(bn_id3).addClass("nuw");
				bn_id=bn_id3;
			}
		})
		$("#banner_id").hover(
			function(){
				clearInterval(MyMar33);
			}
			,
			function(){
				MyMar33=setInterval(Marquee33,speed33);
			}
		)	
	}
	else
	{
		$("#banner_id").hide();
	}
}
</script>
<style type="text/css">
*{margin:0;padding:0;}
body,td,th {
	font-family: 方正细圆简体;
	font-size: 14px;
	color: #666666;
	font-weight: normal;
	line-height: 1.5;
}
body {
	margin:0;
	background-color: #f4f4f4;
	color: #666666;
	line-height: 1.4;
}
.wait_line {
	border-width: 1px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: dotted;
	border-left-style: none;
	border-color:#919191;
}
.line_down {
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: solid;
	border-left-style: none;
	border-top-color: #E4E4E4;
	border-right-color: #E4E4E4;
	border-bottom-color: #E4E4E4;
	border-left-color: #E4E4E4;
}
.r_dline {
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: dashed;
	border-left-style: none;
	border-top-color: #E6002D;
	border-right-color: #E6002D;
	border-bottom-color: #E6002D;
	border-left-color: #E6002D;
	font-size: 15px;
}
.tuan_Text {
	color: #FF0;
	font-size: 30px;
}
.left_xx {
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: none;
	border-left-style: dashed;
	border-top-color: #CCCCCC;
	border-right-color: #CCCCCC;
	border-bottom-color: #CCCCCC;
	border-left-color: #CCCCCC;
}
.text_hui_qian {
	color: #aeaeae;
	font-size: 11px;
}
.text_tuan {
	color: #ff5254;
}
.txt_tuangou {
	font-size: 11px;
	color: #696969;
}
.cp_name {
	color: #000;
	font-weight: bold;
}
.line_down table tr td table {
	color: #FF5254;
}
.bt_td {
	font-size: 12px;
	font-weight: normal;
	color: #FFFFFF;
	text-decoration: none;
	border-radius:5px;
}
.bt_td1 {	font-size: 12px;
	font-weight: normal;
	color: #FFFFFF;
	text-decoration: none;
	border-radius:2px;
}
.bt_td2 {	font-size: 12px;
	font-weight: normal;
	color: #FFFFFF;
	text-decoration: none;
	border-radius:5px;
}
.bt_td11 {font-size: 12px;
	font-weight: normal;
	color: #FFFFFF;
	text-decoration: none;
	border-radius:2px;
}

.banner{width:100%;overflow:auto; position:relative; height:115px;}
.d1{
	width:100%;
	display:block;
	position:absolute;
	left:0px;
}
@media (max-width:640px){
	.nav a{font-size:16px;margin-left:2px;margin-right:2px;}	
}
@media (max-width:320px){
	.nav a,.logo{line-height:40px;}
	.nav{display:none;}
	.nav_menu{display:block;}
	.banner h1{margin-top:10px;font-size:16px;}
	.banner{padding-bottom:10px;}
	.banner p{font-size:12px;line-height:22px;}		
}
.td_r { position:relative; }
.img_r {width:31px; height:31px; position:absolute; top:0; right:0;}
.bt_td3 {	font-size: 12px;
	font-weight: normal;
	color: #FFFFFF;
	text-decoration: none;
	border-radius:5px;
}
.STYLE71 {color: #FF5254}
.STYLE72 {color: #FFFFFF}
.STYLE75 {
	font-size: 18px;
	color: #FF5254;
}
.STYLE76 {font-size: 12px}
.STYLE9 {color: #FF5254; font-family: "方正细黑一简体"; }
.left_line {	border-top-width: 1px;
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
.name {
	font-size: 17px;
	color: #000000;
	font-weight: bold;
	text-align: left;
	vertical-align: baseline;
}
.pri_l {font-size: 24px;color: #FF5254}
.pri_s {	color: #FF5254;
	font-size: 14px;
}
.round_l {	font-size: 9px;
	border: 1px solid #FF5254;
	color:#FF5254;
	border-radius: 2px;
}
.round_l1 {font-size: 9px;
	border: 1px solid #FF5254;
	color:#FF5254;
	border-radius: 2px;
}
.up_do_line_red {
	font-family: "方正细圆简体";
	font-size: 14px;
	font-weight: bold;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: solid;
	margin-right: 22px;
	border-right-style: none;
	border-bottom-style: solid;
	border-left-style: none;
	border-top-color: #CCCCCC;
	border-right-color: #CCCCCC;
	border-bottom-color: #CCCCCC;
	border-left-color: #CCCCCC;
}
.STYLE80 {border-top-width: 1px; border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px; border-top-style: none; border-right-style: none; border-bottom-style: solid; border-left-style: none; border-top-color: #E4E4E4; border-right-color: #E4E4E4; border-bottom-color: #E4E4E4; border-left-color: #E4E4E4; color: #FF5254; }
.STYLE11 {	font-size: 10px;
	color: #FFFFFF;
}
.round_l2 {	font-size: 9px;
	border: 1px solid #FF5254;
	color:#FF5254;
	border-radius: 2px;
}

.round_l21 {font-size: 9px;
	border: 1px solid #FF5254;
	color:#FF5254;
	border-radius: 2px;
}
.a1 {
	height: auto;
	width: 99%;
	text-align:left;
	vertical-align: middle;
	overflow:auto;
	margin:1px 0 1px 5px;
}
.table {
	width: 99%;
    height:auto;
	left:0px;
	right:0px;
}
.r1{
	float: left;
	width: 20px;
	height:20px;
	padding:1px;
}
.r2{
	float: left;
	width: 70px;
	height:20px;
}
.r3{
	display: block;
	float: left;
	width: 70%;
}

</style>
</head>

<body>
<div style="background-color:#000000; width:100%; text-align:left;vertical-align: middle;">
<div class="banner" id="banner" >
			<c:forEach items="${imgs }" var="img">
			<img class="d1" src="${img.img}" background:center no-repeat;/>
			</c:forEach>
			</div>
		<script type="text/javascript">banner()</script>
<a href="/hospital/<%=hid%>/" style="text-decoration: none; width: 100%; display: block; color: #666666;"> <strong style="margin:8px; "><img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/app-home-mfq_qqqindex_37.png" width="9"> <%=hospital_name %></strong> </a>
</div>

<div style="background-color:#ffffff; width:100%; text-align:left;vertical-align: middle;">
<ul class="name" style="margin:8px 0 0 8px;"><%=name %></ul>
<ul><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
			 <td valign="bottom"><span class="pri_s"> &nbsp;&nbsp;<img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/app-home-mfq_qqqindex_41.png" width="18" />￥</span><span class="STYLE3"><span class="pri_l"><%=price %></span></span> <span class="STYLE76"><del>原价￥<%=market_price %></del></span></td>
			<td align="right" style="padding:10px;"><table border="0" cellpadding="1" cellspacing="0" class="round_l21">
          <tr>
            <td width="30" align="center" valign="middle" bgcolor="#FF5254"><span class="STYLE9"><span class="STYLE11">补贴</span></span></td>
            <td align="left" valign="bottom" bgcolor="#FFFFFF"><span class="pri_s" >￥${bt }</span></td>
          </tr>
      </table></td>
          </tr>
      </table>
  </ul>
</div>
<script>function jump(url){
			document.location.href=url;
		}</script>
<div style="width:100%; height:4px;"></div>
<c:if test="${fq_3!= ''}">
	<div style="background-color:#ffffff; width:100%; text-align:center; vertical-align: middle;padding:4px auto;border-top: 1px solid #eee; border-bottom:1px solid #eee;" >分期详情</div>
	<div style="background-color:#ffffff; width:65%; text-align:left; vertical-align: middle; padding:4px auto; position:absolute; overflow:hidden;float: left; height:auto; min-height: 58px;border-bottom: 1px solid #eee;">
		<ul style="text-align:left; vertical-align: middle;"><span class="STYLE75"><c:if test="${fq_3 != '' }"><span class="STYLE76">&nbsp;&nbsp;￥</span>${fq_3}<span class="STYLE76"> x 3期</span></c:if></span></ul>
		<ul style="text-align:left; vertical-align: middle;">
			<c:if test="${fq_6 != '' }">&nbsp;￥${fq_6 }x6期 </c:if>
			<c:if test="${fq_12 != '' }">/ ￥${fq_12 }x12期</c:if>
		</ul>
	</div>
	<div style="background-color:#FF5254;text-align:center; vertical-align:middle; position:relative;right:0px; bottom:0px; left:64%;height:58px; width:35%;float:left">
		<ul style="height:58px;line-height:58px;text-align:center; vertical-align:middle; ">

			<c:if test="${!s }">
				<span class="STYLE72" style="line-height:58px;" onclick="jump('product:fq')">申请分期</span>
			</c:if>

			<c:if test="${s }">
				<span class="STYLE72" style="line-height:58px;" onclick="jump('http://www.5imfq.com/download/app.html')">申请分期</span>
			</c:if>
		</ul>
	</div>

</c:if>

<div style="width:100%; height:4px; clear:both; "></div>

<div class="table" style="background-color:#FFFFFF; padding:10px 0 10px 0">
	<div class="a1">
	    <div class="r1"><img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/app-home-icon.png" width="13" /></div>
		<div class="r2"><span class="STYLE71 STYLE48"><strong>治疗手段:</strong></span></div>
		<div class="r3">${p.cureMeans }</div>
	</div>
	<div class="a1">
	    <div class="r1"><img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/app-home-icon.png" width="13" /></div>
		<div class="r2"><span class="STYLE71 STYLE48"><strong>治疗时长:</strong></span></div>
		<div class="r3">${p.cureDur }</div>
	</div>
	<div class="a1">
	    <div class="r1"><img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/app-home-icon.png" width="13" /></div>
		<div class="r2"><span class="STYLE71 STYLE48"><strong>住院治疗:</strong></span></div>
		<div class="r3">${p.cureHospital }</div>
	</div>
	<div class="a1">
	    <div class="r1"><img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/app-home-icon.png" width="13" /></div>
		<div class="r2"><span class="STYLE71 STYLE48"><strong>恢复时间:</strong></span></div>
		<div class="r3">${p.recoverDur }</div>
	</div>
</div>

<table width="100%" border="0" cellpadding="0" cellspacing="0" id="main">
  <tr>
    <td align="center" valign="middle" bgcolor="#f4f4f4">
		<!--  <div class="banner" id="banner" >
			<img class="d1" src="http://7xlb4j.com2.z0.glb.qiniucdn.com/app-home-banner01.png" background:center no-repeat;/>
			<img class="d1" src="http://7xlb4j.com2.z0.glb.qiniucdn.com/app-home-banner2.jpg" background:center no-repeat;/>
			<img class="d1" src="http://7xlb4j.com2.z0.glb.qiniucdn.com/app-home-banner3.jpg" background:center no-repeat;/>		</div>
		<script type="text/javascript">banner()</script>--></td>
  </tr>

  <tr>
    <td height="3"><img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/app-home-1px.png" width="1"/></td>
  </tr>

  <tr>
    <td height="90" align="center" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="6">
      <tr>
        <td><img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/app-home-splc.png" width="100%" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="100" align="center" valign="top" bgcolor="#FFFFFF" class="line_down"> <span class="STYLE40">　 </span><br />
    <table width="98%" border="0" cellspacing="2" cellpadding="1">
      <tr align="left" valign="bottom" class="up_do_line_red">
        <td  class="STYLE80">优点</td>
      </tr>
      <tr>
        <td>${p.merit }</td>
      </tr>
      <tr class="line_down">
        <td>&nbsp;</td>
      </tr>

      <tr align="left" valign="bottom" class="up_do_line_red">
        <td  class="STYLE80">治疗方法</td>
      </tr>
      <tr>
        <td>${p.cureMethod }</td>
      </tr>

      <tr valign="bottom" class="line_down">
        <td  class="STYLE44">&nbsp;</td>
      </tr>
      <tr align="left" valign="bottom">
        <td  class="STYLE80">注意事项</td>
      </tr>
      <tr class="line_down">
        <td>${p.warning }</td>
      </tr>

      <tr valign="bottom" class="line_down">
        <td class="STYLE44">&nbsp;</td>
      </tr>
      <tr align="left" valign="bottom">
        <td class="STYLE80">适合人群</td>
      </tr>
      <tr class="line_down">
        <td>${p.tabooCrowd }</td>
      </tr>

      <tr valign="bottom" class="line_down">
        <td  class="STYLE44">&nbsp;</td>
      </tr>
      <tr align="left" valign="bottom">
        <td  class="STYLE80">禁忌人群</td>
      </tr>
      <tr class="line_down">
        <td>${p.crowd }</td>
      </tr>

      <tr class="line_down">
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="1">
          <tr>
            <td width="64"><span class="STYLE71 STYLE65">治疗次数</span></td>
            <td>${p.cureNum }</td>
          </tr>
          <tr>
            <td width="64"><span class="STYLE71 STYLE65">麻醉方法</span></td>
            <td>${p.anesMethod }</td>
          </tr>
          <tr>
            <td width="64"><span class="STYLE71 STYLE65">医师级别</span></td>
            <td>${p.doctorLevel }</td>
          </tr>
          <tr>
            <td width="64"><span class="STYLE71 STYLE65">治疗周期</span></td>
            <td>${p.cureCycle }</td>
          </tr>
        </table></td>
      </tr>
    </table>
    <br /></td>
  </tr>
</table>
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
<c:if test="${s}">
    <td align="center" valign="top" bgcolor="#FFFFFF"><a href="http://www.5imfq.com/download/app.html">
<img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/app-home-1px.png" width="1" height="4" /><br />
<img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/app-home-gg_download.png" width="100%" border="0" /></a>
		<br /><br /></td>
</c:if>

  </tr>
</table>
</body>
</html>


