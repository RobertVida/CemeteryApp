/**
 * Created by Catalin Sorecau on 12/27/2014.
 */
$(document).ready(function () {
    RequestsManagerJS.init();
});

var RequestsManagerJS = (function($) {

    var  init = function() {
        $('#container').html($('#request-details').html());
        CemeteryJs.easyPagination(getRequestsPerPage);
    };

    var renderAddPage = function () {
        var url = $("#addRequestPageURL").val();
        window.location.href = url;
    };

    var deleteRequest = function() {
        var url = $("#deleteRequestURL").val() + $("#requestId").val();
        window.location.href = url;
    };

    var submitFilterForm = function () {
        var url = $('#requestFilterURL').val();
        var searchCriteria = $('#requestSearchInput').val();
        var clientId = $('#requestClientId').val();
        var status = $('#requestStatusInput').val();
        var data = { searchCriteria : searchCriteria, clientId : clientId, status : status };
        CemeteryJs.ajaxCall("GET", url, data, 1, '#request-details', getRequestsPerPage);
    };

    var getRequestsPerPage = function(pageNo) {
        var url = $("#requestsURL").val();
        var data = { pageNo : pageNo };
        CemeteryJs.ajaxCall("GET", url, data, 0, "#requests-table");
    };

    var refreshFilter = function() {
        var url = $("#refreshRequestFilterURL").val();
        CemeteryJs.ajaxCall("POST", url, null, 1, '#request-details', getRequestsPerPage);
    };

    return {
        init: init,
        renderAddPage : renderAddPage,
        deleteRequest: deleteRequest,
        submitFilterForm : submitFilterForm,
        getRequestsPerPage : getRequestsPerPage,
        refreshFilter : refreshFilter
    }
})(jQuery);