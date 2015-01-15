/**
 * Created by Cata on 1/5/2015.
 */
$(document).ready(function () {
    ContractManagerJS.init();
});

var ContractManagerJS = (function($) {

    var init = function() {
        $('#container').html($('#contract-details').html());
        CemeteryJs.easyPagination(getContractPerPage);
    };

    var renderAddPage = function () {
        var url = $("#addContractPageURL").val();
        window.location.href = url;
    };

    var deleteContract = function() {
        var url = $("#deleteContractURL").val() + $("#contractId").val();
        window.location.href = url;
    };

    var submitFilterForm = function () {
        var url = $('#contractFilterURL').val();
        var structureId = $("#contractStructureIdInput").val();
        var data = { structureId : structureId };
        CemeteryJs.ajaxCall("GET", url, data, 1, '#contract-details', getContractPerPage);
    };

    var getContractPerPage = function(pageNo) {
        var url = $("#contractsURL").val();
        var data = { pageNo : pageNo };
        CemeteryJs.ajaxCall("GET", url, data, 0, "#contracts-table");
    };

    var refreshFilter = function() {
        var url = $("#refreshContractFilterURL").val();
        CemeteryJs.ajaxCall("POST", url, null, 1, '#contract-details', getContractPerPage);
    };

    return {
        init: init,
        renderAddPage : renderAddPage,
        deleteContract: deleteContract,
        submitFilterForm : submitFilterForm,
        getContractPerPage : getContractPerPage,
        refreshFilter : refreshFilter
    }
})(jQuery);
