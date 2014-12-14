<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 12/14/2014
  Time: 6:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title></title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/logs.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}/logs"/>
<jsp:include page="../fragments/menu.jsp"/>
    <div id="log-details" style="display: none;">
        <h4 class="text-center">
            <b>Istoric modificari</b>
        </h4>

        <div style="margin-top: 20px;">
            <div class="form-group col-lg-3">
                <input id="logSearchInput" type="text" class="form-control" placeholder="Cuvint cheie">
            </div>
            <div class="col-lg-2">
                <select id="tableSelect">
                    <option>--Tabela--</option>
                    <option>restingplacerequests</option>
                    <option>cemeteries</option>
                    <option>clients</option>
                    <option>contracts</option>
                    <option>deceased</option>
                    <option>graves</option>
                    <option>monuments</option>
                    <option>parcels</option>
                </select>
            </div>
            <div class="form-group col-lg-4">
                <input id="tableIdInput" name="tableIdInput" type="text" class="form-control" placeholder="Id-ul tabelei"/>
            </div>
            <button type="button" onclick="LogsManagerJS.submitFilter();" class="btn btn-default">Cauta</button>
        </div>

        <table class="table-margins table table-bordered text-center">
            <thead>
            <tr style="background-color: #0080F8;">
                <th class="text-center">Tabel modificat</th>
                <th class="text-center">Id afectat</th>
                <th class="text-center">Data modificarii</th>
                <th class="text-center">Actiunea</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="log" items="${logList}">
                <tr>
                    <td>${log.tableChanged}</td>
                    <td>${log.idAffected}</td>
                    <td>${log.tookPlaceOn}</td>
                    <td>${log.action}</td>
                    <td>
                        <a href="${contextPath}/get/${log.id}" ><img class="action-icon" src="<c:url value="/resources/icons/info.png" />"/></a>
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
    </div>
</body>
<input id="logsFilterURL" type="hidden" value="${contextPath}/filter"/>
<input id="refreshLogFilterURL" type="hidden" value="${contextPath}/refreshFilter"/>
</html>
