/**
 * Created by Catalin Sorecau on 12/27/2014.
 */
$(document).ready(function () {
    MonumentsManagerJS.init();
});

var MonumentsManagerJS = (function($) {

    var init = function() {
        $('#container').html($('#monument-details').html());

        $(".easypagination").easyPaginate({
            onClickcallback : function(page) {
                getLogsPerPage(page);
            }
        });
    };

    var renderAddPage = function () {
        var url = $("#addMonumentPageURL").val();
        window.location.href = url;
    };

    var deleteMonument = function() {
        var url = $("#deleteMonumentURL").val() + $("#monumentId").val();
        window.location.href = url;
    };

    var submitFilterForm = function () {
        var url = $('#monumentFilterURL').val();
        var searchCriteria = $('#monumentSearchInput').val();
        var parcelId = $("#monumentParcelIdInput").val();
        var data = { searchCriteria : searchCriteria, parcelId : parcelId };
        CemeteryJs.ajaxCall("GET", url, data, 1, '#monument-details');
    };

    var getMonumentPerPage = function(pageNo) {
        var url = $("#monumentsURL").val();
        var data = { pageNo : pageNo };
        CemeteryJs.ajaxCall("GET", url, data, 0, "#monuments-table");
    };

    var refreshFilter = function() {
        var url = $("#refreshMonumentFilterURL").val();
        CemeteryJs.ajaxCall("POST", url, null, 1, '#monument-details');
    };

    return {
        init: init,
        renderAddPage : renderAddPage,
        deleteMonument: deleteMonument,
        submitFilterForm : submitFilterForm,
        getMonumentPerPage : getMonumentPerPage,
        refreshFilter : refreshFilter
    }
})(jQuery);
