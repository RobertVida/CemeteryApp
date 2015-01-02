<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 11/30/2014
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Decedati</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/deceased.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/zebra_datepicker.src.js"></script>
</head>
<body>
<jsp:include page="../fragments/menu.jsp"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}/deceased"/>
<div id="deceased-details" style="margin-top: 20px; display: none;">
    <h4 class="text-center">
        <c:choose>
            <c:when test="${view eq true}">
                <c:set var="actionURL" value="${contextPath}/update"/>
                <b>Informa&#355;iile decedatului ${deceased.firstName} ${deceased.lastName}</b>
            </c:when>
            <c:otherwise>
                <c:set var="actionURL" value="${contextPath}/add"/>
                <b>Informa&#355;iile decedatului</b>
            </c:otherwise>
        </c:choose>
    </h4>
    <form:form id="deceasedForm" action="${actionURL}" commandName="deceased" method="post">
        <div class="details">
            <div class="form-group h35">
                <form:hidden id="deceasedId" path="id" class="form-control"/>
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
                    <form:input path="cnp" class="form-control" type="text" pattern="^[0-9]{13}$" required="true"/>
                </div>
                <div class="col-lg-4" style="float: left; color: red">
                    <form:errors path="cnp"/>
                </div>
            </div>

            <div class="form-group h35">
                <div class="col-lg-4" style="float: left;">
                    <form:label class="control-label" path="religion">Religie</form:label>
                </div>
                <div class="col-lg-4" style="float: left;">
                    <form:input path="religion" class="form-control" type="text" required="true"/>
                </div>
                <div class="col-lg-4" style="float: left; color: red">
                    <form:errors path="religion"/>
                </div>
            </div>

            <div class="form-group h35">
                <div class="col-lg-4" style="float: left;">
                    <form:label class="control-label" path="diedOn">Data mortii</form:label>
                </div>
                <div class="col-lg-4" style="float: left;">
                    <form:input id="diedOn" path="diedOn" class="form-control" type="text" required="true"/>
                </div>
                <div class="col-lg-4" style="float: left; color: red">
                    <form:errors path="diedOn"/>
                </div>
            </div>

            <c:if test="${view eq true}">
                <div class="form-group h35">
                    <div class="col-lg-4" style="float: left;">
                        <form:label class="control-label" path="burialDocumentId">Id-ul documentului de inmormantare</form:label>
                    </div>
                    <div class="col-lg-4" style="float: left;">
                        <form:input path="burialDocumentId" class="form-control number" type="text" pattern="\d*" required="true" readonly="true"/>
                    </div>
                    <div class="col-lg-4" style="float: left; color: red">
                        <form:errors path="burialDocumentId"/>
                    </div>
                </div>
            </c:if>

            <div class="form-group h35">
                <div class="col-lg-4" style="float: left;">
                    <form:label class="control-label" path="structureId">Id-ul mormantului</form:label>
                </div>
                <div class="col-lg-4" style="float: left;">
                    <form:input path="structureId" class="form-control number" type="text" pattern="\d*" required="true"/>
                </div>
                <div class="col-lg-4" style="float: left; color: red">
                    <form:errors path="structureId"/>
                </div>
            </div>

            <div class="form-group h35">
                <div class="col-lg-4" style="float: left;">
                    <form:label class="control-label" path="burialOn">Data ingroparii</form:label>
                </div>
                <div class="col-lg-4" style="float: left;">
                    <form:input id="burialOn" path="burialOn" class="form-control" type="text" required="true"/>
                </div>
                <div class="col-lg-4" style="float: left; color: red">
                    <form:errors path="burialOn"/>
                </div>
            </div>

            <c:if test="${view eq true}">
                <input type="button" onclick="DeceasedManagerJS.deleteDeceased();" value="Sterge" class="btn btn-default pull-right" style="margin-right: 15px;"/>
            </c:if>
            <input id="saveDeceased" onclick="CemeteryJs.validateAndSubmitForm('#deceasedForm');" type="submit" class="btn btn-default pull-right" style="margin-right: 15px;" value="Salveaz&#259;"/>
        </div>
    </form:form>
</div>
<input id="deleteDeceasedURL" type="hidden" value="${contextPath}/delete/"/>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function() {
        var $diedOn = $("#diedOn");
        var $burialOn = $("#burialOn");
        $diedOn.Zebra_DatePicker({
            format: "d/m/Y",
            changeMonth: true,
            changeYear: true,
            yearRange: "1960:+50",
            show_icon: false
        });

        $burialOn.Zebra_DatePicker({
            format: "d/m/Y",
            changeMonth: true,
            changeYear: true,
            yearRange: "1960:+50",
            show_icon: false
        });

        $diedOn.keydown(function() {
            return false;
        });

        $burialOn.keydown(function() {
            return false;
        });
    });
</script>