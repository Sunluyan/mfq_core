<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport" />
	<title>小美预约</title>
	<style type="text/css">
		*{
			margin: 0;
			padding: 0;
		}
		html,body{
			width: 100%;
			height: 100%;
			overflow: hidden;
		}
		.container{
			width: 100%;
			height: 100%;
			position: absolute;
			left: 0;
			top: 0%;
		}
		.container .page{
			height: 100%;
			position: relative;
		}
		.container .page0{
			background: #fff;
			background-size:cover;
			position:relative;
			
		}
		.line{
			width:87%;
			height:46%;
			-background:url(http://7xlb4j.com2.z0.glb.qiniucdn.com/line-bg_03.png);
			background-size:contain;
			position:absolute;
			left:50%;
			top:24%;
			margin-left:-43%;
		}
		.line img{
			width:100%;
			height:100%;
		}
		.line-4{
			width:90%;
			height:48%;
			left:50%;
			top:23%;
			margin-left:-44%;
		}
		.container .page1{
			background-color: #fff;
			position:relative;
		}
		.container .page2{
			background: #fff;
			position:relative;
		}
		.container .page3{
			background-color: #fff;
			position:relative;
		}
		.container .page4{
			background-color: #fff;
			position:relative;
		}
		
		.container .page1 img.no1{
			position: absolute;
			left: 10px;
			top: 50px;
			-webkit-transition:all 1s ease 0s;
		}
		.container .page1.cur img.no1{
			-webkit-transform:rotate(720deg);
		}
		.container .page1 img.no2{
			position: absolute;
			left: 600px;
			top: 50px;
			-webkit-transition:all 1s ease 2s;
		}
		.container .page1.cur img.no2{
			left: 30px;
			top: 100px;
			-webkit-transform:rotate(720deg);
		}
		.xiangxiatishi{
			position: fixed;
			bottom: 20px;
			left: 50%;
			margin-left:-20px;
			
			-webkit-animation:dong 1s linear 0s infinite alternate;
			transition: all 3s linear;
		}

		@-webkit-keyframes dong{
			from{
				bottom:60px;
				transform: scale(0.6, 0.8);
			}
			to{
				bottom: 20px;
				transform: scale(0.9, 0.9);
			}
		}
		.num {
			position:absolute;
			left:50%;
			margin-left:-18px;
			top:11%;
			width:36px;
			height:36px;
			background-image:url(http://7xlb4j.com2.z0.glb.qiniucdn.com/num-1_03.png);
			background-size:contain;
		}
		.num-2{
			background-image:url(http://7xlb4j.com2.z0.glb.qiniucdn.com/num-2_03.png);
		}
		.num-3{
			background-image:url(http://7xlb4j.com2.z0.glb.qiniucdn.com/num-3_03.png);
		}
		.num-4{
			background-image:url(http://7xlb4j.com2.z0.glb.qiniucdn.com/num-4_03.png);
		}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/activity/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/activity/jquery.touchSwipe.min.js"></script>
	<script type="text/javascript">
		$(document).ready(
			function() {
				var nowpage = 0;
				//给最大的盒子增加事件监听
				$("body").swipe(
					{
						swipe:function(event, direction, distance, duration, fingerCount) {
							 if(direction == "up"){
							 	nowpage = nowpage + 1;
							 }else if(direction == "down"){
							 	nowpage = nowpage - 1;
							 }

							 if(nowpage > 3){
							 	nowpage = 3;
							 }

							 if(nowpage < 0){
							 	nowpage = 0;
							 }

							$(".container").animate({"top":nowpage * -100 + "%"},200);

							$(".page").eq(nowpage).addClass("cur").siblings().removeClass("cur");
						}

						/* threshold:2;*/
					}
				);


				/*点击翻页开始*/


				$(".xiangxiatishi").click(
					{
						swipe:function(event, direction, distance, duration, fingerCount) {
							 if(direction == "up"){
							 	nowpage = nowpage + 1;
							 }else if(direction == "down"){
							 	nowpage = nowpage - 1;
							 }

							 if(nowpage > 3){
							 	nowpage = 3;
							 }

							 if(nowpage < 0){
							 	nowpage = 0;
							 }

							$(".container").animate({"top":nowpage * -100 + "%"},400);

							$(".page").eq(nowpage).addClass("cur").siblings().removeClass("cur");
						}
					}
				);
			}
		);
	</script>
 
</head>
<body onmousewheel="return false;">
	<div class="container">
		<div class="page page0 cur"><!-- <h1>你好，我是0号屏幕</h1> -->
			<div class="line">
				<img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/bg-1_03.png" height="474" width="540" alt="">
			</div>
			<div class="num">
				
			</div>
			<img  class="xiangxiatishi" src="http://7xlb4j.com2.z0.glb.qiniucdn.com/prompt.png" height="36" width="54" />
		</div>
		<div class="page page1">
			<!-- <h1>你好，我是1号屏幕</h1> -->
			<div class="line">
				<img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/bg-2_03.png" height="474" width="540" alt="">
			</div>
			<div class="num num-2">
				
			</div>
			<img  class="xiangxiatishi" src="http://7xlb4j.com2.z0.glb.qiniucdn.com/prompt.png" height="36" width="54" />
			
		</div>
		<!-- <div class="page page2"><h1>你好，我是2号屏幕</h1>
			<div class="line">
				<img src="../..http://7xlb4j.com2.z0.glb.qiniucdn.com/activity/mc/bg-3_03.png" height="474" width="540" alt="">
			</div>
			<div class="num num-3">
				
			</div>
		</div> -->
		<div class="page page3">
			<!-- <h1>你好，我是3号屏幕</h1> -->
			<div class="line">
				<img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/bg-3_03.png" height="474" width="540" alt="">
			</div>
			<div class="num num-3">
				
			</div>
			<img  class="xiangxiatishi" src="http://7xlb4j.com2.z0.glb.qiniucdn.com/prompt.png" height="36" width="54" />
		</div>

		<div class="page page4">
			<!-- <h1>你好，我是4号屏幕</h1> -->
			<div class="line line-4">
				<img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/bg-4_03.png" height="560" width="616" alt="">
			</div>
			<div class="num num-4">
				
			</div>
		</div>
	</div>

	
	
	

</body>
</html>