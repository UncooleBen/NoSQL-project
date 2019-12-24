<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Steamed Egg数据库</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <link href="resources/bootstrap/css/style.css" rel='stylesheet' type='text/css'/>
    <link href="resources/bootstrap/css/mystyle.css" rel='stylesheet' type='text/css'/>
    <link type="text/css" href="resources/bootstrap/css/3dstyle.css" rel="stylesheet"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!-- Google Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700' rel='stylesheet' type='text/css'>
    <script type="text/javascript" src="resources/bootstrap/js/jquery.mixitup.min.js"></script>
    <script type="text/javascript">
        $(function () {

            var filterList = {

                init: function () {

                    // MixItUp plugin
                    // http://mixitup.io
                    $('#portfoliolist').mixitup({
                        targetSelector: '.portfolio',
                        filterSelector: '.filter',
                        effects: ['fade'],
                        easing: 'snap',
                        // call the hover effect
                        onMixEnd: filterList.hoverEffect()
                    });

                },

                hoverEffect: function () {

                    // Simple parallax effect
                    $('#portfoliolist .portfolio').hover(
                        function () {
                            $(this).find('.label').stop().animate({bottom: 0}, 200, 'easeOutQuad');
                            $(this).find('img').stop().animate({top: -30}, 500, 'easeOutQuad');
                        },
                        function () {
                            $(this).find('.label').stop().animate({bottom: -40}, 200, 'easeInQuad');
                            $(this).find('img').stop().animate({top: 0}, 300, 'easeOutQuad');
                        }
                    );

                }

            };

            // Run the show!
            filterList.init();


        });
    </script>
    <style>
        /* Make the image fully responsive */
        .carousel-inner img {
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>

<div class="header">
    <div class="container">
        <div class="logo">
            <a href="index.jsp"><img src="resources/bootstrap/images/logo.png" alt=""></a>
        </div>
        <span class="menu"></span>
        <div class="navigation">
            <ul class="navig cl-effect-3">
                <li><a href="index">Home</a></li>
                <li><a href="list?page=1">Games</a></li>
                <li><a href="blogs?page=1">Blog</a></li>
                <li><a href="contact">Contact</a></li>
            </ul>
            <form action="search" method="get">
                <div class="btn-group">
                    <select class="form-control" name="field">
                        <option value="name">名称</option>
                        <option value="category">类别</option>
                        <option value="appid">AppID</option>
                    </select>
                </div>

                <div class="search-bar">
                    <input type="text" placeholder="search" required="" name="value" value=""/>
                    <input type="submit" value=""/>
                    <input type="text" name="page" value="1" hidden="true"/>
                </div>
            </form>
            <div class="clearfix"></div>
            <script>
                $("span.menu").click(function () {
                    $(".navigation").slideToggle("slow", function () {
                        // Animation complete.
                    });
                });
            </script>

        </div>
        <div class="clearfix"></div>
    </div>
</div>

<div class="mydiv">
    <div class="a3d"></div>
</div>
<script src="resources/bootstrap/js/index.js"></script>
</body>
</html>