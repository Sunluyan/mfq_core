<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<title>二维码-美分期</title>
<meta
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"
	name="viewport" id="viewport" />

<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/wx/commer.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/wx/QRCode.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/wx/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/wx/QRCode.js"></script>
</head>

<body>

	<!--login-main-->
	<div class="login-main">
		<h2>logo</h2>
		<h3>
			<span>美分期</span>
		</h3>
		<form name="reg" id="reg" action="${pageContext.request.contextPath}/wechat/qr_coupons"
			method="post">
			<input type="hidden" value="${token}" name="uid" id="uid" />
			
			<div
				style="width: 100%; background-color: #FFF; height: 46px; line-height: 32px; margin-top: 3em;">
				<div class="uname">
					<span class="name_line"><img src="${pageContext.request.contextPath}/images/wx/man.png" /></span>
					<input type="text" placeholder="请输入您的姓名" maxlength="4" minlength="2" name="name"
						class="us_name">
				</div>
				<label class="error" id="uname-error"></label>
			</div>
			
			<div
				style="width: 100%; background-color: #FFF; height: 46px; line-height: 32px;">
				<div class="phone">
					<span class="phone_line"><img src="${pageContext.request.contextPath}/images/wx/mobil.png" /></span>
					<input type="text" placeholder="请输入电话号码" maxlength="11"
						name="phone" class="us_phone">
				</div>
				<label for="phone" class="error" id="phone-error"></label>
			</div>

			<div style="width: 100%; background-color: #FFF; height: 46px; line-height: 32px; position: relative;">
				<div class="check">
					<input type="text" placeholder="请输入验证码" maxlength="4"
						class="v_code" name="vcode"> <div class="v_line" id="btnSendCode" >获取验证码</div>
				</div>
				<label for="vcode" class="error" id="vcode-error"></label>
			</div>
			
			<div style="width: 100%; position: relative;">
				<div class="check2">
					<input type="checkbox" name="check" checked="checked"><span style="margin-left:10px;">是否接受微整形</span>
				</div>
			</div>

			<!--提示-->
			<div class="cue clearfix"></div>
			<div class="quren">确认</div>
		</form>
	</div>

	<!--footer come-->
	<footer>
		<P>Copyright ©2015 美分期</P>
	</footer>

	
	<!--js come-->
	<script type="text/javascript">
		window.thisapp='${pageContext.request.contextPath}';
		userRegister();
	</script>


	<!--js end-->
</body>
</html>
