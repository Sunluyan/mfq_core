$(function() {
	
	var arr =["一分","一元","十元","一百元","一角","五元","二十元","十万元",
	"五百万元","一千万元"]

	$('.begin').click(function(event) {
		var name = $('.name')
		if (name.val() == '') {
            alert('请输入姓名');
            return false;
		}else{

			var code = name.val();
			var length = code.length;
			//alert(length);
			var sum = 0;
			for(var i = 0;i<length;i++){
				sum+=code.charCodeAt(i);
			}
			//alert(sum);
			$('.two').css('display', 'none');
			$('.one').css('display', 'block');
			if(sum>=0&&sum<=10000){
				$('.ten').html(arr[0]);
				$('.can').html(code+'的颜值能值');
				$('.money').css('background-image', 'url(http://7xlcaq.com2.z0.glb.qiniucdn.com/yifen.png)');
			}
			if(sum>=10000&&sum<=20000){
				$('.ten').html(arr[1]);
				$('.can').html(code+'的颜值能值');
				$('.money').css('background-image', 'url(http://7xlcaq.com2.z0.glb.qiniucdn.com/yiyuan.png)');
			}
			if(sum>=20000&&sum<=30000){
				$('.ten').html(arr[2]);
				$('.can').html(code+'的颜值能值');
				$('.money').css('background-image', 'url(http://7xlcaq.com2.z0.glb.qiniucdn.com/shiyuan.png)');
			}
			if(sum>=30000&&sum<=40000){
				$('.ten').html(arr[3]);
				$('.can').html(code+'的颜值能值');
				$('.money').css('background-image', 'url(http://7xlcaq.com2.z0.glb.qiniucdn.com/yibaiyuan.png)');
			}
			if(sum>=40000&&sum<=50000){
				$('.ten').html(arr[4]);
				$('.can').html(code+'的颜值能值');
				$('.money').css('background-image', 'url(http://7xlcaq.com2.z0.glb.qiniucdn.com/yijiao.png)');
			}
			if(sum>=50000&&sum<=60000){
				$('.ten').html(arr[5]);
				$('.can').html(code+'的颜值能值');
				$('.money').css('background-image', 'url(http://7xlcaq.com2.z0.glb.qiniucdn.com/wuyuan.png)');
			}
			if(sum>=60000&&sum<=70000){
				$('.ten').html(arr[6]);
				$('.can').html(code+'的颜值能值');
				$('.money').css('background-image', 'url(http://7xlcaq.com2.z0.glb.qiniucdn.com/ershiyuan.png)');
			}
			if(sum>=70000&&sum<=80000){
				$('.ten').html(arr[7]);
				$('.can').html(code+'的颜值能值');
				$('.money').css('background-image', 'url(http://7xlcaq.com2.z0.glb.qiniucdn.com/shiwanyuan.png)');
			}
			if(sum>=80000&&sum<=90000){
				$('.ten').html(arr[8]);
				$('.can').html(code+'的颜值能值');
				$('.money').css('background-image', 'url(http://7xlcaq.com2.z0.glb.qiniucdn.com/wubaiwanyuan.png)');
			}
			if(sum>=90000&&sum<=200000){
				$('.ten').html(arr[9]);
				$('.can').html(code+'的颜值能值');
				$('.money').css('background-image', 'url(http://7xlcaq.com2.z0.glb.qiniucdn.com/yiyiyuan.png)');
			}

				$('.one .want').click(function(event) {
					//alert('hh');
					$('.two').css('display', 'block');
					$('.one').css('display', 'none');
				});

		};
		
	});
	

	/*微信分享接口*/
		var url = location.href.split('#')[0];

        $.ajax({
            url:"http://i.5imfq.com/wechat/js/token/",
            data:{
                url:url
            },
            dataType:"json",
            success:function(json){
                if(json.code != 0){
                    return;
                }


                wx.config({
                    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                    appId: json.data.appId, // 必填，公众号的唯一标识
                    timestamp: json.data.timestamp, // 必填，生成签名的时间戳
                    nonceStr: json.data.nonceStr, // 必填，生成签名的随机串
                    signature: json.data.signature,// 必填，签名，见附录1
                    jsApiList: ['onMenuShareTimeline','wx.onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
                });

                wx.ready(function(){

                    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。

                    alert("ready")
                    wx.onMenuShareTimeline({
                        title: '测测你的名字值多少钱吧-你的世界因此更美丽！', // 分享标题
                        link: 'http://m.5imfq.com/activity/Christmas/one', // 分享链接
                        imgUrl: 'http://ww2.sinaimg.cn/mw1024/e300167cjw1ezt8olgshwj20hs0qo786.jpg', // 分享图标
                        success: function () {
                            // 用户确认分享后执行的回调函数
                            location.href = "/activity/Christmas/three";
                        },
                        cancel: function () {
                            // 用户取消分享后执行的回调函数
                            alert("取消就不能测试了哦")
                        }
                    });
                    wx.onMenuShareAppMessage({
                        title: '测测你的名字值多少钱吧-你的世界因此更美丽！', // 分享标题
                        desc: '', // 分享描述
                        link: 'http://m.5imfq.com/activity/Christmas/one', // 分享链接
                        imgUrl: 'http://ww2.sinaimg.cn/mw1024/e300167cjw1ezt8olgshwj20hs0qo786.jpg', // 分享图标
                        type: 'link', // 分享类型,music、video或link，不填默认为link
                        dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
                        success: function () {
                            // 用户确认分享后执行的回调函数
                            location.href = "/activity/Christmas/three";
                        },
                        cancel: function () {
                            // 用户取消分享后执行的回调函数
                        }
                    });
                });
                wx.error(function(res){

                    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
                    alert("error")
                });



            }
        })


	/*微信分享接口*/

	


});