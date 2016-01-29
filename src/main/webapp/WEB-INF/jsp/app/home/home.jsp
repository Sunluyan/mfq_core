<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>美分期APP-首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>

    <script src="http://7xlb4j.com2.z0.glb.qiniucdn.com/jqury-1.7.2%2Fjquery-1.7.2.min.js"></script>
    <script src="http://7xlb4j.com2.z0.glb.qiniucdn.com/jquery.mobile-1.4.2.min.css"></script>
    <script src="http://7xlb4j.com2.z0.glb.qiniucdn.com/jquery.mobile-1.4.2.min.js"></script>
    <link rel="stylesheet" href="/css/app/n/mfq-index.css?_=1">
    <script src="/js/app/mfq-index.js"></script>
</head>
<body>
<div class="header">
    <div class="header-inner" >
        <ul id="id" class="fuck">
            <c:forEach items="${classifys}" var="cla">
                <li style="background-image: url(${cla.hgImage})" onclick="window.location.href='classify:${cla.id}-${cla.name}';">
                    <a href="#">
                        <div class="gray">${cla.name}</div>
                    </a>
                </li>

            </c:forEach>


            <%--<li><a href="#"><div class="gray">鼻子</div></a></li>--%>
            <%--<li><a href="#"><div class="gray">唇部</div></a></li>--%>
            <%--<li><a href="#"><div class="gray">牙齿</div></a></li>--%>
            <%--<li><a href="#"><div class="gray">瘦身</div></a></li>--%>
            <%--<li><a href="#"><div class="gray">面部</div></a></li>--%>
            <%--<li><a href="#"><div class="gray">针剂</div></a></li>--%>
            <%--<li><a href="#"><div class="gray">脱毛</div></a></li>--%>
            <%--<li><a href="#"><div class="gray">胸部</div></a></li>--%>
            <%--<li><a href="#"><div class="gray">皮肤</div></a></li>--%>
        </ul>

    </div>

</div>
<%--<div class="hot">--%>
    <%--<div class="hot-1 hot-0">--%>
        <%--<a href="#"></a>--%>
    <%--</div>--%>
    <%--<div class="hot-2 hot-0">--%>
        <%--<a href="#"></a>--%>
    <%--</div>--%>
    <%--<div class="hot-1 hot-0 hot-3">--%>
        <%--<a href="#"></a>--%>
    <%--</div>--%>
    <%--<div class="hot-2 hot-0 hot-4">--%>
        <%--<a href="#"></a>--%>
    <%--</div>--%>
<%--</div>--%>

<%--<!-- 小banner开始 -->--%>
<%--<div class="little-banner">--%>

<%--</div>--%>

<!-- 医院开始 -->
<div class="h-biaoqian">
    合作医院
</div>
<div class="header header_2">
    <div class="header-inner" >
        <ul id="id_2" class="">
            <c:forEach items="${hospitals}" var="hospital">
            <li style="background-image: url(${hospital.img})" onclick="window.location.href='hospital:${hospital.id}-${hospital.name}';">
                <a href="hospital:${hospital.id}-${hospital.name}">
                    <div class="gray">北京美莱</div>
                </a>
            </li>
            </c:forEach>
            <%--<li><a href="#"><div class="gray">鼎点口腔</div></a></li>--%>
            <%--<li><a href="#"><div class="gray">三亚圣地亚</div></a></li>--%>
            <%--<li><a href="#"><div class="gray">恩菲齿科</div></a></li>--%>
            <%--<li><a href="#"><div class="gray">重庆华美</div></a></li>--%>
            <%--<li><a href="#"><div class="gray">北京和谐美丽汇</div></a></li>--%>
            <%--<li><a href="#"><div class="gray">上海艺兴</div></a></li>--%>
            <%--<li><a href="#"><div class="gray">北京航空总医院</div></a></li>--%>
            <%--<li><a href="#"><div class="gray">重庆真伊</div></a></li>--%>

        </ul>

    </div>

</div>

<!-- 医院结束 -->
<div class="i-biaoqian">
    精品推荐
</div>
<div class="item">
    <c:forEach items="${products}" var="product">
    <div class="item-inner" onclick="window.location.href='product:${product.id}-${product.name}';">
        <img class="item-1" src="${product.img}" width="614" alt="">
        <div class="update"></div>
        <div class="item-foot">
            <div class="item-foot-l">
                <p class="top">${product.hosName}<span class="position"></span>${product.city}</p>
                <div class="bottom">
                    <div class="bottom-l">
                        <span></span>
                        <span>可分期</span>
                    </div>
                    <div class="bottom-r">
                        <span></span>
                        <span>可团购</span>
                    </div>
                </div>
            </div>
            <div class="item-foot-r">
                <div class="foot-r-top">
                    ${product.name}
                </div>
                <div class="foot-r-bottom">
                    <p style="color:#fd5a9c;font-size:2rem"><span style="font-size:1rem">￥</span><fmt:formatNumber  value="${product.price }" pattern="###,###"/>
                        <%--.<span class="little" style="font-size:0.5rem;margin-right:0.4rem">00</span>--%>
                        <span class="gray" style="color:#999;font-size:1rem;text-decoration:line-through">原价<fmt:formatNumber  value="${product.marketPrice }" pattern="###,###"/>
                            <%--.<span class="little" style="font-size:0.5rem">00</span>--%>
                        </span></p>
                </div>
            </div>
        </div>

    </div>

    </c:forEach>

</div>

<script>
    $.mobile.hidePageLoadingMsg();
</script>
</body>
</html>