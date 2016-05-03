<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <title>美分期</title>
	<script  src="/js/activity/mui.min.js"></script>
	<script src="/js/activity/jquery-1.11.1.min.js"></script>

    <script src="/js/activity/rem.js" type="text/javascript" charset="utf-8"></script>
    <link href="/css/activity/didi/mui.min.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="/css/activity/didi/index.css"/>
    <script type="text/javascript" charset="utf-8">

      	mui.init();
    </script>
</head>
<body>
	<div class="mui-content">
	    <div class="top">
	    	<img src="/images/activity/didi/123.png"/>

	    </div>
		<div class="middle">
			<ul>
				<li><a href="javascript:;"><img id="1" src="/images/activity/didi/juan1.png"/></a></li>
				<li><a href="javascript:;"><img id="2" src="/images/activity/didi/juan2.png"/></a></li>
				<li><a href="javascript:;"><img id="3" src="/images/activity/didi/juan3.png"/></a></li>
			</ul>
		</div>
	    <div class="information">

	    	<form action="" method="post">
	    		
	    		<input type="text" id="phoneNumber" name="phoneNumber" value="手机号" /><label id="get">获取验证码</label>
	    		<input type="text" name="code" id="code" value="验证码" />
	    	</form>
	    	
	    	
	    </div>
	    <div class="btn">
	    	<button>立即领取</button>
	    </div>
	    
	</div>
	<nav class="mui-bar mui-bar-tab" >
	    <button  onclick=window.open("http://www.5imfq.com/download/app.html")>下载美分期APP</button>

	</nav>
</body>

<script type="text/javascript">
/*	var sendCodeURL = "/api/customer/sendRegiterCode";
	var hasSendCode = false;
	var totalTime = 120;
	var lastTime = totalTime;

	var validateVcode = "";

	$(function(){
		$('#get').bind('click',function(){
			sendMobile();
		});
	});
	function sendMobile(){
		if(hasSendCode){
			return false;
		}
		var phone = $('#phoneNumber').val();
		if(!phone){
			alert('请填写手机号');
			return false;
		}

		var  url = sendCodeURL + "&phone=" + phone;
		$.getJSON(url,function(json){
			if(json['code'] == 22000){
				hasSendCodeShow();
			} else {
				alert("发送失败:" + json['message']);
			}
		});
	}

	function hasSendCodeShow(){
		hasSendCode = true;
		$("#get").removeClass('btns-success').addClass('btns-default');
		timeCount();
	}


	function timeCount(){
		$("#get").text(lastTime);

		if(lastTime <= 0){
			$("#get").removeClass('btns-default').addClass('btns-success').text('获取验证码');
			hasSendCode = false;
			lastTime = totalTime;
			return true;
		}

		lastTime--;
		setTimeout(function() {
			timeCount();
		}, 1000);
	};*/

	$(".btn").click(function(){

		var phone = document.getElementById("phoneNumber").value;
		var vcode = document.getElementById("code").value;
		$.ajax({
			url:"/activity/didi/mark",
			data:{
				mobile:phone,
				vcode:vcode,
				pids:"256,257,258"
			},
			type:"post",
			dataType:"json",
			success:function(data){
				if(data.code == 0){
					alert("注册成功~来下载APP使用美丽优惠券吧")
					location.href="http://www.5imfq.com/download/app.html";
				}
				else if (data.code == 1104 || data.code == 7788){
					alert("您是老用户~来下载APP使用美丽优惠券吧")
					location.href="http://www.5imfq.com/download/app.html";
				}
				else{
					alert(data.msg);
				}
			}
		})
	});
	//服务器创建验证码
	$("#get").click(function () {
		var phone = document.getElementById("phoneNumber").value;
		var message = "123123"
		$.ajax({
			url:"${pageContext.request.contextPath}/wechat/sendcode/",
			data:{
				mobile:phone,
				message:message
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
		var btn = $(this).get(0);
	})



</script>

</html>