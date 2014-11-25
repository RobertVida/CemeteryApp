<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 11/10/2014
  Time: 6:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/header.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/menu.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/dashboard.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/clients.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/cemeteries.css"/>" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/resources/js/clients.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/cemetery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/graves.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/deceased.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/menu.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="navbar navbar-inverse navbar-static-top" role="navigation">
        <a href="${pageContext.request.contextPath}/dashboard"><img class="home-icon" src="<c:url value="/resources/icons/home.png" />"/></a>
        <span class="hello-username white">Buna username!</span>
    </div>
</body>
</html>
