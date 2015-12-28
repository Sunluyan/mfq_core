<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script type="text/javascript">function isMobile() {
    var sUserAgent = navigator.userAgent.toLowerCase();
    var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
    var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
    var bIsMidp = sUserAgent.match(/midp/i) == "midp";
    var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
    var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
    var bIsAndroid = sUserAgent.match(/android/i) == "android";
    var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
    var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";

    
    if (bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) {
        var str = window.location + "";
        if (str.indexOf("goods_") != -1) {
            var str1 = str.split(".");
            var str2 = str1[1].split("_");
            window.location = "http://m.meilime.com/goods/view/" + str2[1]
        } 
         else if(str.indexOf("http://sale.meilime.com/two/index-418.htm") != -1 ){
                
             window.location.href = "http://t.cn/RvoK2Jb";
        }
        else {
                window.location.href = "http://m.meilime.com/";
            }
        
    }
}
isMobile();</script>
    <div class="topHeaderWrap">
        <div class="topHeader">
            <div class="topCollect">
                <p>
                    <i class="star"></i><a id="scbz" href="javascript:addToFavorite()"
                        rel="nofollow" class="cell">收藏</a>
                </p>
                <p>
                    <i class="deli"></i><em>送花至</em><span class="ajax_deli_city"></span><span
                        class="repCity">[<a href="javascript:;">更换</a>]
                    </span>
                </p>
                <script type="text/javascript">var userArray=getCookie("user_province");userArray=userArray.split("_");if(userArray[0]==""||userArray[0]==undefined){var url_kc=domain_public+"/getOrgionByRequest.htm?t="+new Date().getTime();var proxy_url="httpProxyAccessa18a.html?t="+new Date().getTime();jQuery.ajax({type:"POST",url:proxy_url,data:{target:url_kc},dataType:"json",success:function(data){var orgionId="2";if(data){orgionId=data.orgionId}if(orgionId==""){orgionId="2"}setCookie("user_province",orgionId,12);var sa1=returnArrya(1,orgionId);$(".ajax_deli_city").text(sa1[1])}})}else{try{var sa1=returnArrya(1,userArray[0]);$(".ajax_deli_city").text(sa1[1])}catch(e){setCookie("user_province","2",12);$(".ajax_deli_city").text("北京")}};</script>
                <!-- 加载用户的地区到页头 -->
                <div class="deli_citybx">
                    <ul class="deli_citylt">
                        <li><em>华北：</em><p><a href="javascript:;" pid="2">北京</a><a href="javascript:;" pid="27">天津</a><a href="javascript:;" pid="10">河北</a><a href="javascript:;" pid="23">山西</a><a href="javascript:;" pid="19">内蒙古</a></p></li>
                        <li><em>华东：</em><p><a href="javascript:;" pid="25">上海</a><a href="javascript:;" pid="16">江苏</a><a href="javascript:;" pid="31">浙江</a><a href="javascript:;" pid="3">安徽</a><a href="javascript:;" pid="4">福建</a><a href="javascript:;" pid="17">江西</a><a href="javascript:;" pid="22">山东</a></p></li>
                        <li><em>华中：</em><p><a href="javascript:;" pid="11">河南</a><a href="javascript:;" pid="13">湖北</a><a href="javascript:;" pid="14">湖南</a></p></li>
                        <li><em>华南：</em><p><a href="javascript:;" pid="6">广东</a><a href="javascript:;" pid="7">广西</a><a href="javascript:;" pid="9">海南</a></p></li>
                        <li><em>东北：</em><p><a href="javascript:;" pid="18">辽宁</a><a href="javascript:;" pid="15">吉林</a><a href="javascript:;" pid="12">黑龙江</a></p></li>
                        <li><em>西北：</em><p><a href="javascript:;" pid="24">陕西</a><a href="javascript:;" pid="5">甘肃</a><a href="javascript:;" pid="21">青海</a><a href="javascript:;" pid="20">宁夏</a><a href="javascript:;" pid="29">新疆</a></p></li>
                        <li><em>西南：</em><p><a href="javascript:;" pid="32">重庆</a><a href="javascript:;" pid="26">四川</a><a href="javascript:;" pid="8">贵州</a><a href="javascript:;" pid="30">云南</a><a href="javascript:;" pid="28">西藏</a></p></li>
                    </ul>
                    <div title="关闭" class="delcty_close">x</div>
                </div>
            </div>
            <div class="topRight">
                <ul>
                    <li name="_login_status_panel"><span>欢迎来到美分期！[<a href="/login.htm">登录</a>]&nbsp;[<a href="/register.htm">注册</a>]</span><i></i></li>
                    <li><a href="http://member.meilime.com/index.htm" target="_blank" class="userCenter">用户中心</a><i></i></li>
                    <li class="topRightM">
                        <i class="mobile"></i><a href="/mobile.htm" class="mobilePhone">手机美分期</a><em></em><i></i>
                        <div class="hd_mobile_list" style="display: none;">
                            <p class="dd" id="dd1" style="border-left-style: solid; border-left-width: 1px; border-left-color: rgb(221, 221, 221);">
                                <i></i><a style="padding: 0px 1px 0px 2px;" href="http://m.meilime.com/ad/f26cb397f9b15dad" target="_blank">iPhone</a><a style="padding: 0px">/</a><a style="padding: 0px 1px;" href="http://m.meilime.com/ad/4be6f446d4670bc2" target="_blank">Android</a>
                            </p>
                            <p class='dd' id='dd2'><i></i><a href="http://m.meilime.com/ad/d1877649ee531d94" target="_blank">wap</a></p>
                        </div>
                        <div class="hd_mobile_pix" style="display: none;"><img src="#" width=72 height=72 /></div>
                    </li>
                    <li class="topRightGwc"><i class="car"></i><a
                        href="http://cart.meilime.com/" target="_blank" class="cartHand">购物车</a><span
                        class="qty jx_car_num">0</span><i></i></li>
                    <li class="hotTel">客服热线<strong>186-1225-8336</strong></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="midHeader">
        <div class="midHeaderBox">
            <div class="jxwLogo">
                <p class="jxwIndex" style="background: url(/image/bill/36bb79d6b0544085a09106f407e6120b.jpg) no-repeat;"><a href="http://www.meilime.com"></a></p>
                <a href="#" target="_blank"><img src="/image/bill/762148f6efc948318a829cb097759f3e.gif" /></a>
            </div>
            <div class="navBox">
                <div class="searchBox">
                    <form action="/search.htm" method="get">
                        <div class="searchLeft"></div>
                        <input id="wd" name="keys" type="text" autocomplete="off" class="searchBg" value="情人节" maxLength="50"><input name="" type="submit" class="searchBtn" value="搜索">
                    </form>
                    <div class="searchCon" style="display: none;"><ul></ul></div>
                </div>
                <div class="seoNav">
                    <a href="#" target="_blank">红玫瑰</a>|
                    <a href="#" target="_blank">蓝玫瑰</a>|
                    <a href="#" target="_blank">康乃馨</a>|
                    <a href="#" target="_blank">扶朗</a>|
                    <a href="#" target="_blank">百合花</a>|
                    <a href="#" target="_blank">粉玫瑰</a>|
                    <a href="#" target="_blank">白玫瑰</a>
                </div>
            </div>
            <div class="logoAll"><a href="#" target="_blank"></a><a href="#" target="_blank"></a><a href="#" target="_blank"></a></div>
        </div>
    </div>
    <div class="nav_bg">
        <div class="nav">
            <div class="column">
                <ul>
                    <li id="nav_list"><i></i>
                    <p>全部鲜花礼品分类</p></li>
                    <li><a href="http://www.meilime.com" id="index">首&nbsp;&nbsp;页</a></li>
                    <li><a href="/flower/" id="xianhua" target="_blank">鲜花 </a></li>
                    <li class="mjqc"><a href="/cake/" id="dangao" target="_blank">蛋糕 </a><span class="new"></span></li>
                    <li><a href="/list.html" id="lilan" target="_blank">礼篮 </a></li>
                    <li class="mjqc"><a href="/business/" id="shangwu" target="_blank">商务鲜花 </a><span class="hot"></span></li>
                    <li><a href="/plants/" id="lvzhi" target="_blank">绿植花卉 </a></li>
                    <li class="mjqc"><a href="/doll/" id="gongzai" target="_blank">品牌公仔 </a><span class="new"></span></li>
                    <li><a href="/international/" id="guoji" target="_blank">国际鲜花 </a></li>
                    <div class="clear"></div>
                </ul>
            </div>
            <script type="text/javascript">jQuery("#"+stripscript(window.location.href)).addClass("on");</script>
            <div class="goHome">
                <a href="#" target="_blank"><img src='/misc/images/nav-adv/2014/0714/053f86c18c1441929b3b0f69f8f41235.jpg' width="109" height="40" /></a>
            </div>
            <div class="jnavleft">
                <!---默认显示-->
                <ul id="nav">
                    <li class="item single" id="_nowactstr1">
                        <h3 name="tagH" pathName='一键选花' url='http://www.meilime.com' ishot='true' isZlm='0'></h3>
                        <div class="cateCon">
                            <p>
                                <a target="_blank" href="#" class="impot" style="white-space: nowrap;">鲜花+蛋糕</a> 
                                <a target="_blank" href="#" style="white-space: nowrap;">爱情鲜花</a> 
                                <a target="_blank" href="#" style="white-space: nowrap;">礼篮</a>
                            </p>
                            <p>
                                <a target="_blank" href="#" style="white-space: nowrap;">生日鲜花</a>
                                <a target="_blank" href="#" style="white-space: nowrap;">商务鲜花</a>
                                <a target="_blank" href="#" class="impot" style="white-space: nowrap;">国际鲜花</a>
                            </p>
                        </div></li>
                    <li class="item double" id="_nowactstr2">
                        <h3 name="tagH" pathName='鲜花' url='#' ishot='false' isZlm='1'></h3>
                        <div class="cateCon">
                            <p>
                                <a target="_blank" href="#" style="white-space: nowrap;">玫瑰</a>
                                <a target="_blank" href="#" style="white-space: nowrap;">红玫瑰</a>
                                <a target="_blank" href="#" style="white-space: nowrap;">康乃馨</a>
                                <a target="_blank" href="#" style="white-space: nowrap;">郁金香</a>
                            </p>
                            <p>
                                <a target="_blank" href="#" style="white-space: nowrap;">百合</a>
                                <a target="_blank" href="#" style="white-space: nowrap;">扶郎</a>
                                <a target="_blank" href="#" style="white-space: nowrap;">马蹄莲</a>
                                <a target="_blank" href="#" style="white-space: nowrap;">向日葵</a>
                            </p>
                        </div></li>
                    <li class="item single" id="_nowactstr3"><h3 name="tagH" pathName='绿植|礼篮' url='#' ishot='false' isZlm='1'></h3>
                        <div class="cateCon">
                            <p>
                                <a target="_blank" href="#" style="white-space: nowrap;">绿色植物</a>
                                <a target="_blank" href="#" style="white-space: nowrap;">盆栽花卉</a>
                                <a target="_blank" href="#" style="white-space: nowrap;">果篮礼篮</a>
                            </p>
                            <p>
                                <a target="_blank" href="#" style="white-space: nowrap;">卡通花束</a>
                                <a target="_blank" href="#" style="white-space: nowrap;">保鲜花</a>
                            </p>
                        </div></li>
                    <li class="item double" id="_nowactstr4">
                        <h3 name="tagH" pathName='蛋糕' url='#' ishot='false' isZlm='1'></h3>
                        <div class="cateCon">
                            <p>
                                <a target="_blank" href="#" style="white-space: nowrap;">生日蛋糕</a> 
                                <a target="_blank" href="#" style="white-space: nowrap;">儿童蛋糕</a> 
                                <a target="_blank" href="#" style="white-space: nowrap;">鲜奶蛋糕</a> 
                            </p>
                            <p>
                                <a target="_blank" href="#" style="white-space: nowrap;">水果蛋糕</a>
                                <a target="_blank" href="#" style="white-space: nowrap;">巧克力蛋糕</a> 
                            </p>
                        </div></li>
                    <li class="item single" id="_nowactstr5">
                        <h3 name="tagH" pathName='公仔' url='#' ishot='false' isZlm='1'></h3>
                        <div class="cateCon">
                            <p>
                                <a target="_blank" href="#" style="white-space: nowrap;">公仔熊</a> 
                                <a target="_blank" href="#" style="white-space: nowrap;">公仔猪</a> 
                                <a target="_blank" href="#" style="white-space: nowrap;">公仔狗</a> 
                            </p>
                            <p>
                                <a target="_blank" href="#" style="white-space: nowrap;">公仔兔</a> 
                                <a target="_blank" href="#" style="white-space: nowrap;">公仔抱枕</a> 
                            </p>
                        </div></li>
                    <li class="item double last" id="_nowactstr6">
                        <h3 name="tagH" pathName='商务鲜花' url='#' ishot='false' isZlm='0'></h3>
                        <div class="cateCon">
                            <p>
                                <a target="_blank" href="#" style="white-space: nowrap;">开业花篮</a>
                                <a target="_blank" href="#" style="white-space: nowrap;">会议鲜花</a>
                                <a target="_blank" href="#" style="white-space: nowrap;">礼仪鲜花</a>
                        </div></li>
                </ul>
                <div style="background: none repeat scroll 0% 0% transparent; width: 0px; padding: 0px; margin: 0px; height: 0px;  border: 0px none; top: 365px; display: none;" class="menu_eject"></div>
                <div class="menu_eject" style="display: none;">
                    <div class="sub_menu_left">
                        <div class="sub_menu_list wideVersion">
                            <div class="sub_menu_line longLine">按用途选花</div>
                            <div class="sub_menu_con">
                                <div class="sub_menu_box">
                                    <a href="#" target="_blank" class="bh">爱情鲜花</a>
                                    <a href="#" target="_blank">友情鲜花</a>
                                    <a href="#" target="_blank" class="bh">生日鲜花</a>
                                    <a href="#" target="_blank">问候长辈</a>
                                    <a href="#" target="_blank">回报老师</a>
                                    <a href="#" target="_blank">祝福庆贺</a>
                                    <a href="#" target="_blank">婚庆鲜花</a>
                                    <a href="#" target="_blank">探病慰问</a>
                                    <a href="#" target="_blank">生子祝贺</a>
                                    <a href="#" target="_blank">道歉鲜花</a>
                                    <a href="#" target="_blank">家居鲜花</a>
                                    <a href="#" target="_blank">丧葬哀思</a>
                                    <a href="#" target="_blank">开业乔迁</a>
                                    <a href="#" target="_blank">商务礼仪</a>
                                    <a href="#" target="_blank">自选鲜花</a>
                                    <a href="#" target="_blank" class="bh">畅销鲜花排行</a>
                                    <a href="#" target="_blank" class="bh">港澳台鲜花</a>
                                    <a href="#" target="_blank" class="bh">国外鲜花</a>
                                </div>
                            </div>
                        </div>
                        <div class="sub_menu_list wideVersion">
                            <div class="sub_menu_line longLine">按花材选花</div>
                            <div class="sub_menu_con">
                                <div class="sub_menu_box">
                                    <a href="#" target="_blank">玫瑰</a>
                                    <a href="#" target="_blank">红玫瑰</a>
                                    <a href="#" target="_blank">粉玫瑰</a>
                                    <a href="#" target="_blank">白玫瑰</a>
                                    <a href="#" target="_blank">香槟玫瑰</a>
                                    <a href="#" target="_blank">紫玫瑰</a>
                                    <a href="#" target="_blank">蓝玫瑰</a>
                                    <a href="#" target="_blank">黄玫瑰</a>
                                    <a href="#" target="_blank">康乃馨</a>
                                    <a href="#" target="_blank">郁金香</a>
                                    <a href="#" target="_blank">百合</a>
                                    <a href="#" target="_blank">扶郎</a>
                                    <a href="#" target="_blank">马蹄莲</a>
                                    <a href="#" target="_blank">向日葵</a>
                                </div>
                            </div>
                        </div>
                        <div class="sub_menu_list wideVersion">
                            <div class="sub_menu_line longLine">按类别选花</div>
                            <div class="sub_menu_con">
                                <div class="sub_menu_box"> 
                                    <a href="#" target="_blank">传情花束</a>
                                    <a href="#" target="_blank">精致花篮</a>
                                    <a href="#" target="_blank">瓶插花</a>
                                    <a href="#" target="_blank">精品鲜花</a>
                                    <a href="#" target="_blank">创意花盒</a>
                                    <a href="#" target="_blank">鲜花礼篮</a>
                                </div>
                            </div>
                        </div>
                        <div class="sub_menu_list wideVersion">
                            <div class="sub_menu_line longLine">按节日选花</div>
                            <div class="sub_menu_con">
                                <div class="sub_menu_box"> 
                                    <a href="#" target="_blank">元旦节鲜花</a>
                                    <a href="#" target="_blank">春节鲜花</a>
                                    <a href="#" target="_blank">元宵节鲜花</a>
                                    <a href="#" target="_blank">情人节鲜花</a>
                                    <a href="#" target="_blank">妇女节鲜花</a>
                                    <a href="#" target="_blank">清明节鲜花</a>
                                    <a href="#" target="_blank">母亲节鲜花</a>
                                    <a href="#" target="_blank">520情人节</a>
                                    <a href="#" target="_blank">毕业鲜花</a>
                                    <a href="#" target="_blank">父亲节鲜花</a>
                                    <a href="#" target="_blank" class="bh">七夕节鲜花</a>
                                    <a href="#" target="_blank">教师节鲜花</a>
                                    <a href="#" target="_blank">中秋节鲜花</a>
                                    <a href="#" target="_blank">光棍节鲜花</a>
                                    <a href="#" target="_blank">感恩节鲜花</a>
                                    <a href="#" target="_blank">圣诞节鲜花</a>
                                </div>
                            </div>
                        </div>
                        <div class="sub_menu_list wideVersion">
                            <div class="sub_menu_line longLine">按价格选花</div>
                            <div class="sub_menu_con">
                                <div class="sub_menu_box">
                                    <a href="#" target="_blank">特价鲜花</a> 
                                    <a href="#" target="_blank">0-150元鲜花</a> 
                                    <a href="#" target="_blank">150-250元</a> 
                                    <a href="#" target="_blank">250-350元</a> 
                                    <a href="#" target="_blank">350-550元</a> 
                                    <a href="#" target="_blank">550-800元</a> 
                                    <a href="#" target="_blank">800元以上</a> 
                                </div>
                            </div>
                        </div>
                        <div class="sub_row"></div>
                    </div>
                    <div class="sub_menu_right">
                        <div class="sub_menu_line rg">促销活动</div>
                        <ul class="sub_menu_rg">
                            <li><a href="#" target="_blank"><img src="/image/bill/9946e90916524798bac84f1ccd28c3f0.jpg" width="200"></a></li>
                            <li class="sub_lastpro"><a href="#" target="_blank"><img src="/image/bill/d3b9908895164b10864780a2d889cc37.jpg" width="200"></a></li>
                            <div class="clear"></div>
                        </ul>
                        <div class="sub_menu_line rinfo">花语</div>
                        <ul class="sub_menu_info">
                            <li><a href="#" target="_blank">2014七夕送花攻略</a></li>
                            <li><a href="#" target="_blank">2014年七夕是几月几号？</a></li>
                            <li><a href="#" target="_blank">第一次表白送什么花？</a></li>
                            <li class="sub_lastpro"><a href="#" target="_blank">七夕送多少朵玫瑰比较好？</a></li>
                            <div class="clear"></div>
                        </ul>
                    </div>
                </div>
                <div class="menu_eject" style="display: none;">
                    <div class="sub_menu_left">
                        <div class="sub_row">
                            <div class="sub_menu_list wideVersion">
                                <div class="sub_menu_line longLine">按礼品分类选择</div>
                                <div class="sub_menu_con">
                                    <div class="sub_menu_box">
                                        <a href="#" target="_blank" class="bh">果篮礼篮</a>
                                        <a href="#" target="_blank">绿色植物</a>
                                        <a href="#" target="_blank">盆栽花卉</a>
                                        <a href="#" target="_blank">品牌公仔</a>
                                        <a href="#" target="_blank">卡通花束</a>
                                        <a href="#" target="_blank">泰国保鲜花</a>
                                    </div>
                                </div>
                            </div>
                            <div class="sub_menu_list wideVersion">
                                <div class="sub_menu_line longLine">礼品价格区间</div>
                                <div class="sub_menu_con">
                                    <div class="sub_menu_box">
                                        <a href="#" target="_blank">特价礼品</a> 
                                        <a href="#" target="_blank">0-150元礼品</a> 
                                        <a href="#" target="_blank">150-250元</a> 
                                        <a href="#" target="_blank">250-350元</a> 
                                        <a href="#" target="_blank">350-550元</a> 
                                        <a href="#" target="_blank">550-800元</a> 
                                        <a href="#" target="_blank">800元以上</a> 
                                    </div>
                                </div>
                            </div>
                            <div class="sub_menu_list wideVersion">
                                <div class="sub_menu_line longLine">按植物类别选择</div>
                                <div class="sub_menu_con">
                                    <div class="sub_menu_box">
                                        <a href="#" target="_blank">绿色植物</a> 
                                        <a href="#" target="_blank">盆栽花卉</a> 
                                    </div>
                                </div>
                            </div>
                            <div class="sub_menu_list wideVersion">
                                <div class="sub_menu_line longLine">按植物价格选择</div>
                                <div class="sub_menu_con">
                                    <div class="sub_menu_box">
                                        <a href="#" target="_blank">特价鲜花</a> 
                                        <a href="#" target="_blank">0-150元鲜花</a> 
                                        <a href="#" target="_blank">150-250元</a> 
                                        <a href="#" target="_blank">250-350元</a> 
                                        <a href="#" target="_blank">350-550元</a> 
                                        <a href="#" target="_blank">550-800元</a> 
                                        <a href="#" target="_blank">800元以上</a> 
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="sub_menu_right">
                        <div class="sub_menu_line rg">促销活动</div>
                        <ul class="sub_menu_rg">
                            <li><a href="#" target="_blank"><img src="/image/bill/af82d37cd163473396c5ed7e9ceded0a.jpg" width="200"></a></li>
                            <div class="clear"></div>
                        </ul>
                        <div class="sub_menu_line rinfo">最新加盟</div>
                        <ul class="sub_menu_info">
                            <li><a href="#" target="_blank">西班牙半甜葡萄酒 28元</a></li>
                            <li><a href="#" target="_blank">法国圣贝克桃红 39元</a></li>
                            <li class="sub_lastpro"><a href="#" target="_blank">法国圣堡兰帝干红 39元</a></li>
                            <div class="clear"></div>
                        </ul>
                    </div>
                </div>
                <div class="menu_eject" style="display: none;">
                    <div class="sub_menu_left">
                        <div class="sub_row">
                            <div class="sub_menu_list wideVersion">
                                <div class="sub_menu_line longLine">品牌蛋糕</div>
                                <div class="sub_menu_con">
                                    <div class="sub_menu_box">
                                        <a href="#" target="_blank" class="bh">一品轩蛋糕</a>
                                        <a href="#" target="_blank">元祖蛋糕</a>
                                        <a href="#" target="_blank">好利来蛋糕</a>
                                        <a href="#" target="_blank">窝夫小子蛋糕</a>
                                        <a href="#" target="_blank">全国蛋糕</a>
                                    </div>
                                </div>
                            </div>
                            <div class="sub_menu_list wideVersion">
                                <div class="sub_menu_line longLine">蛋糕分类</div>
                                <div class="sub_menu_con">
                                    <div class="sub_menu_box">
                                        <a href="#" target="_blank">鲜奶蛋糕</a>
                                        <a href="#" target="_blank">水果蛋糕</a>
                                        <a href="#" target="_blank">巧克力蛋糕</a>
                                        <a href="#" target="_blank">慕斯蛋糕</a>
                                        <a href="#" target="_blank">无糖蛋糕</a>
                                        <a href="#" target="_blank">生肖蛋糕</a>
                                        <a href="#" target="_blank">情人蛋糕</a>
                                        <a href="#" target="_blank">婚庆蛋糕</a>
                                        <a href="#" target="_blank">祝寿蛋糕</a>
                                        <a href="#" target="_blank">儿童蛋糕</a>
                                        <a href="#" target="_blank">欧式蛋糕</a>
                                        <a href="#" target="_blank">节庆蛋糕</a>
                                    </div>
                                </div>
                            </div>
                            <div class="sub_menu_list wideVersion">
                                <div class="sub_menu_line longLine">蛋糕送达城市</div>
                                <div class="sub_menu_con">
                                    <div class="sub_menu_box">
                                        <a href="#" target="_blank">北京</a>
                                        <a href="#" target="_blank">上海</a>
                                        <a href="#" target="_blank">深圳</a>
                                        <a href="#" target="_blank">天津</a>
                                        <a href="#" target="_blank">重庆</a>
                                        <a href="#" target="_blank">成都</a>
                                        <a href="#" target="_blank">长春</a>
                                        <a href="#" target="_blank">吉林</a>
                                        <a href="#" target="_blank">沈阳</a>
                                        <a href="#" target="_blank">大连</a>
                                        <a href="#" target="_blank">西安</a>
                                        <a href="#" target="_blank">南昌</a>
                                        <a href="#" target="_blank">太原</a>
                                        <a href="#" target="_blank">贵阳</a>
                                        <a href="#" target="_blank">昆明</a>
                                        <a href="#" target="_blank">兰州</a>
                                        <a href="#" target="_blank">济南</a>
                                        <a href="#" target="_blank">郑州</a>
                                        <a href="#" target="_blank">杭州</a>
                                        <a href="#" target="_blank">苏州</a>
                                        <a href="#" target="_blank">福州</a>
                                        <a href="#" target="_blank">南京</a>
                                        <a href="#" target="_blank">无锡</a>
                                        <a href="#" target="_blank">温州</a>
                                        <a href="#" target="_blank">宁波</a>
                                        <a href="#" target="_blank">镇江</a>
                                        <a href="#" target="_blank">合肥</a>
                                        <a href="#" target="_blank">哈尔滨</a>
                                        <a href="#" target="_blank">武汉</a>
                                        <a href="#" target="_blank">青岛</a>
                                        <a href="#" target="_blank">包头</a>
                                        <a href="#" target="_blank">鄂尔多斯</a>
                                        <a href="#" target="_blank">石家庄</a>
                                        <a href="#" target="_blank">呼和浩特</a>
                                        <a href="#" target="_blank">南通</a>
                                        <a href="#" target="_blank">常州</a>
                                        <a href="#" target="_blank">扬州</a>
                                        <a href="#" target="_blank">金华</a>
                                        <a href="#" target="_blank">台州</a>
                                        <a href="#" target="_blank">绍兴</a>
                                        <a href="#" target="_blank" class='bh'>更多地区</a>
                                    </div>
                                </div>
                            </div>
                            <div class="sub_menu_list wideVersion">
                                <div class="sub_menu_line longLine">按价格选蛋糕</div>
                                <div class="sub_menu_con">
                                    <div class="sub_menu_box">
                                        <a href="#" target="_blank">特价蛋糕</a> 
                                        <a href="#" target="_blank">0-150元蛋糕</a> 
                                        <a href="#" target="_blank">150-250元</a> 
                                        <a href="#" target="_blank">250-350元</a> 
                                        <a href="#" target="_blank">350-550元</a> 
                                        <a href="#" target="_blank">550-800元</a> 
                                        <a href="#" target="_blank">800元以上</a> 
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="sub_menu_right">
                        <div class="sub_menu_line rg">促销活动</div>
                        <ul class="sub_menu_rg">
                            <li><a href="#" target="_blank"><img src="/image/bill/b57436dae9954cc4bafebf4a3977799c.jpg" width="200"></a></li>
                            <div class="clear"></div>
                        </ul>
                        <div class="sub_menu_line rinfo">促销信息</div>
                        <ul class="sub_menu_info">
                            <li><a href="#" target="_blank">意大利冰粉红利口酒 119元</a></li>
                            <li><a href="#" target="_blank">百加得绝+可乐味6瓶套 69元</a></li>
                            <li><a href="#" target="_blank">人头马VSOP干邑238元</a></li>
                            <li><a href="#" target="_blank">轩尼诗VSOP 338元</a></li>
                            <li class="sub_lastpro"><a href="#" target="_blank">福斯特庄园窖藏白兰地3年59元</a></li>
                            <div class="clear"></div>
                        </ul>
                    </div>
                </div>
                <div class="menu_eject" style="display: none;">
                    <div class="sub_menu_left">
                        <div class="sub_menu_list wideVersion">
                            <div class="sub_menu_line longLine">按公仔品牌选购</div>
                            <div class="sub_menu_con">
                                <div class="sub_menu_box">
                                    <a href="#" target="_blank">Hello Kitty</a>
                                    <a href="#" target="_blank">韩国Ozland</a>
                                    <a href="#" target="_blank">BestLife抱枕</a>
                                    <a href="#" target="_blank">公仔花束</a>
                                </div>
                            </div>
                        </div>
                        <div class="sub_menu_list wideVersion">
                            <div class="sub_menu_line longLine">按公仔种类选购</div>
                            <div class="sub_menu_con">
                                <div class="sub_menu_box">
                                    <a href="#" target="_blank">公仔熊</a>
                                    <a href="#" target="_blank">公仔猪</a>
                                    <a href="#" target="_blank">公仔狗</a>
                                    <a href="#" target="_blank">公仔兔</a>
                                    <a href="#" target="_blank">公仔抱枕</a>
                                </div>
                            </div>
                        </div>
                        <div class="sub_menu_list wideVersion">
                            <div class="sub_menu_line longLine">按公仔价格选购</div>
                            <div class="sub_menu_con">
                                <div class="sub_menu_box">
                                    <a href="#" target="_blank">80元以下</a>
                                    <a href="#" target="_blank">80-150元</a>
                                    <a href="#" target="_blank">150-300元</a>
                                    <a href="#" target="_blank">300-500元</a>
                                    <a href="#" target="_blank">500元以上</a>
                                </div>
                            </div>
                        </div>
                        <div class="sub_row"></div>
                        <div class="sub_row"></div>
                    </div>
                    <div class="sub_menu_right">
                        <div class="sub_menu_line rinfo">促销信息</div>
                        <ul class="sub_menu_info">
                            <li><a href="#" target="_blank">没有啤酒的世界杯算是NBA么？</a></li>
                            <li><a href="#" target="_blank">营养健康，平民好价。</a></li>
                            <li class="sub_lastpro"><a href="#" target="_blank">送长辈，绿植花卉。</a></li>
                            <div class="clear"></div>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
