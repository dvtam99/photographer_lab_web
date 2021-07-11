<%-- 
    Document   : contact
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
        <link href="css/contact.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

    </head>
    <body>
        <%@include file="component/header.jsp" %>
        <div class="container">
            <div class="container-block">
                <div class="left">

                    <p class="error-text">${error}</p>

                    <div class="contact">
                        <div class="contact-title">
                            <p class="p-title">Contact</p>
                            <p class="contact-p-name">PHOTOGRAPHER</p>
                        </div>
                        <div class="contact-info">
                            <table>
                                <tr>
                                    <td class="td-name">Address:</td>
                                    <td class="td-value">${contact.getAddress()}</td>
                                </tr>
                                <tr>
                                    <td class="td-name">City:</td>
                                    <td class="td-value">${contact.getCity()}</td>
                                </tr>
                                <tr>
                                    <td class="td-name">Country:</td>
                                    <td class="td-value">${contact.getCountry()}</td>
                                </tr>
                            </table>
                            <div class="contact-phone-mail">
                                <table>
                                    <tr>
                                        <td class="td-name">Tel:</td>
                                        <td class="td-value"><a href="">${contact.getTelephone()}</a></td>
                                    </tr>
                                    <tr>
                                        <td class="td-name">Email:</td>
                                        <td class="td-value"><a href="">${contact.getEmail()}</a></td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div class="contact-map">
                            ${contact.getMap()}
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="component/right.jsp" %>
        </div>
        <%@include file="component/footer.jsp" %>
    </body>
</html>
