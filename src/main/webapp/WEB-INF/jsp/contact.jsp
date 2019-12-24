<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>关于Steamed Egg数据库</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link href="resources/bootstrap/css/style.css" rel='stylesheet' type='text/css'/>
    <link href="resources/bootstrap/css/mystyle.css" rel='stylesheet' type='text/css'/>
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
    <script src="resources/bootstrap/js/echarts.min.js"></script>
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
        </div>
    </div>
</div>

<div class="games" id="#id_games_container">
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">
                    关于本项目
                </h3>
            </div>
            <div class="panel-body">
                <table>
                    <tr>
                        <td colspan="2">
                            前端
                        </td>
                        <td colspan="2">
                            郭源杰
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            后端
                        </td>
                        <td colspan="2">
                            彭钧涛
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            数据爬取
                        </td>
                        <td colspan="2">
                            李尚真 彭钧涛
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            Email
                        </td>
                        <td colspan="2">
                            pengjuntao2016@foxmail.com
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                        </td>
                        <td colspan="2">
                            798483752@qq.com
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                        </td>
                        <td colspan="2">
                            327183023@qq.com
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>