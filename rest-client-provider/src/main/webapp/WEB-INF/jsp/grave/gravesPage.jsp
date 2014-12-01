<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Morminte</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
</head>
<body>
    <c:set var="contextPath" value="${pageContext.request.contextPath}/grave"/>
    <jsp:include page="../fragments/menu.jsp"/>
    <div id="grave-details" style="display: none;">
        <h4 class="text-center">
            <b>Lista mormintelor</b>
        </h4>
        <div style="margin-top: 20px;">
            <div class="form-group col-lg-4">
                <input id="graveSearchInput" type="text" class="form-control" placeholder="Cuvint cheie">
            </div>
            <div class="form-group col-lg-4">
                <input id="graveParcelIdInput" type="text" class="form-control" placeholder="Id-ul parcelei">
            </div>
          <button type="submit" onclick="GraveManagerJS.submitFilterForm();" class="btn btn-default">Cauta</button>
        </div>
        <table class="table-margins table table-bordered text-center">
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
                    <td>${grave.createdOn}</td>
                    <td>${grave.width}</td>
                    <td>${grave.length}</td>
                    <td>
                        <a href="${contextPath}/get/${grave.id}" ><img class="action-icon" src="<c:url value="/resources/icons/info.png" />"/></a>
                        <a href="${contextPath}/delete/${grave.id}" ><img class="action-icon" src="<c:url value="/resources/icons/trashcan.png" />"/></a>
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
                        <li><a class="page" page-number="${i}">${i}</a></li>
                    </c:forEach>
                    <li><a><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>
                </ul>
            </nav>
        </c:if>
        <div style="margin-right: 20px; float: right;">
            <button id="addButton" onclick="GraveManagerJS.renderAddPage();" type="submit" class="btn btn-default">Adauga mormant</button>
        </div>
    </div>
</body>
<input id="addGravePageURL" type="hidden" value="${contextPath}/add"/>
<input id="graveFilterURL" type="hidden" value="${contextPath}/filter"/>
<input id="gravesURL" type="hidden" value="${contextPath}"/>
<input id="refreshGraveFilterURL" type="hidden" value="${contextPath}/refreshFilter"/>
</html>