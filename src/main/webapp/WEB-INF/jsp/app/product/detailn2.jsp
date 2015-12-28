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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/app/n/jquery_a.js"/></script>
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
body,td,th {
	font-family: 方正细圆简体;
	font-size: 14px;
	color: #666666;
	font-weight: normal;
	line-height: 1.5;
}
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #f4f4f4;
	color: #666666;
	line-height: 1.4;
}
.wait_line {
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: dotted;
	border-left-style: none;
	border-top-color: #919191;
	border-right-color: #919191;
	border-bottom-color: #919191;
	border-left-color: #919191;
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
.STYLE74 {font-size: 10px}
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
</style>
</head>

<body>


<table width="100%" border="0" cellpadding="0" cellspacing="0" id="main">
  <tr>
    <td align="center" valign="middle" bgcolor="#f4f4f4">
		<div class="banner" id="banner" >
			<c:forEach items="${imgs }" var="img">
			<img class="d1" src="${img.img}" background:center no-repeat;/>
			</c:forEach>
			</div>
		<script type="text/javascript">banner()</script>
  </tr>
  <tr>
    <td height="35" align="center" valign="middle" bgcolor="#000000">
	<table width="97%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><span class="STYLE72"><strong><img src="${pageContext.request.contextPath}/images/app/home/mfq_qqqindex_37.png" width="9" /> <%=hospital_name %></strong></span></td>
        <td align="right" onclick="jump('#')"><!-- <span class="STYLE72">医院简介 &gt;</span>  --></td>
		<script>function jump(url){
			document.location.href=url;
		}</script>
      </tr>
    </table>	</td>
  </tr>
  <tr>
    <td align="center" valign="middle" bgcolor="#FFFFFF" class="line_down td_r">
	<img src="${pageContext.request.contextPath}/images/app/home/1px.png" width="1" height="5" />
      <table width="97%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="2" valign="bottom" class="name"><%=name %></td>
        </tr>
      <tr>
        <td valign="middle"><table width="0" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td valign="middle"><img src="${pageContext.request.contextPath}/images/app/home/mfq_qqqindex_41.png" width="18" /></td>
              <td valign="bottom"><span class="pri_s">￥</span><span class="STYLE3"><span class="pri_l"><%=price %></span></span> <span class="STYLE76"><del>原价￥<%=market_price %></del></span></td>
            </tr>
        </table></td>
        <td align="right" valign="middle"><table border="0" cellpadding="1" cellspacing="0" class="round_l2">
          <tr>
            <td width="30" align="center" valign="middle" bgcolor="#FF5254"><span class="STYLE9"><span class="STYLE11">补贴</span></span></td>
            <td align="left" valign="bottom" bgcolor="#FFFFFF"><span class="pri_s">￥${bt }</span></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="4" align="center" valign="middle" class="line_down td_r"><img src="${pageContext.request.contextPath}/images/app/home/1px.png" width="1" height="1" /></td>
  </tr>
  <tr>
    <td height="60" align="center" valign="middle" bgcolor="#FFFFFF" class="line_down td_r"><table width="97%" height="80" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="20" colspan="2" align="center" valign="bottom" class="line_down">分期详情</td>
        </tr>
      <tr>
        
        <td align="left" valign="bottom"><span class="STYLE75"><span class="STYLE76">￥</span>${fq_6}<span class="STYLE76"> x 6期</span></span></td>
        
        <c:if test="${!s }">
        <td rowspan="2" align="center" valign="middle" bgcolor="#FF5254" class="STYLE72" style="cursor: pointer;" onclick="jump('product:fq')">申请分期</td>
        </c:if>
        
        <c:if test="${s }">
        <td rowspan="2" align="center" valign="middle" bgcolor="#FF5254" class="STYLE72" style="cursor: pointer;" onclick="jump('http://www.5imfq.com/download/app.html')">申请分期</td>
        </c:if>
        
      </tr>
      
      <tr align="left">
        <td valign="top">￥${fq_12 }x12期 </td>
        </tr>
    </table></td>
  </tr>

  <tr>
    <td height="3"><img src="${pageContext.request.contextPath}/images/app/home/1px.png" width="1"/></td>
  </tr>
  <tr>
    <td height="100" align="center" valign="middle" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="2" cellpadding="0" height="90">
      <tr>
        <td class="cp_name"><table width="100%" border="0" cellspacing="4" cellpadding="0">
          <tr>
            <td width="13" align="center" valign="middle"><img src="${pageContext.request.contextPath}/images/app/home/icon.png" width="13" /></td>
            <td width="65"><span class="STYLE71 STYLE48"><strong>治疗手段:</strong></span></td>
            <td>${p.cureMeans }</td>
          </tr>
          <tr>
            <td width="13" align="center" valign="middle"><img src="${pageContext.request.contextPath}/images/app/home/icon.png" width="13" /></td>
            <td width="65"><span class="STYLE71 STYLE48"><strong>治疗时长:</strong></span></td>
            <td>${p.cureDur }</td>
          </tr>
          <tr>
            <td width="13" align="center" valign="middle"><img src="${pageContext.request.contextPath}/images/app/home/icon.png" width="13" /></td>
            <td width="65"><span class="STYLE71 STYLE48"><strong>住院治疗:</strong></span></td>
            <td>${p.cureHospital }</td>
          </tr>
          <tr>
            <td width="13" align="center" valign="middle"><img src="${pageContext.request.contextPath}/images/app/home/icon.png" width="13" /></td>
            <td width="65"><span class="STYLE71 STYLE48"><strong>恢复时间:</strong></span></td>
            <td>${p.recoverDur }</td>
          </tr>
        </table></td>
        </tr>
      
    </table></td>
  </tr>

  <tr>
    <td height="90" align="center" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="6">
      <tr>
        <td><img src="${pageContext.request.contextPath}/images/app/home/splc.png" width="100%" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="100" align="center" valign="top" bgcolor="#FFFFFF" class="line_down"> <span class="STYLE40">　 </span><br />
    <table width="97%" border="0" cellspacing="2" cellpadding="1">
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
        <td>${p.crowd }</td>
      </tr>

      <tr valign="bottom" class="line_down">
        <td  class="STYLE44">&nbsp;</td>
      </tr>
      <tr align="left" valign="bottom">
        <td  class="STYLE80">禁忌人群</td>
      </tr>
      <tr class="line_down">
        <td>${p.tabooCrowd }</td>
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
    <td align="center" valign="top" bgcolor="#FFFFFF"><a href="http://www.5imfq.com/download/app.html">
<img src="${pageContext.request.contextPath}/images/app/home/1px.png" width="1" height="4" /><br />
<img src="${pageContext.request.contextPath}/images/app/home/gg_download.png" width="100%" border="0" /></a><br /><br /></td>
  </tr>
</table>
</body>
</html>

