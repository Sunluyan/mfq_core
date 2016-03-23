$(function() {
	var width=$(window).width();
	//alert(width);
	$('.banner-dot li').click(function(event){
		var index=$(this).index();
		num = index;
		$(this).addClass('current').siblings().removeClass('current');
		left=-index*width;
		$('.banner-img').stop().animate({left:left}, 500);
	})
	
	function autoPlay(){
		num++;
		var index=$(this).index();
		if(num>2){
			num=0;
		}
		$('.banner-dot li').eq(num).addClass('current').siblings().removeClass('current');
		left=-num*width;
		$('.banner-img').stop().animate({left:left}, 500);
		
	}
	function state(){
		clearInterval(timer);
	}
	var num=0;
	var timer=null;
	timer=setInterval(autoPlay,3000)
	$('.banner').mouseover(state).mouseout(function(){
		timer=setInterval(autoPlay,3000)
	});
	
	function prevPage(){
		num--;
		//如果播放到第1张的时候，再点击，就跳转到最后一张
		if(num < 0){
			num = 2;
		}
		$('.banner-dot li').eq(num).addClass('current').siblings().removeClass('current');
		//通过编号查找对应的ul下的li显示
		$('.banner-img').stop().animate({left:-num*width}, 500);
	}
	function nextPage(){
		num++;
		//如果播放到第1张的时候，再点击，就跳转到最后一张
		if(num > 2){
			num = 0;
		}
		$('.banner-dot li').eq(num).addClass('current').siblings().removeClass('current');
		//通过编号查找对应的ul下的li显示
		$('.banner-img').stop().animate({left:-num*width}, 500);
	}
	$('.banner .left').click(prevPage);
	$('.banner .right').click(nextPage)
});