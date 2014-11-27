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

        CemeteriesManagerJS.refreshFilter();
    };

    var renderAddPage = function () {
        var addPageURL = $("#addPageURL").val();
        $.ajax({
            type: "GET",
            url: addPageURL,
            success: function (response) {
                $('#container').html($(response).filter('#cemetery-details').html()).css("display: inline;");
            }
        });
    };

    var deleteCemetery = function() {
        var url = $("#deleteCemeteryURL").val() + $("#cemeteryId").val();
        console.log(url);
        window.location.href = url;
    };

    var submitFilterForm = function () {
        var url = $('#cemeteryFilterURL').val();
        var searchCriteria = $('#cemeterySearchInput');

        if (searchCriteria.val() != "") {
            if (searchCriteria.hasClass("required-input")) {
                searchCriteria.removeClass("required-input");
            }
            $.ajax({
                type: "GET",
                url: url,
                data: { "searchCriteria" : searchCriteria.val() },
                success: function (response) {
                    $('#container').html($(response).filter('#cemetery-details').html());
                    console.log($(response).filter('#cemetery-details').html());
                }
            });
        } else {
            searchCriteria.addClass("required-input");
        }
    };

    var getCemeteriesPerPage = function(pageNo) {
        var url = $("#cemeteriesURL").val();
        $.ajax({
            type: "GET",
            url: url,
            data: { "pageNo" : pageNo },
            success: function (response) {
                $('#cemeteries-table').html($(response).find('#cemeteries-table').html());
            }
        });
    };

    var refreshFilter = function() {
        var refreshFilterURL = $("#refreshFilterURL").val();
        $.ajax({
            type: "POST",
            url: refreshFilterURL,
            success: function (response) {
                $('#container').html($(response).filter('#clients-container').html());
            }
        });
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