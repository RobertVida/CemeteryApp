/**
 * Created by Catalin Sorecau on 11/24/2014.
 */
$(document).ready(function () {
    ParcelsManagerJS.init();
});

var ParcelsManagerJS = (function($) {

    var init = function() {
        $('#container').html($('#parcel-details').html());

        ParcelsManagerJS.refreshFilter();
    };

    var renderAddPage = function () {
        var addPageURL = $("#addParcelPageURL").val();
        $.ajax({
            type: "GET",
            url: addPageURL,
            success: function (response) {
                $('#container').html($(response).filter('#parcel-details').html()).css("display: inline;");
            }
        });
    };

    var deleteParcel = function() {
        var url = $("#deleteParcelURL").val() + $("#parcelId").val();
        console.log(url);
        window.location.href = url;
    };

    var submitFilterForm = function () {
        var url = $('#parcelFilterURL').val();
        var searchCriteria = $('#parcelSearchInput');
        var cemeteryId = $('#parcelCemeteryId');
        $.ajax({
            type: "GET",
            url: url,
            data: {
                "searchCriteria" : searchCriteria.val(),
                "cemeteryId" : cemeteryId.val()
            },
            success: function (response) {
                $('#container').html($(response).filter('#parcel-details').html());
                console.log($(response).filter('#parcel-details').html());
            }
        });
    };

    var getParcelsPerPage = function(pageNo) {
        var url = $("#parcelsURL").val();
        $.ajax({
            type: "GET",
            url: url,
            data: { "pageNo" : pageNo },
            success: function (response) {
                $('#parcels-table').html($(response).find('#parcels-table').html());
            }
        });
    };

    var refreshFilter = function() {
        var refreshFilterURL = $("#refreshParcelFilterURL").val();
        $.ajax({
            type: "POST",
            url: refreshFilterURL,
            success: function (response) {
                $('#container').html($(response).filter('#parcel-details').html());
            }
        });
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