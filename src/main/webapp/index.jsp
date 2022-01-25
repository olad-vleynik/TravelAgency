<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Noname</title>
</head>
    <body>
        <% String login = (String)session.getAttribute("user_login"); %>

        <% if (login == null || "".equals(login)) { %>
            <p align="right"><a href="register">Register</a> | <a href="login">Login</a></p>
        <% } else { %>
            <p align="right"><%= login %> | <a href="/login?action=exit">logout</a></p>
        <% } %>
        <h1><%= "Noname Tourism Agency" %></h1>
    </body>
</html>