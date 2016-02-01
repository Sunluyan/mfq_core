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
<%--<div class="header">--%>
<%--<div class="header-inner" >--%>
<%--<ul id="id" class="fuck">--%>
<%--<c:forEach items="${classifys}" var="cla">--%>
<%--<li style="background-image: url(${cla.hgImage})" onclick="window.location.href='classify:${cla.id}-${cla.name}';">--%>
<%--<a href="#">--%>
<%--<div class="gray">${cla.name}</div>--%>
<%--</a>--%>
<%--</li>--%>

<%--</c:forEach>--%>


<%--&lt;%&ndash;<li><a href="#"><div class="gray">鼻子</div></a></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;<li><a href="#"><div class="gray">唇部</div></a></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;<li><a href="#"><div class="gray">牙齿</div></a></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;<li><a href="#"><div class="gray">瘦身</div></a></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;<li><a href="#"><div class="gray">面部</div></a></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;<li><a href="#"><div class="gray">针剂</div></a></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;<li><a href="#"><div class="gray">脱毛</div></a></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;<li><a href="#"><div class="gray">胸部</div></a></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;<li><a href="#"><div class="gray">皮肤</div></a></li>&ndash;%&gt;--%>
<%--</ul>--%>

<%--</div>--%>

<%--</div>--%>

<div style="height: 10px; width: 100%"></div>
<!-- Save for Web Slices (美分期APP彩色 副本.psd) -->
<table width="100%" border="0" align="center" cellpadding="6" cellspacing="0" bgcolor="#FFFFFF" class="down_line">
    <tr>
        <td align="center" valign="middle">

            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td align="center">
                        <a href="classify:${classify_0.id }-${classify_0.name }" style="text-decoration: none;
    color: #777;font-size: 16px;">
                            <table width="60" border="0" cellpadding="2" cellspacing="1" onClick="">
                                <tr>
                                    <td align="center" valign="middle"><img src="${classify_0.hgImage }" width="43" alt=""> </td>
                                </tr>
                                <tr>
                                    <td align="center" valign="middle">${classify_0.name }</td>
                                </tr>
                            </table>
                        </a>
                    </td>
                    <td align="center">
                        <a href="classify:${classify_1.id }-${classify_1.name }" style="text-decoration: none;
    color: #777;font-size: 16px;">
                            <table width="60" border="0" cellpadding="2" cellspacing="1">
                                <tr>
                                    <td align="center" valign="middle"><img src="${classify_1.hgImage }" alt="" width="43"> </td>
                                </tr>
                                <tr>
                                    <td align="center" valign="middle">${classify_1.name }</td>
                                </tr>
                            </table>
                        </a>
                    </td>
                    <td align="center">
                        <a href="classify:${classify_2.id }-${classify_2.name }" style="text-decoration: none;
    color: #777;font-size: 16px;">
                            <table width="60" border="0" cellpadding="2" cellspacing="1">
                                <tr>
                                    <td align="center" valign="middle"><img src="${classify_2.hgImage }" alt="" width="43"> </td>
                                </tr>
                                <tr>
                                    <td align="center" valign="middle">${classify_2.name }</td>
                                </tr>
                            </table>
                        </a>
                    </td>
                    <td align="center">
                        <a href="classify:${classify_3.id }-${classify_3.name }" style="text-decoration: none;
    color: #777;font-size: 16px;">
                            <table width="60" border="0" cellpadding="2" cellspacing="1">
                                <tr>
                                    <td align="center" valign="middle"><img src="${classify_3.hgImage }" alt="" width="43"> </td>
                                </tr>
                                <tr>
                                    <td align="center" valign="middle">${classify_3.name }</td>
                                </tr>
                            </table>
                        </a>
                    </td>
                    <td align="center">
                        <a href="classify:${classify_4.id }-${classify_4.name }" style="text-decoration: none;
    color: #777;font-size: 16px;">
                            <table width="60" border="0" cellpadding="2" cellspacing="1">
                                <tr>
                                    <td align="center" valign="middle"><img src="${classify_4.hgImage }" alt="" width="43"> </td>
                                </tr>
                                <tr>
                                    <td align="center" valign="middle">${classify_4.name }</td>
                                </tr>
                            </table>
                        </a>
                    </td>
                </tr>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td align="center">
                        <a href="classify:${classify_5.id }-${classify_5.name }" style="text-decoration: none;
    color: #777;font-size: 16px;">
                            <table width="60" border="0" cellpadding="2" cellspacing="1">
                                <tr>
                                    <td align="center" valign="middle"><img src="${classify_5.hgImage }" alt="" width="43"> </td>
                                </tr>
                                <tr>
                                    <td align="center" valign="middle">${classify_5.name }</td>
                                </tr>
                            </table>
                        </a>
                    </td>
                    <td align="center">
                        <a href="classify:${classify_6.id }-${classify_6.name }" style="text-decoration: none;
    color: #777;font-size: 16px;">
                            <table width="60" border="0" cellpadding="2" cellspacing="1">
                                <tr>
                                    <td align="center" valign="middle"><img src="${classify_6.hgImage }" alt="" width="43"> </td>
                                </tr>
                                <tr>
                                    <td align="center" valign="middle">${classify_6.name }</td>
                                </tr>
                            </table>
                        </a>
                    </td>
                    <td align="center">
                        <a href="classify:${classify_7.id }-${classify_7.name }" style="text-decoration: none;
    color: #777;font-size: 16px;">
                            <table width="60" border="0" cellpadding="2" cellspacing="1">
                                <tr>
                                    <td align="center" valign="middle"><img src="${classify_7.hgImage }" alt="" width="43"> </td>
                                </tr>
                                <tr>
                                    <td align="center" valign="middle">${classify_7.name }</td>
                                </tr>
                            </table>
                        </a>
                    </td>
                    <td align="center">
                        <a href="classify:${classify_8.id }-${classify_8.name }" style="text-decoration: none;
    color: #777;font-size: 16px;">
                            <table width="60" border="0" cellpadding="2" cellspacing="1">
                                <tr>
                                    <td align="center" valign="middle"><img src="${classify_8.hgImage }" alt="" width="43"> </td>
                                </tr>
                                <tr>
                                    <td align="center" valign="middle">${classify_8.name }</td>
                                </tr>
                            </table>
                        </a>
                    </td>
                    <td align="center">
                        <a href="classify:${classify_9.id }-${classify_9.name }" style="text-decoration: none;
    color: #777;font-size: 16px;">
                            <table width="60" border="0" cellpadding="2" cellspacing="1">
                                <tr>
                                    <td align="center" valign="middle"><img src="${classify_9.hgImage }" alt="" width="43"> </td>
                                </tr>
                                <tr>
                                    <td align="center" valign="middle">${classify_9.name }</td>
                                </tr>
                            </table>
                        </a>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
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

<script>
    $.mobile.hidePageLoadingMsg();
</script>
</body>
</html>