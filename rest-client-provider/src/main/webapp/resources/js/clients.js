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
});
var ClientsManagerJS = (function($) {

    var init = function() {
        $(".page").click(function() {
            var pageNumber = $(this).attr("page-number");
            getClientsPerPage(pageNumber);
        });

        $("#addButton").click(function () {
            var addPageURL = $("#addPageURL").val();
            $.ajax({
                type: "GET",
                url: addPageURL,
                success: function (response) {
                    $('#container').html($(response).filter('#client-add-details').html()).css("display: inline;");
                }
            })
        });

        $("#refreshFilter").click(function() {
            var refreshFilterURL = $("#refreshFilterURL").val();
            $.ajax({
                type: "POST",
                url: refreshFilterURL,
                success: function (response) {
                    $('#container').html($(response).filter('#clients-container').html());
                }
            });
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

    return {
        init: init
    }
})(jQuery);

