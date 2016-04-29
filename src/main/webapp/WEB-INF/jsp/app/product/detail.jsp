<%@ page import="com.mfq.bean.app.ProductDetail2App" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=0">
    <title>${app.name}</title>
    <script type="text/javascript" src="/js/wx/jquery.js"></script>
    <link href="/css/app/owl.carousel.css" rel="stylesheet">
    <link href="/css/app/owl.theme.css" rel="stylesheet">
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
        @media (min-device-width : 375px) and (max-device-width : 667px) and (-webkit-min-device-pixel-ratio : 2){

            html{
                font-size: 90%;
            }
        }



        *{margin:0;padding:0;list-style:none;border:none;}
        #owl-demo .item{
            margin: 3px;
        }
        #owl-demo .item img{
            display: block;
            width: 100%;
            height: auto;
        }


        body{
            background:rgb(238,238,238);
            overflow: auto;
        }
        .tab{
            width:100%;
            height:3rem;
            background:white;
            border-bottom: 1px solid #ddd;
            color:#999;
        }
        .tab-left{
            width:45%;
            height:100%;
            float:left;
            text-align: right;
            font-size: 1.1rem;
            line-height: 3rem;
            color:#fd5a9c;
        }
        .tab-right{
            width:45%;
            height:100%;
            text-align: left;
            font-size: 1.1rem;
            line-height: 3rem;
            float:right;
        }
        .wrap-father{
            width:100%;
            height:100%;
            overflow: hidden;
        }
        .wrap{
            width:200%;
            height:100%;
            position: relative;
            overflow: hidden;
        }
        .goods{
            width:50%;
            min-height:100%;
            height:auto;
            background:rgb(238,238,238);
            float:left;
            position: relative;
            left:0%;
            transition:all 0.5s;
            overflow: hidden;
        }
        .detail{
            width:50%;
            height:100%;
            background:rgb(238,238,238);
            float:left;
            position: relative;
            left:0%;
            transition:all 0.5s;
        }

        .under-image{
            width:98%;
            height:2.5rem;
            margin-left: 1%;
            background:black;
            position: relative;
            top:-2.6rem;
            opacity: 0.5;
        }
        .proname{
            width:30%;
            height:100%;
            float:left;
            opacity: 1;
            color:white;
            line-height: 2.5rem;
            padding-left: 0.6rem;

        }
        .img-index{
            width:30%;
            height:100%;
            float:right;
            opacity: 1;
            color:white;
            line-height: 2.5rem;
            text-align: right;
            padding-right: 0.6rem;
        }
        .hospital{
            display: block;
            width:100%;
            height:5.5rem;
            background: white;
            position: relative;
            top:-2.6rem;
            padding:0.5rem;
        }
        .hospital-img{
            width:25%;
            height:100%;
            float:left;
            background:white;
        }
        .hos-img{
            height:98%;
            float:left;
            margin-left: 0.3rem;
            overflow:hidden;
        }
        .hospital-content{
            width:60%;
            height:100%;
            float:left;
            background:white;
            overflow: hidden;
            padding-left: 1rem;
        }
        .hos-name{
            font-size: 1.1rem;
            color:#333;
        }
        .hos-desc{
            font-size: 0.8rem;
            color:#999;
            margin-top: 1.2rem;
            line-height: 1.4;
        }

        .arrow{
            width:8%;
            height:100%;
            float:right;
            position: relative;
        }
        .arrow-svg{
            width:2rem;
            height:2rem;
            margin-top: 50%;
            position: absolute;
            top:1rem;
            right:0.5rem;
        }


        .product{
            width:100%;
            min-height:7rem;
            background:white;
            position: relative;
            top:-1.5rem;
            background:#eee;
        }
        .prices{
            border-bottom: none;
            height:3rem;
            line-height: 3rem;
            background:white;
        }
        .price{
            color:#fd5a9c;
            padding-left: 0.5rem;
            font-size: 1.0rem;
        }
        .bigger-font{
            font-size: 1.3rem;
        }
        .smallest-fq{
            font-size: 0.7rem;
            margin-left: 0.3rem;
            color:#999;
        }
        .fq-detail{
            float:right;
            margin-right: 1rem;
            color:#555;
        }
        .arrow-down{
            width:0.8rem;
            height:0.8rem;
            transition: all 0.5s;
        }
        .fq-details{
            width:100%;
            height:0;
            background:white;
            border-bottom: 1px solid #ccc;
            overflow: hidden;
            transition:all 0.5s;
        }
        .fq{
            float:left;
            width:40%;
            height:0.5rem;
            padding:1rem 1rem 0.2rem 1.5rem;
            color:#999;
            text-align: left;
            letter-spacing: 1.2;
        }
        .fq:last-child{
            padding-bottom: 1.2rem;
        }
        .cheap-reason{
            height:5rem;
            background:white;
            padding:0.5rem;
            color:#666;
            letter-spacing: 1.1;
            line-height: 1.2;
        }

        .bottom-wrap-tab{
            width:50%;
            height:3rem;
            line-height: 3rem;
            text-align: center;
            float:left;
            background:white;
            border-bottom: 1px solid #ccc;
            color:#666;
            margin-top: 1rem;
            position: relative;
            font-size: 1.1rem;
        }

        .bottom-tab-choiced{
            color:#fd5a9c;
            border-bottom: 1px solid #fd5a9c;
        }

        .bottom-wrap{
            width:100%;
            height:auto;
            margin-top: 4.1rem;
            background-color: white;
            position: relative;
            overflow: hidden;
        }
        .bottom-wrap-father{
            width:200%;
            height:auto;
            overflow: hidden;
            position: relative;
        }
        .bottom-wrap-left{
            width:50%;
            float:left;
            background:white;
            transition:all 0.5s;
            position: relative;
            left:0%;
        }


        .before{
            width:50%;
            float:left;
            background-color: white;
            position: relative;
            text-align: center;
        }
        .before img{
            padding:0.4rem;
            width:90%;
        }
        .after{
            width:50%;
            float:left;
            background-color: white;
            position: relative;
            text-align: center;
        }
        .after img{
            padding:0.4rem;
            width:90%;
        }

        .beautiful-other{
            width:95%;
            text-align: center;
        }

        .beautiful-other img{
            width:100%;
            padding:0.4rem;
        }

        .bottom-wrap-right{
            width:50%;
            float:left;
            background:white;
            transition:all 0.5s;
            position: relative;
            left:0%;
            text-align: center;
        }
        .bottom-wrap-right img{
            width:95%;
            padding:1rem 0;
        }

        .detail-wrap-father{
            width:200%;
            height:auto;
            overflow: hidden;
            position: relative;
        }

        .detail-tab{
            width:50%;
            height:3rem;
            line-height: 3rem;
            text-align: center;
            float:left;
            background:white;
            margin-top: 1rem;
            position: relative;
            font-size: 1.1rem;
        }

        .detail-wrap-left{
            width:50%;
            float:left;
            background:white;
            transition:all 0.5s;
            position: relative;
            left:0%;
        }

        .detail-wrap-left img{
            width:100%;
            padding:0.5rem 0;
        }
        .detail-wrap-right{
            width:50%;
            float:left;
            background:white;
            transition:all 0.5s;
            position: relative;
            left:0%;
        }


        .ask{
            padding:1rem;
            padding-bottom: 0.5rem;
            padding-left: 1.5rem;
            color:#555;
        }
        .question{
            padding:1rem;
            padding-top: 0;
            padding-bottom: 1rem;
            padding-left:1.5rem;
            color:#999;
        }
    </style>
