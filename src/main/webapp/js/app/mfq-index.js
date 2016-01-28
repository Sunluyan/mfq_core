window.onload=function(){

	/*分类开始*/
	var obj = document.getElementById('id');


	var move = 0;
	var oldLeft = 0;
	var maxLeft = $(obj).width()-$(document).width()-10;

	obj.addEventListener('touchstart', function(event) {
		var touch = event.targetTouches[0];
		move = touch.pageX;
		oldLeft = parseInt($(this).css("left"));
	}, false);

	obj.addEventListener('touchend', function(event) {

	}, false);

	obj.addEventListener('touchmove', function(event) {
		//alert('hh');
	     // 如果这个元素的位置内只有一个手指的话
		var touch = event.targetTouches[0];
	    if (event.targetTouches.length == 1) {
	　　　　 event.preventDefault();// 阻止浏览器默认事件，重要 
	        // 把元素放在手指所在的位置
	        // 
	        var moveNow = touch.pageX - move;
	        if(oldLeft+moveNow+maxLeft<0){
	        	return;
	        }

	        else if(oldLeft+moveNow>0){
	        	return;
	        }
			console.log(oldLeft+moveNow)
			if(oldLeft + moveNow <-582){
				return;
			}

	        obj.style.left = oldLeft + moveNow +"px";

	        /*obj.style.top = touch.pageY-50 + 'px';*/
	        }
	}, false);

	
	/*医院开始*/

	var obj_2 = document.getElementById('id_2');


	var move_2 = 0;
	var oldLeft_2 = 0;
	var maxLeft_2 = $(obj_2).width()-$(document).width()-200;

	obj_2.addEventListener('touchstart', function(event) {
		var touch_2 = event.targetTouches[0];
		move_2 = touch_2.pageX;
		oldLeft_2 = parseInt($(this).css("left"));
	}, false);

	obj_2.addEventListener('touchend', function(event) {

	}, false);

	obj_2.addEventListener('touchmove', function(event) {
		//alert('hh');
	     // 如果这个元素的位置内只有一个手指的话
		var touch_2 = event.targetTouches[0];
	    if (event.targetTouches.length == 1) {
	　　　　 event.preventDefault();// 阻止浏览器默认事件，重要 
	        // 把元素放在手指所在的位置
	        // 
	        var moveNow_2 = touch_2.pageX - move_2;
	        if(oldLeft_2+moveNow_2+maxLeft_2<0){
	        	return;
	        }

	        else if(oldLeft_2+moveNow_2>0){
	        	return;
	        }
			console.log(oldLeft_2+moveNow_2)
			if(oldLeft_2+moveNow_2<-586){
				return;
			}

	        obj_2.style.left = oldLeft_2 + moveNow_2 +"px";

	        /*obj.style.top = touch.pageY-50 + 'px';*/
	        }
	}, false);

	
}
	
