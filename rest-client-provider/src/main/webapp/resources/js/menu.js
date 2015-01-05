/**
 * Created by Catalin Sorecau on 11/15/2014.
 */
$(document).ready(function () {
    var pathURL = window.location.pathname;
    $('.submenu-item a').each(function(){
        var me = $(this);
        if (pathURL.indexOf(me.attr('href')) > -1) {
            me.parent().css("background-color", "white");
            me.children("span").css("color", "black");
        }
    });
    $('.tiptip').tipTip();
});