$(function(){
var nameL = $("input[type=hidden][name=tBhp]");
  //循环取出主类目
  var tahH = $("h3[name=tagH]");
  tahH.each(function(i){	  
      var pathName = $(this).attr("pathName");
      var isZlm = $(this).attr("isZlm");
       var tmpnowactstr = '';
       if(i==0){
           var isHotVals = $(this).attr("ishot");
           var a = '';
           if(isHotVals=="true"){
             a = '<i></i>';
           }
           tmpnowactstr += '<a href="javascript:;">'+pathName+'</a>'+a;
           if(''!=tmpnowactstr)
           {
            jQuery("#_nowactstr"+(parseInt(i)+parseInt(1))).find("h3").html(tmpnowactstr).show();
           }
       }else{
           if(pathName.indexOf('|')>0)
           {
             
             var urlVals = $(this).attr("url");
             var isHotVals = $(this).attr("ishot");
             var a = '';
             if(isHotVals=="true"){
                 a = '<i></i>';
             }
             var urlLists = urlVals.split("|");
             var valList = pathName.split("|");
             for(var j=0;j<valList.length;j++)
             {
                 var vlName = valList[j];
                 var urlPath = urlLists[j];
                 if(j==valList.length-1)
                 {
                     var idName = "tBhp"+j;
                     if(isZlm==1){
                        tmpnowactstr += '<a target="_blank" href='+urlPath+' id = '+idName+'>'+vlName+'</a><span>&gt;</span>'+a;
                     }else{
                        tmpnowactstr += '<a target="_blank" href='+urlPath+' id = '+idName+'>'+vlName+'</a>'+a;
                     } 
                 }else{
                     var idName = "tBhp"+j;
                     tmpnowactstr += '<a target="_blank" href='+urlPath+' id = '+idName+'>'+vlName+'/</a>';
                      
                 }
                 
                 if(''!=tmpnowactstr)
                 {
                    jQuery("#_nowactstr"+(parseInt(i)+parseInt(1))).find("h3").html(tmpnowactstr).show();
                  }
              }	
           }
           else
           {
              var isHotVals = $(this).attr("ishot");
              var a = '';
              if(isHotVals=="true")
              {
                 a = '<i></i>';
              }
              var urlVals = $(this).attr("url");
              var idName = 'tBhp'+i;
              if(isZlm==1){
                tmpnowactstr += '<a target="_blank" href='+urlVals+' id = '+idName+'>'+pathName+'</a><span>&gt;</span>'+a;
              }else{
                tmpnowactstr += '<a target="_blank" href='+urlVals+' id = '+idName+'>'+pathName+'</a>'+a;  
              }
              if(''!=tmpnowactstr)
              {
                jQuery("#_nowactstr"+(parseInt(i)+parseInt(1))).find("h3").html(tmpnowactstr).show();
              }
           }
           
       }
  }); 
});
</script>