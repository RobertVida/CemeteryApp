<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 12/27/2014
  Time: 5:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Monumente</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/monuments.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}${monumentPath}"/>
<jsp:include page="../fragments/menu.jsp"/>
<div id="monument-details" style="display: none;">
    <h4 class="text-center">
        <b>Lista monumentelor</b>
    </h4>
    <div style="margin-top: 20px;">
        <div class="form-group col-lg-4">
            <input id="monumentSearchInput" type="text" class="form-control" placeholder="Cuvint cheie">
        </div>
        <div class="form-group col-lg-4">
            <input id="monumentParcelIdInput" type="text" class="form-control" placeholder="Id-ul parcelei">
        </div>
        <button type="submit" onclick="MonumentsManagerJS.submitFilterForm();" class="btn btn-default">Cauta</button>
        <button type="submit" onclick="MonumentsManagerJS.refreshFilter();" class="btn btn-default">Resetati filtrele</button>
    </div>
    <table id="monuments-table" class="table-margins table table-bordered text-center">
        <thead>
        <tr style="background-color: #0080F8;">
            <th class="text-center">Parcela</th>
            <th class="text-center">Creat in</th>
            <th class="text-center">Latime</th>
            <th class="text-center">Lungime</th>
            <th class="text-center">Name</th>
            <th class="text-center">Descriere</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="monument" items="${monumentList}">
            <tr>
                <td>${monument.parcelId}</td>
                <fmt:message key="date.pattern" var="pattern"/>
                <td><fmt:formatDate value="${monument.createdOn}" pattern="${pattern}"/></td>
                <td>${monument.width}</td>
                <td>${monument.length}</td>
                <td>${monument.name}</td>
                <td>${monument.description}</td>
                <td>
                    <c:if test="${hasAdminRole}">
                        <a href="${contextPath}/delete/${monument.id}" ><img class="action-icon" src="<c:url value="/resources/icons/trashcan.png" />"/></a>
                    </c:if>
                    <a href="${contextPath}/get/${monument.id}" ><img class="action-icon" src="<c:url value="/resources/icons/info.png" />"/></a>
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
                    <li onclick="MonumentsManagerJS.getMonumentPerPage(${i});" style="cursor:pointer;"><a class="page">${i}</a></li>
                </c:forEach>
                <li><a><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>
            </ul>
        </nav>
    </c:if>
    <c:if test="${hasAdminRole}">
        <div style="margin-right: 20px; float: right;">
            <button id="addButton" onclick="MonumentsManagerJS.renderAddPage();" type="submit" class="btn btn-default">Adauga monument</button>
        </div>
    </c:if>
</div>
</body>
<input id="addMonumentPageURL" type="hidden" value="${contextPath}/add"/>
<input id="monumentFilterURL" type="hidden" value="${contextPath}/filter"/>
<input id="monumentsURL" type="hidden" value="${contextPath}"/>
<input id="refreshMonumentFilterURL" type="hidden" value="${contextPath}/refreshFilter"/>
</html>
