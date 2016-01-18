<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>丑女小美变美记</title>
	<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport" />
	<link rel="stylesheet" type="text/css" href="/css/activity/newyear/loading.css">
	<script type="text/javascript" src="/js/activity/jquery-1.8.2.min.js"></script>
	<style>
		/* 		@font-face{
            font-family:xueyafei;
            src: url();
        } */
		*{
			padding:0;
			margin:0;
		}
		html,body{
			width:100%;
			height:100%;
		}
		.now{
			width:100%;
			height:100%;
			background:url(http://7xlb4j.com2.z0.glb.qiniucdn.com/newyear%2before.png);
			background-size:cover;
			position:relative;
			background-position: 50% 50%;
		}
		.shizhong{
			width:96px;
			height:96px;
			background:url(http://7xlb4j.com2.z0.glb.qiniucdn.com/newyear%2shizhong_03.png);
			position:absolute;
			left:50%;
			margin-left:-48px;
			top:55%;
			margin-top:-48px;
			background-size:cover;

		}
		.fenzhen{
			width:25px;
			height:25px;
			background:url(http://7xlb4j.com2.z0.glb.qiniucdn.com/newyear%2fenzhen_03.png);
			position:absolute;
			left:36.1%;
			top:39%;
			margin-left:-12px;
			margin-top:-13px;
			transform: rotate(0deg);
			/* 兼容 */
			-ms-transform: rotate(0deg);/* IE 9 */
			-moz-transform: rotate(0deg);/* Firefox */
			-webkit-transform: rotate(0deg);/* Safari and Chrome */
			-o-transform: rotate(0deg); /* Opera */
			/*兼容 */
			transform-origin: right bottom;
			/* 兼容 */
			-ms-transform-origin: right bottom;/* IE 9 */
			-moz-transform-origin: right bottom;/* Firefox */
			-webkit-transform-origin: right bottom;/* Safari and Chrome */
			-o-transform-origin: right bottom; /* Opera */
			/*兼容 */
			animation: roll 3s linear 0s ;
			/* 兼容 */
			-ms-animation: roll 3s linear 0s ;/* IE 9 */
			-moz-animation: roll 3s linear 0s ;/* Firefox */
			-webkit-animation: roll 3s linear 0s ;/* Safari and Chrome */
			-o-animation: roll 3s linear 0s ; /* Opera */
			/*兼容 */
			animation-fill-mode : both;
			/* 兼容 */
			-ms-animation-fill-mode : both;/* IE 9 */
			-moz-animation-fill-mode : both;/* Firefox */
			-webkit-animation-fill-mode : both;/* Safari and Chrome */
			-o-animation-fill-mode : both; /* Opera */
			/*兼容 */
		}

		/* 兼容
                    -ms-transform:rotate(30deg); IE 9
                    -moz-transform:rotate(30deg); Firefox
                    -webkit-transform:rotate(30deg); Safari and Chrome
                    -o-transform:rotate(30deg); Opera
        兼容 */
		@keyframes roll{
			from{
				transform: rotate(0deg);
				/*兼容*/
				-ms-transform: rotate(0deg);/* IE 9 */
				-moz-transform: rotate(0deg);/* Firefox */
				-webkit-transform: rotate(0deg);/* Safari and Chrome */
				-o-transform: rotate(0deg); /* Opera */
				/*兼容*/
			}
			to{
				transform: rotate(47deg);
				/*兼容*/
				-ms-transform: rotate(47deg);/* IE 9 */
				-moz-transform: rotate(47deg);/* Firefox */
				-webkit-transform: rotate(47deg);/* Safari and Chrome */
				-o-transform: rotate(47deg); /* Opera */
				/*兼容*/
			}
		}
		/* 兼容 */
		@-ms-keyframes roll{
			from{
				transform: rotate(0deg);
				/*兼容*/
				-ms-transform: rotate(0deg);/* IE 9 */
				-moz-transform: rotate(0deg);/* Firefox */
				-webkit-transform: rotate(0deg);/* Safari and Chrome */
				-o-transform: rotate(0deg); /* Opera */
				/*兼容*/
			}
			to{
				transform: rotate(47deg);
				/*兼容*/
				-ms-transform: rotate(47deg);/* IE 9 */
				-moz-transform: rotate(47deg);/* Firefox */
				-webkit-transform: rotate(47deg);/* Safari and Chrome */
				-o-transform: rotate(47deg); /* Opera */
				/*兼容*/
			}
		} /* IE 9 */
		@-moz-keyframes roll{
			from{
				transform: rotate(0deg);
				/*兼容*/
				-ms-transform: rotate(0deg);/* IE 9 */
				-moz-transform: rotate(0deg);/* Firefox */
				-webkit-transform: rotate(0deg);/* Safari and Chrome */
				-o-transform: rotate(0deg); /* Opera */
				/*兼容*/
			}
			to{
				transform: rotate(47deg);
				/*兼容*/
				-ms-transform: rotate(47deg);/* IE 9 */
				-moz-transform: rotate(47deg);/* Firefox */
				-webkit-transform: rotate(47deg);/* Safari and Chrome */
				-o-transform: rotate(47deg); /* Opera */
				/*兼容*/
			}
		}/* Firefox */
		@-webkit-keyframes roll{
			from{
				transform: rotate(0deg);
				/*兼容*/
				-ms-transform: rotate(0deg);/* IE 9 */
				-moz-transform: rotate(0deg);/* Firefox */
				-webkit-transform: rotate(0deg);/* Safari and Chrome */
				-o-transform: rotate(0deg); /* Opera */
				/*兼容*/
			}
			to{
				transform: rotate(47deg);
				/*兼容*/
				-ms-transform: rotate(47deg);/* IE 9 */
				-moz-transform: rotate(47deg);/* Firefox */
				-webkit-transform: rotate(47deg);/* Safari and Chrome */
				-o-transform: rotate(47deg); /* Opera */
				/*兼容*/
			}
		} /* Safari and Chrome */
		@-o-keyframes roll{
			0%{
				transform: rotate(0deg);
				/*兼容*/
				-ms-transform: rotate(0deg);/* IE 9 */
				-moz-transform: rotate(0deg);/* Firefox */
				-webkit-transform: rotate(0deg);/* Safari and Chrome */
				-o-transform: rotate(0deg); /* Opera */
				/*兼容*/
			}
			100%{
				transform: rotate(47deg);
				/*兼容*/
				-ms-transform: rotate(47deg);/* IE 9 */
				-moz-transform: rotate(47deg);/* Firefox */
				-webkit-transform: rotate(47deg);/* Safari and Chrome */
				-o-transform: rotate(47deg); /* Opera */
				/*兼容*/
			}
		} /* Opera */
		/*兼容 */
		.after{
			width:100%;
			height:100%;
			position:absolute;
			left:0;
			top:0;
			background:url(http://7xlb4j.com2.z0.glb.qiniucdn.com/newyear%2after.png);
			background-size:cover;
			background-position: 50% 50%;

			/*animation:state 4s linear 0s ;*/
			opacity:0;
			transition: all 1s;
			/* 兼容 */
			-ms-transition:all 1s; /* IE 9 */
			-moz-transition:all 1s;/* Firefox */
			-webkit-transition:all 1s;/* Safari and Chrome */
			-o-transition:all 1s;/* Opera */
			/*兼容 */

		}
		/* .after-2{
			animation:out 6s linear 5s ;
		} */
		@-webkit-keyframes out{
			from{
				opacity:0;

			}
			to{
				opacity:1;
			}
		}
		@-webkit-keyframes state{
			from{
				opacity:0;

			}
			to{
				opacity:0;
			}
		}
		.wenzi{
			width:50%;
			height:10%;
			position:absolute;
			left:15%;
			top:15%;
		}
		.wenzi-1{
			width:128px;
			height:28px;
			background:url(http://7xlb4j.com2.z0.glb.qiniucdn.com/newyear%2T-1_03.png);
			background-size:cover;
			opacity:0;
			transition: all 5s;
			margin-top:5%;
			/*animation:state 11s linear 0s ;*/
			/*animation:T1-out 3s linear 11s ;*/
			/* 兼容 */
			-ms-transition:all 5s; /* IE 9 */
			-moz-transition:all 5s;/* Firefox */
			-webkit-transition:all 5s;/* Safari and Chrome */
			-o-transition:all 5s;/* Opera */
			/*兼容 */
		}

		@-webkit-keyframes T1-out{
			from{
				opacity:0;
			}
			to{
				opacity:1;
			}
		}
		.wenzi-2{
			width:234px;
			height:25px;
			background:url(http://7xlb4j.com2.z0.glb.qiniucdn.com/newyear%2T-2_07.png);
			background-size:cover;
			opacity:0;
			transition: all 5s;
			margin-top:5%;
			/* 兼容 */
			-ms-transition:all 5s; /* IE 9 */
			-moz-transition:all 5s;/* Firefox */
			-webkit-transition:all 5s;/* Safari and Chrome */
			-o-transition:all 5s;/* Opera */
			/*兼容 */
		}
		.wenzi-3{
			width:74px;
			height:24px;
			float:left;
			background:url(http://7xlb4j.com2.z0.glb.qiniucdn.com/newyear%2T-3_11.png);
			background-size:cover;
			opacity:0;
			transition: all 5s;
			margin-top:5%;
			/* 兼容 */
			-ms-transition:all 5s; /* IE 9 */
			-moz-transition:all 5s;/* Firefox */
			-webkit-transition:all 5s;/* Safari and Chrome */
			-o-transition:all 5s;/* Opera */
			/*兼容 */
		}
		.xiaomei{
			width:70%;
			height:60%;
			background:url(http://7xlb4j.com2.z0.glb.qiniucdn.com/newyear%2xiaomei_14.png);
			background-size:cover;
			opacity:0;
			transition: all 5s;
			position:absolute;
			left:0;
			bottom:0;
			/* 兼容 */
			-ms-transition:all 5s; /* IE 9 */
			-moz-transition:all 5s;/* Firefox */
			-webkit-transition:all 5s;/* Safari and Chrome */
			-o-transition:all 5s;/* Opera */
			/*兼容 */
		}
		.right{
			position:absolute;
			right:-65px;
			top:64%;
			width:57%;
			height:20%;
			opacity:0;
			transition:all 3s;
			/* 兼容 */
			-ms-transition:all 3s; /* IE 9 */
			-moz-transition:all 3s;/* Firefox */
			-webkit-transition:all 3s;/* Safari and Chrome */
			-o-transition:all 3s;/* Opera */
			/*兼容 */

		}
		@media only screen and (min-device-width :1080px) and (-webkit-min-device-pixel-ratio : 2.5)
		{ .right{
			position:absolute;
			right:-85px;
			top:64%;
			width:57%;
			height:21%;
			opacity:0;
			transition:all 3s;
			/* 兼容 */
			-ms-transition:all 3s; /* IE 9 */
			-moz-transition:all 3s;/* Firefox */
			-webkit-transition:all 3s;/* Safari and Chrome */
			-o-transition:all 3s;/* Opera */
			/*兼容 */
		}
		}
		.please{

			background:url(http://7xlb4j.com2.z0.glb.qiniucdn.com/newyear%2help_03.png);
			background-size:cover;
			width:117%;
			height:44%;
			margin-bottom:9%;


		}

		.btn{

			border:none;
			background:#fdd803;
			border-radius:100px;
			background-size:cover;
			width:84%;
			height:48%;
			line-height:48%;
			float:right;
			text-align:center;

		}
		.btn a{
			color:#4c2f59;
			text-decoration:none;
			font-family:"微软雅黑";
			font-size:1.1rem;
		}
		.audio{
			width:30px;
			height:30px;
			border-radius:50%;
			background:rgba(255, 255, 255, 0.8);
			position:absolute;
			right:30px;
			top:30px;
			z-index:1000;

		}
		.audio .icon{
			width:100%;
			height:100%;
			background:url(http://7xlb4j.com2.z0.glb.qiniucdn.com/newyear%2music-2.png) center center no-repeat;
			position:absolute;
		}
	</style>
	<script>

		$(function(){
			var flag=true;
			$('.audio').click(function(event) {
				if(flag==true){

					$(this).css({backgroundPosition:'left top'});

					$('audio').get(0).pause();
					flag = false;
				}else{
					$(this).css({
						backgroundPosition: 'left bottom'
					});

					$('audio').get(0).play();
					flag=true;
				}
			});
		})

	</script>
</head>
<body>
<!-- loading -->
<div class="bg-color" id="bg-color">
	<div class="sk-circle" id="loader">
		<div class="sk-circle1 sk-child"></div>
		<div class="sk-circle2 sk-child"></div>
		<div class="sk-circle3 sk-child"></div>
		<div class="sk-circle4 sk-child"></div>
		<div class="sk-circle5 sk-child"></div>
		<div class="sk-circle6 sk-child"></div>
		<div class="sk-circle7 sk-child"></div>
		<div class="sk-circle8 sk-child"></div>
		<div class="sk-circle9 sk-child"></div>
		<div class="sk-circle10 sk-child"></div>
		<div class="sk-circle11 sk-child"></div>
		<div class="sk-circle12 sk-child"></div>
	</div>
</div>

<script>
	$(window).load(function() {
		$("#loader,#bg-color").animate({
			// top: -200
			opacity: 0
		}, 500);
	});
</script>

<div class="now">
	<div class="shizhong">
		<div class="fenzhen">

		</div>
	</div>

	<div class="after after-2">

	</div>

	<div class="wenzi">
		<div class="wenzi-1"></div>
		<div class="wenzi-2"></div>
		<div class="wenzi-3"></div>
	</div>

	<div class="xiaomei">
		<div class="right">
			<div class="please"></div>
			<button class="btn"><a href="/activity/newyear/two">帮助小美</a></button>
		</div>
	</div>
	<div class="audio">
		<div class="icon"></div>
		<audio src="http://7xlb4j.com2.z0.glb.qiniucdn.com/newyear%2happy newyear.mp3" autoplay></audio>
	</div>
</div>
<script type="text/javascript">

	setTimeout(function  () {
		$(".wenzi-1").css("opacity",1)
	},4000)

	setTimeout(function  () {
		$(".wenzi-2").css("opacity",1)
	},5500)

	setTimeout(function  () {
		$(".wenzi-3").css("opacity",1)
	},7000)

	setTimeout(function  () {
		$(".xiaomei").css("opacity",1)
	},8000)

	setTimeout(function  () {
		$(".after").css("opacity",1)
	},4000)
	setTimeout(function  () {
		$(".right").css("opacity",1)
	},10000)
</script>
</body>
</html>