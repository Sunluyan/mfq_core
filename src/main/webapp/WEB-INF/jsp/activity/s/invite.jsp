<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<title>美分期-邀请码</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/activity/commer.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/activity/invitation.css"/>
    <script src="${pageContext.request.contextPath}/js/activity/jquery-1.8.2.min.js"></script>

</head>

<body>
<div class="inv">
  <div class="inv_top">
     <div class="invtop1">
        <span class="inv_login">
        	<c:if test="${user == null }"><a href="${pageContext.request.contextPath}/activity/login">登录</a></c:if>
        </span>
     </div>
     <form id="fom" action="${pageContext.request.contextPath}/activity/doInvite" method="post">
     <input type="hidden" value="${user.uid }" name="uid" id="uid"/>
     <div class="invtop2">
        <span class="inv_kill">请输入秒杀邀请码</span>
        <input type="text" class="inv_input1" maxlength="6" name="code" id="code">
        <span style="color:red;">${error_msg }</span>
        <span class="inv_gain"></span>
        <div class="inv_fast1">
           <span class="inv_fast">急速秒杀</span>
        </div>
     </div>
     </form>
  </div>
  <div class="inv_center">
      
      <img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/s-images2.png">
      <img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/s-images1.png">
      
  </div>
  <div class="inv_bottom">注：秒杀时请注意产品所在地，秒杀完成后请在规定时间内到指定医院完成项目，过期无效。</div>
</div>

<div id="bg"></div>
<div id="show">
<div id="msg"></div>

</div>

</body>

<script>
$(function(){
	$(".inv_fast").click(function(){
		var code = $("#code").val();
		var uid = $("#uid").val();
		if(code.length!=6){
			$("#bg").css("display","block");
			$("#show").css("display","block");
			$("#share").css("display","none");
			$("#msg").html("邀请码不正确！！");
			return ;
		}
		$.post('${pageContext.request.contextPath}/activity/checkInvite',{code:code,uid:uid},function(data){
			data = eval("(" + data + ")");
			if(data.code == 0){
				$("#fom").submit();				
			}else{
				$("#bg").css("display","block");
				$("#show").css("display","block");
				$("#msg").html(data.msg);
				$("#share").css("display","none");
			}
		})
		
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