<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${app.name} - Steamed Egg数据库</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link href="resources/bootstrap/css/style.css" rel='stylesheet' type='text/css'/>
    <link href="resources/bootstrap/css/mystyle.css" rel='stylesheet' type='text/css'/>
    <script type="application/x-javascript">
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);
        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>
    <link href='http://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700' rel='stylesheet' type='text/css'>
    <script type="text/javascript" src="resources/moment/moment.js"></script>
    <script type="text/javascript">
        function convertDate(millisecond, id) {
            date = new Date(millisecond);
            document.getElementById(id).innerText = moment(date).format("YYYY年MM月DD日");
        }
    </script>
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
                <li><a href="contact">Contact</a></li>
            </ul>
            <div class="search-bar">
                <input type="text" placeholder="search" required="" value="search"/>
                <input type="submit" value=""/>
            </div>
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

<div class="games" id="#id_games_container">>

    <div id="name" class="gameName">${app.name}</div>

    <div id="demo" class="carousel slide" data-ride="carousel">

        <!-- 指示符 -->
        <ul class="carousel-indicators">
            <li data-target="#demo" data-slide-to="0" class="active"></li>
            <li data-target="#demo" data-slide-to="1"></li>
            <li data-target="#demo" data-slide-to="2"></li>
        </ul>

        <!-- 轮播图片 -->
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src=${app.screenshots[0]}>
            </div>
            <c:forEach begin="1" var="screenshot" items="${app.screenshots}">
                <div class="carousel-item">
                    <img src=${screenshot}>
                </div>
            </c:forEach>
        </div>

        <!-- 左右切换按钮 -->
        <a class="carousel-control-prev" href="#demo" data-slide="prev">
            <span class="carousel-control-prev-icon"></span>
        </a>
        <a class="carousel-control-next" href="#demo" data-slide="next">
            <span class="carousel-control-next-icon"></span>
        </a>

    </div>

    <div class="gamedes">
        <div class="row">
            <div class="col-sm-4" style="background-color:lavender;">
                开发商
            </div>
            <c:forEach var="developer" items="${app.developers}">
                <div class="col-sm-8" style="background-color:lavenderblush;">
                        ${developer}
                </div>
            </c:forEach>
        </div>
        <div class="row">
            <div class="col-sm-4" style="background-color:lavender;">
                发行商
            </div>
            <c:forEach var="publisher" items="${app.publishers}">
                <div class="col-sm-8" style="background-color:lavenderblush;">
                        ${publisher}
                </div>
            </c:forEach>
        </div>
        <div class="row">
            <div class="col-sm-4" style="background-color:lavender;">
                发行日期
            </div>
            <div class="col-sm-8" id="release_date" style="background-color:lavenderblush;">
                <script>convertDate(${app.releaseDate}, "release_date")</script>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-4" style="background-color:lavender;">
                Steam App ID
            </div>
            <div class="col-sm-8" style="background-color:lavenderblush;">
                ${app.steamAppId}
            </div>
        </div>
    </div>

    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">
                    游戏描述
                </h3>
            </div>
            <div class="panel-body">
                ${app.detailedDescription}
            </div>
        </div>
    </div>
    <div class="container">
        <h3 class="page-header">游戏标签</h3>
        <ul class="tags_links">
            <c:forEach var="category" items="${app.categories}">
                <li><a href="#">${category}</a></li>
            </c:forEach>
        </ul>
    </div>

    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div class="panel-body" id="echart" style="background-color:white; height:400px;"></div>
    <script type="text/javascript">
        var id_games_container = document.getElementById('#id_games_container');
        var url = 'url(';
        url += '${app.backgroundURL}';
        url += ')';
        id_games_container.style.backgroundImage = url;
        id_games_container.style.backgroundAttachment = 'fixed';
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('echart'));
        $.ajax({
            url : 'chart',
            type: 'GET',
            data : { 'appId' : ${app.steamAppId} },
            success : function(jsonStr) {
                // 指定图表的配置项和数据
                // 使用刚指定的配置项和数据显示图表。
                if (jsonStr == null || jsonStr=="") {
                    document.getElementById('echart').style.visibility = 'hidden';
                    return;
                }
                var json = JSON.parse(jsonStr);
                var option = {
                    xAxis: {
                        type: 'category',
                        data: json["xAxis"]["data"]
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: json["series"][0]["data"],
                        type: 'line',
                        step: 'middle'
                    }]
                };
                myChart.setOption(option);
            }
        });

    </script>
</div>
</body>
</html>