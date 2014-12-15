/**
 * Created by Catalin Sorecau on 11/15/2014.
 */
$(document).ready(function () {
    var pathURL = window.location.pathname;
    $('.submenu-item a').each(function(){
        var me = $(this);
        if (me.attr('href') == pathURL) {
            me.parent().css("background-color", "white");
            me.children("span").css("color", "black");
        }
    });

    $('.selectpicker').selectpicker();
});