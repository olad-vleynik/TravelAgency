<%@ page import="java.util.Optional" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Register</title>
</head>
<body>
<a href="/">Home</a>
<br><br>
<form action="/register" method="POST">
    Name: <input type="text" name="name">
    <%=Optional.ofNullable(request.getSession().getAttribute("name_input_error")).orElse("")%><br>
    Surname: <input type="text" name="surname">
    <%=Optional.ofNullable(request.getSession().getAttribute("surname_input_error")).orElse("")%><br>
    Phone number: <input type="text" name="phoneNumber">
    <%=Optional.ofNullable(request.getSession().getAttribute("phone_number_input_error")).orElse("")%><br>
    E-Mail: <input type="text" name="email">
    <%=Optional.ofNullable(request.getSession().getAttribute("email_input_error")).orElse("")%><br>
    Password: <input type="password" name="password">
    <%=Optional.ofNullable(request.getSession().getAttribute("password_input_error")).orElse("")%><br>
    Date of birth: <input type="date" name="birthday"><br>
    <input type="submit"/>
</form>
</body>
</html>
