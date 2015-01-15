/**
 * Created by Catalin Sorecau on 1/12/2015.
 */
$(document).ready(function() {
    $('#container').html($('#deceased-no-caregiver-registry-details').html());

});

function getPerPage(pageNo) {
    var url = $('#dncrURL').val();
    var data = { pageNo : pageNo };
    CemeteryJs.ajaxCall("GET", url, data, 0, "#deceased-no-caregiver-registry-table");
}

function applyRBFilter() {
    var url = $('#dncrFilterURL').val();
    var searchCriteria = $('#dncrSearchInput').val();
    console.log("NOCAREGIVER: " + searchCriteria);
    var nameOrder = $("#nameOrder").val();
    var diedOnOrder = $("#diedOnOrder").val();
    var data = { searchCriteria : searchCriteria,
        nameOrder : nameOrder,
        diedOnOrder: diedOnOrder };
    CemeteryJs.ajaxCall("GET", url, data, 1, '#deceased-no-caregiver-registry-details');
}

function refreshRBFilter() {
    var url = $("#dncrRefreshFilterURL").val();
    CemeteryJs.ajaxCall("POST", url, null, 1, '#deceased-no-caregiver-registry-details');
}