<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="dictionaries"/>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Travel agency</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mx-auto ">
            <li class="nav-item active">
                <a class="nav-link" href="#"><fmt:message key="catalog"/> <span class="sr-only">(current)</span></a>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto ">
            <c:if test="${user_access_level == 'ADMINISTRATOR' || user_access_level == 'MANAGER' }">
                <li class="nav-item">
                    <a class="nav-link" href="#"><fmt:message key="add.tour"/></a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="control.panel"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#"><fmt:message key="user.manager"/></a>
                        <a class="dropdown-item" href="#"><fmt:message key="tour.manager"/></a>
                    </div>
                </li>
            </c:if>
            <c:if test="${not empty user_id}">
                <li class="nav-item active">
                    <a class="nav-link" href="#">${user_full_name}</a>
                </li>
            </c:if>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>

<% String user_id = String.valueOf(session.getAttribute("user_id")); %>
<% String user_full_name = (String)session.getAttribute("user_full_name"); %>
<br/>
<br/>
<% if (user_id == null || "".equals(user_id) || "null".equals(user_id)) { %>
<a href="login">Login | Sign up</a>
<% } else { %>
<%= user_full_name %> | <a href="/login?action=exit">logout</a>
<% } %>
