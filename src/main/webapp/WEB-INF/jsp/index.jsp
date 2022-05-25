<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="dictionaries"/>
    <title>Travel agency</title>
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
        .hot-tour{
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

        <h1 class="text-center text-warning mb-3"><fmt:message key="hot.tours"/></h1>

        <div class="container">
            <div class="row">
                <c:forEach var="hotTour" items="${hotTours}">
                    <div class="col-md-4 mx-auto my-5 hot-tour border-warning">
                        <div class="row">
                            <a href="/tour?id=${hotTour.id}" class="text-dark h3 col-md-9">${hotTour.name}</a>
                            <p class="text-center text-muted col-md-3">#${hotTour.id}</p>
                        </div>

                        <div class="text-center my-3">
                            <a href="/tour?id=${hotTour.id}">
                                <img src="images/${hotTour.previewFile}" class="rounded img-fluid img-thumbnail" alt="Responsive image" style="width: 800px;"/>
                            </a>
                        </div>

                        <div class="row">
                            <img src="images/icons/departure.jpg" alt="Responsive image" style="width: 18px; height: 18px; margin-left: 1rem; margin-top: 0.25rem;"/>
                            <p class="text-muted ml-1">${hotTour.date}</p>

                            <img src="images/icons/person.jpg" alt="Responsive image" style="width: 17px; height: 17px; margin-left: 2rem; margin-top: 0.25rem;"/>
                            <p class="text-muted ml-1">${hotTour.personsCount}</p>

                            <img src="images/icons/night.jpg" alt="Responsive image" style="width: 17px; height: 17px; margin-left: 2rem; margin-top: 0.25rem;"/>
                            <p class="text-muted ml-1">${hotTour.nightsCount}</p>

                            <h3 class="text-center text-danger ml-auto mr-3">${hotTour.cost}$</h3>

                        </div>
                        <a class="btn btn-outline-success btn-lg btn-block" href="/" role="button"><fmt:message key="book.it"/></a>
                    </div>
                    &nbsp;
                </c:forEach>

                <c:forEach var="nonHotTour" items="${nonHotTours}">
                    <div class="col-md-4 mx-auto my-5 hot-tour">
                        <div class="row">
                            <a href="/tour?id=${nonHotTour.id}" class="text-dark h3 col-md-9">${nonHotTour.name}</a>
                            <p class="text-center text-muted col-md-3">#${nonHotTour.id}</p>
                        </div>

                        <div class="text-center my-3">
                            <a href="/tour?id=${nonHotTour.id}">
                                <img src="images/${nonHotTour.previewFile}" class="rounded img-fluid img-thumbnail" alt="Responsive image" style="width: 800px;"/>
                            </a>
                        </div>

                        <div class="row">
                            <img src="images/icons/departure.jpg" alt="Responsive image" style="width: 18px; height: 18px; margin-left: 1rem; margin-top: 0.25rem;"/>
                            <p class="text-muted ml-1">${nonHotTour.date}</p>

                            <img src="images/icons/person.jpg" alt="Responsive image" style="width: 17px; height: 17px; margin-left: 2rem; margin-top: 0.25rem;"/>
                            <p class="text-muted ml-1">${nonHotTour.personsCount}</p>

                            <img src="images/icons/night.jpg" alt="Responsive image" style="width: 17px; height: 17px; margin-left: 2rem; margin-top: 0.25rem;"/>
                            <p class="text-muted ml-1">${nonHotTour.nightsCount}</p>

                            <h3 class="text-center ml-auto mr-3">${nonHotTour.cost}$</h3>

                        </div>
                        <a class="btn btn-outline-success btn-lg btn-block" href="/" role="button"><fmt:message key="book.it"/></a>
                    </div>
                    &nbsp;
                </c:forEach>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>