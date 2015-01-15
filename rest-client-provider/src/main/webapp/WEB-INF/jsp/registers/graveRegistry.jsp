<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 1/7/2015
  Time: 10:41 PM
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
<div id="grave-registry-details" style="display: none;">
    <c:if test="${not empty errors}">
        <p class="alert alert-danger">${errors}</p>
    </c:if>
    <h4 class="text-center">
        <b>Registrul de morminte</b>
    </h4>
    <div style="margin-top: 20px;">
        <div class="form-group col-lg-4">
            <input id="grSearchInput" name="searchCriteria" type="text" class="form-control" required="true"/>
        </div>
        <button type="button" onclick="applyRBFilter();" class="btn btn-default">Cauta</button>
        <button type="button" onclick="refreshRBFilter();" class="btn btn-default">Resetati filtrele</button>
    </div>
    <table id="grave-registry-table" class="table-margins table table-bordered text-center">
        <thead>
        <tr style="background-color: #0080F8;">
            <th class="text-center">Cimitir</th>
            <th class="text-center">Parcela</th>
            <th class="text-center">Id-ul mormantului</th>
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
        <c:forEach var="registry" items="${graveRegistryList}">
            <tr>
                <td>${registry.cemeteryName}</td>
                <td>${registry.parcelName}</td>
                <td>${registry.graveId}</td>
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
        <script type="text/javascript">
            $(document).ready(function() {
                $(".easypagination").easyPaginate({
                    onClickcallback: function (page) {
                        getPerPage(page);
                    }
                });
            });
        </script>
    </table>
    <c:if test="${pages gt 0}">
        <div class="easypagination" style="text-align: center;" pages="${pages}"></div>
    </c:if>
</div>
</body>
</html>
<input id="grURL" type="hidden" value="${pageContext.request.contextPath}/registers/graveRegistry"/>
<input id="grFilterURL" type="hidden" value="${pageContext.request.contextPath}/registers/graveRegistry/filter"/>
<input id="grRefreshFilterURL" type="hidden" value="${pageContext.request.contextPath}/registers/graveRegistry/refreshFilter"/>
<script type="text/javascript">
    $(document).ready(function() {
        $('#container').html($('#grave-registry-details').html());

        $(".easypagination").easyPaginate({
            onClickcallback : function(page) {
                getPerPage(page);
            }
        });
    });
    function getPerPage(pageNo) {
        var url = $('#grURL').val();
        var data = { pageNo : pageNo };
        CemeteryJs.ajaxCall("GET", url, data, 0, "#grave-registry-table");
    }

    function applyRBFilter() {
        var url = $('#grFilterURL').val();
        var searchCriteria = $('#grSearchInput').val();
        var data = { searchCriteria : searchCriteria };
        CemeteryJs.ajaxCall("GET", url, data, 1, '#grave-registry-details');
    }

    function refreshRBFilter() {
        var url = $("#grRefreshFilterURL").val();
        CemeteryJs.ajaxCall("POST", url, null, 1, '#grave-registry-details');
    }
</script>
