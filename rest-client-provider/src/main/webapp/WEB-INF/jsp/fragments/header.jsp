<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 11/10/2014
  Time: 6:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/header.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/menu.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/dashboard.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/clients.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/cemeteries.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap-select.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/zebra_datepicker.css"/>" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/menu.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap-select.min.js"></script>
    <script type="text/plain" src="${pageContext.request.contextPath}/resources/js/bootstrap-select.js.map"></script>
    <script src="${pageContext.request.contextPath}/resources/js/zebra_datepicker.src.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/mainJS.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>
</head>
<body>
    <div class="navbar navbar-inverse navbar-static-top" role="navigation">
        <a href="${pageContext.request.contextPath}/dashboard"><img class="home-icon" src="<c:url value="/resources/icons/home.png" />"/></a>

        <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
        <sec:authentication var="user" property="principal" />
        <c:choose>
            <c:when test="${not empty user}">
                <p>
                    <span class="hello-username">Buna ${user}!</span>
                </p>
                <p>
                    <a class="header-link hello-username" href="${contextPath}/logout">&nbsp;Delogheaza-te</a>
                </p>
            </c:when>
            <c:otherwise>
                <a href="${contextPath}/login" class="header-link login">Logheaza-te</a>
            </c:otherwise>
        </c:choose>

    </div>
</body>
</html>
