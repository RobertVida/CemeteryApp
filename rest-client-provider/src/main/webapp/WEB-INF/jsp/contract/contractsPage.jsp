<%--
  Created by IntelliJ IDEA.
  User: Cata
  Date: 1/5/2015
  Time: 6:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Contracte</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/contract.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}/contract"/>
<jsp:include page="../fragments/menu.jsp"/>
<div id="contract-details" style="display: none;">
    <h4 class="text-center">
        <b>Lista contractelor</b>
    </h4>
    <div style="margin-top: 20px;">
        <div class="form-group col-lg-4">
            <input id="contractStructureIdInput" type="text" class="form-control" placeholder="Id-ul structurii">
        </div>
        <button type="submit" onclick="ContractManagerJS.submitFilterForm();" class="btn btn-default">Cauta</button>
        <button type="submit" onclick="ContractManagerJS.refreshFilter();" class="btn btn-default">Resetati filtrele</button>
    </div>
    <table id="contracts-table" class="table-margins table table-bordered text-center">
        <thead>
        <tr style="background-color: #0080F8;">
            <th class="text-center">Id-ul structurii</th>
            <th class="text-center">Id-ul cererei</th>
            <th class="text-center">Semnat la</th>
            <th class="text-center">Expira la</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="contract" items="${contractList}">
            <tr>
                <td>${contract.structureId}</td>
                <td>${contract.requestId}</td>
                <fmt:message key="date.pattern" var="pattern"/>
                <td><fmt:formatDate value="${contract.signedOn}" pattern="${pattern}"/></td>
                <td><fmt:formatDate value="${contract.expiresOn}" pattern="${pattern}"/></td>
                <td>
                    <c:if test="${hasAdminRole}">
                        <a href="${contextPath}/delete/${contract.id}" ><img class="action-icon tiptip" title="<fmt:message key='delete.entity'/>" src="<c:url value="/resources/icons/trashcan.png" />"/></a>
                        <a href="${contextPath}/printable/${contract.id}" ><img class="action-icon tiptip" title="<fmt:message key='view.contract.pdf'/>" src="<c:url value="/resources/icons/contracts.png" />"/></a>
                    </c:if>
                    <a href="${contextPath}/get/${contract.id}" ><img class="action-icon tiptip" title="<fmt:message key='view.details'/>" src="<c:url value="/resources/icons/info.png" />"/></a>
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
                    <li onclick="ContractManagerJS.getContractPerPage(${i});" style="cursor:pointer;"><a class="page">${i}</a></li>
                </c:forEach>
                <li><a><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>
            </ul>
        </nav>
    </c:if>
    <c:if test="${hasAdminRole}">
        <div style="margin-right: 20px; float: right;">
            <button id="addButton" onclick="ContractManagerJS.renderAddPage();" type="submit" class="btn btn-default">Adauga contract</button>
        </div>
    </c:if>
</div>
</body>
<input id="addContractPageURL" type="hidden" value="${contextPath}/add"/>
<input id="contractFilterURL" type="hidden" value="${contextPath}/filter"/>
<input id="contractsURL" type="hidden" value="${contextPath}"/>
<input id="refreshContractFilterURL" type="hidden" value="${contextPath}/refreshFilter"/>
</html>

