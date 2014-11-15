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
    </div>
</body>
</html>
