<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=0">

    <title>${hospital.name}</title>
    <script type="text/javascript" src="/js/wx/jquery.js"></script>
    <style type="text/css">
        <%-- 4 和 4s --%>
        @media only screen and (device-height :480px) and (-webkit-device-pixel-ratio:2){
            html{
                font-size: 62.5%;
            }
        }
        @media (device-height:568px) and (-webkit-min-device-pixel-ratio:2){
            /* 兼容iphone5 */
            html{
                font-size: 80%;
            }
        }
        *{
            margin:0;
            padding:0;
            border:none;
        }
        body{
            background:#ccc;
        }
        .top img{
            width:100%;
        }
        .hos-info{
            margin-top: -0.1rem;
            height:auto;
            background:white;
        }
        .hos-name{
            padding:1rem;
            font-size: 1.2rem;
            border-bottom: 1px solid #ccc;
        }
        .hos-desc{
            padding:1rem;
            line-height: 1.4;
            color:#999;
            text-align: left;
        }

        .imgs{
            margin-top: 1rem;
            background:white;
            text-align: center;
            padding-bottom: 0.6rem;
        }
        .imgs img{
            width:95%;
            padding:0.6rem;
            padding-bottom: 0;
        }

        .pros-title{
            padding:0.6rem;
            font-size: 1.2rem;
            border-bottom: 1px solid #ccc;
        }
        .pros{
            background:white;
            position: relative;
            margin-top: 1rem;
            overflow:hidden;
        }
        .pro{
            border-bottom: 1px solid #ccc;
        }

        .pro-left{
            width:30%;
            height:100%;
            padding:3%;
            float:left;
            overflow:hidden;
            position: relative;
        }
        .pro-left img{
            position: relative;
            left:50%;
            margin-left: -310px;
        }
        .pro-right{
            width:58%;
            float:left;
            padding:3%;
        }
        .pro-name{
            font-size: 1.05rem;
            padding:3% 0;
            color:#333;
        }
        .pro-price{
            color:rgb(218,54,126);
            font-size: 1.3rem;
        }
        .market-price{
            text-decoration: line-through;
            color:#999;
        }
        .buy{
            color:#999;
        }
        .pro-price{
            margin-top: 2rem;
        }
        .pro-right-bottom{
            margin-top: 1rem;
            overflow: hidden;
        }
        .market-price{
            float:left;
        }
        .buy{
            float:right;
        }




    </style>
</head>
<body>

<div class="top">
    <img src="${hospital.img_hos}">
</div>
<div class="hos-info">
    <div class="hos-name">
        ${hospital.name}
    </div>
    <p class="hos-desc">
        ${hospital.desc}
    </p>
</div>

<div class="imgs">
    <c:forEach items="${hospital.img_details}" var="img">
        <img src="${img}">
    </c:forEach>
</div>

<div class="pros">
    <div class="pros-title">
        更多产品
    </div>
    <c:forEach items="${hospital.pros}" var="pro">
        <div class="pro">
            <div class="pro-left">
                <div class="item-1" style="background-image:url(${pro.img});
                        background-size: auto 100%;width: 100%;min-height:8rem;height:auto;"></div>
            </div>
            <div class="pro-right">
                <p class="pro-name">${pro.name}</p>
                <p class="pro-price">
                    ￥${pro.price}
                </p>
                <p class="pro-right-bottom">
                    <span class="market-price">原价￥${pro.marketPrice}</span>
                    <span class="buy">${pro.saleNum}人购买</span>
                </p>
            </div>
        </div>
        <div style="clear:both;"></div>
    </c:forEach>

</div>
</body>
<script type="text/javascript">
    $(".pro").click(function(){
        alert("该版本不支持此查看产品，请更新版本。")
    })
</script>

</html>




















