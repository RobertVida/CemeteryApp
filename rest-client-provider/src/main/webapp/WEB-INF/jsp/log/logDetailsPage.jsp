<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 12/14/2014
  Time: 6:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title></title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/logs.js"></script>
</head>
<body>
<jsp:include page="../fragments/menu.jsp"/>
    <div id="log-details" style="margin-top: 20px; display: none;">
        <c:if test="${not empty errors}">
            <p class="alert alert-danger">${errors}</p>
        </c:if>
        <h4 class="text-center">
            <b>Informa&#355;iile modficarii</b>
        </h4>
            <div class="details">
                <div>
                    <div class="col-lg-4">
                        <label class="control-label">Tabela:</label>
                    </div>
                    <div class="col-lg-8" style="word-wrap: break-word;">
                        <p>${log.tableChanged}</p>
                    </div>
                </div>

                <div>
                    <div class="col-lg-4">
                        <label class="control-label">Modificat de:</label>
                    </div>
                    <div class="col-lg-8" style="word-wrap: break-word;">
                        <p>${log.userId}</p>
                    </div>
                </div>

                <div>
                    <div class="col-lg-4">
                        <label class="control-label">Id afectat:</label>
                    </div>
                    <div class="col-lg-8" style="word-wrap: break-word;">
                        <p>${log.idAffected}</p>
                    </div>
                </div>

                <div>
                    <div class="col-lg-4">
                        <label class="control-label">Data modificarii:</label>
                    </div>
                    <div class="col-lg-8" style="word-wrap: break-word;">
                        <fmt:message key="date.pattern" var="pattern"/>
                        <p><fmt:formatDate value="${log.tookPlaceOn}" pattern="${pattern}"/></p>
                    </div>
                </div>

                <div>
                    <div class="col-lg-4">
                        <label class="control-label">Actiunea:</label>
                    </div>
                    <div class="col-lg-8" style="word-wrap: break-word;">
                        <p>${log.action}</p>
                    </div>
                </div>

                <div>
                    <div class="col-lg-4">
                        <label class="control-label">Valoarea veche:</label>
                    </div>
                    <div class="col-lg-6" style="word-wrap: break-word;">
                        <p>${log.oldValue}</p>
                    </div>
                </div>

                <div>
                    <div class="col-lg-4">
                        <label class="control-label">Valoarea noua:</label>
                    </div>
                    <div class="col-lg-6" style="word-wrap: break-word;">
                        <p>${log.newValue}</p>
                    </div>
                </div>
            </div>
    </div>
</body>
</html>
