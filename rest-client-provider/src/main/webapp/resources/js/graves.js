$(document).ready(function () {
    GraveManagerJS.init();
});

var GraveManagerJS = (function($) {

    var init = function() {
        $('#container').html($('#grave-details').html());
        CemeteryJs.easyPagination(getGravePerPage);
    };

    var renderAddPage = function () {
        var url = $("#addGravePageURL").val();
        window.location.href = url;
    };

    var deleteGrave = function() {
        var url = $("#deleteGraveURL").val() + $("#graveId").val();
        window.location.href = url;
    };

    var submitFilterForm = function () {
        var url = $('#graveFilterURL').val();
        var parcelId = $("#graveParcelIdInput").val();
        var data = { parcelId : parcelId };
        CemeteryJs.ajaxCall("GET", url, data, 1, '#grave-details', getGravePerPage);
    };

    var getGravePerPage = function(pageNo) {
        var url = $("#gravesURL").val();
        var data = { pageNo : pageNo };
        CemeteryJs.ajaxCall("GET", url, data, 0, "#graves-table");
    };

    var refreshFilter = function() {
        var url = $("#refreshGraveFilterURL").val();
        CemeteryJs.ajaxCall("POST", url, null, 1, '#grave-details', getGravePerPage);
    };

    return {
        init: init,
        renderAddPage : renderAddPage,
        deleteGrave: deleteGrave,
        submitFilterForm : submitFilterForm,
        getGravePerPage : getGravePerPage,
        refreshFilter : refreshFilter
    }
})(jQuery);