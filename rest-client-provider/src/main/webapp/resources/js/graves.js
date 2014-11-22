$(document).ready(function () {
    $('#container').html($('#graves-container').html());

    $('#container').html($('#grave-details').html());

    $(".dropdown-menu li a").click(function(){
      var selText = $(this).text();
      $(this).parents('.btn-group').find('.dropdown-toggle').html(selText+' <span class="caret"></span>');
    });
});