<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 11/10/2014
  Time: 7:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <div class="menu">
        <div class="submenu-left">
            <div class="submenu-item">
                <a href="${contextPath}/clients">
                    <img src="<c:url value="/resources/icons/clients.png" />"/>
                    <span>Clien&#355;i</span>
                </a>
            </div>
            <div class="submenu-item">
                <a href="${contextPath}/contract">
                    <img src="<c:url value="/resources/icons/contracts.png" />"/>
                    <span>Contracte</span>
                </a>
            </div>
            <div class="submenu-item">
                <a href="${contextPath}/deceased">
                    <img src="<c:url value="/resources/icons/deceased.png" />"/>
                    <span>Deceda&#355;i</span>
                </a>
            </div>
            <div class="submenu-item">
                <a href="${contextPath}/request">
                    <img src="<c:url value="/resources/icons/requests.png" />"/>
                    <span>Cereri</span>
                </a>
            </div>
            <div class="submenu-item last-item">
                <a href="${contextPath}/logs">
                    <img src="<c:url value="/resources/icons/changes_history.png" />"/>
                    <span>Istoric modific&#259;ri</span>
                </a>
            </div>
        </div>
        <div id="container" class="content">
        </div>
        <div class="submenu-right">
            <div class="submenu-item">
                <a href="${contextPath}/cemetery">
                    <img src="<c:url value="/resources/icons/cemetery.png" />"/>
                    <span>Cimitire</span>
                </a>
            </div>
            <div class="submenu-item">
                <a href="${contextPath}/parcel">
                    <img src="<c:url value="/resources/icons/parcels.png" />"/>
                    <span>Parcele</span>
                </a>
            </div>
            <div class="submenu-item">
                <a href="${contextPath}/grave">
                    <img src="<c:url value="/resources/icons/graves.png" />"/>
                    <span>Morminte</span>
                </a>
            </div>
            <div class="submenu-item">
                <a href="${contextPath}/monument">
                    <img src="<c:url value="/resources/icons/monument.png" />"/>
                    <span>Monumente</span>
                </a>
            </div>
            <div class="submenu-item last-item">
                <a href="${contextPath}/structureHistory">
                    <img src="<c:url value="/resources/icons/parcel_history.png" />"/>
                    <span>Istoric structuri</span>
                </a>
            </div>
        </div>
    </div>
</body>
</html>
