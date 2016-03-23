<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>H5active</title>
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=0">
    <meta name="format-detection" content="telephone=no">
    <style type="text/css">
        .xiangxiatishi {
            position: fixed;
            bottom: 20px;
            left: 50%;
            margin-left: -20px;
            cursor: pointer;
            -webkit-animation: tiao 1s linear 0s infinite alternate;
            transition: all 3s linear;
            z-index: 150;
        }

        @-webkit-keyframes tiao {
            from {
                bottom: 60px;
                transform: scale(0.6, 0.8);
                /*兼容*/
                -ms-transform: scale(0.6, 0.8); /* IE 9 */
                -moz-transform: scale(0.6, 0.8); /* Firefox */
                -webkit-transform: scale(0.6, 0.8); /* Safari and Chrome */
                -o-transform: scale(0.6, 0.8); /* Opera */
                /*兼容*/
            }
            to {
                bottom: 20px;
                transform: scale(0.9, 0.9);
                /*兼容*/
                -ms-transform: scale(0.9, 0.9); /* IE 9 */
                -moz-transform: scale(0.9, 0.9); /* Firefox */
                -webkit-transform: scale(0.9, 0.9); /* Safari and Chrome */
                -o-transform: scale(0.9, 0.9); /* Opera */
                /*兼容*/
            }
        }

        /*样式开始*/
        body {
            margin: 0;
            padding: 0;
            list-style: none;
            font-size: 16px;
        }

        .item-0 {
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22item-0-bg.png) no-repeat;
            background-size: cover;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            -ms-background-size: cover;

        }

        .item-1 {
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22chou-bg.png) no-repeat;
            background-size: cover;
            -webkit-background-size: cover;

        }

        .item-1 .tishi-0 {
            display: none;
            position: relative;
            width: 16rem;
            height: 3rem;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22tishi-2.png) no-repeat;
            background-size: cover;
            margin: 0.5rem auto;
            margin: 2.5rem auto 0rem;
            -webkit-animation: tishi-0 1.5s infinite linear 0s alternate;
            -webkit-transition: all 1s linear;
            z-index: 10000;

        }

        @-webkit-keyframes tishi-0 {
            from {
                transform: scale(0.9, 0.9);
                /*兼容*/
                -ms-transform: scale(0.9, 0.9); /* IE 9 */
                -moz-transform: scale(0.9, 0.9); /* Firefox */
                -webkit-transform: scale(0.9, 0.9); /* Safari and Chrome */
                -o-transform: scale(0.9, 0.9); /* Opera */
                /*兼容*/
            }
            to {
                transform: scale(1, 1);
                /*兼容*/
                -ms-transform: scale(1, 1); /* IE 9 */
                -moz-transform: scale(1, 1); /* Firefox */
                -webkit-transform: scale(1, 1); /* Safari and Chrome */
                -o-transform: scale(1, 1); /* Opera */
                /*兼容*/
            }
        }

        @-webkit-keyframes tishi {
            0% {
                left: 210px;
                top: 50px;
            }
            20% {
                left: 230px;
                top: 90px;
            }
            40% {
                left: 240px;
                top: 130px;
            }
            60% {
                left: 230px;
                top: 170px;
            }
            80% {
                left: 220px;
                top: 210px;
            }
            100% {
                left: 210px;
                top: 240px;
            }
        }

        .item-2 {
            background: #c6f7e2;

        }

        .item-4 {
            background: #c9f6e2;
        }

        .play .item-2 img {
            opacity: 1;
            width: 100%;
        }

        * {
            padding: 0;
            margin: 0;
            list-style: none;
            box-sizing: border-box;
            -moz-box-sizing: border-box;
            -webkit-box-sizing: border-box;
        }

        .head {
            width: 280px;
            height: 520px;
            position: fixed;
            left: 50%;
            top: 50%;
            margin-top: -260px;
            margin-left: -140px;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22head.png) no-repeat;
            background-size: cover;
            z-index: 1;
        }

        .head .face-chou {
            width: 270px;
            height: 270px;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22face-chou.png) no-repeat;
            background-size: cover;
            position: absolute;
            left: 50%;
            top: 50%;
            margin-top: -230px;
            margin-left: -123px;
            display: block;
        }

        .head .face-mei {
            width: 270px;
            height: 270px;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22face-mei.png) no-repeat;
            background-size: cover;
            position: absolute;
            left: 50%;
            top: 50%;
            margin-top: -230px;
            margin-left: -123px;
            display: none;
        }

        .eye-l-mei {
            width: 267px;
            height: 179px;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22eye-l-mei.png) no-repeat;
            background-size: cover;
            -webkit-background-size: cover;
            position: absolute;
            left: 36px;
            top: 45px;
            display: none;
        }

        .eye-r-mei {
            width: 267px;
            height: 179px;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22eye-r-mei.png) no-repeat;
            background-size: cover;
            -webkit-background-size: cover;
            position: absolute;
            right: -49px;
            top: 45px;
            display: none;
        }

        .eye-l-chou {
            width: 267px;
            height: 179px;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22eye-l-chou.png) no-repeat;
            background-size: cover;
            -webkit-background-size: cover;
            position: absolute;
            left: 11px;
            top: 45px;
            display: block;
        }

        .eye-r-chou {
            width: 267px;
            height: 179px;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22eye-r-chou.png) no-repeat;
            background-size: cover;
            -webkit-background-size: cover;
            position: absolute;
            right: -27px;
            top: 45px;
            display: block;
        }

        .nose-chou {
            width: 200px;
            height: 200px;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22nose-chou.png) no-repeat;
            background-size: cover;
            -webkit-background-size: cover;
            position: absolute;
            margin-top: 97px;
            margin-left: 52px;
            display: block;
        }

        .nose-mei {
            width: 200px;
            height: 200px;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22nose-mei.png) no-repeat;
            background-size: cover;
            -webkit-background-size: cover;
            position: absolute;
            display: none;
            margin-top: 82px;
            margin-left: 61px;
        }

        .mouse-chou {
            width: 500px;
            height: 500px;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22mouse-chou.png) no-repeat;
            background-size: cover;
            -webkit-background-size: cover;
            position: absolute;
            margin-top: 84px;
            margin-left: -81px;
            display: block;
        }

        .mouse-mei {
            width: 200px;
            height: 200px;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22mouse-mei.png) no-repeat;
            background-size: cover;
            -webkit-background-size: cover;
            position: absolute;
            display: none;
            margin-top: 97px;
            margin-left: 53px;
        }

        .one-step，.two-step, .three-step {
            width: 50px;
            height: 50px;
            background: lightseagreen;
            border-radius: 50%;
        }

        .two-step, .three-step, .four-step {
            display: none;
        }

        .qiuqiu {
            width: 3rem;
            height: 3rem;
            position: absolute;
            z-index: 100;
            left: 2rem;
            top: 8rem;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22qiuqiu.png) no-repeat;
            background-size: cover;
            -webkit-background-size: cover;

            transition: all 1s linear;
            animation: qiu-dong 3s infinite 0s linear alternate;
            -webkit-animation: qiu-dong 3s infinite 0s linear alternate;
            -moz-animation: qiu-dong 3s infinite 0s linear alternate;
            -o-animation: qiu-dong 3s infinite 0s linear alternate;
            -ms-animation: qiu-dong 3s infinite 0s linear alternate;
            -webkit-animation: qiu-dong 3s infinite 0s linear alternate;

        }

        .airplane {
            width: 3rem;
            height: 3rem;
            position: absolute;
            z-index: 100;
            right: 2rem;
            top: 5rem;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22plane.png) no-repeat;
            background-size: cover;
            -webkit-background-size: cover;

            -moz-animation: title-dong 3s infinite 0s linear alternate;
            -o-animation: title-dong 3s infinite 0s linear alternate;
            -ms-animation: title-dong 3s infinite 0s linear alternate;
            -webkit-animation: title-dong 3s infinite 0s linear alternate;

            transition: all 1s linear;
            -webkit-transition: all 1s linear;

        }

        /*页面0开始*/
        .item-0 .title-big {
            width: 16rem;
            height: 9.5rem;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22item-0-text.png) no-repeat;
            background-size: cover;
            -webkit-background-size: cover;
            margin: 4.5rem auto 0rem;
            -webkit-animation: rol 1.5s infinite linear 0s alternate;
            -webkit-transition: all 1s linear;
        }

        .kaishiceshi {
            width: 15rem;
            height: 3rem;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22xiangxiahuadong.png) no-repeat;
            -webkit-background-size: cover;
            margin: 12.5rem auto 0rem;
            -webkit-animation: title-dong 2s infinite linear 0s alternate;
            -webkit-transition: all 1s linear;
            position: absolute;
            left: 50%;
            margin-left: -7rem;
            bottom: 3rem;
            border-radius: 50%;

        }

        .item-0 .item-0-body {
            width: 4rem;
            height: 7rem;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22item-0-body.png) no-repeat;
            position: absolute;
            left: 50%;
            margin-left: -5rem;
            top: -7rem;
            -webkit-background-size: cover;
            -webkit-animation: title-dong 3s linear 0s;
            -webkit-transition: all 1s linear;

        }

        /*页面2开始*/

        .body {
            width: 270px;
            height: 500px;
            position: fixed;
            left: 50%;
            top: 50%;
            margin-top: -250px;
            margin-left: -140px;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22body.png) no-repeat;
            background-size: cover;
            z-index: 1;
            display: none;

        }

        .body-meinv {
            width: 100%;
            height: 100%;
            position: fixed;
            left: 0;
            top: 0;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22meinv-bg-1.png) no-repeat;
            background-size: cover;
            z-index: 1;
            display: none;

        }

        .six-step {
            display: none;
        }

        .five-step {
            display: none;
        }

        .pang {
            width: 270px;
            height: 500px;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22pang.png) no-repeat;
            background-size: cover;
            -webkit-background-size: cover;
            position: absolute;
            display: block;
            margin-top: 1px;
            margin-left: -1px;
        }

        .shou {
            width: 280px;
            height: 520px;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22shou.jpg) no-repeat;
            background-size: cover;
            -webkit-background-size: cover;
            position: absolute;
            display: none;
            margin-top: 0px;
            margin-left: -1px;
        }

        @keyframes qiu-dong {
            from {
                transform: rotate(0deg) scale(1, 1);
                /*兼容*/
                -ms-transform: rotate(0deg) scale(1, 1); /* IE 9 */
                -moz-transform: rotate(0deg) scale(1, 1); /* Firefox */
                -webkit-transform: rotate(0deg) scale(1, 1); /* Safari and Chrome */
                -o-transform: rotate(0deg) scale(1, 1); /* Opera */
                /*兼容*/
            }
            to {
                transform: rotate(360deg) scale(0.5, 0.5);
                /*兼容*/
                -ms-transform: rotate(0deg) scale(0.5, 0.5); /* IE 9 */
                -moz-transform: rotate(0deg) scale(0.5, 0.5); /* Firefox */
                -webkit-transform: rotate(0deg) scale(0.5, 0.5); /* Safari and Chrome */
                -o-transform: rotate(0deg) scale(0.5, 0.5); /* Opera */
                /*兼容*/
            }
        }

        @-webkit-keyframes qiu-dong {
            from {
                transform: rotate(0deg) scale(1, 1);
                /*兼容*/
                -ms-transform: rotate(0deg) scale(1, 1); /* IE 9 */
                -moz-transform: rotate(0deg) scale(1, 1); /* Firefox */
                -webkit-transform: rotate(0deg) scale(1, 1); /* Safari and Chrome */
                -o-transform: rotate(0deg) scale(1, 1); /* Opera */
                /*兼容*/

            }
            to {
                transform: rotate(360deg) scale(0.5, 0.5);
                /*兼容*/
                -ms-transform: rotate(0deg) scale(0.5, 0.5); /* IE 9 */
                -moz-transform: rotate(0deg) scale(0.5, 0.5); /* Firefox */
                -webkit-transform: rotate(0deg) scale(0.5, 0.5); /* Safari and Chrome */
                -o-transform: rotate(0deg) scale(0.5, 0.5); /* Opera */
                /*兼容*/
            }
        }

        @-moz-keyframes qiu-dong {
            from {
                transform: rotate(0deg) scale(1, 1);
                /*兼容*/
                -ms-transform: rotate(0deg) scale(1, 1); /* IE 9 */
                -moz-transform: rotate(0deg) scale(1, 1); /* Firefox */
                -webkit-transform: rotate(0deg) scale(1, 1); /* Safari and Chrome */
                -o-transform: rotate(0deg) scale(1, 1); /* Opera */
                /*兼容*/
            }
            to {
                transform: rotate(360deg) scale(0.5, 0.5);
                /*兼容*/
                -ms-transform: rotate(0deg) scale(0.5, 0.5); /* IE 9 */
                -moz-transform: rotate(0deg) scale(0.5, 0.5); /* Firefox */
                -webkit-transform: rotate(0deg) scale(0.5, 0.5); /* Safari and Chrome */
                -o-transform: rotate(0deg) scale(0.5, 0.5); /* Opera */
                /*兼容*/
            }
        }

        @-o-keyframes qiu-dong {
            from {
                transform: rotate(0deg) scale(1, 1);
                /*兼容*/
                -ms-transform: rotate(0deg) scale(1, 1); /* IE 9 */
                -moz-transform: rotate(0deg) scale(1, 1); /* Firefox */
                -webkit-transform: rotate(0deg) scale(1, 1); /* Safari and Chrome */
                -o-transform: rotate(0deg) scale(1, 1); /* Opera */
                /*兼容*/
            }
            to {
                transform: rotate(360deg) scale(0.5, 0.5);
                /*兼容*/
                -ms-transform: rotate(0deg) scale(0.5, 0.5); /* IE 9 */
                -moz-transform: rotate(0deg) scale(0.5, 0.5); /* Firefox */
                -webkit-transform: rotate(0deg) scale(0.5, 0.5); /* Safari and Chrome */
                -o-transform: rotate(0deg) scale(0.5, 0.5); /* Opera */
                /*兼容*/
            }
        }

        @-ms-keyframes qiu-dong {
            from {
                transform: rotate(0deg) scale(1, 1);
                /*兼容*/
                -ms-transform: rotate(0deg) scale(1, 1); /* IE 9 */
                -moz-transform: rotate(0deg) scale(1, 1); /* Firefox */
                -webkit-transform: rotate(0deg) scale(1, 1); /* Safari and Chrome */
                -o-transform: rotate(0deg) scale(1, 1); /* Opera */
                /*兼容*/
            }
            to {
                transform: rotate(360deg) scale(0.5, 0.5);
                /*兼容*/
                -ms-transform: rotate(0deg) scale(0.5, 0.5); /* IE 9 */
                -moz-transform: rotate(0deg) scale(0.5, 0.5); /* Firefox */
                -webkit-transform: rotate(0deg) scale(0.5, 0.5); /* Safari and Chrome */
                -o-transform: rotate(0deg) scale(0.5, 0.5); /* Opera */
                /*兼容*/
            }
        }

        @-webkit-keyframes title-dong {
            from {
                transform: scale(0.9, 0.9);
                /*兼容*/
                -ms-transform: scale(0.9, 0.9); /* IE 9 */
                -moz-transform: scale(0.9, 0.9); /* Firefox */
                -webkit-transform: scale(0.9, 0.9); /* Safari and Chrome */
                -o-transform: scale(0.9, 0.9); /* Opera */
                /*兼容*/
            }
            to {
                transform: scale(1, 1);
                /*兼容*/
                -ms-transform: scale(1, 1); /* IE 9 */
                -moz-transform: scale(1, 1); /* Firefox */
                -webkit-transform: scale(1, 1); /* Safari and Chrome */
                -o-transform: scale(1, 1); /* Opera */
                /*兼容*/
            }
        }

        .item-1 {
            position: relative;
        }

        .button {
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22button-right.png) no-repeat;
            -webkit-background-size: cover;
            width: 60px;
            height: 75px;
            cursor: pointer;
            position: absolute;
            left: 210px;
            top: 240px;
            z-index: 100;
        }

        .one-step {
            display: none;

        }

        .shanshan-one {
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22shanshan.png) no-repeat;
            -webkit-background-size: cover;
            width: 50px;
            height: 50px;
            margin-left: -11px;
            margin-top: -11px;
            z-index: 90;
            animation: shanshan 1.5s linear 0s alternate;
            -webkit-animation: shanshan 1.5s infinite linear 0s alternate;
        }

        @-webkit-keyframes shanshan {
            from {
                transform: scale(1);
                opacity: 1;
                /*兼容*/
                -ms-transform: scale(1); /* IE 9 */
                -moz-transform: scale(1); /* Firefox */
                -webkit-transform: scale(1); /* Safari and Chrome */
                -o-transform: scale(1); /* Opera */
                /*兼容*/
            }
            to {
                transform: scale(0.8);
                opacity: 0.3;
                /*兼容*/
                -ms-transform: scale(0.8); /* IE 9 */
                -moz-transform: scale(0.8); /* Firefox */
                -webkit-transform: scale(0.8); /* Safari and Chrome */
                -o-transform: scale(0.8); /* Opera */
                /*兼容*/
            }
        }

        .item-1 .two-step {
            left: 240px;
            top: 240px;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22button-right.png) no-repeat;
            -webkit-background-size: cover;
            width: 60px;
            height: 75px;
            cursor: pointer;
            position: absolute;
            z-index: 100;
            animation: mofabang 1s linear 0s alternate;
            -webkit-animation: mofabang 1s infinite linear 0s alternate;
        }

        .shanshan-two {
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22shanshan.png) no-repeat;
            -webkit-background-size: cover;
            width: 50px;
            height: 50px;
            margin-left: -11px;
            margin-top: -11px;
            z-index: 90;
            animation: shanshan 1.5s linear 0s alternate;
            -webkit-animation: shanshan 1.5s infinite linear 0s alternate;
            display: none;
        }

        .item-1 .three-step {
            left: 100px;
            top: 260px;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22button-left.png) no-repeat;
            -webkit-background-size: contain;
            width: 60px;
            height: 75px;
            cursor: pointer;
            position: absolute;
            z-index: 100;
            animation: mofabang 1s linear 0s alternate;
            -webkit-animation: mofabang 1s infinite linear 0s alternate;
        }

        .shanshan-three {
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22shanshan.png) no-repeat;
            -webkit-background-size: cover;
            width: 50px;
            height: 50px;
            margin-left: 16px;
            margin-top: -7px;
            z-index: 90;
            animation: shanshan 1.5s linear 0s alternate;
            -webkit-animation: shanshan 1.5s infinite linear 0s alternate;
            display: none;
        }

        .item-1 .four-step {
            left: 100px;
            top: 300px;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22button-left.png) no-repeat;
            background-size: cover;
            animation: mofabang 1s linear 0s alternate;
            -webkit-animation: mofabang 1s infinite linear 0s alternate;
        }

        .shanshan-four {
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22shanshan.png) no-repeat;
            -webkit-background-size: cover;
            width: 50px;
            height: 50px;
            margin-left: 16px;
            margin-top: -7px;
            z-index: 90;
            animation: shanshan 1.5s linear 0s alternate;
            -webkit-animation: shanshan 1.5s infinite linear 0s alternate;
            display: none;
        }

        .item-1 .five-step {
            left: 200px;
            top: 210px;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22button-right.png) no-repeat;
            -webkit-background-size: contain;
            width: 60px;
            height: 75px;
            cursor: pointer;
            position: absolute;
            z-index: 100;
            animation: mofabang 1s linear 0s alternate;
            -webkit-animation: mofabang 1s infinite linear 0s alternate;
        }

        .shanshan-five {
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22shanshan.png) no-repeat;
            -webkit-background-size: cover;
            width: 50px;
            height: 50px;
            margin-left: -11px;
            margin-top: -11px;
            z-index: 90;
            animation: shanshan 1.5s linear 0s alternate;
            -webkit-animation: shanshan 1.5s infinite linear 0s alternate;
            display: none;
        }

        .item-1 .six-step {
            left: 80px;
            top: 370px;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22button-left.png) no-repeat;
            -webkit-background-size: contain;
            width: 60px;
            height: 75px;
            cursor: pointer;
            position: absolute;
            z-index: 100;
            animation: mofabang 1s linear 0s alternate;
            -webkit-animation: mofabang 1s infinite linear 0s alternate;
        }

        .shanshan-six {
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22shanshan.png) no-repeat;
            -webkit-background-size: cover;
            width: 50px;
            height: 50px;
            margin-left: 16px;
            margin-top: -7px;
            z-index: 90;
            animation: shanshan 1.5s linear 0s alternate;
            -webkit-animation: shanshan 1.5s infinite linear 0s alternate;
            display: none;
        }

        /*兼容4*/
        @media only screen and (device-height: 480px) and (-webkit-device-pixel-ratio: 2) {

            .button {
                left: 210px;
                top: 220px;

            }

            .two-step {
                left: 210px;
                top: 180px;

            }

            .three-step {
                left: 81px;
                top: 300px;
            }

        }

        /*兼容6*/
        @media (min-device-width: 375px)and(max-device-width: 667px)and(-webkit-min-device-pixel-ratio: 2) {
            .button {
                left: 230px;
                top: 280px;

            }

            .two-step {
                left: 230px;
                top: 3300px;

            }

            .three-step {
                left: 81px;
                top: 350px;
            }

        }

        /*兼容5*/
        @media (device-height: 568px) and (-webkit-min-device-pixel-ratio: 2) {
            .item-1 .one-step {
                left: 210px;
                top: 230px;

            }

            .item-1 .fuck {

                left: 220px;
                top: 190px;
            }

            .item-1 .three-step {

                left: 70px;
                top: 230px;
            }

            .item-1 .four-step {

                left: 70px;
                top: 250px;

            }
        }

        /*样式结束*/

        /*金星页面*/
        .jinxing {
            width: 15rem;
            height: 15rem;
            margin: 0rem auto 0;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22jinxing.png) no-repeat;
            background-size: cover;
            animation: rol 1s linear 0s alternate;
            -webkit-animation: rol 1s infinite linear 0s alternate;
            transform-origin: bottom center;
        }

        .jinxing-one .prefect {
            width: 10rem;
            height: 5rem;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22wanmei.png) no-repeat;
            background-size: cover;
            margin: 1rem auto 0;
            animation: wanmei 2s linear 0s infinite alternate;
            -webkit-animation: wanmei 2s linear 0s infinite alternate;
            transform-origin: bottom center;
            animation-fill-mode: forwards;
        }

        @-webkit-keyframes wanmei {
            from {
                transform: scale(0.8);
                /*兼容*/
                -ms-transform: scale(0.8); /* IE 9 */
                -moz-transform: scale(0.8); /* Firefox */
                -webkit-transform: scale(0.8); /* Safari and Chrome */
                -o-transform: scale(0.8); /* Opera */
                /*兼容*/
            }
            to {
                transform: scale(1.1);
                /*兼容*/
                -ms-transform: scale(1.1); /* IE 9 */
                -moz-transform: scale(1.1); /* Firefox */
                -webkit-transform: scale(1.1); /* Safari and Chrome */
                -o-transform: scale(1.1); /* Opera */
                /*兼容*/
            }
        }

        .yanzhiai {
            width: 180px;
            height: 38px;
            margin: 1rem auto 0;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22yanzhiai.png) no-repeat;
            background-size: cover;
        }

        .need {
            width: 150px;
            height: 35px;
            margin: 0.5rem auto 0;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22need.png) no-repeat;
            background-size: cover;
            -webkit-animation: need 1s infinite linear 0s alternate;
            -webkit-transition: all 1s linear;
        }

        @-webkit-keyframes need {
            from {
                transform: scale(0.9);
                /*兼容*/
                -ms-transform: scale(0.9); /* IE 9 */
                -moz-transform: scale(0.9); /* Firefox */
                -webkit-transform: scale(0.9); /* Safari and Chrome */
                -o-transform: scale(0.9); /* Opera */
                /*兼容*/
            }
            to {
                transform: scale(1.1);
                /*兼容*/
                -ms-transform: scale(1.1); /* IE 9 */
                -moz-transform: scale(1.1); /* Firefox */
                -webkit-transform: scale(1.1); /* Safari and Chrome */
                -o-transform: scale(1.1); /* Opera */
                /*兼容*/
            }
        }

        @-webkit-keyframes rol {
            from {
                transform: rotate(-20deg);
                /*兼容*/
                -ms-transform: rotate(-20deg); /* IE 9 */
                -moz-transform: rotate(-20deg); /* Firefox */
                -webkit-transform: rotate(-20deg); /* Safari and Chrome */
                -o-transform: rotate(-20deg); /* Opera */
                /*兼容*/
            }
            to {
                transform: rotate(20deg);
                /*兼容*/
                -ms-transform: rotate(20deg); /* IE 9 */
                -moz-transform: rotate(20deg); /* Firefox */
                -webkit-transform: rotate(20deg); /* Safari and Chrome */
                -o-transform: rotate(20deg); /* Opera */
                /*兼容*/
            }
        }

        .jinxing-one {
            display: block;
        }

        .jinxing-two {
            width: 100%;
            height: 100%;
            display: none;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22baoming-2.png) no-repeat;
            background-size: cover;
        }

        .kuailai {
            width: 214px;
            height: 48px;
            margin: 1rem auto 0;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22kuailai.png) no-repeat;
            background-size: cover;
        }

        .line {
            width: 20rem;
            -height: 300px;
            margin: 0rem auto 0.2rem;
            background: #f95355;
            border-radius: 10px;
            padding: 0.5rem 0.5rem 0.5rem;
            color: #fff;
            font-size: 0.9rem;
            text-align: center;
            overflow: hidden;
            position: relative;
        }

        .tianshi {
            width: 130px;
            height: 75px;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22tianshi.png) no-repeat;
            background-size: cover;
            margin: 0 auto;
        }

        .line input {
            width: 12rem;
            height: 2rem;
            border-radius: 25px;
            border: 1px solid #fff;
            background: none;
            padding-left: 10px;
            margin: 1rem 0 0 0.2rem;
            color: #fff;
            font-size: 1rem;
        }

        .code {
            width: 4rem;
            height: 4rem;
            -background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22code.jpg) no-repeat;
            background-size: cover;
            -webkit-background-size: cover;
            float: right;
        }

        .wenzi {
            position: absolute;
            right: 5rem;
            bottom: 1rem;
        }

        .icon {
            width: 20rem;
            margin: 0 auto;
            padding-left: 4rem;
            position: relative;
            font-size: 8px;
            color: #666;
        }

        .icon-img {
            width: 3rem;
            height: 3rem;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22icon.png) no-repeat;
            background-size: cover;
            position: absolute;
            left: 0.5rem;
            top: 0;
        }

        /*美女页面动画开始*/
        .lou {
            width: 16rem;
            height: 27rem;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22lou.png) no-repeat;
            background-size: cover;
            position: absolute;
            left: 50%;
            bottom: 3rem;
            margin-left: -8rem;
            animation: lou 2s linear 0s alternate;
            -webkit-animation: lou 2s infinite linear 0s alternate;
            transform-origin: bottom center;
        }

        @-webkit-keyframes lou {
            from {
                transform: scale(1);
                /*兼容*/
                -ms-transform: scale(1); /* IE 9 */
                -moz-transform: scale(1); /* Firefox */
                -webkit-transform: scale(1); /* Safari and Chrome */
                -o-transform: scale(1); /* Opera */
                /*兼容*/
            }
            to {
                transform: scale(0.95);
                /*兼容*/
                -ms-transform: scale(0.95); /* IE 9 */
                -moz-transform: scale(0.95); /* Firefox */
                -webkit-transform: scale(0.95); /* Safari and Chrome */
                -o-transform: scale(0.95); /* Opera */
                /*兼容*/
            }
        }

        .button-0 {
            width: 11rem;
            height: 3rem;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22button-1.png) no-repeat;
            background-size: cover;
            position: absolute;
            left: 50%;
            margin-left: -5rem;
            top: 2rem;
            z-index: 210;
            display: none;

        }

        .button-00 {
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22button-00.png) no-repeat;
            background-size: cover;
            width: 10rem;
            height: 3rem;
            display: block;
            top: 2rem;

        }

        .button-2 {
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22button-2.png) no-repeat;
            background-size: cover;
            width: 11rem;
            height: 3rem;
        }

        .button-3 {
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22button-3.png) no-repeat;
            background-size: cover;
            width: 11rem;
            height: 3rem;
        }

        .button-4 {
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22button-4.png) no-repeat;
            background-size: cover;
            width: 10rem;
            height: 3rem;
        }

        .button-6 {
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22button-6.png) no-repeat;
            background-size: cover;
            width: 11rem;
            height: 3rem;
            top: auto;
            bottom: 3rem;

        }

        @-webkit-keyframes mofabang {
            from {
                transform: rotate(0deg);
                transform-origin: bottom right;
                /*兼容*/
                -ms-transform: rotate(0deg); /* IE 9 */
                -moz-transform: rotate(0deg); /* Firefox */
                -webkit-transform: rotate(0deg); /* Safari and Chrome */
                -o-transform: rotate(0deg); /* Opera */

                -ms-transform: bottom right; /* IE 9 */
                -moz-transform: bottom right; /* Firefox */
                -webkit-transform: bottom right; /* Safari and Chrome */
                -o-transform: bottom right; /* Opera */
                /*兼容*/
            }
            to {
                transform: rotate(20deg);
                transform-origin: bottom right;
                /*兼容*/
                -ms-transform: rotate(20deg); /* IE 9 */
                -moz-transform: rotate(20deg); /* Firefox */
                -webkit-transform: rotate(20deg); /* Safari and Chrome */
                -o-transform: rotate(20deg); /* Opera */

                -ms-transform: bottom right; /* IE 9 */
                -moz-transform: bottom right; /* Firefox */
                -webkit-transform: bottom right; /* Safari and Chrome */
                -o-transform: bottom right; /* Opera */
                /*兼容*/
            }
        }

        .ufo {
            width: 5rem;
            height: 3.4rem;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22ufo.png) no-repeat;
            background-size: cover;
            position: absolute;
            left: 3rem;
            top: 3rem;
            animation: ufo 2s linear 0s alternate;
            -webkit-animation: ufo 2s infinite linear 0s alternate;
            transform-origin: bottom center;
        }

        @-webkit-keyframes ufo {
            from {
                transform: translateX(0px);
                /*兼容*/
                -ms-transform: translateX(0px); /* IE 9 */
                -moz-transform: translateX(0px); /* Firefox */
                -webkit-transform: translateX(0px); /* Safari and Chrome */
                -o-transform: translateX(0px); /* Opera */
                /*兼容*/
            }
            to {
                transform: translateX(30px);
                /*兼容*/
                -ms-transform: translateX(30px); /* IE 9 */
                -moz-transform: translateX(30px); /* Firefox */
                -webkit-transform: translateX(30px); /* Safari and Chrome */
                -o-transform: translateX(30px); /* Opera */
                /*兼容*/
            }
        }

        .girl {
            width: 16rem;
            height: 27rem;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22girl.png) no-repeat;
            background-size: cover;
            position: absolute;
            left: 50%;
            margin-left: -8rem;
            bottom: 3rem;

        }

        .shandian-0 {
            width: 3rem;
            height: 5rem;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22shandian-1.png) no-repeat;
            background-size: cover;
            position: absolute;
            left: 4rem;
            bottom: 12rem;
            animation: shandian-ziji 0.5s linear 0s alternate;
            -webkit-animation: shandian-ziji 0.5s infinite linear 0s alternate;
        }

        .shandian-02 {
            width: 2rem;
            height: 3rem;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22shandian-2.png) no-repeat;
            background-size: cover;
            position: absolute;
            left: 11rem;
            top: 17rem;

            animation: shandian-ziji 2s linear 0s alternate;
            -webkit-animation: shandian-ziji 2s infinite linear 0s alternate;
        }

        .shandian-03 {
            width: 3rem;
            height: 5rem;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22shandian-3.png) no-repeat;
            background-size: cover;
            position: absolute;
            left: 12rem;
            top: 22rem;
            animation: shandian-ziji 0.6s linear 0s alternate;
            -webkit-animation: shandian-ziji 0.6s infinite linear 0s alternate;
        }

        @-webkit-keyframes shandian-ziji {
            from {
                opacity: 1;
            }
            to {
                opacity: 0.3;
            }
        }

        /*美女页面动画结束*/

        /*魔法棒开始*/
        @-webkit-keyframes shandian-0 {
            0% {
                top: 50px;
                left: 210px;
                transform: scale(0.1);
            }
            20% {
                top: 90px;
                left: 230px;
                transform: scale(0.1);
            }
            40% {
                top: 130px;
                left: 240px;
                transform: scale(0.1);
            }
            60% {
                top: 170px;
                left: 230px;
                transform: scale(0.1);
            }
            80% {
                top: 210px;
                left: 220px;
                transform: scale(0.1);
            }
            100% {
                top: 230px;
                left: 210px;
                transform: scale(0.1);
            }
        }

        /*魔法棒结束*/

        .line .tijiao {
            width: 5rem;
            line-height: 2rem;
            background: #fff;
            color: #f95355;
            text-align: center;
            padding: 0;
        }

        .chenggong {
            width: 100%;
            height: 100%;
            position: fixed;
            top: 0;
            left: 0;
            background: rgba(0, 0, 0, 0.6);
            z-index: 200;
            display: none;
        }

        .button-chenggong {
            width: 12rem;
            height: 3rem;
            background: url(http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22chenggong.png) no-repeat;
            background-size: cover;
            text-align: center;
            left: 50%;
            top: 50%;
            margin-top: -3.5rem;
            margin-left: -5.5rem;
            position: absolute;

        }
    </style>
    <script src="/js/activity/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="/js/activity/iSlider.js"></script>

