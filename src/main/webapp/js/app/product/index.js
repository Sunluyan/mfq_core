$(function(){
	var zhankai=false;
	$('.xiangqing,.zhedie').click(function(){
		if(zhankai==false){
			$('.zhedie').css({'background-image':'url(img/zhedie-2_03_03.png)'})
			$('.xiangqing-box').css({'display':'block'})
			zhankai=true
		}else{
			$('.zhedie').css({'background-image':'url(img/zhedie_03.png)'})
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
