<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 11/30/2014
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Decedati</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/deceased.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/zebra_datepicker.src.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.easyPaginate.js"></script>
</head>
<body>
<jsp:include page="../fragments/menu.jsp"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}/deceased"/>
<div id="deceased-details" style="display: none;">
    <c:if test="${not empty errors}">
        <p class="alert alert-danger">${errors}</p>
    </c:if>
    <h4 class="text-center">
        <b>Lista decedatilor</b>
    </h4>
    <div style="margin-top: 20px;">
        <div class="form-group col-lg-4">
            <input id="deceasedSearchInput" name="searchCriteria" type="text" class="form-control" placeholder="Cuvinte cheie"/>
        </div>
        <div class="form-group col-lg-4">
            <input id="structureId" name="structureId" type="text" class="form-control" placeholder="Id-ul mormantului"/>
        </div>
        <button type="button" onclick="DeceasedManagerJS.submitFilterForm();" class="btn btn-default">Cauta</button>
        <button type="button" onclick="DeceasedManagerJS.refreshFilter();" class="btn btn-default">Resetati filtrele</button>
    </div>
    <table id="deceased-table" class="table-margins table table-bordered text-center">
        <thead>
        <tr style="background-color: #0080F8;">
            <th class="text-center">Nume</th>
            <th class="text-center">Prenume</th>
            <th class="text-center">CNP</th>
            <th class="text-center">Religie</th>
            <th class="text-center">Data mortii</th>
            <th class="text-center">Data ingroparii</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="deceased" items="${deceasedList}">
            <tr>
                <td>${deceased.lastName}</td>
                <td>${deceased.firstName}</td>
                <td>${deceased.cnp}</td>
                <td>${deceased.religion}</td>
                <fmt:message key="date.pattern" var="pattern"/>
                <td><fmt:formatDate value="${deceased.diedOn}" pattern="${pattern}"/></td>
                <td><fmt:formatDate value="${deceased.burialOn}" pattern="${pattern}"/></td>
                <td>
                    <a href="${contextPath}/get/${deceased.id}" ><img class="action-icon tiptip" title="<fmt:message key='view.details'/>" src="<c:url value="/resources/icons/info.png" />"/></a>
                    <a href="${contextPath}/filterLogs/${deceased.id}" ><img class="action-icon tiptip" title="<fmt:message key='entity.filter.logs'/>" src="<c:url value="/resources/icons/changes_history.png" />"/></a>
                    <c:if test="${hasAdminRole}">
                        <a href="${contextPath}/delete/${deceased.id}" ><img class="action-icon tiptip" title="<fmt:message key='delete.entity'/>" src="<c:url value="/resources/icons/trashcan.png" />"/></a>
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
            <button onclick="DeceasedManagerJS.renderAddPage();" type="submit" class="btn btn-default">Adauga decedat</button>
        </div>
    </c:if>
</div>
<input id="addDeceasedPageURL" type="hidden" value="${pageContext.request.contextPath}/deceased/add"/>
<input id="deceasedFilterURL" type="hidden" value="${pageContext.request.contextPath}/deceased/filter"/>
<input id="deceasedURL" type="hidden" value="${pageContext.request.contextPath}/deceased"/>
<input id="refreshDeceasedFilterURL" type="hidden" value="${pageContext.request.contextPath}/deceased/refreshFilter"/>
</body>
</html>
