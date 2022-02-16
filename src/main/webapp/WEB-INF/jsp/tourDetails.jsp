<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="dictionaries"/>
    <title><fmt:message key="tour.number"/></title>

    <style>
        body{
            padding-bottom:4.2rem;
        }
        a{
            text-decoration:none !important;
        }
        h1,h2,h3{
            font-family: 'Montserrat Alternates', sans-serif;
        }
        .tour-container{
            position: relative;
            display: -ms-flexbox;
            display: flex;
            padding: 1rem;
            -ms-flex-direction: column;
            flex-direction: column;
            width: 100%;
            pointer-events: auto;
            background-color: #fff;
            background-clip: padding-box;
            border: 1px solid rgba(0,0,0,.2);
            border-radius: 1.1rem;
            outline: 0;
            max-width: 1200px;
            margin-top: -0.25rem
        }
    </style>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>

    <jsp:include page="/WEB-INF/jsp/header.jsp" />

    <div class="container tour-container mt-5 col-7">
        <div class="row">
            <div class="col-md-12 mx-auto">
                <div class="logo mb-3">
                    <div class="text-center">
                        <h1>${tour.name}</h1>
                    </div>
                </div>
                <div class="text-center mb-3">
                    <img src="${tourImagePath}" class="rounded img-fluid img-thumbnail" alt="Responsive image" style="width: 800px;"/>
                </div>
                <div class="row mx-2 mb-4">
                    <div class="col">
                        <div class="text-center mb-1">
                            <h2>HOT</h2>
                        </div>
                        <div class="text-center">
                            <table class="table">
                                <tbody>
                                <tr>
                                    <th scope="row">Country</th>
                                    <td>${tour.country}</td>
                                </tr>
                                <tr>
                                    <th scope="row">Type</th>
                                    <td>Relax</td>
                                </tr>
                                <tr>
                                    <th scope="row">Departure</th>
                                    <td>28 feb 2022</td>
                                </tr>
                                <tr>
                                    <th scope="row">Transfer</th>
                                    <td>Airplane</td>
                                </tr>
                                <tr>
                                    <th scope="row">Nights</th>
                                    <td>3</td>
                                </tr>
                                <tr>
                                    <th scope="row">Hotel name</th>
                                    <td>Akvakva</td>
                                </tr>
                                <tr>
                                    <th scope="row">Hotel rating</th>
                                    <td>3</td>
                                </tr>
                                <tr>
                                    <th scope="row">State</th>
                                    <td>Available</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-7 ml-auto">
                        <div class="text-center mb-1">
                            <h2>#1</h2>
                        </div>
                        <div class="text-center">
                            Бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла текст
                        </div>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="text-right mr-3 ml-auto">
                        <h3>Cost: 300$</h3>
                    </div>
                    <div class="text-center mr-3">
                        <button type="submit" class="btn btn-success">Book it!</button>
                    </div>
                    <div class="text-center mr-3">
                        <a class="btn btn-outline-success" href="/" role="button">Back to catalog</a>
                    </div>
                </div>
            </div>
        </div>
    </div>


</body>
</html>
