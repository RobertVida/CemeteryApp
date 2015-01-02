<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title></title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/clients.js"></script>
</head>
<body>
    <jsp:include page="../fragments/menu.jsp"/>
    <c:set var="contextPath" value="${pageContext.request.contextPath}/clients"/>
    <div id="client-details" style="margin-top: 20px; display: none;">
    <h4 class="text-center">
        <c:choose>
            <c:when test="${view eq true}">
                <c:set var="actionURL" value="${contextPath}/update"/>
                <b>Informa&#355;iile clientului ${client.firstName} ${client.lastName}</b>
            </c:when>
            <c:otherwise>
                <c:set var="actionURL" value="${contextPath}/add"/>
                <b>Informa&#355;iile clientului</b>
            </c:otherwise>
        </c:choose>
    </h4>
    <form:form id="clientForm" action="${actionURL}" commandName="client">
        <div class="details">
            <div class="form-group h35">
                <form:hidden id="clientId" path="id" class="form-control" />
                <div class="col-lg-4" style="float: left;">
                    <form:label class="control-label" path="lastName">Nume</form:label>
                </div>
                <div class="col-lg-4" style="float: left;">
                    <form:input path="lastName" class="form-control" type="text" required="true"/>
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
                    <form:input path="firstName" class="form-control" type="text" required="true"/>
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
                    <form:input path="cnp" class="form-control" type="text" pattern="^[0-9]{13}$" required="true" />
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
                    <form:input path="phoneNumber" class="form-control" type="text" pattern="^[0-9]{10}$" required="true"/>
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
                    <form:input path="address" class="form-control" type="text" required="true"/>
                </div>
                <div class="col-lg-4" style="float: left; color: red">
                    <form:errors path="address"/>
                </div>
            </div>

            <c:if test="${hasAdminRole}">
                <c:if test="${view eq true}">
                    <input type="button" onclick="ClientsManagerJS.deleteClient();" value="Sterge" class="btn btn-default pull-right" style="margin-right: 15px;"/>
                </c:if>
                <input id="saveClient" onclick="CemeteryJs.validateAndSubmitForm('#clientForm');" type="submit" class="btn btn-default pull-right" style="margin-right: 15px;" value="Salveaz&#259;"/>
            </c:if>
        </div>
    </form:form>
</div>
<input id="deleteClientURL" type="hidden" value="${pageContext.request.contextPath}/clients/delete/"/>
</body>
</html>
