<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 11/24/2014
  Time: 6:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title></title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/cemetery.js"></script>
</head>
<body>
    <jsp:include page="../fragments/menu.jsp"/>
    <c:set var="contextPath" value="${pageContext.request.contextPath}/cemetery"/>
    <div id="cemetery-details" style="margin-top: 20px; display: none;">
        <h4 class="text-center">
            <c:choose>
                <c:when test="${view eq true}">
                    <c:set var="actionURL" value="${contextPath}/update"/>
                    <b>Informa&#355;iile cimitirului ${cemetery.name}</b>
                </c:when>
                <c:otherwise>
                    <c:set var="actionURL" value="${contextPath}/add"/>
                    <b>Informa&#355;iile cimitirului</b>
                </c:otherwise>
            </c:choose>
        </h4>
        <form:form id="cemeteryForm" action="${actionURL}" commandName="cemetery" method="post">
            <div class="details">
                <div class="form-group h35">
                    <form:input id="cemeteryId" path="id" class="form-control" type="hidden"/>
                    <div class="col-lg-4" style="float: left;">
                        <form:label class="control-label" path="name">Nume</form:label>
                    </div>
                    <div class="col-lg-4" style="float: left;">
                        <form:input path="name" class="form-control" type="text" required="true"/>
                    </div>
                    <div class="col-lg-4" style="float: left; color: red">
                        <form:errors path="name"/>
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
                        <input type="button" onclick="CemeteriesManagerJS.deleteCemetery();" value="Sterge" class="btn btn-default pull-right" style="margin-right: 15px;"/>
                    </c:if>
                    <input onclick="CemeteryJs.validateAndSubmitForm('#cemeteryForm');" type="submit" class="btn btn-default pull-right" style="margin-right: 15px;" value="Salveaz&#259;"/>
                </c:if>
            </div>
        </form:form>
    </div>
</body>
<input id="deleteCemeteryURL" type="hidden" value="${contextPath}/delete/"/>
</html>