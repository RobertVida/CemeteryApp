<%--
  Created by IntelliJ IDEA.
  User: Tudor
  Date: 1/5/2015
  Time: 7:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Istoric structuri</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/structure_history.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/zebra_datepicker.src.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.easyPaginate.js"></script>
</head>
<body>
<jsp:include page="../fragments/menu.jsp"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}/structureHistory"/>
<div id="structure-details" style="display: none;">
    <c:if test="${not empty errors}">
        <p class="alert alert-danger">${errors}</p>
    </c:if>
    <h4 class="text-center">
        <b>Istoric structuri</b>
    </h4>
    <div style="margin-top: 20px;">
        <div class="form-group col-lg-4">
            <input id="structureHistorySearchInput" name="searchCriteria" type="text" class="form-control" placeholder="Cuvinte cheie"/>
        </div>
        <div class="form-group col-lg-4">
            <input id="structureId" name="structureId" type="text" class="form-control" placeholder="Id-ul structurii"/>
        </div>
        <button type="button" onclick="StructureHistoryManagerJS.submitFilterForm();" class="btn btn-default">Cauta</button>
        <button type="button" onclick="StructureHistoryManagerJS.refreshFilter();" class="btn btn-default">Resetati filtrele</button>
    </div>
    <table id="structure-table" class="table-margins table table-bordered text-center">
        <thead>
        <tr style="background-color: #0080F8;">
            <th class="text-center">Id structura</th>
            <th class="text-center">Descriere</th>
            <th class="text-center">Data</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="structure" items="${structureList}">
            <tr>
                <td>${structure.structureId}</td>
                <td>${structure.description}</td>

                <fmt:message key="date.pattern" var="pattern"/>
                <td><fmt:formatDate value="${structure.date}" pattern="${pattern}"/></td>

                <td>
                    <a href="${contextPath}/get/${structure.id}" ><img class="action-icon" src="<c:url value="/resources/icons/info.png" />"/></a>
                    <c:if test="${hasAdminRole}">
                        <a href="${contextPath}/delete/${structure.id}" ><img class="action-icon" src="<c:url value="/resources/icons/trashcan.png" />"/></a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
        <script type="text/javascript">
            $(document).ready(function() {
                $(".easypagination").easyPaginate({
                    onClickcallback: function (page) {
                        StructureHistoryManagerJS.getPerPage(page);
                    }
                });
            });
        </script>
    </table>
    <c:if test="${pages gt 0}">
        <div class="easypagination" style="text-align: center;" pages="${pages}"></div>
    </c:if>
    <c:if test="${hasAdminRole}">
        <div style="margin-right: 20px; margin-bottom: 20px; float: right;">
            <button onclick="StructureHistoryManagerJS.renderAddPage();" type="submit" class="btn btn-default">Adauga structura</button>
        </div>
    </c:if>
</div>
<input id="addStructureHistoryPageURL" type="hidden" value="${pageContext.request.contextPath}/structureHistory/add"/>
<input id="structureHistoryFilterURL" type="hidden" value="${pageContext.request.contextPath}/structureHistory/filter"/>
<input id="structureHistoryURL" type="hidden" value="${pageContext.request.contextPath}/structureHistory"/>
<input id="refreshStructureHistoryFilterURL" type="hidden" value="${pageContext.request.contextPath}/structureHistory/refreshFilter"/>
</body>
</html>
