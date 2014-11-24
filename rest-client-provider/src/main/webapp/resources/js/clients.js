/**
 * Created by Catalin Sorecau on 11/15/2014.
 */
$(document).ready(function () {
    $('#container').html($('#clients-container').html());

    $('#container').html($('#client-details').html());

    $(".dropdown-menu li a").click(function(){
        var selText = $(this).text();
        $(this).parents('.btn-group').find('.dropdown-toggle').html(selText+' <span class="caret"></span>');
    });

    ClientsManagerJS.init();
    ClientsManagerJS.refreshFilter();
});
var ClientsManagerJS = (function($) {

    var init = function() {
        $(".page").click(function() {
            var pageNumber = $(this).attr("page-number");
            getClientsPerPage(pageNumber);
        });

    };

    var getClientsPerPage = function(pageNo) {
        var url = $("#getClientsURL").val();
        $.ajax({
            type: "GET",
            url: url,
            data: { "pageNo" : pageNo },
            success: function (response) {
                $('#clients-table').html($(response).find('#clients-table').html());
            }
        });
    };

    var submitFilterForm = function () {
        var url = $('#filterURL').val();
        var searchCriteria = $('#searchInput');

        if (searchCriteria.val() != "") {
            if (searchCriteria.hasClass("required-input")) {
                searchCriteria.removeClass("required-input");
            }
            $.ajax({
                type: "POST",
                url: url,
                data: { "searchCriteria" : searchCriteria.val() },
                success: function (response) {
                    $('#container').html($(response).filter('#clients-container').html());
                }
            });
        } else {
            searchCriteria.addClass("required-input");
        }
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

    var renderAddPage = function () {
        var addPageURL = $("#addPageURL").val();
        $.ajax({
            type: "GET",
            url: addPageURL,
            success: function (response) {
                $('#container').html($(response).filter('#client-add-details').html()).css("display: inline;");
            }
        });
    };

    return {
        init: init,
        submitFilterForm : submitFilterForm,
        refreshFilter : refreshFilter,
        renderAddPage : renderAddPage
    }
})(jQuery);

