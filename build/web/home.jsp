<%-- 
    Document   : home
    Created on : Jun 21, 2021, 3:23:04 PM
    Author     : TamDV
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/main.css" rel="stylesheet" type="text/css"/>
        <link rel="shortcut icon" type="image/png" href="img/camera_icon.png"/>
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <%@include file="component/header.jsp" %>
        <div class="container main-container ">
            <div class="container-block px-4">

                <div class="left">
                    <p class="error-text">${error}</p>
                    <!--                    <div class="contain-big-img">-->
                    <img src="${description.getPicture()}" alt=""/>
                    <!--</div>-->
                    <p class="p-italic">${description.getItalicText()}</p>

                    <div class="contain-gallery">
                        <c:forEach items="${list}" var="galleryInfo">
                            <div class="home-content">
                                <div class="contain-img-gallery">
                                    <img src="${galleryInfo.getPicture()}" alt=""/>
                                </div>
                                <a href="gallery?id=${galleryInfo.getId()}">View ${galleryInfo.getName()}</a>
                                <div class="home-description">
                                    <p>
                                        ${galleryInfo.getText()}
                                    </p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <%@include file="component/paging.jsp" %>

                    <div class="home-about">
                        <p class="p-about-title">About me</p>
                        <p>
                            ${description.getText()}
                        </p>
                    </div>
                </div>
            </div>
            <%@include file="component/right.jsp" %>
        </div>
        <%@include file="component/footer.jsp" %>
    </body>
</html>
