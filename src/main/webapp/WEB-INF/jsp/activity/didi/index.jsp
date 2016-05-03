<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <title>美分期</title>
    <script src="/js/activity/didi/mui.min.js"></script>
    <script src="/js/activity/jquery-1.11.1.min.js"></script>
    <script src="/js/activity/didi/rem.js" type="text/javascript" charset="utf-8"></script>
    <link href="/css/activity/didi/mui.min.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="/css/activity/didi/index.css"/>
    <script type="text/javascript" charset="utf-8">
        mui.init();
    </script>
</head>
<body>
<div class="mui-content">
    <div class="top">
        <img src="/images/activity/didi/123.png"/>
    </div>
    <div class="middle">
        <ul>
            <li><a href="javascript:;"><img src="/images/activity/didi/juan1.png"/></a></li>
            <li><a href="javascript:;"><img src="/images/activity/didi/juan2.png"/></a></li>
            <li><a href="javascript:;"><img src="/images/activity/didi/juan3.png"/></a></li>
        </ul>
    </div>
    <div class="information">
        <input type="text" id="phoneNumber" name="phoneNumber" placeholder="手机号"/><label class="get-vcode" id="btnSendCode" status="0">获取验证码</label>
        <input type="text" name="code" id="code" placeholder="验证码"/>
    </div>
    <div class="btn">
        <button class="get" status="0">立即领取</button>
    </div>

</div>
<nav class="mui-bar mui-bar-tab">
    <button>下载美分期APP</button>

</nav>
</body>
<script>
    $("#code").on("input webChange",function(){
        var vcode = this.value;
        if(vcode.length == 4)
        {
            $(".get").css({
                "background":"#da5689",
                "color":"white"
            }).attr("status","1")

        }else{
            $(".get").css({
                "background":"#ddd",
                "color":"#646464"
            }).attr("status","0")
        }
    })

    $("#phoneNumber").on("input webChange",function(){
        var phone = this.value;
        if(/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i.test(phone))
        {
            $(".get-vcode").css({
                "background":"#da5689",
                "color":"white"
            }).attr("status","1")
        }else{
            $(".get-vcode").css({
                "background":"#ddd",
                "color":"#646464"
            }).attr("status","0")
        }
    })

    $(".get-vcode").click(function(){
        if($(this).attr("status") == "0"){
            return false;
        }
        var phone = document.getElementById("phoneNumber").value
        $.ajax({
            url:"/wechat/sendcode/",
            data:{
                mobile:phone,
                qewrasdfzxcv:"asdfasdf"
            },
            type:"post",
            dataType:"json",
            success:function(json){
                if(json.code == 0){
                    time(document.getElementById("btnSendCode"));
                }
                else{
                    alert(json.code+"\t"+json.msg)
                }
            }
        })
    })

    var wait=60;
    function time(o) {
        if (wait == 0) {
            $(o).html("获取验证码");
            wait = 60;
            $(".get-vcode").attr("status","0");
        } else {
            $(o).html("(" + wait + ")");
            wait--;
            setTimeout(function() {
                time(o)
            },1000)
        }
    }



    $(".btn").click(function(){
        var phone = document.getElementById("phoneNumber").value;
        var vcode = document.getElementById("code").value;

        $.ajax({
            url:"/activity/didi/mark",
            type:"post",
            dataType:"json",
            data:{
                mobile:phone,
                vcode:vcode,
                pids:'256,257,258'
            },
            success:function(data){
                if(data.code == 0){
                    alert("注册成功~来下载APP使用美丽优惠券吧")
//                    location.href="http://www.5imfq.com/download/app.html";
                }
                else if (data.code == 1104 || data.code == 7788){
                    alert("您是老用户~来下载APP使用美丽优惠券吧")
//                    location.href="http://www.5imfq.com/download/app.html";
                }
                else{
                    alert(data.msg);
                }
            }
        })
    })
</script>
</html>