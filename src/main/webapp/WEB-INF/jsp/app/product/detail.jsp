<%@page import="java.math.BigDecimal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String title = (String) request.getAttribute("title");
String name = (String) request.getAttribute("name");
String viewNum = (String) request.getAttribute("viewNum");
String endTime = (String) request.getAttribute("endTime");
String price = (String) request.getAttribute("price");
String market_price = (String) request.getAttribute("market_price");
String purl = (String) request.getAttribute("purl");

String consume_step = (String) request.getAttribute("consume_step");
String reserve = (String) request.getAttribute("reserve");
String special_note = (String) request.getAttribute("special_note");
String body = (String) request.getAttribute("body");
String p_price = (String) request.getAttribute("p_price");
String p_num = (String) request.getAttribute("p_num");

String hospital_name = (String) request.getAttribute("hospital_name");
String hospital_addr = (String) request.getAttribute("hospital_addr");
String hospital_img = (String) request.getAttribute("hospital_img");

%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title><%=title %></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/app/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/app/product.css">
</head>

<body>
<div class="row">
    <img src="<%=purl %>" class="img-responsive center-block" alt="Responsive image">
    <div class="col-xs-12"><div>
</div>
<div class="container">
<div class="row">
    <div class="col-xs-6">
  		<h5><strong class="product_name"><%=name %></strong></h5>
    </div>
    <div class="col-xs-6">
  		<h5 class="h_view"><i class="view"></i><%=viewNum %></h5>
    </div>
</div>
<div class="row">
    <div class="col-xs-6">
    	<span class="tle_pay">
    	<c:choose>
    	<c:when test="${type == 1 }">
    	<img alt="" width="38pt" src="${pageContext.request.contextPath}/images/p/sale.png">
    	</c:when>
    	<c:otherwise>
    	团购:
    	</c:otherwise>    	
    	</c:choose>
    	</span>
  		<span class="pre">￥<%=price %><i></i></span>
    </div>
    <div class="col-xs-6">
    	<c:choose>
    	<c:when test="${type == 1 }">
    	<span class="tle_pay">原价:</span>
  		<span class="pre">￥<%=market_price %></span><i></i></span>
    	</c:when>
    	<c:otherwise>
    	<span class="tle_pay">分期:</span>
  		<span class="pre">￥<%=p_price %><span id="fq">x<%=p_num %>期</span><i></i></span>
    	</c:otherwise>    	
    	</c:choose>
  		
    </div>
</div>
<div class="row inline"></div>
<div class="row detail">
    <h4 class="col-xs-12">购买需知</h4>
    <div class="col-xs-12 buy"><strong>有效期：</strong><span>优惠价格截止到<%=endTime %></span></div>
    <div class="col-xs-12 buy"><strong>消费流程：</strong><span><%=consume_step %></span></div>
    <div class="col-xs-12 buy"><strong>如何预约：</strong><span><%=reserve %></span></div>
    <div class="col-xs-12 buy"><strong>特殊说明：</strong><span><%=special_note %></span></div>
	<h4 class="col-xs-12">医院简介</h4>
    <div class="col-xs-12">
    <img src="<%=hospital_img %>" class="img-responsive center-block"/>
    </div>
    <span class="col-xs-12"><strong><%=hospital_name %></strong></span>
    <span class="col-xs-12 s_txt">
    	地址：<%=hospital_addr %>
    </span>
    <div class="col-xs-12 delt">
    <%=body %>
    </div>
    
</div>


</div>
</body>
</html>
