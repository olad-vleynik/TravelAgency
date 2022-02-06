<%@ page import="com.gmail.vleynik.olad.travelagency.utils.UserInputCheck" %>
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
    Name: <input type="text" name="name" value="">
    Surname: <input type="text" name="surname">
    Phone number: <input type="text" name="phoneNumber">
    E-Mail: <input type="text" name="email">
    Password: <input type="password" name="password">
    Date of birth: <input type="date" name="birthday"><br>
    <input type="submit"/>
</form>
</body>
</html>
