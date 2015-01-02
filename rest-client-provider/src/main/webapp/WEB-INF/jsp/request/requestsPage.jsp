<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 12/27/2014
  Time: 8:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Cereri</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/request.js"></script>
</head>
<body>
<jsp:include page="../fragments/menu.jsp"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}/request"/>
<div id="request-details" style="display: none;">
    <h4 class="text-center">
        <b>Lista cererilor</b>
    </h4>
    <div style="margin-top: 20px;">
        <div class="form-group col-lg-3">
            <input id="requestClientId" name="requestClientId" type="text" class="form-control" placeholder="Id-ul clientului"/>
        </div>
        <div class="form-group col-lg-3">
            <input id="requestSearchInput" name="searchCriteria" type="text" class="form-control" placeholder="Numar"/>
        </div>
        <div class="form-group col-lg-3">
            <input id="requestStatusInput" name="searchCriteria" type="text" class="form-control" placeholder="Status"/>
        </div>
        <button type="button" onclick="RequestsManagerJS.submitFilterForm();" class="btn btn-default">Cauta</button>
        <button type="button" onclick="RequestsManagerJS.refreshFilter();" class="btn btn-default">Resetati filtrele</button>
    </div>
    <table id="requests-table" class="table-margins table table-bordered text-center">
        <thead>
        <tr style="background-color: #0080F8;">
            <th class="text-center">Id-ul clientului</th>
            <th class="text-center">Creat in</th>
            <th class="text-center">Numar</th>
            <th class="text-center">Status</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="request" items="${requestList}">
            <tr>
                <td>${request.clientId}</td>
                <fmt:message key="date.pattern" var="pattern"/>
                <td><fmt:formatDate value="${request.createdOn}" pattern="${pattern}"/></td>
                <td>${request.infocetNumber}</td>
                <td>${request.status}</td>
                <td>
                    <a href="${contextPath}/get/${request.id}" ><img class="action-icon" src="<c:url value="/resources/icons/info.png" />"/></a>
                    <a href="${contextPath}/delete/${request.id}" ><img class="action-icon" src="<c:url value="/resources/icons/trashcan.png" />"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${pages gt 0}">
        <nav style="text-align: center;">
            <ul class="pagination">
                <li><a><span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span></a></li>
                <c:forEach var="i" begin="1" end="${pages}">
                    <li onclick="RequestsManagerJS.getRequestsPerPage(${i})" style="cursor:pointer;"><a class="page">${i}</a></li>
                </c:forEach>
                <li><a><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>
            </ul>
        </nav>
    </c:if>
    <div style="margin-right: 20px; margin-bottom: 20px; float: right;">
        <button onclick="RequestsManagerJS.renderAddPage();" type="submit" class="btn btn-default">Adauga cerere</button>
    </div>
</div>
</body>
<input id="addRequestPageURL" type="hidden" value="${contextPath}/add"/>
<input id="requestFilterURL" type="hidden" value="${contextPath}/filter"/>
<input id="requestsURL" type="hidden" value="${contextPath}"/>
<input id="refreshRequestFilterURL" type="hidden" value="${contextPath}/refreshFilter"/>
</html>
