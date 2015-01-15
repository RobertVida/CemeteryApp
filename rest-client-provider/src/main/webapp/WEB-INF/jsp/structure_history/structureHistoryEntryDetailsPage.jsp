<%--
  Created by IntelliJ IDEA.
  User: Tudor
  Date: 1/5/2015
  Time: 9:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Istoric structuri</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/structure_history.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/zebra_datepicker.src.js"></script>
</head>
<body>
<jsp:include page="../fragments/menu.jsp"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}/structureHistory"/>
<div id="structure-details" style="margin-top: 20px; display: none;">
    <c:if test="${not empty errors}">
        <p class="alert alert-danger">${errors}</p>
    </c:if>
    <h4 class="text-center">
        <br><br>
        <c:choose>
            <c:when test="${view eq true}">
                <c:set var="actionURL" value="${contextPath}/update"/>
                <b>Informa&#355;ii structura ${structure.id}</b>
            </c:when>
            <c:otherwise>
                <c:set var="actionURL" value="${contextPath}/add"/>
                <b>Informa&#355;ii structura</b>
            </c:otherwise>
        </c:choose>
    </h4>
    <form:form id="structureForm" action="${actionURL}" commandName="structure_history" method="post">
        <div class="details">
            <div class="form-group h35">
                <div class="col-lg-4" style="float: left;">
                    <form:label class="control-label" path="structureId">Structura</form:label>
                </div>
                <div class="col-lg-4" style="float: left;">
                    <form:input path="structureId" class="form-control" type="text" pattern="\d*" required="true" readonly="${view}"/>
                </div>
                <div class="col-lg-4" style="float: left; color: red">
                    <form:errors path="structureId"/>
                </div>
            </div>
            <div class="form-group h35">
                <form:hidden id="structureId" path="id" class="form-control"/>
                <div class="col-lg-4" style="float: left;">
                    <form:label class="control-label" path="description">Descriere</form:label>
                </div>
                <div class="col-lg-4" style="float: left;">
                    <form:input path="description" class="form-control" type="text" required="true"/>
                </div>
                <div class="col-lg-4" style="float: left; color: red">
                    <form:errors path="description"/>
                </div>
            </div>

            <div class="form-group h35">
                <div class="col-lg-4" style="float: left;">
                    <form:label class="control-label" path="date">Data</form:label>
                </div>
                <div class="col-lg-4" style="float: left;">
                    <form:input id="date" path="date" class="form-control" type="text" required="true"/>
                </div>
                <div class="col-lg-4" style="float: left; color: red">
                    <form:errors path="date"/>
                </div>
            </div>
            <c:if test="${hasAdminRole}">
                <c:if test="${view eq true}">
                    <input type="button" onclick="StructureHistoryManagerJS.deleteStructureHistory();" value="Sterge" class="btn btn-default pull-right" style="margin-right: 15px;"/>
                </c:if>
                <input id="saveStructure" onclick="CemeteryJs.validateAndSubmitForm('#structureForm');" type="submit" class="btn btn-default pull-right" style="margin-right: 15px;" value="Salveaz&#259;"/>
            </c:if>
        </div>
    </form:form>
</div>
<input id="deleteStructureHistoryURL" type="hidden" value="${contextPath}/delete/"/>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function() {
        var $date = $("#date");
        $date.Zebra_DatePicker({
            format: "d/m/Y",
            changeMonth: true,
            changeYear: true,
            yearRange: "1960:+50",
            show_icon: false
        });

    });
</script>