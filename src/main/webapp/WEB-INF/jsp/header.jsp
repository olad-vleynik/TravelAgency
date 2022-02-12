<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="dictionaries"/>

<nav class="navbar navbar-expand-lg navbar-light mb-5 " style="background-color: #F4F4F4;">
    <a class="navbar-brand mr-4" href="/">Travel agency</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav">
            <li class="nav-item <c:if test="${locale == 'en'}">active</c:if>">
                <a class="nav-link <c:if test="${locale == 'ru'}">disabled</c:if>" href="/locale?l=ru">рус</a>
            </li>
            <li class="nav-item mx-n2">
                <a class="nav-link disabled" href="#">|</a>
            </li>
            <li class="nav-item <c:if test="${locale == 'ru'}">active</c:if>">
                <a class="nav-link <c:if test="${locale == 'en'}">disabled</c:if>" href="/locale?l=en">en</a>
            </li>
            <li class="nav-item active mx-5">
                <a class="nav-link" href="#"><fmt:message key="catalog"/> <span class="sr-only">(current)</span></a>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <c:if test="${not empty user_id}">
                <c:if test="${user_access_level == 'ADMINISTRATOR' || user_access_level == 'MANAGER' }">
                    <li class="nav-item active mr-3">
                        <a class="nav-link" href="/tour?action=add"><fmt:message key="add.tour"/></a>
                    </li>
                    <li class="nav-item dropdown mr-3">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <fmt:message key="control.panel"/>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#"><fmt:message key="tour.manager"/></a>
                            <a class="dropdown-item" href="#"><fmt:message key="user.manager"/></a>
                        </div>
                    </li>
                </c:if>
                <li class="nav-item dropdown mr-3">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownProfile" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        ${user_full_name}
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownProfile">
                        <a class="dropdown-item" href="#"><fmt:message key="profile.orders"/></a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#"><fmt:message key="profile.settings"/></a>
                    </div>
                </li>
                <a class="btn btn-outline-danger" href="/login?action=exit" role="button"><fmt:message key="logout"/></a>
            </c:if>
            <c:if test="${empty user_id}">
                <a class="btn btn-link" href="/login" role="button"><fmt:message key="sign.in"/></a>
                <a class="btn btn-outline-success" href="/register" role="button"><fmt:message key="sign.up"/></a>
            </c:if>
        </ul>
    </div>
</nav>
