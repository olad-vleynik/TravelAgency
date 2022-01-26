<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Noname</title>
</head>
    <body>
        <% String user_id = String.valueOf(session.getAttribute("user_id")); %>
        <% String user_full_name = (String)session.getAttribute("user_full_name"); %>

        <% if (user_id == null || "".equals(user_id)) { %>
            <p align="right"><a href="register">Register</a> | <a href="login">Login</a></p>
        <% } else { %>
            <p align="right"><%= user_full_name %> | <a href="/login?action=exit">logout</a></p>
        <% } %>
        <h1><%= "Noname Tourism Agency" %></h1>
    </body>
</html>