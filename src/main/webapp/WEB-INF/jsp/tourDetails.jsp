<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="dictionaries"/>
    <title><fmt:message key="tour.number"/>${tour.id}</title>

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
                        <div class="text-center mb-3" style="color: #ff5100;">

                        </div>
                        <div class="text-center">
                            <table class="table">
                                <tbody>
                                <tr>
                                    <th scope="row"><fmt:message key="country"/></th>
                                    <td>${tour.country}</td>
                                </tr>
                                <tr>
                                    <th scope="row"><fmt:message key="tour.type"/></th>
                                    <td><fmt:message key="${tour.tourType.name}"/></td>
                                </tr>
                                <tr>
                                    <th scope="row"><fmt:message key="departure.date"/></th>
                                    <td>${tour.date}</td>
                                </tr>
                                <tr>
                                    <th scope="row"><fmt:message key="transfer"/></th>
                                    <td><fmt:message key="${tour.transferType.name}"/></td>
                                </tr>
                                <tr>
                                    <th scope="row"><fmt:message key="nights"/></th>
                                    <td>${tour.nightsCount}</td>
                                </tr>
                                <tr>
                                    <th scope="row"><fmt:message key="persons"/></th>
                                    <td>${tour.personsCount}</td>
                                </tr>
                                <tr>
                                    <th scope="row"><fmt:message key="hotel.name"/></th>
                                    <td>${tour.hotelName}</td>
                                </tr>
                                <tr>
                                    <th scope="row"><fmt:message key="hotel.rating"/></th>
                                    <td><fmt:message key="${tour.hotelRating}star"/></td>
                                </tr>
                                <tr>
                                    <th scope="row"><fmt:message key="tour.state"/></th>
                                    <td><fmt:message key="${tour.tourState.name}"/></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-7 ml-auto">
                        <div class="text-center mb-1">
                            <h2>#${tour.id}
                            <c:if test="${tour.hot}">
                                <span style="color: #ff5100;"><fmt:message key="hot.upper.case"/></span>
                            </c:if>
                            </h2>
                        </div>
                        <div class="text-center">
                            ${tour.info}
                        </div>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="text-right mr-3 ml-auto">
                        <h3><fmt:message key="cost"/>: ${tour.cost}$</h3>
                    </div>
                    <div class="text-center mr-3">
                        <button type="submit" class="btn btn-success"><fmt:message key="book.it"/></button>
                    </div>
                    <div class="text-center mr-3">
                        <a class="btn btn-outline-success" href="/" role="button"><fmt:message key="back.to.catalog"/></a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
