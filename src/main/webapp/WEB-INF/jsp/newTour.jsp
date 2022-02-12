<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="dictionaries"/>
    <title><fmt:message key="add.tour"/></title>
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
        .new-tour-form{
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
        /*
        Make bootstrap-select work with bootstrap 4 see:
        https://github.com/silviomoreto/bootstrap-select/issues/1135
        */
        .dropdown-toggle.btn-default {
            color: #292b2c;
            background-color: #fff;
            border-color: #ccc;
        }
        .bootstrap-select.show > .dropdown-menu > .dropdown-menu {
            display: block;
        }
        .bootstrap-select > .dropdown-menu > .dropdown-menu li.hidden {
            display: none;
        }
        .bootstrap-select > .dropdown-menu > .dropdown-menu li a {
            display: block;
            width: 100%;
            padding: 3px 1.5rem;
            clear: both;
            font-weight: 400;
            color: #292b2c;
            text-align: inherit;
            white-space: nowrap;
            background: 0 0;
            border: 0;
            text-decoration: none;
        }
        .bootstrap-select > .dropdown-menu > .dropdown-menu li a:hover {
            background-color: #f4f4f4;
        }
        .bootstrap-select > .dropdown-toggle {
            width: 100%;
        }
        .dropdown-menu > li.active > a {
            color: #fff !important;
            background-color: #337ab7 !important;
        }
        .bootstrap-select .check-mark {
            line-height: 14px;
        }
        .bootstrap-select .check-mark::after {
            font-family: "FontAwesome";
            content: "\f00c";
        }
        .bootstrap-select button {
            overflow: hidden;
            text-overflow: ellipsis;
        }

        /* Make filled out selects be the same size as empty selects */
        .bootstrap-select.btn-group .dropdown-toggle .filter-option {
            display: inline !important;
        }
    </style>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>  <link rel="stylesheet" href="//unpkg.com/bootstrap-select@1.12.4/dist/css/bootstrap-select.min.css" type="text/css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>





</head>
<body>

<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div class="container">
    <div class="row">
        <div class="col-md-12 mx-auto">
            <div class="new-tour-form form">
                <div class="logo mb-3">
                    <div class="text-center">
                        <h1><fmt:message key="new.tour"/></h1>
                    </div>
                </div>
                <form class="mb-n3" action="#" name="addTour">
                    <div class="form-row mb-4">
                        <div class="form-group col-md-5 mr-3">
                            <label for="tourName"><fmt:message key="tour.name"/></label>
                            <input type="text" class="form-control" id="tourName" placeholder="<fmt:message key="enter.tour.name"/>">
                        </div>
                        <div class="form-group col-md-3 mr-3">
                            <label for="tourType"><fmt:message key="tour.type"/></label>
                            <select id="tourType" class="form-control">
                                <option selected><fmt:message key="choose"/></option>
                                <option><fmt:message key="relax"/></option>
                                <option><fmt:message key="sightseeing"/></option>
                                <option><fmt:message key="shopping"/></option>
                            </select>
                        </div>
                        <div class="custom-control custom-switch pt-3 mt-4">
                            <input type="checkbox" class="custom-control-input" id="hot">
                            <label class="custom-control-label" for="hot"><fmt:message key="hot"/></label>
                        </div>
                    </div>
                    <div class="form-group mb-4">
                        <label for="info"><fmt:message key="detail.information"/></label>
                        <textarea class="form-control" id="info" rows="5" placeholder="<fmt:message key="enter.tour.details"/>"></textarea>
                    </div>
                    <div class="form-row mb-4 pt-3">
                        <div class="form-group col-md-5 mr-3">
                            <label for="country"><fmt:message key="country"/></label>
                            <select class="selectpicker countrypicker form-control" data-default="Choose" id="country" title="Chooosesss" data-live-search="true"></select>
                        </div>
                        <div class="form-group col-md-3 mr-3">
                            <label for="departure"><fmt:message key="departure.date"/></label>
                            <input type="date" class="form-control" id="departure" name="departure" width="200" required />
                        </div>
                        <div class="form-group col-md-3">
                            <label for="transferType"><fmt:message key="transfer"/></label>
                            <select id="transferType" class="form-control">
                                <option selected><fmt:message key="choose"/></option>
                                <option><fmt:message key="airplane"/></option>
                                <option><fmt:message key="bus"/></option>
                                <option><fmt:message key="cruise.liner"/></option>
                                <option><fmt:message key="train"/></option>
                            </select>
                        </div>
                    </div>
                    <div class="form-row mb-4">
                        <div class="form-group col-md-5 mr-3">
                            <label for="hotelName"><fmt:message key="hotel.name"/></label>
                            <input type="text" class="form-control" id="hotelName" placeholder="<fmt:message key="enter.hotel.name"/>">
                        </div>
                        <div class="form-group col-md-3 mr-3">
                            <label for="rating"><fmt:message key="rating"/></label>
                            <select id="rating" class="form-control">
                                <option selected><fmt:message key="choose"/></option>
                                <option><fmt:message key="3star"/></option>
                                <option><fmt:message key="4star"/></option>
                                <option><fmt:message key="5star"/></option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="form-label" for="nights"><fmt:message key="nights"/></label>
                            <input type="number" id="nights" class="form-control col-4" />
                        </div>
                    </div>
                    <div class="form-row mb-4">
                        <div class="form-group col-md-5 mr-3">
                            <label for="previewFile"><fmt:message key="preview"/></label>
                            <div class="custom-file">
                                <label class="custom-file-label" for="previewFile"><fmt:message key="choose.file"/></label>
                                <input type="file" class="custom-file-input" id="previewFile">
                            </div>
                        </div>
                        <div class="form-group col-2">
                            <label for="price"><fmt:message key="price"/></label>
                            <div class="input-group ">
                                <input type="number" class="form-control" id="price">
                                <div class="input-group-append ">
                                    <span class="input-group-text">$</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-row mb-3">
                        <div class="text-center ml-auto mr-3">
                            <button type="submit" class="btn btn-success"><fmt:message key="add"/></button>
                        </div>
                        <div class="text-center">
                            <a class="btn btn-outline-danger" href="/" role="button"><fmt:message key="cancel"/></a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>

<script src="//unpkg.com/bootstrap-select@1.12.4/dist/js/bootstrap-select.min.js"></script>
<script src="//unpkg.com/bootstrap-select-country@4.0.0/dist/js/bootstrap-select-country.min.js"></script>
<script>
    $(function(){
        var dtToday = new Date();

        var month = dtToday.getMonth() + 1;
        var day = dtToday.getDate();
        var year = dtToday.getFullYear();
        if(month < 10)
            month = '0' + month.toString();
        if(day < 10)
            day = '0' + day.toString();
        var minDate = year + '-' + month + '-' + day;
        $('#departure').attr('min', minDate);
    });
</script>
</body>
</html>