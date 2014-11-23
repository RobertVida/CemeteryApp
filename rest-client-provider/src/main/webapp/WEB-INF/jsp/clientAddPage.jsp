<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title></title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
</head>
<body>
<jsp:include page="fragments/menu.jsp"/>
<div id="client-add-details" style="margin-top: 20px; display: none;">
    <h4 class="text-center">
        <b>Informa&#355;iile clientului</b>
    </h4>
    <form:form action="${pageContext.request.contextPath}/clients/add" commandName="clientDetails">
        <div class="details">
            <div class="form-group h35">
                <div class="col-lg-4" style="float: left;">
                    <form:label class="control-label" path="lastName">Nume</form:label>
                </div>
                <div class="col-lg-4" style="float: left;">
                    <form:input path="lastName" class="form-control" type="text"/>
                </div>
                <div class="col-lg-4" style="float: left; color: red">
                    <form:errors path="lastName"/>
                </div>
            </div>

            <div class="form-group h35">
                <div class="col-lg-4" style="float: left;">
                    <form:label class="control-label" path="firstName">Prenume</form:label>
                </div>
                <div class="col-lg-4" style="float: left;">
                    <form:input path="firstName" class="form-control" type="text"/>
                </div>
                <div class="col-lg-4" style="float: left; color: red">
                    <form:errors path="firstName"/>
                </div>
            </div>

            <div class="form-group h35">
                <div class="col-lg-4" style="float: left;">
                    <form:label class="control-label" path="cnp">CNP</form:label>
                </div>
                <div class="col-lg-4" style="float: left;">
                    <form:input path="cnp" class="form-control" type="text"/>
                </div>
                <div class="col-lg-4" style="float: left; color: red">
                    <form:errors path="cnp"/>
                </div>
            </div>

            <div class="form-group h35">
                <div class="col-lg-4" style="float: left;">
                    <form:label class="control-label" path="phoneNumber">Nr. telefon</form:label>
                </div>
                <div class="col-lg-4" style="float: left;">
                    <form:input path="phoneNumber" class="form-control" type="text"/>
                </div>
                <div class="col-lg-4" style="float: left; color: red">
                    <form:errors path="phoneNumber"/>
                </div>
            </div>

            <div class="form-group h35">
                <div class="col-lg-4" style="float: left;">
                    <form:label class="control-label" path="address">Adres&#259;</form:label>
                </div>
                <div class="col-lg-4" style="float: left;">
                    <form:input path="address" class="form-control" type="text"/>
                </div>
                <div class="col-lg-4" style="float: left; color: red">
                    <form:errors path="address"/>
                </div>
            </div>

            <button id="saveClient" type="submit" class="btn btn-default pull-right" style="margin-right: 15px;">Salveaz&#259;</button>
        </div>
    </form:form>
</div>
</body>
</html>
<script type="text/javascript">
    $(document).ready( function() {
        $('#container').html($('#client-add-details')).html();
        $('#client-add-details').css("display", "inline");
    });
</script>