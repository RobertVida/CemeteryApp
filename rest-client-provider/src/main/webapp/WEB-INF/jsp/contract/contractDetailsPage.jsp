<%--
  Created by IntelliJ IDEA.
  User: Cata
  Date: 1/5/2015
  Time: 6:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Contracte</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/contract.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/zebra_datepicker.src.js"></script>
</head>
<body>
<jsp:include page="../fragments/menu.jsp"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}/contract"/>
<div id="contract-details" style="margin-top: 20px; display: none;">
    <h4 class="text-center">
        <b>Informa&#355;iile contractului</b>
        <c:choose>
            <c:when test="${view eq true}">
                <c:set var="actionURL" value="${contextPath}/update"/>
            </c:when>
            <c:otherwise>
                <c:set var="actionURL" value="${contextPath}/add"/>
            </c:otherwise>
        </c:choose>
    </h4>
    <form:form id="contractForm" action="${actionURL}" commandName="contract" method="post">
        <div class="details">
            <div class="form-group h35">
                <form:input id="contractId" path="id" class="form-control" type="hidden"/>
                <div class="col-lg-4" style="float: left;">
                    <form:label class="control-label" path="structureId">Id-ul structurii</form:label>
                </div>
                <div class="col-lg-4" style="float: left;">
                    <form:input path="structureId" class="form-control" type="text" pattern="\d*" required="true"/>
                </div>
                <div class="col-lg-4" style="float: left; color: red">
                    <form:errors path="structureId"/>
                </div>
            </div>

            <div class="form-group h35">
                <div class="col-lg-4" style="float: left;">
                    <form:label class="control-label" path="requestId">Id-ul cererii</form:label>
                </div>
                <div class="col-lg-4" style="float: left;">
                    <form:input path="requestId" class="form-control" type="text" pattern="\d*" required="true"/>
                </div>
                <div class="col-lg-4" style="float: left; color: red">
                    <form:errors path="requestId"/>
                </div>
            </div>

            <c:choose>
                <c:when test="${view eq null}">
                    <div class="form-group h35">
                        <div class="col-lg-4" style="float: left;">
                            <form:label class="control-label" path="signedOn">Semnal la</form:label>
                        </div>
                        <div class="col-lg-4" style="float: left;">
                            <form:input id="signedOn" path="signedOn" class="form-control" type="text" required="true"/>
                        </div>
                        <div class="col-lg-4" style="float: left; color: red">
                            <form:errors path="signedOn"/>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <form:input path="signedOn" type="hidden" />
                    <div class="form-group h35">
                        <div class="col-lg-4" style="float: left;">
                            <form:label class="control-label" path="updatedOn">Modificat la</form:label>
                        </div>
                        <div class="col-lg-4" style="float: left;">
                            <form:input id="updatedOn" path="updatedOn" class="form-control" type="text" required="true"/>
                        </div>
                        <div class="col-lg-4" style="float: left; color: red">
                            <form:errors path="updatedOn"/>
                        </div>
                    </div>

                    <div class="form-group h35">
                        <div class="col-lg-4" style="float: left;">
                            <form:label class="control-label" path="expiresOn">Expira la</form:label>
                        </div>
                        <div class="col-lg-4" style="float: left;">
                            <form:input id="expiresOn" path="expiresOn" class="form-control" type="text" required="true"/>
                        </div>
                        <div class="col-lg-4" style="float: left; color: red">
                            <form:errors path="expiresOn"/>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>

            <c:if test="${hasAdminRole}">
                <c:if test="${view eq true}">
                    <input type="button" onclick="ContractManagerJS.deleteContract();" value="Sterge" class="btn btn-default pull-right" style="margin-right: 15px;"/>
                </c:if>
                <input id="saveContract" onclick="CemeteryJs.validateAndSubmitForm('#contractForm');" type="submit" class="btn btn-default pull-right" style="margin-right: 15px;" value="Salveaz&#259;" />
            </c:if>
        </div>
    </form:form>
</div>
<input id="deleteContractURL" type="hidden" value="${contextPath}/delete/"/>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function() {
        var $signedOn = $("#signedOn");
        var $updatedOn = $("#updatedOn");
        var $expiresOn = $("#expiresOn");
        $signedOn.Zebra_DatePicker({
            format: "d/m/Y",
            changeMonth: true,
            changeYear: true,
            yearRange: "1960:+50",
            show_icon: false
        });
        $updatedOn.Zebra_DatePicker({
            format: "d/m/Y",
            changeMonth: true,
            changeYear: true,
            yearRange: "1960:+50",
            show_icon: false
        });
        $expiresOn.Zebra_DatePicker({
            format: "d/m/Y",
            changeMonth: true,
            changeYear: true,
            yearRange: "1960:+50",
            show_icon: false
        });

        $signedOn.keydown(function() {
            return false;
        });
        $updatedOn.keydown(function() {
            return false;
        });
        $expiresOn.keydown(function() {
            return false;
        });
    });
</script>
