<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		
		<meta name="keywords" content="51社保网,代缴社保,社保代缴,个人社保,社保补缴,社保网,社保代理,补充医疗" />
		<meta name="description" content="51社保网是中国最大的专业社保代缴平台,为您提供最专业最低价的社保代缴和社保补缴服务,另外还有最实惠的补充医疗保险和意外险.个人社保,我们保证您的社保不断缴,企业社保,我们免费为您提供定制化的社保代缴服务.51社保网力求做全国最好的社保代缴服务机构!" />
		<meta name="MSSmartTagsPreventParsing" content="True" />
		<meta name="Copyright" Content="本页版权归51社保网所有">
		<link rel="shortcut icon" href="/static/images/favicon.ico" >
		<!-- html5兼容 -->
		<!--[if lt IE 9]>
		<script type="text/javascript" src="/static/lib/html5/html5.js"></script>
		<![endif]-->
		<link type="text/css" rel="stylesheet" href="http://img01.shebao.net/2015081902/static/css/base.css" />
		<link type="text/css" rel="stylesheet" href="http://img01.shebao.net/2015081902/static/css/style.css" />
		<!--推广样式-->
		<link type="text/css" rel="stylesheet" href="http://img01.shebao.net/2015081902/static/css/tuiguang.css" />
		<!--推广样式 end-->
		<script type="text/javascript" src="http://img01.shebao.net/2015081902/static/lib/jquery/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="http://img01.shebao.net/2015081902/static/js/common.js"></script>
		<script type="text/javascript" src="http://img01.shebao.net/2015081902/static/js/jquery.jplaceholder.js"></script>
		<!-- banner -->
		<script type="text/javascript" src="http://img01.shebao.net/2015081902/static/js/jquery.flexslider-min.js"></script>
		<!-- 切换效果 -->
		<script type="text/javascript" src="http://img01.shebao.net/2015081902/static/js/jquery.tab.js"></script>
		<title>登录_51社保网</title>

	</head>
	
	<body class="body person">
	
	




<div class="menu">
	<div class="menu_box clearfix">
		<a class="menu_left" href="http://e.51shebao.com/" target="_blank" rel="nofollow">HR管理后台入口</a>
		<a class="menu_left" href="http://www.51shebao.org/" target="_blank" rel="nofollow" id="top_nav_bbs">社保论坛</a>
		<div class="menu_right">
			<p class="tal">咨询电话  400-616-5151</p>
			<ul class="menu_icon">
				<li>
					<a class="wx" href="">
						<img src="http://img01.shebao.net/2015081902/static/images/geren/QR_code.jpg" alt="51社保网微信公共账号">
					</a>
				</li>
				<li>
					<a class="wb" href="http://weibo.com/qqyuhr" target="_blank"></a>
				</li>
				<li>
					<a class="qq" href=" http://webim.qiao.baidu.com//im/index?siteid=4496944&ucid=3698680" target="_blank" rel="nofollow"></a>
				</li>
			</ul>
		</div>
	</div>
</div>
<header >
	<div class="header clearfix">
		<!--logo-->
				<h1 class="logo fl">
			<a href="/" onClick="_gaq.push(['_trackEvent', 'logo', 'shouye'])" target="_blank">
				<img src="http://img01.shebao.net/2015081902/static/images/logo.jpg" alt="【51社保网】-中国领先的企业社保代缴,个人社保补缴平台">	
			</a>
		</h1>	
				<!--end logo-->
		<!--主导航-->
		<nav class="fl clearfix">
											<a class=" ihr posRel" href="/" onClick="_gaq.push(['_trackEvent', 'nav', '网站首页'])"   id="top_nav_index" >

					网站首页 					<span class="posAbs"></span>
				</a>
								<a class=" ihr posRel" href="/service/com/" onClick="_gaq.push(['_trackEvent', 'nav', '企业服务'])"   id="top_nav_cop" >

					企业服务 					<span class="posAbs"></span>
				</a>
								<a class=" ihr posRel" href="/service/person/" onClick="_gaq.push(['_trackEvent', 'nav', '个人服务'])"   id="top_nav_person" >

					个人服务 					<span class="posAbs"></span>
				</a>
								<a class=" ihr posRel" href="http://www.101hr.com/" onClick="_gaq.push(['_trackEvent', 'nav', '101HR'])"   target="_blank"  id="101hr" >

					101HR <i class="posAbs new"></i>					<span class="posAbs"></span>
				</a>
								<a class=" ihr posRel" href="/about/about/" onClick="_gaq.push(['_trackEvent', 'nav', '关于我们 '])"   id="top_nav_about" >

					关于我们  					<span class="posAbs"></span>
				</a>
									</nav>
		<!--end 主导航-->

				<div class="login fr " >
			<a class="login_l borRadius" id="top_login"  href="/customer/login/" rel="nofollow">登录</a>
			<a class="login_r borRadius" id="top_register" href="/customer/register/" rel="nofollow">注册</a>
		</div>
			</div>
