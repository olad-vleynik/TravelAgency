<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="dictionaries"/>
    <meta charset="utf-8">
    <!-- This file has been downloaded from Bootsnipp.com. Enjoy! -->
    <title>Entry</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat+Alternates&display=swap" rel="stylesheet">
    <style type="text/css">
        body{
            padding-bottom:4.2rem;
        }
        a{
            text-decoration:none !important;
        }
        h1,h2,h3{
            font-family: 'Montserrat Alternates', sans-serif;
        }
        .myform{
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
            max-width: 500px;
            margin-top: -0.25rem
        }
        .tx-tfm{
            text-transform:uppercase;
        }
        .mybtn{
            border-radius:50px;
        }

        .login-or {
            position: relative;
            color: #aaa;
            margin-top: 10px;
            margin-bottom: 10px;
            padding-top: 10px;
            padding-bottom: 10px;
        }
        .span-or {
            display: block;
            position: absolute;
            left: 50%;
            top: -2px;
            margin-left: -25px;
            background-color: #fff;
            width: 50px;
            text-align: center;
        }
        .hr-or {
            height: 1px;
            margin-top: 0px !important;
            margin-bottom: 0px !important;
        }
        form .error {
            color: #ff0000;
        }
        <c:choose>
            <c:when test="${requestScope.action == 'registration'}">
                #first{display:none;}
            </c:when>
            <c:otherwise>
                #second{display:none;}
            </c:otherwise>
        </c:choose>
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>
</head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<body>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>



