<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<title>双十一</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/activity/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/activity/items.css">
<script src="${pageContext.request.contextPath}/js/activity/jquery-1.8.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/activity/items.js"></script>

</head>

<body>
<div class="container">
<div class="row timer_p">
<div class="col-xs-12 times">
<div class="time_d">
<span class="time">限时倒计时</span>
</div>
</div>
<div class="col-lg-12 timer_t">
<span id="countdown"></span>
</div>
<div class="col-xs-12">
<img src="http://7xlb4j.com2.z0.glb.qiniucdn.com/s-ts.png" class="img-responsive center-block"/>
</div>

</div>
<c:forEach items="${products }" var="product">
<div class="row item_p">
	<img src="${product.img }" class="img-responsive center-block" alt="Responsive image">
	<div class="row detail_p">
	<div class="col-xs-10 title_th"><h4>特惠价：${product.price } <small>${product.marketPrice }<small></h4></div>
	<div class="col-xs-2 title_r">
		<a href="${pageContext.request.contextPath}/activity/product/special?pid=${product.id}"><div class="th"></div></a>
	</div>
	</div>
</div>
</c:forEach>
</div>
</body>
<script>
fnTimeCountDown('${time}', $("#countdown"));
</script>
</html>
