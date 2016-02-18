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
<link rel="stylesheet" href="/css/app/mfq-index.css">
<script src="/js/app/jquery-1.7.2.min.js"></script>
<!-- <script src="jquery.mobile-1.4.2/jquery.mobile-1.4.2.min.css"></script>
<script src="jquery.mobile-1.4.2/jquery.mobile-1.4.2.min.js"></script> -->
<script src="/js/app/mfq-index.js"></script>
</head>
<body>
<div class="header">
    <!-- <div class="header-inner" >
        <ul id="id" class="fuck">
            <li >
                <a href="#">
                    <div class="gray">眉眼</div>
                </a>
            </li>
            <li><a href="#"><div class="gray">鼻子</div></a></li>
            <li><a href="#"><div class="gray">唇部</div></a></li>
            <li><a href="#"><div class="gray">牙齿</div></a></li>
            <li><a href="#"><div class="gray">瘦身</div></a></li>
            <li><a href="#"><div class="gray">面部</div></a></li>
            <li><a href="#"><div class="gray">针剂</div></a></li>
            <li><a href="#"><div class="gray">脱毛</div></a></li>
            <li><a href="#"><div class="gray">胸部</div></a></li>
            <li><a href="#"><div class="gray">皮肤</div></a></li>
        </ul>

    </div> -->
    <ul class="xiangmu">
        <li>
            <a href="classify:${classify_0.id }-${classify_0.name}">
                <img src="${classify_0.hgImage}" height="150" width="150" alt="">
                <p>${classify_0.name}</p>
            </a>
        </li>
        <li>
            <a href="classify:${classify_1.id}-${classify_1.name}">
                <img src="${classify_1.hgImage}" height="150" width="150" alt="">
                <p>${classify_1.name}</p>
            </a>
        </li>
        <li>
            <a href="classify:${classify_2.id}-${classify_2.name}">
                <img src="${classify_2.hgImage}" height="150" width="150" alt="">
                <p>${classify_2.name}</p>
            </a>
        </li>
        <li>
            <a href="classify:${classify_3.id}-${classify_3.name}">
                <img src="${classify_3.hgImage}" height="150" width="150" alt="">
                <p>${classify_3.name}</p>
            </a>
        </li>
        <li>
            <a href="classify:${classify_4.id}-${classify_4.name}">
                <img src="${classify_4.hgImage}" height="150" width="150" alt="">
                <p>${classify_4.name}</p>
            </a>
        </li>
    </ul>
    <ul class="xiangmu">
        <li>
            <a href="classify:${classify_5.id}-${classify_5.name}">
                <img src="${classify_5.hgImage}" height="150" width="150" alt="">
                <p>${classify_5.name}</p>
            </a>
        </li>
        <li>
            <a href="classify:${classify_6.id}-${classify_6.name}">
                <img src="${classify_6.hgImage}" height="150" width="150" alt="">
                <p>${classify_6.name}</p>
            </a>
        </li>
        <li>
            <a href="classify:${classify_7.id}-${classify_7.name}">
                <img src="${classify_7.hgImage}" height="150" width="150" alt="">
                <p>${classify_7.name}</p>
            </a>
        </li>
        <li>
            <a href="classify:${classify_8.id}-${classify_8.name}">
                <img src="${classify_8.hgImage}" height="150" width="150" alt="">
                <p>${classify_8.name}</p>
            </a>
        </li>
        <li>
            <a href="classify:${classify_9.id}-${classify_9.name}">
                <img src="${classify_9.hgImage}" height="150" width="150" alt="">
                <p>${classify_9.name}</p>
            </a>
        </li>

    </ul>

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
                    <a href="javascript:void(0)">
                        <div class="gray">${hospital.name}</div>
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
                    <p class="top">${product.hospitalName}<span class="position"></span>${product.city}</p>
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
</body>
</html>