/**
 * Created by Catalin Sorecau on 12/14/2014.
 */
$(document).ready(function () {
    LogsManagerJS.init();
});

var LogsManagerJS = (function($) {

    var init = function() {
        $('#container').html($('#log-details').html());
        LogsManagerJS.refreshFilter();
    };

    var submitFilter = function() {
        var url = $('#logsFilterURL').val();
        var tableName = $( "#tableSelect").find("option:selected" ).val();
        if (tableName == "--Tabela--") {
            tableName = "";
        }
        var tableId = $("#tableIdInput");
        var searchCriteria = $("#logSearchInput").val();

        $.ajax({
            type: "GET",
            url: url,
            data: {
                "searchCriteria" : searchCriteria,
                "tableName" : tableName,
                "tableId" : tableId.val()
            },
            success: function (response) {
                $('#container').html($(response).filter('#log-details').html());
            }
        });
    };

    var refreshFilter = function() {
        var refreshFilterURL = $("#refreshLogFilterURL").val();
        $.ajax({
            type: "POST",
            url: refreshFilterURL,
            success: function (response) {
                $('#container').html($(response).filter('#grave-details').html());
            }
        });
    };

    return {
        init: init,
        submitFilter: submitFilter,
        refreshFilter: refreshFilter
    };
})(jQuery);