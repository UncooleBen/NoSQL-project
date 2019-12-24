<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Blogs - Steamed Egg数据库</title>
    <link href="resources/bootstrap/css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="resources/bootstrap/js/jquery-1.11.0.min.js"></script>
    <!-- Custom Theme files -->
    <link href="resources/bootstrap/css/style.css" rel='stylesheet' type='text/css'/>
    <!-- Custom Theme files -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!-- Google Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700' rel='stylesheet' type='text/css'>
    <script src="resources/bootstrap/moment/moment.js"></script>
</head>
<body>
<!-- Header Starts Here -->
<div class="header">
    <div class="container">
        <div class="logo">
            <a href="index.html"><img src="resources/bootstrap/images/logo.png" alt=""></a>
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
            <script>
                $("span.menu").click(function () {
                    $(".navigation").slideToggle("slow", function () {
                        // Animation complete.
                    });
                });
            </script>
            <div class="clearfix"></div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<!-- Header Ends Here -->
<!-- Games Page Starts here -->
<script type="text/javascript">
    function gotoBlog(id) {
        window.location = 'blogDetail?id=' + id;
    }

    function convertDate(millisecond, id) {
        date = new Date(millisecond);
        document.getElementById(id).innerText = moment(date).format("YYYY/MM/DD");
    }
</script>
<div class="games">
    <div class="container">
        <div class="page-path">
            <div class="clearfix"></div>
        </div>
        <h3 class="page-header">
            Recent Blogs
        </h3>
        <div class="blog-content">
            <c:forEach var="blog" items="${blogList}">
                <div class="blog-post">
                    <h3><a href="javascript:gotoBlog(${blog.id})">${blog.title}</a></h3>
                    <div class="post-details">
                        <p>Posted By <a href="#">${blog.author}</a> &nbsp;&nbsp; On <span id="#blog_'${blog.id}'_date"><script>convertDate(${blog.date}, "#blog_'${blog.id}'_date")</script></span>
                            &nbsp;&nbsp;
                        </p>
                    </div>
                    <h4>${blog.subtitle}</h4>
                    <p>${blog.content}</p>
                    <nav class="cl-effect-3"><a href="javascript:gotoBlog(${blog.id})">More</a></nav>
                </div>
            </c:forEach>
        </div>
        <div class="blog-sidebar">
            <h3 class="page-header">Catgeories</h3>
            <ul class="product-categories color">
                <li class="cat-item cat-item-42"><a href="#">Shooting</a> <span class="count">(14)</span></li>
                <li class="cat-item cat-item-60"><a href="#">Action</a> <span class="count">(2)</span></li>
                <li class="cat-item cat-item-63"><a href="#">Gloves</a> <span class="count">(2)</span></li>
                <li class="cat-item cat-item-54"><a href="#">Cars</a> <span class="count">(8)</span></li>
                <li class="cat-item cat-item-55"><a href="#">Racing</a> <span class="count">(11)</span></li>
                <li class="cat-item cat-item-64"><a href="#">Sports</a> <span class="count">(3)</span></li>
                <li class="cat-item cat-item-61"><a href="#">Style</a> <span class="count">(3)</span></li>
                <li class="cat-item cat-item-56"><a href="#">Bikes</a> <span class="count">(6)</span></li>
                <li class="cat-item cat-item-57"><a href="#">Zombies</a> <span class="count">(13)</span></li>
                <li class="cat-item cat-item-58"><a href="#">Wressling</a> <span class="count">(7)</span></li>
                <li class="cat-item cat-item-62"><a href="#">Watchers</a> <span class="count">(2)</span></li>
                <li class="cat-item cat-item-41"><a href="#">Women</a> <span class="count">(17)</span></li>
            </ul>
            <h3 class="page-header">Popular Tags</h3>
            <ul class="tags_links">
                <li><a href="#">FPS</a></li>
                <li><a href="#">Dota2</a></li>
                <li><a href="#">League</a></li>
                <li><a href="#">Iceborne</a></li>
                <li><a href="#">Hardware</a></li>
                <li><a href="#">Display</a></li>
                <li><a href="#">Workshop</a></li>
                <li><a href="#">Steam</a></li>
            </ul>

        </div>
        <div class="clearfix"></div>

    </div>
</div>
<!-- What New Part Endss Here -->

</body>
</html>