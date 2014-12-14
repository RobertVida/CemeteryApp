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
<html>
<head>
    <title></title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/logs.js"></script>
</head>
<body>
<jsp:include page="../fragments/menu.jsp"/>
    <div id="log-details" style="margin-top: 20px; display: none;">
        <h4 class="text-center">
            <b>Informa&#355;iile modficarii</b>
        </h4>
            <div class="details">
                <div class="form-group h35">
                    <div class="col-lg-4" style="float: left;">
                        <label class="control-label" for="tableName">Tabela</label>
                    </div>
                    <div class="col-lg-4" style="float: left;">
                        <input id="tableName" class="form-control" type="text" value="${log.tableChanged}"/>
                    </div>
                </div>

                <div class="form-group h35">
                    <div class="col-lg-4" style="float: left;">
                        <label class="control-label" for="ifAffected">Id afectat</label>
                    </div>
                    <div class="col-lg-4" style="float: left;">
                        <input id="ifAffected" class="form-control" type="text" value="${log.idAffected}"/>
                    </div>
                </div>

                <div class="form-group h35">
                    <div class="col-lg-4" style="float: left;">
                        <label class="control-label" for="tookPlaceOn">Data modificarii</label>
                    </div>
                    <div class="col-lg-4" style="float: left;">
                        <input id="tookPlaceOn" class="form-control" type="text" value="${log.tookPlaceOn}"/>
                    </div>
                </div>

                <div class="form-group h35">
                    <div class="col-lg-4" style="float: left;">
                        <label class="control-label" for="action">Actiunea</label>
                    </div>
                    <div class="col-lg-4" style="float: left;">
                        <input id="action" class="form-control" type="text" value="${log.action}"/>
                    </div>
                </div>

                <div class="form-group h35">
                    <div class="col-lg-4" style="float: left;">
                        <label class="control-label" for="action">Valoarea veche</label>
                    </div>
                    <div class="col-lg-4" style="float: left;">
                        <p>${log.oldValue}</p>
                    </div>
                </div>

                <div class="form-group h35">
                    <div class="col-lg-4" style="float: left;">
                        <label class="control-label" for="action">Valoarea noua</label>
                    </div>
                    <div class="col-lg-4" style="float: left;">
                        <p>${log.newValue}</p>
                    </div>
                </div>
                <button type="button" class="btn btn-default pull-right" style="margin-right: 15px;">Salveaz&#259;</button>
            </div>
    </div>
</body>
</html>
