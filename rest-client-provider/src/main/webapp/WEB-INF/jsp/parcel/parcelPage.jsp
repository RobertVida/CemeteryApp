<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 11/24/2014
  Time: 2:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Parcele</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/parcel.js"></script>
</head>
<body>
    <jsp:include page="../fragments/menu.jsp"/>
    <c:set var="contextPath" value="${pageContext.request.contextPath}/parcel"/>
    <div id="parcel-details" style="display: none;">
        <h4 class="text-center">
            <b>Lista parcelelor</b>
        </h4>
        <div style="margin-top: 20px;">
            <div class="form-group col-lg-4">
                <input id="parcelSearchInput" name="searchCriteria" type="text" class="form-control" placeholder="Numele parcelei"/>
            </div>
            <div class="form-group col-lg-4">
                <input id="parcelCemeteryId" name="parcelCemeteryId" type="text" class="form-control" placeholder="Id-ul cimitirului"/>
            </div>
            <button type="button" onclick="ParcelsManagerJS.submitFilterForm();" class="btn btn-default">Cauta</button>
            <button type="button" onclick="ParcelsManagerJS.refreshFilter();" class="btn btn-default">Resetati filtrele</button>
        </div>
        <table id="parcels-table" class="table-margins table table-bordered text-center">
            <thead>
            <tr style="background-color: #0080F8;">
                <th class="text-center">Nume</th>
                <th class="text-center">Cimitir</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="parcel" items="${parcelList}">
                <tr>
                    <td>${parcel.name}</td>
                    <td>${parcel.cemeteryId}</td>
                    <td>
                        <a href="${contextPath}/get/${parcel.id}" ><img class="action-icon" src="<c:url value="/resources/icons/info.png" />"/></a>
                        <a href="${contextPath}/delete/${parcel.id}" ><img class="action-icon" src="<c:url value="/resources/icons/trashcan.png" />"/></a>
                        <a href="${contextPath}/filterStructures/${parcel.id}/grave" ><img class="action-icon" src="<c:url value="/resources/icons/graves.png" />"/></a>
                        <a href="${contextPath}/addStructure/${parcel.id}/grave" >
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                        </a>
                        <a href="${contextPath}/filterStructures/${parcel.id}/monument" ><img class="action-icon" src="<c:url value="/resources/icons/monument.png" />"/></a>
                        <a href="${contextPath}/addStructure/${parcel.id}/monument" >
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                        </a>
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
                        <li onclick="ParcelsManagerJS.getCemeteriesPerPage(${i})" style="cursor:pointer;"><a class="page">${i}</a></li>
                    </c:forEach>
                    <li><a><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>
                </ul>
            </nav>
        </c:if>
        <div style="margin-right: 20px; margin-bottom: 20px; float: right;">
            <button onclick="ParcelsManagerJS.renderAddPage();" type="submit" class="btn btn-default">Adauga parcela</button>
        </div>
    </div>
</body>
<input id="addParcelPageURL" type="hidden" value="${pageContext.request.contextPath}/parcel/add"/>
<input id="parcelFilterURL" type="hidden" value="${pageContext.request.contextPath}/parcel/filter"/>
<input id="parcelsURL" type="hidden" value="${pageContext.request.contextPath}/parcel"/>
<input id="refreshParcelFilterURL" type="hidden" value="${pageContext.request.contextPath}/parcel/refreshFilter"/>
</html>
