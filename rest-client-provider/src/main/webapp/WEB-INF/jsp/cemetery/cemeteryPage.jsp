<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 11/24/2014
  Time: 2:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Cimitire</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/cemetery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.easyPaginate.js"></script>
</head>
<body>
    <jsp:include page="../fragments/menu.jsp"/>
    <c:set var="contextPath" value="${pageContext.request.contextPath}/cemetery"/>
    <div id="cemetery-details" style="display: none;">
        <c:if test="${not empty errors}">
            <p class="alert alert-danger">${errors}</p>
        </c:if>
        <h4 class="text-center">
            <b>Lista cimitirelor</b>
        </h4>
        <div style="margin-top: 20px;">
            <div class="form-group col-lg-4">
                <input id="cemeterySearchInput" name="searchCriteria" type="text" class="form-control"/>
            </div>
            <button type="button" onclick="CemeteriesManagerJS.submitFilterForm();" class="btn btn-default">Cauta</button>
            <button type="button" onclick="CemeteriesManagerJS.refreshFilter();" class="btn btn-default">Resetati filtrele</button>
        </div>
        <table id="cemeteries-table" class="table-margins table table-bordered text-center">
            <thead>
            <tr style="background-color: #0080F8;">
                <th class="text-center">Nume</th>
                <th class="text-center">Adres&#259;</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="cemetery" items="${cemeteryList}">
                <tr>
                    <td>${cemetery.name}</td>
                    <td>${cemetery.address}</td>
                    <td>
                        <a href="${contextPath}/get/${cemetery.id}" ><img class="action-icon tiptip" title="<fmt:message key='view.details'/>" src="<c:url value="/resources/icons/info.png" />"/></a>
                        <a href="${contextPath}/filterParcels/${cemetery.id}" ><img class="action-icon tiptip" title="<fmt:message key='cemetery.filter.parcel'/>" src="<c:url value="/resources/icons/parcels.png" />"/></a>
                        <a href="${contextPath}/filterLogs/${cemetery.id}" ><img class="action-icon tiptip" title="<fmt:message key='entity.filter.logs'/>" src="<c:url value="/resources/icons/changes_history.png" />"/></a>
                        <c:if test="${hasAdminRole}">
                            <a href="${contextPath}/delete/${cemetery.id}" ><img class="action-icon tiptip" title="<fmt:message key='delete.entity'/>" src="<c:url value="/resources/icons/trashcan.png" />"/></a>
                            <a href="${contextPath}/addParcel/${cemetery.id}" >
                                <span class="glyphicon glyphicon-plus tiptip" title="<fmt:message key='cemetery.add.parcel'/>" aria-hidden="true"></span>
                            </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${pages gt 0}">
            <div class="easypagination" style="text-align: center;" pages="${pages}"></div>
        </c:if>
        <c:if test="${hasAdminRole}">
            <div style="margin-right: 20px; margin-bottom: 20px; float: right;">
                <button onclick="CemeteriesManagerJS.renderAddPage();" type="submit" class="btn btn-default">Adauga cimitir</button>
            </div>
        </c:if>
    </div>
</body>
<input id="addPageURL" type="hidden" value="${pageContext.request.contextPath}/cemetery/add"/>
<input id="cemeteryFilterURL" type="hidden" value="${pageContext.request.contextPath}/cemetery/filter"/>
<input id="cemeteriesURL" type="hidden" value="${pageContext.request.contextPath}/cemetery"/>
<input id="refreshFilterURL" type="hidden" value="${pageContext.request.contextPath}/cemetery/refreshFilter"/>
</html>
