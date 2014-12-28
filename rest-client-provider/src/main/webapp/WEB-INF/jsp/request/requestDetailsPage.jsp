<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 12/27/2014
  Time: 8:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Cereri</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/request.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/zebra_datepicker.src.js"></script>
</head>
<body>
<jsp:include page="../fragments/menu.jsp"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}/request"/>
<div id="request-details" style="margin-top: 20px; display: none;">
    <h4 class="text-center">
        <c:choose>
            <c:when test="${view eq true}">
                <c:set var="actionURL" value="${contextPath}/update"/>
                <b>Informa&#355;iile cererii</b>
            </c:when>
            <c:otherwise>
                <c:set var="actionURL" value="${contextPath}/add"/>
                <b>Informa&#355;iile cererii</b>
            </c:otherwise>
        </c:choose>
    </h4>
    <form:form id="requestForm" action="${actionURL}" commandName="request" method="post">
        <div class="details">
            <div class="form-group h35">
                <form:input id="requestId" path="id" class="form-control" type="hidden"/>
                <div class="col-lg-4" style="float: left;">
                    <form:label class="control-label" path="clientId">Id-ul clientului</form:label>
                </div>
                <div class="col-lg-4" style="float: left;">
                    <form:input path="clientId" class="form-control" type="text" required="true"/>
                </div>
                <div class="col-lg-4" style="float: left; color: red">
                    <form:errors path="clientId"/>
                </div>
            </div>

            <div class="form-group h35">
                <div class="col-lg-4" style="float: left;">
                    <form:label class="control-label" path="createdOn">Creat in</form:label>
                </div>
                <div class="col-lg-4" style="float: left;">
                    <form:input id="createdOn" path="createdOn" class="form-control" type="text" required="true"/>
                </div>
                <div class="col-lg-4" style="float: left; color: red">
                    <form:errors path="createdOn"/>
                </div>
            </div>

            <div class="form-group h35">
                <div class="col-lg-4" style="float: left;">
                    <form:label class="control-label" path="infocetNumber">Numar</form:label>
                </div>
                <div class="col-lg-4" style="float: left;">
                    <form:input path="infocetNumber" class="form-control" type="text" pattern="\d*" required="true"/>
                </div>
                <div class="col-lg-4" style="float: left; color: red">
                    <form:errors path="infocetNumber"/>
                </div>
            </div>

            <div class="form-group h35">
                <div class="col-lg-4" style="float: left;">
                    <form:label class="control-label" path="status">Status</form:label>
                </div>
                <div class="col-lg-4" style="float: left;">
                    <form:input path="status" class="form-control" type="text" required="true" />
                </div>
                <div class="col-lg-4" style="float: left; color: red">
                    <form:errors path="status"/>
                </div>
            </div>

            <c:if test="${view eq true}">
                <input type="button" onclick="RequestsManagerJS.deleteRequest();" value="Sterge" class="btn btn-default pull-right" style="margin-right: 15px;"/>
            </c:if>
            <input id="saveRequest" onclick="CemeteryJs.validateAndSubmitForm('#requestForm', '#saveRequest');" type="submit" class="btn btn-default pull-right" style="margin-right: 15px;" value="Salveaz&#259;"/>
        </div>
    </form:form>
</div>
<input id="deleteRequestURL" type="hidden" value="${contextPath}/delete/"/>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function() {
        var $createdOn = $("#createdOn");
        $createdOn.Zebra_DatePicker({
            format: "d/m/Y",
            changeMonth: true,
            changeYear: true,
            yearRange: "1960:+50",
            show_icon: false
        });

        $createdOn.keydown(function() {
            return false;
        });
    });
</script>
