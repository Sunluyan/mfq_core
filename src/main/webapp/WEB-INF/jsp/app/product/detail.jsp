<%@ page import="com.mfq.bean.app.ProductDetail2App" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=0">
    <link rel="stylesheet" type="text/css" href="/css/app/index.css"/>
    <script src="/js/wx/jquery.js"></script>
    <script type="text/javascript" src="/js/app/product/lunbo.js"></script>
    <script type="text/javascript" src="/js/app/product/index.js"></script>
        <%
    ProductDetail2App app = (ProductDetail2App) request.getAttribute("app");
    %>
    <title><%=app.getName()%> </title>
</head>
<body>

<div class="banner">
    <ul class="banner-img">
        <%
         for(int i = 0;i<app.getPro_imgs().size();i++){
             %>
        <li>
            <a href="#">
                <img src="<%=app.getPro_imgs().get(i)%>"/>
            </a>
            <div class="gray">
                <%=app.getName() %>
            </div>
        </li>
        <%
         }
        %>
    </ul>
    <!--<ul class="banner-dot">
        <li class="current">
            <a href="#">1</a>
        </li>
        <li>
            <a href="#">2</a>
        </li>
        <li>
            <a href="#">3</a>
        </li>
    </ul>-->
    <span class="left arrow"><</span>
    <span class="right arrow">></span>
</div>
<div class="yiyuan">
    <img src="<%=app.getHos_img()%>" class="yiyuan-img"/>
    <!--<div class="yiyuan-img">
    </div>-->
    <h3><%=app.getHos_name()%></h3>
    <p><%=app.getHos_desc()%></p>
    <span></span>
</div>
<div class="fenqi">
    <span class="houjia"><span class="little">￥</span><%=app.getPrice()%>.<span class='little'>00</span></span>
    <span class="yuanjia little"> <span class="little-2">￥</span><%=app.getMarket_price()%>.<span class="little-2">00x12期</span></span>
    <span class="xiangqing">分期详情</span>
    <span class="zhedie"></span>
    <div class="xiangqing-box">
        <%
             for (Integer key : app.getFqs().keySet()) {
                 %>
        <span><%=app.getFqs().get(key)%>.00×<%=key%>期</span>
        <%

        %>

        <span>100.00×12期</span>
    </div>
</div>
<div class="zhengce">
    优惠政策优惠政策优惠政策优惠政策优惠政策优惠政策优惠政策优惠政策优惠政xxxxxxxxxxxxxxxxxxxxxxx
</div>
<ul class="shoushu">
    <li class="shuqian current">
        美丽日记
    </li>
    <li class="shuhou">
        手术纪实
    </li>
    <div class="shuqian-box">
        <div class="qianhou">
					<span>
						<img />
					</span>
					<span>
						<img  />
					</span>
        </div>
        <div class="jiangjie">
            <img src="img/jiangjie.png" class="jiangjie-img"/>
            <!--<div class="jiangjie-img">
            </div>-->
        </div>
    </div>
    <img src="img/jiangjie.png" class="shuhou-box"/>
    <!--<div class="shuhou-box">
    </div>-->
</ul>
</body>

</html>
