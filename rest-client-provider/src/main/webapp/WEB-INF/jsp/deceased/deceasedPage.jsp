<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 11/30/2014
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Decedati</title>
</head>
<body>
<jsp:include page="../fragments/menu.jsp"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}/deceased"/>
<div id="deceased-details" style="display: none;">
    <h4 class="text-center">
        <b>Lista decedatilor</b>
    </h4>
    <div style="margin-top: 20px;">
        <div class="form-group col-lg-4">
            <input id="deceasedSearchInput" name="searchCriteria" type="text" class="form-control" placeholder="Cuvinte cheie"/>
        </div>
        <div class="form-group col-lg-4">
            <input id="structureId" name="structureId" type="text" class="form-control" placeholder="Id-ul mormantului"/>
        </div>
        <button type="button" onclick="DeceasedManagerJS.submitFilterForm();" class="btn btn-default">Cauta</button>
    </div>
    <table id="deceased-table" class="table-margins table table-bordered text-center">
        <thead>
        <tr style="background-color: #0080F8;">
            <th class="text-center">Nume</th>
            <th class="text-center">Prenume</th>
            <th class="text-center">CNP</th>
            <th class="text-center">Religie</th>
            <th class="text-center">Data mortii</th>
            <th class="text-center">Id-ul documentului de inmormantare</th>
            <th class="text-center">Id-ul mormantului</th>
            <th class="text-center">Data ingroparii</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="deceased" items="${deceasedList}">
            <tr>
                <td>${deceased.lastName}</td>
                <td>${deceased.firstName}</td>
                <td>${deceased.cnp}</td>
                <td>${deceased.religion}</td>
                <td>${deceased.diedOn}</td>
                <td>${deceased.burialDocumentId}</td>
                <td>${deceased.structureId}</td>
                <td>${deceased.burialOn}</td>
                <td>
                    <a href="${contextPath}/get/${deceased.id}" ><img class="action-icon" src="<c:url value="/resources/icons/info.png" />"/></a>
                    <a href="${contextPath}/delete/${deceased.id}" ><img class="action-icon" src="<c:url value="/resources/icons/trashcan.png" />"/></a>
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
                    <li onclick="DeceasedManagerJS.getPerPage(${i})" style="cursor:pointer;"><a class="page">${i}</a></li>
                </c:forEach>
                <li><a><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>
            </ul>
        </nav>
    </c:if>
    <div style="margin-right: 20px; margin-bottom: 20px; float: right;">
        <button onclick="DeceasedManagerJS.renderAddPage();" type="submit" class="btn btn-default">Adauga decedat</button>
    </div>
</div>
<input id="addDeceasedPageURL" type="hidden" value="${pageContext.request.contextPath}/deceased/add"/>
<input id="deceasedFilterURL" type="hidden" value="${pageContext.request.contextPath}/deceased/filter"/>
<input id="deceasedURL" type="hidden" value="${pageContext.request.contextPath}/deceased"/>
<input id="refreshDeceasedFilterURL" type="hidden" value="${pageContext.request.contextPath}/deceased/refreshFilter"/>
</body>
</html>
