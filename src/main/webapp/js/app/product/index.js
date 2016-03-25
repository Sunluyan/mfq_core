$(function(){
	var zhankai=false;
	$('.xiangqing,.zhedie').click(function(){
		if(zhankai==false){
			$('.zhedie').css({'background-image':'url(http://7xlb4k.com2.z0.glb.qiniucdn.com/2016-03-23%2Fzhedie-2_03_03.png)'})
			$('.xiangqing-box').css({'display':'block'})
			zhankai=true
		}else{
			$('.zhedie').css({'background-image':'url(http://7xlb4k.com2.z0.glb.qiniucdn.com/2016-03-23%2Fzhedie_03.png)'})
			$('.xiangqing-box').css({'display':'none'})
			zhankai=false
		}

	})
	$('.shuhou').click(function(){
		$('.shuhou-box').css({'display':'block'})
		$('.shuqian-box').css({'display':'none'})
		$('.shuqian').removeClass('current')
		$('.shuhou').addClass('current')
		
	})
	$('.shuqian').click(function(){
		$('.shuhou-box').css({'display':'none'})
		$('.shuqian-box').css({'display':'block'})
		$('.shuhou').removeClass('current')
		$('.shuqian').addClass('current')
	})
	
	
	
})
