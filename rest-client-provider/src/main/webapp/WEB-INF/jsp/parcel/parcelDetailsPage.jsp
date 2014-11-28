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
</head>
<body>
    <jsp:include page="../fragments/menu.jsp"/>
    <c:set var="contextPath" value="${pageContext.request.contextPath}/parcel"/>
    <div id="parcel-details" style="margin-top: 20px; display: none;">
        <h4 class="text-center">
            <c:choose>
                <c:when test="${view eq true}">
                    <c:set var="actionURL" value="${contextPath}/update"/>
                    <b>Informa&#355;iile parcelei ${parcel.name}</b>
                </c:when>
                <c:otherwise>
                    <c:set var="actionURL" value="${contextPath}/add"/>
                    <b>Informa&#355;iile parcelei</b>
                </c:otherwise>
            </c:choose>
        </h4>
        <form:form action="${actionURL}" commandName="parcel" method="post">
            <div class="details">
                <div class="form-group h35">
                    <form:input id="parcelId" path="id" class="form-control" type="hidden"/>
                    <div class="col-lg-4" style="float: left;">
                        <form:label class="control-label" path="name">Nume</form:label>
                    </div>
                    <div class="col-lg-4" style="float: left;">
                        <form:input path="name" class="form-control" type="text"/>
                    </div>
                    <div class="col-lg-4" style="float: left; color: red">
                        <form:errors path="name"/>
                    </div>
                </div>

                <div class="form-group h35">
                    <div class="col-lg-4" style="float: left;">
                        <form:label class="control-label" path="cemeteryId">Cimitir</form:label>
                    </div>
                    <div class="col-lg-4" style="float: left;">
                        <form:input path="cemeteryId" class="form-control" type="text" readonly="${view}"/>
                    </div>
                    <div class="col-lg-4" style="float: left; color: red">
                        <form:errors path="cemeteryId"/>
                    </div>
                </div>

                <c:if test="${view eq true}">
                    <input type="button" onclick="ParcelsManagerJS.deleteParcel();" value="Sterge" class="btn btn-default pull-right" style="margin-right: 15px;"/>
                </c:if>
                <button type="submit" class="btn btn-default pull-right" style="margin-right: 15px;">Salveaz&#259;</button>
            </div>
        </form:form>
    </div>
</body>
<input id="deleteParcelURL" type="hidden" value="${contextPath}/delete/"/>
</html>
