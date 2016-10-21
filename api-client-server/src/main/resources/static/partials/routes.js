ssta.config(function ($stateProvider) {
    $stateProvider

    .state('main', {
        url: '/main',
        controller: 'MainController',
        templateUrl: 'partials/main/main.template.html'
    })

    .state('main.admin', {
        url: '^/admin',
        controller: 'AdminController',
        templateUrl: 'partials/admin/admin.template.html'
    })

    .state('main.user', {
        url: '^/user',
        controller: 'UserController',
        templateUrl: 'partials/user/user.template.html'
    })
});