app
    .factory('authorizationService', function (serverService, $cookies) {

        return {
            login: function(credentials) {
                return serverService.login(credentials);
            },

            logout: function() {
                $cookies.remove('JJWT');
            }
        };

    });