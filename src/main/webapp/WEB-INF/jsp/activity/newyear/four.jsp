<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
	<link rel="stylesheet" type="text/css" href="/css/activity/newyear/zhuce.css">
	<script src="/js/activity/jquery-1.8.2.min.js"></script>
	<title>注册领取</title>
</head>
<body>
<div class="box">
	<form>
		<input alt="手机号" type="text" id="phone" placeholder="手机号">
		<div class="box01">
			<input class="yanzhengma" alt="验证码" type="text" id="vcode" placeholder="验证码">
			<input class="huoqu" href="#" type="button" id="btnSendCode" value="获取" >
		</div>
		<input alt="密码" type="password" id="password" placeholder="密&nbsp&nbsp&nbsp码">
		<div class="yaoqingma">邀请码&nbspUF8HFWTSDFR</div>
		<div class="error"></div>
		<input class="queren" value="确认" type="button">
	</form>
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
		var phone = document.getElementById("phone").value;
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
					$(".error").html(json.msg)
				}
			}
		})
	})

	$(".queren").click(function(){
		var phone = document.getElementById("phone").value;
		var vcode = document.getElementById("vcode").value;
		var password = document.getElementById("password").value;
		$.ajax({
			url:"/activity/newyear/register/",
			type:"post",
			dataType:"json",
			data:{
				phone:phone,
				vcode:vcode,
				passwd:password
			},
			success:function(data){

				if(data.code == 0){
					location.href = "/activity/newyear/pass"
				}
				else if (data.code == 1104 || data.code == 7788){
					location.href = "/activity/newyear/fail"
				}
				else{
					alert(data.msg);
					$(".error").html(data.msg)
				}
			}
		})
	})




</script>
</body>
</html>