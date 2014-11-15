<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 11/15/2014
  Time: 2:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
</head>
<body>
    <jsp:include page="fragments/menu.jsp"/>
    <div id="client-details" style="display: none;">
        <h4 class="text-center">
            <b>Informa&#355;iile clientului __</b>
        </h4>
        <div class="details">
            <div class="form-group">
                <label class="control-label label-white" for="id">Id</label>
                <div class="col-lg-8" style="float: right;">
                    <input id="id" class="form-control" type="text">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label" for="lastName">Nume</label>
                <div class="col-lg-8" style="float: right;">
                    <input id="lastName" class="form-control" type="text">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label" for="firstName">Prenume</label>
                <div class="col-lg-8" style="float: right;">
                    <input id="firstName" class="form-control" type="text">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label" for="cnp">CNP</label>
                <div class="col-lg-8" style="float: right;">
                    <input id="cnp" class="form-control" type="text">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label" for="pohneNumber">Nr. telefon</label>
                <div class="col-lg-8" style="float: right;">
                    <input id="pohneNumber" class="form-control" type="text">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label" for="address">Adres&#259;</label>
                <div class="col-lg-8" style="float: right;">
                    <input id="address" class="form-control" type="text">
                </div>
            </div>

            <button type="submit" class="btn btn-default pull-right" style="margin-right: 15px;">&#x218;terge</button>
            <button type="submit" class="btn btn-default pull-right" style="margin-right: 15px;">Salveaz&#259;</button>
        </div>
    </div>
</body>
</html>
