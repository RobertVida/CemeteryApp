/**
 * Created by Catalin Sorecau on 12/14/2014.
 */
$(document).ready(function () {
    LogsManagerJS.init();
});

var LogsManagerJS = (function($) {

    var init = function() {
        $('#container').html($('#log-details').html());
    };

    var getLogsPerPage = function(pageNo) {
        var url = $('#logsUrl').val();
        var data = { pageNo : pageNo };
        CemeteryJs.ajaxCall("GET", url, data, 0, "#logs-table");
    };

    var submitFilter = function() {
        var url = $('#logsFilterURL').val();
        var tableName = $( "#tableSelect").find("option:selected" ).val();
        if (tableName == "--Tabela--") {
            tableName = "";
        }
        var tableId = $("#tableIdInput").val();
        var searchCriteria = $("#logSearchInput").val();

        var data = { searchCriteria : searchCriteria, tableName : tableName, tableId : tableId };
        CemeteryJs.ajaxCall("GET", url, data, 1, '#log-details');
    };

    var refreshFilter = function() {
        var url = $("#refreshLogFilterURL").val();
        CemeteryJs.ajaxCall("POST", url, null, 1, '#log-details');
    };

    return {
        init: init,
        submitFilter: submitFilter,
        refreshFilter: refreshFilter,
        getLogsPerPage: getLogsPerPage
    };
})(jQuery);