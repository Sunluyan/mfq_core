<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=0">
    <meta name="format-detection" content="telephone=no">
    <title>医院详情页</title>
    <style>
        *{
            padding:0;
            margin:0;
            list-style:none;
            box-sizing: border-box;
            -moz-box-sizing: border-box;
            font-size:16px;
        }
        .header{
            width:100%;
            height:10rem;
            background:url(images/header-bg.png) no-repeat;
            background-size:cover;
        }
        .title{
            font-size:1rem;
            color:#222;
            padding-left:1rem;
            height:3rem;
            line-height:3rem;
            border-bottom:1px solid #f1f1f1;
            font-weight: normal;
        }
        .decript{
            font-size:0.6rem;
            color:#999;
            padding:0.5rem 0 1rem 1rem;
            text-spacing: 25;
            border-bottom:1rem solid #eee;
        }
        .img{
            padding:1rem 1rem 0 1rem;
        }
        .img-1{
            width:100%;
            height:10rem;
            margin-bottom:0.3rem;
        }
        .more{
            font-size:0.8rem;
            color:#333;
            padding-left:1rem;
            height:2.5rem;
            line-height:2.5rem;
            border-bottom:1px solid #f1f1f1;

        }
        .more-item{
            border-bottom:1px solid #f1f1f1;
            height:6rem;
            padding:0.5rem 0 0.5rem 1rem;
            border-bottom:1px solid #f1f1f1;
            position:relative;
        }
        .left{
            display:inline-block;
            width:5rem;
            height:5rem;
            background:url(images/more-item-1_03.png) no-repeat;
            background-size:cover;
            float:left;
        }
        .right{
            float:left;
            padding:0 0.5rem 0 0.5rem;
            height:5rem;
            width:auto;
        }
        .red{
            color:#f43488;
        }
        .gray{
            color:#ccc;
            font-size:0.5rem;
        }
        .gray-right{
            position:absolute;
            right:1rem;
            bottom:1rem;
        }
        .del{
            text-decoration:line-through;
        }
        h4{
            color:#333;
            height:2rem;
            line-height:2rem;
        }
    </style>
</head>
<body>
<div class="header">
</div>
<h2 class="title">
    北京美莱医疗美容医院
</h2>
<p class="decript">
    医院介绍：北京美莱医疗美容医院—隶属于美莱医学美容集团。医院下设整形、皮肤、无创、抗衰老、中医、口腔六大中心，涵盖胸部整形、鼻部整形、眼部整形、祛斑美白、祛痘嫩肤、紧肤除皱、无创塑形、功能医学抗衰老、中医美容、口腔美容等经典项目。
</p>
<p class="img">
    <img class="img-1" src="images/img-1.png" height="300" width="600" alt="">
    <img class="img-1" src="images/img-2.png" height="300" width="600" alt="">
    <img class="img-1" src="images/img-3.png" height="300" width="600" alt="">
</p>

<p class="more">
    更多产品
</p>
<div class="more-item">
    <span class="left"></span>
    <div class="right">
        <H4>韩式半永久眉</H4>
        <p class="red"><span class="little">￥</span>3200</p>
        <span class="gray del" >原价￥4500</span>
    </div>
    <span class="gray gray-right" >10人购买</span>
</div>
<div class="more-item">
    <span class="left"></span>
    <div class="right">
        <H4>韩式半永久眉</H4>
        <p class="red"><span class="little">￥</span>3200</p>
        <span class="gray del" >原价￥4500</span>
    </div>
    <span class="gray gray-right" >10人购买</span>
</div>
</body>
</html>