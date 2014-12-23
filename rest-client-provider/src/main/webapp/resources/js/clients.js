/**
 * Created by Catalin Sorecau on 11/15/2014.
 */
$(document).ready(function () {
    $('#container').html($('#client-details').html());

    ClientsManagerJS.init();
});
var ClientsManagerJS = (function($) {

    var init = function() {
        $(".page").click(function() {
            var pageNumber = $(this).attr("page-number");
            getClientsPerPage(pageNumber);
        });

    };

    var getClientsPerPage = function(pageNo) {
        var url = $("#getClientsURL").val();
        var data = { pageNo : pageNo };
        CemeteryJs.ajaxCall("GET", url, data, 0, "#clients-table");
    };

    var submitFilterForm = function () {
        var url = $('#filterURL').val();
        var searchCriteria = $('#searchInput').val();
        var data = { searchCriteria : searchCriteria };
        CemeteryJs.ajaxCall("GET", url, data, 1, '#client-details');
    };

    var refreshFilter = function() {
        var url = $("#refreshFilterURL").val();
        CemeteryJs.ajaxCall("POST", url, null, 1, '#client-details');
    };

    var renderAddPage = function () {
        var url = $("#addPageURL").val();
        CemeteryJs.ajaxCall("GET", url, null, 1, '#client-details');
    };

    var deleteClient = function() {
        var url = $("#deleteClientURL").val() + $("#clientId").val();
        window.location.href = url;
    };

    return {
        init: init,
        submitFilterForm : submitFilterForm,
        refreshFilter : refreshFilter,
        renderAddPage : renderAddPage,
        deleteClient : deleteClient
    }
})(jQuery);

