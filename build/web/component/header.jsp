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
        <link href="css/main.css" rel="stylesheet" type="text/css"/>
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="js/menu.js"></script>

    </head>
    <body>
        <div class="header">
            <div class="menu" >
                <div class="container">
                    <div class="topnav" id="myTopnav">

                        <a href="home" <c:if test="${curretItemMenu==1}"> class="current"</c:if>>My front page</a>

                        <c:forEach items="${menuGallery}" var="menu">

                            <a href="gallery?id=${menu.getId()}" <c:if test="${gallery==menu.getId()}"> class="current"</c:if>>${menu.getName()}</a>

                        </c:forEach>

                        <a href="contact" <c:if test="${curretItemMenu==2}"> class="current"</c:if>>Contact</a>

                        <a href="javascript:void(0);" class="icon" onclick="myFunction()">
                            <i class="fa fa-bars"></i>
                        </a>

                    </div>
                </div>
                <div class="title px-2">
                    <div class="container title-container">
                        <a href="home" class="img-link">  </a>
                        <div class="content-title ">
                            <a class="title-link" href="home">PHOTOGRAPHER</a>
                            <p class="title-wellcome">Welcome to this website</p>
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </body>
</html>
