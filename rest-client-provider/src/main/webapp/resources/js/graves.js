$(document).ready(function () {
    GraveManagerJS.init();
});

var GraveManagerJS = (function($) {

    var init = function() {
        $('#container').html($('#grave-details').html());

        GraveManagerJS.refreshFilter();
    };

    var renderAddPage = function () {
        var addPageURL = $("#addGravePageURL").val();
        $.ajax({
            type: "GET",
            url: addPageURL,
            success: function (response) {
                $('#container').html($(response).filter('#grave-details').html()).css("display: inline;");
            }
        });
    };

    var deleteGrave = function() {
        var url = $("#deleteGraveURL").val() + $("#graveId").val();
        window.location.href = url;
    };

    var submitFilterForm = function () {
        var url = $('#graveFilterURL').val();
        var searchCriteria = $('#graveSearchInput');
        var parcelId = $("#graveParcelIdInput");
        if (searchCriteria.val() != "") {
            if (searchCriteria.hasClass("required-input")) {
                searchCriteria.removeClass("required-input");
            }
            $.ajax({
                type: "GET",
                url: url,
                data: {
                    "searchCriteria" : searchCriteria.val(),
                    "parcelId" : parcelId.val()
                },
                success: function (response) {
                    $('#container').html($(response).filter('#grave-details').html());
                    console.log($(response).filter('#grave-details').html());
                }
            });
        } else {
            searchCriteria.addClass("required-input");
        }
    };

    var getGravePerPage = function(pageNo) {
        var url = $("#gravesURL").val();
        $.ajax({
            type: "GET",
            url: url,
            data: { "pageNo" : pageNo },
            success: function (response) {
                $('#grave-table').html($(response).find('#grave-table').html());
            }
        });
    };

    var refreshFilter = function() {
        var refreshFilterURL = $("#refreshFilterURL").val();
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
        renderAddPage : renderAddPage,
        deleteGrave: deleteGrave,
        submitFilterForm : submitFilterForm,
        getGravePerPage : getGravePerPage,
        refreshFilter : refreshFilter
    }
})(jQuery);