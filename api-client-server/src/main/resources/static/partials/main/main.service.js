ssta.factory('MainService', ['$cookies', 'ServerService', function ($cookies, ServerService) {

    return {
        initUsers: function () {
            return ServerService.initUsers();
        },

        login: function(credentials) {
            return ServerService.login(credentials);
        },

        logout: function() {
            $cookies.remove('JJWT');
        }
    };
}]);