<jsp:include page="/WEB-INF/jsp/userHeader.jsp" />
<div class="container">
    <div class="row">
        <div class="col-md-5 mx-auto">
            <div id="first">
                <div class="myform form ">
                    <div class="logo mb-3">
                        <div class="col-md-12 text-center">
                            <h1><fmt:message key="login"/></h1>
                        </div>
                    </div>
                    <form class="mb-n3" action="" method="post" name="login">
                        <div class="form-group">
                            <label for="login"><fmt:message key="email.or.phone"/></label>
                            <input type="login" name="login"  class="form-control" id="login" aria-describedby="emailHelp" placeholder="<fmt:message key="enter.email.or.phone"/>">
                        </div>
                        <div class="form-group">
                            <label for="password"><fmt:message key="password"/></label>
                            <input type="password" name="password" id="password"  class="form-control" aria-describedby="emailHelp" placeholder="<fmt:message key="enter.password"/>">
                        </div>
                        <div class="form-check mb-3">
                            <input class="form-check-input mt-2" type="checkbox" value="" name="remember" id="remember">
                            <label class="form-check-label" for="remember">
                                <fmt:message key="remember.me"/>
                            </label>
                        </div>
                        <div class="col-md-12 text-center ">
                            <button type="submit" class=" btn btn-block mybtn btn-primary tx-tfm"><fmt:message key="sign.in"/></button>
                            <c:if test="${not empty loginErrorMessage}">
                                <div class="text-danger">
                                    <p class="text-center"><small><fmt:message key="${loginErrorMessage}"/></small></p>
                                </div>
                            </c:if>
                        </div>
                        <div class="col-md-12 ">
                            <div class="login-or">
                                <hr class="hr-or">
                                <span class="span-or"><fmt:message key="or"/></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <p class="text-center"><fmt:message key="dont.have.account"/> <a href="#" id="signup"><fmt:message key="sign.up"/></a></p>
                        </div>
                    </form>
                </div>
            </div>
            <div id="second">
                <div class="myform form ">
                    <div class="logo mb-3">
                        <div class="col-md-12 text-center">
                            <h1 ><fmt:message key="registration"/></h1>
                        </div>
                    </div>
                    <form class="mb-n3" action="#" name="registerme">
                        <div class="form-group">
                            <label for="name"><fmt:message key="name"/></label>
                            <input type="text"  name="name" class="form-control" id="name" aria-describedby="emailHelp" placeholder="<fmt:message key="enter.name"/>">
                        </div>
                        <div class="form-group">
                            <label for="surname"><fmt:message key="surname"/></label>
                            <input type="text"  name="surname" class="form-control" id="surname" aria-describedby="emailHelp" placeholder="<fmt:message key="enter.surname"/>">
                        </div>
                        <div class="form-group">
                            <label for="phoneNumber"><fmt:message key="phone.number"/></label>
                            <input type="text" name="phoneNumber"  class="form-control" id="phoneNumber" aria-describedby="emailHelp" placeholder="<fmt:message key="enter.phone.number"/>">
                        </div>
                        <div class="form-group">
                            <label for="email"><fmt:message key="email"/></label>
                            <input type="text" name="email"  class="form-control" id="email" aria-describedby="emailHelp" placeholder="<fmt:message key="enter.email"/>">
                        </div>
                        <div class="form-group">
                            <label for="regPassword"><fmt:message key="password"/></label>
                            <input type="password" name="regPassword" id="regPassword"  class="form-control" aria-describedby="emailHelp" placeholder="<fmt:message key="enter.password"/>">
                        </div>
                        <div class="form-group">
                            <label for="regPasswordConfirm"><fmt:message key="confirm.password"/></label>
                            <input type="password" name="regPasswordConfirm" id="regPasswordConfirm"  class="form-control" aria-describedby="emailHelp" placeholder="<fmt:message key="enter.same.password"/>">
                        </div>
                        <div class="form-group mb-4">
                            <label for="birthday"><fmt:message key="birthday"/></label>
                            <input type="date" class="form-control" id="birthday" name="birthday" aria-describedby="minAge" required />
                            <small id="minAge" class="form-text text-muted">
                                <fmt:message key="only.16"/>
                            </small>
                        </div>
                        <div class="col-md-12 text-center mb-3">
                            <button type="submit" class=" btn btn-block mybtn btn-primary tx-tfm"><fmt:message key="sign.up"/></button>
                            <c:if test="${not empty registrationErrorMessage}">
                                <div class="text-danger">
                                    <p class="text-center"><small><fmt:message key="${registrationErrorMessage}"/></small></p>
                                </div>
                            </c:if>
                        </div>
                        <div class="col-md-12 ">
                            <div class="form-group">
                                <p class="text-center"><a href="#" id="signin"><fmt:message key="have.account"/></a></p>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $("#signup").click(function() {
        $("#first").fadeOut("fast", function() {
            $("#second").fadeIn("fast");
        });
    });

    $("#signin").click(function() {
        $("#second").fadeOut("fast", function() {
            $("#first").fadeIn("fast");
        });
    });

    $(function(){
        var dtToday = new Date();

        var month = dtToday.getMonth() + 1;
        var day = dtToday.getDate();
        var year = dtToday.getFullYear() - 16;
        if(month < 10)
            month = '0' + month.toString();
        if(day < 10)
            day = '0' + day.toString();
        var maxDate = year + '-' + month + '-' + day;
        $('#birthday').attr('max', maxDate);
    });

    $(function() {
        $("form[name='login']").validate({
            rules: {

                login: {
                    required: true,
                    regex:/(^(\+38)?0(67|68|96|97|98|50|66|95|99|63|73|93)\d{7}$)|(^[a-zA-Z0-9]+[._-]?[a-zA-Z0-9]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$)/
                },
                password: {
                    required: true
                }
            },
            messages: {
                login: "<fmt:message key="invalid.email.or.phone"/>",
                password: "<fmt:message key="invalid.password"/>"
            },
            submitHandler: function(form) {
                form.method = "POST";
                form.action = "/login";
                form.submit();
            }
        });
    });



    $(function() {

        $("form[name='registerme']").validate({
            rules: {
                name: {
                    required: true,
                    regex:/^(([А-Я][а-я]{1,39})|([A-Z][a-z]{1,39}))$/
                },
                surname: {
                    required: true,
                    regex:/^(([А-Я][а-я]{1,39})|([A-Z][a-z]{1,39}))$/
                },
                phoneNumber: {
                    required: true,
                    regex:/^(\+38)?0(67|68|96|97|98|50|66|95|99|63|73|93)\d{7}$/
                },
                email: {
                    required: true,
                    regex:/^[a-zA-Z0-9]+[._-]?[a-zA-Z0-9]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/,
                    maxlength: 40
                },
                regPassword: {
                    required: true,
                    minlength: 5
                },
                regPasswordConfirm: {
                    required: true,
                    minlength: 5,
                    equalTo: "#regPassword"
                },
                birthday: {
                    required: true
                }
            },

            messages: {
                name: "<fmt:message key="invalid.name"/>",
                surname: "<fmt:message key="invalid.surname"/>",
                regPassword: {
                    required: "<fmt:message key="invalid.password"/>",
                    minlength: "<fmt:message key="invalid.password.length"/>"
                },
                regPasswordConfirm: {
                    required: "<fmt:message key="invalid.password"/>",
                    minlength: "<fmt:message key="invalid.password.length"/>",
                    equalTo: "<fmt:message key="passwords.not.same"/>"
                },
                phoneNumber: "<fmt:message key="invalid.phone.number"/>",
                email: "<fmt:message key="invalid.email"/>",
                birthday: "<fmt:message key="invalid.birthday"/>"
            },

            submitHandler: function(form) {
                form.method = "POST";
                form.action = "/register";
                form.submit();
            }
        });
    });


    $.validator.addMethod(
        "regex",
        function(value, element, regexp) {
            return this.optional(element) || regexp.test(value);
        }
    );

</script>
</body>
</html>