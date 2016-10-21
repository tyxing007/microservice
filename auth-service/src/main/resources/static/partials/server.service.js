ssta.factory('ServerService', ['$http', function ($http) {

    var serverURLs = {
        initUsersGET: "/uaa/api/init",
        authentificationPOST: "/uaa/api/auth",
        usersGET: "/uaa/api/users"
    };

    return {
        initUsers: function () {
            return $http.get(serverURLs.initUsersGET);
        },

        login: function (credentials) {
            return $http.post(serverURLs.authentificationPOST, credentials);
        },

        getUsers: function () {
            return $http.get(serverURLs.usersGET);
        }
    };
}]);