<%--
  Created by IntelliJ IDEA.
  User: Cata
  Date: 1/10/2015
  Time: 9:32 PM
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
<div id="request-registry-details" style="display: none;">
    <c:if test="${not empty errors}">
        <p class="alert alert-danger">${errors}</p>
    </c:if>
    <h4 class="text-center">
        <b>Registrul cererilor de atribuire a locurilor de inhumare</b>
    </h4>
    <div style="margin-top: 20px;">
        <div class="form-group col-lg-4">
            <input id="rrSearchInput" name="searchCriteria" type="text" class="form-control" required="true"/>
        </div>
        <button type="button" onclick="applyRBFilter();" class="btn btn-default">Cauta</button>
        <button type="button" onclick="refreshRBFilter();" class="btn btn-default">Resetati filtrele</button>
    </div>
    <table id="request-registry-table" class="table-margins table table-bordered text-center">
        <thead>
        <tr style="background-color: #0080F8;">
            <th class="text-center">Id-ul cererii</th>
            <th class="text-center">Creat la</th>
            <th class="text-center">Numar infocet</th>
            <th class="text-center">Status</th>
            <th class="text-center">Numele clientului</th>
            <th class="text-center">Prenumele clientului</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="registry" items="${requestRegistryList}">
            <tr>
                <td>${registry.requestId}</td>
                <fmt:message key="date.pattern" var="pattern"/>
                <td><fmt:formatDate value="${registry.createdOn}" pattern="${pattern}"/></td>
                <td>${registry.infocetNumber}</td>
                <td>${registry.status}</td>
                <td>${registry.clientLastName}</td>
                <td>${registry.clientFirstName}</td>
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
<input id="rrURL" type="hidden" value="${pageContext.request.contextPath}/registers/requestRegistry"/>
<input id="rrFilterURL" type="hidden" value="${pageContext.request.contextPath}/registers/requestRegistry/filter"/>
<input id="rrRefreshFilterURL" type="hidden" value="${pageContext.request.contextPath}/registers/requestRegistry/refreshFilter"/>
<script type="text/javascript">
    $(document).ready(function() {
        $('#container').html($('#request-registry-details').html());

        $(".easypagination").easyPaginate({
            onClickcallback : function(page) {
                getPerPage(page);
            }
        });
    });
    function getPerPage(pageNo) {
        var url = $('#rrURL').val();
        var data = { pageNo : pageNo };
        CemeteryJs.ajaxCall("GET", url, data, 0, "#request-registry-table");
    }

    function applyRBFilter() {
        var url = $('#rrFilterURL').val();
        var searchCriteria = $('#rrSearchInput').val();
        var data = { searchCriteria : searchCriteria };
        CemeteryJs.ajaxCall("GET", url, data, 1, '#request-registry-details');
    }

    function refreshRBFilter() {
        var url = $("#rrRefreshFilterURL").val();
        CemeteryJs.ajaxCall("POST", url, null, 1, '#request-registry-details');
    }
</script>
