<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Morminte</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/graves.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.easyPaginate.js"></script>
</head>
<body>
    <c:set var="contextPath" value="${pageContext.request.contextPath}/grave"/>
    <jsp:include page="../fragments/menu.jsp"/>
    <div id="grave-details" style="display: none;">
        <c:if test="${not empty errors}">
            <p class="alert alert-danger">${errors}</p>
        </c:if>
        <h4 class="text-center">
            <b>Lista mormintelor</b>
        </h4>
        <div style="margin-top: 20px;">
            <div class="form-group col-lg-4">
                <input id="graveParcelIdInput" type="text" class="form-control" placeholder="Id-ul parcelei">
            </div>
          <button type="submit" onclick="GraveManagerJS.submitFilterForm();" class="btn btn-default">Cauta</button>
          <button type="submit" onclick="GraveManagerJS.refreshFilter();" class="btn btn-default">Resetati filtrele</button>
        </div>
        <table id="graves-table" class="table-margins table table-bordered text-center">
            <thead>
            <tr style="background-color: #0080F8;">
                <th class="text-center">Parcela</th>
                <th class="text-center">Creat in</th>
                <th class="text-center">Latime</th>
                <th class="text-center">Lungime</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="grave" items="${graveList}">
                <tr>
                    <td>${grave.parcelId}</td>
                    <fmt:message key="date.pattern" var="pattern"/>
                    <td><fmt:formatDate value="${grave.createdOn}" pattern="${pattern}"/></td>
                    <td>${grave.width}</td>
                    <td>${grave.length}</td>
                    <td>
                        <a href="${contextPath}/filterAction/${grave.id}/contract" ><img class="action-icon tiptip" title="<fmt:message key='grave.filter.contracts'/>" src="<c:url value="/resources/icons/contracts.png" />"/></a>
                        <a href="${contextPath}/filterAction/${grave.id}/structureHistory" ><img class="action-icon tiptip" title="<fmt:message key='grave.filter.structureHistiry'/>" src="<c:url value="/resources/icons/parcel_history.png" />"/></a>
                        <a href="${contextPath}/filterAction/${grave.id}/deceased" ><img class="action-icon tiptip" title="<fmt:message key='grave.filter.deceased'/>" src="<c:url value="/resources/icons/deceased.png" />"/></a>
                        <a href="${contextPath}/filterAction/${grave.id}/logs" ><img class="action-icon tiptip" title="<fmt:message key='entity.filter.logs'/>" src="<c:url value="/resources/icons/changes_history.png" />"/></a>
                        <c:if test="${hasAdminRole}">
                            <a href="${contextPath}/delete/${grave.id}" ><img class="action-icon tiptip" title="<fmt:message key='delete.entity'/>" src="<c:url value="/resources/icons/trashcan.png" />"/></a>
                            <a href="${contextPath}/addAction/${grave.id}/contract" >
                                <span class="glyphicon glyphicon-plus tiptip" title="<fmt:message key='grave.add.contract'/>" aria-hidden="true"></span>
                            </a>
                            <a href="${contextPath}/addAction/${grave.id}/structureHistory" >
                                <span class="glyphicon glyphicon-plus tiptip" title="<fmt:message key='grave.add.structureHistiry'/>" aria-hidden="true"></span>
                            </a>
                            <a href="${contextPath}/addAction/${grave.id}/deceased" >
                                <span class="glyphicon glyphicon-plus tiptip" title="<fmt:message key='grave.add.deceased'/>" aria-hidden="true"></span>
                            </a>
                        </c:if>
                        <a href="${contextPath}/get/${grave.id}" ><img class="action-icon tiptip" title="<fmt:message key='view.details'/>" src="<c:url value="/resources/icons/info.png" />"/></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <script type="text/javascript">
                $(document).ready(function() {
                    $(".easypagination").easyPaginate({
                        onClickcallback: function (page) {
                            getCemeteriesPerPage(page);
                        }
                    });
                });
            </script>
        </table>
        <c:if test="${pages gt 0}">
            <div class="easypagination" style="text-align: center;" pages="${pages}"></div>
        </c:if>
        <c:if test="${hasAdminRole}">
            <div style="margin-right: 20px; float: right;">
                <button id="addButton" onclick="GraveManagerJS.renderAddPage();" type="submit" class="btn btn-default">Adauga mormant</button>
            </div>
        </c:if>
    </div>
</body>
<input id="addGravePageURL" type="hidden" value="${contextPath}/add"/>
<input id="graveFilterURL" type="hidden" value="${contextPath}/filter"/>
<input id="gravesURL" type="hidden" value="${contextPath}"/>
<input id="refreshGraveFilterURL" type="hidden" value="${contextPath}/refreshFilter"/>
</html>
