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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

    </head>
    <body>

        <div class="div-page">
            <c:if test="${numberPage > 1}">
                <c:forEach begin="1" end="${numberPage}" var="page">
                    <c:if test="${currentPage == page}">
                        <a class="link-disabled"
                           href="home?page=${page}">${page}</a>
                    </c:if>
                    <c:if test="${currentPage != page}">
                        <a class="" href="home?page=${page}">${page}</a>
                    </c:if>
                </c:forEach>
            </c:if>

        </div>
    </body>
</html>
