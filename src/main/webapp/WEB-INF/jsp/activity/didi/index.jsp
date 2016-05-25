<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
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
        <!--第一部分-->
        <div class="top">
            <img src="/images/activity/didi/up.png"/>
        </div>
        <!--第二部分-->
        <div class="middle">
            <!--选项按钮部分-->
            <div class="cover" id="cover1"  >
                <!--点击选中切换背景图-->
            </div>
            <div class="cover" id="cover2" >
                <!--点击选中切换背景图-->
            </div>
            <div class="cover" id="cover3">
                <!--点击选中切换背景图-->
            </div>

            <img src="/images/activity/didi/down.png" class="down"/>


        <!--获取验证码-->
    <div class="information">
        <form action="" method="post">

            <input type="text" id="phoneNumber" name="phoneNumber" placeholder="手机号"/><label class="get-vcode" id="btnSendCode" status="0">获取验证码</label>
            <input type="text" name="code" id="code" placeholder="验证码"/>

        </form>
    </div>
    <div class="btn">
        <button class="get" status="0">立即领取</button>
    </div>
  </div>
</div>
    <!--第三部分-->


    <div class="mui-backdrop" id="tips">
    <div class="alert">
                <a href="http://www.5imfq.com/download/app.html"><img src="/images/activity/didi/alert.png"/></a>
    </div>
    </div>
    <nav class="mui-bar mui-bar-tab" >
        <button onclick="window.location='http://www.5imfq.com/download/app.html'">下载美分期APP</button>

    </nav>

    <script src="/js/activity/didi/index.js" type="text/javascript" charset="utf-8">

    </script>



</body>

<script>
    var cover1=document.getElementById("cover1");
    var cover2=document.getElementById("cover2");
    var cover3=document.getElementById("cover3");
    var i =300;
    var j =400;
    var k =500;


    cover1.onclick=function(){
        if (i==300 ){
            cover1.style.background="url(/images/activity/didi/选定状态按钮.png) no-repeat 4% 30%";
            cover1.style.backgroundSize="8% 16%";
            i=325;
        }else {
            cover1.style.background="url(/images/activity/didi/灰色按钮.png) no-repeat 4% 30%";
            cover1.style.backgroundSize="8% 16%";
            i=300;
        }

    }
    cover2.onclick=function(){
        if (j==400 ){
            cover2.style.background="url(/images/activity/didi/选定状态按钮.png) no-repeat 100% 70%";
            cover2.style.backgroundSize="8% 16%";
            j=326;
        }else {
            cover2.style.background="url(/images/activity/didi/灰色按钮.png) no-repeat 100% 70%";
            cover2.style.backgroundSize="8% 16%";
            j=400;
        }

    }
    cover3.onclick=function(){
        if ( k==500){
            cover3.style.background="url(/images/activity/didi/选定状态按钮.png) no-repeat 98% 24%";
            cover3.style.backgroundSize="8% 16%";
            k=327;
        }else {
            cover3.style.background="url(/images/activity/didi/灰色按钮.png) no-repeat 98% 24%";
            cover3.style.backgroundSize="8% 16%";
            k=500;
        }

    }


    //验证手机格式
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
    //验证码倒计时
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
        var p = i+","+j+","+k;
        $.ajax({
            url:"/activity/didi/mark",
            type:"post",
            dataType:"json",
            data:{
                mobile:phone,
                vcode:vcode,
                pids:p
            },
            success:function(data){
                if(data.code == 0 ){
                    if (i==325 || j==326 || k==327){

                    var tips = document.getElementById("tips");
                     var oBtn = document.getElementById("btn");

                     tips.style.display="block";}else {
                        alert("亲 还没有领取优惠券哦，请到上方点击领取！")
                    }


                   // alert("领取成功~来下载APP使用美丽优惠券吧")
                // location.href="http://www.5imfq.com/download/app.html";
                }
                else if (data.code == 1104 || data.code == 7788){
                    alert("您是老用户~来下载APP使用美丽优惠券吧")
                    location.href="http://www.5imfq.com/download/app.html";
                }
                else{
                    alert(data.msg);
                }
            }
        })
    })
</script>
</html>