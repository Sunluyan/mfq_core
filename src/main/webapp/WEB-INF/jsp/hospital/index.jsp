<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,com.mfq.bean.*" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <title>美分</title>
</head>
<body>
<style>
    body {
        background-color: #f4f4f4;
        margin: 0;
        font-family: "NewFont";
    }



    a {
        text-decoration: none;
        color: #000;
    }

    .boxs {
        margin: 20px 3%;
        position: relative;
    }

    .box {
        overflow: hidden;
        border-radius: 10px;
        border: solid 1px #dcdcdc;
        box-shadow: 0 0 3px 0 rgba(0, 0, 0, .3);
        position: relative;
    }

    .box .bg {
        float: left;
        width: 100%;
    }

    .point {
        background: rgba(255, 255, 255, .95);
        position: absolute;
        bottom: 0;
        left: 0;
        right: 0;
        padding-left: 3%;
        border-top: solid 1px rgba(0, 0, 0, .1);
    }

    .point .left {
        font-size: 1rem;
        padding: .4rem 0;
    }

    .point .left div {
        font-size: .6rem;
        margin-top: .4rem;
    }

    .boxs .boxs-icon {
        position: relative;
    }

    .boxs .boxs-fix {
        position: absolute;
        bottom: .6rem;
        right: -5px;
        overflow: hidden;
        height: 2.6rem;
    }

    .boxs .boxs-fix img {
        float: left;
        height: 100%;
    }

    .boxs .boxs-text {
        color: white;
        position: absolute;
        line-height: 3rem;
        top: 0;
        left: 0;
        font-size: .6rem;
        text-align: center;
        width: 100%;
        font-weight: 100;
    }

    .boxs .boxs-text span {
        font-size: 1.2rem;
    }

    .add-icon {
        width: .5rem;
        height: .7rem;
        padding-right: .6rem;
    }
</style>
<a href="#">
    <div class="boxs">
        <script type="text/javascript" src="/js/activity/jquery-1.8.2.min.js"></script>
        <div class="box">
            <img class="bg hosimg" src="../../images/hospital/img/mei-bg.jpg" alt="背景" draggable="false">

            <div class="point">
                <div class="left"><span class="hosname">上海艺星医疗美容医院</span>
                    <div>
                        <img src="../../images/hospital/img/add_01.png" class="add-icon"/>
                        <span class="hosaddress">地址：上海市长宁区虹桥路1165号</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="boxs-icon">
            <div class="boxs-fix">
                <img src="../../images/hospital/img/mei-icon1.png" alt="icon" draggable="false">

                <div class="boxs-text">项目 | <span class="total">300</span></div>
            </div>
        </div>
    </div>
</a>

</body>
</html>


<!-- [{"id":1,"name":"北京航空总医院","img":"http://img1.mfqzz.com/images/hospital/hkzyy.png","address":"北京市朝阳区安外北苑路3号院","createdAt":"Sep 21, 2015 12:21:17 PM","updatedAt":"Nov 2, 2015 7:22:53 PM"},{"id":2,"name":"上海艺星医疗美容医院","img":"http://img1.mfqzz.com/images/hospital/yixing.jpg","address":"上海市长宁区虹桥路1165号","createdAt":"Sep 21, 2015 12:25:27 PM","updatedAt":"Sep 21, 2015 12:25:29 PM"},{"id":3,"name":"北京美莱医疗美容医院","img":"http://img1.mfqzz.com/images/hospital/meilai.jpg","address":"北京市朝阳区朝外大街227号","createdAt":"Sep 21, 2015 12:26:52 PM","updatedAt":"Dec 3, 2015 11:51:31 AM"},{"id":4,"name":"北京和谐美丽汇医疗美容","img":"http://img1.mfqzz.com/images/hospital/hxmlh.jpg","address":"北京西城骡马市大街14号3号楼","createdAt":"Sep 21, 2015 12:26:52 PM","updatedAt":"Sep 21, 2015 12:26:55 PM"},{"id":5,"name":"重庆华美整形美容医院","img":"http://img1.mfqzz.com/images/hospital/huamei.jpg","address":"重庆市渝中区上清寺环球广场1-6楼","createdAt":"Sep 21, 2015 12:26:52 PM","updatedAt":"Sep 21, 2015 12:26:55 PM"},{"id":6,"name":"恩菲齿科","img":"http://img1.mfqzz.com/images/hospital/enfei.jpg","address":"北京市东城区东四十条新中西里13号巨石大厦","createdAt":"Sep 21, 2015 12:26:52 PM","updatedAt":"Sep 21, 2015 12:26:55 PM"},{"id":7,"name":"鼎点口腔连锁三亚分院","img":"http://img1.mfqzz.com/images/hospital/dingdian.jpg","address":"三亚河东区凤凰路1号金元椰景蓝岸3号商铺","createdAt":"Sep 21, 2015 12:26:52 PM","updatedAt":"Sep 21, 2015 12:26:55 PM"},{"id":8,"name":"三亚圣迪亚美容整形医院","img":"http://img1.mfqzz.com/images/hospital/sdy.jpg","address":"海南三亚大东海榆亚大道195号","createdAt":"Sep 21, 2015 12:26:52 PM","updatedAt":"Sep 21, 2015 12:26:55 PM"}] -->
<script type="text/javascript">
    $.get("/hospital/list/data").done(function (json) {

        var $boxs = $(".boxs").eq(0).clone(true);
        $(".boxs").eq(0).remove();
        for (var i = 0; i < json.length; i++) {

            var $box = $boxs.clone(true);
            $box.find(".hosimg").attr("src", json[i].img);
            $box.find(".hosname").html(json[i].name);
            $box.find(".hosaddress").html(json[i].address.substr(0, 15) + '...');
            $box.find(".total").html(json[i].total);

            $box.show();
            $("body").append($box);
        }
    })
</script>
</html>









