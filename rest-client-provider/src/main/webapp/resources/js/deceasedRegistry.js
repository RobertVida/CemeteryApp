/**
 * Created by Catalin Sorecau on 1/12/2015.
 */
$(document).ready(function() {
    $('#container').html($('#deceased-registry-details').html());

});
function getPerPage(pageNo) {
    var url = $('#drURL').val();
    var data = { pageNo : pageNo };
    CemeteryJs.ajaxCall("GET", url, data, 0, "#deceased-registry-table");
}

function applyRBFilter() {
    var url = $('#drFilterURL').val();
    var searchCriteria = $('#drSearchInput').val();
    console.log("WITHCAREGIVER: " + searchCriteria);
    var nameOrder = $("#nameOrder").val();
    var diedOnOrder = $("#diedOnOrder").val();
    var data = { searchCriteria : searchCriteria,
        nameOrder : nameOrder,
        diedOnOrder: diedOnOrder };
    CemeteryJs.ajaxCall("GET", url, data, 1, '#deceased-registry-details');
}

function refreshRBFilter() {
    var url = $("#drRefreshFilterURL").val();
    CemeteryJs.ajaxCall("POST", url, null, 1, '#deceased-registry-details');
}