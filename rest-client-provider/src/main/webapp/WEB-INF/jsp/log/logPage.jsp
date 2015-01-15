<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 12/14/2014
  Time: 6:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title></title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/logs.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.easyPaginate.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}/logs"/>
<jsp:include page="../fragments/menu.jsp"/>
    <div id="log-details" style="display: none;">
        <c:if test="${not empty errors}">
            <p class="alert alert-danger">${errors}</p>
        </c:if>
        <h4 class="text-center">
            <b>Istoric modificari</b>
        </h4>

        <div style="margin-top: 20px;">
            <div class="col-lg-4">
                <select id="tableSelect" class="selectpicker">
                    <option>--Tabela--</option>
                    <option value="restingplacerequests">Cereri</option>
                    <option value="cemeteries">Cimitire</option>
                    <option value="clients">Clienti</option>
                    <option value="contracts">Contracte</option>
                    <option value="deceased">Decedati</option>
                    <option value="graves">Morminte</option>
                    <option value="monument">Monumente</option>
                    <option value="parcel">Parcele</option>
                </select>
            </div>
            <div class="form-group col-lg-2">
                <input id="tableIdInput" name="tableIdInput" type="text" class="form-control" placeholder="Id-ul tabelei"/>
            </div>
            <button type="button" onclick="LogsManagerJS.submitFilter();" class="btn btn-default">Cauta</button>
            <button type="button" onclick="LogsManagerJS.refreshFilter();" class="btn btn-default">Resetati filtrele</button>
        </div>

        <table id="logs-table" class="table-margins table table-bordered text-center">
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
                    <fmt:message key="date.pattern" var="pattern"/>
                    <td><fmt:formatDate value="${log.tookPlaceOn}" pattern="${pattern}"/></td>
                    <td>${log.action}</td>
                    <td>
                        <a href="${contextPath}/get/${log.id}" ><img class="action-icon tiptip" title="<fmt:message key='view.details'/>" src="<c:url value="/resources/icons/info.png" />"/></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <script type="text/javascript">
                $(document).ready(function() {
                    $('#tableSelect').selectpicker();
                });
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
    </div>
</body>
<input id="logsFilterURL" type="hidden" value="${contextPath}/filter"/>
<input id="refreshLogFilterURL" type="hidden" value="${contextPath}/refreshFilter"/>
<input id="logsUrl" type="hidden" value="${contextPath}"/>
</html>

