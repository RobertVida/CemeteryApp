<%--
  Created by IntelliJ IDEA.
  User: Cata
  Date: 1/10/2015
  Time: 5:45 PM
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
    <script src="${pageContext.request.contextPath}/resources/js/deceasedRegistry.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.easyPaginate.js"></script>
</head>
<body>
<jsp:include page="../fragments/menu.jsp"/>
<div id="deceased-registry-details" style="display: none;">
    <c:if test="${not empty errors}">
        <p class="alert alert-danger">${errors}</p>
    </c:if>
    <h4 class="text-center">
        <b>Registrul index anual al decedatilor</b>
    </h4>
    <div style="margin-top: 20px;">
        <div class="form-group col-lg-3">
            <input id="drSearchInput" name="searchCriteria" type="text" class="form-control" required="true"/>
        </div>
        <div class="col-lg-1">
            <label for="nameOrder">Nume</label>
        </div>
        <div class="col-lg-2">
            <select id="nameOrder" class="selectpicker">
                <option value="ASC">ASC</option>
                <option value="DESC">DESC</option>
            </select>
        </div>
        <div class="col-lg-3">
            <label for="nameOrder">Data ingroparii</label>
        </div>
        <div class="col-lg-2">
            <select id="diedOnOrder" class="selectpicker">
                <option value="ASC">ASC</option>
                <option value="DESC">DESC</option>
            </select>
        </div>
    </div>

    <div style="margin-top: 75px; text-align: center;">
        <button type="button" onclick="applyRBFilter();" class="btn btn-default">Cauta</button>
        <button type="button" onclick="refreshRBFilter();" class="btn btn-default">Resetati filtrele</button>
    </div>

    <table id="deceased-registry-table" class="table-margins table table-bordered text-center">
        <thead>
        <tr style="background-color: #0080F8;">
            <th class="text-center">Id-ul decedatului</th>
            <th class="text-center">Nume</th>
            <th class="text-center">Prenume</th>
            <th class="text-center">Cimitir</th>
            <th class="text-center">Parcela</th>
            <th class="text-center">Id-ul mormantului</th>
            <th class="text-center">Ingropat la</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="registry" items="${deceasedRegistryList}">
            <tr>
                <td>${registry.deceasedId}</td>
                <td>${registry.lastName}</td>
                <td>${registry.firstName}</td>
                <td>${registry.cemeteryName}</td>
                <td>${registry.parcelName}</td>
                <td>${registry.graveId}</td>
                <fmt:message key="date.pattern" var="pattern"/>
                <td><fmt:formatDate value="${registry.buriedOn}" pattern="${pattern}"/></td>
            </tr>
        </c:forEach>
        </tbody>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#nameOrder').selectpicker();
                $('#diedOnOrder').selectpicker();
                $(".bootstrap-select").css("width", "75px");
            });
        </script>
    </table>
    <c:if test="${pages gt 0}">
        <div class="easypagination" style="text-align: center;" pages="${pages}"></div>
    </c:if>
</div>
</body>
</html>
<input id="drURL" type="hidden" value="${pageContext.request.contextPath}/registers/deceasedRegistry"/>
<input id="drFilterURL" type="hidden" value="${pageContext.request.contextPath}/registers/deceasedRegistry/filter"/>
<input id="drRefreshFilterURL" type="hidden" value="${pageContext.request.contextPath}/registers/deceasedRegistry/refreshFilter"/>
<script type="text/javascript">

</script>