</head>

<body>
<div class="music-1">
    <audio src="http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22music-chou.mp3" id="audio-1"
           autoplay="autoplay"></audio>
</div>

<div class="music">
    <audio src="http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22music-beautiful.mp3" id="audio-2" loop="loop"></audio>
</div>
<div class="wrap">

    <div class="item item-0">
        <!--页面0-->
        <!--音乐开始-->

        <!--音乐结束-->
        <div class="title-big">
        </div>

        <div class="kaishiceshi" index="0" onclick="kaishiceshi(this)">
            <div class="item-0-body">

            </div>
        </div>
        <img class="xiangxiatishi" src="http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22arrow.png" height="36"
             width="54"/>

    </div>
    <div class="item item-1">
        <!--页面1-->
        <!--音乐开始-->
        <!--<div class="music-1">
            <audio src="music-chou.mp3" loop="loop" ></audio>
        </div>-->
        <!--音乐结束-->
        <a href="javascript:void(0)" class="tishi-0" onclick="tishi()">

        </a>
        <div class="head">
            <div class="face-chou">
            </div>
            <div class="face-mei">
            </div>
            <div class="eye-l-chou">
            </div>
            <div class="eye-r-chou">
            </div>
            <div class="eye-l-mei">
            </div>
            <div class="eye-r-mei">
            </div>

            <div class="nose-chou">
            </div>
            <div class="nose-mei">
            </div>

            <div class="mouse-chou">
            </div>
            <div class="mouse-mei">
            </div>
        </div>

        <div class="one-step button button-right" id="one-step">
            <div class="shanshan-one">
            </div>
        </div>


        <div class="two-step button fuck  button-right">
            <div class="shanshan-two">
            </div>
        </div>


        <div class="three-step button  button-left">
            <div class="shanshan-three">
            </div>
        </div>


        <div class="four-step button   button-left">
            <div class="shanshan-four">
            </div>
        </div>


        <div class="qiuqiu">
        </div>
        <div class="qiuqiu" style="opacity: 0.5; left: 80px; top:200px; width: 2rem; height: 2rem;">
        </div>
        <div class="qiuqiu" style="opacity: 0.8; left: 250px; top:400px; width: 2rem; height: 2rem;">
        </div>
        <div class="qiuqiu" style="opacity: 0.3; left: 50px; top:450px; width: 2rem; height: 2rem;">
        </div>
        <div class="airplane">
        </div>


        <!--身体开始-->
        <div class="body">
            <div class="pang">
            </div>

        </div>
        <div class="body-meinv">
            <div class="ufo">
            </div>
            <div class="lou">
            </div>
            <div class="girl">
            </div>
            <div class="shandian-0 shandian-01">
            </div>
            <div class="shandian-0 shandian-02">
            </div>
            <div class="shandian-0 shandian-03">
            </div>
            <!--音乐开始-->

            <!--音乐结束-->
        </div>

        <div class="six-step  button   button-left">
            <div class="shanshan-six">
            </div>
        </div>


        <div class="five-step  button   button-left five-step-2">
            <div class="shanshan-five">
            </div>
        </div>

        <div class="button-00 button-0">

        </div>
        <div class="button-1 button-0">
        </div>
        <div class="button-2 button-0">
        </div>
        <div class="button-3 button-0">
        </div>
        <div class="button-4 button-0">
        </div>

        <div class="button-6 button-0">
        </div>
        <!--身体结束-->


    </div>

    <div class="item item-2">

        <div class="jinxing-one">
            <div class="prefect">

            </div>
            <div class="jinxing">
            </div>
            <div class="yanzhiai">
            </div>
            <div class="need" onclick="ttt()">

            </div>
        </div>
        <div class="jinxing-two">


        </div>
        <img class="xiangxiatishi" src="http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22arrow.png" height="36"
             width="54"/>
    </div>
    <div class="item item-4">
        <!--页面3-->

        <div class="kuailai">
        </div>
        <div class="line">
            <div class="tianshi">
            </div>
            <p>姓名 <input type="text" id="name" maxlength="10"/></p>
            <p>手机 <input type="text" id="mobile" maxlength="11"/></p>
            <p>微信 <input type="text" id="wechat"/></p>
            <input type="button" value="提交" class="submit" id="submit"/>
            <div>
            </div>
            <img src="http://7xlcaq.com2.z0.glb.qiniucdn.com/2016-03-22code.jpg" alt="" class="code"/>
            <div class="wenzi">
                扫小美二维码快速报名
            </div>
        </div>
        <div class="icon">
            <div class="icon-img">

            </div>
            <p>北京美丽分期信息技术有限公司</p>
            <p>电话：010-64399899</p>
            <p>地址：北京市朝阳区望京北路9号,叶青大厦C座202室</p>
        </div>
        <div class="chenggong">
            <div class="button-chenggong">

            </div>
        </div>
    </div>