</header>






<script type="text/javascript">
	$(document).ready(function() {
		$(".personal").hover(function() {
			// $("#orderedlist li:last").hover(function() {
				$(".personal .tab").show();
			}, function() {
				$(".personal .tab").hide() ;
			});
		});
</script>


<section class="main clearfix">
	<div class="login_left fl ">
		<img src="http://img01.shebao.net/2015081902/static/images/login.png" alt="">
	</div>
	<div class="login_right fl borRadius">
		<div class="list">
			<form action="" method="post">
			<input  class="form-control success"  name="phone" type="text" placeholder="手机号" value="">
			<input  class="form-control" id="" name="password" type="password" placeholder="密码" value="">
			<!--  
			<div class="yzm clearfix" >
		        <input  class="form-control error" id="" name="code" type="text" placeholder="验证码" value="">
		        <img src="http://img01.shebao.net/2015081902/static/images/yanzm.png" arl="验证码"/>
		        <span>看不清，换一张</span>
	        </div>
	        -->
	        <div class="checkbox clearfix">
	              <input type="checkbox" name="remember" value="1" checked> <label for="check">记住我</label>
	        </div>
	        <button type="submit" class="btns " id="login_btn" onClick="_gaq.push(['_trackEvent', 'customer', 'login_button'])" rel="nofollow">登录</button>
	        <div class="ahref tRight">
	        	<a href="/customer/register" id="register" onClick="_gaq.push(['_trackEvent', 'customer', 'register'])" rel="nofollow">立即注册>></a>
	        </div>
	        <p class="weixin">微信公众账号:wx51shebao</p>
	        </form>
		</div>
	</div>
</section>

	<footer class="footer clearfix">
		<div class="footer_box clearfix">
			<div class="footer_left fl">
				<dl class="site_item">
					<dt>企业服务</dt>
					<dd><a href="/service/com_flow/" target="_blank" rel="nofollow">模式对比</a></dd>
					<dd><a href="/service/guide_serve/" target="_blank" rel="nofollow">服务内容</a></dd>
					<dd><a href="/service/flow/" target="_blank" rel="nofollow">怎样办理</a></dd>
				</dl>
				<dl class="site_item">
					<dt>个人服务</dt>
					<dd><a href="/service/guide_flow/" target="_blank" rel="nofollow">社保的好处</a></dd>
					<dd><a href="/service/person_flow/" target="_blank" rel="nofollow">怎样办理</a></dd>
				</dl>
				<address class=" clearfix">“51社保网”国贸总部：北京市朝阳区建外SOHO2号楼2005室</address>
			</div>
			<div class="footer_right fl">
				<div class="foot_r_top clearfix">
					<div class="erwei">
						<img src="http://img01.shebao.net/2015081902/static/images/erwei.jpg" alt="51社保网微信公众账号">
					</div>
					<div class="hotline">
						<span>咨询热线：</span>
						<p>400-616-5151</p>
					</div>
					<div class="online">
						<a onClick="_gaq.push(['_trackEvent', 'lijizixun', 'footer'])" href=" http://webim.qiao.baidu.com//im/index?siteid=4496944&ucid=3698680" class="icon" target="_blank" rel="nofollow"></a>
						<!--<a href="" class="icon icon2"></a>-->
					</div>
				</div>
				<p class="copy">Copyright&nbsp;&nbsp;©&nbsp;&nbsp;2010-2015&nbsp;&nbsp;All&nbsp;&nbsp;Rights&nbsp;&nbsp;
					Reserved.&nbsp;&nbsp;<a herf="http://www.miibeian.gov.cn/" target="_blank" rel="nofollow">京ICP备13001493号-7</a></p>
				<p class="copy">北京众合天下管理咨询有限公司(<a herf="/" target="_blank">www.51shebao.com</a>)&nbsp;&nbsp;版权所有</p>
			</div>
		</div>
	</footer>
	
	


<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?9ff162bfe3685ed2a2c02c334f975e51";
  var s = document.getElementsByTagName("script")[0];
  s.parentNode.insertBefore(hm, s);
})();
</script>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?717da9fbbb380c7b043ea096861c919d";
  var s = document.getElementsByTagName("script")[0];
  s.parentNode.insertBefore(hm, s);
})();
</script>
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-57883134-1', 'auto');
  ga('send', 'pageview');
</script>

	<!--爱投DSP 重定向代码-->
	<script type="text/javascript">
            var _gtc = _gtc || [];
            _gtc.push(["fnSetAccount", "927"]);
            var nGtc = document.createElement("script");nGtc.type = "text/javascript"; nGtc.async = true;nGtc.src = ("https:" == document.location.protocol ? "https://sslcdn.istreamsche.com" : "http://ga.istreamsche.com") + "/stat/gtc.js";document.getElementsByTagName("head")[0].appendChild(nGtc);
      </script>
      <!--爱投DSP 重定向代码 end-->
	</body>
</html>
	
	