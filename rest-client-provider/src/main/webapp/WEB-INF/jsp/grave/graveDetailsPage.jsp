<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 11/30/2014
  Time: 7:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Morminte</title>
</head>
<body>
<jsp:include page="../fragments/menu.jsp"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}/grave"/>
    <div id="grave-details" style="margin-top: 20px; display: none;">
        <h4 class="text-center">
            <b>Informa&#355;iile mormantului</b>
            <c:choose>
                <c:when test="${view eq true}">
                    <c:set var="actionURL" value="${contextPath}/update"/>
                </c:when>
                <c:otherwise>
                    <c:set var="actionURL" value="${contextPath}/add"/>
                </c:otherwise>
            </c:choose>
        </h4>
        <form:form action="${actionURL}" commandName="grave" method="post">
            <div class="details">
                <div class="form-group h35">
                    <form:input id="graveId" path="id" class="form-control" type="hidden"/>
                    <div class="col-lg-4" style="float: left;">
                        <form:label class="control-label" path="parcelId">Id-ul parcelei</form:label>
                    </div>
                    <div class="col-lg-4" style="float: left;">
                        <form:input path="parcelId" class="form-control" type="text"/>
                    </div>
                    <div class="col-lg-4" style="float: left; color: red">
                        <form:errors path="parcelId"/>
                    </div>
                </div>

                <div class="form-group h35">
                    <div class="col-lg-4" style="float: left;">
                        <form:label class="control-label" path="createdOn">Creat in</form:label>
                    </div>
                    <div class="col-lg-4" style="float: left;">
                        <form:input path="createdOn" class="form-control" type="text"/>
                    </div>
                    <div class="col-lg-4" style="float: left; color: red">
                        <form:errors path="createdOn"/>
                    </div>
                </div>

                <form:input path="type" type="hidden"/>

                <div class="form-group h35">
                    <div class="col-lg-4" style="float: left;">
                        <form:label class="control-label" path="width">Latime</form:label>
                    </div>
                    <div class="col-lg-4" style="float: left;">
                        <form:input path="width" class="form-control" type="text"/>
                    </div>
                    <div class="col-lg-4" style="float: left; color: red">
                        <form:errors path="width"/>
                    </div>
                </div>

                <div class="form-group h35">
                    <div class="col-lg-4" style="float: left;">
                        <form:label class="control-label" path="length">Lungime</form:label>
                    </div>
                    <div class="col-lg-4" style="float: left;">
                        <form:input path="length" class="form-control" type="text"/>
                    </div>
                    <div class="col-lg-4" style="float: left; color: red">
                        <form:errors path="length"/>
                    </div>
                </div>

                <c:if test="${view eq true}">
                    <input type="button" onclick="GraveManagerJS.deleteGrave();" value="Sterge" class="btn btn-default pull-right" style="margin-right: 15px;"/>
                </c:if>
                <button type="submit" class="btn btn-default pull-right" style="margin-right: 15px;">Salveaz&#259;</button>
            </div>
        </form:form>
    </div>
<input id="deleteGraveURL" type="hidden" value="${contextPath}/delete/"/>
</body>
</html>