</head>
<body>
<div class="tab">
    <div class="tab-left">商品</div>
    <div class="tab-right">详情</div>
</div>
<div class="wrap-father">

    <div class="wrap">
        <div class="goods">
            <div id="owl-demo" class="owl-carousel">
                <c:forEach items="${app.pro_imgs}" var="img">
                    <div class="item"><img class="lazyOwl" data-src="${img}"></div>
                </c:forEach>
                <div class="item"><img class="lazyOwl" data-src="http://img1.mfqzz.com/img1/p/20160324/14588175845217hT.jpg"></div>
                <div class="item"><img class="lazyOwl" data-src="http://img1.mfqzz.com/img1/p/20160324/14588175845217hT.jpg"></div>
                <div class="item"><img class="lazyOwl" data-src="http://img1.mfqzz.com/img1/p/20160324/14588175845217hT.jpg"></div>
                <div class="item"><img class="lazyOwl" data-src="http://img1.mfqzz.com/img1/p/20160324/14588175845217hT.jpg"></div>
                <div class="item"><img class="lazyOwl" data-src="http://img1.mfqzz.com/img1/p/20160324/14588175845217hT.jpg"></div>
                <div class="item"><img class="lazyOwl" data-src="http://img1.mfqzz.com/img1/p/20160324/14588175845217hT.jpg"></div>
                <div class="item"><img class="lazyOwl" data-src="http://img1.mfqzz.com/img1/p/20160324/14588175845217hT.jpg"></div>

            </div>
            <div class="under-image">
                <div class="proname">${app.name}</div>
                <div class="img-index"><span class="img-inx">0</span>/<span class="img-total">0</span></div>
            </div>
            <a href="/hospital/${app.hos_id}" class="hospital">
                <div class="hospital-img">
                    <img class="hos-img" src="${app.hos_img}">
                </div>

                <div class="hospital-content">
                    <div class="hos-name">${app.hos_name}</div>
                    <p class="hos-desc">${app.hos_desc}</p>
                </div>
                <div class="arrow">
                    <img src="http://7xlcaq.com2.z0.glb.qiniucdn.com/2016.03.35.iconfont-arrow.svg" class="arrow-svg">
                </div>
            </a>

            <div class="product">
                <div class="prices">
					<span class="price">￥<span class="bigger-font">${app.fqs[12]}</span><span> x12期</span>
					<span class="smallest-fq">全价￥${app.price}</span>
					<span class="fq-detail">分期详情
						<img src="http://7xlcaq.com2.z0.glb.qiniucdn.com/2016.03.35.iconfont-down.svg" class="arrow-down">
					</span>
                </div>
                <div class="fq-details">
                    <span class="fq">${app.fqs[3]} x 3期</span>
                    <span class="fq">${app.fqs[6]} x 6期</span>
                    <span class="fq">${app.fqs[12]} x 12期</span>
                </div>
                <div class="cheap-reason">
                    <p>
                        ${app.cheap_reason}
                    </p>
                </div>
                <div class="tab-beautiful bottom-wrap-tab bottom-tab-choiced">蜕变日记</div>
                <div class="tab-surgery bottom-wrap-tab">第三视角</div>

                <div class="bottom-wrap">
                    <div class="bottom-wrap-father">
                        <div class="bottom-wrap-left">
                            <div class="beautiful-detail">
                                <div class="before">
                                    <img src="${app.before}" >
                                </div>
                                <div class="after">
                                    <img src="${app.after}" >
                                </div>
                                <div class="beautiful-other">
                                    <img src="${app.dairy}" >
                                </div>
                            </div>
                        </div>
                        <div class="bottom-wrap-right">
                            <img src="${app.surgery}">
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="detail">
            <div class="detail-tab-message detail-tab bottom-tab-choiced">产品信息</div>
            <div class="detail-tab-ask detail-tab">常见问题</div>

            <div class="detail-wrap-father">
                <div class="detail-wrap-left">
                    <div class="detail-product">
                        <c:forEach items="${app.details}" var="img">
                            <img src="${img}" >
                        </c:forEach>
                    </div>
                </div>

                <div class="detail-wrap-right">
                    <p class="ask" style="display: none;">
                        问：孕妇可以割双眼皮吗？
                    </p>

                    <p class="question" style="display: none;">
                        答：孕妇不可以割双眼皮
                    </p>


                </div>
            </div>
        </div>
    </div>
