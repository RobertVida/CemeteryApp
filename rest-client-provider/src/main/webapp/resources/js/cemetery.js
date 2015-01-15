/**
 * Created by Catalin Sorecau on 11/24/2014.
 */
$(document).ready(function () {
    CemeteriesManagerJS.init();
});

var CemeteriesManagerJS = (function($) {

    var init = function() {
        $('#container').html($('#cemetery-container').html());
        $('#container').html($('#cemetery-details').html());
        CemeteryJs.easyPagination(getCemeteriesPerPage);
    };

    var renderAddPage = function () {
        var url = $("#addPageURL").val();
        CemeteryJs.ajaxCall("GET", url, null, 1, '#cemetery-details');
    };

    var deleteCemetery = function() {
        var url = $("#deleteCemeteryURL").val() + $("#cemeteryId").val();
        window.location.href = url;
    };

    var submitFilterForm = function () {
        var url = $('#cemeteryFilterURL').val();
        var searchCriteria = $('#cemeterySearchInput').val();
        var data = { searchCriteria : searchCriteria };
        CemeteryJs.ajaxCall("GET", url, data, 1, '#cemetery-details', getCemeteriesPerPage);
    };

    var getCemeteriesPerPage = function(pageNo) {
        var url = $("#cemeteriesURL").val();
        var data = { pageNo : pageNo };
        CemeteryJs.ajaxCall("GET", url, data, 0, '#cemeteries-table');
    };

    var refreshFilter = function() {
        var url = $("#refreshFilterURL").val();
        CemeteryJs.ajaxCall("POST", url, null, 1, '#cemetery-details', getCemeteriesPerPage);
    };

    return {
        init: init,
        renderAddPage : renderAddPage,
        deleteCemetery: deleteCemetery,
        submitFilterForm : submitFilterForm,
        getCemeteriesPerPage : getCemeteriesPerPage,
        refreshFilter : refreshFilter
    }
})(jQuery);