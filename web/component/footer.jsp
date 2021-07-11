<%-- 
    Document   : footer
    Created on : Jun 21, 2021, 2:07:49 PM
    Author     : TamDV
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/main.css" rel="stylesheet" type="text/css"/>
        <link href="css/footer.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

    </head>
    <body>
        <div class="footer">
            <div class="container px-2">
                <div class="link">
                    <a href="">Create by: TamDV</a>
                </div>
                <div class="number">
                    <c:forEach items="${count}" var="number">
                        <p class="number">${number}</p>
                    </c:forEach>
                </div>
            </div>
        </div>
    </body>
</html>
