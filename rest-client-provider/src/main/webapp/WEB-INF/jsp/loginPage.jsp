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
    <link href="<c:url value="/resources/css/login.css"/>" rel="stylesheet">
</head>
<body>
    <div class="login-form">
        <form id="loginForm" class="form-horizontal" action="#">
            <div class="form-group">
                <label class="control-label label-white" for="username">Utilizator</label>
                <div class="col-lg-8" style="float: right;">
                    <input id="username" class="form-control" type="text">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label label-white" for="password">Parola</label>
                <div class="col-lg-8" style="float: right;">
                    <input id="password" class="form-control" type="text">
                </div>
            </div>

            <button type="submit" class="btn btn-default pull-right">Logheaza-te</button>
        </form>
    </div>
</body>
</html>