</div>
<script type="text/javascript">
    $(document).ready(function () {
        new iSlider()
        var tishi = false;
        setInterval(function () {
            //console.log('11')

            if ($('.item-1').is('.play')) {
                //console.log('haole')
                if (tishi == false) {
                    setTimeout(function () {
                        $('.button-00').hide(1000)
                        $('.tishi-0').css({
                            'display': 'block'
                        })

                    }, 2000)
                    tishi = true;
                }
            }
        }, 1000);
        $('.one-step').click(function (event) {
            //alert('变脸');
            $('.one-step').hide(1000);
            $('.face-chou').hide(1000);
            $('.shanshan-one').hide(1000);
            $('.button-00').hide(1000);
            $('.face-mei').show(100);
            $('.two-step').show(100);
            $('.shanshan-two').show(100);
            $('.button-1').show(100);

        })

        $('.two-step').click(function (event) {
            //alert('眼睛')
            $('.two-step').hide(100);
            $('.eye-l-chou').hide(100);
            $('.eye-r-chou').hide(100);
            $('.shanshan-two').hide(1000);
            $('.button-1').hide(100);
            $('.eye-l-mei').show(100);
            $('.eye-r-mei').show(100);
            $('.three-step').show(100);
            $('.shanshan-three').show(100);
            $('.button-2').show(100);
        })

        $('.three-step').click(function (event) {
            //alert('鼻子')
            $('.three-step').hide(100);
            $('.nose-chou').hide(100);
            $('.shanshan-three').hide(1000);
            $('.button-2').hide(100);
            $('.nose-mei').show(100);
            $('.four-step').show(100);
            $('.shanshan-four').show(100);
            $('.button-3').show(100);
        })
        $('.four-step').click(function (event) {
            //alert('嘴巴')
            $('.four-step').hide(100);
            $('.mouse-chou').hide(100);
            $('.shanshan-four').hide(1000);
            $('.button-3').hide(100);
            $('.mouse-mei').show(100);
            $('.six-step').show(100);
            $('.shanshan-six').show(100);
            $('.button-4').show(100);
        })

        $('.six-step').click(function (event) {
            //alert('嘴巴')
            $('.head').hide(100);
            $('.six-step').hide(100);
            $('.shanshan-six').hide(1000);
            $('.button-4').hide(100);
            $('.body').show(100);
            $('.five-step').css({'display': 'block'});
            $('.shanshan-five').show(100);
            /*$('.button-5').show(100);*/
        })
        $('.five-step').click(function (event) {
            //alert('身体')

            $('.five-step').hide();
            $('.shanshan-five').hide(1000);
            $('.body').hide();
            $('.body-meinv').show();
            $('.button-6').show(100);
            /*$("#audio-1").get(0).pause()*/
            /*$("#audio-2").get(0).play()*/
        })


    });

    function ttt() {
        $('.jinxing-one').hide();
        $('.jinxing-two').show();
    }

    var distanceZero = 0;
    var distanceOne = 0;
    var distanceTwo = 0;
    var distanceThree = 0;


    function tishi() {
        $('.tishi-0').hide();
        $(".one-step").show();
        $(".one-step").css({
            "-webkit-animation": "tishi 2s linear 0s"
        })
    }

</script>


<script>
    window.onload = function () {
        var tt = document.querySelector('#one-step');
        if(tt){
            tt.addEventListener("webkitAnimationEnd", function () { //动画结束时事件
                console.log('hh')
                tt.style.animation = "mofabang 1s infinite linear 0s alternate"
            }, false)
        }
    };

    $(document).on("click",".submit",function(){
        $.ajax({
            url: "/activity/yanzhiai/baoming",
            data: {
                name: $("#name").val().trim(),
                mobile: $("#mobile").val().trim(),
                wechat: $("#wechat").val().trim()
            },
            type: "post",
            dataType: "json",
            success: function (data) {
                if(data.code!=0){
                    alert(data.msg);
                    return false;
                }
                $('.chenggong').css({'display': 'block'})

            }
        })
    })



</script>

</body>
</html>