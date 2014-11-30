/**
 * Created by Catalin Sorecau on 11/15/2014.
 */
$(document).ready(function () {
    $('#container').html($('#client-details').html());

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
                type: "GET",
                url: url,
                data: { "searchCriteria" : searchCriteria.val() },
                success: function (response) {
                    $('#container').html($(response).filter('#client-details').html());
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
                $('#container').html($(response).filter('#client-details').html());
            }
        });
    };

    var renderAddPage = function () {
        var addPageURL = $("#addPageURL").val();
        $.ajax({
            type: "GET",
            url: addPageURL,
            success: function (response) {
                $('#container').html($(response).filter('#client-details').html()).css("display: inline;");
            }
        });
    };

    var deleteClient = function() {
        var url = $("#deleteClientURL").val() + $("#clientId").val();
        console.log(url);
        window.location.href = url;
    };

    return {
        init: init,
        submitFilterForm : submitFilterForm,
        refreshFilter : refreshFilter,
        renderAddPage : renderAddPage,
        deleteClient : deleteClient
    }
})(jQuery);

