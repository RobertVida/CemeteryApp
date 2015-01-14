/**
 * Created by Catalin Sorecau on 11/24/2014.
 */
$(document).ready(function () {
    ParcelsManagerJS.init();
});

var ParcelsManagerJS = (function($) {

    var init = function() {
        $('#container').html($('#parcel-details').html());

        $(".easypagination").easyPaginate({
            onClickcallback : function(page) {
                getParcelsPerPage(page);
            }
        });
    };

    var renderAddPage = function () {
        var url = $("#addParcelPageURL").val();
        CemeteryJs.ajaxCall("GET", url, null, 1, '#parcel-details');
    };

    var deleteParcel = function() {
        var url = $("#deleteParcelURL").val() + $("#parcelId").val();
        window.location.href = url;
    };

    var submitFilterForm = function () {
        var url = $('#parcelFilterURL').val();
        var searchCriteria = $('#parcelSearchInput').val();
        var cemeteryId = $('#parcelCemeteryId').val();
        var data = { searchCriteria : searchCriteria, cemeteryId : cemeteryId };
        CemeteryJs.ajaxCall("GET", url, data, 1, '#parcel-details');
    };

    var getParcelsPerPage = function(pageNo) {
        var url = $("#parcelsURL").val();
        var data = { pageNo : pageNo };
        CemeteryJs.ajaxCall("GET", url, data, 0, "#parcels-table");
    };

    var refreshFilter = function() {
        var url = $("#refreshParcelFilterURL").val();
        CemeteryJs.ajaxCall("POST", url, null, 1, '#parcel-details');
    };

    return {
        init: init,
        renderAddPage : renderAddPage,
        deleteParcel: deleteParcel,
        submitFilterForm : submitFilterForm,
        getParcelsPerPage : getParcelsPerPage,
        refreshFilter : refreshFilter
    }
})(jQuery);