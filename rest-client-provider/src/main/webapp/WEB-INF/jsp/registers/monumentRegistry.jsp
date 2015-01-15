<%--
  Created by IntelliJ IDEA.
  User: Cata
  Date: 1/10/2015
  Time: 5:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Registru</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.easyPaginate.js"></script>
</head>
<body>
<jsp:include page="../fragments/menu.jsp"/>
<div id="monument-registry-details" style="display: none;">
    <c:if test="${not empty errors}">
        <p class="alert alert-danger">${errors}</p>
    </c:if>
    <h4 class="text-center">
        <b>Registrul de monumente funerare</b>
    </h4>
    <div style="margin-top: 20px;">
        <div class="form-group col-lg-4">
            <input id="mrSearchInput" name="searchCriteria" type="text" class="form-control" required="true"/>
        </div>
        <button type="button" onclick="applyRBFilter();" class="btn btn-default">Cauta</button>
        <button type="button" onclick="refreshRBFilter();" class="btn btn-default">Resetati filtrele</button>
    </div>
    <table id="monument-registry-table" class="table-margins table table-bordered text-center">
        <thead>
        <tr style="background-color: #0080F8;">
            <th class="text-center">Cimitir</th>
            <th class="text-center">Parcela</th>
            <th class="text-center">Id-ul monumentului</th>
            <th class="text-center">Numele proprietarului</th>
            <th class="text-center">Prenumele proprietarului</th>
            <th class="text-center">Adresa proprietarului</th>
            <th class="text-center">Numar chitanta</th>
            <th class="text-center">Nume decedat</th>
            <th class="text-center">Prenume decedat</th>
            <th class="text-center">Ingropat la</th>
            <th class="text-center">Suprafata</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="registry" items="${monumentRegistryList}">
            <tr>
                <td>${registry.cemeteryName}</td>
                <td>${registry.parcelName}</td>
                <td>${registry.monumentId}</td>
                <td>${registry.ownerLastName}</td>
                <td>${registry.ownerFirstName}</td>
                <td>${registry.ownerAddress}</td>
                <td>${registry.receiptNumber}</td>
                <td>${registry.deceasedLastName}</td>
                <td>${registry.deceasedFirstName}</td>
                <fmt:message key="date.pattern" var="pattern"/>
                <td><fmt:formatDate value="${registry.burialDate}" pattern="${pattern}"/></td>
                <td>${registry.surface}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${pages gt 0}">
        <div class="easypagination" style="text-align: center;" pages="${pages}"></div>
    </c:if>
</div>
</body>
</html>
<input id="mrURL" type="hidden" value="${pageContext.request.contextPath}/registers/monumentRegistry"/>
<input id="mrFilterURL" type="hidden" value="${pageContext.request.contextPath}/registers/monumentRegistry/filter"/>
<input id="mrRefreshFilterURL" type="hidden" value="${pageContext.request.contextPath}/registers/monumentRegistry/refreshFilter"/>
<script type="text/javascript">
    $(document).ready(function() {
        $('#container').html($('#monument-registry-details').html());

        CemeteryJs.easyPagination(getPerPage);
    });
    function getPerPage(pageNo) {
        var url = $('#grURL').val();
        var data = { pageNo : pageNo };
        CemeteryJs.ajaxCall("GET", url, data, 0, "#monument-registry-table");
    }

    function applyRBFilter() {
        var url = $('#mrFilterURL').val();
        var searchCriteria = $('#mrSearchInput').val();
        var data = { searchCriteria : searchCriteria };
        CemeteryJs.ajaxCall("GET", url, data, 1, '#monument-registry-details', getPerPage);
    }

    function refreshRBFilter() {
        var url = $("#mrRefreshFilterURL").val();
        CemeteryJs.ajaxCall("POST", url, null, 1, '#monument-registry-details', getPerPage);
    }
</script>
