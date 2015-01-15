/**
 * Created by Tudor on 1/5/2015.
 */
$(document).ready(function () {
    StructureHistoryManagerJS.init();
});

var StructureHistoryManagerJS = (function($) {

    var init = function() {
        $('#container').html($('#structure-details').html());

    };

    var renderAddPage = function () {
        var url = $("#addStructureHistoryPageURL").val();
        window.location.href = url;
    };

    var deleteStructureHistory = function() {
        var url = $("#deleteStructureHistoryURL").val() + $("#structureId").val();
        window.location.href = url;
    };

    var submitFilterForm = function () {
        var url = $('#structureHistoryFilterURL').val();
        var searchCriteria = $('#structureHistorySearchInput').val();
        var structureId = $('#structureId').val();
        var data = { searchCriteria : searchCriteria, structureId : structureId };
        CemeteryJs.ajaxCall("GET", url, data, 1, '#structure-details');
    };

    var getPerPage = function(pageNo) {
        var url = $("#structureHistoryURL").val();
        var data = { pageNo : pageNo };
        CemeteryJs.ajaxCall("GET", url, data, 0, "#structure-table");
    };

    var refreshFilter = function() {
        var url = $("#refreshStructureHistoryFilterURL").val();
        CemeteryJs.ajaxCall("POST", url, null, 1, '#structure-details');
    };

    return {
        init: init,
        renderAddPage : renderAddPage,
        deleteStructureHistory : deleteStructureHistory,
        submitFilterForm : submitFilterForm,
        getPerPage : getPerPage,
        refreshFilter : refreshFilter
    }
})(jQuery);