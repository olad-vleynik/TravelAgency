<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Oops...</title>
    <style>
        h1,h2,h3{
            font-family: 'Montserrat Alternates', sans-serif;
        }
        .centered {
            position: absolute;
            top: 50%;
            left: 50%;
            width: 900px;
            height:500px;
            margin: -350px 0 0 -450px;
        }
    </style>
</head>
<body>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="dictionaries"/>
    <div class="centered">
        <img style="widht:528px; height:354px; margin-left: 186px" src="../../../images/oops.jpg">
        <h2 style="text-align:center">
            <fmt:message key="no.access"/>
        </h2>
        <div style="text-align:center">
            <a href="/"><fmt:message key="home"/></a>
        </div>
    </div>
</body>
</html>