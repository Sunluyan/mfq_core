<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport" />
	
	<!--[if lt IE 9]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
	<title>一顿饭的时间,提升颜值的秘密</title>
	<style type="text/css">
		*{
			margin: 0;
			padding: 0;
			box-sizing: border-box;
			-moz-box-sizing: border-box;
			-webkit-box-sizing: border-box;
			
		}
		html,body{
			width: 100%;
			height: 100%;
			overflow: hidden;
			font-size: 12px;
		}
		.container{
			width: 100%;
			height: 100%;
			position: absolute;
			left: 0;
			top: 0%;
		}
		.container .page{
			width: 100%;
			height: 100%;
			position: relative;
			
		}
		.container .page0{
			background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-02page-0.png) no-repeat;
			background-size: cover;
			background-origin: 50% 50%;
			-webkit-background-origin: 50% 50%;
			background-size:cover;
			position:relative;
			box-sizing: border-box;
			-moz-box-sizing: border-box;
			-webkit-box-sizing: border-box;
			padding-top: 3rem;
		}
		
		.title{
			width: 100%;
			height: ;
			
			
		}
		
		.page0 .line{
			width: 100%;
			height: 1rem;
			background: #f44688;
			margin-top: 1.5rem;
		}
		.container .page0 .header{
			width: 24rem;
			height: 8rem;
			background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-02page-0-header.png) no-repeat;
			background-size: contain;
			margin: 0 auto;
		}
		.page0 .body{
			width: 24rem;
			height: 26rem;
			background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-02page-0-body-2.png) no-repeat;
			background-size: contain;
			margin: 1rem auto;
		}
		
		.container .page1{
			position:relative;
			background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-02page-1.png) no-repeat;
			background-size: cover;
			background-origin: 50% 50%;
			-webkit-background-origin: 50% 50%;
			background-size:cover;
			position:relative;
			box-sizing: border-box;
			-moz-box-sizing: border-box;
			-webkit-box-sizing: border-box;
			padding-top: 1.5rem;
			color: #fff66b;
		}
		.container .page1 .header{
			width: 85%;
			margin: 0 auto;
			border-bottom: 1px solid #9097d3;
			overflow: hidden;
			padding-bottom:1rem;
		}
		.container .page1 .header table{
			cellspacing="1rem" ;
			cellpadding="1rem";
		}
		.container .page1 .header .td1{
			font-size: 1.4rem;
			vertical-align: bottom;
			cellspacing="1rem" ;
		}
		.container .page1 .header .td2{
			font-size: 1rem;
			vertical-align: bottom;
			
		}
		.container .page1 .fugai{
			width: 85%;
			margin: 0 auto;
			padding-top: 1rem;
		}
		.container .page1 .fugai .hot{
			margin-top: 1.3rem;
		}
		.container .page1 .p1{
			font-size: 1.4rem;
			height: 2.5rem;
			line-height: 2.5rem;
			
		}
		.container .page1 .p2{
			height: 3rem;
			line-height: 1.5rem;
			
		}
		.container .page1 .p3{
			height: 1.5rem;
			line-height: 1.5rem;
			
		}
		.container .page1 .big{
			color: #ec5d5d;
			
		}
		.container .page3{
			position:relative;
			background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-02page-2.png) no-repeat;
			background-size: cover;
			background-origin: 50% 50%;
			-webkit-background-origin: 50% 50%;
			background-size:cover;
			position:relative;
			box-sizing: border-box;
			-moz-box-sizing: border-box;
			-webkit-box-sizing: border-box;
			padding-top: 1.5rem;
			color: #2b1b0e;
		}
		.container .page3 .body{
			width: 85%;
			margin:0 auto;
		}
		.container .page3 .body .title{
			font-size: 1.4rem;
			height: 3rem;
			line-height: 3rem;
		}
		.container .page3 .body .description{
			line-height: 1.5rem;
		}
		.container .page3 .body .one{
			width: 100%;
			height: 100%;
			display: block;
			border-radius: 0.3rem;
			border: 1px solid #2b1b0e;
			
		}
		.container .page3 .body .top{
			margin: 1rem 0;
		}
		.container .page4{
			position:relative;
			background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-02page-3.png) no-repeat;
			background-size: cover;
			background-origin: 50% 50%;
			-webkit-background-origin: 50% 50%;
			background-size:cover;
			position:relative;
			box-sizing: border-box;
			-moz-box-sizing: border-box;
			-webkit-box-sizing: border-box;
			padding-top: 1.0rem;
			color: #2b1b0e;
			
		}
		.container .page4 .body{
			
		}
		.container .page4 .body{
			width: 85%;
			margin:0 auto;
		}
		.container .page4 .body .title{
			font-size: 1.4rem;
			height: 3rem;
			line-height: 3rem;
		}
		.container .page4 .body .description{
			line-height: 1.5rem;
		}
		.container .page4 .body .one{
			width: 100%;
			height: 100%;
			display: block;
			border-radius: 0.3rem;
			border: 1px solid #2b1b0e;
			
		}
		.container .page4 .body .top{
			margin: 0.2rem 0;
		}
		.container .page5{
			position:relative;
			background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-02page-4.png) no-repeat;
			background-size: cover;
			background-origin: 50% 50%;
			-webkit-background-origin: 50% 50%;
			background-size:cover;
			position:relative;
			box-sizing: border-box;
			-moz-box-sizing: border-box;
			-webkit-box-sizing: border-box;
			padding-top: 0.8rem;
			color: #ffa3c7;
		}
		.container .page5 .body{
			width: 85%;
			margin:0 auto;
		}
		.container .page5 .body .title{
			font-size: 1.4rem;
			height: 3rem;
			line-height: 3rem;
			color: #fff;
			margin-bottom: 0.5rem;
		}
		.container .page5 .doctor-1{
			border-bottom: 1px solid #ffa3c7;
			margin-bottom: 0.6rem;
			position: relative;
		}
		.container .page5 .doctor-2{

			margin-bottom: 1rem;
			position: relative;
		}
		.container .page5 .doctor-1 .span-1{
			width: 6rem;
			height: 18rem;
			background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-02doctor-1.png) no-repeat;
			background-size: contain;
			display: inline-block;
		}
		.container .page5 .doctor-1 .span-2{
			display: inline-block;
			position: absolute;
			left: 8rem;
			top: 0;
			
		}
		.container .page5 .doctor-2 .span-2{
			display: inline-block;
			position: absolute;
			left: 8rem;
			top: 0;
			
		}
		.container .page5 .doctor-1 .span-2 .p2{
			font-size: 1rem;
			line-height: 1.5rem;
		}
		.container .page5 .doctor-2 .span-2 .p2{
			font-size: 1rem;
			line-height: 1.5rem;
		}
		.container .page5 .span-2 .title-2{
			font-size: 1.4rem;
			line-height: 3rem;
			color: #ffa3c7s;
			font-weight:bold;
		}
		.container .page5 .span-2 .title-2-2{
			margin-top: 0.5rem;
		}
		.container .page5 .doctor-2 .span-1{
			width: 6rem;
			height: 18rem;
			background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-02doctor-2.png) no-repeat;
			background-size: contain;
			display: inline-block;
		}
		.container .page6{
			position:relative;
			background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-02page-5.png) no-repeat;
			background-size: cover;
			background-origin: 50% 50%;
			-webkit-background-origin: 50% 50%;
			background-size:cover;
			position:relative;
			box-sizing: border-box;
			-moz-box-sizing: border-box;
			-webkit-box-sizing: border-box;
			padding-top: 1.5rem;
			color: #fff;
		}
		.container .page6 .body{
			width: 85%;
			margin:0 auto;
		}
		.container .page6 .body .title{
			font-size: 1.8rem;
			height: 3rem;
			line-height: 3rem;
			color: #fff;
			margin-bottom: 0.5rem;
		}
		.code{
			display: block;
			width: 10rem;
			height: 10rem;
			margin: 1rem auto;
		}
		
		.xiangxiatishi{
			position: fixed;
			bottom: 20px;
			left: 50%;
			margin-left:-20px;
			cursor: pointer;
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
		/*4 4s*/
		@media only screen and (device-height :480px) and (-webkit-device-pixel-ratio:2){
			.page0 .body{
				width: 20rem;
				height: 26rem;

			}
			.container .page0 .header{
			width: 19rem;
			height: 7rem;
			}
			.container .page3 .body .title,.container .page4 .body .title,.container .page5 .body .title{
				height: 2rem;
				line-height: 2rem;
			}
			.container .page3 .body .top{
				margin: 0.5rem 0 0.4rem;
				
				
			}
			.container .page3 .body .one{
				width: 95%;
			}
			.container .page4 .body .top{
				margin: 0.5rem 0;
			}
			.container .page5 .doctor-1 .span-1{
				height: 17rem;
			}
		}
	</style>
	<script type="text/javascript" src="/js/activity/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="/js/activity/jquery.touchSwipe.min.js"></script>
	<script type="text/javascript">
	//if(window.localStorage){     alert("浏览支持localStorage") }else{    alert("浏览暂不支持localStorage") } //或者 if(typeof window.localStorage == 'undefined'){ 	alert("浏览暂不支持localStorage") }
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

							 if(nowpage > 5){
							 	nowpage = 5;
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

							 if(nowpage > 4){
							 	nowpage = 4;
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
		<div class="page page0 cur">
			<!-- <h1>你好，我是0号屏幕</h1> -->
			<h3 class="header"></h3>
			<div class="line">
				
			</div>
			<div class="body">
				
			</div>
			
			<img  class="xiangxiatishi" src="http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-02arrow.png" height="36" width="54" />
		</div>
		<div class="page page1">
			<!-- <h1>你好，我是1号屏幕</h1> -->
			<div class="header">
				<table>
					<tr style="border-spacing: 10px;">
						<td class="td1" >活动时间 </td>
						<td> </td>
						<td> </td>
						<td class="td2"> 2016年03月08日  14：30 ~ 17: 00</td>
					</tr>
					<tr>
						<td class="td1">邀请时间 </td>
						<td> </td>
						<td> </td>
						<td class="td2"> 2016年2月29日-3月7日</td>
					</tr>
					<tr>
						<td class="td1">邀请人数   </td>
						<td> </td>
						<td> </td>
						<td class="td2" style="color: #ec5d5d;font-weight: bold;"> 30人</td>
					</tr>
				</table>
				
			</div>
			<div class="fugai">
				<p class="p1">活动覆盖产品</p>
				<p class="p2">主要覆盖无创类针剂产品：水光针、瘦脸针 、玻尿酸等。</p>
				<p class="p1 hot">活动特惠</p>
				<p class="p3">3月8日参加活动的顾客，现场下单可享受</p>
				<p class="p3 big">水光针立减<span style="color: #fff66b; ">2000</span>元，原价<span style="color: #fff66b; ">3980</span>元，现价<span style="color: #fff66b; ">1980</span>元</p>
				<p class="p3 big">肉毒素（衡力）瘦咬肌：<span style="color: #fff66b; ">1888</span>元，免注射费,(仅限现场下单)</p>
				
				<p class="p1 hot">活动主要内容</p>
				<p class="p3">1. 签到领取礼包，全程免费提供饮品及零食</p>
				<p class="p3">2. 开场介绍及抽取现金抵用券</p>
				<p class="p3">3.北京美莱医院专家讲解，进行一对一交流</p>
				<p class="p3">4.与真人秀零距离接触，分享整形故事，变美心得</p>
				
				<p class="p1 hot">活动地址</p>
				<p class="p3">北京美莱医院5层-VIP室</p>
				<p class="p3">北京市朝阳区朝阳门外大街227号</p>
			</div>
			<img  class="xiangxiatishi" src="http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-02arrow.png" height="36" width="54" />
			
		</div>
		
		<div class="page page3">
			<!-- <h1>你好，我是3号屏幕</h1> -->
			<div class="body">
				<p class="title">医院介绍</p>
				<p class="description">
					北京美莱美容医院 汇集百位知名医师团队，安全塑美超过300万用户，18年来始终超越行业标准。北京美莱美容医院对于品质的不断追求也促进美莱成为“医美行业领跑品牌。
				</p>
				<img class="one top" src="http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-02one.png"/>
				<img class="one" src="http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-02two.png"/>
				
			</div>
			<img  class="xiangxiatishi" src="http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-02arrow.png" height="36" width="54" />
		</div>

		<div class="page page4">
			<!-- <h1>你好，我是4号屏幕</h1> -->
			<div class="body">
				<p class="title">活动场地</p>
				<p class="description">
					北京美莱美容医院 汇集百位知名医师团队，安全塑美超过300万用户。
				</p>
				<img class="one top" src="http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-02one.png"/>
				<img class="one" src="http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-02two.png" style="margin-bottom: 1rem;"/>
				<p class="description">
					北京美莱医院5层-VIP室
				</p>
				<p class="description">
					北京市朝阳区朝阳门外大街227号
				</p>
				<img  class="xiangxiatishi" src="http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-02arrow.png" height="36" width="54" />

			</div>
			
			
		</div>
		
		<div class="page page5">
			<!-- <h1>你好，我是5号屏幕</h1> -->
			<div class="body">
				<p class="title">医生介绍</p>
				<div class="doctor-1">
					<span class="span-1"></span>
					<span class="span-2">
						<p class="title-2">职称荣誉</p>
						<p class="p2">中国医师协会美容与整形医师分会会员</p>
						<p class="p2">中国医师协会白天鹅奖获得者</p>
						
						<p class="title-2 title-2-2">擅长领域</p>
						<p class="p2">注射微整形</p>
						<p class="p2">注射除皱</p>
						<p class="p2">五官塑性</p>
					</span>
				</div>
				<div class="doctor-2">
					<span class="span-1"></span>
					<span class="span-2">
						<p class="title-2">职称荣誉</p>
						<p class="p2">中国整形美容协会会员</p>
						<p class="p2">中国医师协会面部年轻化分会委员</p>
						<p class="p2">中国医师协会美容与整形医师分会会员</p>
						
						<p class="title-2 title-2-2">擅长领域</p>
						<p class="p2">注射微整形</p>
						<p class="p2">注射除皱</p>
						<p class="p2">五官塑性</p>
					</span>
				</div>
				
				
				<img  class="xiangxiatishi" src="http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-02arrow.png" height="36" width="54" />

			</div>
			
			
		</div>
		<div class="page page6">
			<!-- <h1>你好，我是6号屏幕</h1> -->
			<div class="body">
				<p class="title">报名方式</p>
				
				<div class="fangfa">
					<span style="font-size: 1.4rem;">方式1 : </span><div style="text-indent: 3rem;margin-bottom:1rem;font-size: 1.2rem;">长按下图“识别二维码”关注“美分期APP”公众号，发送
		   发送活动名称（纯针）+个人信息（姓名+电话）至公众号，
	          即可报名成功。如纯针+张三 186****1234。</div>
				</div>
				<div class="fangfa">
					<span style="font-size: 1.4rem;">方式2 : </span><div style="text-indent: 3rem;margin-bottom:1rem;font-size: 1.2rem;">发送活动名称（纯针）+个人信息（姓名+电话）至18600172457
		   如 : 纯针+张三 186****1234</div>
				</div>
				<div class="fangfa">
					<span style="font-size: 1.4rem;">方式3 : </span><div style="text-indent: 3rem;margin-bottom:1rem;font-size: 1.2rem;">致电至 客服 18600172457 电话报名</div>
				</div>
				<img src="http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-02code.png" class="code"/>

			</div>
			
			
		</div>
	</div>

	
	
	

</body>
</html>