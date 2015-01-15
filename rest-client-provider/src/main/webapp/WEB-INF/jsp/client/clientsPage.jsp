<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 11/15/2014
  Time: 12:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title></title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/clients.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.easyPaginate.js"></script>
</head>
<body>
    <jsp:include page="../fragments/menu.jsp"/>
    <c:set var="contextPath" value="${pageContext.request.contextPath}/clients"/>
    <div id="client-details" style="display: none;">
        <c:if test="${not empty errors}">
            <p class="alert alert-danger">${errors}</p>
        </c:if>
        <h4 class="text-center">
            <b>Lista clien&#355;ilor</b>
        </h4>
        <div style="margin-top: 20px;">
            <div class="form-group col-lg-4">
                <input id="searchInput" name="searchCriteria" type="text" class="form-control" required="true"/>
          </div>
          <button id="searchButton" type="button" onclick="ClientsManagerJS.submitFilterForm();" class="btn btn-default">Cauta</button>
            <button type="button" onclick="ClientsManagerJS.refreshFilter();" class="btn btn-default">Resetati filtrele</button>
        </div>
        <table id="clients-table" class="table-margins table table-bordered text-center">
            <thead>
            <tr style="background-color: #0080F8;">
                <%--<th class="text-center">Id</th>--%>
                <th class="text-center">Nume</th>
                <th class="text-center">Prenume</th>
                <th class="text-center">CNP</th>
                <th class="text-center">Nr.telefon</th>
                <th class="text-center">Adres&#259;</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="client" items="${clientList}">
                    <tr>
                        <td>${client.lastName}</td>
                        <td>${client.firstName}</td>
                        <td>${client.cnp}</td>
                        <td>${client.phoneNumber}</td>
                        <td>${client.address}</td>
                        <td>
                            <c:if test="${hasAdminRole}">
                                <a href="${contextPath}/delete/${client.id}" ><img class="action-icon tiptip" title="<fmt:message key='delete.entity'/>" src="<c:url value="/resources/icons/trashcan.png" />"/></a>
                                <a href="${contextPath}/addRequest/${client.id}" >
                                    <span class="glyphicon glyphicon-plus tiptip" title="<fmt:message key='client.add.request'/>" aria-hidden="true"></span>
                                </a>
                            </c:if>
                            <a href="${contextPath}/get/${client.id}" ><img class="action-icon tiptip" title="<fmt:message key='view.details'/>" src="<c:url value="/resources/icons/info.png" />"/></a>
                            <a href="${contextPath}/filterRequests/${client.id}" ><img class="action-icon tiptip" title="<fmt:message key='client.filter.requests'/>" src="<c:url value="/resources/icons/requests.png" />"/></a>
                            <a href="${contextPath}/filterLogs/${client.id}" ><img class="action-icon tiptip" title="<fmt:message key='entity.filter.logs'/>" src="<c:url value="/resources/icons/changes_history.png" />"/></a>
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
                <button onclick="ClientsManagerJS.renderAddPage();" type="submit" class="btn btn-default">Adauga client</button>
            </div>
        </c:if>
    </div>
</body>
<input id="getClientsURL" type="hidden" value="${pageContext.request.contextPath}${clientsPageURL}"/>
<input id="addPageURL" type="hidden" value="${pageContext.request.contextPath}${clientsPageURL}${addPageURL}"/>
<input id="filterURL" type="hidden" value="${pageContext.request.contextPath}${clientsPageURL}${filterURL}"/>
<input id="refreshFilterURL" type="hidden" value="${pageContext.request.contextPath}${clientsPageURL}${refreshFilterURL}"/>
</html>
