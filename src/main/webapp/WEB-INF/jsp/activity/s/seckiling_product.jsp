<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>秒杀详情</title>
<script src="${pageContext.request.contextPath}/js/activity/jquery-1.8.2.min.js"></script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color:#B1030A;
}
.STYLE1 {color: #FFFFFF; font-weight: inherit;}
.STYLE3 {color: #FFFFFF; font-weight: bold; }
body,td,th {
	font-size: 16px;
	font-family: 微软雅黑;
}
.inv_fast{ cursor:pointer;}
#bg{ display: none;  position: absolute;  top: 0%;  left: 0%;  width: 100%;  height: 100%;  background-color: black;  z-index:1001;  -moz-opacity: 0.7;  opacity:.70;  filter: alpha(opacity=70);}
#show{display: none;  text-align:center; position: absolute;  top: 25%;  left: 15%;  width: 70%;  height: auto;  background-color: #FFE14A;  z-index:1002;  overflow: auto; padding-bottom: 5pt; }
#msg{color:#3BA9CC; font-size:1.6rem; margin:8px;}
#share{float:left;}
#share span{display: black; width:27pt; height:28pt; float: left; margin-left: 21pt;}
-->
</style>
</head>

<body>
<form id="fom" action="${pageContext.request.contextPath}/activity/doSeckilling" method="get">
<input type="hidden" value="${product.id}" name="id" id="id" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center" bgcolor="#B1030A"><img src="${product.img}" width="100%" /></td>
  </tr>
  <tr>
    <td align="center" valign="top" bgcolor="#DC0419"><table width="80%" border="0" cellpadding="5" cellspacing="5">
        <tr>
          <td align="left"><span class="STYLE1" style="font-size: 1.2rem;">${product.name }</span></td>
          <td align="right"><span class="STYLE1">秒杀价：${price }元</span></td>
        </tr>
        <tr>
          <td><span class="STYLE1">仅剩：${product.remainNum }份</span></td>
          <td><span class="STYLE1">${detail }</span></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td height="100" align="center" valign="middle" bgcolor="#FDD000"><table width="80%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>■医院：${hospital.name }<br />
			■地址：${hospital.address }<br />
			■联系电话：010-64399899</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="50" align="center" valign="middle" bgcolor="#B1030A"><span class="STYLE3">输入秒杀码+支付1元钱</span></td>
  </tr>
  <tr>
    <td bgcolor="#B1030A"><table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#B1030A">
      <tr>
        <td height="50" colspan="2" align="center"><span class="STYLE1">输入秒杀码-&gt;
          <input type="text" name="code" maxlength="6" id="code"/>
        </span>          <label></label></td>
        </tr>
      <tr>
        <td align="left">
        <c:if test="${is_start }">
        <img src="${pageContext.request.contextPath}/images/activity/s/seckiling/ms_14.png" width="180" class="inv_fast" />
        </c:if>
        <c:if test="${!is_start }">
        <img src="${pageContext.request.contextPath}/images/activity/s/seckiling/gray_button.png" width="180" />
        </c:if>
        </td>
        <td align="right"><a href="${pageContext.request.contextPath}/activity/f_share"><img src="${pageContext.request.contextPath}/images/activity/s/seckiling/ms_16.png" width="120" /></a></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td bgcolor="#B1030A" style="text-align: center;"><span id="countdown" style="color:#FFF;"></span></td>
  </tr>
</table>
</form>

<div id="bg"></div>
<div id="show">
<div id="msg"></div>
<script src="${pageContext.request.contextPath}/js/activity/items.js"></script>
<script>
fnTimeCountDown('${time}', $("#countdown"));
</script>
<script>
$(function(){
	$(".inv_fast").click(function(){
		var code = $("#code").val();
		var pid = $("#id").val();
		if(code.length!=6){
			$("#bg").css("display","block");
			$("#show").css("display","block");
			$("#share").css("display","none");
			$("#msg").html("秒杀码不正确！！");
			return ;
		}
		$("#fom").submit();
		//window.location.href=${pageContext.request.contextPath}+"/activity/doSeckilling?code="+code+"&pid="+pid;	
	})
	$("#bg").click(function(){
		$("#bg").css("display","none");
		$("#show").css("display","none");
		$("#share").css("display","none");
	});
});


</script>
</body>
</html>