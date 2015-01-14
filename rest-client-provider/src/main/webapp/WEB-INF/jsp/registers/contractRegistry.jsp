<%--
  Created by IntelliJ IDEA.
  User: Cata
  Date: 1/10/2015
  Time: 9:56 PM
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
<div id="contract-registry-details" style="display: none;">
    <h4 class="text-center">
        <b>Registrul anual de evidenta a contractelor de concesiune</b>
    </h4>
    <div style="margin-top: 20px;">
        <div class="form-group col-lg-4">
            <input id="crSearchInput" name="searchCriteria" type="text" class="form-control" required="true"/>
        </div>
        <button type="button" onclick="applyRBFilter();" class="btn btn-default">Cauta</button>
        <button type="button" onclick="refreshRBFilter();" class="btn btn-default">Resetati filtrele</button>
    </div>
    <table id="contract-registry-table" class="table-margins table table-bordered text-center">
        <thead>
        <tr style="background-color: #0080F8;">
            <th class="text-center">Id-ul contractului</th>
            <th class="text-center">Semnat la</th>
            <th class="text-center">Numele clientului</th>
            <th class="text-center">Prenumele clientului</th>
            <th class="text-center">Adresa clientului</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="registry" items="${contractRegistryList}">
            <tr>
                <td>${registry.contractId}</td>
                <fmt:message key="date.pattern" var="pattern"/>
                <td><fmt:formatDate value="${registry.signedOn}" pattern="${pattern}"/></td>
                <td>${registry.clientLastName}</td>
                <td>${registry.clientFirstName}</td>
                <td>${registry.clientAddress}</td>
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
<input id="crURL" type="hidden" value="${pageContext.request.contextPath}/registers/contractRegistry"/>
<input id="crFilterURL" type="hidden" value="${pageContext.request.contextPath}/registers/contractRegistry/filter"/>
<input id="crRefreshFilterURL" type="hidden" value="${pageContext.request.contextPath}/registers/contractRegistry/refreshFilter"/>
<script type="text/javascript">
    $(document).ready(function() {
        $('#container').html($('#contract-registry-details').html());

        $(".easypagination").easyPaginate({
            onClickcallback : function(page) {
                getPerPage(page);
            }
        });
    });
    function getPerPage(pageNo) {
        var url = $('#crURL').val();
        var data = { pageNo : pageNo };
        CemeteryJs.ajaxCall("GET", url, data, 0, "#contract-registry-table");
    }

    function applyRBFilter() {
        var url = $('#crFilterURL').val();
        var searchCriteria = $('#crSearchInput').val();
        var data = { searchCriteria : searchCriteria };
        CemeteryJs.ajaxCall("GET", url, data, 1, '#contract-registry-details');
    }

    function refreshRBFilter() {
        var url = $("#crRefreshFilterURL").val();
        CemeteryJs.ajaxCall("POST", url, null, 1, '#contract-registry-details');
    }
</script>
