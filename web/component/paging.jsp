<%-- 
    Document   : header
    Created on : Jun 21, 2021, 2:07:49 PM
    Author     : TamDV
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="description" content="PHOTOGRAPHER - http://us-123photo.simplesite.com/"/>
        <link rel="stylesheet" type="text/css" href="//css.simplesite.com/dd/05/8596505.design.v26374.css?h=a6d8c669238417a06cc9d9c415d1a19954122061e30fdd85ce05619d78ac8286"/>
        <link rel="stylesheet" type="text/css" href="//css.simplesite.com/d/1624605012/designs/base/base.css"/>
        <link rel="stylesheet" type="text/css" href="/d/designs/base/rowsconcept.css"/>
        <link rel="stylesheet" type="text/css" href="/d/designs/base/somelinks.css"/>
        <link rel="stylesheet" type="text/css" href="/d/designs/base/footerlayout5.css"/>
        <link rel="stylesheet" type="text/css" href="/Content/fontawesome-all.css"/>
        <link rel="canonical" href="http://us-123photo.simplesite.com/410915248"/>
        <link rel="icon" sizes="194x194" href="/favicon-194x194.png">
        <link rel="stylesheet" type="text/css" href="/c/css/ionicons/ionicons.min.css"/>
        <script type="text/javascript" src="/userPages/pages/FrontendAppLocalePage.aspx?CultureKey=en-US"></script>
    </head>
    <body>
        <div class="container-fluid header-wrapper " id="header">
            <!-- this is the Header Wrapper -->
            <div class="container">
                <div class="navbar navbar-compact">
                    <div class="navbar-inner">
                        <div class="container">
                            <!-- .btn-navbar is used as the toggle for collapsed navbar content -->
                            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse" title="Toggle menu">
                                <span class="menu-name">Menu</span>
                            </a>
                            <!-- Everything you want hidden at 940px or less, place within here -->
                            <div class="nav-collapse collapse">
                                <ul class="nav" id="topMenu" data-submenu="horizontal">
                                    <li>
                                        <a href="home" <c:if test="${curretItemMenu==1}">class="current"</c:if>>My front page</a>
                                        </li>
                                    <c:forEach items="${menuGallery}" var="menu">
                                        <li>
                                            <a href="gallery?id=${menu.getId()}" <c:if test="${gallery==menu.getId()}">class="current"</c:if>>${menu.getName()}</a>
                                            </li>
                                    </c:forEach>
                                    <li>
                                        <a href="contact" <c:if test="${curretItemMenu==2}">class="current"</c:if>>Contact</a>
                                    </li>

                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- this is the Menu content -->
                <div class="title-wrapper">
                    <div class="title-wrapper-inner">
                        <a class="logo " href="http://us-123photo.simplesite.com/"></a>
                        <div class="title ">
                            <a class="title  title-link" href="http://us-123photo.simplesite.com/">PHOTOGRAPHER
                            </a>
                        </div>
                        <div class="subtitle">Welcome to this website
                        </div>
                    </div>
                </div>
                <!-- these are the titles -->
            </div>
        </div>
    </body>
</html>
