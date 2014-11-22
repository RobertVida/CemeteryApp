<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 11/15/2014
  Time: 12:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title></title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
</head>
<body>
    <jsp:include page="fragments/menu.jsp"/>
    <div id="clients-container" style="display: none;">
        <h4 class="text-center">
            <b>Lista clien&#355;ilor</b>
        </h4>
        <div style="margin-top: 20px;">
            <div class="form-group col-lg-4">
                <input type="text" class="form-control">
          </div>
          <button type="submit" class="btn btn-default">Cauta</button>
        </div>
        <table class="table-margins table table-bordered text-center">
            <thead>
            <tr style="background-color: #0080F8;">
                <th class="text-center">Id</th>
                <th class="text-center">Nume</th>
                <th class="text-center">Prenume</th>
                <th class="text-center">CNP</th>
                <th class="text-center">Nr.telefon</th>
                <th class="text-center">Adres&#259;</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
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
            <button id="addButton" type="submit" class="btn btn-default">Adauga client</button>
        </div>
    </div>
</body>
<input id="getClientsURL" type="hidden" value="${getClients}"/>
</html>
