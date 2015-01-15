/**
 * Created by Catalin Sorecau on 11/15/2014.
 */
$(document).ready(function () {
    DeceasedManagerJS.init();
});

var DeceasedManagerJS = (function($) {

    var init = function() {
        $('#container').html($('#deceased-details').html());
        CemeteryJs.easyPagination(getPerPage);
    };

    var renderAddPage = function () {
        var url = $("#addDeceasedPageURL").val();
        window.location.href = url;
    };

    var deleteDeceased = function() {
        var url = $("#deleteDeceasedURL").val() + $("#deceasedId").val();
        window.location.href = url;
    };

    var submitFilterForm = function () {
        var url = $('#deceasedFilterURL').val();
        var searchCriteria = $('#deceasedSearchInput').val();
        var structureId = $('#structureId').val();
        var data = { searchCriteria : searchCriteria, structureId : structureId };
        CemeteryJs.ajaxCall("GET", url, data, 1, '#deceased-details', getPerPage);
    };

    var getPerPage = function(pageNo) {
        var url = $("#deceasedURL").val();
        var data = { pageNo : pageNo };
        CemeteryJs.ajaxCall("GET", url, data, 0, "#deceased-table");
    };

    var refreshFilter = function() {
        var url = $("#refreshDeceasedFilterURL").val();
        CemeteryJs.ajaxCall("POST", url, null, 1, '#deceased-details', getPerPage);
    };

    return {
        init: init,
        renderAddPage : renderAddPage,
        deleteDeceased : deleteDeceased,
        submitFilterForm : submitFilterForm,
        getPerPage : getPerPage,
        refreshFilter : refreshFilter
    }
})(jQuery);