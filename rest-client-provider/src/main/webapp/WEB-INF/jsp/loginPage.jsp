<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 11/10/2014
  Time: 2:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Logare</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/login.css"/>" rel="stylesheet">
</head>
<body>
    <div class="login-form">
        <form id="loginForm" class="form-horizontal" action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label style="padding-left: 15px;" class="control-label label-white" for="username">Utilizator</label>
                <div class="col-lg-8" style="float: right;">
                    <input id="username" name="username" class="form-control" type="text" required="true">
                </div>
            </div>

            <div class="form-group">
                <label style="padding-left: 15px;" class="control-label label-white" for="password">Parola</label>
                <div class="col-lg-8" style="float: right;">
                    <input id="password" name="password" class="form-control" type="password" required="true">
                </div>
            </div>

            <c:if test="${not empty error}">
                <p class="alert alert-danger">${error}</p>
            </c:if>

            <button type="submit" style="margin-bottom: 15px;" class="btn btn-default pull-right">Logheaza-te</button>
        </form>
    </div>
</body>
</html>
