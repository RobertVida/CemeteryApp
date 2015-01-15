/**
 * Created by Catalin Sorecau on 12/22/2014.
 */
var CemeteryJs = (function ($) {

    var ajaxCall = function (type, url, data, filter, param, method) {
        jQuery.ajax({
            type: type,
            url: url,
            cache: false,
            data: data,
            traditional: true,
            success: function (response) {
                if (filter) {
                    successFunctionWIthFilter(response, param, method);
                } else {
                    successFunctionWithFind(response, param);
                }
            },
            error: function (response) {
                errorFunction(response);
            }
        });
    };

    var successFunctionWIthFilter = function(response, param, method) {
        $("#container").html($(response).filter(param).html()).css("display", "inline");
        easyPagination(method);
    };

    var successFunctionWithFind = function(response, param) {
        $(param).html($(response).find(param).html()).css("display", "table");
    };

    var errorFunction = function(response) {
      console.error(response);
    };

    var validateAndSubmitForm = function(form) {
        if ($(form).validate()) {
            $(form).submit();
        }
    };

    var easyPagination = function(method) {
        $(".easypagination").easyPaginate({
            onClickcallback: function (page) {
                method(page);
            }
        });
    };

    return {
        ajaxCall: ajaxCall,
        validateAndSubmitForm: validateAndSubmitForm,
        easyPagination: easyPagination
    }

})(jQuery);