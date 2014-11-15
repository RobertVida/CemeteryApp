<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 11/15/2014
  Time: 1:46 PM
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
<div id="deceased-container" style="display: none;">
    <h4 class="text-center">
        <b>Registrul index anual al deceda&#355;ilor</b>
    </h4>
    <table class="table-margins table table-bordered text-center">
        <thead>
        <tr style="background-color: #0080F8;">
            <th class="text-center">Id</th>
            <th class="text-center">Nume</th>
            <th class="text-center">Prenume</th>
            <th class="text-center">Cimitir</th>
            <th class="text-center">Parcela</th>
            <th class="text-center">Nr.morm&#226;nt</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
</body>
</html>
