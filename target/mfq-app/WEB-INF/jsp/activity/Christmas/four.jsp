<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
	<link rel="stylesheet" type="text/css" href="/css/activity/Christmas/zhuce.css">
	<script src="/js/activity/jquery-1.8.2.min.js"></script>
	<title>注册领取红包</title>
</head>
<body>
<div class="box">
		<input alt="手机号" type="text" placeholder="手机号" id = "phone">
		<div class="box01">
			<input class="yanzhengma" alt="验证码" type="text" placeholder="验证码" id="vcode">
			<input class="huoqu" href="#" type="button" id="btnSendCode" value="获取" >
		</div>
		<input alt="密&nbsp&nbsp&nbsp码" type="password" placeholder="密&nbsp&nbsp&nbsp码" id="password">
		<div class="yaoqingma">邀请码&nbsp UF8HFWTSDFR</div>
		<input class="queren" value="确认" type="submit">
</div>
<script type="text/javascript">  

/**********获取验证码倒计时事件**********************************/
var wait=60;  
function time(o) {  
	if (wait == 0) {  
		o.removeAttribute("disabled");            
		o.value="获取"; 
		document.getElementById("btnSendCode").disabled = false;
		wait = 60;  
	} else {  
		o.setAttribute("disabled", true);  
		o.value="(" + wait + ")";
		document.getElementById("btnSendCode").disabled = true;  
		wait--;  
		setTimeout(function() {  
			time(o)  
		},1000)  
	}  
}


var validateVcode = "";
$("#btnSendCode").click(function(){
	var phone = document.getElementById("phone").value
	$.ajax({
		url:"/wechat/sendcode/",
		data:{
			mobile:phone,
			qewrasdfzxcv:"asdfasdf"
		},
		type:"post",
		dataType:"json",
		success:function(json){
			if(json.code == 0){
				time(document.getElementById("btnSendCode"));
			}
			else{
				alert(json.code+"\t"+json.msg)
			}
		}
	})
})

$(".queren").click(function(){
	var phone = document.getElementById("phone").value
	var vcode = document.getElementById("vcode").value
	var password = document.getElementById("password").value
	$.ajax({
		url:"/activity/Christmas/register/",
		type:"post",
		dataType:"json",
		data:{
			phone:phone,
			vcode:vcode,
			passwd:password
		},
		success:function(data){

			if(data.code == 0){
				location.href = "/activity/Christmas/pass"
			}
			else if (data.code == 1104){
				location.href = "/activity/Christmas/fail"
			}
			else{
				alert(data.msg);
			}
		}
	})
})




</script>
</body>
</html>