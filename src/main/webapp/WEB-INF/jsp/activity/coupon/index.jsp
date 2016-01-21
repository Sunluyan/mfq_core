<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <title>注册页面</title>
    <link rel="stylesheet" href="/css/activity/coupon/index.css">
    <script src="/js/activity/jquery-1.8.2.min.js"></script>

</head>
<body>
<div class="body">
    <div class="body-top">
        <label class="phone" for="input-phone"></label>
        <input type="text" placeholder="请输入手机号" class="input-phone" id="input-phone">

        <label class="check-num" for="input-check"></label>
        <input type="text" placeholder="请输入验证码" class="input-check" id="input-check">
        <input type="button" class="have huoqu" id="btnSendCode" value="获取验证码" />

        <label class="phone password" for="input-password"></label>
        <input type="password" placeholder="6到25个字符组成" class="input-phone" id="input-password">
        <div class="yaoqing">
            <input type="text" placeholder="请输入邀请码(非必填)" class="input-check" >
            <br/>
            <div>填写可获得500元代金券</div>
        </div>


    </div>
    <div class="foot">
        <button class="zhuce queren">注册</button>
        <div></div>
        <input type="checkbox" name="sex" id="a" value="X" class="radio">
        <label for="a" class="radio">我已同意并阅读《美分期用户注册协议》</label>
    </div>

</div>
</body>
<script type="text/javascript">

    var wait=60;
    function time(o) {
        if (wait == 0) {
            o.removeAttribute("disabled");
            o.value="获取";
            document.getElementById("btnSendCode").disabled = false;
            wait = 60;
        } else {
            o.setAttribute("disabled", true);
            o.value="(" + wait + ")";
            document.getElementById("btnSendCode").disabled = true;
            wait--;
            setTimeout(function() {
                time(o)
            },1000)
        }
    }

    var validateVcode = "";
    $("#btnSendCode").click(function(){

        var phone = document.getElementById("input-phone").value;
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
                    $(".error").html(json.msg)
                }
            }
        })
        var btn = $(this).get(0);
    })

    $(".queren").click(function(){
        var phone = document.getElementById("input-phone").value;
        var vcode = document.getElementById("input-check").value;
        var password = document.getElementById("input-password").value;
        $.ajax({
            url:"/activity/newyear/register/",
            type:"post",
            dataType:"json",
            data:{
                phone:phone,
                vcode:vcode,
                passwd:password,
                activityname:"coupon"
            },
            success:function(data){
                if(data.code == 0){
                    alert("注册成功~来下载APP使用500元美丽优惠券吧")
                    location.href="http://www.5imfq.com/download/app.html";
                }
                else if (data.code == 1104 || data.code == 7788){
                    alert("您是老用户~来下载APP使用500元美丽优惠券吧")
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