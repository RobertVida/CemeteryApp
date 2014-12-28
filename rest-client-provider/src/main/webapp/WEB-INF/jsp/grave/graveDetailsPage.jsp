<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 11/30/2014
  Time: 7:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Morminte</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/graves.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/zebra_datepicker.src.js"></script>
</head>
<body>
<jsp:include page="../fragments/menu.jsp"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}/grave"/>
    <div id="grave-details" style="margin-top: 20px; display: none;">
        <h4 class="text-center">
            <b>Informa&#355;iile mormantului</b>
            <c:choose>
                <c:when test="${view eq true}">
                    <c:set var="actionURL" value="${contextPath}/update"/>
                </c:when>
                <c:otherwise>
                    <c:set var="actionURL" value="${contextPath}/add"/>
                </c:otherwise>
            </c:choose>
        </h4>
        <form:form id="graveForm" action="${actionURL}" commandName="grave" method="post">
            <div class="details">
                <div class="form-group h35">
                    <form:input id="graveId" path="id" class="form-control" type="hidden"/>
                    <div class="col-lg-4" style="float: left;">
                        <form:label class="control-label" path="parcelId">Id-ul parcelei</form:label>
                    </div>
                    <div class="col-lg-4" style="float: left;">
                        <form:input path="parcelId" class="form-control" type="text" pattern="\d*" required="true"/>
                    </div>
                    <div class="col-lg-4" style="float: left; color: red">
                        <form:errors path="parcelId"/>
                    </div>
                </div>

                <div class="form-group h35">
                    <div class="col-lg-4" style="float: left;">
                        <form:label class="control-label" path="createdOn">Creat in</form:label>
                    </div>
                    <div class="col-lg-4" style="float: left;">
                        <form:input id="createdOn" path="createdOn" class="form-control" type="text" required="true"/>
                    </div>
                    <div class="col-lg-4" style="float: left; color: red">
                        <form:errors path="createdOn"/>
                    </div>
                </div>

                <form:input path="type" type="hidden"/>

                <div class="form-group h35">
                    <div class="col-lg-4" style="float: left;">
                        <form:label class="control-label" path="width">Latime</form:label>
                    </div>
                    <div class="col-lg-4" style="float: left;">
                        <form:input path="width" class="form-control" pattern="\d*" type="text" required="true"/>
                    </div>
                    <div class="col-lg-4" style="float: left; color: red">
                        <form:errors path="width"/>
                    </div>
                </div>

                <div class="form-group h35">
                    <div class="col-lg-4" style="float: left;">
                        <form:label class="control-label" path="length">Lungime</form:label>
                    </div>
                    <div class="col-lg-4" style="float: left;">
                        <form:input path="length" class="form-control" pattern="\d*" type="text" required="true"/>
                    </div>
                    <div class="col-lg-4" style="float: left; color: red">
                        <form:errors path="length"/>
                    </div>
                </div>

                <c:if test="${view eq true}">
                    <input type="button" onclick="GraveManagerJS.deleteGrave();" value="Sterge" class="btn btn-default pull-right" style="margin-right: 15px;"/>
                </c:if>
                <input id="saveGrave" onclick="CemeteryJs.validateAndSubmitForm('#graveForm');" type="submit" class="btn btn-default pull-right" style="margin-right: 15px;" value="Salveaz&#259;" />
            </div>
        </form:form>
    </div>
<input id="deleteGraveURL" type="hidden" value="${contextPath}/delete/"/>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function() {
        var $createdOn = $("#createdOn");
        $createdOn.Zebra_DatePicker({
            format: "d/m/Y",
            changeMonth: true,
            changeYear: true,
            yearRange: "1960:+50",
            show_icon: false
        });

        $createdOn.keydown(function() {
            return false;
        });
    });
</script>