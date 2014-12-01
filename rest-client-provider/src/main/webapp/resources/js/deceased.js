/**
 * Created by Catalin Sorecau on 11/15/2014.
 */
$(document).ready(function () {
    DeceasedManagerJS.init();
});

var DeceasedManagerJS = (function($) {

    var init = function() {
        $('#container').html($('#deceased-details').html());

        DeceasedManagerJS.refreshFilter();
    };

    var renderAddPage = function () {
        var addPageURL = $("#addDeceasedPageURL").val();
        $.ajax({
            type: "GET",
            url: addPageURL,
            success: function (response) {
                $('#container').html($(response).filter('#deceased-details').html()).css("display: inline;");
            }
        });
    };

    var deleteDeceased = function() {
        var url = $("#deleteDeceasedURL").val() + $("#deceasedId").val();
        window.location.href = url;
    };

    var submitFilterForm = function () {
        var url = $('#deceasedFilterURL').val();
        var searchCriteria = $('#deceasedSearchInput');
        var structureId = $('#structureId');
        //TODO validate cemeteryId
        if (searchCriteria.val() != "") {
            if (searchCriteria.hasClass("required-input")) {
                searchCriteria.removeClass("required-input");
            }
            $.ajax({
                type: "GET",
                url: url,
                data: {
                    "searchCriteria" : searchCriteria.val(),
                    "cemeteryId" : structureId.val()
                },
                success: function (response) {
                    $('#container').html($(response).filter('#deceased-details').html());
                }
            });
        } else {
            searchCriteria.addClass("required-input");
        }
    };

    var getPerPage = function(pageNo) {
        var url = $("#deceasedURL").val();
        $.ajax({
            type: "GET",
            url: url,
            data: { "pageNo" : pageNo },
            success: function (response) {
                $('#deceased-table').html($(response).find('#deceased-table').html());
            }
        });
    };

    var refreshFilter = function() {
        var refreshFilterURL = $("#refreshDeceasedFilterURL").val();
        $.ajax({
            type: "POST",
            url: refreshFilterURL,
            success: function (response) {
                $('#container').html($(response).filter('#deceased-details').html());
            }
        });
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