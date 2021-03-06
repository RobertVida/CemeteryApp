<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 11/10/2014
  Time: 6:06 PM
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
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div id="dashboard-container">
    <div class="box">
        <div class="box-image">
            <img src="<c:url value="/resources/icons/registru_decedati.png" />"/>
        </div>
        <div class="box-content">
            <a href="${contextPath}/registers/burialRegistry">
                <h4 class="truncate">Registrul anual de programare a inmormantarilor</h4>
                <p class="truncate">Contine date despre decedatii care se inmormanteaza in cimitire.</p>
            </a>
        </div>
    </div>
    <div class="box">
        <div class="box-image">
            <img src="<c:url value="/resources/icons/deceased.png" />"/>
        </div>
        <div class="box-content">
            <a href="${contextPath}/registers/graveRegistry">
                <h4 class="truncate">Registrul de morminte</h4>
                <p class="truncate">Contine date despre toate mormintele din cimitir.</p>
            </a>
        </div>
    </div>
    <div class="box">
        <div class="box-image">
            <img src="<c:url value="/resources/icons/monument.png" />"/>
        </div>
        <div class="box-content">
            <a href="${contextPath}/registers/monumentRegistry">
                <h4 class="truncate">Registrul de monumente funerare</h4>
                <p class="truncate">Contine date despre monumentele din cimitir.</p>
            </a>
        </div>
    </div>
    <div class="box">
        <div class="box-image">
            <img src="<c:url value="/resources/icons/deceased.png" />"/>
        </div>
        <div class="box-content">
            <a href="${contextPath}/registers/deceasedRegistry">
                <h4 class="truncate">Registrul index anual al decedatilor</h4>
                <p class="truncate">Contine date despre toti decedatii din cimitir.</p>
            </a>
        </div>
    </div>
    <div class="box">
        <div class="box-image">
            <img src="<c:url value="/resources/icons/deceased.png" />"/>
        </div>
        <div class="box-content">
            <a href="${contextPath}/registers/deceasedNoCaregiverRegistry">
                <h4 class="truncate">Registrul anual al decedatilor fara apartinator</h4>
                <p class="truncate">Contine date despre decedatii fara apartinator din cimitir.</p>
            </a>
        </div>
    </div>
    <div class="box">
        <div class="box-image">
            <img src="<c:url value="/resources/icons/requests.png" />"/>
        </div>
        <div class="box-content">
            <a href="${contextPath}/registers/requestRegistry">
                <h4 class="truncate">Registrul cererilor de atribuire a locurilor de inhumare</h4>
                <p class="truncate">Contine date despre cererile de atribuire a locurilor de inhumare depuse de clienti.</p>
            </a>
        </div>
    </div>
    <div class="box">
        <div class="box-image">
            <img src="<c:url value="/resources/icons/contracts.png" />"/>
        </div>
        <div class="box-content">
            <a href="${contextPath}/registers/contractRegistry">
                <h4 class="truncate">Registrul anual de evidenta a contractelor de concesiune</h4>
                <p class="truncate">Contine date despre contractele de concesiune eliberate.</p>
            </a>
        </div>
    </div>
    <div style="text-align: center; margin: 30px;">
        <input type="button" class="btn btn-info" onclick="window.location=document.getElementById('showStatistics').value" value="Show statistics"/>
    </div>
</div>
<input id="showStatistics" type=hidden value="${contextPath}/printable/statistics"/>
</body>
</html>
