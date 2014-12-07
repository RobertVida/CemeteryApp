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
<html>
<head>
    <title>Decedati</title>
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
    <form:form action="${actionURL}" commandName="deceased" method="post">
        <div class="details">
            <div class="form-group h35">
                <form:input id="deceasedId" path="id" class="form-control" type="hidden"/>
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
                    <form:label class="control-label" path="religion">Religie</form:label>
                </div>
                <div class="col-lg-4" style="float: left;">
                    <form:input path="religion" class="form-control" type="text"/>
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
                    <form:input path="diedOn" class="form-control" type="text"/>
                </div>
                <div class="col-lg-4" style="float: left; color: red">
                    <form:errors path="diedOn"/>
                </div>
            </div>

            <div class="form-group h35">
                <div class="col-lg-4" style="float: left;">
                    <form:label class="control-label" path="burialDocumentId">Id-ul documentului de inmormantare</form:label>
                </div>
                <div class="col-lg-4" style="float: left;">
                    <form:input path="burialDocumentId" class="form-control" type="text"/>
                </div>
                <div class="col-lg-4" style="float: left; color: red">
                    <form:errors path="burialDocumentId"/>
                </div>
            </div>

            <div class="form-group h35">
                <div class="col-lg-4" style="float: left;">
                    <form:label class="control-label" path="structureId">Id-ul mormantului</form:label>
                </div>
                <div class="col-lg-4" style="float: left;">
                    <form:input path="structureId" class="form-control" type="text" />
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
                    <form:input path="burialOn" class="form-control" type="text" />
                </div>
                <div class="col-lg-4" style="float: left; color: red">
                    <form:errors path="burialOn"/>
                </div>
            </div>

            <c:if test="${view eq true}">
                <input type="button" onclick="DeceasedManagerJS.deleteDeceased();" value="Sterge" class="btn btn-default pull-right" style="margin-right: 15px;"/>
            </c:if>
            <button type="submit" class="btn btn-default pull-right" style="margin-right: 15px;">Salveaz&#259;</button>
        </div>
    </form:form>
</div>
<input id="deleteDeceasedURL" type="hidden" value="${contextPath}/delete/"/>
</body>
</html>