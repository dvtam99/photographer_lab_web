<%-- 
    Document   : gallery
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
        <link href="css/gallery.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="js/showSlides.js"></script>

    </head>
    <body>
        <%@include file="component/header.jsp" %>
        <div class="container main-container ">
            <div class="container-block px-4">
                <div class="left">

                    <p class="error-text">${error}</p>

                    <div class="gallery-title">
                        <p class="p-title">${galleryInfo.getName()}</p>

                        <div class="slideshow-container">
                            <div class="cover">
                                <button>
                                    play
                                </button>
                            </div>
                            <div class="mySlides fade" style="display: block">
                                <img src="${galleryInfo.getPicture()}" style="width:100%">
                            </div>
                            <c:forEach items="${up}" var="img">
                                <div class="mySlides fade">
                                    <img src="${img}" style="width:100%">
                                </div>
                            </c:forEach>


                        </div>


                        <script>
                            showSlides();
                        </script>

                        <div class="gallery-content">
                            <div class="contain-gallery-img">
                                <c:forEach items="${up}" var="img">
                                    <a class="" href="gallery?id=${galleryInfo.getId()}&page=${pagination.getCurrentPage()}&img=${img}">
                                        <div class="gallery-background-img">
                                            <img src="${img}" alt=""/>
                                        </div>
                                    </a>
                                </c:forEach>
                            </div>
                            <div>
                                <c:forEach items="${down}" var="img">
                                    <a class="" href="gallery?id=${galleryInfo.getId()}&page=${pagination.getCurrentPage()}&img=${img}">
                                        <div class="gallery-background-img">
                                            <img src="${img}" alt=""/>
                                        </div>
                                    </a>
                                </c:forEach>
                            </div>

                            <div class="div-page">

                                <c:if test="${numberPage > 1}">
                                    <c:forEach begin="1" end="${numberPage}" var="page">
                                        <c:if test="${currentPage == page}">
                                            <a class="link-disabled"
                                               href="gallery?id=${galleryInfo.getId()}&page=${page}&img=${galleryInfo.getPicture()}">${page}</a>
                                        </c:if>
                                        <c:if test="${currentPage != page}">
                                            <a class="" href="gallery?id=${galleryInfo.getId()}&page=${page}&img=${galleryInfo.getPicture()}">${page}</a>

                                        </c:if>
                                    </c:forEach>
                                </c:if>

                            </div>


                        </div>
                    </div>
                </div>

            </div>
            <%@include file="component/right.jsp" %>
        </div>
        <%@include file="component/footer.jsp" %>
    </body>
</html>
