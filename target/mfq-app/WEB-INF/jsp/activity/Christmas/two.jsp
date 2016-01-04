<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <link rel="stylesheet" type="text/css" href="/css/activity/Christmas/index.css" >
    <title>丑女小美变美记</title>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script src="/js/activity/jquery-1.8.2.min.js"></script>

</head>
<body>
    <!-- 刮奖区 -->
<div class="demoBox">
    <div class="demo">
        <canvas><img id="lamp" src="/images/activity/Christmas/newnewchou.png" height="546" width="537"></canvas>
    </div>
</div>
<script type="text/javascript">
        var bodyStyle = document.body.style;
        bodyStyle.mozUserSelect = 'none';
        bodyStyle.webkitUserSelect = 'none';

        var img = new Image();
        var canvas = document.querySelector('canvas');
        canvas.style.backgroundColor='transparent';
        canvas.style.backgroundSize='98%';
        canvas.style.backgroundRepeat='no-repeat';
        canvas.style.margin='0';
// canvas.style.position = 'absolute';
        var imgs = '/images/activity/Christmas/mei.png';  //刮开后显示的底图

        img.src = imgs;
        img.addEventListener('load', function(e){
            var ctx;
            var w = 254,  //宽度
                    h = 248;  //高度
            var offsetX = canvas.offsetLeft,
                    offsetY = canvas.offsetTop;
            var mousedown = false;
            
            function layer(ctx) {
                var img = document.getElementById('lamp')
                var pat=ctx.createPattern(img,"no-repeat");
                // ctx.fillStyle = 'gray';   //刮卡之前颜色
                ctx.fillStyle = pat;  //刮卡之前img
                // ctx.fillRect(0, 0, w, h);  //坐标
                ctx.drawImage(img, 0, 0,254,254);
            }

            function eventDown(e){
                e.preventDefault();
                mousedown=true;
            }

            function eventUp(e){
                e.preventDefault();
                mousedown=false;
            }

            function eventMove(e){
                e.preventDefault();
                if(mousedown) {
                    if(e.changedTouches){
                        e=e.changedTouches[e.changedTouches.length-1];
                    }
                    var x = (e.clientX + document.body.scrollLeft || e.pageX) - offsetX || 0,
                            y = (e.clientY + document.body.scrollTop || e.pageY) - offsetY || 0;
                    with(ctx) {
                        beginPath()
                        arc(x, y, 30, 0, Math.PI * 2);  //刮卡画笔大小
                        fill();
                    }
                }
            }

            canvas.width=w;
            canvas.height=h;
            canvas.style.backgroundImage='url('+img.src+')';
            ctx=canvas.getContext('2d');
            ctx.fillStyle='transparent';
            ctx.fillRect(0, 0, w, h);
            layer(ctx);

            ctx.globalCompositeOperation = 'destination-out';

            canvas.addEventListener('touchstart', eventDown);
            canvas.addEventListener('touchend', eventUp);
            canvas.addEventListener('touchmove', eventMove);
            canvas.addEventListener('mousedown', eventDown);
            canvas.addEventListener('mouseup', eventUp);
            canvas.addEventListener('mousemove', eventMove);
        });

        var url = location.href.split('#')[0];

        $.ajax({
            url:"/wechat/js/token/",
            data:{
                url:url
            },
            dataType:"json",
            success:function(json){
                if(json.code != 0){
                    return;
                }


                wx.config({
                    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                    appId: json.data.appId, // 必填，公众号的唯一标识
                    timestamp: json.data.timestamp, // 必填，生成签名的时间戳
                    nonceStr: json.data.nonceStr, // 必填，生成签名的随机串
                    signature: json.data.signature,// 必填，签名，见附录1
                    jsApiList: ['onMenuShareTimeline'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
                });

                wx.ready(function(){

                    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。

                    //alert("ready")
                    wx.onMenuShareTimeline({
                        title: '圣诞元旦-拯救丑女大作战! 500红包等你拿!', // 分享标题
                        link: 'http://m.5imfq.com/activity/Christmas/one', // 分享链接
                        imgUrl: 'http://7xlcaq.com2.z0.glb.qiniucdn.com/Chrismas.jpg', // 分享图标
                        success: function () {
                            // 用户确认分享后执行的回调函数
                            location.href = "/activity/Christmas/three";
                        },
                        cancel: function () {
                            // 用户取消分享后执行的回调函数
                            alert("取消就没有红包了哦")
                        }
                    });
                    wx.onMenuShareAppMessage({
                        title: '圣诞元旦-拯救丑女大作战! 500红包等你拿!', // 分享标题
                        desc: '', // 分享描述
                        link: 'http://m.5imfq.com/activity/Christmas/one', // 分享链接
                        imgUrl: 'http://7xlcaq.com2.z0.glb.qiniucdn.com/Chrismas.jpg', // 分享图标
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




</script>
</body>
</html>