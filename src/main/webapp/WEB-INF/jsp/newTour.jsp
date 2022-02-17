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
        form .error {
            color: #ff0000;
        }
        .custom-control-input:focus~.custom-control-label::before {
            border-color: #ff5100 !important;
            box-shadow: 0 0 0 0.2rem rgba(255, 81, 0, 0.25) !important;
        }

        .custom-control-input:checked~.custom-control-label::before {
            border-color: #ff5100 !important;
            background-color: #ff5100 !important;
        }

        .custom-control-input:active~.custom-control-label::before {
            background-color: #ff5100 !important;
            border-color: #ff5100 !important;
        }

        .custom-control-input:focus:not(:checked)~.custom-control-label::before {
            border-color: #ff5100 !important;
        }

        .custom-control-input-green:not(:disabled):active~.custom-control-label::before {
            background-color: #ff5100 !important;
            border-color: #ff5100 !important;
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
            text-align: left;
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

<jsp:include page="/WEB-INF/jsp/userHeader.jsp" />

<div class="container">
    <div class="row">
        <div class="col-md-12 mx-auto">
            <div class="new-tour-form form">
                <div class="logo mb-3">
                    <div class="text-center">
                        <h1><fmt:message key="new.tour"/></h1>
                    </div>
                </div>
                <form class="mb-n3" action="#" name="addTour" enctype="multipart/form-data">
                    <div class="form-row mb-4">
                        <div class="form-group col-md-5 mr-3">
                            <label for="tourName"><fmt:message key="tour.name"/></label>
                            <input type="text" class="form-control" name="tourName" id="tourName" placeholder="<fmt:message key="enter.tour.name"/>">
                        </div>
                        <div class="form-group col-md-3 mr-3">
                            <label for="tourType"><fmt:message key="tour.type"/></label>
                            <select id="tourType" class="form-control" name="tourType">
                                <option selected value=""><fmt:message key="choose"/></option>
                                <option value="RELAX"><fmt:message key="relax"/></option>
                                <option value="SIGHTSEEING"><fmt:message key="sightseeing"/></option>
                                <option value="SHOPPING"><fmt:message key="shopping"/></option>
                            </select>
                        </div>
                        <div class="custom-control custom-switch pt-3 mt-4">
                            <input type="checkbox" class="custom-control-input" id="hot" name="hot">
                            <label class="custom-control-label" for="hot"><fmt:message key="hot"/></label>
                        </div>
                    </div>
                    <div class="form-group mb-4">
                        <label for="info"><fmt:message key="detail.information"/></label>
                        <textarea class="form-control" id="info" rows="5" name="info" placeholder="<fmt:message key="enter.tour.details"/>"></textarea>
                    </div>
                    <div class="form-row mb-4 pt-3">
                        <div class="form-group col-md-5 mr-3">
                            <label for="country"><fmt:message key="country"/></label>
                            <select class="selectpicker countrypicker form-control" data-default="UA" name="country" id="country" data-live-search="true"></select>
                        </div>
                        <div class="form-group col-md-3 mr-3">
                            <label for="departure"><fmt:message key="departure.date"/></label>
                            <input type="date" class="form-control" id="departure" name="departure" width="200"/>
                        </div>
                        <div class="form-group col-md-3">
                            <label for="transferType"><fmt:message key="transfer"/></label>
                            <select id="transferType" class="form-control" name="transfer">
                                <option selected value=""><fmt:message key="choose"/></option>
                                <option value="AIRPLANE"><fmt:message key="airplane"/></option>
                                <option value="BUS"><fmt:message key="bus"/></option>
                                <option value="CRUISE_LINER"><fmt:message key="cruise.liner"/></option>
                                <option value="TRAIN"><fmt:message key="train"/></option>
                            </select>
                        </div>
                    </div>
                    <div class="form-row mb-4">
                        <div class="form-group col-md-5 mr-3">
                            <label for="hotelName"><fmt:message key="hotel.name"/></label>
                            <input type="text" class="form-control" id="hotelName" name="hotelName" placeholder="<fmt:message key="enter.hotel.name"/>">
                        </div>
                        <div class="form-group col-md-3 mr-3">
                            <label for="rating"><fmt:message key="rating"/></label>
                            <select id="rating" class="form-control" name="rating">
                                <option selected value=""><fmt:message key="choose"/></option>
                                <option value="3star"><fmt:message key="3star"/></option>
                                <option value="4star"><fmt:message key="4star"/></option>
                                <option value="5star"><fmt:message key="5star"/></option>
                            </select>
                        </div>
                        <div class="form-group col-1 mr-3">
                            <label class="form-label" for="nights"><fmt:message key="nights"/></label>
                            <input type="number" id="nights" class="form-control" value="1" name="nights"/>
                        </div>
                        <div class="form-group col-1">
                            <label class="form-label" for="persons"><fmt:message key="persons"/></label>
                            <input type="number" id="persons" class="form-control" value="1" name="persons"/>
                        </div>
                    </div>
                    <div class="form-row mb-4">
                        <div class="form-group col-md-5 mr-3">
                            <label for="previewFile"><fmt:message key="preview"/></label>
                            <div class="custom-file">
                                <input type="file" class="custom-file-input" id="previewFile" name="previewFile" >
                                <label class="custom-file-label" for="previewFile"><fmt:message key="choose.file"/></label>
                            </div>
                        </div>
                        <div class="form-group col-3">
                            <label for="price"><fmt:message key="price"/></label>
                            <div class="input-group">
                                <input type="number" class="form-control" id="price" name="price">
                                <div class="input-group-append pr-5 mr-3">
                                    <span class="input-group-text">$</span>
                                </div>
                                <label id="price-error" class="error order-last mr-3" for="price" style="display: none;"></label>
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
<script src="https://cdn.jsdelivr.net/npm/bs-custom-file-input/dist/bs-custom-file-input.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/additional-methods.js"></script>

<script>
    $(document).ready(function () {
        bsCustomFileInput.init()

        $.validator.addMethod('filesize', function (value, element, param) {
            return this.optional(element) || (element.files[0].size <= param)
        }, 'File size must be less than {0}');
    })


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

    $(function() {

        $("form[name='addTour']").validate({
            rules: {
                tourName: {
                    required: true,
                    maxlength: 40
                },
                tourType: {
                    required: true
                },
                info: {
                    required: true,
                    maxlength: 1000
                },
                departure: {
                    required: true
                },
                transfer: {
                    required: true
                },
                hotelName: {
                    required: true,
                    maxlength: 20
                },
                rating: {
                    required: true
                },
                nights: {
                    required: true,
                    min: 1
                },
                persons: {
                    required: true,
                    min: 1
                },
                price: {
                    required: true,
                    min: 1
                },
                previewFile: {
                    extension: "jpg|jpeg",
                    filesize: 5242880
                }
            },

            messages: {
                tourName: {
                    required: "<fmt:message key="please.enter.tour.name"/>",
                    maxlength: "<fmt:message key="max.length.forty"/>"
                },
                tourType: "<fmt:message key="please.choose"/>",
                info: {
                    required: "<fmt:message key="please.enter.info"/>",
                    maxlength: "<fmt:message key="max.length.thousand"/>"
                },
                departure: "<fmt:message key="please.pick.date"/>",
                transfer: "<fmt:message key="please.choose"/>",
                hotelName: {
                    required: "<fmt:message key="please.enter.hotel.name"/>",
                    maxlength: "<fmt:message key="max.length.twenty"/>"
                },
                rating: "<fmt:message key="please.choose"/>",
                nights: {
                    required: "<fmt:message key="required"/>",
                    min: "<fmt:message key="min.one.short"/>"
                },
                persons: {
                    required: "<fmt:message key="required"/>",
                    min: "<fmt:message key="min.one.short"/>"
                },
                price: {
                    required: "<fmt:message key="please.enter.price"/>",
                    min: "<fmt:message key="min.one"/>"
                },
                previewFile: {
                    extension: "<fmt:message key="extension.jpg"/>",
                    filesize: "<fmt:message key="max.5.mb"/>"
                }
            },
            submitHandler: function(form) {
                var formData = new FormData(form);
                $.ajax({
                    url: '/tour',
                    data: formData,
                    cache: false,
                    contentType: false,
                    processData: false,
                    method: 'POST',
                    success : function(data) {
                        window.location = "/tour?id=" + data;
                    }
                });
            }
        });
    });
</script>
</body>
</html>