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
    Name: <input type="text" name="name"><br>
    Surname: <input type="text" name="surname"><br>
    Phone number: <input type="text" name="phoneNumber"> <%=(String)session.getAttribute("phone_number_error") == null ? "" : (String)session.getAttribute("phone_number_error")%><br>
    E-Mail: <input type="text" name="email"><br>
    Password: <input type="password" name="password"><br>
    Date of birth: <input type="date" name="birthday"><br>
    <input type="submit"/>
</form>
</body>
</html>