</div>


</body>
<script src="/js/app/product/owl.carousel.online.min.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("#owl-demo").owlCarousel({
            lazyLoad : true,
            navigation : false
        });
    });


    $(".tab-right").click(function(){
        $(".goods,.detail").css("left","-50%");
        $(".tab-left").css("color","#999")
        $(".tab-right").css("color","#fd5a9c")

        //调整wrap的高度
        $(".detail-wrap-father").height($(".detail-wrap-left").height())
        $(".wrap").height($(".detail-tab").height()+$(".detail-wrap-father").height())

    })
    $(".tab-left").click(function(){
        $(".goods,.detail").css("left","0%");
        $(".tab-right").css("color","#999")
        $(".tab-left").css("color","#fd5a9c")
        $(".wrap").height($(".goods").height())
    })

    var fqOpen = false;
    $(".fq-detail").click(function(){
        if(fqOpen){
            $(".fq-details").css("height","0")
            setTimeout(function(){
                $(".prices").css("border-bottom","none");
            },430)
            fqOpen = false;
        }else{
            $(".fq-details").css("height","5rem")
            $(".prices").css("border-bottom","1px solid #ccc");
            fqOpen = true;
        }
    })

    $(".bottom-wrap-tab").click(function(){
        $(".bottom-wrap-tab").removeClass("bottom-tab-choiced")
        $(this).addClass("bottom-tab-choiced");
        if(this.className.indexOf('beautiful') == -1){
            $(".bottom-wrap-left").css("left","-50%")
            $(".bottom-wrap-right").css("left","-50%")
            $(".bottom-wrap").height($(".bottom-wrap-right").height());
        }else{
            $(".bottom-wrap-left").css("left","0%")
            $(".bottom-wrap-right").css("left","0%")
            $(".bottom-wrap").height($(".bottom-wrap-left").height());
        }
    })

    $(".detail-tab").click(function(){
        $(".detail-tab").removeClass("bottom-tab-choiced")
        $(this).addClass("bottom-tab-choiced");
        if(this.className.indexOf('message') == -1){
            //第二个
            $(".detail-wrap-left").css("left","-50%")
            $(".detail-wrap-right").css("left","0%")

            $(".detail-wrap-father").height($(".detail-wrap-right").height())
            $(".wrap").height($(".detail-tab").height()*2+$(".detail-wrap-father").height())
        }else{
            //第一个
            $(".detail-wrap-left").css("left","0%")
            $(".detail-wrap-right").css("left","0%")

            $(".detail-wrap-father").height($(".detail-wrap-left").height())
            $(".wrap").height($(".detail-tab").height()*2+$(".detail-wrap-father").height())
        }
    })
    $(".img-total").html($(".lazyOwl").length)

    var json = ${app.ask}
    for(var i = 0;i<json.length;i++){
        var $ask = $(".ask").last();
        $ask.html("问："+json[i].question)
        $ask.show();
        $(".detail-wrap-right").append($ask);

        var $question = $(".question").last();
        $question.html("答："+json[i].answer)
        $question.show();
        $(".detail-wrap-right").append($question);

    }
</script>
</html>




















