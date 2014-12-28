/**
 * Created by Catalin Sorecau on 12/22/2014.
 */
var CemeteryJs = (function ($) {

    var ajaxCall = function (type, url, data, filter, param) {
        jQuery.ajax({
            type: type,
            url: url,
            cache: false,
            data: data,
            traditional: true,
            success: function (response) {
                if (filter) {
                    successFunctionWIthFilter(response, param);
                } else {
                    successFunctionWithFind(response, param);
                }
            },
            error: function (response) {
                errorFunction(response);
            }
        });
    };

    var successFunctionWIthFilter = function(response, param) {
        $("#container").html($(response).filter(param).html()).css("display", "inline");
    };

    var successFunctionWithFind = function(response, param) {
        $(param).html($(response).find(param).html()).css("display", "inline");
    };

    var errorFunction = function(response) {
      console.error(response);
    };

    var validateAndSubmitForm = function(form) {
        if ($(form).validate()) {
            $(form).submit();
        }
    };

    return {
        ajaxCall: ajaxCall,
        validateAndSubmitForm: validateAndSubmitForm
    }

})(jQuery);