/**
 * Header Controller
 *
 * @author
 */
app
    .controller('HeaderController', function ($scope) {

    });
app.controller('AppCtrl', function() {

});

app.controller('DropdownCtrl', function ($scope, $log) {
    $scope.items = [
        'The first choice!',
        'And another choice for you.',
        'but wait! A third!'
    ];

});

$(function () {
    // Remove Search if user Resets Form or hits Escape!
    $('body').on('keyup', function(event) {
        if (event.which == 27 && $('.navbar-collapse form[role="search"]').hasClass('active')) {
            closeSearch();
        }
    }).on('click', '.navbar-collapse form[role="search"] button[type="reset"]', function() {
        closeSearch();
    });
    function closeSearch() {
        var $form = $('.navbar-collapse form[role="search"].active');
        $form.find('input').val('');
        $form.removeClass('active');
    }
    // Show Search if form is not active // event.preventDefault() is important, this prevents the form from submitting
    $(document).on('click', '.navbar-collapse form[role="search"]:not(.active) button[type="submit"]', function(event) {
        event.preventDefault();
        var $form = $(this).closest('form'),
            $input = $form.find('input');
        $form.addClass('active');
        $input.focus();

    });
    // ONLY FOR DEMO // Please use $('form').submit(function(event)) to track from submission
    // if your form is ajax remember to call `closeSearch()` to close the search container
    // $(document).on('click', '.navbar-collapse form[role="search"].active button[type="submit"]', function(event) {
    //     event.preventDefault();
    //     var $form = $(this).closest('form'),
    //         $input = $form.find('input');
    //     $('#showSearchTerm').text($input.val());
    //     // closeSearch()
    // });
});