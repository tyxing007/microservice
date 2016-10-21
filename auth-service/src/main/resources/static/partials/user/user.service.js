ssta.factory('UserService', ['ServerService', function (ServerService) {

    return {
        getUsers: function () {
            return ServerService.getUsers();
        }
    };

}]);